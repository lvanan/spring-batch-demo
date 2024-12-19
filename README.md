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