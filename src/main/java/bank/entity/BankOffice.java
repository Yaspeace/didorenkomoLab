package bank.entity;

import bank.entity.base.BaseNameEntity;

public class BankOffice extends BaseNameEntity {
    public String address;
    public boolean isWorking;
    public boolean canPlaceAtm;
    public int atmNum;
    public boolean isCrediting;
    public boolean isGivesMoney;
    public boolean isTakesMoney;
    public double moneyAmount;
    public double rentPrice;
}
