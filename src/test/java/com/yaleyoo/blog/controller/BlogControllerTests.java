package com.yaleyoo.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaleyoo.blog.domain.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Random;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by steve on 13/3/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BlogControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAllBlog() throws Exception{
        this.mockMvc.perform(get("/blog/0")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("blogName")));
    }

    @Test
    @WithMockUser(username = "fdfd", roles = {"MANAGER"})
    public void testAdd() throws Exception{
        Blog b = new Blog();
        b.setBlogContent("fdsfsdfsdfsdfds");
        b.setBlogName("Testing-auto");
        b.setBlogDescription("dfd");
        b.setBlogHP(false);
        b.setId("1");
        b.setPrivate(false);
        b.setType("Java");
        this.mockMvc.perform(
                post("/blog").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(b))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(content().string(containsString("blogName")));
    }

    @Test
    public void testFindOne() throws Exception{
        this.mockMvc.perform(get("/blog/2019/03/18/newBlog--xx"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testDelete() throws Exception{
        this.mockMvc.perform(delete("/blog/2019/03/18/newBlog--xx2"))
                .andDo(print()).andExpect(status().isOk());
    }

    public static String asJsonString(final Blog obj) {
//        try {
//            String a = new ObjectMapper().writeValueAsString(obj);
//            System.out.println(a);
//            return a;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        return "{"+
	"\"blogName\":\"newBlog--xx"+ (int)Math.random()*10000+"\","+
	"\"blogContent\": \"blogTestingPremiumblogTestingPremiumblogTestingPremiumblogTestingPremiumblogTestingPremiumblogTestingPrem\","+
	"\"blogHP\":true,"+
	"\"blogKeyword\":\"a,b,c\","+
	"\"blogDescription\":\"yaleyoo's testing description\","+
	"\"type\":\"newType\","+
	"\"isPrivate\":true" +
    "}";
    }
}
