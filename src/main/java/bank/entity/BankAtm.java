package bank.entity;

import bank.entity.base.BaseNameEntity;
import bank.entity.enums.AtmStatuses;


public class BankAtm extends BaseNameEntity {
    public String address;
    public AtmStatuses status;
    public int bankId;
    public Bank bank;
    public int placingOfficeId;
    public BankOffice placingOffice;
    public int serveEmployeeId;
    public Employee serveEmployee;
    public boolean isGivesMoney;
    public boolean isTakesMoney;
    private double moneyAmount;
    public double servicePrice;

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
        if(this.moneyAmount == 0 && moneyAmount > 0)
            if(!(status == AtmStatuses.notWorking))
                status = AtmStatuses.working;
        this.moneyAmount = moneyAmount;
        if(this.moneyAmount <= 0)
        {
            this.moneyAmount = 0;
            if(!(status == AtmStatuses.notWorking))
            {
                status = AtmStatuses.noMoney;
            }
        }

    }
}
