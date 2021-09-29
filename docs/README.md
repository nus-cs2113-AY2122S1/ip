# User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).

* Quick Start
* Features
  * Adding a new task
    * To-do tasks: `todo`
    * Deadline tasks: `deadline`
    * Event tasks: `event`
  * Marking a task as done: `done`
  * Listing all tasks: `list`
  * Searching for a specific task: `find`
  * Deleting a task: `delete`
  * Exiting the program: `bye`

##Quick Start
 1. Ensure you have Java 11 or above installed in your computer.
 2. Download the latest version of the Duke JAR file from github.
 3. Copy the JAR file to a folder where you intend to run it from.
 4. Open the command line and navigate to that folder, before running the command `java -jar ip.jar`
 5. Type your commands in the command line and press Enter to execute it
    * Example:
      * `deadline Assignment /by 12-11-2021 2359`: Adds a deadline task named Assignment to the task list.
      * `list`: Lists all tasks in the task list.
      * `delete 1`: Deletes the entry of index 1 in the task list.
 6. Refer to the _Features_ section below for more details for each command.
## Features 

### To-do tasks: `todo`

Adds a to-do task to the task list.

* Format: `todo TASK_NAME`
* Example: `todo Finish Assignment 1`

### Deadline tasks: `deadline`

Adds a deadline task to the task list.

* Format: `deadline TASK_NAME /by DD-MM-YYYY`
* Example: `deadline Submit final draft /by 22-1-2020`

### Event tasks: `event`

Adds an event task to the task list.

* Format: `event TASK_NAME /at DD-MM-YYYY`
* Example: `event Group meeting /at 22-1-2020`

### Deleting a task: `delete`

Deletes a task from the task list.

* Format: `delete TASK_INDEX`
  * TASK_INDEX refers to the index of the task that the user wants to delete.
  * TASK_INDEX must be a positive integer 1, 2, 3, etc.
* Example: `delete 1` deletes the first index of the task list.

### Finding tasks: `find`

Find tasks stored in the task list by giving a keyword.

* Format: `find SEARCH_TERM`
    * `SEARCH_TERM` is case-sensitive.
* Example: `find homework`

### Marking a task as done: `done`

Marks the task as done.

* Format: `done TASK_INDEX`
    * TASK_INDEX refers to the index of the task that the user wants to mark as done.
    * TASK_INDEX must be a positive integer 1, 2, 3, etc.
* Example: `done 1` marks the first index of the task list as done.

### Listing all tasks: `list`

Lists all tasks currently stored in the task list.

* Format: `list`


### Exiting the program: `bye`

Exits the program.

* Format: `bye`

## Command Prompts

Action | Format & Example
-------|-----------------
Add a to-do task| `todo TASK_NAME` (eg. `todo Finish assignment 1`)
Add a deadline task| `deadline deadline TASK_NAME /by DD-MM-YYYY` (eg. `deadline Submit final draft /by 22-1-2020`)
Add a event task|`event TASK_NAME /at DD-MM-YYYY` (eg. `event Group meeting /at 22-1-2020`)
Deleting a task|`delete TASK_INDEX` (eg. `delete 1`)
Finding tasks|`find SEARCH_TERM` (eg. `find homework`)
Mark a task as done|`done TASK_INDEX` (eg. `done 1`)
Listing all tasks|`list`
Exiting the program|`bye`