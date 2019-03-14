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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Created by steve on 12/3/19.
 */
@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

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
        return blogRepository.save(blog);
    }

    public Blog updateBlog(Blog newBlog) throws BlogNotFoundException{
        newBlog.setLastUpdateDate(LocalDate.now());
        return this.getBlogById(newBlog.getId())
                .map(b -> blogRepository.save(newBlog))
                .orElseThrow(BlogNotFoundException::new);
    }
}
