package com.yaleyoo.blog.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve on 13/3/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogTypeServiceTests {
    @Autowired
    private BlogTypeRepository blogTypeRepository;

    @Test
    public void testAll(){
        List<BlogType> list = blogTypeRepository.findAll();
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testFindType(){
        BlogType type = blogTypeRepository.findByTypeName("Java");
        Assert.assertEquals("Java", type.getTypeName());
    }

    @Test
    public void testInsert(){
        BlogType type = new BlogType();
        type.setTypeName("Java");
        type.setBlogList(new ArrayList<>());
        blogTypeRepository.save(type);
    }

    @Test
    public void testDelete(){
        BlogType type = new BlogType();
        type.setTypeName("Java");
        System.out.println(blogTypeRepository.deleteBlogTypeByTypeName("Java"));
    }

}
