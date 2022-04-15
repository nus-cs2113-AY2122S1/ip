# User Guide

ip is a desktop app for managing the tasks you need to do.

## Starting the program

1. Ensure you have Java `11` or above installed in your computer.
2. Download [`ip.jar`](https://github.com/huien77/ip/releases/download/A-Release/ip.jar).
3. Copy `ip.jar` to the folder you want to use as the **home** directory.
4. Open `Command Prompt`.
5. `cd` to the **home** directory.
6. Enter `java -jar ip.jar` to start the program.

## Features 

These features will help you use the app correctly.

### Get help: `help`

Shows a message that displays all the commands available and its format.

Format: `help`

### List all the tasks: `list`

Shows a list of all the tasks in task list.

Format: `list`

### Add task to do: `todo`

Adds a todo task which includes a description of the task.

Format: `todo description`

Example:

- `todo assignment`

### Add task with deadline: `deadline`

Adds a tasks with a deadline which includes a description of the task, due date and due time.

Format: 

- dueDate: `yyyy-MM-dd`
- dueTime: `hh:mm`
- `deadline description /by dueDate dueTime`

Examples:

- `deadline assignment /by 2021-09-24 09:59`
- `deadline assignment /by 2021-09-24 23:59`

### Add event: `event`

Adds an event which includes a description of the event, event date, start and end time of event.

Format: 

- date: `yyyy-MM-dd`
- startTime / endTime: `hh:mm`
- `event description /at date startTime endTime`

Examples:

- `event lecture /at 2021-09-24 10:00 12:00`
- `event lecture /at 2021-09-24 16:00 18:00`

### Mark a task as done: `done`

Marks the task at the given index as done.

Format:

- index: `int`
- `done index`

Example:

- `done 1`

### Delete a task: `delete`

Deletes a task in task list at the given index.

Format:

- index: `int`
- `delete index`

Example:

- `delete 1`

### Find a task by keyword: `find`

Finds a task in task list that contains the keyword supplied.

Format: `find keyword`

Examples:

- `find assignment`
- `find lecture`

### Exit the program: `bye`

Exit the program.

Format: `bye`

### Saving the data

The list of task is stored in the hard disk automatically before exiting the program.

The list will be stored at a relative file path, `data/duke.txt`.
