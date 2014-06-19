package com.globallogic.push_service_poc.demo.controller;

import com.globallogic.push_service_poc.demo.entity.User;
import com.globallogic.push_service_poc.demo.entity.User_;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.List;

/**
 * Created by VladyslavPrytula on 4/14/14.
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Controller
public class HomeController {
    @PersistenceContext(unitName = "mongoDBUnit2")
    private EntityManager em;


    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String printWelcome(ModelMap model) throws ParseException {


        CriteriaBuilder queryBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = queryBuilder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class );
        criteria.select(userRoot);

        criteria.where(queryBuilder.equal(userRoot.get(User_.userId),9999l));

        List<User> userQueried = em.createQuery(criteria).getResultList();

        if (!userQueried.isEmpty()){
            model.addAttribute("users",userQueried);
        }

        return "home";
    }
}
