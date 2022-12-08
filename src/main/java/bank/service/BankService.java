package bank.service;

import bank.entity.*;

import java.util.Collection;

/**Интерфейс сервисов по работе с банками**/
public interface BankService {
    /**
     * Получить банк
     * @return Модель банка
     */
    Bank get(int id);

    /**
     * Получить все банки
     * @return Коллекция банков
     */
    Collection<Bank> getALl();

    /**
     * Добавить новый банк
     * @return Модель нового банка
     */
    Bank addBank(Bank bank);

    /**
     * Изменить банк
     * @param bank Модель банка для изменения
     * @return Измененная модель
     */
    Bank updateBank(Bank bank);

    /**
     * Добавить банку банкомат
     * @param bankId Идентификатор банка
     * @return Модель банка
     */
    Bank addAtmToBank(int bankId, int atmId) throws Exception;

    /**
     * Добавить офис банку
     * @param bankId Идентификатор банка
     * @return Модель банка
     */
    Bank addNewBankOffice(int bankId, int officeId);

    /**
     * Добавить сотрудника банку
     * @param bankId Идентификатор банка
     * @return Модель банка
     */
    Bank addEmployeeToBank(int bankId, int employeeId);

    /**
     * Добавить пользователя банка
     * @param bankId Идентификатор банка
     * @return Модель банка
     */
    Bank addBankUser(int bankId, int userId);
}
