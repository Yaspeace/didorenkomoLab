package bank.service;

import bank.entity.Employee;

public interface EmployeeService {
    public Employee getEmployee();

    public Employee addNewEmployee();

    public Employee updateEmployee(Employee model);
}
