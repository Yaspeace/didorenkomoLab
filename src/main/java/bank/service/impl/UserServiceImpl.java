package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.User;
import bank.service.UserService;

public class UserServiceImpl implements UserService {
    private final BankRepository rep;

    public UserServiceImpl(BankRepository rep) {
        this.rep = rep;
    }

    @Override
    public User getUser() {
        return rep.users.get();
    }

    @Override
    public User addNewUser() {
        return rep.users.update(new User());
    }

    @Override
    public User updateUser(User model) {
        return rep.users.update(model);
    }
}
