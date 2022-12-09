package bank;

import bank.dataaccess.BankRepository;
import bank.entity.*;
import helpers.*;

import java.util.*;

public class Main {
    /**Репозиторий сущностей**/
    private static final BankRepository rep = new BankRepository();
    /**Логгер**/
    private static final Logger logger = new ConsoleLogger();

    public static void main(String[] args) {
        try {
            ServiceHandler services = new ServiceHandler(rep);
            Startup startup = new Startup(services);
            startup.initEntities();

            // Тест исключений
            /*User u = new User();
            services.getUserService().updateUser(u);*/

            printBankMenu(services.getBankService().getALl());

            Scanner sc = new Scanner(System.in);
            int bankId = sc.nextInt();
            System.out.print(services.getBankService().get(bankId).toString());
        }
        catch(Exception ex) {
            logger.logError("Ошибка работы приложения: " + ex.getMessage());
        }
    }

    /**
     * Вывести меню выбора банка
     * @param banks Список банков
     */
    private static void printBankMenu(Collection<Bank> banks) {
        System.out.println("Введите идентификатор банка для вывода:");
        for(Bank bank : banks) {
            System.out.println(bank.id + " - " + "\"" + bank.name + "\"");
        }
    }
}