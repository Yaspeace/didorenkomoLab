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
        return rep.atms.getEntity();
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
    public BankAtm placeToOffice(BankOffice office) {
        BankAtm atm = this.getAtm();
        atm.placingOfficeId = office.id;
        atm.placingOffice = office;
        atm.address = office.address;
        rep.atms.update(atm);
        office.atmNum++;
        rep.offices.update(office);
        return rep.atms.getEntity();
    }

    @Override
    public BankAtm setEmployee(Employee employee) {
        BankAtm atm = rep.atms.getEntity();
        atm.serveEmployeeId = employee.id;
        atm.serveEmployee = employee;
        return rep.atms.update(atm);
    }

    @Override
    public double takeMoney(double amount) {
        BankAtm atm = rep.atms.getEntity();
        double res = 0;
        if(atm.status == AtmStatuses.noMoney || atm.status == AtmStatuses.notWorking)
            return res;
        res = Math.min(amount, atm.getMoneyAmount());
        atm.setMoneyAmount(atm.getMoneyAmount() - amount);
        return res;
    }

    @Override
    public double depositMoney(double amount) {
        BankAtm atm = rep.atms.getEntity();
        atm.setMoneyAmount(atm.getMoneyAmount() + amount);
        return amount;
    }
}
