package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.service.*;

/**Сервис по работе с кредитными счетами*/
public class CreditAccountServiceImpl implements CreditAccountService {
    /**Репозиторий*/
    private final BankRepository rep;

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
                                    UserService userService,
                                    BankService bankService,
                                    EmployeeService employeeService,
                                    PaymentAccountService paymentAccountService) {
        this.rep = rep;
        this.userService = userService;
        this.bankService = bankService;
        this.employeeService = employeeService;
        this.paymentAccountService = paymentAccountService;
    }

    @Override
    public CreditAccount getCreditAccount() {
        return rep.creditAccounts.get();
    }

    @Override
    public CreditAccount addNewCreditAccount() {
        return rep.creditAccounts.update(new CreditAccount());
    }

    @Override
    public CreditAccount updateCreditAccount(CreditAccount model) {
        return rep.creditAccounts.update(model);
    }

    @Override
    public CreditAccount openCreditAccount(int userId,
                                           int bankId,
                                           int employeeId,
                                           int paymentAccountId,
                                           double monthPayment,
                                           int months) {
        User u = userService.getUser();
        Bank b = bankService.getBank();
        Employee e = employeeService.getEmployee();
        PaymentAccount p = paymentAccountService.getPaymentAccount();
        CreditAccount c = addNewCreditAccount();

        c.bankName = b.name;
        c.percent = b.percent;
        c.userId = u.id;
        c.user = u;
        c.employeeId = e.id;
        c.employee = e;
        c.paymentAccountId = p.id;
        c.paymentAccount = p;
        c.months = months;
        c.monthPayment = monthPayment;

        u.creditAccounts = c;
        userService.updateUser(u);

        return updateCreditAccount(c);
    }
}
