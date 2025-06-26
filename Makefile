# Makefile for Spring Boot app using Gradle

.PHONY: build run test clean docker

build:
	./gradlew build

run:
	./gradlew bootRun

test:
	./gradlew test

clean:
	./gradlew clean
