package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
private static final String URL = "jdbc:mysql://localhost:3306/newbd123";
private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
private static final String USERNAME = "root";
private static final String PASSWORD = "rootuser";
private final Connection connect;
private static SessionFactory sessionFactory = null;

public Util() {
    try {
        Class.forName(DRIVER);
        connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
}

public Connection getConnect() {
    return connect;
}

public void close() throws Exception {
    connect.close();
}

//Hibernate
public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
        try {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, DRIVER);
            settings.put(Environment.URL, URL);
            settings.put(Environment.USER, USERNAME);
            settings.put(Environment.PASS, PASSWORD);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return sessionFactory;
}
}

