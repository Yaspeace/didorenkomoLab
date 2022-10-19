package bank.entity;

import bank.entity.base.BaseNameEntity;

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
    public double moneyAmount;

    /**Стоимость аренды офиса*/
    public double rentPrice;

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
                moneyAmount,
                rentPrice);
    }
}
