package com.yaleyoo.blog.domain;

import java.time.LocalDate;

/**
 * Created by steve on 14/3/19.
 */
public class BlogDTO {
    private String blogName;
    private LocalDate createDate;

    public BlogDTO(Blog blog){
        this.blogName = blog.getBlogName();
        this.createDate = blog.getCreateDate();
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
