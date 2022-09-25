package bank.entity;

import bank.entity.base.BaseNameEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User extends BaseNameEntity {
    public Date birthday;
    public String workingPlace;
    public double salary;
    public List<String> banks;
    public List<PaymentAccount> paymentAccounts;
    public List<CreditAccount> creditAccounts;
    public int creditRate;
    public User()
    {
        banks = new ArrayList<>();
        paymentAccounts = new ArrayList<>();
        creditAccounts = new ArrayList<>();
    }
}
