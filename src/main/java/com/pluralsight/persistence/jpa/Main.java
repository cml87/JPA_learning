package com.pluralsight.persistence.jpa;

import com.pluralsight.persistence.jpa.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;

import static com.pluralsight.persistence.jpa.Language.ENGLISH;
import static com.pluralsight.persistence.jpa.Util.date;


public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        /** See 'Check' classes where I check that entities Book and Author are managed as desired*/

        /** Check classes are intended to execute whole blocks of code independently. Otherwise I
         * would have to stuff all blocks in a common main() */

    }
}




