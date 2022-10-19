package bank.entity;

import bank.entity.base.BaseNameEntity;
import bank.entity.enums.AtmStatuses;

/**Модель банкомата*/
public class BankAtm extends BaseNameEntity {
    /**Адрес*/
    public String address;

    /**Статус*/
    public AtmStatuses status;

    /**Идентификатор банка*/
    public int bankId;
    /**Банк*/
    public Bank bank;

    /**Идентификатор офиса в котором располоден банкомат*/
    public int placingOfficeId;
    /**Офис в котором расположен банкомат*/
    public BankOffice placingOffice;

    /**Идентификатор обслуживающего персонала*/
    public int serveEmployeeId;
    /**Обслуживающий персонал*/
    public Employee serveEmployee;

    /**Может ли работать на выдачу денег*/
    public boolean isGivesMoney;

    /**Может ли работать на внесение денег*/
    public boolean isTakesMoney;

    /**Кол-во денег в банкомате*/
    private double moneyAmount;

    /**Цена обслуживания*/
    public double servicePrice;

    /**
     * Получить кол-во денег банкомата
     * @return Кол-во денег
     */
    public double getMoneyAmount() {
        return moneyAmount;
    }

    /**
     * Установить кол-во денег банкомата
     * @param moneyAmount Кол-во денег
     */
    public void setMoneyAmount(double moneyAmount) {
        if(this.moneyAmount == 0 && moneyAmount > 0)
            if(!(status == AtmStatuses.notWorking))
                status = AtmStatuses.working;
        this.moneyAmount = moneyAmount;
        if(this.moneyAmount <= 0)
        {
            this.moneyAmount = 0;
            if(!(status == AtmStatuses.notWorking))
            {
                status = AtmStatuses.noMoney;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("BankAtm: id=%s;name=%s;address=%s;status=%s;bankId=%s;placingOfficeId=%s;serveEmployeeId=%s;" +
                        "isGivesMoney=%s;isTakesMoney=%s;moneyAmount=%s;servicePrice=%s",
                id,
                name,
                address,
                status,
                bankId,
                placingOfficeId,
                serveEmployeeId,
                isGivesMoney,
                isTakesMoney,
                moneyAmount,
                servicePrice);
    }
}
