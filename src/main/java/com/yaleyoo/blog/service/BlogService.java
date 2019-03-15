package com.yaleyoo.blog.service;

import com.yaleyoo.blog.config.DisplayConfig;
import com.yaleyoo.blog.domain.Blog;
import com.yaleyoo.blog.exception.BlogNotFoundException;
import com.yaleyoo.blog.persistence.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by steve on 12/3/19.
 */
@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public Page<Blog> getBlogPage(int page){
        Sort sort = new Sort(Sort.Direction.DESC, "blogTime");
        Pageable pageable = PageRequest.of(page, DisplayConfig.BLOG_PER_PAGE, sort);

        return blogRepository.findByIsPrivate(pageable, false);
    }

    public Page<Blog> getBlogPageByHP(int page){
        Sort sort = new Sort(Sort.Direction.DESC, "blogTime");
        Pageable pageable = PageRequest.of(page, DisplayConfig.BLOG_PER_HOMEPAGE, sort);

        return blogRepository.findBlogsByBlogHP(true, pageable);
    }

    public Page<Blog> getBlogPageByType(String type, int page){
        Sort sort = new Sort(Sort.Direction.DESC, "blogTime");
        Pageable pageable = PageRequest.of(page, DisplayConfig.BLOG_PER_PAGE, sort);

        return blogRepository.findBlogsByType(type, pageable);
    }


    public Blog getBlog(LocalDate createDate, String blogName) throws BlogNotFoundException{
        return blogRepository.findByBlogNameAndCreateDate(blogName, createDate)
                .orElseThrow(BlogNotFoundException::new);
    }

    private Optional<Blog> getBlogById(String id){
        return blogRepository.findById(id);
    }

    public Blog insertBlog(Blog blog){
        blog.setCreateDate(LocalDate.now());
        blog.setLastUpdateDate(LocalDate.now());
        HashOperations<String, String, Integer> hashOp = stringRedisTemplate.opsForHash();
        Object map = hashOp.entries("typeStatistic::com.yaleyoo.blog.controller.BlogControllergetTypes");
        return null;
//        return blogRepository.save(blog);
    }

    public Blog updateBlog(Blog newBlog) throws BlogNotFoundException{
        newBlog.setLastUpdateDate(LocalDate.now());
        return this.getBlogById(newBlog.getId())
                .map(b -> blogRepository.save(newBlog))
                .orElseThrow(BlogNotFoundException::new);
    }

    public long deleteBlog(LocalDate localDate, String blogName){
        return blogRepository.deleteByBlogNameAndAndCreateDate(blogName, localDate);
    }

    public HashMap<String, Integer> typeStatistic(){
        HashMap<String, Integer> statistic = new HashMap<>();
        List<Blog> blogList = blogRepository.findAll();
//        blogList.stream().map(
//                blog -> statistic.put(blog.getType(), statistic.getOrDefault(blog.getType(), 0) + 1));
        for (Blog b : blogList){
            statistic.put(b.getType(), statistic.getOrDefault(b.getType(), 0) + 1);
        }
        return statistic;
    }
}
