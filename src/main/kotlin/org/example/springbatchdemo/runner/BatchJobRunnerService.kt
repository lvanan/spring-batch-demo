package org.example.springbatchdemo.runner

import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service


@Service
@Profile("flexible")
class BatchJobRunnerService(
    private val jobLauncher: JobLauncher, private val job: Job
) {

    private val logger = KotlinLogging.logger {}

    fun runSpringBatchJob(exportType: ExportType, role: String) {
        val jobParameters = JobParametersBuilder().addString("role", role)
            .addLong("time", System.currentTimeMillis()) // Ensure uniqueness
            .toJobParameters()

        val jobExecution = jobLauncher.run(job, jobParameters)

        logger.info {
            "Export $exportType job submitted with id: ${jobExecution.id}, jobId: ${jobExecution.jobId}, " +
                    "jobName: ${jobExecution.jobInstance.jobName}, instanceId: ${jobExecution.jobInstance.id}"
        }
    }
}