package com.yaleyoo.blog.service;

import com.yaleyoo.blog.config.DisplayConfig;
import com.yaleyoo.blog.domain.Blog;
import com.yaleyoo.blog.exception.BlogNotFoundException;
import com.yaleyoo.blog.exception.CacheUpdateException;
import com.yaleyoo.blog.persistence.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
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
                .orElseThrow(() -> new BlogNotFoundException(blogName));
    }

    private Optional<Blog> getBlogById(String id){
        return blogRepository.findById(id);
    }

    public Blog insertBlog(Blog blog) throws CacheUpdateException{
        blog.setCreateDate(LocalDate.now());
        blog.setLastUpdateDate(LocalDate.now());

        if (updateCacheWhileInsert(blog.getType()))
            return blogRepository.save(blog);
        else throw new CacheUpdateException();
    }

    public Blog updateBlog(Blog newBlog) throws BlogNotFoundException, CacheUpdateException{
        Optional<Blog> blogOptional = this.getBlogById(newBlog.getId());
        if (blogOptional.isPresent()){
            if (updateCacheWhileUpdate(blogOptional.get().getType(), newBlog.getType())){
                newBlog.setCreateDate(blogOptional.get().getCreateDate());
                newBlog.setLastUpdateDate(LocalDate.now());
                Blog b = blogRepository.save(newBlog);
                return b;
            }
            else throw new CacheUpdateException();
        }
        else throw new BlogNotFoundException(newBlog.getBlogName());
    }

    public void deleteBlog(LocalDate localDate, String blogName) throws BlogNotFoundException{

        Blog blog = this.getBlog(localDate, blogName);
        updateCacheWhileDelete(blog.getType());
        blogRepository.delete(blog);
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
    /*=== Update Cache ===*/

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
