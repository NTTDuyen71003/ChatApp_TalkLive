/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author DD
 */
public class Database_connection {
    private static Database_connection instance;
    private Connection connection;

    public static Database_connection getInstance() {
        if (instance == null) {
            instance = new Database_connection();
        }
        return instance;
    }

    private Database_connection() {

    }

    public void connectToDatabase() throws SQLException {
        //kết nối máy chủ
        String server = "192.168.31.140";
//        String server="localhost";
        String port = "3306";
        String database = "chattingtl";
        String userName = "duyen";
//        String userName = "root";
        String password = "doicau1999(";
        connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, userName, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
