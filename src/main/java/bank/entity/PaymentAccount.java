package bank.entity;

import bank.entity.base.BaseEntity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

/**Модель платежного счета*/
public class PaymentAccount extends BaseEntity {
    /**Идентификатор пользователя*/
    public int userId;

    /**Пользователь*/
    public User user;

    /**Идентификатор банка**/
    public int bankId;

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

    public String toShortString() {
        return String.format("id=%s; bankName=%s; moneyAmount=%.2f", id, bankName, moneyAmount);
    }

    public String toString() {
        return String.format("Payment Account: id=%s;userId=%s;bankName=%s;moneyAmount=%.2f;bankId=%s",
                id,
                userId,
                bankName,
                moneyAmount,
                bankId);
    }

    public static PaymentAccount fromMap(Map<String, String> map) {
        PaymentAccount res = new PaymentAccount();
        res.id = Integer.parseInt(map.get("id"));
        res.bankId = Integer.parseInt(map.get("bankId"));
        res.bankName = map.get("bankName");
        res.userId = Integer.parseInt(map.get("userId"));
        res.moneyAmount = Double.parseDouble(map.get("moneyAmount"));
        return res;
    }
}
