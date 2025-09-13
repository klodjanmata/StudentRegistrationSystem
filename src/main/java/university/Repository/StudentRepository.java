package university.Repository;

import university.Entity.Student;
import university.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentRepository {

    public Student create(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public Student read(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Student.class, id);
        }

    }

    public Student update(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public Student delete(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(student);
            transaction.commit();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public List<Student> findAll() {
        Transaction transaction = null;
        List<Student> students = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            students = session.createQuery("from Student", Student.class).list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return students;
    }
}
