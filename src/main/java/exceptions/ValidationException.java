package exceptions;

public class ValidationException extends Exception {

    private final String fieldName;

    private final Object value;

    public ValidationException(String message, String fieldName, Object value) {
        super(message);
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + ". Поле " + fieldName + " имело значение " + value.toString() + ".";
    }
}
