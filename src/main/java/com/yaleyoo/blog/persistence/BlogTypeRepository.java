package com.yaleyoo.blog.persistence;

import com.yaleyoo.blog.domain.BlogType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by steve on 13/3/19.
 */
public interface BlogTypeRepository extends MongoRepository<BlogType, Integer> {
    List<BlogType> findAll();
    Optional<BlogType> findByTypeName(String typeName);
    long deleteBlogTypeByTypeName(String typeName);
}
