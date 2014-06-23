package com.globallogic.push_service_poc.demo.controller;

import com.globallogic.push_service_poc.demo.repository.DeviceRepository;
import com.globallogic.push_service_poc.demo.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import java.text.ParseException;

@TransactionManagement(TransactionManagementType.CONTAINER)

@RequestMapping(value = "/device")
@Controller
public class DeviceManagerController {

    @Inject
    private UserRepository userRepository;

    @Inject
    private DeviceRepository deviceRepository;

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) throws ParseException {
        model.addAttribute("user", userRepository.getUser(9999l));
        return "manage_devices";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelMap registerDevice(@RequestParam("userId") Long userId, @RequestParam("deviceId") String deviceId) {
        deviceRepository.registerDevice(userId, deviceId);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("status", "ok");
        return modelMap;
    }

    @RequestMapping(value = "/unregister", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelMap unregisterDevice(@RequestParam("userId") Long userId, @RequestParam("deviceId") String deviceId) {
        deviceRepository.unregisterDevice(userId, deviceId);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("status", "ok");
        return modelMap;
    }

}
