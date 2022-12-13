package bank.helpers;

import bank.entity.*;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Random;

/**Класс для инициализации программы**/
public class Startup {

    /**Рандомайзер**/
    private final Random rnd;

    /**Хранилище сервисов**/
    private final ServiceHandler services;

    /**Словарь имен (для сотрудников/клиентов)**/
    private final String[] nameDict = new String[] {
            "Сидоров И.И.",
            "Иванов А.И.",
            "Петров Д.Н.",
            "Грачев В.В",
            "Сидоренко И.О."
    };

    /**
     * Конструктор
     * @param services Хранилище сервисов
     */
    public Startup(ServiceHandler services) {
        this.rnd = new Random();
        this.services = services;
    }

    /**
     * Инициализировать сущности
     * @throws Exception Ошибка инициализации сущностей
     */
    public void initEntities() throws RuntimeException {
        Bank bank = new Bank();
        bank.name = "ФТБ";
        services.getBankService().addBank(bank);
        initOffices(bank);
        initClients(bank);

        bank = new Bank();
        bank.name = "Сбарбанк";
        services.getBankService().addBank(bank);
        initOffices(bank);
        initClients(bank);

        bank = new Bank();
        bank.name = "Теньков";
        services.getBankService().addBank(bank);
        initOffices(bank);
        initClients(bank);

        bank = new Bank();
        bank.name = "Банк Расея";
        services.getBankService().addBank(bank);
        initOffices(bank);
        initClients(bank);

        bank = new Bank();
        bank.name = "МосГорГлавМинБанк";
        services.getBankService().addBank(bank);
        initOffices(bank);
        initClients(bank);
    }

    /**
     * Инициализировать банкоматы
     * @param bank Банк
     * @throws Exception Ошибка инициализации
     */
    private void initBankAtms(Bank bank, BankOffice office) throws RuntimeException {
        BankAtm atm = new BankAtm();
        atm.name = bank.name + office.address + " банкомат № 1";
        atm.isGivesMoney = true;
        atm.isTakesMoney = true;
        atm.setMoneyAmount(100000);
        services.getAtmService().addAtm(atm);
        services.getBankService().addAtmToBank(bank.id, atm.id);
        services.getAtmService().placeToOffice(atm, office);

        atm = new BankAtm();
        atm.name = bank.name + office.address + " банкомат № 2";
        atm.isGivesMoney = false;
        atm.isTakesMoney = true;
        atm.setMoneyAmount(100000);
        services.getAtmService().addAtm(atm);
        services.getBankService().addAtmToBank(bank.id, atm.id);
        services.getAtmService().placeToOffice(atm, office);

        atm = new BankAtm();
        atm.name = bank.name + office.address + " банкомат № 3";
        atm.isGivesMoney = true;
        atm.isTakesMoney = false;
        atm.setMoneyAmount(100000);
        services.getAtmService().addAtm(atm);
        services.getBankService().addAtmToBank(bank.id, atm.id);
        services.getAtmService().placeToOffice(atm, office);
    }

    /**
     * Инициализировать банковские офисы
     * @param bank Банк
     */
    private void initOffices(Bank bank) throws RuntimeException {
        BankOffice office = new BankOffice();
        office.address = "г. Запупок, ул. Выхухольная, д.69а";
        office.rentPrice = 5000;
        office.canPlaceAtm = true;
        office.isWorking = true;
        office.isCrediting = true;
        office.isGivesMoney = true;
        office.isTakesMoney = true;
        services.getOfficeService().addOffice(office);
        services.getBankService().addNewBankOffice(bank.id, office.id);
        initEmployees(office);
        initBankAtms(bank, office);

        office = new BankOffice();
        office.address = "г. Мытищчи, ул. Спортивная, д.25";
        office.rentPrice = 5000;
        office.canPlaceAtm = false;
        office.isWorking = true;
        office.isCrediting = true;
        office.isGivesMoney = true;
        office.isTakesMoney = true;
        services.getOfficeService().addOffice(office);
        services.getBankService().addNewBankOffice(bank.id, office.id);
        initEmployees(office);
        initBankAtms(bank, office);

        office = new BankOffice();
        office.address = "г. Белгород, ул. Корочанская, д.1024";
        office.rentPrice = 4000;
        office.canPlaceAtm = true;
        office.isWorking = true;
        office.isCrediting = true;
        office.isGivesMoney = true;
        office.isTakesMoney = true;
        services.getOfficeService().addOffice(office);
        services.getBankService().addNewBankOffice(bank.id, office.id);
        initEmployees(office);
        initBankAtms(bank, office);
    }

    /**
     * Инициализировать сотрудников
     * @param office Офис
     */
    private void initEmployees(BankOffice office) throws RuntimeException {
        for(int i = 0; i < 5; i++) {
            Employee emp = new Employee();
            emp.name = nameDict[rnd.nextInt(nameDict.length)];
            emp.isDistantWorking = rnd.nextInt(10) > 5;
            emp.canGiveCredit = rnd.nextInt(10) > 5;
            emp.birthday = new GregorianCalendar(1900 + rnd.nextInt(100), rnd.nextInt(13),rnd.nextInt(28)).getTime();
            emp.post = "Кредитор-сантехник";
            services.getEmployeeService().addEmployee(emp);
            services.getOfficeService().addEmployeeToOffice(office.id, emp.id);

            Collection<Bank> banks = services.getBankService().getAll();
            for(Bank bank : banks) {
                if(bank.offices.contains(office)) {
                    services.getBankService().addEmployeeToBank(bank.id, emp.id);
                }
            }
        }
    }

    /**
     * Инициализировать клиентов банка
     * @param bank Банк
     * @throws Exception Ошибка инициализации
     */
    private void initClients(Bank bank) throws RuntimeException {
        for(int i = 0; i < 5; i++) {
            User user = new User();
            user.name = nameDict[rnd.nextInt(nameDict.length)];
            user.setSalary(rnd.nextDouble(100000));
            user.birthday = new GregorianCalendar(1900 + rnd.nextInt(100), rnd.nextInt(13),rnd.nextInt(28)).getTime();
            services.getUserService().addUser(user);
            services.getBankService().addBankUser(bank.id, user.id);
            initPaymentAccounts(user, bank);
            initCreditAccounts(user, bank);
        }
    }

    /**
     * Инициализировать платежные счета клиента банка
     * @param user Клиент
     * @param bank Банк
     */
    private void initPaymentAccounts(User user, Bank bank) throws RuntimeException {
        for(int i = 0; i < 2; i++) {
            services.getPaymentAccountService().openPaymentAccount(user.id, bank.id, rnd.nextDouble(500));
        }
    }

    /**
     * Инициализировать кредитные счета клиента банка
     * @param user Клиент
     * @param bank Банк
     * @throws Exception Ошибка инициализации
     */
    private void initCreditAccounts(User user, Bank bank) throws RuntimeException {
        Employee giver = null;
        for(Employee empl : bank.employees) {
            if(empl.canGiveCredit) {
                giver = empl;
                break;
            }
        }
        if(giver == null) throw new RuntimeException("У банка \"" + bank.name + "\" нет сотрудников, которые выдают кредиты");

        for(PaymentAccount payAcc : user.paymentAccounts) {
            services.getCreditAccountService().openCreditAccount(
                    user.id, bank.id, giver.id, payAcc.id, rnd.nextDouble(10000), rnd.nextInt(12) + 1);
        }
    }
}
