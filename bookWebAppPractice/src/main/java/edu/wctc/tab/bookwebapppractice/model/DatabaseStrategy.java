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

    public abstract PreparedStatement buildInsertStatement(String tableName, List colNames) throws SQLException, Exception;

    //--------RETRIEVE-------//
    public abstract List<Map<String, Object>> findAllRecords(String tableName) throws SQLException, Exception;
    
    public abstract Map<String, Object> findRecordById(String tableName, String primaryKeyField, Object keyValue) throws SQLException, Exception;

    //--------UPDATE-------//
    public abstract boolean updateSingleRecord(String tableName, List cols, List vals, String whereField, Object whereValue) throws SQLException, Exception;
    
    public abstract PreparedStatement buildUpdateStatement(String tableName, List colNames, String whereField) throws SQLException, Exception;

    //--------DELETE-------//
    public abstract int deleteSingleRecord(String tableName, String whereField, Object whereValue) throws SQLException, Exception;
    
    public abstract PreparedStatement buildDeleteStatement(String tableName, String whereField) throws SQLException, Exception;

    //--------CLOSE CONNECTION-------//
    public abstract void closeConnection() throws SQLException, Exception;

}
