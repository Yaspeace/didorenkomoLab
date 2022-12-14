package bank.entity.base;

import java.util.Map;

/**Базовый класс сущности с наименованием и идентификатором*/
public class BaseNameEntity extends BaseEntity {
    /**Наименование*/
    public String name;

    /**Конструктор*/
    public BaseNameEntity()
    {
        super();
        name = "";
    }

    public String toShortString() {
        return "id=" + id + "; name=" + name;
    }

    public static BaseNameEntity fromMap(Map<String, String> map) {
        BaseNameEntity res = new BaseNameEntity();
        res.name = map.get("name");
        return res;
    }
}
