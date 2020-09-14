/**
 * 
 */
package com.pon.kafka.producer;

/**
 * @author Sanjeev
 *
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.pon.entity.User;

public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;
    
    /**
     * Using kafka template, the following send() method will send payload and topic name to the kafka server 
     * for writing it there under the sent topic 
     * */

    public void send(String topic, User payload) {
        LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
        kafkaTemplate.send(topic, payload);
    }
}