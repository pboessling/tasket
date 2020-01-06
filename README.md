# Tasket

A simple task management tool.

Work in progress...

## Requirements

* JDK 8

## Local Development

Run from source via Spring-Boot-Maven-Plugin:

```
./mvnw spring-boot:run
```

Or build and run from packaged JAR:

```
./mvnw package && java -jar target/tasket-1.0-SNAPSHOT.jar
```

## Installation

TBD

## Configuration

TBD

## Usage

TBD

### URL Patterns

Daily Log:
```
/dailylog/2020-01-06
```

Weekly Log:
```
/weeklylog/2020-W01
```

Monthly Log:
``` 
/monthlylog/2020-01
```

Yearly Log:
``` 
/yearlylog/2019
```

## Changelog

### v1.0

* Initial release.

## License

[MIT](LICENSE)

## Author Information

Created by [Philippe Bößling](https://www.gihub.com/pboessling).