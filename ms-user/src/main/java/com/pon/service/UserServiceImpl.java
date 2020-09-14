package com.pon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pon.entity.User;
import com.pon.kafka.producer.Sender;
import com.pon.repository.UserRepository;


/**
 * @author Sanjeev
 *
 */

@Service
public class UserServiceImpl implements UserService{

	//This will be used by sender for writing a message on which topic
	 @Value("${spring.kafka.topic.userCreated}")
	    private String USER_CREATED_TOPIC;

	    private UserRepository userRepository;
	    private Sender sender;

	    @Autowired
	    UserServiceImpl(UserRepository userRepository, Sender sender) {
	        this.userRepository = userRepository;
	        this.sender = sender;
	    }
    
	    
	    
	    //This method is used through controller when a request for a user registration come over there.
	    //This request comes with a payload. Here it is a User object having all the details for user registration.
	    @Override
	    public User registerUser(User input) {
	        User createdUser = userRepository.save(input);
	        //Now write a message regarding this event at Kafka under USER_CREATED_TOPIC 
	        sender.send(USER_CREATED_TOPIC, createdUser);
	        return createdUser;
	    }

	    @Override
	    public Iterable<User> findAll() {
	        return userRepository.findAll();
	    }
	}
	
