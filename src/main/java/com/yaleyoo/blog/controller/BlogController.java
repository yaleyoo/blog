package com.yaleyoo.blog.controller;

import com.yaleyoo.blog.domain.Blog;
import com.yaleyoo.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by steve on 13/3/19.
 */
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/blog/{page}", method = RequestMethod.GET)
    public List<Blog> blog(@PathVariable int page){
        return blogService.getBlogPage(page).getContent();
    }
}
