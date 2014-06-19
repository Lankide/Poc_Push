package com.globallogic.push_service_poc.demo.controller;

import com.globallogic.push_service_poc.demo.bo.InvoicePredictor;
import com.globallogic.push_service_poc.demo.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    @PersistenceContext(unitName = "mongoDBUnit2")
    private EntityManager em;

    @RequestMapping(value = "/{userId}",
            method = RequestMethod.GET)

    public
    @ResponseBody
    Integer predictInvoiceOfUser(@PathVariable("userId") String userId) {
        CriteriaBuilder queryBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = queryBuilder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.select(userRoot);
        criteria.where(queryBuilder.equal(userRoot.get(User_.userId), userId));
        InvoicePredictor invoicePredictor = new InvoicePredictor();
        return invoicePredictor.predictDate(em.createQuery(criteria).getResultList().get(0));
    }
}

