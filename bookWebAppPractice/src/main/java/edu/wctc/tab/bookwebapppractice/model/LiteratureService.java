package edu.wctc.tab.bookwebapppractice.model;

import java.util.List;

/**
 *
 * @author Tyler
 */
public class LiteratureService {
    
    private AuthorDAOstrategy author;

    public LiteratureService(AuthorDAOstrategy author) {
        setAuthor(author);
    }

    public AuthorDAOstrategy getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDAOstrategy author) {
        this.author = author;
    }
    
    public List<Author> getAllRecords() throws Exception{
        return author.getAllAuthors();
    }
    
    
    
//    public static void main(String[] args) throws Exception {
//        LiteratureService ls = new LiteratureService(new AuthorDAO(new MySqlDBStrategy(), "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/literature", "root", "admin"));
//        System.out.println(ls.getAllRecords());
//    }
         
}
