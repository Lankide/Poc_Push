package com.globallogic.push_service_poc.demo.controller;

import com.globallogic.push_service_poc.demo.repository.UserRepository;
import com.globallogic.push_service_poc.demo.repository.mongo.UserRepositoryJPA;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import java.text.ParseException;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Controller
public class HomeController {

    @Inject
    private UserRepository userRepository;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) throws ParseException {
        model.addAttribute("user", userRepository.getUser(9999l));
        return "home";
    }
}
