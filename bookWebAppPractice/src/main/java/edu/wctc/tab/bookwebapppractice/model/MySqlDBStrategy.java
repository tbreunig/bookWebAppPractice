package edu.wctc.tab.bookwebapppractice.model;

import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author Tyler
 */
public class MySqlDBStrategy implements DatabaseStrategy {

    private Connection conn;

    @Override
    public void openConnection(String driverClass, String url, String userName, String password) throws SQLException, Exception {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);

    }

    @Override
    public void openConnection(DataSource source) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createNewRecord(String tableName, List colHeaders, List colVals) throws SQLException, Exception {

        PreparedStatement pstmt = null;
        int manipulatedRecs = 0;

        try {
            pstmt = buildInsertStatement(conn, tableName, colHeaders);
            final Iterator i = colVals.iterator();
            int index = 1;
            while (i.hasNext()) {
                final Object obj = i.next();
                pstmt.setObject(index++, obj);
            }
            manipulatedRecs = pstmt.executeUpdate();

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                throw e;
            }
        }
        if (manipulatedRecs == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PreparedStatement buildInsertStatement(Connection conn, String tableName, List colNames) throws SQLException, Exception {

        PreparedStatement pstmt = null;

        //INSERT INTO Author (author_name) VALUES (?);
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(tableName).append(" (");
        for (Object colName : colNames) {
            sb.append(colName.toString()).append(", ");
        }
        int index = sb.lastIndexOf(",");
        String s = sb.substring(0, index);
        System.out.println(s);
        s = s += ") VALUES (";
        for (Object colName : colNames) {
            s += "?, ";
        }
        index = s.lastIndexOf(",");
        s = s.substring(0, index);
        s += ")";

        pstmt = conn.prepareStatement(s);
        return pstmt;
    }

    @Override
    public List<Map<String, Object>> findAllRecords(String tableName) throws SQLException, Exception {
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
    public void updateSingleRecord(String tableName, List cols, List vals, Object fieldToUpdate, Object dataToUpdate) throws SQLException, Exception {

    }

    @Override
    public PreparedStatement buildUpdateStatement(Connection conn, String tableName, List colNames, List colVals, Object fieldToUpdate, Object dataToUpdate) throws SQLException, Exception {

        PreparedStatement pstmt = null;

        //UPDATE Author SET author_name = ? WHERE author_id = #;
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(tableName).append(" SET ");
        for (Object colName : colNames) {
            sb.append(colName.toString()).append(" = ").append("?, ");
        }
        int index = sb.lastIndexOf(",");
        String s = sb.substring(0, index);
        System.out.println(s);
        s = s += " WHERE ";

        for (Object colVal : colVals) {
            sb.append(fieldToUpdate).append(" = ?");
        }

        System.out.println(s);
        pstmt = conn.prepareStatement(s);
        return pstmt;
    }

    @Override
    public void deleteSingleRecord(String tableName, Object primeKeyName, Object primeKeyValue) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() throws SQLException, Exception {
        conn.close();
    }

    public static void main(String[] args) throws Exception {
        MySqlDBStrategy db = new MySqlDBStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/literature", "root", "admin");

//        ------FIND ALL------
//        List<Map<String, Object>> records = db.findAllRecords("Author");
//        for (Map record : records) {
//            System.out.println(record);
//        }
//      
//        -----CREATE NEW RECORD-----
//        List<String> colNames = Arrays.asList("author_name");
//        List<Object> colVals = Arrays.asList("Bob Smith");
//        db.createNewRecord("Author", colNames, colVals);
//        System.out.println(db.findAllRecords("Author"));
//        db.closeConnection();
        
//        -----UPDATE RECORD-----
        List<String> colNames = Arrays.asList("author_name");
        List<Object> colVals = Arrays.asList("Tyler Anthony Breunig");
        db.updateSingleRecord("Author", colNames, colVals, "author_id", 1);
        System.out.println(db.findAllRecords("Author"));
        db.closeConnection();

//        -----DELETE RECORD-----
    }

}
