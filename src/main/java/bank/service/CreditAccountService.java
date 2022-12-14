package bank.service;

import bank.entity.CreditAccount;
import bank.exceptions.NotFoundException;

import java.util.Collection;
import java.util.Map;

/**Интерфейс сервисов по работе с кредитными счетами*/
public interface CreditAccountService {

    /**
     * Получить кредитный счет
     * @param id Идентификатор платежного счета
     * @return Модель кредитного счета
     */
    CreditAccount getCreditAccount(int id) throws NotFoundException;

    Collection<CreditAccount> getAll();

    /**
     * Добавить новый кредитный аккаунт
     * @return Модель нового кредитного счета
     */
    CreditAccount addCreditAccount(CreditAccount creditAcc) throws RuntimeException;

    /**
     * Изменить кредитный счет
     * @param model Модель кредитного счета для изменения
     * @return Измененная модель
     */
    CreditAccount updateCreditAccount(CreditAccount model) throws RuntimeException;

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
    CreditAccount openCreditAccount(int userId, int bankId, int employeeId, int paymentAccountId, double monthPayment, int months) throws RuntimeException;

    /**
     * Перенести данные кредитного счета в новый платежный счет через сериализацию
     * @param credAccData Сериализованные данные кредитного счета со старым платежным счетом
     * @param payAccId Идентификатор нового платежного счета
     * @return Перенесенный кредитный счет
     * @throws Exception Ошибка переноса
     */
    CreditAccount migrateToNewPaymentAccount(Map<String, String> credAccData, int payAccId) throws Exception;
}
