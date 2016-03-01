#!/bin/bash

# Important configurations
# Database connection host, localhost is set for development, default value is set to `db`
# `db` is set for helping container linking using the link name as db
export DB_HOST=localhost

# The port of postgres db service, if not provided will use, 5432 as default.
# The environment variable DB_PORT should not be used as it causes a conflict with `db` link
export DB_POSTGRES_PORT=5432

# The database to connect can be varying for environment, postgres is set to default.
# postgres is default as it is populated in most environment during the initdb
export DB_POSTGRES_DB=postgres

# Schema is considered optional, default will be set to public and if needed it can vary.
export DB_POSTGRES_SCHEMA=public

# Access credentials, if used with container linking using `db` as the link.
# The following will be auto populated.
export DB_POSTGRES_USER=postgres
export DB_POSTGRES_PASSWORD=

# Optional Parameters

# Server port to listen for tomcat, default 8080
export SERVER_PORT=8080

# Web application session timeout, default 60 minutes
export SESSION_TIMEOUT=60

# Backend security admin
export BACKEND_USERNAME=admin
export BACKEND_PASSWORD=admin