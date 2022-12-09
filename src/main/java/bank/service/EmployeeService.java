package bank.service;

import bank.entity.Employee;

import java.util.Collection;

/**Интерфейс сервисов по работе с сотрудниками*/
public interface EmployeeService {
    /**
     * Получить сотрудника
     * @return Модель сотрудника
     */
    Employee getEmployee(int id);

    Collection<Employee> getAll();

    /**
     * Добавить нового сотрудника
     * @return Модель нового сотрудника
     */
    Employee addEmployee(Employee entity) throws Exception;

    /**
     * Изменить сотрудника
     * @param model Модель сотрудника для изменения
     * @return Измененная модель
     */
    Employee updateEmployee(Employee model) throws Exception;
}
