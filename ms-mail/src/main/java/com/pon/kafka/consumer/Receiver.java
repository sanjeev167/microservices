/**
 * 
 */
package com.pon.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.pon.entity.User;
import com.pon.service.EmailService;

import java.util.concurrent.CountDownLatch;


/**
 * @author Sanjeev
 *
 */
public class Receiver {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
	
	/**
	 * CountDownLatch is a concurrency construct that allows one or more threads to
	 * wait for a given set of operations to complete. A CountDownLatch is
	 * initialized with a given count. This count is decremented by calls to the
	 * countDown() method. ... Calling await() blocks the thread until the count
	 * reaches zero
	 */

    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private EmailService emailService;
    
    //In the method annotated with @KafkaListener, we add the logic we want to be invoked when a message is received.
    //Whenever a topic with a name "spring.kafka.topic.userCreated" is written on kafka server, this listener will be
    //invoked and its code written here will send a mail to the mentioned user.

    @KafkaListener(topics = "${spring.kafka.topic.userCreated}")
    public void receive(User payload) {
        LOGGER.info("received payload='{}'", payload);
        System.out.println("Receiver => Kanhajee => Mail is going to be sent.");
        emailService.sendSimpleMessage(payload);
        System.out.println("Receiver => Sanjeev => Mail has been sent successfully");
        latch.countDown();//It will decrease latch count by 1. Once it is zero, it will free this thread for other request
    }
}
