package com.pluralsight.persistence.jpa;

import com.pluralsight.persistence.jpa.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("\n\n>>> Executing : " + com.pluralsight.persistence.jpa.Main.class.toString() + " <<<\n");

        persistBook(new com.pluralsight.persistence.jpa.Book(5000L, "H2G2", "Best IT Scifi Book", 12.5f, "1234-5678-5678", 247));


        com.pluralsight.persistence.jpa.Book book = findBook(5000L);

        System.out.println("# " + book);

        em.close();

    }

    /**
     * Gets an entity manager
     */
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("module01-persistence-unit");
    private static EntityManager em = emf.createEntityManager();

    /**
     * Persists the book to the database
     */
    private static void persistBook(com.pluralsight.persistence.jpa.Book book) {
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
    }

    /**
     * Finds the book from the database
     */
    private static com.pluralsight.persistence.jpa.Book findBook(Long id) {
        return em.find(Book.class, id);
    }
}


