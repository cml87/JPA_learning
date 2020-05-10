package com.pluralsight.persistence.jpa;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static com.pluralsight.persistence.jpa.Language.ENGLISH;
import static com.pluralsight.persistence.jpa.Util.date;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class AuthorCheck {

    @Test
    public void run(){

        /**
         The table T_AUTHOR is an already existing table in this case. Thus I shouldn't use
         <property name="javax.persistence.schema-generation.database.action" value="drop-and-create"/>
         in the persistence.xml file
        */

        // Notice how the managing of the entity, called Author in this case, is different
        // to how we made before with the Book entity

        System.out.println("\n\n>>> Executing : " + Main.class.toString() + " <<<\n");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit-dev");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // In this case we initialize the service with a particular entity manager
        AuthorService authorService = new AuthorService(em);

        // Notice how we convert the date string to a java.time.LocalDate object through the date() method
        //
        Author author = new Author("Douglas", "Adams", "Adams is best known as the author of H2G2", date("11/03/1952"), 49, ENGLISH);

        // Creates and persists an author
        // Method createAuthor() in this case simply persists the author object.
        tx.begin();
        author = authorService.createAuthor(author);
        tx.commit();
        Long id = author.getId();

        System.out.println("Author Persisted : " + author);

        // Finds the author
        author = authorService.findAuthor(id);

        System.out.println("Author Found     : " + author);

        // Removes the author
        tx.begin();
        authorService.removeAuthor(author);
        tx.commit();

        System.out.println("Author Removed");

        // Finds the author
        author = authorService.findAuthor(id);

        System.out.println("Author Not Found : " + author);

        // Here we explicitly close the EntityManager and the EntityManagerFactory
        em.close();
        emf.close();

    }

}