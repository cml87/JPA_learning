package com.pluralsight.persistence.module01.demo01;

import com.pluralsight.persistence.module01.demo02.Book;

import java.sql.*;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Main {

  public static void main(String[] args) throws SQLException, ClassNotFoundException {

    System.out.println("\n\n>>> Executing : " + Main.class.toString() + " <<<\n");

    persistBook(new Book(5000L, "H2G2", "Best IT Scifi Book", 12.5f, "1234-5678-5678", 247));

    Book book = findBook(5000L);

    System.out.println("# " + book);
  }

  /**
   * Gets a database connection
   */
  static {
    try {
      Class.forName("org.h2.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:h2:~/cdbookstoreDB");
  }

  /**
   * Persists the book to the database
   */
  private static void persistBook(Book book) throws SQLException {

    String query = "INSERT INTO BOOK (ID, TITLE, DESCRIPTION, UNITCOST, ISBN, NBOFPAGE) VALUES (?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = getConnection().prepareStatement(query)) {

      stmt.setLong(1, book.getId());
      stmt.setString(2, book.getTitle());
      stmt.setString(3, book.getDescription());
      stmt.setFloat(4, book.getUnitCost());
      stmt.setString(5, book.getIsbn());
      stmt.setInt(6, book.getNbOfPage());

      stmt.executeUpdate();
    }
  }

  /**
   * Finds the book from the database
   */
  private static Book findBook(Long id) throws SQLException {

    Book book = new Book(id);
    String query = "SELECT ID, TITLE, DESCRIPTION, UNITCOST, ISBN, NBOFPAGE FROM BOOK WHERE ID = ?";

    try (PreparedStatement stmt = getConnection().prepareStatement(query)) {

      stmt.setLong(1, id);

      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        book.setTitle(rs.getString("TITLE"));
        book.setDescription(rs.getString("DESCRIPTION"));
        book.setUnitCost(rs.getFloat("UNITCOST"));
        book.setIsbn(rs.getString("ISBN"));
        book.setNbOfPage(rs.getInt("NBOFPAGE"));
      }
    }
    return book;
  }
}


