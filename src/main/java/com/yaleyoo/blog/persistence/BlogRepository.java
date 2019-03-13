package com.yaleyoo.blog.persistence;

import com.yaleyoo.blog.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by steve on 12/3/19.
 */
public interface BlogRepository extends MongoRepository<Blog, Integer>{
    Page<Blog> findAll(Pageable page);
    Page<Blog> findBlogsByBlogHp(boolean isHp, Pageable page);
    Page<Blog> findBlogsByType(String type, Pageable page);
}
