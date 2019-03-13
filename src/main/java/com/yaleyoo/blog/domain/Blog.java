package com.yaleyoo.blog.domain;

import java.time.LocalDate;

public class Blog {
    private String blogName;
	private LocalDate blogTime;
	private String blogContent;
	private boolean blogHp;
	private String blogKeyword;
	private LocalDate blogUpdate;
	private String blogDescription;
	private String type;


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

    public boolean isBlogIsHp() {
        return blogHp;
    }

    public void setBlogIsHp(boolean blogIsHp) {
        this.blogHp = blogIsHp;
    }
}
