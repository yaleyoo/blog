package com.yaleyoo.blog.domain;

import java.util.List;

public class BlogType {
    private String typeName;
    private List<String> blogList;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<String> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<String> blogList) {
        this.blogList = blogList;
    }
}
