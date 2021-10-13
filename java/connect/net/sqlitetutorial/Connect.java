package net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author sqlitetutorial.net
 */
public class Connect {
     /**
     * Connect to a sample database
     */
    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/sqlite/db/chinook.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");

            // Statement statement0 = conn.createStatement();
            // statement0.execute("DROP TABLE IF EXISTS Movies;");
            // statement0.close();


            Statement statement1 = conn.createStatement();
            statement1.execute(
                    "CREATE TABLE IF NOT EXISTS Movies(movie_name TEXT, actor TEXT, actress TEXT, director TEXT, yearOfRelease INTEGER)");
            statement1.close();


            System.out.println("Movie table has been created");


            System.out.println("Before insertion : ");

            Statement statement2 = conn.createStatement();
            statement2.execute("SELECT * FROM Movies");
            ResultSet results = statement2.getResultSet();
            while (results.next()) {
                String movieName = results.getString("movie_name");
                String actor = results.getString("actor");
                String actress = results.getString("actress");
                String director = results.getString("director");
                String yearOfRelease = results.getString("yearOfRelease");
                System.out.println(
                        movieName + "-> " + actor + "-> " + actress + "-> " + director + "-> " + yearOfRelease);
            }
            results.close();
            statement2.close();




            // #####################################
            Statement statement3 = conn.createStatement();
            statement3.execute(
                    "INSERT INTO Movies (movie_name, actor, actress, director, yearOfRelease) VALUES ('Barfi', 'Ranbir Kapoor', 'Priyanka Chopra', 'Anurag Basu', 2012)");
            statement3.execute(
                    "INSERT INTO Movies (movie_name, actor, actress, director, yearOfRelease) VALUES ('PK', 'Aamir Khan', 'Anushka Sharma', 'Rajkumar Hirani', 2014)");
            statement3.close();
            //conn.close();



            System.out.println("After insertion : ");

            Statement statement4 = conn.createStatement();
            statement4.execute("SELECT * FROM Movies");

            ResultSet results2 = statement4.getResultSet();
            while (results2.next()) {
                String movieName = results2.getString("movie_name");
                String actor = results2.getString("actor");
                String actress = results2.getString("actress");
                String director = results2.getString("director");
                String yearOfRelease = results2.getString("yearOfRelease");
                System.out.println(
                        movieName + "-> " + actor + "-> " + actress + "-> " + director + "-> " + yearOfRelease);
            }
            results2.close();
            statement4.close();
            
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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        connect();
    }
}