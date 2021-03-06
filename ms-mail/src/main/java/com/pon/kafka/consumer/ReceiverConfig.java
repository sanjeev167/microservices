/**
 * 
 */
package com.pon.kafka.consumer;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.pon.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sanjeev
 *
 *
 *         To be able to read the messages from Kafka, we need to configure
 *         ConsumerFactory and wrap KafkaListenerContainerFactory. As well as
 *         the ProducerFactory it needs some configuration properties to be
 *         set. @EnableKafka is needed to enable detection of @KafkaListener
 *         annotations on any Spring-managed beans.
 */
@Configuration
@EnableKafka
public class ReceiverConfig {

	 @Value("${spring.kafka.bootstrap-servers}")//Will pick kafka server url though ms-config-server
	private String bootstrapServers;

	@Value("${spring.kafka.consumer.group-id}")//Will pick consumer group id though ms-config-server
	private String consumerGroupId;

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);

		return props;
	}

	@Bean
	public ConsumerFactory<String, User> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
				new JsonDeserializer<>(User.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, User> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}

	@Bean
	public Receiver receiver() {
		return new Receiver();
	}
}
