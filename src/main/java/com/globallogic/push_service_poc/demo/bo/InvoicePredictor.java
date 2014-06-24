package com.globallogic.push_service_poc.demo.bo;


import com.globallogic.push_service_poc.demo.entity.Invoice;
import com.globallogic.push_service_poc.demo.entity.User;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.SupervisedTrainingElement;
import org.neuroph.core.learning.TrainingElement;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.LMS;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by VladyslavPrytula on 3/25/14.
 */

@Service
public class InvoicePredictor {

    private static final int MAX_ITERATIONS = 10000;

    protected final Logger logger = Logger.getLogger(getClass().getName());

    public InvoicePredictor() {
    }

    public Date predictDate(User user) {

        List<Invoice> invoiceList = user.getInvoiceList();

        NeuralNetwork neuralNet = new MultiLayerPerceptron(4, 9, 1);
        ((LMS) neuralNet.getLearningRule()).setMaxError(0.001); //0-1
        ((LMS) neuralNet.getLearningRule()).setLearningRate(0.7); //0-1
        ((LMS) neuralNet.getLearningRule()).setMaxIterations(MAX_ITERATIONS); //0-1

        TrainingSet trainingSet = new TrainingSet();

        Integer maxDays = computeMaxClearedDay(invoiceList);
        List<Integer> daysClearedList = datesClearedList(invoiceList);

        logger.info("Day list size: " + daysClearedList.size() + " Max days: " + maxDays);

        // 20 invoices : (4 input + 1 output) * 4
        for (int i = 0; i < 4; i++) {
            trainingSet.addElement(new SupervisedTrainingElement(new double[]{
                    (double) daysClearedList.get(i * 5) / maxDays,
                    (double) daysClearedList.get(i * 5 + 1) / maxDays,
                    (double) daysClearedList.get(i * 5 + 2) / maxDays,
                    (double) daysClearedList.get(i * 5 + 3) / maxDays},
                    new double[]{(double) daysClearedList.get(i * 5 + 4) / maxDays}
            ));
        }

        neuralNet.learnInSameThread(trainingSet);

        TrainingSet testSet = new TrainingSet();
        testSet.addElement(new TrainingElement((double) daysClearedList.get(daysClearedList.size() - 4) / maxDays,
                (double) daysClearedList.get(daysClearedList.size() - 3) / maxDays,
                (double) daysClearedList.get(daysClearedList.size() - 2) / maxDays,
                (double) daysClearedList.get(daysClearedList.size() - 1) / maxDays));

        for (TrainingElement testElement : testSet.trainingElements()) {
            neuralNet.setInput(testElement.getInput());
            neuralNet.calculate();
            Vector<Double> networkOutput = neuralNet.getOutput();
            logger.info("Input: " + testElement.getInput());
            logger.info(" Output: " + networkOutput);
        }
        return new DateTime(invoiceList.get(invoiceList.size() - 1).getInvoiceSubmittedTS())
                .plusDays((int) Math.floor(neuralNet.getOutput().get(0) * maxDays)).toDate();
    }

    private Integer computeMaxClearedDay(List<Invoice> invoiceList) {
        Integer maxDays = 0;
        for (Integer days : datesClearedList(invoiceList)) {
            if (days > maxDays)
                maxDays = days;
        }
        return maxDays;
    }

    private List<Integer> datesClearedList(List<Invoice> invoiceList) {
        List<Integer> dateList = new ArrayList<>();
        for (Invoice invoice : invoiceList) {
            Date submittedDate = invoice.getInvoiceSubmittedTS();
            Date completedDate = invoice.getInvoiceCompletedTS();
            if (completedDate != null && submittedDate != null)
                dateList.add(Days.daysBetween(new DateTime(submittedDate), new DateTime(completedDate)).getDays());
        }
        return dateList;
    }

}
