package com.pluralsight.persistence.module01.demo02;

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

    System.out.println("\n\n>>> Executing : " + Main.class.toString() + " <<<\n");

    persistBook(new Book(5000L, "H2G2", "Best IT Scifi Book", 12.5f, "1234-5678-5678", 247));


    Book book = findBook(5000L);

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
  private static void persistBook(Book book) {
    em.getTransaction().begin();
    em.persist(book);
    em.getTransaction().commit();
  }

  /**
   * Finds the book from the database
   */
  private static Book findBook(Long id) {
    return em.find(Book.class, id);
  }
}


