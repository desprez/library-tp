package fr.training.spring.library.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "fr.training.spring.library" })
@EnableBatchProcessing(modular = true)
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

	/*	@Bean
	public JobLauncherCommandLineRunner jobLauncherCommandLineRunnerJobLauncher(final JobLauncher jobLauncher,
			final JobExplorer jobExplorer, final JobRepository jobRepository,

			@Value("${job.name}") final String jobName) {

		final JobLauncherCommandLineRunner runner = new JobLauncherCommandLineRunner(jobLauncher, jobExplorer,
				jobRepository);

		runner.setJobNames(jobName);

		return runner;
	}*/

	// @Bean
	// public ApplicationContextFactory scheduledTransferJobDefinition() {
	// return new
	// GenericApplicationContextFactory(ScheduledTransferJobConfig.class);
	// }

	/*	@Bean
	public ApplicationContextFactory exportJobDefinition() {
		return new GenericApplicationContextFactory(ExportAccountJobConfig.class);
	}

	@Bean
	public ApplicationContextFactory importJobDefinition() {
		return new GenericApplicationContextFactory(ImportJobConfig.class);
	}*/

}
