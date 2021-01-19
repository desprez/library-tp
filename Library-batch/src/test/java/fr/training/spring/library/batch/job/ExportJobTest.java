package fr.training.spring.library.batch.job;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileSystemUtils;

import fr.training.spring.library.LibraryBatchApplication;

@SpringBootTest(classes = { LibraryBatchApplication.class, JobConfiguration.class })
public class ExportJobTest {

	private static final Logger LOG = LoggerFactory.getLogger(ExportJobTest.class);

	/** directory for temporary test files */
	private static final String TMP_DIR = "./tmp";

	/**
	 * orderExportJob of type Job
	 */
	@Autowired
	@Qualifier("exportJob")
	private Job exportJob;

	/**
	 * jobLauncher of type JobLauncher
	 */
	@Autowired
	private JobLauncher jobLauncher;

	/**
	 * @throws Exception
	 */
	@BeforeEach
	public void setup() throws Exception {

		// setup test data
		LOG.debug("Delete tmp directory");
		FileSystemUtils.deleteRecursively(new File(TMP_DIR));
	}

	/**
	 * @throws Exception thrown by JobLauncherTestUtils
	 */
	@Test
	public void exportLibraryJob_should_success() throws Exception {
		// Given
		final File targetFile = new File(TMP_DIR + "/library.json");
		final JobParametersBuilder jobParameterBuilder = new JobParametersBuilder();
		jobParameterBuilder.addString("output-file", targetFile.getAbsolutePath());
		// When
		final JobExecution jobExecution = getJobLauncherTestUtils(exportJob)
				.launchJob(jobParameterBuilder.toJobParameters());
		// Then
		assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
		assertThat(targetFile.exists()).isTrue();
	}

	/**
	 * @param job
	 * @return
	 */
	private JobLauncherTestUtils getJobLauncherTestUtils(final Job job) {
		final JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
		jobLauncherTestUtils.setJob(job);
		jobLauncherTestUtils.setJobLauncher(jobLauncher);
		return jobLauncherTestUtils;
	}
}