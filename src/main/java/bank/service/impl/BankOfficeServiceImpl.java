package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.BankOffice;
import bank.entity.Employee;
import bank.service.BankOfficeService;
import bank.service.EmployeeService;
import helpers.Logger;

import java.util.Collection;

/**Сервис по работе с банковскими офисами*/
public class BankOfficeServiceImpl implements BankOfficeService {
    /**Репозиторий*/
    private final BankRepository rep;
    /**Логгер**/
    private final Logger logger;

    /**Сервис для работы с сотрудниками**/
    private final EmployeeService employeeService;

    /**
     * Конструктор
     * @param rep Репозиторий
     * @param logger Логгер
     */
    public BankOfficeServiceImpl(BankRepository rep, Logger logger, EmployeeService employeeService) {
        this.rep = rep;
        this.logger = logger;
        this.employeeService = employeeService;
    }

    public BankOffice getOffice(int id) {
        return rep.offices.get(id);
    }

    public Collection<BankOffice> getAllOffices() {
        return rep.offices.get();
    }

    public BankOffice addOffice(BankOffice office) {
        try {
            rep.offices.add(office);
            return office;
        }
        catch(Exception ex) {
            logger.logError("Ошибка добавления офиса: " + ex.getMessage());
            return null;
        }
    }

    public BankOffice updateBankOffice(BankOffice office) {
        try {
            return rep.offices.update(office);
        }
        catch(Exception ex) {
            logger.logError("Ошибка изменения офиса: " + ex.getMessage());
            return null;
        }
    }

    public BankOffice addEmployeeToOffice(int officeId, int employeeId) {
        BankOffice office = this.getOffice(officeId);
        Employee empl = employeeService.getEmployee(employeeId);

        office.employees.add(empl);
        empl.officeId = officeId;
        empl.office = office;

        employeeService.updateEmployee(empl);
        return this.updateBankOffice(office);
    }
}
