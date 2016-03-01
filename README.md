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

Application needs following properties to run

The following environment variables are needed to run, if not found it will choose the default value given below.

* DB_HOST=db
* DB_PORT=3306
* DB_SCHEMA=test
* DB_USER=root
* DB_PASSWORD=

### In local environment

If we need to run the app in the local, you can populate the environment variables and export them. Simple way is to edit the dev-setup.sh use it. There is a default example-setup.sh file, which has all the environment varibales needed and offically documented.

```
cp example-setup.sh dev-setup.sh
vi dev-setup.sh
source dev-setup.sh
```

You can also make a host entry /etc/hosts with 127.0.0.1 mapping to `db`

### In dockerized environment

Running with linked hosts

```
docker run -d --name mydb -e MYSQL_ROOT_PASSWORD=password  mariadb
docker run -d --name conflux -e DB_PASSWORD=password  --link mydb:db -e DB_SCHEMA=test -p 8010:8080 conflux
```

Running with non linked approach
```
docker run -d --name conflux -e DB_USER=appuser -e DB_PASSWORD=password  DB_HOST=db.example.com -e DB_SCHEMA=test -p 8010:8080 conflux
```

### Publishing the build

### Using the published build for the production deployment

