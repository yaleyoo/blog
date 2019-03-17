package com.yaleyoo.blog.service;

import com.yaleyoo.blog.domain.Blog;
import com.yaleyoo.blog.exception.CacheUpdateException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steve on 12/3/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogServiceTests {
    @Autowired
    private BlogService blogService;
    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testBlogPage(){
        Page<Blog> blog = blogService.getBlogPage(0);
        Assert.assertEquals(1,blog.getContent().size());
    }

    @Test
    public void testBlogHomePage(){
        Page<Blog> blog = blogService.getBlogPageByHP(0);
        Assert.assertEquals(1,blog.getContent().size());
    }

    @Test
    public void testBlogPageType(){
        Page<Blog> blog = blogService.getBlogPageByType("Java", 0);
        Assert.assertEquals(1,blog.getContent().size());
    }

    @Test
    public void testAddBlog() throws CacheUpdateException {
        Blog blog = new Blog();
        blog.setBlogName("testing2");
        blog.setBlogContent("testing............");
        Blog b = blogService.insertBlog(blog);
        System.out.println();
    }

    @Test
    public void testRedis(){
        Map<String, Integer> map = new HashMap<>();
        map.put("Java", 1);
        redisTemplate.opsForValue().set("testing", map);

        Object o = redisTemplate.opsForValue().get("testing");
        System.out.println();
    }

    @Test
    public void testDelete(){
    }
}
