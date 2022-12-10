package exceptions;

/**Исключение ненахождения объекта по идентификатору**/
public class NotFoundException extends Exception {
    /**Идентификатор, по которому осуществлялся поиск**/
    private final int id;
    /**Класс объекта, подлежащего поиску**/
    private final Class<?> entityClass;

    /**
     * Конструктор
     * @param id Идентификатор, по которому осуществлялся поиск
     * @param entityClass Класс объекта, подлежащего поиску
     */
    public NotFoundException(int id, Class<?> entityClass) {
        this.id = id;
        this.entityClass = entityClass;
    }

    @Override
    public String getMessage() {
        return "Объект класса " + entityClass.getSimpleName() + " с id=" + id + " не найден";
    }
}
