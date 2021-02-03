package ir.maktab.dao;

import ir.maktab.model.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class TicketDao extends AbstractDao<Ticket, Integer> {
    public TicketDao(EntityManager entityManager) {
        super(entityManager);
    }
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    @Override
    public Class<Ticket> getEntityClass() {
        return Ticket.class;
    }


    public List<Ticket> loadAllTicket() {


        CriteriaQuery<Ticket> criteriaQuery = cb.createQuery(Ticket.class);
        Root<Ticket> from = criteriaQuery.from(Ticket.class);
        criteriaQuery.select(from);
        TypedQuery<Ticket> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();

    }

    public List<Ticket> searchTicket(String origin) {

        CriteriaQuery<Ticket> criteriaQuery = cb.createQuery(Ticket.class);
        Root<Ticket> from = criteriaQuery.from(Ticket.class);
        criteriaQuery.select(from)
                .where(cb.equal(from.get("origin"),origin));
        TypedQuery<Ticket> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();

    }
    public List<Ticket> searchTicket(String origin,String destination) {

        CriteriaQuery<Ticket> criteriaQuery = cb.createQuery(Ticket.class);
        Root<Ticket> from = criteriaQuery.from(Ticket.class);
        Predicate predicate= cb.and(
                cb.equal(from.get("origin"),origin),
                cb.equal(from.get("destination"),destination)

        );
        criteriaQuery.select(from)
                .where(predicate);
        TypedQuery<Ticket> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();

    }
    public List<Ticket> searchTicket(String origin,String destination,String dateOfDeparture) {

        CriteriaQuery<Ticket> criteriaQuery = cb.createQuery(Ticket.class);
        Root<Ticket> from = criteriaQuery.from(Ticket.class);
        Predicate predicate= cb.and(
                cb.equal(from.get("origin"),origin),
                cb.equal(from.get("destination"),destination),
                cb.equal(from.get("dateOfDeparture"),dateOfDeparture)

        );
        criteriaQuery.select(from)
                .where(predicate);
        TypedQuery<Ticket> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();

    }

    public List<Ticket> searchTicketType2(String dateOfDeparture) {

        CriteriaQuery<Ticket> criteriaQuery = cb.createQuery(Ticket.class);
        Root<Ticket> from = criteriaQuery.from(Ticket.class);
        criteriaQuery.select(from)
                .where(cb.equal(from.get("dateOfDeparture"),dateOfDeparture));
        TypedQuery<Ticket> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();

    }
    public List<Ticket> searchTicketType2(String destination,String dateOfDeparture) {

        CriteriaQuery<Ticket> criteriaQuery = cb.createQuery(Ticket.class);
        Root<Ticket> from = criteriaQuery.from(Ticket.class);
        Predicate predicate= cb.and(
                cb.equal(from.get("destination"),destination),
                cb.equal(from.get("dateOfDeparture"),dateOfDeparture)

        );
        criteriaQuery.select(from)
                .where(predicate);
        TypedQuery<Ticket> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();

    }

    public List<Ticket> searchTicketType3(String destination) {

        CriteriaQuery<Ticket> criteriaQuery = cb.createQuery(Ticket.class);
        Root<Ticket> from = criteriaQuery.from(Ticket.class);
        criteriaQuery.select(from)
                .where(cb.equal(from.get("destination"),destination));
        TypedQuery<Ticket> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();

    }
    public List<Ticket> searchTicketType3(String origin,String dateOfDeparture) {

        CriteriaQuery<Ticket> criteriaQuery = cb.createQuery(Ticket.class);
        Root<Ticket> from = criteriaQuery.from(Ticket.class);
        Predicate predicate= cb.and(
                cb.equal(from.get("origin"),origin),
                cb.equal(from.get("dateOfDeparture"),dateOfDeparture)

        );
        criteriaQuery.select(from)
                .where(predicate);
        TypedQuery<Ticket> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();

    }
}
