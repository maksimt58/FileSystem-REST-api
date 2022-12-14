package com.maxisoft.rest.repository.impHibernate;

import com.maxisoft.rest.model.File;
import com.maxisoft.rest.repository.FileRepository;
import com.maxisoft.rest.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FileHibernateRepoImpl implements FileRepository {

    @Override
    public File getById(Long id) {
        try (Session session = HibernateUtils.openSession()) {
            File file = session.get(File.class, id);
            return file;
        }
    }

    @Override
    public boolean delete(Long id) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.openSession()){
            transaction = session.beginTransaction();

            File file = session.get(File.class, id);

            session.remove(file);

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
    public File update(File file) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.openSession()){
            transaction = session.beginTransaction();

            session.merge(file);

            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed update method " + e.getMessage());
        }


        return file;
    }

    @Override
    public List<File> getAll() {
        try (Session session = HibernateUtils.openSession()) {
            List<File> files = session.createQuery("FROM File", File.class).getResultList();
            return files;
        }
    }

    @Override
    public File save(File file) {
        Transaction transaction = null;

        try(Session session = HibernateUtils.openSession()){
            transaction = session.beginTransaction();

            session.persist(file);

            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failed save method " + e.getMessage());
        }

        return file;
    }
}
