package com.yaleyoo.blog.config;

import com.yaleyoo.blog.util.BlogProperties;
import com.yaleyoo.blog.util.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by steve on 13/3/19.
 */
@Configuration
public class DisplayConfig implements ApplicationListener<ContextRefreshedEvent>{
    public static int BLOG_PER_HOMEPAGE;
    public static int BLOG_PER_PAGE;

    @Autowired
    private BlogProperties blogProperties;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            BLOG_PER_PAGE = Integer.parseInt(blogProperties.getPerPage());
            BLOG_PER_HOMEPAGE = Integer.parseInt(blogProperties.getPerHomepage());
        } catch (NumberFormatException e){
            System.out.println("[ERROR] - 'per-page/per-homepage' setting is wrong in application.properties.");
            e.printStackTrace();
        }
    }
}
