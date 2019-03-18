package com.yaleyoo.blog.persistence;

import com.yaleyoo.blog.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by steve on 13/3/19.
 */
public interface UserRepository extends MongoRepository<User, String>{
    long deleteByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
}
