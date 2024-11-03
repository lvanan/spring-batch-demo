package org.example.springbatchdemo.processor

import org.example.springbatchdemo.entity.EmployeeMongoEntity
import org.example.springbatchdemo.entity.ProcessedItemEntity
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
@StepScope
class JobItemProcessor : ItemProcessor<EmployeeMongoEntity, ProcessedItemEntity> {
    override fun process(mongoEntity: EmployeeMongoEntity): ProcessedItemEntity {

        val csvLine: String = mongoEntity.age.toString() + ", " + mongoEntity.name + ", " + mongoEntity.role

        return ProcessedItemEntity(null, csvLine)
    }

    companion object {
        private const val LIST_OBJECT_DELIMITER = ";"
    }
}