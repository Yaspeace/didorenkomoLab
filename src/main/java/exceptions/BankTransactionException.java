package exceptions;

/**Исключение банковской транзакции**/
public class BankTransactionException extends Exception {
    /**Номер транзакции**/
    private final long number;

    /**Вид транзакции**/
    private final BankTransactions transactionType;

    /**
     * Конструктор
     * @param message Сообщение об ошибке
     * @param number Номер транзакции
     * @param transactionType Вид транзакции
     */
    public BankTransactionException(String message, long number, BankTransactions transactionType) {
        super(message);
        this.number = number;
        this.transactionType = transactionType;
    }

    @Override
    public String getMessage() {
        return "Ошибка выполнения транзакции типа " +
                "\"" + transactionType.toString() + "\" номер " + number + ": " + super.getMessage();
    }
}
