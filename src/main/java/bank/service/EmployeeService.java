package bank.service;

import bank.entity.Employee;

/**Интерфейс сервисов по работе с сотрудниками*/
public interface EmployeeService {
    /**
     * Получить сотрудника
     * @return Модель сотрудника
     */
    public Employee getEmployee();

    /**
     * Добавить нового сотрудника
     * @return Модель нового сотрудника
     */
    public Employee addNewEmployee();

    /**
     * Изменить сотрудника
     * @param model Модель сотрудника для изменения
     * @return Измененная модель
     */
    public Employee updateEmployee(Employee model);
}
