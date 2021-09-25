# User Guide

Duke is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI).
If you can type fast, Duke can manage your tasks faster than traditional GUI apps.

- [Quick Start](#quick-start)
- [Features](#features)
  - [Manage 3 different types of tasks](#manage-3-different-types-of-tasks)
  - [Adding a Todo task: `todo`](#adding-a-todo-task-todo)
  - [Adding a Deadline task: `deadline`](#adding-a-deadline-task-deadline)
  - [Adding an Event task: `event`](#adding-an-event-task-event)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Marking a task as done: `done`](#marking-a-task-as-done-done)
  - [Deleting a task done: `delete`](#deleting-a-task-delete)
  - [Searching for a task: `find`](#searching-for-a-task-find)
  - [Exit the program: `bye`](#exit-the-program-bye)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Command Summary](#command-summary)

--------------------------------------

## Quick Start

1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `iP.jar` from [here](https://github.com/jonathanmui4/ip/releases/)
3. Go to the folder you saved `iP.jar` and note the **absolute file path**.
4. If you are using **Windows**, open up a Command prompt terminal `cmd.exe` or `powershell.exe` and 
navigate to the folder where `iP.jar` is stored (using the file path).
5. For **Mac** and **Linux** users, do the same as step 4 with the terminal of your respective systems.
6. Execute `java -jar iP.jar`in the terminal and the application will start running. 

You should be able to see something like this:
```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

─────────────────────────────────────────────────────────────
Hello! I'm Duke
What can I do for you?
─────────────────────────────────────────────────────────────
```

--------------------------------------

## Features 

### Manage 3 different types of tasks

Duke inherently allows users to manage **Todo** tasks (tasks in a typical todo list), **Deadline** tasks
(tasks with a due date) and **Event** tasks (tasks with a venue).

### Adding a Todo task: `todo`

Adds a Todo task to Duke. A Todo task only contains a task description.  

Format: `todo {task description}`

Example:
```
todo buy grapes
─────────────────────────────────────────────────────────────

Got it. I've added this task:
[T][ ] buy grapes
Now you have 2 tasks in the list.
─────────────────────────────────────────────────────────────
```

### Adding a Deadline task: `deadline`

Adds a Deadline task to Duke. A Deadline task contains a task description and a due date.

Format: `deadline {task description} /by {due date}

`` :warning: `due date` needs to be in format `yyyy-MM-dd`.

Example:
```
deadline finish user guide /by 2021-09-29
─────────────────────────────────────────────────────────────

Got it. I've added this task:
[D][ ] finish user guide  (by: 29 Sep 2021)
Now you have 3 tasks in the list.
─────────────────────────────────────────────────────────────
```

### Adding an Event task: `event`

Adds an Event task to Duke. An Event task contains a task description and the event information.

Format: `event {task description} /at {event information}`

Example:
```
event Family Dinner /at Grandma's house 26 Sep 6pm
─────────────────────────────────────────────────────────────

Got it. I've added this task:
[E][ ] Family Dinner  (at: Grandma's house 26 Sep 6pm)
Now you have 4 tasks in the list.
─────────────────────────────────────────────────────────────
```

### Listing all tasks: `list`

Shows a list of all tasks stored by Duke.

Format: `list`

Example:
```
list
─────────────────────────────────────────────────────────────

Here are the tasks in your list:
1.[D][ ] return book  (by: 02 Sep 2021)
2.[T][ ] buy grapes
3.[D][ ] finish user guide  (by: 29 Sep 2021)
4.[E][ ] Family Dinner  (at: Grandma's house 26 Sep 6pm)
─────────────────────────────────────────────────────────────
```

### Marking a task as done: `done`

Marks a task as completed in Duke. Index given should be a **valid number** from the task list.

Format: `done {task index}`

Example:
```
done 1
─────────────────────────────────────────────────────────────

Nice! I've marked this task as done:
[D][X] return book  (by: 02 Sep 2021)
─────────────────────────────────────────────────────────────
```

### Deleting a task: `delete`

Deletes a task from Duke. Index given should be a **valid number** from the task list.

Format: `delete {task index}`

Example:
```
delete 1
─────────────────────────────────────────────────────────────

Noted. I've removed this task:
[D][X] return book  (by: 02 Sep 2021)
Now you have 3 tasks in the list.
─────────────────────────────────────────────────────────────
```

### Searching for a task: `find`

Finds every task that contains the searched `keyword`.

Format: `find {keyword}`

Example:
```
find sep
─────────────────────────────────────────────────────────────

Here are the matching tasks in your list:
1.[D][ ] finish user guide  (by: 29 Sep 2021)
2.[E][ ] Family Dinner  (at: Grandma's house 26 Sep 6pm)
─────────────────────────────────────────────────────────────
```

### Exit the program: `bye`

Terminates Duke.

Format:`bye`

Example:
```
bye
─────────────────────────────────────────────────────────────
Bye. Hope to see you again soon!
─────────────────────────────────────────────────────────────
```

### Saving the data

Task data in Duke are saved in the hard disk automatically after any command that changes the data. There is no need to 
save manually.

### Editing the data file

AddressBook data are saved as a csv file `[JAR file location]/data/tasks.csv`. Advanced users are welcome to update data 
directly by editing that data file.

`` :exclamation: **Caution**: If your changes to the data file is detected to be corrupted or does not conform to Duke's 
saved file format (csv), Duke will not run.

-----------------------------------------------

## FAQ

**Question:** How do I transfer my data to another Computer?

**Answer:** Install the app in the other computer and overwrite the empty data file it creates with the file that 
contains the data of your previous Duke home folder.

-----------------------------------------------

## Command Summary

Action | Format | Example
------ | ------ | -------
Adding a Todo task | `todo {task description}` | `todo buy grapes`
Adding a Deadline task | `deadline {task description} /by {due date}` | `deadline finish user guide /by 2021-09-29`
Adding an Event task | `event {task description} /at {event information}` | `event Family Dinner /at Grandma's house 26 Sep 6pm`
Listing all tasks | `list` | `list`
Marking a task as done | `done {task index}` | `done 1`
Deleting a task | `delete {task index} ` | `delete 1`
Searching for a task | `find {keyword}` | `find sep`
Exit the program | `bye` | `bye`

-----------------------------------------------
