package br.com.entelgy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The @SpringBootApplication annotation is equivalent to
 * using @Configuration, @EnableAutoConfiguration and @ComponentScan with their
 * default attributes.
 * 
 * @author falvojr
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
