package bank.entity;

import bank.entity.base.BaseNameEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**Модель пользователя*/
public class User extends BaseNameEntity {
    /**Дата рождения*/
    public Date birthday;
    /**Место работы*/
    public String workingPlace;
    /**Размер заработной платы*/
    public double salary;
    /**Банки, которыми пользуется пользователь*/
    public Bank banks;
    /**Платежные счета*/
    public PaymentAccount paymentAccounts;
    /**Кредитные счета*/
    public CreditAccount creditAccounts;
    /**Кредитный рейтинг*/
    public int creditRate;

    @Override
    public String toString() {
        return String.format("User: id=%s;name=%s;birthday=%s;workingPlace=%s;salary=%s;banks=%s;" +
                        "paymentAccounts=%s;creditAccounts=%s",
                id,
                name,
                birthday,
                workingPlace,
                salary,
                banks,
                paymentAccounts,
                creditAccounts);
    }
}
