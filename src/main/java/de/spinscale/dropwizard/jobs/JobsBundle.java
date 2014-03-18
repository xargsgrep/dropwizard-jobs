package de.spinscale.dropwizard.jobs;

import io.dropwizard.Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.codahale.metrics.SharedMetricRegistries;

public class JobsBundle implements Bundle {

	private String scanURL = null;

	@Override
	public void initialize(Bootstrap<?> bootstrap) {
		// add shared metrics registry to be used by Jobs, since defaultRegistry
		// has been removed
		SharedMetricRegistries.add(Job.DROPWIZARD_JOBS_KEY,
				bootstrap.getMetricRegistry());
	}

	public JobsBundle() {
	}

	public JobsBundle(String scanURL) {
		this.scanURL = scanURL;
	}

	@Override
	public void run(Environment environment) {
		JobManager jobManager = new JobManager(scanURL);
		environment.lifecycle().manage(jobManager);
	}

}
