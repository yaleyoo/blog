package com.yaleyoo.blog.service;

import com.yaleyoo.blog.domain.User;
import com.yaleyoo.blog.exception.UserNotFoundException;
import com.yaleyoo.blog.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by steve on 13/3/19.
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public long deleteUserByUsernameAndPassword(String username, String password){
        return userRepository.deleteByUsernameAndPassword(username, password);
    }

    public Optional<User> updateUser(User user) throws UserNotFoundException{
        if (userRepository.findByUsername(user.getUsername()).getId()!=null){
            return Optional.of(userRepository.save(user));
        }
        else
            return Optional.empty();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
