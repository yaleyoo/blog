package com.yaleyoo.blog.persistence;

import com.yaleyoo.blog.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by steve on 12/3/19.
 */
public interface BlogRepository extends MongoRepository<Blog, Integer>{
    Page<Blog> findAll(Pageable page);
    Page<Blog> findByIsPrivate(Pageable page, boolean isPrivate);
    Page<Blog> findBlogsByBlogHP(boolean blogHP, Pageable page);
    Page<Blog> findBlogsByType(String type, Pageable page);
    Optional<Blog> findByBlogNameAndCreateDate(String blogName, LocalDate createDate);
    Optional<Blog> findById(String id);
    long deleteByBlogNameAndCreateDate(String blogName, LocalDate createDate);
}
