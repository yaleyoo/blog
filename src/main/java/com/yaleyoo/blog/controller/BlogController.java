package com.yaleyoo.blog.controller;

import com.yaleyoo.blog.domain.Blog;
import com.yaleyoo.blog.exception.BlogNotFoundException;
import com.yaleyoo.blog.exception.CacheUpdateException;
import com.yaleyoo.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by steve on 13/3/19.
 */
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/blog/{page}", method = RequestMethod.GET)
    ResponseEntity getPagedBlog(@PathVariable int page){

        return blogService.getBlogPage(page);
    }

    @RequestMapping(value = "/blog/{year}/{month}/{day}/{blogName}", method = RequestMethod.GET)
    ResponseEntity getBlog(@PathVariable int year, @PathVariable int month, @PathVariable int day,
                         @PathVariable String blogName) throws BlogNotFoundException{

        return blogService.getBlog(LocalDate.of(year, month, day), blogName);
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    ResponseEntity insertBlog(@RequestBody Blog blog) throws CacheUpdateException{

        return blogService.insertBlog(blog);
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @RequestMapping(value = "/blog", method = RequestMethod.PUT)
    ResponseEntity updateBlog(@RequestBody Blog blog) throws BlogNotFoundException, CacheUpdateException{

        return blogService.updateBlog(blog);
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @RequestMapping(value = "/blog/{year}/{month}/{day}/{blogName}", method = RequestMethod.DELETE)
    ResponseEntity deleteBlog(@PathVariable int year, @PathVariable int month, @PathVariable int day,
                        @PathVariable String blogName) throws CacheUpdateException, BlogNotFoundException {

        return blogService.deleteBlog(LocalDate.of(year, month, day), blogName);
    }

    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public ResponseEntity getTypes(){

        return blogService.typeStatistic();
    }

    @RequestMapping(value = "/blog/{type}/{page}", method = RequestMethod.GET)
    ResponseEntity getBlogByType(@PathVariable String type, @PathVariable int page){
        return blogService.getBlogPageByType(type, page);
    }

}
