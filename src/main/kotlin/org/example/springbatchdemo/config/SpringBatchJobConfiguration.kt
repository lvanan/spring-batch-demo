package org.example.springbatchdemo.config

import org.example.springbatchdemo.entity.EmployeeMongoEntity
import org.example.springbatchdemo.entity.ProcessedItemEntity
import org.example.springbatchdemo.processor.JobItemCsvProcessor
import org.example.springbatchdemo.reader.MongoJobEntityReader
import org.example.springbatchdemo.writer.JobCsvWriter
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.transaction.PlatformTransactionManager
import java.util.concurrent.ThreadPoolExecutor


@Configuration
@Profile("flexible")
@EnableBatchProcessing
class SpringBatchJobConfiguration(private val jobRepository: JobRepository, private val transactionManager: PlatformTransactionManager) {

    @Bean
    @Throws(Exception::class)
    fun job(step: Step): Job {
        return JobBuilder("export_job", jobRepository).start(step).build()
    }

    @Bean
    @Async
    @Throws(Exception::class)
    fun step(mongoJobEntityReader: MongoJobEntityReader): Step {
        return StepBuilder("myStep", jobRepository)
            .chunk<EmployeeMongoEntity, ProcessedItemEntity>(10, transactionManager)
            .writer(JobCsvWriter())
            .reader(mongoJobEntityReader)
            .processor(JobItemCsvProcessor())
            .build();
    }

    @Bean
    fun taskExecutor(): TaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 64
        executor.maxPoolSize = 64
        executor.queueCapacity = 64
        executor.setRejectedExecutionHandler(ThreadPoolExecutor.CallerRunsPolicy())
        executor.setThreadNamePrefix("MultiThreaded-")
        return executor
    }
}