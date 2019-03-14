package com.yaleyoo.blog.controller;

import com.yaleyoo.blog.domain.Blog;
import com.yaleyoo.blog.exception.BlogNotFoundException;
import com.yaleyoo.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by steve on 13/3/19.
 */
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/blog/{page}", method = RequestMethod.GET)
    public List<Blog> getPagedBlog(@PathVariable int page){

        return blogService.getBlogPage(page).getContent();
    }

    @RequestMapping(value = "/blog/{year}/{month}/{day}/{blogName}", method = RequestMethod.GET)
    public Blog getBlog(@PathVariable int year, @PathVariable int month, @PathVariable int day,
                         @PathVariable String blogName) throws BlogNotFoundException{

        return blogService.getBlog(LocalDate.of(year, month, day), blogName);
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    public Blog insertBlog(@RequestBody Blog blog){

        return blogService.insertBlog(blog);
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @RequestMapping(value = "/blog", method = RequestMethod.PUT)
    public Blog updateBlog(@RequestBody Blog blog) throws BlogNotFoundException{

        return blogService.updateBlog(blog);
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @RequestMapping(value = "/blog/{year}/{month}/{day}/{blogName}", method = RequestMethod.DELETE)
    public long deleteBlog(@PathVariable int year, @PathVariable int month, @PathVariable int day,
                        @PathVariable String blogName){

        return blogService.deleteBlog(LocalDate.of(year, month, day), blogName);
    }

}
