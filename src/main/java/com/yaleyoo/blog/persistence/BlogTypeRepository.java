package com.yaleyoo.blog.persistence;

import com.yaleyoo.blog.domain.BlogType;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by steve on 13/3/19.
 */
public interface BlogTypeRepository extends MongoRepository<BlogType, Integer> {
}
