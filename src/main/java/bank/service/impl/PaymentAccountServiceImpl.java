package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.service.BankService;
import bank.service.PaymentAccountService;
import bank.service.UserService;
import bank.exceptions.NotFoundException;

import java.util.Collection;

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

    public PaymentAccount getPaymentAccount(int id) throws NotFoundException {
        PaymentAccount res = rep.paymentAccounts.get(id);
        if(res == null) throw new NotFoundException(id, PaymentAccount.class);
        return res;
    }

    public Collection<PaymentAccount> getAll() {
        return rep.paymentAccounts.get();
    }

    public PaymentAccount addPaymentAccount(PaymentAccount paymentAcc) throws RuntimeException {
        rep.paymentAccounts.add(paymentAcc);
        return paymentAcc;
    }

    public PaymentAccount updatePaymentAccount(PaymentAccount model) throws RuntimeException {
        return rep.paymentAccounts.update(model);
    }

    public PaymentAccount openPaymentAccount(int userId, int bankId, double initialSumm) throws RuntimeException {
        PaymentAccount pAcc = new PaymentAccount();
        User user = userService.getUser(userId);
        Bank bank = bankService.get(bankId);

        pAcc.userId = userId;
        pAcc.user = user;
        pAcc.bankId = bankId;
        pAcc.bankName = bank.name;
        pAcc.moneyAmount = initialSumm;

        bank.paymentAccounts.add(pAcc);
        user.paymentAccounts.add(pAcc);

        userService.updateUser(user);

        return this.addPaymentAccount(pAcc);
    }
}
