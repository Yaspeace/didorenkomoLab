package bank.dataaccess;

import bank.entity.*;


public class BankRepository {
    public EntityPool<Bank> banks;
    public EntityPool<BankAtm> atms;
    public EntityPool<BankOffice> offices;
    public EntityPool<CreditAccount> creditAccounts;
    public EntityPool<Employee> employees;
    public EntityPool<PaymentAccount> paymentAccounts;
    public EntityPool<User> users;

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
