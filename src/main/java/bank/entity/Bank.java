package bank.entity;

import bank.entity.base.BaseNameEntity;

import java.util.Random;

public class Bank extends BaseNameEntity {
    public int officeNum;
    public int atmNum;
    public int employeeNum;
    public int clientNum;
    public int rate;
    public double percent;
    private double totalMoneyAmount;
    public void setTotalMoneyAmount(double value)
    {
        totalMoneyAmount = value;
        if(totalMoneyAmount < 0) totalMoneyAmount = 0;
    }
    public double getTotalMoneyAmount() { return totalMoneyAmount; }

    public Bank() {
        Random rnd = new Random();
        rate = rnd.nextInt(101);
        percent = rnd.nextDouble(11 - (rate / 10.0), 20 - (rate / 10.0));
    }
}
