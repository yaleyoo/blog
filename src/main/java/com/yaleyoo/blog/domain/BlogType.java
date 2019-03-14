package com.yaleyoo.blog.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
/**
 * Created by steve on 13/3/19.
 */
@Document(collection = "blogType")
public class BlogType {
    @Id
    private String id;
    private String typeName;
    private List<BlogDTO> blogList;

    public BlogType(String id, String typeName, List<BlogDTO> blogList) {
        this.id = id;
        this.typeName = typeName;
        this.blogList = blogList;
    }

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

    public List<BlogDTO> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<BlogDTO> blogList) {
        this.blogList = blogList;
    }
}
