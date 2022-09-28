package bank.entity;

import bank.entity.base.BaseNameEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User extends BaseNameEntity {
    public Date birthday;
    public String workingPlace;
    public double salary;
    public Bank banks;
    public PaymentAccount paymentAccounts;
    public CreditAccount creditAccounts;
    public int creditRate;
}
