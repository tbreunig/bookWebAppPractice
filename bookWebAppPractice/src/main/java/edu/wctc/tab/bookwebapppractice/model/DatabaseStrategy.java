package edu.wctc.tab.bookwebapppractice.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author Tyler
 */
public interface DatabaseStrategy {

    //--------OPEN CONNECTION: SINGULAR & CONNECTION POOL-------//
    public abstract void openConnection(String driverClass, String url, String userName, String password) throws SQLException, Exception;

    public abstract void openConnection(DataSource source) throws SQLException, Exception;

    //--------CREATE-------//
    public abstract boolean createNewRecord(String tableName, List cols, List vals) throws SQLException, Exception;

    public abstract PreparedStatement buildInsertStatement(Connection conn, String tableName, List colNames) throws SQLException, Exception;

    //--------RETRIEVE-------//
    public abstract List<Map<String, Object>> findAllRecords(String tableName) throws SQLException, Exception;

    //--------UPDATE-------//
    public abstract void updateSingleRecord(String tableName, List cols, List vals, Object fieldToUpdate, Object dataToUpdate) throws SQLException, Exception;
    
    public abstract PreparedStatement buildUpdateStatement(Connection conn, String tableName, List colNames, List colVals, Object fieldToUpdate, Object dataToUpdate) throws SQLException, Exception;

    //--------DELETE-------//
    public abstract void deleteSingleRecord(String tableName, Object primeKeyName, Object primeKeyValue) throws SQLException, Exception;

    //--------CLOSE CONNECTION-------//
    public abstract void closeConnection() throws SQLException, Exception;

}
