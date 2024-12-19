package org.example.springbatchdemo.processor

import org.example.springbatchdemo.entity.EmployeeMongoEntity
import org.example.springbatchdemo.entity.ProcessedItemEntity
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
@StepScope
class JobItemCsvProcessor : ItemProcessor<EmployeeMongoEntity, ProcessedItemEntity> {
    override fun process(mongoEntity: EmployeeMongoEntity): ProcessedItemEntity {
        return ProcessedItemEntity(null, transformationWithComma(mongoEntity.age, mongoEntity.name, mongoEntity.role))
    }

    private fun transformationWithComma(age: Int, name: String, role: String): String {
        return "$age, $name, $role"
    }

    // use this transformation of necessary
    private fun transformationWithRoleChange(age: Int, name: String, role: String): String {
        // promote each engineer to the manager
        if (role == "Engineer") {
            return "$age, $name, Manager"
        }

        return "$age, $name, $role"
    }
}