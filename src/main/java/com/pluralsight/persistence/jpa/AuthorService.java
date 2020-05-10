package com.pluralsight.persistence.jpa;

import com.pluralsight.persistence.jpa.Author;

import javax.persistence.EntityManager;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class AuthorService {

    // ======================================
    // =             Attributes             =
    // ======================================

    private EntityManager em;

    // ======================================
    // =            Constructors            =
    // ======================================

    public AuthorService(EntityManager em) {
        this.em = em;
    }

    // ======================================
    // =           Public Methods           =
    // ======================================

    public Author createAuthor(Author author) {
        em.persist(author);
        return author;
    }

    public void removeAuthor(Author author) {
        em.remove(em.merge(author));
    }

    public Author findAuthor(Long id) {
        return em.find(Author.class, id);
    }
}