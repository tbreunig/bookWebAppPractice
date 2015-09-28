package edu.wctc.tab.bookwebapppractice.model;

import java.util.List;

/**
 *
 * @author Tyler
 */
public interface AuthorDAOstrategy {

    public abstract List<Author> getAllAuthors() throws Exception;
    
}
