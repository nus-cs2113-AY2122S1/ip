# User Guide

Duke is an app for **managing Tasks, Deadlines and Events via a Command Line Interface (CLI)**. If you can type fast, 
Duke is able to manage your various tasks faster than traditional GUI applications.
- [Quick Start](#quick-start)
- [Features](#features)
  - [Qutting the program: `bye`](#bye---quits-the-program)
  - [Adding a deadline: `deadline`](#deadline---creates-a-task-with-deadline)
  - [Deleting a task: `delete`](#delete---deletes-a-task)
  - [Creating an event: `event`](#event---creates-an-event)
  - [Seaching for tasks: `find`](#find---search-for-tasks)
  - [Viewing help: `help`](#help---displays-the-help-message)
  - [Viewing all tasks: `list`](#list---list-all-tasks)
  - [Creating a todo: `todo`](#todo---creates-a-todo-list)
  - [Viewing tasks by date: `whatson`](#whats-on---list-tasks-on-a-day)
  - [Saving the data:](#saving-the-data)
  - [Editing the data file:](#editing-the-data-file)
- [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `11` installed in your computer.
2. Download the latest `duke.jar` from [here](https://github.com/alvintan01/ip/releases/tag/A-Jar).
3. Copy the file to the folder you want to use as the home folder for your task list.
4. Run the application using the command `java -jar duke.jar`.


## Features

**Notes about the commands format**
- All dates and times must be in `dd/MM/yyyy HHmm` format. For example `20/09/2021 1600` represents the 
`20 September 2021 4pm`. 
- Parameters must be given in the format specified.


### `Bye` - Quits the program.

Expected outcome:

The program ends and saves the data.

Format: `bye`

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Bye. Hope to see you again soon!
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```
  
### `Deadline` - Creates a task with deadline.

Creates a task with a deadline and adds it to the task list.

Format: `deadline <name> /by <date in dd/MM/yyyy HHmm>`

Example of usage: 

`deadline project /by 30/09/2021 1200`

Expected outcome:

A task named project is created with dateline 30/09/2021 1200.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Got it. I've added this task:
[D][ ] project (by: Sep 30 2021 1200)
Now you have 1 tasks in the list.
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `Delete` - Deletes a task.

Deletes a specified task.

Format: `delete <task id>`

Example of usage: 

`delete 1`

Expected outcome:
The task selected is removed from the task list.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Noted. I've removed this task:
[D][ ] project (by: Sep 30 2021 1200)
Now you have 0 tasks in the list.
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `Done` - Marks a task complete.

Marks a specified task as completed.

Format: `done <task id>`

Example of usage: 

`done 1`

Expected outcome:

The task selected is marked as completed.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Nice! I've marked this task as done:
[D][X] project (by: Sep 30 2021 1200)
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `Event` - Creates an Event.

Creates an event in the task list.

Format: `event <event name> /at <date in dd/MM/yyyy HHmm>`

Example of usage: 

`event open house /at 15/10/2021 0900`

Expected outcome:

The event named open house is created with date and time 15/10/2021 0900.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Got it. I've added this task:
[E][ ] open house (at: Oct 15 2021 0900)
Now you have 2 tasks in the list.
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `Find` - Search for tasks.

Finds all tasks with the specified word.

Format: `find <string to search>`

Example of usage: 

`find open`

Expected outcome:

The event named open house is returned.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Here are the tasks in your list:
1.[E][ ] open house (at: Oct 15 2021 0900)
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `Help` - Displays the help message.

Shows the description and syntax of all commands.

Format: `help`

Expected outcome:

The description and syntax of the commands are returned.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Welcome to the help page.
Bye: Exits the program.
Deadline: Adds a task with deadline. Command Syntax: deadline <name> /by <date in dd/MM/yyyy HHmm>
Delete: Deletes a task. Command Syntax: delete <task id>
Done: Marks a task as completed. Command Syntax: done <task id>
Event: Creates an event. Command Syntax: event <event name> /at <date in dd/MM/yyyy HHmm>
Find: Finds all tasks with the specified word. Command Syntax: find <string to search>
Help: Displays the help message.
List: Lists all tasks.
Todo: Creates a Todo task. Command Syntax: todo <task name>
What's On: Lists all tasks occurring on the specified day. Command Syntax: whatson <date in dd/MM/yyyy>
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `List` - List all tasks.

List all tasks in the task list.

Format: `list`

Expected outcome:

All tasks in the task list are listed.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Here are the tasks in your list:
1.[D][X] project (by: Sep 30 2021 1200)
2.[E][ ] open house (at: Oct 15 2021 0900)
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `Todo` - Creates a Todo list.

Creates a Todo in the task list.

Format: `todo <task name>`

Example of usage: 

`todo buy bread`

Expected outcome:

The task buy bread is added to the task list.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Got it. I've added this task:
[T][ ] buy bread
Now you have 3 tasks in the list.
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `What's On` - List tasks on a day.

Lists all tasks occurring on the specified date.

Format: `whatson <date in dd/MM/yyyy`
Example of usage: 

`whatson 15/10/2021`

Expected outcome:

All tasks on the specific date is returned.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Here are the tasks in your list:
1.[E][ ] open house (at: Oct 15 2021 0900)
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### Saving the data
Duke will automatically save the data after any command that changes the data. There is no need to save manually.

### Editing the data file
Duke's data are saved by a pipe `|` delimited file at `data/duke.txt`. Advanced users are welcome to update the data
directly by editing that data file.

`!Caution: If your changes to the data file makes its format invalid, Duke will discard all data and start with an 
empty data file at the next run.`

## Command Summary
| Action | Command Parameters |
| --- | --- |
| `bye` | - |
| `deadline` | `deadline <name> /by <date in dd/MM/yyyy HHmm>` |
| `delete` | `delete <task id>` |
| `done` | `done <task id>`|
| `event` | `event <event name> /at <date in dd/MM/yyyy HHmm>` |
| `find` | `find <string to search>` |
| `help` | - |
| `list` | - |
| `todo` | `todo <task name>` |
| `whatson` | `whatson <date in dd/MM/yyyy>` |
