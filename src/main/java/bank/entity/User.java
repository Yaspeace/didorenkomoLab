package bank.entity;

import bank.entity.base.BaseNameEntity;
import helpers.CollectionPrinter;

import java.util.*;

/**Модель пользователя*/
public class User extends BaseNameEntity {
    /**Дата рождения*/
    public Date birthday;
    /**Место работы*/
    public String workingPlace;
    /**Размер заработной платы*/
    public double salary;
    /**Кредитный рейтинг*/
    public int creditRate;
    /**Банки, которыми пользуется пользователь*/
    public Collection<Bank> banks;
    /**Платежные счета*/
    public Collection<PaymentAccount> paymentAccounts;
    /**Кредитные счета*/
    public Collection<CreditAccount> creditAccounts;

    public User() {
        banks = new LinkedList<>();
        paymentAccounts = new LinkedList<>();
        creditAccounts = new LinkedList<>();
    }

    public String toString() {
        return String.format("""
                        User: 
                        \sid=%s;
                        \sname=%s;
                        \sbirthday=%s;
                        \sworkingPlace=%s;
                        \screditRate=%s,
                        \ssalary=%s;
                        \spaymentAccounts=%s;
                        \screditAccounts=%s""",
                id,
                name,
                birthday,
                workingPlace,
                creditRate,
                salary,
                CollectionPrinter.collectionToString(paymentAccounts),
                CollectionPrinter.collectionToString(creditAccounts));
    }
}
