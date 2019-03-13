package com.yaleyoo.blog.persistence;

import com.yaleyoo.blog.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by steve on 13/3/19.
 */
public interface UserRepository extends MongoRepository<User, String>{
    long deleteByUsername(String username);
    boolean existsByUsername(String username);
    User findByUsername(String username);
}
