package bank.service;

import bank.entity.CreditAccount;

/**Интерфейс сервисов по работе с кредитными счетами*/
public interface CreditAccountService {

    /**
     * Получить кредитный счет
     * @return Модель кредитного счета
     */
    public CreditAccount getCreditAccount();

    /**
     * Добавить новый кредитный аккаунт
     * @return Модель нового кредитного счета
     */
    public CreditAccount addNewCreditAccount();

    /**
     * Изменить кредитный счет
     * @param model Модель кредитного счета для изменения
     * @return Измененная модель
     */
    public CreditAccount updateCreditAccount(CreditAccount model);

    /**
     * Открыть кредитный счет
     * @param userId Идентификатор пользователя
     * @param bankId Идентификатор банка
     * @param employeeId Идентификатор сотрудника
     * @param paymentAccountId Идентификатор платежного счета
     * @param monthPayment Величина ежемесячного платежа
     * @param months Число месяцев действия кредитного счета
     * @return Открытый кредитный счет
     */
    public CreditAccount openCreditAccount(int userId, int bankId, int employeeId, int paymentAccountId, double monthPayment, int months);
}
