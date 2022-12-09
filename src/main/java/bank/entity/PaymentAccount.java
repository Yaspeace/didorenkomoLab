package bank.entity;

import bank.entity.base.BaseEntity;

import java.util.Collection;
import java.util.LinkedList;

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

    /**Кредитные счета, привязанные к данному счету**/
    public Collection<CreditAccount> creditAccounts;

    /**
     * Конструктор
     */
    public PaymentAccount() {
        creditAccounts = new LinkedList<>();
    }

    public String toString() {
        return String.format("Payment Account: id=%s;userId=%s;bankName=%s;moneyAmount=%s",
                id,
                userId,
                bankName,
                moneyAmount);
    }
}
