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

    /**Сервис для работы с сотрудниками**/
    private final EmployeeService employeeService;

    /**
     * Конструктор
     * @param rep Репозиторий
     */
    public BankOfficeServiceImpl(BankRepository rep, EmployeeService employeeService) {
        this.rep = rep;
        this.employeeService = employeeService;
    }

    public BankOffice getOffice(int id) {
        return rep.offices.get(id);
    }

    public Collection<BankOffice> getAllOffices() {
        return rep.offices.get();
    }

    public BankOffice addOffice(BankOffice office) throws Exception {
        rep.offices.add(office);
        return office;
    }

    public BankOffice updateBankOffice(BankOffice office) throws Exception {
        return rep.offices.update(office);
    }

    public BankOffice addEmployeeToOffice(int officeId, int employeeId) throws Exception {
        BankOffice office = this.getOffice(officeId);
        Employee empl = employeeService.getEmployee(employeeId);

        office.employees.add(empl);
        empl.officeId = officeId;
        empl.office = office;

        employeeService.updateEmployee(empl);
        return this.updateBankOffice(office);
    }
}
