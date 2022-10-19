package bank.service;

import bank.entity.*;

/**Интерфейс сервисов по работе с банками**/
public interface BankService {
    /**
     * Получить банк
     * @return Модель банка
     */
    public Bank getBank();

    /**
     * Добавить новый банк
     * @return Модель нового банка
     */
    public Bank addNewBank();

    /**
     * Изменить банк
     * @param b Модель банка для изменения
     * @return Измененная модель
     */
    public Bank updateBank(Bank b);

    /**
     * Добавить банку банкомат
     * @param bankId Идентификатор банка
     * @return Модель банкомата указанного банка
     */
    public BankAtm addAtmToBank(int bankId);

    /**
     * Добавить офис банку
     * @param bankId Идентификатор банка
     * @return Модель офиса указанного банка
     */
    public BankOffice addNewBankOffice(int bankId);

    /**
     * Добавить сотрудника банку
     * @param bankId Идентификатор банка
     * @return Модель сотрудника указанного банка
     */
    public Employee addEmployeeToBank(int bankId);

    /**
     * Добавить пользователя банка
     * @param bankId Идентификатор банка
     * @return Модель пользователя указанного банка
     */
    public User addBankUser(int bankId);
}
