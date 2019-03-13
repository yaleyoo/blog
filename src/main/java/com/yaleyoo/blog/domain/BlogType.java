package com.yaleyoo.blog.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "blogType")
public class BlogType {
    @Id
    private String id;
    private String typeName;
    private List<String> blogList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
