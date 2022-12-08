package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.service.*;
import helpers.Logger;

import java.util.Collection;

/**Сервис по работе с кредитными счетами*/
public class CreditAccountServiceImpl implements CreditAccountService {
    /**Репозиторий*/
    private final BankRepository rep;

    /**Логгер**/
    private final Logger logger;

    /**Сервис для работы с пользователями*/
    private final UserService userService;

    /**Сервис для работы с банками*/
    private final BankService bankService;

    /**Сервис для работы с сотрудниками*/
    private final EmployeeService employeeService;

    /**Сервис для работы с платежными счетами*/
    private final PaymentAccountService paymentAccountService;

    /**
     * Конструктор
     * @param rep Репозиторий
     * @param userService Сервис для работы с пользователями
     * @param bankService Сервис для работы с банками
     * @param employeeService Сервис для работы с сотрудниками
     * @param paymentAccountService Сервис для работы с платежными счетами
     */
    public CreditAccountServiceImpl(BankRepository rep,
                                    Logger logger,
                                    UserService userService,
                                    BankService bankService,
                                    EmployeeService employeeService,
                                    PaymentAccountService paymentAccountService) {
        this.rep = rep;
        this.logger = logger;
        this.userService = userService;
        this.bankService = bankService;
        this.employeeService = employeeService;
        this.paymentAccountService = paymentAccountService;
    }

    public CreditAccount getCreditAccount(int id) {
        return rep.creditAccounts.get(id);
    }

    public Collection<CreditAccount> getAll() {
        return rep.creditAccounts.get();
    }

    public CreditAccount addCreditAccount(CreditAccount creditAcc) {
        try {
            rep.creditAccounts.add(creditAcc);
            return creditAcc;
        }
        catch (Exception ex) {
            logger.logError("Ошибка добавления кредитного счета: " + ex.getMessage());
            return null;
        }
    }

    public CreditAccount updateCreditAccount(CreditAccount model) {
        try {
            return rep.creditAccounts.update(model);
        }
        catch(Exception ex) {
            logger.logError("Ошибка изменения кредитного счета: " + ex.getMessage());
            return null;
        }
    }

    public CreditAccount openCreditAccount(int userId,
                                           int bankId,
                                           int employeeId,
                                           int paymentAccountId,
                                           double monthPayment,
                                           int months) {
        User user = userService.getUser(userId);
        Bank bank = bankService.get(bankId);
        Employee empl = employeeService.getEmployee(employeeId);
        PaymentAccount payAcc = paymentAccountService.getPaymentAccount(paymentAccountId);
        CreditAccount credAcc = new CreditAccount();

        credAcc.bankName = bank.name;
        credAcc.percent = bank.percent;
        credAcc.userId = user.id;
        credAcc.user = user;
        credAcc.employeeId = empl.id;
        credAcc.employee = empl;
        credAcc.paymentAccountId = payAcc.id;
        credAcc.paymentAccount = payAcc;
        credAcc.months = months;
        credAcc.monthPayment = monthPayment;

        user.creditAccounts.add(credAcc);
        empl.creditAccounts.add(credAcc);
        payAcc.creditAccounts.add(credAcc);

        userService.updateUser(user);
        employeeService.updateEmployee(empl);
        paymentAccountService.updatePaymentAccount(payAcc);

        return this.addCreditAccount(credAcc);
    }
}
