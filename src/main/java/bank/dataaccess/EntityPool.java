package bank.dataaccess;

import bank.entity.base.BaseEntity;
import exceptions.CrudOperationException;
import exceptions.CrudOperations;

import java.lang.reflect.ParameterizedType;
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
    public void add(Tentity entity) throws CrudOperationException {
        if(entityList.contains(entity))
            throw new CrudOperationException(
                    "Коллекция уже содержит данный элемент", entity.getClass(), CrudOperations.Add);
        int maxId = 0;
        for(Tentity ent : entityList) {
            if(ent.id == entity.id)
                throw new CrudOperationException(
                        "Ошибка уникальности ключа сущности", entity.getClass(), CrudOperations.Add);
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
    public Tentity update(Tentity entity) throws CrudOperationException {
        Tentity savedEntity = get(entity.id);
        if(savedEntity == null) {
            throw new CrudOperationException(
                "Не удалось найти сущность с идентификатором " + entity.id, entity.getClass(), CrudOperations.Update);
        }

        entityList.remove(savedEntity);
        entityList.add(entity);

        return get(entity.id);
    }

    /**
     * Удалить сущность с указанными идентификатором
     * @param id Идентификатор
     */
    public void remove(int id) throws CrudOperationException {
        if(entityList.size() < 1)
            throw new CrudOperationException("Коллекция пуста", "", CrudOperations.Delete);

        for(Tentity e : entityList) {
            if(e.id == id) {
                entityList.remove(e);
                return;
            }
        }

        throw new CrudOperationException("Отсутствует объект с id=" + id, "", CrudOperations.Delete);
    }

    /**
     * Удалить указанную сущность
     * @param entity Сущность
     */
    public void remove(Tentity entity) throws CrudOperationException {
        try {
            entityList.remove(entity);
        } catch (Exception ex) {
            throw new CrudOperationException("Ошибка удаления объекта: " + ex.getMessage(), "", CrudOperations.Delete);
        }

    }
}
