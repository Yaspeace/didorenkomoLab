package bank.dataaccess;

import bank.entity.base.BaseEntity;

/**
 * Класс-контейнер сущностей
 * @param <Tentity> Тип сущностей
 */
public class EntityPool<Tentity extends BaseEntity> {
    /**@deprecated Хранимая сущность*/
    private Tentity entity;

    /**Конструктор*/
    public EntityPool()
    {
        entity = null;
    }

    /**
     * Получить сущность
     * @return Объект сущности
     */
    public Tentity get()
    {
        return entity;
    }

    /**
     * Изменить сущность
     * @param entity Сущность для изменения
     * @return Измененная сущность
     */
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
