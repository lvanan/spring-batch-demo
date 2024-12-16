This repository contains example for Spring batch with the following requirements:

- Kotlin 1.9
- Spring boot 3.3.3
- Spring batch 5.0.1

Databases in use:

- For spring batch job repository - Postgres 17.2
- Mongo - to read the data

Spring batch is illustrated in Mongo collection > csv export with the following setup:

- Spring batch beans configuration in SpringBatchJobConfiguration. You define beans (reader, writer, processor)
  in the configuration file, and spring manages it.
- Flexible export - beans are created manually in BatchFlexibleJobRunnerService. This is needed when the exact implementation
  of spring batch beans shall be decided in the runtime. For example, when reader implementation depends on the request parameter.

To run export