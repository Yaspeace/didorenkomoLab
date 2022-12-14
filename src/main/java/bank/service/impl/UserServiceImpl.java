package bank.service.impl;

import bank.clients.Client;
import bank.dataaccess.BankRepository;
import bank.entity.CreditAccount;
import bank.entity.PaymentAccount;
import bank.entity.User;
import bank.helpers.serialization.Serializer;
import bank.service.UserService;
import bank.exceptions.NotFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final BankRepository rep;

    private final Client sendClient;

    public UserServiceImpl(BankRepository rep, Client sendClient) {
        this.rep = rep;
        this.sendClient = sendClient;
    }

    public User getUser(int id) throws NotFoundException {
        User res = rep.users.get(id);
        if(res == null) throw new NotFoundException(id, User.class);
        return res;
    }

    public Collection<User> getAll() {
        return rep.users.get();
    }

    public User addUser(User user) throws RuntimeException {
        rep.users.add(user);
        return user;
    }

    @Override
    public User updateUser(User model) throws RuntimeException {
        return rep.users.update(model);
    }

    @Override
    public void sendPayAccounts(int userId, int bankId, String destination) throws Exception {
        User user = getUser(userId);
        Collection<PaymentAccount> paymentAccounts = user.paymentAccounts.stream().filter(x -> x.bankId == bankId).toList();
        if(paymentAccounts.isEmpty()) return;
        String toSend = Serializer.serializeMany(paymentAccounts);
        sendClient.send(destination, toSend);
    }

    @Override
    public void sendCredAccounts(int userId, int payAccId, String destination) throws Exception {
        User user = getUser(userId);
        PaymentAccount payAcc = user.paymentAccounts.stream().filter(x -> x.id == payAccId).findFirst().orElseThrow();
        Collection<CreditAccount> credAccs = payAcc.creditAccounts;
        if(credAccs.isEmpty()) return;
        String toSend = Serializer.serializeMany(credAccs);
        sendClient.send(destination, toSend);
    }
}
