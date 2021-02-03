package ir.maktab.dao;



import javax.persistence.EntityManager;
import ir.maktab.model.IEntity;
public abstract class AbstractDao<E extends IEntity,T> {

    protected EntityManager entityManager;

    public AbstractDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(IEntity entity){
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
    public void delete(IEntity entity){
      //  entityManager.getTransaction().begin();
        entityManager.remove(entity);
       /// entityManager.getTransaction().commit();
    }
    public void update(IEntity entity){
        entityManager.merge(entity);
    }
    public E loadById(T id){
        return entityManager.find(getEntityClass(),id);
    }
    public abstract Class<E> getEntityClass();

}
