package fr.training.spring.library.exposition;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "fr.training.spring.library" })
public class LibraryApplication {

	private static final Logger logger = LoggerFactory.getLogger(LibraryApplication.class);

	/**
	 * Application main method
	 *
	 * @param args main aguments
	 * @throws UnknownHostException
	 */
	public static void main(final String[] args) throws UnknownHostException {
		final Environment env = SpringApplication.run(LibraryApplication.class, args).getEnvironment();
		logApplicationStartup(env);
	}

	/**
	 * Log application urls and used profiles
	 *
	 * @param env the spring environment object
	 * @throws UnknownHostException
	 */
	private static void logApplicationStartup(final Environment env) throws UnknownHostException {
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		final String serverPort = env.getProperty("server.port");
		String contextPath = env.getProperty("server.servlet.context-path");
		if (StringUtils.isBlank(contextPath)) {
			contextPath = "/";
		}
		String hostAddress = "localhost";

		hostAddress = InetAddress.getLocalHost().getHostAddress();

		logger.info("\n----------------------------------------------------------\n\t" //
				+ "Application '{}' is running! Access URLs:\n\t" //
				+ "Local: \t\t{}://localhost:{}{}\n\t" //
				+ "External: \t{}://{}:{}{}\n\t" //
				+ "Profile(s): \t{}\n----------------------------------------------------------", //
				env.getProperty("spring.application.name"), protocol, serverPort, contextPath, protocol, hostAddress,
				serverPort, contextPath, env.getActiveProfiles());
	}
}
