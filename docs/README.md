# Duke User Guide

**Duke** is a Command Line Interface (CLI) app which helps you **manage tasks and keep track 
of events and deadlines**. It is a personalised **chatbox** that offers customised feedback 
and helps you stay ahead of the curve.

## Table of Contents

- [Quick start](#quick-start)
- [Features](#features)
    - [Help `help`](#help-help)
    - [Add Todo Task `todo`](#add-todo-task-todo)
    - [Add Event Task `event`](#add-event-task-event)
    - [Add Deadline Task `deadline`](#add-deadline-task-deadline)
    - [List All Tasks `list`](#list-all-tasks-list)
    - [Mark a Task as Done `done`](#mark-a-task-as-done-done)
    - [Delete a Task `delete`](#delete-a-task-delete)
    - [Find a Task `find`](#find-a-task-find)
    - [End the Program `bye`](#end-the-program-bye)
    - [Save Tasks to File]
- [Command Summary](#command-summary)

-------------------------------------------------

## Quick start

1. Ensure that you have `Java 11` installed on your computer.
2. Download the latest `ip.jar` file from 
[here](https://github.com/mayankp291/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for `Duke`.
4. Open the terminal (such as `powershell`) in this folder and launch by typing `java -jar ip.jar`.
6. A welcome message should be displayed like the one below:
```
____________________________________________________________
Howdy there! I'm Fluke
What can I do for you today master?
____________________________________________________________
```
7. Refer to Features for proper usage instructions and commands.

-------------------------------------------------
## Features

### Note
- Words in `<UPPER_CASE>` are parameters given by the user.

### Help: `help`
Displays all the commands and appropriate usage.

Format: `help`

### Add Todo Task: `todo`
Adds a todo task for DUKE to keep track.

Format: `todo <TASK_DESCRIPTION>`

Example: `todo finish eating lunch`

```
todo finish eating lunch
____________________________________________________________
Got it. I've added this task:
[T][ ] finish eating lunch
Now you have 1 tasks in the list.
____________________________________________________________
```

### Add Event Task: `event`
Adds an event with a specific date and time for DUKE to keep track.

Format: `event <TASK_DESCRIPTION> /at <YYYY-MM-DD HH:MM>`

Example: `event Attend CG2027 lecture /at 2021-10-12 15:21`

```
event Attend CG2027 lecture /at 2021-10-12 15:21
____________________________________________________________
Got it. I've added this task:
[E][ ] Attend CG2027 lecture  (on: Tuesday, October 12, 2021 03:21 PM)
Now you have 2 tasks in the list.
____________________________________________________________
```

### Add Deadline Task: `deadline`
Adds a deadline with a specific date and time for DUKE to keep track.

Format: `deadline <TASK_DESCRIPTION> /at <YYYY-MM-DD HH:MM>`

### List all tasks: `list`
Lists all the tasks added to Duke

Format: `list`

### Mark a Task as Done: `done`
Marks a task that DUKE is tracking as done.

Format: `done <TASK_INDEX>` 

### Delete a Task: `delete`
Delete the task from Duke and duketasks.txt

Format: `delete <TASK_INDEX>` 

### Find a Task: `find`
Finds tasks with a `<SEARCH_STRING>` that contains the string inputted by the user.

Format: `find <SEARCH_STRING>`

### End the Program: `bye`
Duke bids farewell and closes the application.

Format: `bye`

-------------------------------------------------
## Command Summary

Action | Command Format | Example
--- | --- | --- | 
List all valid commands and usage | `help` | `help`
Add todo task| `todo <TASK_DESCRIPTION>` | `todo do cs2113 homework`
Add deadline task | `deadline <TASK_DESCRIPTION> /by <YYYY-MM-DD HH:MM>` | `deadline submit ip jar /by 2021-10-02 22:20`
Add event task| `event <TASK_DESCRIPTION> /at <YYYY-MM-DD HH:MM>` | `event graduation party /at 2021-11-01 18:10`
List all the tasks| `list` | `list`
Mark a task done | `done <TASK_INDEX>` | `done 2`
Delete a task| `delete <TASK_INDEX>` | `delete 3`
Find matching tasks| `find <SEARCH_STRING>` | `find homework`
Exit duke | `bye` | `bye`
