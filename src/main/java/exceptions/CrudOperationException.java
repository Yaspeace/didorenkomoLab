package exceptions;

public class CrudOperationException extends Exception {
    private final Class<?> entityClass;

    private final CrudOperations operation;

    public CrudOperationException(String message, Class<?> entityClass, CrudOperations operation) {
        super(message);
        this.entityClass = entityClass;
        this.operation = operation;
    }

    @Override
    public String getMessage() {
        return "Ошибка при выполнении метода \"" + operation.toString()
                + "\" над сущностью " + entityClass.getSimpleName() + ": " + super.getMessage();
    }
}
