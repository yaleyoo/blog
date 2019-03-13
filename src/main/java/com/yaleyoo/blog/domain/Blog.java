package com.yaleyoo.blog.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;

@Document(collection = "blog")
public class Blog {
    @Id
    private String id;
    private String blogName;
	private LocalDate blogTime;
	private String blogContent;
	private boolean blogHP;
	private String blogKeyword;
	private LocalDate blogUpdate;
	private String blogDescription;
	private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getBlogTime() {
        return blogTime;
    }

    public void setBlogTime(LocalDate blogTime) {
        this.blogTime = blogTime;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogKeyword() {
        return blogKeyword;
    }

    public void setBlogKeyword(String blogKeyword) {
        this.blogKeyword = blogKeyword;
    }

    public LocalDate getBlogUpdate() {
        return blogUpdate;
    }

    public void setBlogUpdate(LocalDate blogUpdate) {
        this.blogUpdate = blogUpdate;
    }

    public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isBlogHP() {
        return blogHP;
    }

    public void setBlogHP(boolean blogHP) {
        this.blogHP = blogHP;
    }
}
