package edu.wctc.tab.bookwebapppractice.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author Tyler
 */
public interface DatabaseStrategy {

    public abstract void openConnection(String driverClass, String url, String userName, String password) throws Exception;
    
    public abstract void openConnection(DataSource source) throws Exception; 
    
    public abstract void createNewRecord(String tableName, String columnName, Object columnValue) throws SQLException;
    
    public abstract List<Map<String, Object>> findAllRecords(String tableName) throws SQLException;
    
    public abstract void updateSingleRecord(String tableName, Object primeKeyName, Object primeKeyValue, String columnName, Object columnValue) throws SQLException;

    public abstract void deleteSingleRecord(String tableName, Object primeKeyName, Object primeKeyValue) throws SQLException;

    public abstract void closeConnection() throws SQLException;

}
