package bank.entity;

import bank.entity.base.BaseNameEntity;
import helpers.CollectionPrinter;

import java.util.Collection;
import java.util.LinkedList;
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

    /**Банкоматы банка**/
    public Collection<BankAtm> atms;

    /**Офисы банка**/
    public Collection<BankOffice> offices;

    /**Пользователи банка**/
    public Collection<User> users;

    /**Сотрудники банка**/
    public Collection<Employee> employees;

    /**Платежные счета относящиеся к данному банку**/
    public Collection<PaymentAccount> paymentAccounts;

    /**Всего денег у банка*/
    private double totalMoneyAmount;

    /**
     * Установить количетсов денег банка
     * @param value Количество денег
     */
    public void setTotalMoneyAmount(double value) throws Exception
    {
        if(value < 0)
            throw new Exception("Попытка установить отрицательную сумму: " + value);

        totalMoneyAmount = value;
    }

    /**
     * Получить количество денег банка
     * @return Количество денег
     */
    public double getTotalMoneyAmount() {
        return totalMoneyAmount + offices.stream().mapToDouble(BankOffice::getMoneyAmount).sum();
    }

    /**
     * Конструктор
     */
    public Bank() {
        Random rnd = new Random();
        rate = rnd.nextInt(101);
        percent = rnd.nextDouble(11 - (rate / 10.0), 20 - (rate / 10.0));
        atms = new LinkedList<>();
        offices = new LinkedList<>();
        users = new LinkedList<>();
        employees = new LinkedList<>();
        paymentAccounts = new LinkedList<>();
    }

    public String toShortString() {
        return String.format("id=%s; name=%s; officeNum=%s; atmNum=%s; " +
                "employeeNum=%s; clientNum=%s; rate=%s; percent=%.3f; totalMoneyAmount=%.2f;",
                id,
                name,
                officeNum,
                atmNum,
                employeeNum,
                clientNum,
                rate,
                percent,
                getTotalMoneyAmount());
    }

    public String toString() {
        return String.format("""
                        Bank:
                        \sid=%s;
                        \sname=%s;
                        \sofficeNum=%s;
                        \satmNum=%s;
                        \semployeeNum=%s;
                        \sclientNum=%s;
                        \srate=%s;
                        \spercent=%.3f;
                        \stotalMoneyAmount=%.2f;
                        \satms=%s;
                        \soffices=%s;
                        \susers=%s;
                        \semployees=%s;
                        \spaymentAccounts=%s""",
                id,
                name,
                officeNum,
                atmNum,
                employeeNum,
                clientNum,
                rate,
                percent,
                getTotalMoneyAmount(),
                CollectionPrinter.collectionToString(atms),
                CollectionPrinter.collectionToString(offices),
                CollectionPrinter.collectionToString(users),
                CollectionPrinter.collectionToString(employees),
                CollectionPrinter.collectionToString(paymentAccounts));
    }
}
