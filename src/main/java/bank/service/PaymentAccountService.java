package bank.service;

import bank.entity.PaymentAccount;

/**Интерфейс сервисов по работе с платежными счетами*/
public interface PaymentAccountService {

    /**
     * Получить платежный счет
     * @return Модель платежного счета
     */
    public PaymentAccount getPaymentAccount();

    /**
     * Добавить новый платежный счет
     * @return Модель нового платежного счета
     */
    public PaymentAccount addNewPaymentAccount();

    /**
     * Изменить платежный счет
     * @param model Модель платежного счета для изменения
     * @return Измененная модель
     */
    public PaymentAccount updatePaymentAccount(PaymentAccount model);

    /**
     * Открыть платежный счет
     * @param userId Идентификатор пользователя
     * @param bankId Идентификатор банка
     * @return Открытый платежный счет
     */
    public PaymentAccount openPaymentAccount(int userId, int bankId);
}
