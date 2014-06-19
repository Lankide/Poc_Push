package com.globallogic.push_service_poc.demo.entity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by VladyslavPrytula on 3/20/14.
 * Integration test for persistence units
 */
public class UserTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mongoDBUnit1");

    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void initEntityManager() throws Exception {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void closeEntityManager() throws Exception {
        if (em != null) em.close();
    }

    @Test
    public void shouldUserPassword() throws Exception {
        User user = em.find(User.class, 1111L);

        Assert.assertEquals("pass01", user.getUserPassword());
    }


    @Test
    public void shouldCreateUser() throws Exception {

        // Creates an instance of user
        User user = new User(1111, "user@test.com", "pass01",
                "11111111");

        // Persists the book to the database
        tx.begin();
        em.persist(user);
        tx.commit();
        assertNotNull("ID should not be null", user.getUserId());

        // Retrieves all the instances from the database
        //TODO:implement comment
    }

    @Test
    public void shouldCreateAllEntitiesAndReadThem() throws ParseException {
        // Creates an instance of all entities

        User user = new User(2111l, "user@test.com", "pass02", "21111111");
        Invoice invoice = new Invoice(123l, "testInvoice", 1123d, new SimpleDateFormat("MM/dd/yyyy").parse("05/18/2008"), new SimpleDateFormat("MM/dd/yyyy").parse("05/18/2009"));
        Payment payment = new Payment(1111l, 234d);

        // Persists entities to the database
        tx.begin();
        em.persist(user);
        em.persist(invoice);
        em.persist(payment);
        tx.commit();

        //Retrieve invoice from the database
        Invoice testInvoice = em.find(Invoice.class, 123l);

        assertNotNull(testInvoice.getInvoiceAmount());
//        assertNotNull(testInvoice.getUser().getUserEmail());
//        assertEquals(testInvoice.getUser().getUserEmail(),em.find(User.class,2111l).getUserEmail());
    }


    @Test
    public void populateInvoices() {
        class RandomDateOfTransaction {

            public Date prepareDate(int lowerBound, int upperBound) {

                GregorianCalendar gc = new GregorianCalendar();
                int year = randBetween(lowerBound, upperBound);
                gc.set(gc.YEAR, year);
                int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
                gc.set(gc.DAY_OF_YEAR, dayOfYear);

                //TODO: needs to be logged (gc.get(gc.YEAR) + "-" + gc.get(gc.MONTH) + "-" + gc.get(gc.DAY_OF_MONTH));

                return gc.getTime();
            }

            public int randBetween(int start, int end) {
                return start + (int) Math.round(Math.random() * (end - start));
            }
        }

        User user = new User(3111l, "user@test.com", "pass02", "21111111");
        Invoice invoice = new Invoice(1234l, "testInvoice", 1000.12d,
                new RandomDateOfTransaction().prepareDate(2012, 2013),
                new RandomDateOfTransaction().prepareDate(2012, 2013));
        Payment payment = new Payment(1111l, 2345d,
                new RandomDateOfTransaction().prepareDate(2012, 2013),
                new RandomDateOfTransaction().prepareDate(2012, 2013));

        // Persists entities to the database
        tx.begin();
        em.persist(user);
        em.persist(invoice);
        em.persist(payment);
        tx.commit();


        //Retrieve invoice from the database
        Invoice testInvoice = em.find(Invoice.class, 1234l);

        assertNotNull(testInvoice.getInvoiceAmount());
//        assertNotNull(testInvoice.getUser().getUserEmail());
//        assertEquals(testInvoice.getUser().getUserEmail(),em.find(User.class,3111l).getUserEmail());

    }


}
