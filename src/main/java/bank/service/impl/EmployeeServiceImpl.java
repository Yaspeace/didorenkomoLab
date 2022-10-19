package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.Employee;
import bank.service.EmployeeService;

/**Сервис по работы с сотрудниками*/
public class EmployeeServiceImpl implements EmployeeService {
    /**Репозиторий*/
    private final BankRepository rep;

    /**
     * Конструктор
     * @param rep Репозиторий
     */
    public EmployeeServiceImpl(BankRepository rep) {
        this.rep = rep;
    }

    @Override
    public Employee getEmployee() {
        return rep.employees.get();
    }

    @Override
    public Employee addNewEmployee() {
        return rep.employees.update(new Employee());
    }

    @Override
    public Employee updateEmployee(Employee model) {
        return rep.employees.update(model);
    }
}
