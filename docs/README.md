# User Guide

Duke is a simple Command Line Interface (CLI) app for managing tasks.

## Table of Contents
* Features
    1. [Exit](#bye-exits-duke-a-name--byea)
    2. Add
        * [Todo](#todo---add-todo)
        * [Deadline](#deadline---add-deadline)
        * [Event](#event---add-event)
    3. [Done](#done---mark-task-as-done)
    4. [List](#list---list-all-tasks)
    5. [Find](#find---finds-task)
    6. [Help](#help---list-a-short-summary-of-commands-and-formats)
* [Command Summary](#command-summary)

## Features

### `bye` Exits Duke <a name = "bye"></a>

Exits duke.

Example of usage:

`bye`

Expected outcome:

Duke will print an exit message and exit.

```
____________________________________________________________________________

Bye. Hope to see you again soon!
____________________________________________________________________________
```

<br>

### `todo` - Add Todo

Adds a todo to the task list.

Format: `todo TASKNAME`
* `TASKNAME` has to be filled in.

Example of usage:

`todo Test`

Expected outcome:

Duke will add the todo to the task list and print a message.

```
____________________________________________________________________________

Got it. I've added this task: 
[T][ ] Test
You now have 4 items in the list.
____________________________________________________________________________
```
<br>

### `deadline` - Add Deadline

Adds a deadline to the task list.

Format: `deadline DESCRIPTION / DATE`
* Date has to follow `yyyy-MM-dd` formats.
* Both `DESCRIPTION` and `DATE` have to be filled in.

Example of usage:

* `deadline Submit IP / 2021-10-01`

Expected outcome:

Duke will add the deadline to the task list and print a message.

```
____________________________________________________________________________

Got it. I've added this task: 
[D][ ] Submit IP (by: Oct 01 2021)
You now have 2 items in the list.
____________________________________________________________________________
```
<br>

### `event` - Add Event

Adds an event to the task list.

Format: `event DESCRIPTION / EVENT TIME`
* Date has to follow `ddMMyyyy`, `dd/MM/yyyy` or `dd-MM-yyyy` formats.
* Both `DESCRIPTION` and `EVENT TIME` have to be filled in.

Example of usage:

* `event CS2113T Tut / Wednesday 11am - 12pm`


Expected outcome:

Duke will add the event to the task list and print a message.

```
____________________________________________________________________________

Got it. I've added this task: 
[E][ ] CS2113T Tut (at: Wednesday 11am - 12pm)
You now have 3 items in the list.
____________________________________________________________________________
```
<br>

### `delete` - Delete Task

Deletes the task with the given `INDEX`. `INDEX` has to be positive and refer to a valid task.

Format:
`delete INDEX`
* `INDEX` has to be positive and refer to a valid task.

Example of usage:

* `delete 1`

Expected outcome:

Duke will delete the task and print a message.

```
____________________________________________________________________________

Noted. I've removed this task: 
[T][ ] Test
You now have 2 items in the list.
____________________________________________________________________________
```
<br>

### `done` - Mark Task as Done

Marks the task with the given `INDEX` as done. `INDEX` has to be positive and refer to a valid task.

Format:
`done INDEX`
* `INDEX` has to be positive and refer to a valid task.

Example of usage:

* `done 1`

Expected outcome:

Duke will mark the task as done and print a message.

```
____________________________________________________________________________

Nice I've marked this task as done:
[D][X] Submit IP (by: Oct 01 2021)
____________________________________________________________________________
```
<br>

### `list` - List all Tasks

Lists all tasks.

Example of usage:
`list`

Expected outcome:

Duke will list all tasks.

```
____________________________________________________________________________

1.[D][X] Submit IP (by: Oct 01 2021)
2.[E][ ] CS2113T Tut (at: Wednesday 11am - 12pm)
____________________________________________________________________________
```
<br>

### `find` - Finds Task

Lists all tasks that contain the search term.

Example of usage: `find tut`
* Search is case-insensitive.
* Lists all tasks that contain the search term.

Expected outcome:

```
____________________________________________________________________________

[E][ ] CS2113T Tut (at: Wednesday 11am - 12pm)
____________________________________________________________________________
```

### `help` - List a short summary of commands and formats

Lists all available commands and the required input formats

Format:
`help`

Example of usage:

* `help`

Expected outcome:

Duke will print a help message.

```
____________________________________________________________________________

Hi! If you need help on how to use me you've come to the right place!
Here's the list of commands I can handle now:
1.List - list
2.Add todo - todo <description>
3.Add deadline - deadline <description> /<due>
4.Add event - event <description> /<due>
5.Set task as done - done <task index>
5.Delete task - delete <task index>
6.Find tasks - find <search keywords>
____________________________________________________________________________
```

<br>

## Command Summary

Command | Format, Examples
------------ | -------------
Exit | `bye`
Add | `todo TASKNAME`, `deadline TASKNAME /by DATE`, `event TASKNAME /at DATE`
Delete | `delete INDEX`
Done | `done INDEX`
List | `list`
Find | `find SEARCHTERM`
Help | `help`