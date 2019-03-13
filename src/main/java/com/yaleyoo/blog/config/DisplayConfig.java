package com.yaleyoo.blog.config;

import com.yaleyoo.blog.util.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by steve on 13/3/19.
 */
@Component
public class DisplayConfig implements ApplicationListener<ContextRefreshedEvent>{
    public static int BLOG_PER_HOMEPAGE;
    public static int BLOG_PER_PAGE;

    final private PropertiesLoader propertiesLoader;

    @Autowired
    public DisplayConfig(PropertiesLoader propertiesLoader) {
        this.propertiesLoader = propertiesLoader;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            propertiesLoader.loadValue("per-page").ifPresent(x -> BLOG_PER_PAGE = Integer.valueOf(x));
            propertiesLoader.loadValue("per-homepage").ifPresent(x -> BLOG_PER_HOMEPAGE = Integer.valueOf(x));
        } catch (NumberFormatException e){
            System.out.println("[ERROR] - 'per-page/per-homepage' setting is wrong in application.properties.");
            e.printStackTrace();
        }
    }
}
