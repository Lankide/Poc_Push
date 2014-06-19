package com.globallogic.push_service_poc.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by VladyslavPrytula on 4/12/14.
 */
@Configuration
@EnableWebMvc //this is the same as <mvc:annotation-driven/>
@Import({DataBaseConfig.class, ViewConfig.class})
@ComponentScan(basePackages = {"com.globallogic.push_service_poc.demo"}) //his is the same as <context:component-scan base-package=”
/**
 *  Extend the class to use WebMvcConfigurerAdapter.
 *  This adds stub implementations from the WebMvcConfigurer interface which is used by @EnableWebMVC.
 *  It also gives us a chance to override resources and the default handler.
 */

public class AppConfig extends WebMvcConfigurerAdapter{

    //Set default servlet handler, this is the same as <mvc:default-servlet-handler/>
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    //to load static resources
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }


}
