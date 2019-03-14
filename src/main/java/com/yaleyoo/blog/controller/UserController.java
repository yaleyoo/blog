package com.yaleyoo.blog.controller;

import com.yaleyoo.blog.domain.User;
import com.yaleyoo.blog.exception.UserNotFoundException;
import com.yaleyoo.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by steve on 14/3/19.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize(value = "hasRole('MANAGER')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    long deleteUser(@RequestBody @Validated User user){
        return userService.deleteUserByUsernameAndPassword(user.getUsername(), passwordEncoder.encode(user.getPassword()));
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    boolean insertUser(@RequestBody @Validated User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.saveUser(user).getId() != null;
    }

    @PreAuthorize(value = "hasRole('MANAGER')")
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    boolean updateUser(@RequestBody @Validated User user) throws UserNotFoundException{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.updateUser(user).isPresent();
    }
}
