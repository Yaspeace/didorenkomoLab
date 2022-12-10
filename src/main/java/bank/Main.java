package bank;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import exceptions.BankTransactionException;
import exceptions.BankTransactions;
import exceptions.ValidationException;
import helpers.*;
import ui.ConsoleMenu;

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

            System.out.println("Введите сумму, которую хотите взять в кредит...");
            double summ = sc.nextDouble();

            CreditAccount credAcc = getCredit(userId, services, summ);
            System.out.print("Поздравляем с успешным получением кредита! Данные вашего кредитного аккаунта:");
            System.out.print(credAcc.toShortString());
        }
        catch(Exception ex) {
            logger.logError("Ошибка работы приложения: " + ex.getMessage());
        }
    }

    /**
     * Получить кредит
     * @param userId Идентификатор клиента
     * @param services Хранилище сервисов
     * @param creditSumm Сумма кредита
     * @return Кредитный счет, на который был выдан кредит
     * @throws Exception Ошибка выдачи кредита
     */
    private static CreditAccount getCredit(int userId, ServiceHandler services, double creditSumm) throws Exception {
        int bankId = ConsoleMenu.getIdFromUser(
                "Выберите банк для получения кредита (id)",
                services.getBankService().getAll());

        return getCreditFromBank(userId, bankId, services, creditSumm);
    }

    /**
     * Получить кредит у банка
     * @param userId Идентификатор клиента
     * @param bankId Идентификатор банка
     * @param services Хранилище сервисов
     * @param creditSumm Сумма кредита
     * @return Кредитный аккаунт с кредитом
     * @throws Exception Ошибка получения кредита
     */
    private static CreditAccount getCreditFromBank(
            int userId,
            int bankId,
            ServiceHandler services,
            double creditSumm) throws Exception {
        User curUser = services.getUserService().getUser(userId);
        Bank bank = services.getBankService().get(bankId);

        int officeId = ConsoleMenu.getIdFromUser(
                "Выберите офис (id)",
                bank.offices);
        BankOffice office = services.getOfficeService().getOffice(officeId);
        while(!office.isWorking || !office.isCrediting || office.moneyAmount < creditSumm) {
            System.out.print("В данном офисе получение кредита сейчас невозможно. Выберите другой офис (id)...");
            officeId = new Scanner(System.in).nextInt();
            office = services.getOfficeService().getOffice(officeId);
        }

        int emplId = ConsoleMenu.getIdFromUser(
                "Выберите сотрудника (id)",
                office.employees);
        Employee empl = services.getEmployeeService().getEmployee(emplId);
        while(!empl.canGiveCredit) {
            System.out.print("Данный сотрудник не выдает кредитов. Выберите другого (id)...");
            emplId = new Scanner(System.in).nextInt();
            empl = services.getEmployeeService().getEmployee(emplId);
        }

        Optional<PaymentAccount> payAccOptional =
                curUser.paymentAccounts.stream().filter(pAcc -> pAcc.bankId == bank.id).findFirst();
        PaymentAccount payAcc;

        if(payAccOptional.isPresent()) {
            payAcc = payAccOptional.get();
        } else {
            payAcc = services.getPaymentAccountService().openPaymentAccount(curUser.id, bank.id, 0);
        }

        CreditValidation(curUser, bank);

        Optional<BankAtm> atmOpt = office.atms.stream()
                .filter(atm -> atm.getMoneyAmount() > creditSumm)
                .findFirst();
        if(atmOpt.isPresent()) {
            double moneyAmount = services.getAtmService().takeMoney(atmOpt.get().id, creditSumm);
            if(moneyAmount != creditSumm)
                throw new BankTransactionException("Непредвиденная ошибка получения денег банкомата", atmOpt.get().hashCode(), BankTransactions.Disbursement);
            return services.getCreditAccountService().openCreditAccount(
                    curUser.id,
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
                                        curUser.hashCode(),
                                        BankTransactions.Crediting);

            System.out.print("В данном офисе нет подходящих банкоматов. Обратитесь в офис с id="+ suitableAtm.get().placingOffice.id);
            return getCreditFromBank(userId, bankId, services, creditSumm);
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
            throw new ValidationException("Пользователь с id=" + user.id, "creditRate", 5000);
        }
    }
}