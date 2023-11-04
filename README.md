# Java ORM Framework MicroBenchmark

## ORM Framework

ORM Frameworks:
- [JDBC](https://docs.oracle.com/en/database/oracle/oracle-database/23/jjdbc/introducing-JDBC.html)
- [MyBatis](https://mybatis.org/mybatis-3/zh/index.html)
- [Hibernate](https://hibernate.org)

Connection Pool:
- [HiKariCP](https://github.com/brettwooldridge/HikariCP)

## Data Source

> [MySQL 从零开始：08 番外：随机生成数据库数据](https://cloud.tencent.com/developer/article/1373196)

GitHub: [benkeen/generatedata](https://github.com/benkeen/generatedata)

Website: [Generate test data. Quickly.](https://generatedata.com/generator)

## Unit Test

JUnit 5 is the next generation of JUnit. The goal is to create an up-to-date foundation
for developer-side testing on the JVM. This includes focusing on Java 8 and above,
as well as enabling many different styles of testing.

Unit Test Cases:
- CRUD
    - [AddUnitTest](./src/test/java/com/blankspace/orm/AddUnitTest.java)
        - `addByJdbc()`
        - `addByMyBatis()`
        - `addByHibernate()`
    - [DeleteUnitTest](./src/test/java/com/blankspace/orm/DeleteUnitTest.java)
        - `deleteByJdbc()`
        - `deleteByMyBatis()`
        - `deleteByHibernate()`
    - [QueryUnitTest](./src/test/java/com/blankspace/orm/QueryUnitTest.java)
        - `queryByJdbc()`
        - `queryByMyBatis()`
        - `queryByHibernate()`
    - [UpdateUnitTest](./src/test/java/com/blankspace/orm/UpdateUnitTest.java)
        - `updateByJdbc()`
        - `updateByMyBatis()`
        - `updateByHibernate()`
- Framework
    - [UpdateUnitTest](./src/test/java/com/blankspace/orm/JdbcUnitTest.java)
        - `addByJdbc()` (Order 1)
        - `updateByJdbc()` (Order 2)
        - `deleteByJdbc()` (Order 3)
        - `queryByJdbc()` (Order 4)
    - [MyBatisUnitTest](./src/test/java/com/blankspace/orm/MyBatisUnitTest.java)
        - `addByMyBatis()` (Order 1)
        - `updateByMyBatis()` (Order 2)
        - `deleteByMyBatis()` (Order 3)
        - `queryByMyBatis()` (Order 4)
    - [HibernateUnitTest](./src/test/java/com/blankspace/orm/HibernateUnitTest.java)
        - `addByHibernate()` (Order 1)
        - `updateByHibernate()` (Order 2)
        - `deleteByHibernate()` (Order 3)
        - `queryByHibernate()` (Order 4)

## Performance Test

Java Microbenchmark Harness (JMH) is a Java harness for building, running,
and analysing nano/micro/milli/macro benchmarks written in Java and other languages targeting the JVM.

Performance Test Cases:
- [ORMBaseBenchmark](./src/test/java/com/blankspace/orm/ORMBaseBenchmark.java)
    - [ORMQueryBenchmark](./src/test/java/com/blankspace/orm/ORMQueryBenchmark.java)

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

| Benchmark | Mode | Cnt | Score | Error | Units |
|:----:|:------:|:----:|:----:|:----:|:----:|
| ORMQueryBenchmark.measureQuery | jdbc | avgt | 5 | 0.133 | ±0.009 | ms/op |
| ORMQueryBenchmark.measureQuery | mybatis | avgt | 5 | 0.302 | ±0.027 | ms/op |
| ORMQueryBenchmark.measureQuery | hibernate | avgt | 5 | 0.407 | ±0.003 | ms/op |
