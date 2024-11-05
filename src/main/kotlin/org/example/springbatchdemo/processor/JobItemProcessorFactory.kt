package org.example.springbatchdemo.processor

import org.example.springbatchdemo.entity.EmployeeMongoEntity
import org.example.springbatchdemo.entity.ProcessedItemEntity
import org.example.springbatchdemo.runner.ExportType
import org.springframework.batch.item.ItemProcessor

class JobItemProcessorFactory {

    companion object {
        fun getJobItemProcessor(exportType: ExportType): ItemProcessor<EmployeeMongoEntity, ProcessedItemEntity> {
            return when (exportType) {
                ExportType.CSV -> JobItemCsvProcessor()
                else -> throw Exception("unsupported export type for the job processor")
            }
        }
    }
}
