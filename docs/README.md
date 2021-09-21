# IzzIbot

## User Guide
IzzIbot is a task management chatbot application, optimized for use via a Command Line Interface (CLI). 
Available features include adding, removing, finding, setting as done and listing down of tasks.

## Features

### Getting Started
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest iP.jar.
3. Copy the file into an empty folder.
4. Open a command window in that folder.
5. Run the command `java -jar iP.jar`
6. The first run will create a new storage file `IzziBot_data` and terminate.
7. Repeat step 5 to use IzzIbot.

### Add a `Task`
Adds a `Task` of one of three types - `Todo`, `Deadline` or `Event` to the task manager.
`Deadline` and `Event` tasks each have an additional temporal attribute attached to them.

### List down all `Task`
Lists down all `Task` objects currently in the task manager.

### Mark a `Task` as Done
Sets a `Task` in the task manager as completed.

### Delete a `Task`
Removes a `Task` from the task manager.

### Find a `Task`
Searches for all `Task` objects in the task manager with a matching keyword.

### Exit `IzzIbot`
Terminates the application.

### Data Storage
`Task` objects are saved automatically in the task manager to the hard disk automatically whenever
the task list changes. There is no need for manual saving. 

User data is stored in a folder named `IzzIbot_data`.
The tasks are stored in that folder in a file named `IzzIbot.txt`.

## Usage
The formatting of commands are as follows. The Words in `UPPER_CASE` are parameters.

### Adding a `Todo`: `todo`
Adds a `Todo` `Task` to the task manager.

Format: `todo TASK`

Example of usage:

`todo shower`

### Adding a `Deadline`: `deadline`
Adds a `Deadline` `Task` to the task manager with a deadline.

Format: `deadline TASK /by TIME_AND_OR_DATE`

Example of usage:

* `deadline iP submission /by 1st October 2359`
* `deadline buy stationery /by today`

### Adding an `Event`: `event`
Adds an `Event` `Task` to the task manager with a time and/or place.

Format: `event TASK /by TIME_AND_OR_DATE`

Example of usage:

* `event CS2113T tutorial /at Friday 1200`
* `event watch latest drama episode /at 10pm`

### List down all `Task`: `list`
Lists down all `Task` objects currently in the task manager.

Example of usage:

`list`

Expected outcome:
```
____________________________________________________________
Your task list:
1. [T][ ] shower 
2. [D][X] iP submission (by: 1st October 2359)
3. [E][ ] watch latest drama episode (at: 10pm)
____________________________________________________________
```

### Mark a `Task` as Done: `done`
Sets a `Task` in the task manager as completed. You need to refer
to the task number of the task from the output of the `list` command.

Format: `done INDEX`

Example of usage:

`done 1`

Expected outcome:
```
____________________________________________________________
set as done: [T][X] shower
____________________________________________________________
```

### Delete a `Task`
Removes a `Task` from the task manager.
Similar to `done`, this is used in conjunction with `list`.

Format: `delete INDEX`

Example of usage:

`delete 2`

Expected outcome:
```
____________________________________________________________
deleted: [E][ ] tP weekly meeting (at: Tues 11pm)
____________________________________________________________
```

### Find a `Task`
Searches for all `Task` objects in the task manager with a matching keyword.
The matching results are then printed as a list.

Format: `find KEYWORD`

Example of usage:

`find watch`

Expected outcome:
```
____________________________________________________________
Here are the matching tasks in your list that contains [watch]:
Your task list:
1. [E][ ] watch latest drama episode (at: 10pm)
____________________________________________________________
```

### Exit `IzzIbot`: `bye`
Terminates the application and prints a message.

Example of usage:

`bye`

Expected outcome:
```
____________________________________________________________
You've terminated IzzIbot. Have a good day!
____________________________________________________________
```

## Command summary

Command | Format, Example
--------|------------------
**Todo** | `todo TASK` <br> e.g., `todo shower`
**Deadline** | `deadline TASK /by TIME_AND_OR_DATE` <br> e.g., `deadline iP submission /by 1st October 2359`
**Event** | `event TASK /by TIME_AND_OR_DATE` <br> e.g., `event CS2113T tutorial /at Friday 1200`
**List** | `list`
**Done** | `done INDEX`<br> e.g., `done 1`
**Delete** | `delete INDEX`<br> e.g., `delete 2`
**Find** | `find KEYWORD`<br> e.g., `find watch`