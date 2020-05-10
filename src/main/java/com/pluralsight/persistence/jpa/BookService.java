package com.pluralsight.persistence.jpa;

import com.pluralsight.persistence.jpa.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class BookService {

    // ======================================
    // =             Attributes             =
    // ======================================

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit-prod");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    // ======================================
    // =           Public Methods           =
    // ======================================

    public Book createBook(Long id, String title, String description, Float unitCost, String isbn, Integer nbOfPage) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setDescription(description);
        book.setUnitCost(unitCost);
        book.setIsbn(isbn);
        book.setNbOfPage(nbOfPage);
        tx.begin();
        em.persist(book);
        tx.commit();
        return book;
    }

    public Book raiseUnitCost(Long id, Float raise) {
        Book book = em.find(Book.class, id); //get reference to a managed book object with that id
        if (book != null) {
            tx.begin();
            book.setUnitCost(book.getUnitCost() + raise);
            tx.commit();
        }
        return book;
    }

    public void removeBook(Long id) {
        Book book = em.find(Book.class, id); //get reference to a managed book object with that id
        if (book != null) {
            tx.begin();
            em.remove(book);
            tx.commit();
        }
    }

    // returns the in-memory state of a managed entity having the supplied id. Doesn't return the actual database
    // record of the entity with such id.
    public Book findBook(Long id) {
        return em.find(Book.class, id);
    }
}