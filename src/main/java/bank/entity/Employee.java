package bank.entity;

import bank.entity.base.BaseNameEntity;

import java.util.Date;

public class Employee extends BaseNameEntity {
    public Date birthday;
    public String post;
    public int bankId;
    public Bank bank;
    public boolean isDistantWorking;
    public int officeId;
    public BankOffice office;
    public boolean canGiveCredit;
    public double salary;
}
