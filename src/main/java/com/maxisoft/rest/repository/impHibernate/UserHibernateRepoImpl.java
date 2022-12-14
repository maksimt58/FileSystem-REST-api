package com.maxisoft.rest.repository.impHibernate;

import com.maxisoft.rest.model.User;
import com.maxisoft.rest.repository.UserRepository;
import com.maxisoft.rest.util.HibernateUtils;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserHibernateRepoImpl implements UserRepository {
    @Override
    public User getById(Long id) {
        try(Session session = HibernateUtils.openSession()){
            TypedQuery<User> userTypedQuery = session.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.events where u.id = ?1", User.class);

            User user = userTypedQuery.setParameter(1, id).getSingleResult();

            return user;
        }
    }

    @Override
    public boolean delete(Long id) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.openSession()){
            transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            session.remove(user);

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
    public User update(User user) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.openSession()){
            transaction = session.beginTransaction();

            session.merge(user);

            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed update method " + e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        try(Session session = HibernateUtils.openSession()){
            List<User> users =session.createQuery("FROM User u LEFT JOIN FETCH u.events", User.class).getResultList();
            return users;
        }
    }

    @Override
    public User save(User user) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.openSession()){
            transaction = session.beginTransaction();

            session.persist(user);

            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed save method " + e.getMessage());
        }

        return user;
    }
}
