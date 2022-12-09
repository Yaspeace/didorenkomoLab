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
    BankAtm getAtm(int id);

    /**
     * Получить все банкоматы
     * @return Список банкоматов
     */
    Collection<BankAtm> getAllAtms();

    /**
     * Добавить новый банкомат
     * @param atm Модель банкомата на добавление
     * @return Модель добавленного банкомата
     * **/
    BankAtm addAtm(BankAtm atm) throws Exception;

    /**
     * Изменить модель банкомата
     * @param model Модель банкомата для изменения
     * @return Измененная модель
     * **/
    BankAtm updateAtm(BankAtm model) throws Exception;

    /**
     * Поместить банкомат в офис
     * @param atm Банкомат
     * @param office Офис
     * **/
    void placeToOffice(BankAtm atm, BankOffice office) throws Exception;

    /**
     * Назначить обслуживающего сотрудника
     * @param atmId Идентификатор банкомата
     * @param employee Обслуживающий сотрудник для назначения
     * @return Модель банкомата после назначения сотрудника
     * **/
    BankAtm setEmployee(int atmId, Employee employee) throws Exception;

    /**
     * Получение денег у банкомата
     * @param atmId Идентификатор банкомата
     * @param amount Количество денег к получению
     * @return Количество фактически полученных денег
     * **/
    double takeMoney(int atmId, double amount);

    /**
     * Добавление денег в банкомат
     * @param atmId Идентификатор банкомата
     * @param amount Количество денег к добавилению
     * @return Количество фактически добавленных денег
     * **/
    double depositMoney(int atmId, double amount);
}
