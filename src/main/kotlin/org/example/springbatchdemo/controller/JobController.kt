package org.example.springbatchdemo.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JobController {

    @GetMapping("/export/csv")
    fun triggerCsvExport(): ResponseEntity<String> {
        return ResponseEntity.ok("true");
    }
}