package com.globallogic.push_service_poc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Created by VladyslavPrytula on 4/14/14.
 */
@Configuration
public class ViewConfig {

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(new String[]
                {"/WEB-INF/layouts/tiles.xml",
                        "/WEB-INF/views*//**//*tiles.xml"});
        configurer.setCheckRefresh(true);
        return configurer;
    }

    @Bean
    public TilesViewResolver getTilesViewResolver() {
        TilesViewResolver tilesViewResolver = new TilesViewResolver();
        /*
         * tilesViewResolver.setPrefix("/WEB-INF/jsp/");
         * tilesViewResolver.setSuffix(".jsp");
         */
        tilesViewResolver.setViewClass(TilesView.class);
        return tilesViewResolver;
    }


    // Add bean for InternalResourceViewResolver
//    @Bean
//    public InternalResourceViewResolver getInternalResourceViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/pages/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }
}
