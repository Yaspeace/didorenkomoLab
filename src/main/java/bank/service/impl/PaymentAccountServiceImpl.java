package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.service.BankService;
import bank.service.EmployeeService;
import bank.service.PaymentAccountService;
import bank.service.UserService;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    private final BankRepository rep;
    private final UserService userService;
    private final BankService bankService;

    public PaymentAccountServiceImpl(BankRepository rep,
                                     UserService userService,
                                     BankService bankService) {
        this.rep = rep;
        this.userService = userService;
        this.bankService = bankService;
    }

    @Override
    public PaymentAccount getPaymentAccount() {
        return rep.paymentAccounts.get();
    }

    @Override
    public PaymentAccount addNewPaymentAccount() {
        return rep.paymentAccounts.update(new PaymentAccount());
    }

    @Override
    public PaymentAccount updatePaymentAccount(PaymentAccount model) {
        return rep.paymentAccounts.update(model);
    }

    public PaymentAccount openPaymentAccount(int userId, int bankId) {
        PaymentAccount p = addNewPaymentAccount();
        User u = userService.getUser();
        Bank b = bankService.getBank();

        p.userId = userId;
        p.user = u;
        p.bankName = b.name;

        return updatePaymentAccount(p);
    }
}
