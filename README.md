# spring-boot-demo

A Spring Boot v4 app that reproduces [an issue with the logbook starter](https://github.com/zalando/logbook/issues/2177).

To reproduce the issue, run `./gradlew bootRun` and observe the exception that occurs at startup. If the logbook
starter is removed from `build.gradle`, the exception does not occur.
