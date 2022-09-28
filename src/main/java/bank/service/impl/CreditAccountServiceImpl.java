package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.service.*;

public class CreditAccountServiceImpl implements CreditAccountService {
    private final BankRepository rep;
    private final UserService userService;
    private final BankService bankService;
    private final EmployeeService employeeService;
    private final PaymentAccountService paymentAccountService;

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

        return updateCreditAccount(c);
    }
}
