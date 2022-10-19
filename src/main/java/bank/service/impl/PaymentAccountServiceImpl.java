package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.service.BankService;
import bank.service.EmployeeService;
import bank.service.PaymentAccountService;
import bank.service.UserService;

/**Сервис по работе с платежными счетами*/
public class PaymentAccountServiceImpl implements PaymentAccountService {
    /**Репозиторий*/
    private final BankRepository rep;

    /**Сервис для работы с пользователями*/
    private final UserService userService;

    /**Сервис для работы с банками*/
    private final BankService bankService;

    /**
     * Конструктор
     * @param rep Репозиторий
     * @param userService Сервис для работы с пользователями
     * @param bankService Сервис для работы с банками
     */
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

    @Override
    public PaymentAccount openPaymentAccount(int userId, int bankId) {
        PaymentAccount p = addNewPaymentAccount();
        User u = userService.getUser();
        Bank b = bankService.getBank();

        p.userId = userId;
        p.user = u;
        p.bankName = b.name;

        u.paymentAccounts = p;
        userService.updateUser(u);

        return updatePaymentAccount(p);
    }
}
