package ir.maktab.dao;

import ir.maktab.model.Ticket;
import ir.maktab.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import ir.maktab.model.User;

import java.util.List;

public class UserDao extends AbstractDao<User,Integer>{
    CriteriaBuilder cb=entityManager.getCriteriaBuilder();
    public UserDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
    public User detectUser(String userName){
        CriteriaQuery<User> criteriaQuery= cb.createQuery(getEntityClass());
        Root<User> from=criteriaQuery.from(getEntityClass());
        criteriaQuery.select(from).where(cb.equal(from.get("userName"),userName));
        TypedQuery<User> typedQuery= entityManager.createQuery(criteriaQuery);
       return typedQuery.getSingleResult();
    }

}
