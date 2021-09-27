# User Guide <!-- omit in toc -->

## About H.A.L 9000

The H.A.L 9000 is a **Command Line Interface (CLI)** desktop application to manage a multitude of tasks. fast typers will find that they can manage tasks at a much faster rate compared to Graphical User Interface (GUI) applications. This task manager features the personality of H.A.L 9000 from *2001: A Space Odyssey*. 

- [About H.A.L 9000](#about-hal-9000)
- [Quick Start](#quick-start)
- [Features](#features)
  - [Add tasks](#add-tasks)
    - [Add a Todo: `todo`](#add-a-todo-todo)
    - [Add a Deadline: `deadline`](#add-a-deadline-deadline)
    - [Add an Event: `event`](#add-an-event-event)
  - [List all tasks: `list`](#list-all-tasks-list)
  - [Mark a task as completed: `done`](#mark-a-task-as-completed-done)
  - [Delete a task: `delete`](#delete-a-task-delete)
  - [Find tasks: `find`](#find-tasks-find)
  - [Print accepted commands: `help`](#print-accepted-commands-help)
  - [Exit the program: `bye`](#exit-the-program-bye)
  - [Save File](#save-file)
    - [Automatic saving](#automatic-saving)
    - [Editing the save file](#editing-the-save-file)
  - [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `hal.jar` from [here](https://github.com/arraysius/ip/releases/tag/A-Release) to a suitable directory.
3. Open a command prompt in the directory with `hal.jar` and run the command `java -jar hal.jar`.

## Features

### Add tasks

Add a task to be managed. There are three types of tasks supported. Todo, Deadline and Event.

#### Add a Todo: `todo`

A Todo task only keeps track of the task description.

Format: `todo <description>`

Example Usage: `todo buy milk`

Expected Outcome:
```
____________________________________________________________
I have added the task:
[T][ ] buy milk
There are now 1 tasks in the list.
____________________________________________________________
```

#### Add a Deadline: `deadline`

A Deadline task keeps track of the task description and its end date.

Format: `deadline <description> /by <end_date>`

> 💡 `<end_date>` must be entered in the format of `YYYY-MM-DD`.

Example Usage: `deadline math homework /by 2021-10-10`

Expected Outcome:
```
____________________________________________________________
I have added the task:
[D][ ] math homework (by: 2021-10-10)
There are now 2 tasks in the list.
____________________________________________________________
```

#### Add an Event: `event`

An Event task keeps track of the task description and its event date.

Format: `event <description> /at <event_date>`

> 💡 `<event_date>` must be entered in the format of `YYYY-MM-DD`.

Example Usage: `event movie night /at 2021-10-11`

Expected Outcome:
```
____________________________________________________________
I have added the task:
[E][ ] movie night (at: 2021-10-11)
There are now 3 tasks in the list.
____________________________________________________________
```

### List all tasks: `list`

Prints a list of all the tasks currently being tracked.

Format: `list`

Expected Outcome:
```
____________________________________________________________
Here are the tasks in your list:
1. [T][ ] buy milk
2. [D][ ] math homework (by: 2021-10-10)
3. [E][ ] movie night (at: 2021-10-11)
____________________________________________________________
```

### Mark a task as completed: `done`

Mark a task as completed and change the status icon of the task to `X`.

Format: `done <task_index>`

> 💡 `<task_index>` must be a valid index in the list of tasks.

Example Usage: `done 2`

Expected Outcome:
```
____________________________________________________________
Affirmative. I will mark this task as done:
[D][X] math homework (by: 2021-10-10)
____________________________________________________________
```

### Delete a task: `delete`

Remove a task from the currently tracked list of tasks.

Format: `delete <task_index>`

> 💡 `<task_index>` must be a valid index in the list of tasks.

Example Usage: `delete 2`

Expected Outcome:
```
____________________________________________________________
Affirmative. I have removed this task:
[D][X] math homework (by: 2021-10-10)
You have 2 tasks left in the list.
____________________________________________________________
```

### Find tasks: `find`

Search for tasks in the task list with the search term in its description.

Format: `find <search_term>`

> 💡 `<search_term>` must not be blank.

Example Usage: `find movie`

Expected Outcome:
```
____________________________________________________________
Here are the matching tasks in your list:
1.[E][ ] movie night (at: 2021-10-11)
____________________________________________________________
```

### Print accepted commands: `help`

Prints a useful help mesage to the user of the accepted commands.

Format: `help`

Expected Outcome:
```
____________________________________________________________
Here are a list of accepted commands:
help
list
done <item no.>
todo <description>
deadline <description> /by <date and time>
event <description> /at <date and time>
delete <item no.>
find <search term>
bye
____________________________________________________________
```

### Exit the program: `bye`

Exits the program and return the user to the command prompt.

Format: `help`

Expected Outcome:
```
____________________________________________________________
This conversation can serve no purpose anymore. Goodbye.
____________________________________________________________
```

### Save File

#### Automatic saving

The tasks in the managed task list will automatically be saved to a file `tasks.txt` in the current directory after each addition, deletion or completion of a task.

#### Editing the save file

The save file `tasks.txt` can be modified such that the modified task information will be imported into the program when it is next run.

Each line in a single task and the pipe `|` character is used to separate task information.

The format for each type of task is as follows:

Todo: `<type>|<completion_status>|<description>`

E.g. `T|0|buy milk`

Deadline and Event: `<type>|<completion_status>|<description>|<date>`

E.g. Deadline: `D|0|movie night|2021-10-10`

E.g. Event: `E|0|movie night|2021-10-11`

> 💡 `<completion_status>` must be either `0` for incompete or `1` for completed.
> 
> 💡 `<event_date>` must be entered in the format of `YYYY-MM-DD`.
> 
> ❗ If the save file is corrupted or does not follow the save format, the task information will not be loaded and the file will be overwritten.

### FAQ

---

**Question**: What if I do not have a save file.  
**Answer**: A new save file will be created upon adding or deleting a task, or when marking a task as done.

**Question**: Can I transfer the save file to a different computer?
**Answer**: Yes.
1. Follow the instructions in the [Quick Start](#quick-start) to obtain a copy of `hal.jar` on the target computer.
2. Transfer the existing `tasks.txt` from the source computer to the target computer. Place `tasks.txt` in the same directory as `hal.jar`.


## Command Summary

---

| Action                   | Format                                  | Example                                 |
| ------------------------ | --------------------------------------- | --------------------------------------- |
| Add a Todo               | `todo <description>`                    | `todo buy milk`                         |
| Add a Deadline           | `deadline <description> /by <end_date>` | `deadline math homework /by 2021-10-10` |
| Add an Event             | `event <description> /at <event_date>`  | `event movie night /at 2021-10-11`      |
| Mark a task as completed | `done <task_index>`                     | `done 2`                                |
| Delete a task            | `delete <task_index>`                   | `delete 2`                              |
| Find tasks               | `find <search_term> `                   | `find movie`                            |
| Print accepted commands  | `help`                                  | `help`                                  |
| Exit the program         | `bye`                                   | `bye`                                   |
