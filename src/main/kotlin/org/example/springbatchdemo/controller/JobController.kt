package org.example.springbatchdemo.controller

import org.example.springbatchdemo.runner.BatchJobRunnerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JobController(batchJobRunnerService: BatchJobRunnerService) {


    @GetMapping("/export/csv")
    fun triggerCsvExport(): ResponseEntity<String> {
        return ResponseEntity.ok("true");
    }
}