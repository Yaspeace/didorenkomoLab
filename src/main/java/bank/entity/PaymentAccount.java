package bank.entity;

import bank.entity.base.BaseEntity;

/**Модель платежного счета*/
public class PaymentAccount extends BaseEntity {
    /**Идентификатор пользователя*/
    public int userId;
    /**Пользователь*/
    public User user;

    /**Название банка*/
    public String bankName;

    /**Кол-во денег на счете*/
    public double moneyAmount;

    @Override
    public String toString() {
        return String.format("Payment Account: id=%s;userId=%s;bankName=%s;moneyAmount=%s",
                id,
                userId,
                bankName,
                moneyAmount);
    }
}
