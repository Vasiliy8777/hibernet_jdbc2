package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
private static final String CREATE_NEW_TAB = "CREATE TABLE users (id INT NOT NULL AUTO_INCREMENT,name VARCHAR(100) NOT NULL,lastName VARCHAR(100) NOT NULL, Age INT NOT NULL,PRIMARY KEY (id))";
private static final String DROP_TAB = "DROP TABLE users";
private static final String CLEAR_USERS = "TRUNCATE TABLE users";
private static final Session session = Util.getSessionFactory().openSession();
public UserDaoHibernateImpl() {
}
@Override
public void createUsersTable() {
    Transaction transaction = session.beginTransaction();
    if (!session.createSQLQuery("SHOW TABLES").getResultList().isEmpty()) {
        return;
    }
    Query query = session.createSQLQuery(CREATE_NEW_TAB);
    query.executeUpdate();
    transaction.commit();
    session.close();
}

@Override
public void dropUsersTable() {
    Transaction transaction = session.beginTransaction();
    if (session.createSQLQuery("SHOW TABLES").getResultList().isEmpty()) {
        return;
    }
    Query query = session.createSQLQuery(DROP_TAB);
    query.executeUpdate();
    transaction.commit();
    session.close();
}

@Override
public void saveUser(String name, String lastName, byte age) {
    Transaction transaction = session.beginTransaction();
    String sql = "INSERT INTO users (name, lastName, Age) VALUES('" + name + "','" + lastName + "','" + age + "'" + ")";
    Query query = session.createSQLQuery(sql);
    query.executeUpdate();
    transaction.commit();
    session.close();
}

@Override
public void removeUserById(long id) {
    Transaction transaction = session.beginTransaction();
    Query query = session.createSQLQuery("DELETE FROM users where id='" + id + "'");
    query.executeUpdate();
    transaction.commit();
    session.close();
}

@Override
public List<User> getAllUsers() {
    Transaction transaction = session.beginTransaction();
    List<User> list = session.createQuery("SELECT a FROM User a", User.class).getResultList();

    transaction.commit();
    session.close();
    return list;
}

@Override
public void cleanUsersTable() {
    Transaction transaction = session.beginTransaction();
    Query query = session.createSQLQuery(CLEAR_USERS);
    query.executeUpdate();
    transaction.commit();
    session.close();
}
}
