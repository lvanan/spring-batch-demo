package org.example.springbatchdemo.controller

import org.example.springbatchdemo.runner.BatchJobRunnerService
import org.example.springbatchdemo.runner.ExportType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JobController(private val batchJobRunnerService: BatchJobRunnerService) {


    @GetMapping("/export/csv")
    fun triggerCsvExport(): ResponseEntity<String> {
        batchJobRunnerService.runSpringBatchJob(ExportType.CSV, "Engineer")
        return ResponseEntity.ok("true");
    }
}