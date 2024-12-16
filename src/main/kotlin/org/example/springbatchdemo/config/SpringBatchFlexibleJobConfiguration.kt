package org.example.springbatchdemo.config

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher
import org.springframework.batch.core.repository.JobRepository
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.task.SimpleAsyncTaskExecutor

const val DEFAULT_CHUNK_SIZE: Int = 500

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
@Profile("flexible")
class SpringBatchFlexibleJobConfiguration (private val jobRepository: JobRepository) {

    @Bean(name = ["asyncJobLauncher"])
    @Throws(Exception::class)
    fun simpleJobLauncher(): JobLauncher {
        val jobLauncher = TaskExecutorJobLauncher()
        jobLauncher.setJobRepository(jobRepository)
        jobLauncher.setTaskExecutor(SimpleAsyncTaskExecutor())
        jobLauncher.afterPropertiesSet()
        return jobLauncher
    }
}