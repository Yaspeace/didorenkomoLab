package bank.dataaccess;

import bank.entity.base.BaseEntity;

/**Класс-контейнер сущностей**/
public class EntityPool<Tentity extends BaseEntity> {
    private Tentity entity;
    public EntityPool()
    {
        entity = null;
    }
    /**Получить сущность**/
    public Tentity get()
    {
        return entity;
    }

    /**Изменить сущность**/
    public Tentity update(Tentity entity)
    {
        this.entity = entity;
        return this.entity;
    }

    /**Удалить сущность**/
    public void remove()
    {
        entity = null;
    }
}
