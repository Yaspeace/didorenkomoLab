package bank.service;

import bank.entity.BankAtm;
import bank.entity.BankOffice;
import bank.entity.Employee;

import java.util.Collection;

/**Интерфейс сервисов по работе с банкоматами**/
public interface AtmService {
    /**
     * Получить банкомат по id
     * @param id Идентификатор банкомата
     * @return Модель банкомата
     * **/
    public BankAtm getAtm(int id);

    /**
     * Получить все банкоматы
     * @return Список банкоматов
     */
    public Collection<BankAtm> getAllAtms();

    /**
     * Добавить новый банкомат
     * @param atm Модель банкомата на добавление
     * @return Модель добавленного банкомата
     * **/
    public BankAtm addAtm(BankAtm atm);

    /**
     * Изменить модель банкомата
     * @param model Модель банкомата для изменения
     * @return Измененная модель
     * **/
    public BankAtm updateAtm(BankAtm model);

    /**
     * Поместить банкомат в офис
     * @param atm Банкомат
     * @param office Офис
     * **/
    public void placeToOffice(BankAtm atm, BankOffice office) throws Exception;

    /**
     * Назначить обслуживающего сотрудника
     * @param atmId Идентификатор банкомата
     * @param employee Обслуживающий сотрудник для назначения
     * @return Модель банкомата после назначения сотрудника
     * **/
    public BankAtm setEmployee(int atmId, Employee employee) throws Exception;

    /**
     * Получение денег у банкомата
     * @param atmId Идентификатор банкомата
     * @param amount Количество денег к получению
     * @return Количество фактически полученных денег
     * **/
    public double takeMoney(int atmId, double amount);

    /**
     * Добавление денег в банкомат
     * @param atmId Идентификатор банкомата
     * @param amount Количество денег к добавилению
     * @return Количество фактически добавленных денег
     * **/
    public double depositMoney(int atmId, double amount);
}
