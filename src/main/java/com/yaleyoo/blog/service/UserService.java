package com.yaleyoo.blog.service;

import com.yaleyoo.blog.domain.User;
import com.yaleyoo.blog.exception.UserNotFoundException;
import com.yaleyoo.blog.persistence.UserRepository;
import com.yaleyoo.blog.response.SimpleHttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity getAllUser(){
        return new ResponseEntity(
                new SimpleHttpResult(userRepository.findAll()),
                HttpStatus.OK);
    }

    public ResponseEntity saveUser(User user){
        return new ResponseEntity(
                new SimpleHttpResult(userRepository.save(user)),
                HttpStatus.OK);
    }

    public ResponseEntity deleteUserByUsernameAndPassword(String username, String password) throws UserNotFoundException{
        long deleted = userRepository.deleteByUsernameAndPassword(username, password);

        if (deleted == 0) throw new UserNotFoundException();

        return new ResponseEntity(
                new SimpleHttpResult(true, deleted + " users are deleted."),
                HttpStatus.OK);
    }

    public ResponseEntity updateUser(User user) throws UserNotFoundException{
        User u = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UserNotFoundException());
        return new ResponseEntity(
                new SimpleHttpResult(u),
                HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).get();
    }
}
