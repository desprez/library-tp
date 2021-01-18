package fr.training.spring.library.batch.importjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import fr.training.spring.library.batch.common.FullReportListener;
import fr.training.spring.library.domain.library.Address;
import fr.training.spring.library.domain.library.Director;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.LibraryRepository;

@Configuration
public class ImportJobConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(ImportJobConfiguration.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private FullReportListener jobListener;

	@Autowired
	private LibraryRepository libraryRepository;

	@Bean
	public Job importJob(final Step importStep) {
		return jobBuilderFactory.get("import-job") //
				.incrementer(new RunIdIncrementer()) // job can be launched as many times as desired
				.start(importStep) //
				.listener(jobListener) //
				.build();
	}

	@Bean
	public Step importStep() {
		return stepBuilderFactory.get("import-step") //
				.<LibraryDto, Library>chunk(10) //
				.reader(importReader(null)) //
				.processor(importProcessor()) //
				.writer(importWriter()) //
				.build();
	}

	@StepScope // Mandatory for using jobParameters
	@Bean
	public FlatFileItemReader<LibraryDto> importReader(
			@Value("#{jobParameters['input-file']}") final String inputFile) {
		final FlatFileItemReader<LibraryDto> reader = new FlatFileItemReader<LibraryDto>();
		final DefaultLineMapper<LibraryDto> lineMapper = new DefaultLineMapper<LibraryDto>();

		final DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(";");
		tokenizer.setNames(new String[] { "type", "addressNumber", "addressStreet", "addressPostalCode", "addressCity",
				"directorSurname", "directorName" });
		lineMapper.setLineTokenizer(tokenizer);

		final BeanWrapperFieldSetMapper<LibraryDto> fieldSetMapper = new BeanWrapperFieldSetMapper<LibraryDto>();
		fieldSetMapper.setTargetType(LibraryDto.class);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		reader.setResource(new FileSystemResource(inputFile));
		reader.setLineMapper(lineMapper);
		reader.setLinesToSkip(1);
		return reader;
	}

	private ItemProcessor<LibraryDto, Library> importProcessor() {
		return new ItemProcessor<LibraryDto, Library>() {
			@Override
			public Library process(final LibraryDto libraryDto) throws Exception {

				final Address address = new Address(libraryDto.getAddressNumber(), libraryDto.getAddressStreet(),
						libraryDto.getAddressPostalCode(), libraryDto.getAddressCity());
				final Director director = new Director(libraryDto.getDirectorSurname(), libraryDto.getDirectorName());
				final Library library = new Library(null, libraryDto.getType(), address, director, null);

				logger.info(library.toString());
				return library;
			}
		};
	}

	@Bean
	public ItemWriterAdapter<Library> importWriter() {
		final ItemWriterAdapter<Library> writer = new ItemWriterAdapter<Library>();
		writer.setTargetObject(libraryRepository);
		writer.setTargetMethod("save");
		return writer;
	}
}
