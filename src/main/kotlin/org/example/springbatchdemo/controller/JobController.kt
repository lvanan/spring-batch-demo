package org.example.springbatchdemo.controller

import org.example.springbatchdemo.runner.BatchFlexibleJobRunnerService
import org.example.springbatchdemo.runner.BatchJobRunnerService
import org.example.springbatchdemo.runner.ExportType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class JobController(
    private val batchFlexibleJobRunnerService: BatchFlexibleJobRunnerService,
    private val batchJobRunnerService: BatchJobRunnerService
) {

    @GetMapping("/export")
    fun triggerCsvExport(@RequestParam exportType: ExportType): ResponseEntity<String> {
        batchFlexibleJobRunnerService.runSpringBatchJob(exportType, "Engineer")
        return ResponseEntity.ok("export triggered");
    }

    @GetMapping("flexible/export")
    fun triggerCsvFlexibleExport(@RequestParam exportType: ExportType): ResponseEntity<String> {
        batchJobRunnerService.runSpringBatchJob(exportType, "Engineer")
        return ResponseEntity.ok("export triggered");
    }
}