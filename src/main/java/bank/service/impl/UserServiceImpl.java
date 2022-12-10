package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.User;
import bank.service.UserService;
import exceptions.NotFoundException;
import helpers.Logger;

import java.util.Collection;

public class UserServiceImpl implements UserService {
    private final BankRepository rep;

    public UserServiceImpl(BankRepository rep) {
        this.rep = rep;
    }

    public User getUser(int id) throws NotFoundException {
        User res = rep.users.get(id);
        if(res == null) throw new NotFoundException(id, User.class);
        return res;
    }

    public Collection<User> getAll() {
        return rep.users.get();
    }

    public User addUser(User user) throws Exception {
        rep.users.add(user);
        return user;
    }

    @Override
    public User updateUser(User model) throws Exception {
        return rep.users.update(model);
    }
}
