package bank.service;

import bank.entity.User;
import bank.exceptions.NotFoundException;

import java.util.Collection;

/**Интерфейс сервисов по работе с пользователями*/
public interface UserService {
    /**
     * Получить пользователя
     * @param id Идентификатор пользователя
     * @return Модель пользователя
     */
    User getUser(int id) throws NotFoundException;

    /**
     * Получить всех пользователей
     * @return Коллекция моделей пользователей
     */
    Collection<User> getAll();

    /**
     * Добавить нового пользователя
     * @return Модель нового пользователя
     */
    User addUser(User user) throws RuntimeException;

    /**
     * Изменить пользователя
     * @param model Модель пользователя для изменения
     * @return Измененная модель
     */
    User updateUser(User model) throws RuntimeException;
}
