# User Guide

Duke is a CLI task-managing app to help you and make your life easier.

##Quick Start
1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest Duke JAR file from github.
3. Locate the JAR file into a folder which you will run it from.
4. Navigate to this folder using CLI and run the command `java -jar ip.jar`
5. You can now start using Duke by typing the features command directly!

## Features

### Showing all tasks: `list`

Shows all tasks in the task list.

* Format: `list`

### Finding a task: `find`

Finds a task in the task list.

* Format: `find TASK`
* Example: `find study`

### Marking a task as done: `done`

Marks the task as done.

* Format: `done TASK_INDEX`
* TASK_INDEX is a positive number which refer to the task.
* Example: `done 1`

### Deleting a task: `delete`

Deletes a task from the task list.

* Format: `delete TASK_INDEX`
* TASK_INDEX is a positive number which refer to the task.
* Example: `delete 1`

### Recording a deadline task: `deadline`

Adds a deadline task into the task list.

* Format: `deadline TASK_NAME /by TASK_TIME`
* Example: `deadline sleep /by 11p.m.`

### Recording a event task: `event`

Adds a event task into the task list.

* Format: `event TASK_NAME /at TASK_TIME`
* Example: `event eat /at 11a.m.`

### Recording a todo task: `todo`

Adds a todo task into the task list.

* Format: `todo TASK_NAME`
* Example: `todo study`

### Exiting the program: `bye`

Exits the program.

* Format: `bye`

## Command Summary

Action | Format & Example
-------|-----------------
Show all tasks|`list`
Find a task|`find TASK` (eg. `find work`)
Mark a task as done|`done TASK_INDEX` (eg. `done 1`)
Delete a task|`delete TASK_INDEX` (eg. `delete 1`)
Record a deadline task| `deadline deadline TASK_NAME /by TASK_TIME` (eg. `deadline sleep /by 11p.m.`)
Record a event task|`event TASK_NAME /at TASK_TIME` (eg. `event eat /at 11a.m.`)
Record a todo task| `todo TASK_NAME` (eg. `todo study`)
Exit the program|`bye`