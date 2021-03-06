/**
 * 
 */
package com.pon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pon.entity.User;
import com.pon.service.UserServiceImpl;

/**
 * @author Sanjeev
 *
 */
@RestController
//All methods within this class will produce json data as a response
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

   
    @Autowired
    UserServiceImpl userService;
    

    @RequestMapping(method = RequestMethod.GET, path = "/members")
    public ResponseEntity<Iterable<User>> getAll() {

        Iterable<User> all = userService.findAll();

        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<User> register(@RequestBody User input) {

        User result = userService.registerUser(input);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
