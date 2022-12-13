package bank.service;

import bank.entity.PaymentAccount;
import bank.exceptions.NotFoundException;

import java.util.Collection;

/**Интерфейс сервисов по работе с платежными счетами*/
public interface PaymentAccountService {
    /**
     * Получить платежный счет
     * @param id Идентификатор платежного счета
     * @return Модель платежного счета
     */
    PaymentAccount getPaymentAccount(int id) throws NotFoundException;

    /**
     * Получить все платежные счета
     * @return Коллекция моделей платежных счетов
     */
    Collection<PaymentAccount> getAll();

    /**
     * Добавить платежный счет
     * @return Модель нового платежного счета
     */
    PaymentAccount addPaymentAccount(PaymentAccount paymentAcc) throws RuntimeException;

    /**
     * Изменить платежный счет
     * @param model Модель платежного счета для изменения
     * @return Измененная модель
     */
    PaymentAccount updatePaymentAccount(PaymentAccount model) throws RuntimeException;

    /**
     * Открыть платежный счет
     * @param userId Идентификатор пользователя
     * @param bankId Идентификатор банка
     * @param initialSumm Начальная сумма на счете
     * @return Открытый платежный счет
     */
    PaymentAccount openPaymentAccount(int userId, int bankId, double initialSumm) throws RuntimeException;
}
