package bank;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.exceptions.BankTransactionException;
import bank.exceptions.BankTransactions;
import bank.exceptions.ValidationException;
import bank.helpers.*;
import bank.ui.ConsoleMenu;

import java.util.*;

public class Main {
    /**Репозиторий сущностей**/
    private static final BankRepository rep = new BankRepository();
    /**Логгер**/
    private static final Logger logger = new ConsoleLogger();

    public static void main(String[] args) {
        try {
            ServiceHandler services = new ServiceHandler(rep);
            Startup startup = new Startup(services);
            startup.initEntities();

            Scanner sc = new Scanner(System.in);
            System.out.print("Представтесь системе (введите id клиента)...");
            int userId = sc.nextInt();
            User curUser = services.getUserService().getUser(userId);

            String[] variants = new String[] {
                    "Получить кредит",
                    "Перенести платежные счета по банку",
                    "Перенести кредитные счета по платежному",
                    "Выход"
            };

            int userInput = ConsoleMenu.getVariant("Что бы вы хотели сделать?", variants);
            while(userInput != 3) {
                switch (userInput) {
                    case 0 -> getCredit(curUser, services);
                    case 1 -> migratePayAccs(curUser, services);
                    case 2 -> migrateCredAccs(curUser, services);
                }
                userInput = ConsoleMenu.getVariant("Хотите еще что-нибудь?", variants);
            }

            ConsoleMenu.showTitle("Будем рады видеть вас снова!");
        }
        catch(Exception ex) {
            logger.logError("Ошибка работы приложения: " + ex.getMessage());
        }
    }

    /**
     * Перенос кредитных счетов
     * @param user Пользователь
     * @param services Хранилище сервисов
     * @throws Exception Ошибка переноса счетов
     */
    private static void migrateCredAccs(User user, ServiceHandler services) throws Exception {
        int[] payAccIds = user.creditAccounts.stream()
                .map(x -> x.paymentAccountId).distinct()
                .mapToInt(Integer::intValue).toArray();
        Collection<PaymentAccount> payAccs = services.getPaymentAccountService().getAll().stream()
                .filter(x -> Arrays.stream(payAccIds).anyMatch(y -> y == x.id))
                .toList();
        PaymentAccount chosenPayAcc = ConsoleMenu.getObjectFromUser(
                "Выберите платежный счет для переноса с него кредитных счетов (id)",
                payAccs);
        logger.log("Выгрузка начата");
        services.getUserService().sendCredAccounts(user.id, chosenPayAcc.id, "credAccs.txt");
        logger.log("Успешно!");

        PaymentAccount newPayAcc = ConsoleMenu.getObjectFromUser(
                "Выберите новый платежный счет (id)",
                user.paymentAccounts);
        Collection<CreditAccount> migrated =
                services.getCreditAccountService().migrateFromSource("credAccs.txt", newPayAcc.id);
        ConsoleMenu.printCollection("Перенесенные кредитные счета", migrated);
    }

    /**
     * Перенос платежных счетов пользователя
     * @param user Пользователь
     * @param services Хранилище сервисов
     * @throws Exception Ошибка переноса счетов
     */
    private static void migratePayAccs(User user, ServiceHandler services) throws Exception {
        int[] bankIds = user.paymentAccounts.stream().map(x -> x.bankId).distinct().mapToInt(Integer::intValue).toArray();
        Collection<Bank> banks = services.getBankService().getAll()
                .stream().filter(x ->
                        Arrays.stream(bankIds).anyMatch(y -> y == x.id))
                .toList();
        Bank bank = ConsoleMenu.getObjectFromUser(
                "У вас есть счета в указанных банках. Выберите один для выгрузки счетов (id)",
                banks);
        logger.log("Выгрузка начата");
        services.getUserService().sendPayAccounts(user.id, bank.id, "payAccs.txt");
        logger.log("Успешно!");

        banks = services.getBankService().getAll();
        bank = ConsoleMenu.getObjectFromUser(
                "Выберите банк для переноса счетов (id)",
                banks);

        Collection<PaymentAccount> migratedAccounts =
                services.getPaymentAccountService().migrateFromSource("payAccs.txt", bank.id);
        ConsoleMenu.printCollection("Перенесенные платежные счета", migratedAccounts);
    }

