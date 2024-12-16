package org.example.springbatchdemo.runner

import mu.KotlinLogging
import org.example.springbatchdemo.entity.EmployeeMongoEntity
import org.example.springbatchdemo.entity.ProcessedItemEntity
import org.example.springbatchdemo.processor.JobItemProcessorFactory
import org.example.springbatchdemo.reader.MongoJobEntityReader
import org.example.springbatchdemo.writer.JobWriterFactory
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager

@Service
@Profile("flexible")
class BatchFlexibleJobRunnerService(
    private val jobLauncher: JobLauncher, private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
    private val mongoJobEntityReader: MongoJobEntityReader,
) {

    private val logger = KotlinLogging.logger {}

    fun runSpringBatchJob(exportType: ExportType, role: String) {
        val jobParameters = JobParametersBuilder().addString("role", role)
            .addLong("time", System.currentTimeMillis()) // Ensure uniqueness
            .toJobParameters()

        val step: Step = StepBuilder("csv_export", jobRepository)
            .chunk<EmployeeMongoEntity, ProcessedItemEntity>(500, transactionManager)
            .reader(mongoJobEntityReader)
            .processor(JobItemProcessorFactory.getJobItemProcessor(ExportType.CSV))
            .writer(JobWriterFactory.getJobWriter(ExportType.CSV))
            .build()

        val job = JobBuilder("csv job", jobRepository).start(step).build()
        val jobExecution = jobLauncher.run(job, jobParameters)
        logger.info {
            "Flexible export $exportType job submitted with id: ${jobExecution.id}, jobId: ${jobExecution.jobId}, " +
                    "jobName: ${jobExecution.jobInstance.jobName}, instanceId: ${jobExecution.jobInstance.id}"
        }
    }
}