package fr.training.spring.library.batch.job;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import fr.training.spring.library.batch.common.FullReportListener;

@Configuration
public class JobConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(JobConfiguration.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@Autowired
	private FullReportListener jobListener;

	@Bean
	public Job exportJob(final Step exportStep /* injected by Spring*/) {
		return jobBuilderFactory.get("export-job") //
				.validator(new DefaultJobParametersValidator(new String[] { "output-file" }, new String[] {})) //
				.incrementer(new RunIdIncrementer()) // job can be launched as many times as desired
				.start(exportStep) //
				.listener(jobListener) //
				.build();
	}

	@Bean
	public Step exportStep(final JsonFileItemWriter<LibraryDto> exportWriter /* injected by Spring*/,
			final LibraryProcessor libraryProcessor /* injected by Spring*/) {
		return stepBuilderFactory.get("export-step").<Long, LibraryDto>chunk(10) //
				.reader(exportReader()) //
				.processor(libraryProcessor) //
				.writer(exportWriter) //
				.build();
	}

	@Bean
	public JdbcCursorItemReader<Long> exportReader() {
		final JdbcCursorItemReader<Long> reader = new JdbcCursorItemReader<Long>();
		reader.setDataSource(dataSource);
		reader.setSql("SELECT id  FROM library");
		reader.setRowMapper(new SingleColumnRowMapper<Long>());
		return reader;
	}

	@StepScope // Mandatory for using jobParameters
	@Bean
	public JsonFileItemWriter<LibraryDto> jsonFileItemWriter(
			@Value("#{jobParameters['output-file']}") final String fileName /* injected by Spring*/) {

		return new JsonFileItemWriterBuilder<LibraryDto>() //
				.jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>()) //
				.resource(new FileSystemResource(fileName)) //
				.name("LibraryJsonFileItemWriter") //
				.build();
	}

}