    /**
     * Получить кредит
     * @param user Клиент
     * @param services Хранилище сервисов
     * @throws Exception Ошибка выдачи кредита
     */
    private static void getCredit(User user, ServiceHandler services) throws Exception {
        System.out.println("***Кредитный рейтинг пользователя: " + user.creditRate + "***");
        System.out.println("Введите сумму, которую хотите взять в кредит...");
        double creditSumm = new Scanner(System.in).nextDouble();

        Bank bank = ConsoleMenu.getObjectFromUser(
                "Выберите банк для получения кредита (id)",
                services.getBankService().getAll());

        CreditAccount credAcc = getCreditFromBank(user, bank, services, creditSumm);
        System.out.print("Поздравляем с успешным получением кредита! Данные вашего кредитного аккаунта:");
        System.out.print(credAcc.toShortString());
    }

    /**
     * Получить кредит у банка
     * @param user Клиент
     * @param bank Банк
     * @param services Хранилище сервисов
     * @param creditSumm Сумма кредита
     * @return Кредитный аккаунт с кредитом
     * @throws Exception Ошибка получения кредита
     */
    private static CreditAccount getCreditFromBank(
            User user,
            Bank bank,
            ServiceHandler services,
            double creditSumm) throws Exception {

        BankOffice office = ConsoleMenu.getObjectFromUser(
                "Выберите офис (id)",
                bank.offices);
        while(!office.isWorking || !office.isCrediting()) {
            office = ConsoleMenu.getObjectFromUser(
                    "В данном офисе получение кредита сейчас невозможно. Выберите другой офис (id)...",
                    bank.offices);
        }

        Employee empl = ConsoleMenu.getObjectFromUser(
                "Выберите сотрудника (id)",
                office.employees);
        while(!empl.canGiveCredit) {
            empl = ConsoleMenu.getObjectFromUser(
                    "Данный сотрудник не выдает кредитов. Выберите другого (id)...",
                    office.employees);
        }

        PaymentAccount payAcc = user.paymentAccounts.stream()
                .filter(pAcc -> pAcc.bankId == bank.id)
                .findFirst()
                .orElseGet(() -> services.getPaymentAccountService().openPaymentAccount(user.id, bank.id, 0));

        CreditValidation(user, bank);

        Optional<BankAtm> atmOpt = office.atms.stream()
                .filter(atm -> atm.getMoneyAmount() > creditSumm)
                .findFirst();
        if(atmOpt.isPresent()) {
            double moneyAmount = services.getAtmService().takeMoney(atmOpt.get().id, creditSumm);
            if(moneyAmount != creditSumm)
                throw new BankTransactionException("Непредвиденная ошибка получения денег банкомата", atmOpt.get().hashCode(), BankTransactions.Disbursement);
            return services.getCreditAccountService().openCreditAccount(
                    user.id,
                    bank.id,
                    empl.id,
                    payAcc.id,
                    moneyAmount / 12,
                    12
            );
        } else {
            Optional<BankAtm> suitableAtm = bank.atms.stream()
                    .filter(atm -> atm.getMoneyAmount() > creditSumm).findFirst();
            if(suitableAtm.isEmpty()) throw new BankTransactionException(
                    "Нет подходящих банкоматов в банке",
                    user.hashCode(),
                    BankTransactions.Crediting);

            BankOffice suitableOffice = suitableAtm.get().placingOffice;

            System.out.print("В данном офисе нет подходящих банкоматов. " +
                    "Обратитесь в офис по адресу \"" + suitableOffice.address
                    + "\" (id="+ suitableAtm.get().placingOffice.id + ")");
            return getCreditFromBank(user, bank, services, creditSumm);
        }
    }

    /**
     * Проверка возможности выдачи кредита клиенту
     * @param user Клиент
     * @param bank Банк
     * @throws Exception Ошибка валидации
     */
    private static void CreditValidation(User user, Bank bank) throws Exception {
        if(user.creditRate < 5000 && bank.rate > 50) {
            throw new ValidationException("Пользователь с id=" + user.id, "creditRate", user.creditRate);
        }
    }
}