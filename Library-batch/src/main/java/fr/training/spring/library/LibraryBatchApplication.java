package fr.training.spring.library;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "fr.training.spring.library" })
@EnableBatchProcessing
public class LibraryBatchApplication {

	/**
	 * Entry point for spring-boot based app
	 *
	 * @param args - arguments
	 */
	public static void main(final String[] args) {
		final ApplicationContext context = SpringApplication.run(LibraryBatchApplication.class, args);
		System.exit(SpringApplication.exit(context));
	}

}
