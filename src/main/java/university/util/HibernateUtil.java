package university.util;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import university.entity.*;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        try{
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
//                    .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
//                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/studentregistrationsystem")
//                    .setProperty("hibernate.connection.username", "root")
//                    .setProperty("hibernate.connection.password", "root")
//                    .setProperty("hibernate.show_sql", "true")
//                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Department.class)
                    .addAnnotatedClass(Enrollment.class)
                    .addAnnotatedClass(Professor.class)
                    .addAnnotatedClass(Semester.class)
                    .addAnnotatedClass(Course.class)

                    .buildSessionFactory();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void shutdown() {
        sessionFactory.close();
    }

}