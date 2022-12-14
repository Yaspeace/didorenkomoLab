package bank.entity;

import bank.entity.base.BaseEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**Модель кредитного счета*/
public class CreditAccount extends BaseEntity {
    /**Идентификатор пользователя за которым закреплен счет*/
    public int userId;
    /**Пользователь за которым закреплен счет*/
    public User user;

    /**Название банка*/
    public String bankName;

    /**Дата начала кредита*/
    public Date dateBegin;

    /**Дата окончания кредита*/
    public Date dateEnd;

    /**Кол-во месяцев кредита*/
    public int months;

    /**Ежемесячный платеж*/
    public double monthPayment;

    /**Процентная ставка по кредиту*/
    public double percent;

    /**Идентификатор сотрудника, выдавшего кредит*/
    public int employeeId;
    /**Сотрудник, выдавший кредит*/
    public Employee employee;

    /**Идентификатор платежного счета*/
    public int paymentAccountId;
    /**Платежный счет*/
    public PaymentAccount paymentAccount;

    public String toShortString() {
        return String.format("id=%s; bankName=%s; dateBegin=%s; dateEnd=%s; months=%s; monthPayment=%.2f; percent=%.3f;",
                id,
                bankName,
                new SimpleDateFormat("dd.MM.yyyy").format(dateBegin),
                new SimpleDateFormat("dd.MM.yyyy").format(dateEnd),
                months,
                monthPayment,
                percent);
    }

    public String toString() {
        return String.format("Credit Account: id=%s;userId=%s;bankName=%s;dateBegin=%s;dateEnd=%s;months=%s;monthPayment=%.2f;" +
                        "percent=%.3f;employeeId=%s;paymentAccountId=%s",
                id,
                userId,
                bankName,
                new SimpleDateFormat("dd.MM.yyyy").format(dateBegin),
                new SimpleDateFormat("dd.MM.yyyy").format(dateEnd),
                months,
                monthPayment,
                percent,
                employeeId,
                paymentAccountId);
    }

    public static CreditAccount fromMap(Map<String, String> map) throws Exception {
        CreditAccount res = new CreditAccount();
        res.id = Integer.parseInt(map.get("id"));
        res.bankName = map.get("bankName");
        res.dateBegin = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", new Locale("us")).parse(map.get("dateBegin"));
        res.dateEnd = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", new Locale("us")).parse(map.get("dateEnd"));
        res.employeeId = Integer.parseInt(map.get("employeeId"));
        res.months = Integer.parseInt(map.get("months"));
        res.monthPayment = Double.parseDouble(map.get("monthPayment"));
        res.percent = Double.parseDouble(map.get("percent"));
        res.paymentAccountId = Integer.parseInt(map.get("paymentAccountId"));
        res.userId = Integer.parseInt(map.get("userId"));
        return res;
    }
}
