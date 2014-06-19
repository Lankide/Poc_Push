package com.globallogic.push_service_poc.demo.bo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by VladyslavPrytula on 3/25/14.
 */
public class InvoicePredictorTest {

    private static EntityManagerFactory emf =  Persistence.createEntityManagerFactory("mongoDBUnit1");

    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void initEntityManager() throws Exception {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPredictDate() throws Exception {

    }


}
