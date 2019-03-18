package com.yaleyoo.blog.service;

import com.yaleyoo.blog.config.DisplayConfig;
import com.yaleyoo.blog.domain.Blog;
import com.yaleyoo.blog.exception.BlogNotFoundException;
import com.yaleyoo.blog.exception.CacheUpdateException;
import com.yaleyoo.blog.persistence.BlogRepository;
import com.yaleyoo.blog.response.SimpleHttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Created by steve on 12/3/19.
 */
@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    public ResponseEntity getBlogPage(int page){
        Sort sort = new Sort(Sort.Direction.DESC, "blogTime");
        Pageable pageable = PageRequest.of(page, DisplayConfig.BLOG_PER_PAGE, sort);

        return new ResponseEntity(
                new SimpleHttpResult(blogRepository.findByIsPrivate(pageable, false).getContent()),
                HttpStatus.OK);
    }

    public ResponseEntity getBlogPageByHP(int page){
        Sort sort = new Sort(Sort.Direction.DESC, "blogTime");
        Pageable pageable = PageRequest.of(page, DisplayConfig.BLOG_PER_HOMEPAGE, sort);

        return new ResponseEntity(
                new SimpleHttpResult(blogRepository.findBlogsByBlogHP(true, pageable).getContent()),
                HttpStatus.OK);
    }

    public ResponseEntity getBlogPageByType(String type, int page){
        Sort sort = new Sort(Sort.Direction.DESC, "blogTime");
        Pageable pageable = PageRequest.of(page, DisplayConfig.BLOG_PER_PAGE, sort);

        return new ResponseEntity(
                new SimpleHttpResult(blogRepository.findBlogsByType(type, pageable).getContent()),
                HttpStatus.OK);
    }


    public ResponseEntity getBlog(LocalDate createDate, String blogName) throws BlogNotFoundException{
        Blog blog = blogRepository.findByBlogNameAndCreateDate(blogName, createDate)
                .orElseThrow(() -> new BlogNotFoundException(blogName));
        return new ResponseEntity(
                new SimpleHttpResult(blog),
                HttpStatus.OK);
    }

    private ResponseEntity getBlogById(String id) throws BlogNotFoundException{
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException(id));
        return new ResponseEntity(
                new SimpleHttpResult(blog),
                HttpStatus.OK);
    }

    public ResponseEntity insertBlog(Blog blog) throws CacheUpdateException{
        blog.setCreateDate(LocalDate.now());
        blog.setLastUpdateDate(LocalDate.now());

        if (updateCacheWhileInsert(blog.getType()))
            return new ResponseEntity(
                    new SimpleHttpResult(blogRepository.save(blog)),
                    HttpStatus.OK);
        else throw new CacheUpdateException();
    }

    public ResponseEntity updateBlog(Blog newBlog) throws BlogNotFoundException, CacheUpdateException{
        Optional<Blog> blogOptional = blogRepository.findById(newBlog.getId());
        if (blogOptional.isPresent()){
            if (updateCacheWhileUpdate(blogOptional.get().getType(), newBlog.getType())){
                newBlog.setCreateDate(blogOptional.get().getCreateDate());
                newBlog.setLastUpdateDate(LocalDate.now());
                Blog b = blogRepository.save(newBlog);
                return new ResponseEntity(
                        new SimpleHttpResult(b),
                        HttpStatus.OK);
            }
            else throw new CacheUpdateException();
        }
        else throw new BlogNotFoundException(newBlog.getBlogName());
    }

    public ResponseEntity deleteBlog(LocalDate localDate, String blogName) throws BlogNotFoundException, CacheUpdateException{

        Blog blog = blogRepository.findByBlogNameAndCreateDate(blogName, localDate)
                .orElseThrow(() -> new BlogNotFoundException(blogName));
        if (updateCacheWhileDelete(blog.getType())) {
            blogRepository.delete(blog);
            return new ResponseEntity(
                    new SimpleHttpResult(),
                    HttpStatus.OK);
        }
        else
            throw new CacheUpdateException();
    }

    public ResponseEntity typeStatistic(){
        HashMap<String, Integer> statistic = new HashMap<>();
        List<Blog> blogList = blogRepository.findAll();
//        blogList.stream().map(
//                blog -> statistic.put(blog.getType(), statistic.getOrDefault(blog.getType(), 0) + 1));
        for (Blog b : blogList){
            statistic.put(b.getType(), statistic.getOrDefault(b.getType(), 0) + 1);
        }
        // cache into redis
        statistic.forEach((k, v) -> redisTemplate.opsForValue().setIfAbsent(k, v.toString()));
        return new ResponseEntity(
                new SimpleHttpResult(statistic),
                HttpStatus.OK);
    }
    /** Update Cache **/
    private boolean updateCacheWhileInsert(String typeName){
        boolean isUpdated;
        if (isPresent(typeName)) {
            isUpdated = redisTemplate.opsForValue().setIfPresent(
                    typeName,
                    Integer.parseInt((String) redisTemplate.opsForValue().get(typeName)) + 1 + "");
        }
        else{
            isUpdated = redisTemplate.opsForValue().setIfAbsent(typeName, 1 + "");
        }
        return isUpdated;
    }

    private boolean updateCacheWhileUpdate(String typeName, String newTypeName){
        boolean isUpdated = updateCacheWhileDelete(typeName);
        if (isUpdated)
            isUpdated = updateCacheWhileInsert(newTypeName);
        return isUpdated;
    }

    private boolean updateCacheWhileDelete(String typeName){
        boolean isUpdated = redisTemplate.opsForValue().setIfPresent(
                typeName,
                Integer.parseInt((String) redisTemplate.opsForValue().get(typeName)) - 1 + "");

        return isUpdated;
    }

    private boolean isPresent(String key){
        return redisTemplate.keys("*").contains(key);
    }
}
