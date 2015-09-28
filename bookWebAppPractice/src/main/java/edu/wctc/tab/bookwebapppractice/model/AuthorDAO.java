package edu.wctc.tab.bookwebapppractice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 *
 * @author Tyler
 */
public class AuthorDAO implements AuthorDAOstrategy {

    //private static final String FIND_ALL_AUTHORS = "SELECT * from Author";

    public DatabaseStrategy db;
    public String driverClass;
    public String url;
    public String userName;
    public String password;

    public AuthorDAO(DatabaseStrategy db, String driverClass, String url, String userName, String password) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public List<Author> getAllAuthors() throws Exception {
        //Open the connection to the database
        db.openConnection(driverClass, url, userName, password);

        //Create a list of raw data to work with and convert to Author objects.
        List<Map<String, Object>> rawData = new ArrayList<>(db.findAllRecords("literature.author"));
        //Store them in a records list that you'll iterate over to display your
        //your results
        List<Author> records = new ArrayList<>();

        for (Map<String, Object> info : rawData) {
            Object o = info.get("author_id");
            int id = (o == null) ? 0 : Integer.valueOf(o.toString());

            o = info.get("author_name");
            String name = (o == null) ? "" : o.toString();

            o = info.get("date_added");
            Date date = (o == null) ? new Date() : (Date)o;

            Author a = new Author(id, name, date);
            records.add(a);

        }

        return records;

    }

//    public static void main(String[] args) throws Exception {
//        AuthorDAO author = new AuthorDAO(new MySqlDBStrategy(), "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/literature", "root", "admin");
//        System.out.println(author.getAllAuthors());
//
//    }

}
