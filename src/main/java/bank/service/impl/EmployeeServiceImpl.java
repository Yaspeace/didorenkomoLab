package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.Employee;
import bank.service.EmployeeService;
import helpers.Logger;

import java.util.Collection;

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

    public Employee getEmployee(int id) {
        return rep.employees.get(id);
    }

    public Collection<Employee> getAll() {
        return rep.employees.get();
    }

    public Employee addEmployee(Employee entity) throws Exception {
        rep.employees.add(entity);
        return entity;
    }

    @Override
    public Employee updateEmployee(Employee model) throws Exception {
        return rep.employees.update(model);
    }
}
