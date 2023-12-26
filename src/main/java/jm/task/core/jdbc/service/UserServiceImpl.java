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
public static UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

public void createUsersTable() {
    userDaoHibernate.createUsersTable();
}

public void dropUsersTable() {
    userDaoHibernate.dropUsersTable();
}

public void saveUser(String name, String lastName, byte age) {
    userDaoHibernate.saveUser(name, lastName, age);
    System.out.println(String.format("User с именем - %s добавлен в базу данных", name));
}

public void removeUserById(long id) {
    userDaoHibernate.removeUserById(id);
}

public List<User> getAllUsers() {
    return userDaoHibernate.getAllUsers();
}

public void cleanUsersTable() {
    userDaoHibernate.cleanUsersTable();
}
}
