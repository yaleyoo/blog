package com.yaleyoo.blog.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by steve on 22/3/19.
 */
@Component
@ConfigurationProperties(prefix = "yaleyoo.blog")
public class BlogProperties {
    private String perPage;
    private String perHomepage;

    public String getPerPage() {
        return perPage;
    }

    public void setPerPage(String perPage) {
        this.perPage = perPage;
    }

    public String getPerHomepage() {
        return perHomepage;
    }

    public void setPerHomepage(String perHomepage) {
        this.perHomepage = perHomepage;
    }
}
