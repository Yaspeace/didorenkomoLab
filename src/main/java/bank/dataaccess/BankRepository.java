package bank.dataaccess;

import bank.entity.*;

/**Репозиторий объектов банка*/
public class BankRepository {
    /**Банки*/
    public EntityPool<Bank> banks;

    /**Банкоматы*/
    public EntityPool<BankAtm> atms;

    /**Офисы*/
    public EntityPool<BankOffice> offices;

    /**Средитный счета*/
    public EntityPool<CreditAccount> creditAccounts;

    /**Сотрудники*/
    public EntityPool<Employee> employees;

    /**Платежные счета*/
    public EntityPool<PaymentAccount> paymentAccounts;

    /**Пользователи*/
    public EntityPool<User> users;

    /**Конструктор*/
    public BankRepository()
    {
        banks = new EntityPool<>();
        atms = new EntityPool<>();
        offices = new EntityPool<>();
        creditAccounts = new EntityPool<>();
        employees = new EntityPool<>();
        paymentAccounts = new EntityPool<>();
        users = new EntityPool<>();
    }
}
