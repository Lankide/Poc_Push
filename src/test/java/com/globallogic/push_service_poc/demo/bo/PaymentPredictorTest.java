package com.globallogic.push_service_poc.demo.bo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by VladyslavPrytula on 3/24/14.
 */
public class PaymentPredictorTest {

    private static EntityManagerFactory emf =  Persistence.createEntityManagerFactory("mongoDBUnit1");

    private EntityManager em;
    @Before
    public void setUp(){
        em = emf.createEntityManager();
    }

    @After
    public void tearDown(){
        if (em != null) em.close();
    }

    @Test
    public void testPredict(){

    }


}
