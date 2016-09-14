package br.com.entelgy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClientURI;

/**
 * Register a MongoDbFactory instance using Java based metadata.
 * 
 * @author falvojr
 */
@Configuration
public class MongoConfig {

	private static final String MONGODB_CLIENT_URI = "mongodb://entelgy:entelgy@ds029476.mlab.com:29476/entelgy-challenge";

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClientURI(MONGODB_CLIENT_URI));
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}
}
