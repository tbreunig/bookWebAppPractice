package edu.wctc.tab.bookwebapppractice.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 *
 * @author Tyler
 */
public class AuthorDAO implements AuthorDAOstrategy {

    public DatabaseStrategy db;
    public String driverClass;
    public String url;
    public String userName;
    public String password;
    public String tableName = "Author";
    public List<String> cols;

    public AuthorDAO(DatabaseStrategy db, String driverClass, String url, String userName, String password) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
        cols = Arrays.asList("author_name");

    }

    @Override
    public int createNewAuthor(List vals) throws Exception {

        int manipulatedRecs = 0;
        db.openConnection(driverClass, url, userName, password);

        try {
            db.createNewRecord(tableName, cols, vals);
            manipulatedRecs++;

        } catch (Exception e) {

            manipulatedRecs--;
            throw e;
        }

        db.closeConnection();
        return manipulatedRecs;
    }

    @Override
    public List<Author> getAllAuthors() throws Exception {
        //Open the connection to the database
        db.openConnection(driverClass, url, userName, password);

        //Create a list of maps (raw data) to work with and convert to Author objects.
        List<Map<String, Object>> rawData = new ArrayList<>(db.findAllRecords("Author"));
        //Store them in a list (records) that you'll iterate over to display your
        //your results
        List<Author> records = new ArrayList<>();

        for (Map<String, Object> info : rawData) {
            Object o = info.get("author_id");
            int id = (o == null) ? 0 : Integer.valueOf(o.toString());

            o = info.get("author_name");
            String name = (o == null) ? "" : o.toString();

            o = info.get("date_added");
            Date date = (o == null) ? new Date() : (Date) o;

            Author a = new Author(id, name, date);
            records.add(a);

        }

        db.closeConnection();
        return records;
    }

    @Override
    public Author getAuthorById(int id) throws Exception {
        db.openConnection(driverClass, url, userName, password);

        //Create a list of maps (raw data) to work with and convert to Author objects.
        Map<String, Object> rawData = db.findRecordById(tableName, "author_id", id);

        Author author = new Author();
        Object objValue = rawData.get("author_id");
        Integer authorId = objValue == null ? 0 : Integer.parseInt(objValue.toString());
        author.setauthorId(authorId);

        objValue = rawData.get("author_name");
        String authorName = objValue == null ? "Empty!" : objValue.toString();
        author.setauthorName(authorName);

        objValue = rawData.get("date_added");

        Date date = objValue == null ? null : (Date) objValue;
        author.setdateAdded(date);

        db.closeConnection();

        return author;
    }

    @Override
    public int updateAuthor(int id, Author author) throws Exception {

        db.openConnection(driverClass, url, userName, password);

        String name = author.getauthorName();

        //This method is overly simplistic for this application. In future applications, you'll be updating multiple fields at a time!
        boolean result = db.updateSingleRecord(tableName, cols, Arrays.asList(name), "author_id", id);

        db.closeConnection();

        return result ? 1 : 0;
    }

    @Override
    public int deleteSingleAuthor(int id) throws Exception {
        db.openConnection(driverClass, url, userName, password);
        int recsDeleted = db.deleteSingleRecord("Author", "author_id", id);
        db.closeConnection();
        return recsDeleted;
    }

    public static void main(String[] args) throws Exception {
        AuthorDAO dao = new AuthorDAO(new MySqlDBStrategy(), "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/literature", "root", "admin");

//        -----CREATE NEW AUTHOR-----
//        List<String> val = new ArrayList();
//        val.add("Bo Jackson");
//        System.out.println(dao.createNewAuthor(val));
//        -----RETRIEVE ALL AUTHORS-----
//         System.out.println(dao.getAllAuthors());
//        -----RETRIEVE AUTHOR BY ID-----
//        System.out.println(dao.getAuthorById(5));
//        -----UPDATE AUTHOR-----
//        Author author = new Author();
//        System.out.println(dao.updateAuthor(11, author));
//        -----DELETE SINGLE AUTHOR-----
//        int recsDeleted = dao.deleteSingleAuthor("6");
//        System.out.println(recsDeleted);
//        
    }
}
