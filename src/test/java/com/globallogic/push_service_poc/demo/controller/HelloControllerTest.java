//package com.globallogic.push_service_poc.demo.controller;
//
//
////import com.globallogic.push_service_poc.demo.bo.PaymentPredictor;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
///**
// * Created by VladyslavPrytula on 4/11/14.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations =  {"classpath:**/AppConfig.java" })//("file:/Users/VladyslavPrytula/Documents/JavaTestPrep/push_service/gcm-demo-server/src/main/java/com/google/android/gcm/demo/config/AppConfig.java")//classes = AppConfig.class)
////@ContextConfiguration("file:/Users/VladyslavPrytula/Documents/JavaTestPrep/push_service/gcm-demo-server/src/main/webapp/WEB-INF/mvc-dispatcher-servlet-test.xml")
//public class HelloControllerTest {
//    @Autowired
//    protected WebApplicationContext wac;
//
////    @Mock
////    PaymentPredictor paymentPredictor;
//
//    @InjectMocks
//    HelloController helloController;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
//      //  this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//    }
//
//    @Test
//    public void testPrintWelcome() throws Exception {
//     //   Mockito.when(PaymentPredictor.predict(user)).thenReturn("test");
//        mockMvc.perform(get("/hello"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("hello_test"));
//
//    }
//}
