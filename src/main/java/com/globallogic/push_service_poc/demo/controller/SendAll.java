package com.globallogic.push_service_poc.demo.controller;

import com.globallogic.push_service_poc.demo.server.AsyncSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by vladyslavprytula on 4/22/14.
 */
@Controller
@RequestMapping("sendAllnew")
public class SendAll {

    @Inject
    AsyncSender asyncSender;

    @RequestMapping(value ="/test", method = RequestMethod.GET)
    public String sendAll(ModelMap model) throws ParseException, IOException {

        model.addAttribute("sentStatus", asyncSender.sendAll());
        return "sent";
    }
}