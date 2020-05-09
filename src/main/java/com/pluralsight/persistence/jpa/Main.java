package com.pluralsight.persistence.jpa;

import com.pluralsight.persistence.jpa.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("\n\n>>> Executing : " + Main.class.toString() + " <<<\n");

        BookService service = new BookService();

        // Creates and persists a Book
        // Returns a reference to a managed book entity
        Book book = service.createBook(4044L, "H2G2", "Scifi Book", 12.5f, "1234-5678-5678", 247);

        // This is another managed entity we create but without storing the reference to it.
        service.createBook(3333L, "H2G2", "Scifi Book", 12.5f, "1234-5678-5678", 247);


        System.out.println("Book Persisted : " + book);

        // Finds the book
        /**Important: The findBook method simply returns the in-memory state of a managed entity. It
         * doesn't return the actual record of that entity in the table of the connected to database.
         * Both things match exactly only when we do a transaction (begin() ... commit() changes).
         * Managed entities are just POJOs flagged as "managed" by JPA*/
        //book.setTitle("I changed the title but didnt commit...");
        Book bookFound = service.findBook(4044L);


        // Show that findBook() returns a reference to a managed entity
        if (bookFound == book)
            System.out.println("# ... pointers to the same object ...");

        System.out.println("-- Book Found     : " + bookFound);

        // Raises the price of the book. 4044 is the id of the above managed entity
        service.raiseUnitCost(4044L, 12.5F);
        System.out.println("Book Updated   : " + book);

        /**Similarly, method remove() remove the corresponding ecord from the database table, once commited,
         * but doesn't remove the actual obeject from memory. The object is simply no flagged anymore as
         * managed. It is detached from the entity manager.*/
        // Removes the book. More accurate: remove the book from the database and the persistence context
        service.removeBook(4044L);
        System.out.println("Book Removed");

        // Finds the book
        bookFound = service.findBook(4044L);
        System.out.println("Book Not Found in the DB: " + bookFound);

        // Show that object still exist !
        System.out.println("The book object still exists, though: " + book);

    }
}




