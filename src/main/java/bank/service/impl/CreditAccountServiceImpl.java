package bank.service.impl;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import bank.service.*;
import bank.exceptions.NotFoundException;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Map;

/**Сервис по работе с кредитными счетами*/
public class CreditAccountServiceImpl implements CreditAccountService {
    /**Репозиторий*/
    private final BankRepository rep;

    /**Сервис для работы с пользователями*/
    private final UserService userService;

    /**Сервис для работы с банками*/
    private final BankService bankService;

    /**Сервис для работы с сотрудниками*/
    private final EmployeeService employeeService;

    /**Сервис для работы с платежными счетами*/
    private final PaymentAccountService paymentAccountService;

    /**
     * Конструктор
     * @param rep Репозиторий
     * @param userService Сервис для работы с пользователями
     * @param bankService Сервис для работы с банками
     * @param employeeService Сервис для работы с сотрудниками
     * @param paymentAccountService Сервис для работы с платежными счетами
     */
    public CreditAccountServiceImpl(BankRepository rep,
                                    UserService userService,
                                    BankService bankService,
                                    EmployeeService employeeService,
                                    PaymentAccountService paymentAccountService) {
        this.rep = rep;
        this.userService = userService;
        this.bankService = bankService;
        this.employeeService = employeeService;
        this.paymentAccountService = paymentAccountService;
    }

    public CreditAccount getCreditAccount(int id) throws NotFoundException {
        CreditAccount res = rep.creditAccounts.get(id);
        if(res == null) throw new NotFoundException(id, CreditAccount.class);
        return res;
    }

    public Collection<CreditAccount> getAll() {
        return rep.creditAccounts.get();
    }

    public CreditAccount addCreditAccount(CreditAccount creditAcc) throws RuntimeException {
        rep.creditAccounts.add(creditAcc);
        return creditAcc;
    }

    public CreditAccount updateCreditAccount(CreditAccount model) throws RuntimeException {
        return rep.creditAccounts.update(model);
    }

    public CreditAccount openCreditAccount(int userId,
                                           int bankId,
                                           int employeeId,
                                           int paymentAccountId,
                                           double monthPayment,
                                           int months) throws RuntimeException {
        User user = userService.getUser(userId);
        Bank bank = bankService.get(bankId);
        Employee empl = employeeService.getEmployee(employeeId);
        PaymentAccount payAcc = paymentAccountService.getPaymentAccount(paymentAccountId);
        CreditAccount credAcc = new CreditAccount();

        credAcc.bankName = bank.name;
        credAcc.percent = bank.percent;
        credAcc.userId = user.id;
        credAcc.user = user;
        credAcc.employeeId = empl.id;
        credAcc.employee = empl;
        credAcc.paymentAccountId = payAcc.id;
        credAcc.paymentAccount = payAcc;
        credAcc.months = months;
        credAcc.monthPayment = monthPayment;

        Calendar calendar = new GregorianCalendar();
        credAcc.dateBegin = calendar.getTime();
        calendar.add(Calendar.MONTH, 12);
        credAcc.dateEnd = calendar.getTime();

        user.creditAccounts.add(credAcc);
        empl.creditAccounts.add(credAcc);
        payAcc.creditAccounts.add(credAcc);

        userService.updateUser(user);
        employeeService.updateEmployee(empl);
        paymentAccountService.updatePaymentAccount(payAcc);

        return this.addCreditAccount(credAcc);
    }

    @Override
    public CreditAccount migrateToNewPaymentAccount(Map<String, String> credAccData, int payAccId) throws Exception {
        CreditAccount newCredAcc = CreditAccount.fromMap(credAccData);

        if(newCredAcc.paymentAccountId == payAccId)
            throw new Exception("Ошибка переноса: попытка перенести кредитный счет в тот же платежный счет");

        PaymentAccount newPayAcc = paymentAccountService.getPaymentAccount(payAccId);

        return openCreditAccount(
                newCredAcc.userId,
                newPayAcc.bankId,
                newCredAcc.employeeId,
                newPayAcc.id,
                newCredAcc.monthPayment,
                newCredAcc.months);
    }
}
