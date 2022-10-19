package bank.service;

import bank.entity.BankAtm;
import bank.entity.BankOffice;

/**Интерфейс сервисов по работе с банковскими офисами**/
public interface BankOfficeService {
    /**
     * Получить офис
     * @return Модель офиса
     * **/
    public BankOffice getOffice();

    /**
     * Добавить новый офис
     * @return Модель нового офиса
     *  **/
    public BankOffice addNewOffice();

    /**
     * Изменить модель офиса
     * @param office Модель офиса для изменения
     * @return Измененная модель
     * **/
    public BankOffice updateBankOffice(BankOffice office);
}
