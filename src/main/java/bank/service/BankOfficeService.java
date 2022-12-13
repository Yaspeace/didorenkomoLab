package bank.service;

import bank.entity.BankOffice;
import bank.exceptions.NotFoundException;

import java.util.Collection;

/**Интерфейс сервисов по работе с банковскими офисами**/
public interface BankOfficeService {
    /**
     * Получить офис по id
     * @param id Идентификатор офиса
     * @return Модель офиса
     * **/
    BankOffice getOffice(int id) throws NotFoundException;

    /**
     * Получить все банковские офисы
     * @return Коллекция офисов
     */
    Collection<BankOffice> getAllOffices();

    /**
     * Добавить новый офис
     * @param office Модель офиса на добавление
     * @return Модель нового офиса
     *  **/
    BankOffice addOffice(BankOffice office) throws RuntimeException;

    /**
     * Изменить модель офиса
     * @param office Модель офиса для изменения
     * @return Измененная модель
     * **/
    BankOffice updateBankOffice(BankOffice office) throws RuntimeException;

    BankOffice addEmployeeToOffice(int officeId, int employeeId) throws RuntimeException;
}
