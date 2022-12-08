package bank.entity;

import bank.entity.base.BaseNameEntity;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**Модель сотрудника*/
public class Employee extends BaseNameEntity {
    /**Дата рождения*/
    public Date birthday;

    /**Должность*/
    public String post;

    /**Идентификатор банка*/
    public int bankId;

    /**Банк*/
    public Bank bank;

    /**Работет ли удаленно*/
    public boolean isDistantWorking;

    /**Идентификатор офиса в котором работает сотрудник*/
    public int officeId;

    /**Офис в котором работает сотрудник*/
    public BankOffice office;

    /**Может ли выдавать кредиты*/
    public boolean canGiveCredit;

    /**Размер заработной платы*/
    public double salary;

    /**Банкоматы, обслуживаемые данным работником**/
    public Collection<BankAtm> servingAtms;

    /**Кредитные счета, выданные данным сотрудником**/
    public Collection<CreditAccount> creditAccounts;

    public Employee() {
        servingAtms = new LinkedList<>();
        creditAccounts = new LinkedList<>();
    }

    @Override
    public String toString() {
        return String.format("Employee: id=%s;name=%s;birthday=%s;post=%s;bankId=%s;isDistantWorking=%s;officeId=%s;" +
                        "canGiveCredit=%s;salary=%s",
                id,
                name,
                birthday,
                post,
                bankId,
                isDistantWorking,
                officeId,
                canGiveCredit,
                salary);
    }
}
