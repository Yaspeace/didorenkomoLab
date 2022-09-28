package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.BankAtm;
import bank.entity.BankOffice;
import bank.entity.Employee;
import bank.entity.enums.AtmStatuses;
import bank.service.AtmService;

public class AtmServiceImpl implements AtmService {
    private final BankRepository rep;

    public AtmServiceImpl(BankRepository rep)
    {
        this.rep = rep;
    }
    @Override
    public BankAtm getAtm() {
        return rep.atms.get();
    }

    @Override
    public BankAtm addNewAtm() {
        return rep.atms.update(new BankAtm());
    }

    @Override
    public BankAtm updateAtm(BankAtm model) {
        return rep.atms.update(model);
    }

    @Override
    public void placeToOffice(BankAtm atm, BankOffice office) {
        if(!office.canPlaceAtm) return;

        atm.placingOfficeId = office.id;
        atm.placingOffice = office;
        atm.address = office.address;
        rep.atms.update(atm);
        office.atmNum++;
        rep.offices.update(office);
    }

    @Override
    public BankAtm setEmployee(Employee employee) {
        BankAtm atm = rep.atms.get();
        atm.serveEmployeeId = employee.id;
        atm.serveEmployee = employee;
        return rep.atms.update(atm);
    }

    @Override
    public double takeMoney(double amount) {
        BankAtm atm = rep.atms.get();
        double res = 0;
        if(atm.status == AtmStatuses.noMoney || atm.status == AtmStatuses.notWorking)
            return res;
        res = Math.min(amount, atm.getMoneyAmount());
        atm.setMoneyAmount(atm.getMoneyAmount() - amount);
        atm.bank.setTotalMoneyAmount(atm.bank.getTotalMoneyAmount() - amount);
        return res;
    }

    @Override
    public double depositMoney(double amount) {
        BankAtm atm = rep.atms.get();
        atm.setMoneyAmount(atm.getMoneyAmount() + amount);
        atm.bank.setTotalMoneyAmount(atm.bank.getTotalMoneyAmount() + amount);
        return amount;
    }
}
