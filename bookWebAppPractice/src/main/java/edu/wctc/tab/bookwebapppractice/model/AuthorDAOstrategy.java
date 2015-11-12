package edu.wctc.tab.bookwebapppractice.model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Tyler
 */
public interface AuthorDAOstrategy {
    
    //CREATE
    public abstract int createNewAuthor(List vals) throws Exception;

    //RETRIEVE
    public abstract List<Author> getAllAuthors() throws Exception;
    
    public abstract Author getAuthorById(int id) throws Exception;
    
    //UPDATE
    public abstract int updateAuthor(int id, Author author) throws Exception;
    
    //DELETE
    public abstract int deleteSingleAuthor(int id) throws Exception;
    
}
