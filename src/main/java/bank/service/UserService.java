package bank.service;

import bank.entity.User;

/**Интерфейс сервисов по работе с пользователями*/
public interface UserService {
    /**
     * Получить пользователя
     * @return Модель пользователя
     */
    public User getUser();

    /**
     * Добавить нового пользователя
     * @return Модель нового пользователя
     */
    public User addNewUser();

    /**
     * Изменить пользователя
     * @param model Модель пользователя для изменения
     * @return Измененная модель
     */
    public User updateUser(User model);
}
