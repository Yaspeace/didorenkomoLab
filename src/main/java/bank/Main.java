package bank;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.service.*;
import bank.service.impl.*;

import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        BankRepository rep = new BankRepository();

        AtmService atmService = new AtmServiceImpl(rep);
        BankOfficeService officeService = new BankOfficeServiceImpl(rep);
        EmployeeService employeeService = new EmployeeServiceImpl(rep);
        UserService userService = new UserServiceImpl(rep);
        BankService bankService = new BankServiceImpl(rep, atmService, officeService, employeeService, userService);
        PaymentAccountService payAccService = new PaymentAccountServiceImpl(rep, userService, bankService);
        CreditAccountService credAccService = new CreditAccountServiceImpl(rep, userService, bankService, employeeService, payAccService);

        Bank bank = bankService.addNewBank();
        bank.name = "НедоБанк";
        bankService.updateBank(bank);

        BankAtm atm = bankService.addAtmToBank(bank.id);
        atm.id = 1;
        atm.isGivesMoney = true;
        atmService.updateAtm(atm);

        BankOffice office = bankService.addNewBankOffice(bank.id);
        office.address = "г. Запупок, ул. Выхухольная, д.69а";
        office.rentPrice = 5000;
        office.id = 1;
        office.canPlaceAtm = true;
        officeService.updateBankOffice(office);

        atmService.placeToOffice(atm, office);

        Employee employee = bankService.addEmployeeToBank(bank.id);
        employee.name = "Хрущев А.В.";
        employee.birthday = new Date(1991, Calendar.MARCH,1);
        employeeService.updateEmployee(employee);

        atmService.setEmployee(employee);

        User user = bankService.addBankUser(bank.id);
        user.name = "Мощный О.О.";
        user.birthday = new Date(1990, Calendar.JULY, 24);
        user.salary = 15000;
        user.workingPlace = "ООО Золотой глобус";
        user.creditRate = 200;
        userService.updateUser(user);

        PaymentAccount payAcc = payAccService.openPaymentAccount(user.id, bank.id);
        CreditAccount credAcc = credAccService.openCreditAccount(user.id, bank.id, employee.id, payAcc.id, 10000, 12);

        System.out.println(bankService.getBank());
        System.out.println(atmService.getAtm());
        System.out.println(officeService.getOffice());
        System.out.println(employeeService.getEmployee());
        System.out.println(userService.getUser());
        System.out.println(payAccService.getPaymentAccount());
        System.out.println(credAccService.getCreditAccount());
    }
}