# Task Tracker CLI

A simple command-line app to track your tasks, written in plain Java with no external libraries. Tasks are saved in a `tasks.json` file in the current folder, which is created automatically if it doesn't exist.

Based on the [Task Tracker project](https://roadmap.sh/projects/task-tracker) from roadmap.sh.

## Task fields

Each task has an `id`, `description`, `status` (`todo`, `in-progress`, or `done`), `createdAt`, and `updatedAt`.

## Build and run

    javac *.java
    java Main <command> [arguments]

## Commands

    java Main add "Buy milk"
    java Main list