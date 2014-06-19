package com.globallogic.push_service_poc.demo.bo;


import com.globallogic.push_service_poc.demo.entity.Invoice;
import com.globallogic.push_service_poc.demo.entity.User;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.SupervisedTrainingElement;
import org.neuroph.core.learning.TrainingElement;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.LMS;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by VladyslavPrytula on 3/25/14.
 */

@Service
public class InvoicePredictor {
    private int maxDate=31;
    private int maxIterations = 10000;

    public InvoicePredictor() {
    }

    public Integer predictDate(User user){

//        List<Invoice> invoiceList = user.getInvoiceList();
//
//        NeuralNetwork neuralNet = new MultiLayerPerceptron(4, 9, 1);
//        ((LMS) neuralNet.getLearningRule()).setMaxError(0.001);//0-1
//        ((LMS) neuralNet.getLearningRule()).setLearningRate(0.7);//0-1
//        ((LMS) neuralNet.getLearningRule()).setMaxIterations(maxIterations);//0-1
//
//        TrainingSet trainingSet = new TrainingSet();
//
////        To find the max value of Payment : maxPayment = max(days [k], k =0, days.length-1))
////        To calculate normalized values:
////        paymentNorm [i] = (days [i] [3] / maxPayment)*0.8+0.1, where 0.8 and 0.1 will be used to avoid the very small (0.0...) and very big (0.9999) values.
////        We have carried out a simplification, have simply divided on 10000.
//        // double daxmax = 10000.0D;
//
//        Double maxDate = computeMaxClearedDay(invoiceList);
//        List<Double> datesClearedList = datesClearedList(invoiceList);
//
//        for(int i=0; i<datesClearedList.size(); i=i-4) {
//            trainingSet.addElement(new SupervisedTrainingElement(new double[]{
//                    datesClearedList.get(i) / maxDate,
//                    datesClearedList.get(i+1) / maxDate,
//                    datesClearedList.get(i+2) / maxDate,
//                    datesClearedList.get(i+3) / maxDate},
//                    new double[]{datesClearedList.get(i+4) / maxDate}
//            ));
//        }
//
//
//        neuralNet.learnInSameThread(trainingSet);
//        System.out.println("Time stamp N2:" + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));
//
//        TrainingSet testSet = new TrainingSet();
//        testSet.addElement(new TrainingElement(new double[]{(datesClearedList.size()-3)/ maxDate,
//                datesClearedList.get(datesClearedList.size()-2)/ maxDate,
//                datesClearedList.get(7)-1/ maxDate,
//                datesClearedList.get(8)/ maxDate}));
//
//        for (TrainingElement testElement : testSet.trainingElements()) {
//            neuralNet.setInput(testElement.getInput());
//            neuralNet.calculate();
//            Vector<Double> networkOutput = neuralNet.getOutput();
//            System.out.print("Input: " + testElement.getInput());
//            System.out.println(" Output: " + networkOutput);
//        }
//        System.out.println("Time stamp N3:" + new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss:MM").format(new Date()));

        return 15;
    }

    private Double computeMaxClearedDay(List<Invoice> invoiceList){
        double max = 0;
        for (Invoice invoice:invoiceList){
            Calendar cal = Calendar.getInstance();
            cal.setTime(invoice.getInvoiceCompletedTS());
            int day = cal.get(Calendar.DAY_OF_MONTH);
            if (day >= max){
                max = (double)day;
            }
        }
        return max;
    }

    private List<Double> datesClearedList(List<Invoice> invoiceList){
        List<Double> dateList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Invoice invoice:invoiceList){
            cal.setTime(invoice.getInvoiceCompletedTS());
            int day = cal.get(Calendar.DAY_OF_MONTH);
            dateList.add((double)cal.get(Calendar.DAY_OF_MONTH));
        }
        return dateList;
    }

}
