package org.example.springbatchdemo.runner

import org.example.springbatchdemo.entity.EmployeeMongoEntity
import org.example.springbatchdemo.entity.ProcessedItemEntity
import org.example.springbatchdemo.processor.JobItemProcessor
import org.example.springbatchdemo.reader.MongoJobEntityReader
import org.example.springbatchdemo.writer.JobCsvWriter
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager

@Service
class BatchJobRunnerService(
    private val jobLauncher: JobLauncher, private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
    private val mongoJobEntityReader: MongoJobEntityReader,
    private val jobItemProcessor: JobItemProcessor
) {

    fun runSpringBatchJob(exportType: ExportType, role: String) {
        val jobParameters = JobParametersBuilder().addString("role", role)
            .addLong("time", System.currentTimeMillis()) // Ensure uniqueness
            .toJobParameters()

        val step: Step = StepBuilder("csv_export", jobRepository)
            .chunk<EmployeeMongoEntity, ProcessedItemEntity>(500, transactionManager)
            .reader(mongoJobEntityReader)
            .processor(jobItemProcessor)
            .writer(JobCsvWriter())
            .build()

        val job = JobBuilder("csv job", jobRepository).start(step).build()
        val jobExecution = jobLauncher.run(job, jobParameters)
        println(
            "Update display job submitted with id: {}, jobId: {}, instance.getJobName: {}," + " instance.getId: {}".format(
                jobExecution.id,
                jobExecution.jobId,
                jobExecution.jobInstance.jobName,
                jobExecution.jobInstance.id
            )
        )
    }
}