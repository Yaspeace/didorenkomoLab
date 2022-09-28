package bank.service;

import bank.entity.PaymentAccount;

public interface PaymentAccountService {
    public PaymentAccount getPaymentAccount();

    public PaymentAccount addNewPaymentAccount();

    public PaymentAccount updatePaymentAccount(PaymentAccount model);
}
