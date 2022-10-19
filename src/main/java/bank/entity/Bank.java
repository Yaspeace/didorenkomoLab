package bank.entity;

import bank.entity.base.BaseNameEntity;

import java.util.Random;

/**Модель банка*/
public class Bank extends BaseNameEntity {
    /**Число офисов банка*/
    public int officeNum;

    /**Число банкоматов банка*/
    public int atmNum;

    /**Число работников банка*/
    public int employeeNum;

    /**Число клиентов банка*/
    public int clientNum;

    /**Рейтинг*/
    public int rate;

    /**Процентная ставка*/
    public double percent;

    /**Всего денег у банка*/
    private double totalMoneyAmount;

    /**
     * Устуновить количетсов денег банка
     * @param value Количество денег
     */
    public void setTotalMoneyAmount(double value)
    {
        totalMoneyAmount = value;
        if(totalMoneyAmount < 0) totalMoneyAmount = 0;
    }

    /**
     * Получить количество денег банка
     * @return Количество денег
     */
    public double getTotalMoneyAmount() { return totalMoneyAmount; }

    /**
     * Конструктор
     */
    public Bank() {
        Random rnd = new Random();
        rate = rnd.nextInt(101);
        percent = rnd.nextDouble(11 - (rate / 10.0), 20 - (rate / 10.0));
    }

    @Override
    public String toString() {
        return String.format("Bank: id=%s;name=%s;officeNum=%s;atmNum=%s;employeeNum=%s;clientNum=%s;rate=%s;percent=%s;totalMoneyAmount=%s",
                id,
                name,
                officeNum,
                atmNum,
                employeeNum,
                clientNum,
                rate,
                percent,
                totalMoneyAmount);
    }
}
