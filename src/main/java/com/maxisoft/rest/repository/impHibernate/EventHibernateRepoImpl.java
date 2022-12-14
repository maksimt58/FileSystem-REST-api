package com.maxisoft.rest.repository.impHibernate;

import com.maxisoft.rest.model.Event;
import com.maxisoft.rest.repository.EventRepository;
import com.maxisoft.rest.util.HibernateUtils;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EventHibernateRepoImpl implements EventRepository {
    @Override
    public Event getById(Long id) {
        try(Session session = HibernateUtils.openSession()) {

            TypedQuery<Event> eventTypedQuery = session.createQuery("SELECT e FROM Event e LEFT JOIN FETCH e.file where e.id = ?1", Event.class);

            Event event = eventTypedQuery.setParameter(1, id).getSingleResult();

            return event;
        }
    }

    @Override
    public boolean delete(Long id) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.openSession()){
            transaction = session.beginTransaction();

            Event event = session.get(Event.class, id);
            session.remove(event);

            transaction.commit();

        }catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed delete method " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public Event update(Event event) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.openSession()){
            transaction = session.beginTransaction();

            session.merge(event);

            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed update method " + e.getMessage());
        }

        return event;
    }

    @Override
    public List<Event> getAll() {
        try(Session session = HibernateUtils.openSession()) {
            List<Event> events = session.createQuery("FROM Event e LEFT JOIN FETCH e.file", Event.class).getResultList();
            return events;
        }
    }

    @Override
    public Event save(Event event) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.openSession()){
            transaction = session.beginTransaction();

            session.persist(event);

            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed save method " + e.getMessage());
        }

        return event;
    }
}
