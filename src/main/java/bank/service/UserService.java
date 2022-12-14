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

    /**
     * Отправить информацию о платежных счетах клиента по банку на указанный путь
     * @param userId Идентификатор клиента
     * @param bankId Идентификатор банка
     * @param destination Путь отправки
     * @throws Exception Ошибка отправки
     */
    void sendPayAccounts(int userId, int bankId, String destination) throws Exception;

    /**
     * Отправить информацию о кредитных счетах пользователя по платежному счету на указанный путь
     * @param userId Идентификатор клиента
     * @param payAccId Идентификатор платежного счета
     * @param destination Путь отправки
     * @throws Exception Ошибка отправки
     */
    void sendCredAccounts(int userId, int payAccId, String destination) throws Exception;
}
