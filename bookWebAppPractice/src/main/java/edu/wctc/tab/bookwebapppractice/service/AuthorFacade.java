/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.tab.bookwebapppractice.service;

import edu.wctc.tab.bookwebapppractice.entity.Author;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tyler
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> {
    @PersistenceContext(unitName = "edu.wctc.tab_bookwebapppractice_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    
}
