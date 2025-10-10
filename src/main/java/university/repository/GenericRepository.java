package university.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import university.util.HibernateUtil;

import java.util.Collections;
import java.util.List;

public class GenericRepository<T> {

    private final Class<T> entityClass;

    public GenericRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T create(T entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            return entity;
        } catch (ConstraintViolationException e) {
            if (tx != null) tx.rollback();
            System.err.println("Duplicate or invalid data while creating " + entityClass.getSimpleName() + ": " + e.getMessage());
            return null;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Unexpected error during CREATE for " + entityClass.getSimpleName() + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public T read(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(entityClass, id);
        } catch (Exception e) {
            System.err.println("Error reading " + entityClass.getSimpleName() + " with ID " + id + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public T update(T entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
            return entity;
        } catch (ConstraintViolationException e) {
            if (tx != null) tx.rollback();
            System.err.println("Duplicate or invalid data while updating " + entityClass.getSimpleName() + ": " + e.getMessage());
            return null;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Unexpected error during UPDATE for " + entityClass.getSimpleName() + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void delete(T entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting " + entityClass.getSimpleName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from " + entityClass.getSimpleName(), entityClass).list();
        } catch (Exception e) {
            System.err.println("Error fetching all " + entityClass.getSimpleName() + " entities: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
