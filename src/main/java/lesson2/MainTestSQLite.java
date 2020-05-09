package lesson2;

import java.sql.*;

public class MainTestSQLite {

    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException r) {
            r.printStackTrace();
        }
    }

    public static void create(String tableName, String creationParametrs) throws SQLException, ClassNotFoundException {
            connect();
            stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + creationParametrs + ")";
            stmt.executeUpdate(sql);
    }

    public static void update(String tableName, String updParametrs) throws SQLException, ClassNotFoundException {
            connect();
            stmt = connection.createStatement();
            String sql = "UPDATE " + tableName + " " + updParametrs;
            stmt.executeUpdate(sql);
    }

    public static void delete(String tableName, String parametrs) throws SQLException, ClassNotFoundException {
            connect();
            stmt = connection.createStatement();
            String sql = "DELETE FROM " + tableName + " " + parametrs;
            stmt.executeUpdate(sql);
    }

    public static void insert(String tableName, String parametrs) throws SQLException, ClassNotFoundException {
            connect();
            stmt = connection.createStatement();
            String sql = "INSERT OR REPLACE INTO " + tableName + " VALUES ( " + parametrs + ")";
            stmt.executeUpdate(sql);
    }

    public static void select(String tableName, String parametrs) throws SQLException, ClassNotFoundException {
            connect();
            stmt = connection.createStatement();
            String sql = "SELECT " + parametrs + " FROM  " + tableName;
            ResultSet rs = stmt.executeQuery(sql);
    }

    public static void main(String[] args) {

        try {
            connect();
            create("Ae", "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, first TEXT NOT NULL");
            insert("Ae", "1, 'Text1'" );
            update("Ae", "SET first = 'hahaha' WHERE id = 1");
            insert("Ae", "2, 'Uaau,cool!'");

            ResultSet re = stmt.executeQuery("SELECT * FROM Ae");
            while(re.next()) {
                System.out.println(re.getString("first"));
            }
            delete("Ae", "WHERE id = 1");

            re = stmt.executeQuery("SELECT * FROM Ae");

            while(re.next()) {
                System.out.println(re.getString("first"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        disconnect();

    }
}
