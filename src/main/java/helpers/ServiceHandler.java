package helpers;

import bank.dataaccess.BankRepository;
import bank.service.*;
import bank.service.impl.*;

/**
 * Класс инкапсулирующий сервисы
 */
public class ServiceHandler {
    /**Сервис для работы с банкоматами**/
    private final AtmService atmService;
    /**Сервис по работе с сотрудниками**/
    private final EmployeeService employeeService;
    /**Сервис по работе с банковскими офисами**/
    private final BankOfficeService officeService;
    /**Сервис по работе с клиентами**/
    private final UserService userService;
    /**Сервис по работе с банками**/
    private final BankService bankService;
    /**Сервис по работе с платежными счетами**/
    private final PaymentAccountService payAccService;
    /**Сервис по работе с кредитными счетами**/
    private final CreditAccountService credAccService;

    /**
     * Конструктор
     * @param rep Репозиторий
     * @param logger Логгер
     */
    public ServiceHandler(BankRepository rep, Logger logger) {
        atmService = new AtmServiceImpl(rep, logger);
        employeeService = new EmployeeServiceImpl(rep, logger);
        officeService = new BankOfficeServiceImpl(rep, logger, employeeService);
        userService = new UserServiceImpl(rep, logger);
        bankService = new BankServiceImpl(rep, logger, atmService, officeService, employeeService, userService);
        payAccService = new PaymentAccountServiceImpl(rep, logger, userService, bankService);
        credAccService = new CreditAccountServiceImpl(rep, logger, userService, bankService, employeeService, payAccService);
    }

    /**Получить сервис для работы с банкоматами**/
    public AtmService getAtmService() {
        return atmService;
    }

    /**Получить сервис для работы с сотрудниками**/
    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    /**Получить сервис для работы с банковскими офисами**/
    public BankOfficeService getOfficeService() {
        return officeService;
    }

    /**Получить сервис для работы с клиентами**/
    public UserService getUserService() {
        return userService;
    }

    /**Получить сервис для работы с платежными счетами**/
    public PaymentAccountService getPaymentAccountService() {
        return payAccService;
    }

    /**Получить сервис для работы с кредитными счетами**/
    public CreditAccountService getCreditAccountService() {
        return credAccService;
    }

    /**Получить сервис для работы с банками**/
    public BankService getBankService() {
        return bankService;
    }
}
