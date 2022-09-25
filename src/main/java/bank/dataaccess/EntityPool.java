package bank.dataaccess;

import bank.entity.base.BaseEntity;

public class EntityPool<Tentity extends BaseEntity> {
    private Tentity entity;
    public EntityPool()
    {
        entity = null;
    }
    public Tentity getEntity()
    {
        return entity;
    }
    public Tentity update(Tentity entity)
    {
        this.entity = entity;
        return this.entity;
    }
    public void remove()
    {
        entity = null;
    }
}
