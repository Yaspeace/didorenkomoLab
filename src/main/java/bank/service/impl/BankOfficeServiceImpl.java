package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.BankOffice;
import bank.service.BankOfficeService;

/**Сервис по работе с банковскими офисами*/
public class BankOfficeServiceImpl implements BankOfficeService {
    /**Репозиторий*/
    private final BankRepository rep;

    /**
     * Конструктор
     * @param rep Репозиторий
     */
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
