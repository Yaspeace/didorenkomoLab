package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.BankOffice;
import bank.service.BankOfficeService;

public class BankOfficeServiceImpl implements BankOfficeService {
    private final BankRepository rep;

    public BankOfficeServiceImpl(BankRepository rep) {
        this.rep = rep;
    }

    @Override
    public BankOffice getOffice() {
        return rep.offices.get();
    }

    @Override
    public BankOffice addNewOffice() {
        return rep.offices.update(new BankOffice());
    }

    @Override
    public BankOffice updateBankOffice(BankOffice office) {
        return rep.offices.update(office);
    }
}
