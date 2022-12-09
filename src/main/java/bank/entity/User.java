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
    private double salary;
    /**Кредитный рейтинг*/
    public int creditRate;
    /**Банки, которыми пользуется пользователь*/
    public Collection<Bank> banks;
    /**Платежные счета*/
    public Collection<PaymentAccount> paymentAccounts;
    /**Кредитные счета*/
    public Collection<CreditAccount> creditAccounts;

    private final Random rnd;

    public User() {
        banks = new LinkedList<>();
        paymentAccounts = new LinkedList<>();
        creditAccounts = new LinkedList<>();
        rnd = new Random();
    }

    /**
     * Установить зарплату (с пересчетом кредитного рейтинга)
     * @param salary Зарплата
     */
    public void setSalary(double salary) {
        int lowerBound = (int)salary / 10 - 100;
        if(lowerBound < 0) lowerBound = 0;

        this.creditRate = rnd.nextInt(lowerBound, lowerBound + 100);
        this.salary = salary;
    }

    /**
     * Получить зарплату
     * @return Зарплата
     */
    public double getSalary() {
        return salary;
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
