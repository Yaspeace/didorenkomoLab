package bank.entity;

import bank.entity.base.BaseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
