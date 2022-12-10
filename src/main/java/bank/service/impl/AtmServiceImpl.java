package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.BankAtm;
import bank.entity.BankOffice;
import bank.entity.Employee;
import bank.entity.enums.AtmStatuses;
import bank.service.AtmService;
import exceptions.CrudOperationException;
import exceptions.NotFoundException;
import exceptions.ObjectAccessException;
import helpers.Logger;

import java.util.Collection;

/**Сервис по работе с банкоматами*/
public class AtmServiceImpl implements AtmService {

    /**Репозиторий*/
    private final BankRepository rep;

    /**
     * Конструктор
     * @param rep Репозиторий
     */
    public AtmServiceImpl(BankRepository rep) {
        this.rep = rep;
    }

    @Override
    public BankAtm getAtm(int id) throws NotFoundException {
        BankAtm res = rep.atms.get(id);
        if (res == null) throw new NotFoundException(id, BankAtm.class);
        return res;
    }

    @Override
    public Collection<BankAtm> getAllAtms() {
        return rep.atms.get();
    }

    @Override
    public BankAtm addAtm(BankAtm atm) throws Exception {
        rep.atms.add(atm);
        return atm;
    }

    @Override
    public BankAtm updateAtm(BankAtm model) throws Exception {
        return rep.atms.update(model);
    }

    @Override
    public void placeToOffice(BankAtm atm, BankOffice office) throws Exception {
        if(!office.canPlaceAtm) return;

        atm.placingOfficeId = office.id;
        atm.placingOffice = office;
        atm.address = office.address;

        rep.atms.update(atm);
        office.atmNum++;
        office.atms.add(atm);
        rep.offices.update(office);
    }

    @Override
    public BankAtm setEmployee(int atmId, Employee employee) throws Exception {
        BankAtm atm = rep.atms.get(atmId);
        atm.serveEmployeeId = employee.id;
        atm.serveEmployee = employee;
        return rep.atms.update(atm);
    }

    @Override
    public double takeMoney(int atmId, double amount) throws Exception {
        BankAtm atm = rep.atms.get(atmId);
        if(!atm.isGivesMoney)
            throw new ObjectAccessException(atm.name, "не работает на выдачу");
        if(atm.status == AtmStatuses.noMoney || atm.status == AtmStatuses.notWorking)
            throw new ObjectAccessException(atm.name, "находится в не рабочем состоянии");
        if(atm.getMoneyAmount() < amount)
            throw new Exception("Сумма в банкомате (" + atm.getMoneyAmount() + ") меньше запрашиваемой (" + amount + ")");
        double res = Math.min(amount, atm.getMoneyAmount());
        atm.setMoneyAmount(atm.getMoneyAmount() - amount);
        atm.bank.setTotalMoneyAmount(atm.bank.getTotalMoneyAmount() - amount);
        return res;
    }

    @Override
    public double depositMoney(int atmId, double amount) {
        BankAtm atm = rep.atms.get(atmId);
        atm.setMoneyAmount(atm.getMoneyAmount() + amount);
        atm.bank.setTotalMoneyAmount(atm.bank.getTotalMoneyAmount() + amount);
        return amount;
    }
}
