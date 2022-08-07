package com.example.schoolapi.repository;

import java.beans.Statement;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {
    public Connection con ;
    public static Properties loadPropertiesFile() throws Exception {

        Properties prop = new Properties();
        InputStream in = new FileInputStream("application.properties");
        prop.load(in);
        in.close();
        return prop;
    }

    public static String Connex() {

        System.out.println("create jdbc connection using properties file");

        Connection con = null;

        try {

            Properties prop = loadPropertiesFile();

            String driverClass = prop.getProperty("spring.datasource.driver-class-name");
            String url = prop.getProperty("spring.datasource.url");
            String username = prop.getProperty("spring.datasource.username");
            String password = prop.getProperty("spring.datasource.password");

            Class.forName(driverClass);

            con = DriverManager.getConnection(url, username, password);


            if (con != null) {
                System.out.println("connection created successfully using properties file") ;
            }
            else {
                System.out.println(" unable to create connection");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
