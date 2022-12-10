package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.Employee;
import bank.service.EmployeeService;
import exceptions.NotFoundException;
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

    public Employee getEmployee(int id) throws NotFoundException {
        Employee res = rep.employees.get(id);
        if(res == null) throw new NotFoundException(id, Employee.class);
        return res;
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
