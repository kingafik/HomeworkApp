package com.afik;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

/**
 * This class implements IHomework interface which defines method for working with SQL
 * and this Class is implements Singleton.
 */
public class DBController implements IHomework {
    private static DBController DBInstance;
    private static Logger logger = null;
    private static boolean firstCreation = true;
    private final SessionFactory sessionFactory;

    private DBController() {
        sessionFactory = initSessionFactory();
        logger = Logger.getLogger(ServletUtills.class.getName());
        PropertyConfigurator.configure("log4j.properties");
        BasicConfigurator.configure();
    }

    /**
     * Singleton implementation.
     *
     * @return the reference to the Singleton object.
     */
    public static DBController createHibernateDAOManager() {
        if (firstCreation) {
            firstCreation = false;
            DBInstance = new DBController();
        }
        return DBInstance;
    }

    /**
     * Add an object to the database.
     *
     * @param ob can only be one of each classes, User, Course, Homework.
     * @return true is the operation is success, false otherwise.
     * @throws ClassCastException when the input object isn't one above.
     */
    public boolean add(Object ob) throws ClassCastException, HomeworkException {
        Session session = null;

        if (ob instanceof Course)
            ob = (Course) ob;
        else if (ob instanceof Homework)
            ob = (Homework) ob;
        else if (ob instanceof User)
            ob = (User) ob;
        else
            throw new ClassCastException("Only User, Course or Homework are available");

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(ob);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            session.getTransaction().rollback();
            throw new HomeworkException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * Get the requested course from the database.
     *
     * @param courseName - String that represent the courseName.
     * @param user       - User that reference user in the specific course.
     * @return the requested course or null when method failed.
     */
    public Course getCourse(String courseName, User user) throws HomeworkException {
        Session session = null;
        Course course = null;
        List list = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Course where user = :user and courseName = :courseName");
            query.setEntity("user", user);
            query.setString("courseName", courseName);
            if ((list = query.list()) != null) {
                course = (Course) list.toArray()[0];
            }
            session.getTransaction().commit();
            return course;
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            session.getTransaction().rollback();
            throw new HomeworkException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * Get the requested homework from the database.
     *
     * @param taskName - String that represent the task/homework name.
     * @param user     - User that reference user in the specific homework.
     * @param course   - Course that reference course in the specific homework.
     * @return the requested homework or null when method failed.
     */
    public Homework getHomework(String taskName, User user, Course course) throws HomeworkException {
        Session session = null;
        Homework homework = null;
        List list = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Homework where user = :user and course = :course and taskName = :taskName ");
            query.setEntity("user", user);
            query.setEntity("course", course);
            query.setString("taskName", taskName);
            if ((list = query.list()) != null) {
                homework = (Homework) list.toArray()[0];
            }
            session.getTransaction().commit();
            return homework;
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            session.getTransaction().rollback();
            throw new HomeworkException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * Get the requested user from the database.
     *
     * @param id - String that represent the username.
     * @return the requested user or null when method failed.
     */
    public User getUser(String id) throws HomeworkException {
        Session session = null;
        User ob = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            ob = (User) session.get(User.class, id);
            session.getTransaction().commit();
            return ob;
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            session.getTransaction().rollback();
            throw new HomeworkException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * Edit the requested course/homework into the database.
     *
     * @param ob - Course/Homework that you want to edit with the new values.
     * @return true is the operation is success, false otherwise.
     * @throws ClassCastException when the input object isn't one above.
     */
    public boolean edit(Object ob) throws ClassCastException, HomeworkException {
        Session session = null;

        if (ob instanceof Course)
            ob = (Course) ob;
        else if (ob instanceof Homework)
            ob = (Homework) ob;
        else
            new ClassCastException("Only Course or Homework are available");

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(ob);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            session.getTransaction().rollback();
            throw new HomeworkException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * Get all courses the registers on the input user.
     *
     * @param user - User that all courses are wanted.
     * @return the requested iterator of courses for the specific user or null when method failed.
     */
    public Iterator<Course> getCourses(User user) throws HomeworkException {
        Session session = null;
        List<Course> list = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Course where user = :user");
            query.setEntity("user", user);
            list = query.list();
            session.getTransaction().commit();
            for (int i = 0; i < list.size(); i++) {
                session.update(list.get(i));
            }
            return list.iterator();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            session.getTransaction().rollback();
            throw new HomeworkException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * Get all homeworks the registers on the input user.
     *
     * @param user - - User that all homeworks are wanted.
     * @return the requested iterator of homeworks for the specific user or null when method failed.
     */
    public Iterator<Homework> getHomeworks(User user) throws HomeworkException {
        Session session = null;
        List<Homework> list = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Homework where user = :user");
            query.setEntity("user", user);
            list = query.list();
            session.getTransaction().commit();
            for (int i = 0; i < list.size(); i++) {
                session.update(list.get(i));
            }
            return list.iterator();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            session.getTransaction().rollback();
            throw new HomeworkException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * Delete an Object from the database.
     * @param ob can only be one of each classes Course and Homework.
     * @return true is the operation is success, false otherwise.
     */
    public boolean delete(Object ob) throws HomeworkException {
        Session session = null;

        if (ob instanceof Course)
            ob = (Course) ob;
        else if (ob instanceof Homework)
            ob = (Homework) ob;
        else
            throw new ClassCastException("Only Course or Homework are available");

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(ob);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            session.getTransaction().rollback();
            throw new HomeworkException(e.getMessage());
        } finally {
            if (session != null)
                session.close();
        }
    }

    private SessionFactory initSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }
}
