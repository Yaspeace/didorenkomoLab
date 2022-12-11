package bank.entity;

import bank.entity.base.BaseNameEntity;

import java.util.Collection;
import java.util.LinkedList;

/**Модель банковского офиса*/
public class BankOffice extends BaseNameEntity {
    /**Адрес*/
    public String address;

    /**Работает ли офис*/
    public boolean isWorking;

    /**Можно ли поместить банкомат*/
    public boolean canPlaceAtm;

    /**Число банкоматов в офисе*/
    public int atmNum;

    /**Можно ли получить в офисе кредит*/
    public boolean isCrediting;

    /**Работает ли на выдачу денег*/
    public boolean isGivesMoney;

    /**Работает ли на внесение денег*/
    public boolean isTakesMoney;

    /**Кол-во денег в офисе*/
    private double moneyAmount;

    /**Стоимость аренды офиса*/
    public double rentPrice;

    /**Сотрудники офиса**/
    public Collection<Employee> employees;

    /**Банкоматы в офисе**/
    public Collection<BankAtm> atms;

    /**
     * Конструктор
     */
    public BankOffice() {
        employees = new LinkedList<>();
        atms = new LinkedList<>();
    }

    public double getMoneyAmount() {
        return moneyAmount + atms.stream().mapToDouble(BankAtm::getMoneyAmount).sum();
    }

    public void setMoneyAmount(double value) throws Exception {
        if(value < 0)
            throw new Exception("Попытка задать отрицательное количество денег: " + value);
        moneyAmount = value;
    }

    public String toShortString() {
        return String.format("id=%s; name=%s; address=%s; isWorking=%s; canPlaceAtm=%s;" +
                "atmNum=%s; isCrediting=%s; isGivesMoney=%s; isTakesMoney=%s; moneyAmount=%s;",
                id,
                name,
                address,
                isWorking,
                canPlaceAtm,
                atmNum,
                isCrediting,
                isGivesMoney,
                isTakesMoney,
                getMoneyAmount());
    }

    @Override
    public String toString() {
        return String.format("BankOffice: id=%s;name=%s;address=%s;isWorking=%s;canPlaceAtm=%s;atmNum=%s;isCrediting=%s;" +
                        "isGivesMoney=%s;isTakesMoney=%s;moneyAmount=%s;rentPrice=%s",
                id,
                name,
                address,
                isWorking,
                canPlaceAtm,
                atmNum,
                isCrediting,
                isGivesMoney,
                isTakesMoney,
                getMoneyAmount(),
                rentPrice);
    }
}
