package com.yaleyoo.blog.service;

import com.yaleyoo.blog.domain.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by steve on 12/3/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogServiceTests {
    @Autowired
    private BlogService blogService;

    @Test
    public void testBlogPage(){
        Page<Blog> blog = blogService.getBlogPage(0);
        System.out.println(blog.getContent().size());
    }

    @Test
    public void testAddBlog(){
        Blog blog = new Blog();
        blog.setBlogName("testing2");
        blog.setBlogContent("testing............");
//        blogService.insertBlog(blog);
    }
}
