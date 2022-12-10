package bank.entity.base;

/**Базовый класс сущности с идентификатором*/
public class BaseEntity {
    /**Идентификатор*/
    public int id;

    /**Конструктор*/
    public BaseEntity()
    {
        id = 0;
    }

    /**Вывод краткой информации о сущности**/
    public String toShortString() {
        return "id=" + id;
    }
}
