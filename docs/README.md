# User Guide

Karlett is a desktop app for keeping track of task lists, optimized for use via a Command Line Interface (CLI).

## Features 

> Notes about the command format:

> 1. Words in UPPER_CASE are the parameters to be supplied by the user.
> e.g. in add n/NAME, NAME is a parameter which can be used as add n/John Doe.

> 2. Extraneous parameters for commands that do not take in parameters (i.e. list, exit) will be ignored.
> e.g. if the command specifies list 123, it will be interpreted as list.

### Adding a task of type todo: `todo`

Adds a task of either type todo, type deadline or type event in the task list.

Format: `todo TASK DESCRIPTIONS`

### Adding a task of type deadline: `deadline ... /by`

Adds a task of either type todo, type deadline or type event in the task list.

Format: `deadline TASK DESCRIPTIONS /by DATE TIME`

- `DATE TIME` must follow the format *yyyy-MM-dd HH:mm*.

### Adding a task of type event: `event ... /at`

Adds a task of either type todo, type deadline or type event in the task list.

Format: `event TASK DESCRIPTIONS /at DATE TIME`

- `DATE TIME` must follow the format *yyyy-MM-dd HH:mm*.

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

### Locating tasks by a key word: `find`

Finds all tasks that contain an input key word.

Format: `find WORD`

### Marking a task as done: `done INDEX`

Updates the task status of the specified task as done in the task list.

Format: `done INDEX`

- Marks the task at the specified INDEX as done.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

### Deleting a task: `delete INDEX`

Delete the specified task in the task list.

Format: `delete INDEX`

- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Load the data

Karlett automatically finds a file named "karlett.txt" and load data from it upon opening. If no matching file is found, user has the option to ask Karlett to create it for data storage.

### Saving the data

Karlett data are saved in a file named "karlett.txt" in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
