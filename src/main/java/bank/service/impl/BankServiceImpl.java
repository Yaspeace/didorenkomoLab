package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.service.*;

public class BankServiceImpl implements BankService {
    private final BankRepository rep;

    private final AtmService atmService;

    private final BankOfficeService officeService;

    private final EmployeeService employeeService;

    private final UserService userService;

    public BankServiceImpl(
            BankRepository rep,
            AtmService atmService,
            BankOfficeService officeService,
            EmployeeService employeeService,
            UserService userService) {
        this.rep = rep;
        this.atmService = atmService;
        this.officeService = officeService;
        this.employeeService = employeeService;
        this.userService = userService;
    }

    @Override
    public Bank getBank() {
        return rep.banks.get();
    }

    @Override
    public Bank addNewBank() {
        return rep.banks.update(new Bank());
    }

    @Override
    public Bank updateBank(Bank b) {
        return rep.banks.update(b);
    }

    @Override
    public BankAtm addAtmToBank(int bankId) {
        Bank b = rep.banks.get();
        BankAtm atm = atmService.addNewAtm();

        atm.bankId = bankId;
        atm.bank = b;
        b.atmNum++;

        updateBank(b);
        return atmService.updateAtm(atm);
    }

    @Override
    public BankOffice addNewBankOffice(int bankId) {
        Bank b = rep.banks.get();
        BankOffice office = officeService.addNewOffice();

        b.officeNum++;
        updateBank(b);

        return office;
    }

    @Override
    public Employee addEmployeeToBank(int bankId) {
        Bank b = rep.banks.get();
        Employee e = employeeService.addNewEmployee();

        e.bankId = bankId;
        e.bank = b;
        b.employeeNum++;

        updateBank(b);
        return employeeService.updateEmployee(e);
    }

    @Override
    public User addBankUser(int bankId) {
        Bank b = rep.banks.get();
        User u = userService.getUser();

        u.banks = b;
        b.clientNum++;

        updateBank(b);
        return userService.updateUser(u);
    }
}
