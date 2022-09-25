package bank.service;

import bank.entity.BankAtm;
import bank.entity.BankOffice;
import bank.entity.Employee;

public interface AtmService {
    public BankAtm getAtm();
    public BankAtm addNewAtm();
    public BankAtm updateAtm(BankAtm model);
    public BankAtm placeToOffice(BankOffice office);
    public BankAtm setEmployee(Employee employee);
    public double takeMoney(double amount);
    public double depositMoney(double amount);
}
