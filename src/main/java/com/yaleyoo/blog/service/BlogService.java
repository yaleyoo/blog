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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Created by steve on 12/3/19.
 */
@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private BlogTypeService blogTypeService;

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

    @Transactional
    public Blog insertBlog(Blog blog){
        blogTypeService.updateBlogTypeWhileInsert(blog);
        return blogRepository.save(blog);
    }

    private Optional<Blog> getBlogById(String id){
        return blogRepository.findById(id);
    }

    @Transactional
    public Blog updateBlog(Blog newBlog) throws BlogNotFoundException{
        Optional<Blog> oldBlog = getBlogById(newBlog.getId());
        if (oldBlog.isPresent()){
            // if type is modified
            if (oldBlog.get().getType().equals(newBlog.getType()))
                this.updateBlogType(oldBlog.get(), newBlog);
            return blogRepository.save(newBlog);
        }
        else
            throw new BlogNotFoundException();
    }

    private void updateBlogType(Blog oldBlog,Blog newBlog){
        blogTypeService.updateBlogTypeWhileDelete(oldBlog);
        blogTypeService.updateBlogTypeWhileInsert(newBlog);
    }

}
