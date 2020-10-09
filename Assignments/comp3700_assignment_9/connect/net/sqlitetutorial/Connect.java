package connect.net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * I would just like to say that the website tutorial
 * for this assignment was very misleading at first and
 * I was really confused for a couple of hours! Fun times...
 * Fun times.
 */

 public class Connect {
    public static void connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:C:/Users/matth/Documents/Programming/Github/Comp3700/Assignments/comp3700_assignment_9/db/store.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        connect();
    }
 }