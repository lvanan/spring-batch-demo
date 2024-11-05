package org.example.springbatchdemo.writer

import org.example.springbatchdemo.entity.ProcessedItemEntity
import org.example.springbatchdemo.runner.ExportType
import org.springframework.batch.item.ItemWriter

class JobWriterFactory {

    companion object {
        fun getJobWriter(exportType: ExportType): ItemWriter<ProcessedItemEntity> {
            when (exportType) {
                ExportType.CSV -> return JobCsvWriter();
                else -> throw Exception("unsupported export type for the job writer")
            }
        }
    }
}