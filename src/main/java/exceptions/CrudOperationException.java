package exceptions;

/**Исключение выполнения CRUD-операции**/
public class CrudOperationException extends Exception {
    /**Имя класса сущности, над которой производилась операция**/
    private final String className;

    /**Вид CRUD-операции**/
    private final CrudOperations operation;

    /**
     * Конструктор
     * @param message Сообщение об ошибке
     * @param entityClass Класс сущности, над которой производилась операция
     * @param operation Вид CRUD-операции
     */
    public CrudOperationException(String message, Class<?> entityClass, CrudOperations operation) {
        super(message);
        this.className = entityClass.getSimpleName();
        this.operation = operation;
    }

    public CrudOperationException(String message, String className, CrudOperations operation) {
        super(message);
        this.className = className;
        this.operation = operation;
    }

    @Override
    public String getMessage() {
        return "Ошибка при выполнении метода \"" + operation.toString()
                + "\" над сущностью " + className + ": " + super.getMessage();
    }
}
