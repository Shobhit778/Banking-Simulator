package com.shobhit.bankingsimulator.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    public static Connection getConnection() throws SQLException {

    Properties prop = new Properties();
    try{
        InputStream is = DbConnection.class.getClassLoader().getResourceAsStream("db.properties");
        prop.load(is);
    }
    catch(IOException e){
        e.printStackTrace();
    }
    String url = prop.getProperty("db.url");
    String username = prop.getProperty("db.username");
    String password = prop.getProperty("db.password");

    return DriverManager.getConnection(url, username, password);
    }
}
