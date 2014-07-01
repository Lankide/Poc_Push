package com.globallogic.push_service_poc.demo.controller;

import com.globallogic.push_service_poc.demo.bo.InvoicePredictor;
import com.globallogic.push_service_poc.demo.entity.Invoice;
import com.globallogic.push_service_poc.demo.entity.User;
import com.globallogic.push_service_poc.demo.repository.InvoiceRepository;
import com.globallogic.push_service_poc.demo.repository.UserRepository;
import com.globallogic.push_service_poc.demo.sender.Message;
import com.globallogic.push_service_poc.demo.server.AsyncSender;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by vladyslavprytula on 4/22/14.
 */
@Controller
public class PredictAndSend {

    @Inject
    AsyncSender asyncSender;

    @Inject
    UserRepository userRepository;

    @Inject
    InvoiceRepository invoiceRepository;

    protected final Logger logger = Logger.getLogger(getClass().getName());

    @RequestMapping(value = "/predictAndSend", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelMap predictAndSendAll() throws ParseException, IOException {
        Message message = null;

        //Get user with id 9999 from MongoDB
        User user = userRepository.getUser(9999l);

        //Predict invoice completed date for userId 9999
        InvoicePredictor invoicePredictor = new InvoicePredictor();
        Date predictedDate = null;
        if (user != null) {
            predictedDate = invoicePredictor.predictDate(user);
        }

        //Get invoice with id 25 from MongoDB
        Invoice predictedInvoice = invoiceRepository.getInvoice(25l);

        //Create message
        if (predictedDate != null && predictedInvoice != null) {
            logger.info("Invoice amount: " + predictedInvoice.getInvoiceAmount());
            ObjectWriter objectWriter = new ObjectMapper().writer();
            message = new Message.Builder()
                    .addData("invoice", objectWriter.writeValueAsString(predictedInvoice))
                    .addData("predictedDate", predictedDate.toString())
                    .build();
        }

        //Send message
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("sentStatus", "Predicted date: " + new DateTime(predictedDate).toString(DateTimeFormat.forPattern("dd-MM-yyyy")) + "\t" + asyncSender.sendAll(message));
        Date a = new Date(Long.parseLong("123"));
        return modelMap;
    }
}