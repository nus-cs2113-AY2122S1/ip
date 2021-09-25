# User Guide

Duke is a desktop application for **keeping track of tasks** via a **Command Line Interface (CLI)**.

* Quick start
* Features
  * Adding a todo: `todo`
  * Adding a deadline: `deadline`
  * Adding an event: `event`
  * Listing all tasks: `list`
  * Checking off a task: `done`
  * Finding a task by keyword: `find`
  * Deleting a task: `delete`
  * Exiting the program: `bye`
* Command Summary

## Quick start
1. Ensure you have **Java 11** or above installed on your computer.
2. Download the latest Duke.jar from [here](https://github.com/jerrelllzw/ip/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Duke.
4. Open your command prompt and navigate to your **home folder** as chosen in step 3.
5. Type `java -jar ip.jar` in the command prompt and press enter to run Duke.
6. Type a command in Duke's command box and press enter to execute it.
7. Refer to the **Features** below for details of each command.

## Features 

### Adding a todo: `todo`

Adds a todo to the task list.

Format: `todo TASK_DESCRIPTION`

Example:
`todo read book` Adds the task "read book" to the task list.
```
____________________________________________________________
 Got it. I've added this task:
  [T][ ] read book
 Now you have 1 tasks in the list.
____________________________________________________________
```

### Adding a deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline TASK_DESCRIPTION /by DEADLINE`

Example:
`deadline finish essay /by Mon 9pm` Adds the task "finish essay" with a deadline "Mon 9pm) to the task list.
```
____________________________________________________________
 Got it. I've added this task:
  [D][ ] finish essay (by: Mon 9pm)
 Now you have 1 tasks in the list.
____________________________________________________________
```

### Adding an event: event

Adds an event to the task list.

### Listing all tasks: list

Shows a list of all tasks in the task list.

### Checking off a task: done

Marks a task as done.

### Finding a task by keyword: find

Finds tasks with descriptions that contain the keyword.

### Deleting a task: delete

Deletes a task in the task list.

### Exiting the program: bye

Exits the program.

## Command Summary