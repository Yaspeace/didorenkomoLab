package bank.service;

import bank.entity.*;

public interface BankService {
    public Bank getBank();
    public Bank addNewBank();
    public Bank updateBank(Bank b);
    public BankAtm addAtmToBank(int bankId);
    public BankOffice addNewBankOffice(int bankId);
    public Employee addEmployeeToBank(int bankId);
    public User addBankUser(int bankId);
}
