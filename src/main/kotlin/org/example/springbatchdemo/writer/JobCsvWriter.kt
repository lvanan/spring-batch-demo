package org.example.springbatchdemo.writer

import org.example.springbatchdemo.entity.ProcessedItemEntity
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.batch.core.annotation.AfterStep
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

class JobCsvWriter : ItemWriter<ProcessedItemEntity>, StepExecutionListener {
    private var stepExecution: StepExecution? = null

    @Throws(IOException::class)
    override fun write(chunk: Chunk<out ProcessedItemEntity?>) {

        FileOutputStream("output.csv").use { outputStream ->
            OutputStreamWriter(outputStream, StandardCharsets.UTF_8).use { writer ->

                chunk.items.stream().forEach { processedItemEntity -> writer.write(processedItemEntity!!.line) }
            }
        }
    }

    @BeforeStep
    @Throws(IOException::class)
    fun beforeStepExecution(stepExecution: StepExecution) {
        this.stepExecution = stepExecution
    }

    @AfterStep
    @Throws(IOException::class)
    fun afterStepExecution() {
    }
}