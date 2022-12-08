package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.BankAtm;
import bank.entity.BankOffice;
import bank.entity.Employee;
import bank.entity.enums.AtmStatuses;
import bank.service.AtmService;
import helpers.Logger;

import java.util.Collection;

/**Сервис по работе с банкоматами*/
public class AtmServiceImpl implements AtmService {

    /**Репозиторий*/
    private final BankRepository rep;
    /**Логгер**/
    private final Logger logger;

    /**
     * Конструктор
     * @param rep Репозиторий
     * @param logger Логгер
     */
    public AtmServiceImpl(BankRepository rep, Logger logger)
    {
        this.rep = rep;
        this.logger = logger;
    }

    @Override
    public BankAtm getAtm(int id) {
        return rep.atms.get(id);
    }

    @Override
    public Collection<BankAtm> getAllAtms() {
        return rep.atms.get();
    }

    @Override
    public BankAtm addAtm(BankAtm atm) {
        try
        {
            rep.atms.add(atm);
            return atm;
        }
        catch(Exception ex)
        {
            logger.logError("Произошла ошибка при добавлении нового банкомата");
            return null;
        }

    }

    @Override
    public BankAtm updateAtm(BankAtm model) {
        try {
            return rep.atms.update(model);
        }
        catch (Exception ex) {
            logger.logError("Ошибка при изменении банкомата");
            return null;
        }
    }

    @Override
    public void placeToOffice(BankAtm atm, BankOffice office) {
        if(!office.canPlaceAtm) return;
        try {
            atm.placingOfficeId = office.id;
            atm.placingOffice = office;
            atm.address = office.address;
            rep.atms.update(atm);
            office.atmNum++;
            rep.offices.update(office);
        }
        catch(Exception ex) {
            logger.logError("Произошла ошибка при помещении банкомата в офис");
        }
    }

    @Override
    public BankAtm setEmployee(int atmId, Employee employee) throws Exception {
        BankAtm atm = rep.atms.get(atmId);
        atm.serveEmployeeId = employee.id;
        atm.serveEmployee = employee;
        return rep.atms.update(atm);
    }

    @Override
    public double takeMoney(int atmId, double amount) {
        BankAtm atm = rep.atms.get(atmId);
        double res = 0;
        if(atm.status == AtmStatuses.noMoney || atm.status == AtmStatuses.notWorking)
            return res;
        res = Math.min(amount, atm.getMoneyAmount());
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
