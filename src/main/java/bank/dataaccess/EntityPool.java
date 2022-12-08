package bank.dataaccess;

import bank.entity.base.BaseEntity;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Класс-контейнер сущностей
 * @param <Tentity> Тип сущностей
 */
public class EntityPool<Tentity extends BaseEntity> {

    /**
     * Внутренний список сущностей
     */
    private final LinkedList<Tentity> entityList;

    /**Конструктор*/
    public EntityPool()
    {
        entityList = new LinkedList<>();
    }

    /**
     * Получить список сущностей
     * @return Список сущностей
     */
    public Collection<Tentity> get() {
        return entityList;
    }

    /**
     * Получить сущность по идентификатору
     * @return Объект сущности, или null, если сущность с таким id не найдена
     */
    public Tentity get(int id) {
        for(Tentity ent : entityList) {
            if(ent.id == id) return ent;
        }
        return null;
    }

    /**
     * Добавить сущность в коллекцию
     * @param entity Добавляемая сущность
     */
    public void add(Tentity entity) throws Exception {
        if(entityList.contains(entity)) throw new Exception("Коллекция уже содержит данный элемент");
        int maxId = 0;
        for(Tentity ent : entityList) {
            if(ent.id == entity.id) throw new Exception("Ошибка уникальности ключа сущности");
            if(ent.id > maxId) maxId = ent.id;
        }
        entity.id = maxId + 1;
        entityList.add(entity);
    }

    /**
     * Изменить сущность
     * @param entity Сущность для изменения
     * @return Измененная сущность
     */
    public Tentity update(Tentity entity) throws Exception {
        Tentity savedEntity = get(entity.id);
        if(savedEntity == null) {
            throw new Exception("Не удалось найти сущность с идентификатором " + entity.id);
        }

        savedEntity = entity;

        return get(entity.id);
    }

    /**
     * Удалить сущность с указанными идентификатором
     * @param id Идентификатор
     */
    public void remove(int id)
    {
        LinkedList<Tentity> toDelete = new LinkedList<>();
        for(Tentity e : entityList) {
            if(e.id == id) toDelete.add(e);
        }
        entityList.removeAll(toDelete);
    }

    /**
     * Удалить указанную сущность
     * @param entity Сущность
     */
    public void remove(Tentity entity) {
        entityList.remove(entity);
    }
}
