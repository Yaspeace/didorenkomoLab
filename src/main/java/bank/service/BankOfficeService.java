package bank.service;

import bank.entity.BankAtm;
import bank.entity.BankOffice;

public interface BankOfficeService {
    /**Получить офис**/
    public BankOffice getOffice();

    /**Добавить новый офис**/
    public BankOffice addNewOffice();

    /**Изменить модель офиса**/
    public BankOffice updateBankOffice(BankOffice office);
}
