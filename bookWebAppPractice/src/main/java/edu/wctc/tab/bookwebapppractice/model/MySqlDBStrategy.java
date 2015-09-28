package edu.wctc.tab.bookwebapppractice.model;

import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tyler
 */
public class MySqlDBStrategy implements DatabaseStrategy {

    private Connection conn;

    @Override
    public void openConnection(String driverClass, String url, String userName, String password) throws Exception {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);

    }

    @Override
    public void createNewRecord(String tableName, Object primeKeyName, Object primeKeyValue) throws SQLException {
        String sql = "INSERT INTO literature.author .....";
        Statement stmt = null;

    }

    @Override
    public List<Map<String, Object>> findAllRecords(String tableName) throws SQLException {
        String sql = "SELECT * FROM " + tableName;

        //You need to create a LIST for data to be stored in and worked with below
        List<Map<String, Object>> recordList = new ArrayList<>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        //This metaDate call has the ability to figure out column names that you don't know to begin with.
        ResultSetMetaData metaData = rs.getMetaData();
        //Figure out how many columns there are in the Database.
        int columnCount = metaData.getColumnCount();

        while (rs.next()) {
            Map<String, Object> record = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                record.put(metaData.getColumnName(i), rs.getObject(i));
            }
            recordList.add(record);
        }

        return recordList;
    }

    @Override
    public void updateSingleRecord(String tableName, Object primeKeyName, Object primeKeyValue) throws SQLException {
        String sql = "UPDATE literature.author SET.....";
        Statement stmt = null;

    }

    @Override
    public void deleteSingleRecord(String tableName, Object primeKeyName, Object primeKeyValue) throws SQLException {
        String sql;
        Statement stmt = null;

        if (primeKeyValue instanceof String) {
            sql = "DELETE FROM " + tableName + " WHERE " + primeKeyName + " = '" + primeKeyValue + "'";
            stmt = conn.prepareStatement(sql);

        } else {
            sql = "DELETE FROM " + tableName + " WHERE " + primeKeyName + " = " + primeKeyValue;
            stmt = conn.prepareStatement(sql);
        }

        stmt.executeUpdate(sql);
    }

    @Override
    public void closeConnection() throws SQLException {
        conn.close();
    }

    public static void main(String[] args) throws Exception {
        MySqlDBStrategy db = new MySqlDBStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/literature", "root", "admin");
//        List<Map<String, Object>> records = db.findAllRecords("author");
//        for (Map record : records) {
//            System.out.println(record);
//        }

        db.deleteSingleRecord("author", "author_id", 3);

        System.out.println(db.findAllRecords("author"));
    }

}
