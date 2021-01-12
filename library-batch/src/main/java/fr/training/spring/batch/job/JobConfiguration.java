package fr.training.spring.batch.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.training.spring.batch.common.FullReportListener;

@Configuration
public class JobConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(JobConfiguration.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private FullReportListener jobListener;

	@Bean
	public Job exportJob(final Step step1) {
		return jobBuilderFactory.get("job") //
				.incrementer(new RunIdIncrementer()) // job can be launched as many times as desired
				.start(step1) //
				.listener(jobListener) //
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1") //
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext)
							throws Exception {
						logger.info("Hello Spring batch !");
						return RepeatStatus.FINISHED;
					}
				}).build();
	}

}
