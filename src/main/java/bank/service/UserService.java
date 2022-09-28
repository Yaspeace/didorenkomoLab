package bank.service;

import bank.entity.User;

public interface UserService {
    public User getUser();

    public User addNewUser();

    public User updateUser(User model);
}
