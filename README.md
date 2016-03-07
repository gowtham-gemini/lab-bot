# Project Guidelines

Follow the steps provided to setup your environment, build, test and deploy

## Dev Environment Setup

Once you have cloned your code to your local, ensure you have gradle.

### Gradle installation

Use sdkman.io

```
sdk use gradle
```

### Eclipse Support

navigate to the cloned directory execute the following commands

```
gradle clean eclipse
```

### Additional Configurations in Eclipse

* Enable YAML editor
* PMD
* Checkstyle
* Gradle Build Tool
* AnyText

### TODO: Should explain how to configure Checkstyle/PMD

### TODO: Debug the application via IDE

## Build

```
gradle build distDocker
```

###  Verifying Build

There are two builds happening, jar & docker image

build/libs/*.jar is the location where the new build is available

Docker images can be verified by the following command

```
docker images
```

## Running the App

Install Java 8

java -jar lab-bot.jar

## Report List

/volumeReports - gives repors about volume usage

/flavoeReports -  gives repors about flavor usage in all region

/accountReports - gives repors about account resource usage
