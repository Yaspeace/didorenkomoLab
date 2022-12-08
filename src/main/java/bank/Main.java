package bank;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.service.*;
import bank.service.impl.*;
import helpers.CollectionPrinter;
import helpers.ConsoleLogger;
import helpers.Logger;

import java.util.*;

public class Main {
    private static BankRepository rep = new BankRepository();
    private static Logger logger = new ConsoleLogger();
    private static AtmService atmService = new AtmServiceImpl(rep, logger);
    private static EmployeeService employeeService = new EmployeeServiceImpl(rep, logger);
    private static BankOfficeService officeService = new BankOfficeServiceImpl(rep, logger, employeeService);
    private static UserService userService = new UserServiceImpl(rep, logger);
    private static BankService bankService = new BankServiceImpl(rep, logger, atmService, officeService, employeeService, userService);
    private static PaymentAccountService payAccService = new PaymentAccountServiceImpl(rep, logger, userService, bankService);

    private static CreditAccountService credAccService = new CreditAccountServiceImpl(rep, logger, userService, bankService, employeeService, payAccService);

    private static final String[] nameDict = new String[] {
            "Сидоров И.И.",
            "Иванов А.И.",
            "Петров Д.Н.",
    };

    private static final Random rnd = new Random();

    public static void main(String[] args) {
        try {
            initBanks();

            printBankMenu(bankService.getALl());

            Scanner sc = new Scanner(System.in);
            int bankId = sc.nextInt();
            System.out.print(bankService.get(bankId).toString());
        }
        catch(Exception ex) {
            logger.logError("Ошибка работы приложения: " + ex.getMessage());
        }
    }

    private static void printBankMenu(Collection<Bank> banks) {
        System.out.println("Введите идентификатор банка для вывода:");
        for(Bank bank : banks) {
            System.out.println(bank.id + " - " + "\"" + bank.name + "\"");
        }
    }

    private static void initBanks() throws Exception {
        Bank bank = new Bank();
        bank.name = "ФТБ";
        bankService.addBank(bank);
        initBankAtms(bank);
        initOffices(bank);

        bank = new Bank();
        bank.name = "Сбарбанк";
        bankService.addBank(bank);
        initBankAtms(bank);
        initOffices(bank);

        bank = new Bank();
        bank.name = "Теньков";
        bankService.addBank(bank);
        initBankAtms(bank);
        initOffices(bank);

        bank = new Bank();
        bank.name = "Банк Расея";
        bankService.addBank(bank);
        initBankAtms(bank);
        initOffices(bank);

        bank = new Bank();
        bank.name = "МосГорГлавМинБанк";
        bankService.addBank(bank);
        initBankAtms(bank);
        initOffices(bank);
    }

    private static void initBankAtms(Bank bank) throws Exception {
        BankAtm atm = new BankAtm();
        atm.name = bank.name + " банкомат № 1";
        atm.isGivesMoney = true;
        atm.isTakesMoney = true;
        atmService.addAtm(atm);
        bankService.addAtmToBank(bank.id, atm.id);

        atm = new BankAtm();
        atm.name = bank.name + " банкомат № 2";
        atm.isGivesMoney = false;
        atm.isTakesMoney = true;
        atmService.addAtm(atm);
        bankService.addAtmToBank(bank.id, atm.id);

        atm = new BankAtm();
        atm.name = bank.name + " банкомат № 3";
        atm.isGivesMoney = true;
        atm.isTakesMoney = false;
        atmService.addAtm(atm);
        bankService.addAtmToBank(bank.id, atm.id);
    }

    private static void initOffices(Bank bank) throws Exception {
        BankOffice office = new BankOffice();
        office.address = "г. Запупок, ул. Выхухольная, д.69а";
        office.rentPrice = 5000;
        office.canPlaceAtm = true;
        officeService.addOffice(office);
        bankService.addNewBankOffice(bank.id, office.id);
        initEmployees(office);

        office = new BankOffice();
        office.address = "г. Мытищчи, ул. Спортивная, д.25";
        office.rentPrice = 5000;
        office.canPlaceAtm = false;
        officeService.addOffice(office);
        bankService.addNewBankOffice(bank.id, office.id);
        initEmployees(office);

        office = new BankOffice();
        office.address = "г. Белгород, ул. Корочанская, д.1024";
        office.rentPrice = 4000;
        office.canPlaceAtm = true;
        officeService.addOffice(office);
        bankService.addNewBankOffice(bank.id, office.id);
        initEmployees(office);
    }

    private static void initEmployees(BankOffice office) {
        for(int i = 0; i < 5; i++) {
            Employee emp = new Employee();
            emp.name = nameDict[rnd.nextInt(3)];
            emp.isDistantWorking = rnd.nextInt(10) > 5;
            emp.canGiveCredit = rnd.nextInt(10) > 5;
            employeeService.addEmployee(emp);
            officeService.addEmployeeToOffice(office.id, emp.id);

            Collection<Bank> banks = bankService.getALl();
            for(Bank bank : banks) {
                if(bank.offices.contains(office)) {
                    bankService.addEmployeeToBank(bank.id, emp.id);
                }
            }
        }
    }
}