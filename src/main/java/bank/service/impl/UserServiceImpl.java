package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.User;
import bank.service.UserService;
import helpers.Logger;

import java.util.Collection;

public class UserServiceImpl implements UserService {
    private final BankRepository rep;

    private final Logger logger;

    public UserServiceImpl(BankRepository rep, Logger logger) {
        this.rep = rep;
        this.logger = logger;
    }

    public User getUser(int id) {
        return rep.users.get(id);
    }

    public Collection<User> getAll() {
        return rep.users.get();
    }

    public User addUser(User user) {
        try {
            rep.users.add(user);
            return user;
        }
        catch(Exception ex) {
            logger.logError("Ошибка при добавлении пользователя: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public User updateUser(User model) {
        try {
            return rep.users.update(model);
        }
        catch(Exception ex) {
            logger.logError("Ошибка при изменении данных пользователя: " + ex.getMessage());
            return null;
        }
    }
}
