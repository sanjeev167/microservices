/**
 * 
 */
package com.pon.kafka.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.pon.entity.User;

@Configuration
public class SenderConfig {

	//Here "spring.kafka.bootstrap-servers" is picked through ms-config-server where this property has been defined
	// in ms-user.yml file.
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // Here the producer is ms-user application. For writing a message on kafka server, 
    //we need to configure "producerConfigs".
    //This will receive three things.
    //1. kafka server location which is fed through bootstrapServers variable. 
    //This variable has already been assigned a value above
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return props;
    }
    
    
   //Now the ProducerFactory bean is prepared using  producerConfigs()
    @Bean
    public ProducerFactory<String, User> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    //Finally, KafkaTemplate bean is made ready using ProducerFactory().
    //This is the bean that will be used through sender bean for actual sending of topic and payload to the kafka server.
    @Bean
    public KafkaTemplate<String, User> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    //This sender class has already been defined. Here we are telling spring to prepare a bean 
    // to be used within the service or we can annotate this class with @component
    @Bean
    public Sender sender() {
        return new Sender();
    }
}
