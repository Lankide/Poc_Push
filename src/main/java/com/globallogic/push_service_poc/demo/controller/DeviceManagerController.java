package com.globallogic.push_service_poc.demo.controller;

import com.globallogic.push_service_poc.demo.entity.User;
import com.globallogic.push_service_poc.demo.entity.User_;
import com.globallogic.push_service_poc.demo.repository.UserRepository;
import com.globallogic.push_service_poc.demo.server.Datastore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.List;

@TransactionManagement(TransactionManagementType.CONTAINER)
@Controller
public class DeviceManagerController {

    @Inject
    private UserRepository userRepository;

    @RequestMapping(value = "/manageDevices", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) throws ParseException {
        model.addAttribute("user", userRepository.getUser(9999l));
        return "manage_devices";
    }
}
