package bank.entity;

import bank.entity.base.BaseEntity;

import java.util.Date;

public class CreditAccount extends BaseEntity {
    public int userId;
    public User user;
    public String bankName;
    public Date dateBegin;
    public Date dateEnd;
    public int months;
    public double monthPayment;
    public double percent;
    public int employeeId;
    public Employee employee;
    public int paymentAccountId;
    public PaymentAccount paymentAccount;
}
