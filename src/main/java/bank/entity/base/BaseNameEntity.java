package bank.entity.base;

/**Базовый класс сущности с наименованием и идентификатором*/
public class BaseNameEntity extends BaseEntity{
    /**Наименование*/
    public String name;

    /**Конструктор*/
    public BaseNameEntity()
    {
        super();
        name = "";
    }
}
