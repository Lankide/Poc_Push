package com.globallogic.push_service_poc.demo.server;

import com.globallogic.push_service_poc.demo.entity.Device;
import com.globallogic.push_service_poc.demo.entity.User;
import com.globallogic.push_service_poc.demo.entity.User_;
import com.globallogic.push_service_poc.demo.sender.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vladyslavprytula on 4/22/14.
 */
//TODO: must be split in controller and service

@Service
public class AsyncSender {

    private static final int MULTICAST_SIZE = 1000;

    @PersistenceContext(unitName = "mongoDBUnit2")
    private EntityManager em;

    private Sender sender;
    static final boolean DEBUG = true;

    protected final Logger logger = Logger.getLogger(getClass().getName());

    private static final Executor threadPool = Executors.newFixedThreadPool(5);

    @Inject
    ServletContext servletContext;

    @PostConstruct
    public void createSender() {
        sender = new Sender((String) servletContext.getAttribute(ApiKeyInitializer.ATTRIBUTE_ACCESS_KEY));
    }


    public String sendAll() throws ParseException, IOException {
//        List<String> devices = Datastore.getDevices();

        // Get all devices of user with id 9999
        CriteriaBuilder queryBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = queryBuilder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.select(userRoot);

        criteria.where(queryBuilder.equal(userRoot.get(User_.userId), 9999l));

        List<User> userQueried = em.createQuery(criteria).getResultList();

        List<Device> devices = new LinkedList<>();

        for (User user : userQueried) {
            devices.addAll(user.getDeviceList());
        }

        String status;
        if (devices.isEmpty()) {
            status = "Message ignored as there is no device registered!";
        } else {
            // NOTE: check below is for demonstration purposes; a real application
            // could always send a multicast, even for just one recipient
            if (devices.size() == 1) {
                // send a single message using plain post
                String registrationId = devices.get(0).getDeviceId();
                Message message = new Message.Builder().build();
                Result result = sender.send(message, registrationId, 5);
                status = "Sent message to one device: " + result;
            } else {
                // send a multicast message using JSON
                // must split in chunks of 1000 devices (GCM limit)
                int total = devices.size();
                List<Device> partialDevices = new ArrayList<Device>(total);
                int counter = 0;
                int tasks = 0;
                for (Device device : devices) {
                    counter++;
                    partialDevices.add(device);
                    int partialSize = partialDevices.size();
                    if (partialSize == MULTICAST_SIZE || counter == total) {
                        asyncSend(partialDevices);
                        partialDevices.clear();
                        tasks++;
                    }
                }
                status = "Asynchronously sending " + tasks + " multicast messages to " +
                        total + " devices";
            }
        }

        return status.toString();
    }

    private void asyncSend(List<Device> partialDevices) {
        // make a copy
        final List<String> deviceIds = new LinkedList<>();
        for (Device device : partialDevices)
            deviceIds.add(device.getDeviceId());
        threadPool.execute(new Runnable() {

            public void run() {
//        Message message = new Message.Builder().build();
                User user = em.find(User.class, 9999L);
                Message message = new Message.Builder().addData(user.getUserEmail(), user.getUserPassword()).build();

                MulticastResult multicastResult;
                try {
                    multicastResult = sender.send(message, deviceIds, 5);
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Error posting messages", e);
                    return;
                }
                List<Result> results = multicastResult.getResults();
                // analyze the results
                for (int i = 0; i < deviceIds.size(); i++) {
                    String regId = deviceIds.get(i);
                    Result result = results.get(i);
                    String messageId = result.getMessageId();
                    if (messageId != null) {
                        logger.fine("Successfully sent message to device: " + regId +
                                "; messageId = " + messageId);
                        String canonicalRegId = result.getCanonicalRegistrationId();
                        if (canonicalRegId != null) {
                            // same device has more than on registration id: update it
                            logger.info("canonicalRegId " + canonicalRegId);
                            Datastore.updateRegistration(regId, canonicalRegId);
                        }
                    } else {
                        String error = result.getErrorCodeName();
                        if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
                            // application has been removed from device - unregister it
                            logger.info("Unregistered device: " + regId);
                            Datastore.unregister(regId);
                        } else {
                            logger.severe("Error sending message to " + regId + ": " + error);
                        }
                    }
                }
            }
        });
    }
}