package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.service.*;
import helpers.Logger;

import java.util.Collection;

/**Сервис по работе с банками*/
public class BankServiceImpl implements BankService {

    /**Репозиторий*/
    private final BankRepository rep;

    /**Логгер**/
    private final Logger logger;

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
     * @param logger Логгер
     * @param atmService Сервис для работы с банкоматами
     * @param officeService Сервис для работы с офисами
     * @param employeeService Сервис для работы с работниками
     * @param userService Сервис для работы с пользователями
     */
    public BankServiceImpl(
            BankRepository rep,
            Logger logger,
            AtmService atmService,
            BankOfficeService officeService,
            EmployeeService employeeService,
            UserService userService) {
        this.rep = rep;
        this.logger = logger;
        this.atmService = atmService;
        this.officeService = officeService;
        this.employeeService = employeeService;
        this.userService = userService;
    }

    public Bank get(int id) {
        return rep.banks.get(id);
    }

    public Collection<Bank> getALl() {
        return rep.banks.get();
    }

    public Bank addBank(Bank bank) {
        try {
            rep.banks.add(bank);
            return bank;
        }
        catch(Exception ex) {
            logger.logError("Ошибка добавления банка: " + ex.getMessage());
            return null;
        }
    }

    public Bank updateBank(Bank b) {
        try {
            return rep.banks.update(b);
        }
        catch(Exception ex) {
            logger.logError("Ошибка при изменении банка: " + ex.getMessage());
            return null;
        }
    }

    public Bank addAtmToBank(int bankId, int atmId) throws Exception {
        Bank bank = this.get(bankId);
        BankAtm atm = atmService.getAtm(atmId);

        if(bank.atms.contains(atm)) throw new Exception("Банкомат с id=" + atm.id + " уже содержится в банке с id=" + bank.id);

        atm.bankId = bankId;
        atm.bank = bank;
        bank.atms.add(atm);
        bank.atmNum++;

        atmService.updateAtm(atm);
        return updateBank(bank);
    }

    public Bank addNewBankOffice(int bankId, int officeId) {
        Bank bank = this.get(bankId);
        BankOffice office = officeService.getOffice(officeId);

        bank.officeNum++;
        bank.offices.add(office);

        return updateBank(bank);
    }

    public Bank addEmployeeToBank(int bankId, int employeeId) {
        Bank bank = this.get(bankId);
        Employee empl = employeeService.getEmployee(employeeId);

        empl.bankId = bankId;
        empl.bank = bank;
        bank.employeeNum++;
        bank.employees.add(empl);

        employeeService.updateEmployee(empl);
        return updateBank(bank);
    }

    public Bank addBankUser(int bankId, int userId) {
        Bank bank = this.get(bankId);
        User user = userService.getUser(userId);

        user.banks.add(bank);
        bank.clientNum++;
        bank.users.add(user);

        userService.updateUser(user);
        return updateBank(bank);
    }
}
