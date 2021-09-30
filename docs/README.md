# Gaben Task Manager User Guide

## About Gaben

Gaben is a **Command Line Interface (CLI)** application that helps users to manage their tasks. Fast typers will find that managing task with Gaben is much faster as compared to an equivalent Graphical User Interface (GUI) application.

# Table of Contents
- Quick Start
- Features
  - Adding of Tasks
    - Add a Todo: `todo`
    - Add a Deadline: `deadline`
    - Add a Event: `event`
  - List all tasks: `list`
  - Mark a task as done: `done`
  - Delete a task: `delete`
  - Find tasks: `find`
  - Quit the program: `bye`
  - Saving data to file
    - Automatic saving
    - Editing the save file
- Command Summary

# Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest release of `gaben.jar` from [here](https://github.com/Teckwhye/ip/releases) to your desired directory.
4. Open command prompt in the directory containing the downloaded jar file `gaben.jar`.
5. Run `gaben.jar` program with the command `java -jar gaben.jar`.

# Features

## Adding of Tasks

Add a task to be managed. Supported task types are `Todo`, `Deadline` and `Event`.

>❗Important to take note that `,` should not be used in any of the add command.

### Add a Todo: `todo`

A Todo task keeps track of task description. 

Format: `todo <description>`

Examples: `todo help wash the dishes`

Expected Outcome:

```
todo help wash the dishes
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Gaben have seen and will add the following task for you:
[T][ ] help wash the dishes
You now have 1 task in the list
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
```

### Add a Deadline: `deadline`

A Deadline task keeps track of a task description and its due date

Format: `deadline <description> /by <due_date>`

Examples: `deadline CS2113T IP /by 1st October 2021 23:59`

Expected Outcome:

```
deadline CS2113T IP /by 1st October 2021 23:59
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Gaben have seen and will add the following task for you:
[D][ ] CS2113T IP  (by: 1st October 2021 23:59)
You now have 2 task in the list
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
```

### Add a Event: `event`

A Event task keeps track of a task description and its date of event

Format: `event <description> /at <date_of_event>`

Examples: `event my birthday /at 2nd October 2021`

Expected Outcome:

```
event my birthday /at 2nd October 2021
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Gaben have seen and will add the following task for you:
[E][ ] my birthday  (at: 2nd October 2021)
You now have 3 task in the list
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
```

## List all tasks: `list`

Prints a list of all the tasks currently being managed.

Format: `list`

Expected Outcomes:

- There is a list of task

```
list
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Total of 3 task(s)
1.[T][ ] help wash the dishes
2.[D][ ] CS2113T IP  (by: 1st October 2021 23:59)
3.[E][ ] my birthday  (at: 2nd October 2021)

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
```

- There is no list of task

```
list
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Oh! You have no tasks left!
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
```

## Mark a task as done: `done`

Mark a task as done.

Format: `done <task_number>`

Example Usage: `done 1`

Expected Outcome:

```
done 1
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Good lad, you have finally completed the task you needed to do.
[T][X] help wash the dishes
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
```

## Delete a task: `delete`

Delete a task from the currently managed list of tasks.

Format: `delete <task_number>`

Example Usage: `delete 2`

Expected Outcome:

```
delete 2
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Why would you delete the following task? But anyways I have removed the following task.
[D][ ] CS2113T IP  (by: 1st October 2021 23:59)
Now you have left 2 tasks in the list.
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
```

## Find tasks: `find`

Look for tasks in the task list that match the search term in its description.

Format: `find <search_term>`

Example Usage: `find dishes`

Expected Outcome:

```
find dishes
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Total of 1 task(s) found that matches your description.
1.[T][X] help wash the dishes

=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
```

## Quit the program: `bye`

Exits the program.

Format: `bye`

Expected Outcome:

```
bye
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Thank you for using Gaben.
Remember to keep your wallet stacked before using me again!
=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
```

## Saving data to file

### Automatic saving

The tasks in the task list will automatically be saved to a file `data/tasks.txt` in the current directory after every add, delete and done command.

### Editing the save file

The save file `tasks.txt` can be editted such that task information inside the file will be uploaded to the program on its next run.

Each line represents a single task and the `,` character is used to seperate the task information.

The save file saves tasks in the following formats:

```
<type>,<completion_status>,<description>
<type>,<completion_status>,<description>,<date>
```
> 💡 `<type>` **T** for *Todo*, **D** for *Deadline* and **E** for *Event*
> 
> 💡 `<completion_status>` must be either `1` for compete or `0` for incompleted.
> 
> 💡 `<description>` must not have `,`
> 
## Command Summary

| Action                   | Format                                  | Example                                        |
|--------------------------|-----------------------------------------|------------------------------------------------|
| Add a task - Todo        | `todo <description>`                      | todo help wash the dishes                      |
| Add a task - Deadline    | `deadline <description> /by <due_date>`   | deadline CS2113T IP /by 1st October 2021 23:59 |
| Add an task - Event      | `event <description> /at <date_of_event>` | event my birthday /at 2nd October 2021         |
| List all tasks           | `list`                                    | list                                           |
| Mark a task as done      | `done <task_number>`                      | done 1                                         |
| Delete a task            | `delete <task_number>`                    | delete 2                                       |
| Find tasks               | `find <search_term>`                      | find dishes                                    |
| Quit the program         | `bye`                                     | bye                                            |

