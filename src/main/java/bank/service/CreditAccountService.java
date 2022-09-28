package bank.service;

import bank.entity.CreditAccount;

public interface CreditAccountService {
    public CreditAccount getCreditAccount();

    public CreditAccount addNewCreditAccount();

    public CreditAccount updateCreditAccount(CreditAccount model);
}
