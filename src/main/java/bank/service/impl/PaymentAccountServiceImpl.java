package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.service.BankService;
import bank.service.PaymentAccountService;
import bank.service.UserService;
import helpers.Logger;

import java.util.Collection;

/**Сервис по работе с платежными счетами*/
public class PaymentAccountServiceImpl implements PaymentAccountService {
    /**Репозиторий*/
    private final BankRepository rep;

    /**Логгер**/
    private final Logger logger;

    /**Сервис для работы с пользователями*/
    private final UserService userService;

    /**Сервис для работы с банками*/
    private final BankService bankService;

    /**
     * Конструктор
     * @param rep Репозиторий
     * @param logger Логгер
     * @param userService Сервис для работы с пользователями
     * @param bankService Сервис для работы с банками
     */
    public PaymentAccountServiceImpl(BankRepository rep,
                                     Logger logger,
                                     UserService userService,
                                     BankService bankService) {
        this.rep = rep;
        this.logger = logger;
        this.userService = userService;
        this.bankService = bankService;
    }

    public PaymentAccount getPaymentAccount(int id) {
        return rep.paymentAccounts.get(id);
    }

    public Collection<PaymentAccount> getAll() {
        return rep.paymentAccounts.get();
    }

    public PaymentAccount addPaymentAccount(PaymentAccount paymentAcc) {
        try {
            rep.paymentAccounts.add(paymentAcc);
            return paymentAcc;
        }
        catch (Exception ex) {
            logger.logError("Ошибка при добавлении платежного счета: " + ex.getMessage());
            return null;
        }
    }

    public PaymentAccount updatePaymentAccount(PaymentAccount model) {
        try {
            return rep.paymentAccounts.update(model);
        }
        catch(Exception ex) {
            logger.logError("Ошибка при изменении платежного счета: " + ex.getMessage());
            return null;
        }
    }

    public PaymentAccount openPaymentAccount(int userId, int bankId, double initialSumm) {
        PaymentAccount pAcc = new PaymentAccount();
        User user = userService.getUser(userId);
        Bank bank = bankService.get(bankId);

        pAcc.userId = userId;
        pAcc.user = user;
        pAcc.bankName = bank.name;
        pAcc.moneyAmount = initialSumm;

        bank.paymentAccounts.add(pAcc);
        user.paymentAccounts.add(pAcc);

        userService.updateUser(user);

        return this.addPaymentAccount(pAcc);
    }
}
