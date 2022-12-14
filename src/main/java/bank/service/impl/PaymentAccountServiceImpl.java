package bank.service.impl;

import bank.clients.Client;
import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.helpers.serialization.Serializer;
import bank.service.BankService;
import bank.service.PaymentAccountService;
import bank.service.UserService;
import bank.exceptions.NotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**Сервис по работе с платежными счетами*/
public class PaymentAccountServiceImpl implements PaymentAccountService {
    /**Репозиторий*/
    private final BankRepository rep;

    /**Сервис для работы с пользователями*/
    private final UserService userService;

    /**Сервис для работы с банками*/
    private final BankService bankService;

    private final Client reciever;

    /**
     * Конструктор
     * @param rep Репозиторий
     * @param userService Сервис для работы с пользователями
     * @param bankService Сервис для работы с банками
     * @param reciever Клиент получения из удаленных источников
     */
    public PaymentAccountServiceImpl(BankRepository rep,
                                     UserService userService,
                                     BankService bankService,
                                     Client reciever) {
        this.rep = rep;
        this.userService = userService;
        this.bankService = bankService;
        this.reciever = reciever;
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

    @Override
    public PaymentAccount migrateToBank(Map<String, String> payAccData, int bankId) throws Exception {
        PaymentAccount newPayAcc = PaymentAccount.fromMap(payAccData);

        if(newPayAcc.bankId == bankId)
            throw new Exception("Ошибка переноса: банк-источник и банк-цель совпадают");

        return openPaymentAccount(newPayAcc.userId, bankId, newPayAcc.moneyAmount);
    }

    public Collection<PaymentAccount> migrateFromSource(String source, int bankId) throws Exception {
        String serialized = reciever.get(source);
        Collection<HashMap<String, String>> maps = Serializer.deserializeMany(serialized);
        LinkedList<PaymentAccount> res = new LinkedList<>();
        for(Map<String, String> map : maps) {
            res.add(migrateToBank(map, bankId));
        }
        return res;
    }
}
