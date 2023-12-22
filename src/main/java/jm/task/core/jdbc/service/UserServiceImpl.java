package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.DatabaseMetaData;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

public void createUsersTable() {
//    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//    userDaoJDBC.createUsersTable();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
    userDaoHibernate.createUsersTable();
    // System.out.println(String.format("Таблица создана"));
}

public void dropUsersTable() {
//    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//    userDaoJDBC.dropUsersTable();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
    userDaoHibernate.dropUsersTable();
    // System.out.println(String.format("Таблица удалена"));
}

public void saveUser(String name, String lastName, byte age) {
//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.saveUser(name, lastName, age);
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
    userDaoHibernate.saveUser(name, lastName, age);
    System.out.println(String.format("User с именем - %s добавлен в базу данных", name));
}

public void removeUserById(long id) {
//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.removeUserById(id);
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
    userDaoHibernate.removeUserById(id);
}

public List<User> getAllUsers() {
//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        return userDaoJDBC.getAllUsers();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
    return userDaoHibernate.getAllUsers();
}

public void cleanUsersTable() {
//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.cleanUsersTable();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
    userDaoHibernate.cleanUsersTable();
}
}
