package bank.service;

import bank.entity.*;
import bank.exceptions.NotFoundException;

import java.util.Collection;

/**Интерфейс сервисов по работе с банками**/
public interface BankService {
    /**
     * Получить банк
     * @return Модель банка
     */
    Bank get(int id) throws NotFoundException;

    /**
     * Получить все банки
     *
     * @return Коллекция банков
     */
    Collection<Bank> getAll();

    /**
     * Добавить новый банк
     * @return Модель нового банка
     */
    Bank addBank(Bank bank) throws RuntimeException;

    /**
     * Изменить банк
     * @param bank Модель банка для изменения
     * @return Измененная модель
     */
    Bank updateBank(Bank bank) throws RuntimeException;

    /**
     * Добавить банку банкомат
     * @param bankId Идентификатор банка
     * @return Модель банка
     */
    Bank addAtmToBank(int bankId, int atmId) throws RuntimeException;

    /**
     * Добавить офис банку
     * @param bankId Идентификатор банка
     * @return Модель банка
     */
    Bank addNewBankOffice(int bankId, int officeId) throws RuntimeException;

    /**
     * Добавить сотрудника банку
     * @param bankId Идентификатор банка
     * @return Модель банка
     */
    Bank addEmployeeToBank(int bankId, int employeeId) throws RuntimeException;

    /**
     * Добавить пользователя банка
     * @param bankId Идентификатор банка
     * @return Модель банка
     */
    Bank addBankUser(int bankId, int userId) throws RuntimeException;
}
