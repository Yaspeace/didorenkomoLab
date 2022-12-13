package bank.exceptions;

/**Исключение доступности объекта**/
public class ObjectAccessException extends RuntimeException {
    /**Имя объекта**/
    private final String objectName;

    /**Причина отказа в доступе**/
    private final String reason;

    /**
     * Конструктор
     * @param objectName Имя объекта
     * @param reason Причина отказа в доступе
     */
    public ObjectAccessException(String objectName, String reason) {
        this.objectName = objectName;
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return "Ошибка доступа к объекту \"" + objectName + "\" по причине " + reason;
    }
}
