package bank.exceptions;

/**Исключение валидации объекта**/
public class ValidationException extends RuntimeException {

    /**Имя поля, не прошедшего валидацию**/
    private final String fieldName;
    /**Значение поля, которое не прошло валидацию**/
    private final Object value;

    /**
     * Конструктор
     * @param objectName Название объекта, не прошедшего валидацию
     * @param fieldName Название поля, не прошедшего валидацию
     * @param value Значение поля, не прошедшего валидацию
     */
    public ValidationException(String objectName, String fieldName, Object value) {
        super(objectName);
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public String getMessage() {
        return "Ошибка валидации " + super.getMessage() + ". Поле "
                + fieldName + " имело значение " + value.toString() + ".";
    }
}
