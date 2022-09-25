package bank.entity;

import bank.entity.base.BaseEntity;

public class PaymentAccount extends BaseEntity {
    public int userId;
    public User user;
    public String bankName;
    public double moneyAmount;
}
