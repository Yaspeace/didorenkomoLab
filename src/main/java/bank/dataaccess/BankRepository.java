package bank.dataaccess;

import bank.entity.*;


public class BankRepository {
    public EntityPool<Bank> banks;
    public EntityPool<BankAtm> atms;
    public EntityPool<BankOffice> offices;
    public EntityPool<CreditAccount> creditAccounts;
    public EntityPool<Employee> employees;
    public EntityPool<PaymentAccount> paymentAccounts;
    public EntityPool<User> Users;

    public BankRepository()
    {
        banks = new EntityPool<>();
        atms = new EntityPool<>();
        offices = new EntityPool<>();
        creditAccounts = new EntityPool<>();
        employees = new EntityPool<>();
        paymentAccounts = new EntityPool<>();
        Users = new EntityPool<>();

    }
}
