package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.service.*;

/**Сервис по работе с банками*/
public class BankServiceImpl implements BankService {

    /**Репозиторий*/
    private final BankRepository rep;

    /**Сервис для работы с банкоматами*/
    private final AtmService atmService;

    /**Сервис для работы с офисами*/
    private final BankOfficeService officeService;

    /**Сервис по работе с сотрудниками*/
    private final EmployeeService employeeService;

    /**Сервис по работе с пользователями*/
    private final UserService userService;

    /**
     * Конструктор
     * @param rep Репозиторий
     * @param atmService Сервис для работы с банкоматами
     * @param officeService Сервис для работы с офисами
     * @param employeeService Сервис для работы с работниками
     * @param userService Сервис для работы с пользователями
     */
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
        User u = userService.addNewUser();

        u.banks = b;
        b.clientNum++;

        updateBank(b);
        return userService.updateUser(u);
    }
}
