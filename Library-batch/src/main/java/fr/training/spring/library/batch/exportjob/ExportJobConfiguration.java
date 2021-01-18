package fr.training.spring.library.batch.exportjob;

import java.io.IOException;
import java.io.Writer;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.SingleColumnRowMapper;

@Configuration
public class ExportJobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@Bean
	public Step exportStep(final FlatFileItemWriter<BookDto> exportWriter, final BookProcessor customerProcessor) {
		return stepBuilderFactory.get("export-step").<String, BookDto>chunk(10) //
				.reader(exportReader()) //
				.processor(customerProcessor) //
				.writer(exportWriter) //
				.build();
	}

	@Bean(name = "exportJob")
	public Job exportJob(final Step exportStep) {
		return jobBuilderFactory.get("export-job") //
				.validator(new DefaultJobParametersValidator(new String[] { "output-file" }, new String[] {})) //
				.incrementer(new RunIdIncrementer()) //
				.flow(exportStep) //
				.end() //
				.build();
	}


	@Bean
	public JdbcCursorItemReader<String> exportReader() {
		final JdbcCursorItemReader<String> reader = new JdbcCursorItemReader<String>();
		reader.setDataSource(dataSource);
		reader.setSql("SELECT id  FROM Customer");
		reader.setRowMapper(new SingleColumnRowMapper<String>());
		return reader;
	}

	@StepScope // Mandatory for using jobParameters
	@Bean
	public FlatFileItemWriter<BookDto> exportWriter(
			@Value("#{jobParameters['output-file']}") final String outputFile) {
		final FlatFileItemWriter<BookDto> writer = new FlatFileItemWriter<BookDto>();
		writer.setResource(new FileSystemResource(outputFile));

		final DelimitedLineAggregator<BookDto> lineAggregator = new DelimitedLineAggregator<BookDto>();

		final BeanWrapperFieldExtractor<BookDto> fieldExtractor = new BeanWrapperFieldExtractor<BookDto>();
		fieldExtractor.setNames(new String[] { "id", "name", "password", "email", "street", "country", "postalCode" });
		lineAggregator.setFieldExtractor(fieldExtractor);
		lineAggregator.setDelimiter(";");

		writer.setLineAggregator(lineAggregator);
		writer.setHeaderCallback(new FlatFileHeaderCallback() {
			@Override
			public void writeHeader(final Writer writer) throws IOException {
				writer.write("id;name;password;email;street;city;country;postalCode");
			}
		});
		return writer;
	}
}