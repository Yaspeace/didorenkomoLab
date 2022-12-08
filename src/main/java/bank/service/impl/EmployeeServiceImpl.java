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

    /**Логгер**/
    private final Logger logger;

    /**
     * Конструктор
     * @param rep Репозиторий
     * @param logger Логгер
     */
    public EmployeeServiceImpl(BankRepository rep, Logger logger) {
        this.rep = rep;
        this.logger = logger;
    }

    public Employee getEmployee(int id) {
        return rep.employees.get(id);
    }

    public Collection<Employee> getAll() {
        return rep.employees.get();
    }

    public Employee addEmployee(Employee entity) {
        try {
            rep.employees.add(entity);
            return entity;
        }
        catch(Exception ex) {
            logger.logError("Ошибка добавления сотрудника: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee model) {
        try {
            return rep.employees.update(model);
        }
        catch(Exception ex) {
            logger.logError("Ошибка изменения сотрудника: " + ex.getMessage());
            return null;
        }
    }
}
