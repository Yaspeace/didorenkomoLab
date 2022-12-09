package bank.service;

import bank.entity.CreditAccount;

import java.util.Collection;

/**Интерфейс сервисов по работе с кредитными счетами*/
public interface CreditAccountService {

    /**
     * Получить кредитный счет
     * @param id Идентификатор платежного счета
     * @return Модель кредитного счета
     */
    CreditAccount getCreditAccount(int id);

    Collection<CreditAccount> getAll();

    /**
     * Добавить новый кредитный аккаунт
     * @return Модель нового кредитного счета
     */
    CreditAccount addCreditAccount(CreditAccount creditAcc) throws Exception;

    /**
     * Изменить кредитный счет
     * @param model Модель кредитного счета для изменения
     * @return Измененная модель
     */
    CreditAccount updateCreditAccount(CreditAccount model) throws Exception;

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
    CreditAccount openCreditAccount(int userId, int bankId, int employeeId, int paymentAccountId, double monthPayment, int months) throws Exception;
}
