package edu.wctc.tab.bookwebapppractice.model;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Tyler
 */
public class LiteratureService {

    private AuthorDAOstrategy authorStrategy;

    public LiteratureService(AuthorDAOstrategy author) {
        setAuthor(author);
    }

    public AuthorDAOstrategy getAuthor() {
        return authorStrategy;
    }

    public void setAuthor(AuthorDAOstrategy author) {
        this.authorStrategy = author;
    }

    public int createNewAuthor(List vals) throws Exception {
        return authorStrategy.createNewAuthor(vals);
    }

    public List<Author> getAllRecords() throws Exception {
        return authorStrategy.getAllAuthors();
    }

    public Author getAuthorById(int id) throws Exception {
        return authorStrategy.getAuthorById(id);
    }

    public int updateAuthor(int id, Author author) throws Exception {
        return authorStrategy.updateAuthor(id, author);
    }

    public int deleteRecord(int id) throws Exception {
        return authorStrategy.deleteSingleAuthor(id);
    }
    
    public static void main(String[] args) throws Exception {
        LiteratureService ls = new LiteratureService(new AuthorDAO(new MySqlDBStrategy(), "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/literature", "root", "admin"));

//        -----CREATE NEW AUTHOR-----
//        System.out.println(ls.createNewAuthor(Arrays.asList("Johnny Walker")));
//        -----RETRIEVE ALL AUTHORS-----
//        System.out.println(ls.getAllRecords());
//        -----RETRIEVE AUTHOR BY ID-----
//        System.out.println(ls.getAuthorById(14));
//        -----UPDATE AUTHOR-----
//        System.out.println(ls.updateAuthor(14, new Author(null, "Sherman Cooper", null)));
//        -----DELETE SINGLE AUTHOR-----
//        System.out.println(ls.deleteRecord(8));
//
//        
    }
}
