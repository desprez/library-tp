package fr.training.spring.library.batch.common;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

public class DailyJobTimestamper implements JobParametersIncrementer {
	/**
	 * Increment the current.date parameter.
	 */
	@Override
	public JobParameters getNext(final JobParameters parameters) {
		Date today = new Date();
		if (parameters != null && !parameters.isEmpty()) {
			final Date oldDate = parameters.getDate("current.date", new Date());
			today = DateUtils.addDays(oldDate, 1);
		}
		return new JobParametersBuilder().addDate("current.date", today).toJobParameters();
	}
}