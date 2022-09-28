package bank.service;

import bank.entity.BankAtm;
import bank.entity.BankOffice;
import bank.entity.Employee;

public interface AtmService {
    /**Получить банкомат**/
    public BankAtm getAtm();

    /**Добавить новый банкомат**/
    public BankAtm addNewAtm();

    /**Изменить данные модели банкомата**/
    public BankAtm updateAtm(BankAtm model);

    /**Поместить банкомат в офис**/
    public void placeToOffice(BankAtm atm, BankOffice office);

    /**Назначить обслуживающего сотрудника**/
    public BankAtm setEmployee(Employee employee);

    /**Получение денег у банкомата**/
    public double takeMoney(double amount);

    /**Добавление денег в банкомат**/
    public double depositMoney(double amount);
}
