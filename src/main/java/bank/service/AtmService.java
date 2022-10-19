package bank.service;

import bank.entity.BankAtm;
import bank.entity.BankOffice;
import bank.entity.Employee;

/**Интерфейс сервисов по работе с банкоматами**/
public interface AtmService {
    /**
     * Получить банкомат
     * @return Модель банкомата
     * **/
    public BankAtm getAtm();

    /**
     * Добавить новый банкомат
     * @return Модель добавленного банкомата
     * **/
    public BankAtm addNewAtm();

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
    public void placeToOffice(BankAtm atm, BankOffice office);

    /**
     * Назначить обслуживающего сотрудника
     * @param employee Обслуживающий сотрудник для назначения
     * @return Модель банкомата после назначения сотрудника
     * **/
    public BankAtm setEmployee(Employee employee);

    /**
     * Получение денег у банкомата
     * @param amount Количество денег к получению
     * @return Количество фактически полученных денег
     * **/
    public double takeMoney(double amount);

    /**
     * Добавление денег в банкомат
     * @param amount Количество денег к добавилению
     * @return Количество фактически добавленных денег
     * **/
    public double depositMoney(double amount);
}
