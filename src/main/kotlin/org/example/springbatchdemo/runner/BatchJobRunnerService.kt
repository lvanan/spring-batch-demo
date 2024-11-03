package org.example.springbatchdemo.runner

import org.springframework.batch.core.launch.JobLauncher
import org.springframework.stereotype.Service

@Service
class BatchJobRunnerService(jobLauncher: JobLauncher) {

    fun runSpringBatchJob(exportType: ExportType){

    }
}