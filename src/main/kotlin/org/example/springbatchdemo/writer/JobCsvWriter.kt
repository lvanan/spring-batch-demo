package org.example.springbatchdemo.writer

import org.example.springbatchdemo.entity.ProcessedItemEntity
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.core.annotation.AfterStep
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

class JobCsvWriter : ItemWriter<ProcessedItemEntity>, StepExecutionListener {
    private var stepExecution: StepExecution? = null
    private var outputStream: FileOutputStream? = null
    private var writer: OutputStreamWriter? = null

    @BeforeStep
    @Throws(IOException::class)
    fun beforeStepExecution(stepExecution: StepExecution) {
        this.stepExecution = stepExecution
        outputStream = FileOutputStream("output.csv")
        writer = OutputStreamWriter(outputStream, StandardCharsets.UTF_8)
    }

    @Throws(IOException::class)
    override fun write(chunk: Chunk<out ProcessedItemEntity?>) {
        writer?.let {
            chunk.items.forEach { processedItemEntity ->
                it.write(processedItemEntity!!.line)
            }
        }
    }

    @AfterStep
    @Throws(IOException::class)
    fun afterStepExecution() {
        writer?.close()
        outputStream?.close()
    }
}
