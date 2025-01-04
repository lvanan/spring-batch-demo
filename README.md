This repository contains examples for Spring batch csv exporter with the following requirements:

- Kotlin 1.9
- Spring boot 3.3.3
- Spring batch
- Gradle

Databases in use:

- For spring batch job repository - Postgres 17.2
- Mongo - to read the data

Spring batch is illustrated in Mongo collection > csv export with the following setup:

- Spring batch beans configuration in SpringBatchJobConfiguration. You define beans (reader, writer, processor)
  in the configuration file, and Spring manages it.
- Flexible export - beans are created via the code in BatchFlexibleJobRunnerService.
  This is needed when the exact implementation of spring batch beans shall be decided in the runtime.
  For example, when the writer implementation depends on the request parameter.

To run export:

- docker compose up from "spring-batch-demo" path to run databases
- ./gradlew bootRun to run the project
- trigger GET requests
    - /export?exportType=CSV for export
    - flexible/export?exportType=CSV for flexible export
- find export result in output.csv in "spring-batch-demo" folder

To track metrics, traces and logs via openTelemetry:

- run otel-collector:
  docker run --name otel-collector \
  -p 4317:4317 -p 4318:4318 -p 55681:55681 \
  otel/opentelemetry-collector-contrib:latest
- download java agent and add it to the spring-boot-demo folder:
  curl -LJO https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.11.0/opentelemetry-javaagent.jar
- ./gradlew bootRun to run the project