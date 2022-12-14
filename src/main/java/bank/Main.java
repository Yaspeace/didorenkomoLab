package bank;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.exceptions.BankTransactionException;
import bank.exceptions.BankTransactions;
import bank.exceptions.ValidationException;
import bank.helpers.*;
import bank.helpers.serialization.Serializer;
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

            migratePayAccs(curUser, services);

            /*
             getCredit(curUser, services);
            */
        }
        catch(Exception ex) {
            logger.logError("Ошибка работы приложения: " + ex.getMessage());
        }
    }

    private static void migratePayAccs(User user, ServiceHandler services) throws Exception {
        var bankIds = user.paymentAccounts.stream().map(x -> x.bankId).distinct().mapToInt(Integer::intValue).toArray();
        Collection<Bank> banks = services.getBankService().getAll()
                .stream().filter(x ->
                        Arrays.stream(bankIds).anyMatch(y -> y == x.id))
                .toList();
        Bank bank = ConsoleMenu.getIdFromUser(
                "У вас есть счета в указанных банках. Выберите один для выгрузки счетов (id)",
                banks);
        ConsoleMenu.title("Выгрузка начата");
        services.getUserService().sendPayAccounts(user.id, bank.id, "payAccs.txt");
        ConsoleMenu.title("Выгрузка закончена");

        banks = services.getBankService().getAll();
        bank = ConsoleMenu.getIdFromUser(
                "Выберите банк для переноса счетов (id)",
                banks);

        Collection<PaymentAccount> migratedAccounts =
                services.getPaymentAccountService().migrateFromSource("payAccs.txt", bank.id);
        ConsoleMenu.title("Перенесенные счета");
        System.out.println(CollectionPrinter.collectionToString(migratedAccounts));
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

        Bank bank = ConsoleMenu.getIdFromUser(
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

        BankOffice office = ConsoleMenu.getIdFromUser(
                "Выберите офис (id)",
                bank.offices);
        while(!office.isWorking || !office.isCrediting) {
            office = ConsoleMenu.getIdFromUser(
                    "В данном офисе получение кредита сейчас невозможно. Выберите другой офис (id)...",
                    bank.offices);
        }

        Employee empl = ConsoleMenu.getIdFromUser(
                "Выберите сотрудника (id)",
                office.employees);
        while(!empl.canGiveCredit) {
            empl = ConsoleMenu.getIdFromUser(
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