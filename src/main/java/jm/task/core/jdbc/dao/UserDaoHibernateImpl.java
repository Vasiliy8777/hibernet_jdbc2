package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
private static final String CREATE_NEW_TAB = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT,name VARCHAR(100) NOT NULL,lastName VARCHAR(100) NOT NULL, Age INT NOT NULL,PRIMARY KEY (id))";
private static final String DROP_TAB = "DROP TABLE IF EXISTS users";
private static final String CLEAR_USERS = "TRUNCATE TABLE users";
private static Session session = null;
private static Transaction transaction = null;

public UserDaoHibernateImpl() {
}

@Override
public void createUsersTable() {
    try (Session session = Util.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        Query query = session.createSQLQuery(CREATE_NEW_TAB);
        query.executeUpdate();
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}

@Override
public void dropUsersTable() {
    try (Session session = Util.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        Query query = session.createSQLQuery(DROP_TAB);
        query.executeUpdate();
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}

@Override
public void saveUser(String name, String lastName, byte age) {
    try (Session session = Util.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        String sql = "INSERT INTO users (name, lastName, Age) VALUES('" + name + "','" + lastName + "','" + age + "'" + ")";
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}

@Override
public void removeUserById(long id) {
    try (Session session = Util.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        Query query = session.createSQLQuery("DELETE FROM users where id='" + id + "'");
        query.executeUpdate();
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}

@Override
public List<User> getAllUsers() {
    List<User> list = new ArrayList<>();
    try (Session session = Util.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        list = session.createQuery("SELECT a FROM User a", User.class).getResultList();
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
    return list;
}

@Override
public void cleanUsersTable() {
    try (Session session = Util.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        Query query = session.createSQLQuery(CLEAR_USERS);
        query.executeUpdate();
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
}
