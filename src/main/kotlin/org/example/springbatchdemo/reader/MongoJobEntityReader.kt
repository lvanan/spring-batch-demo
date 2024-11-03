package org.example.springbatchdemo.reader

import org.example.springbatchdemo.config.DEFAULT_CHUNK_SIZE
import org.example.springbatchdemo.entity.EmployeeMongoEntity
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.data.MongoCursorItemReader
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component

@Component
@StepScope
class MongoJobEntityReader(
    mongoTemplate: MongoTemplate,
    @Value("#{jobParameters['role']}") roleName: String?
) : MongoCursorItemReader<EmployeeMongoEntity>() {

    private val COLLECTION_NAME_TO_READ: String = "employee"
    private val LIST_ID_QUERY_TEMPLATE: String = "{ 'role': '%s' }"

    init {
        setCollection(COLLECTION_NAME_TO_READ)
        setBatchSize(DEFAULT_CHUNK_SIZE)
        setTemplate(mongoTemplate)
        setQuery(String.format(LIST_ID_QUERY_TEMPLATE, roleName))
        setTargetType(EmployeeMongoEntity::class.java)
        setSort(HashMap())
    }
}