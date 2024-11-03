package org.example.springbatchdemo.processor

import org.example.springbatchdemo.entity.EmployeeMongoEntity
import org.example.springbatchdemo.entity.ProcessedItemEntity
import org.springframework.batch.item.ItemProcessor

class JobItemProcessor : ItemProcessor<EmployeeMongoEntity, ProcessedItemEntity> {
    override fun process(mongoEntity: EmployeeMongoEntity): ProcessedItemEntity {

        val csvLine: String = mongoEntity.age.toString() + ", " + mongoEntity.name + ", " + mongoEntity.role

        return ProcessedItemEntity(null, csvLine)
    }

    companion object {
        private const val LIST_OBJECT_DELIMITER = ";"
    }
}