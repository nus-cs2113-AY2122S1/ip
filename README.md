# User Guide

Shika is a simple Command Line Interface (CLI) app for managing tasks.

## Table of Contents
* Features
   1. [Exit](#bye-exits-shika-a-name--byea)
   2. Add
      * [Todo](#todo---add-todo)
      * [Deadline](#deadline---add-deadline)
      * [Event](#event---add-event)
   3. [Done](#done---mark-task-as-done)
   4. [List](#list---list-all-tasks)
   5. [Find](#find---finds-task)
* [Command Summary](#command-summary)

## Features

### `bye` Exits Shika <a name = "bye"></a>

Exits Shika.

Example of usage:

`bye`

Expected outcome:

Shika will print an exit message and exit.

```
____________________________________________________________________________
> Bye friend!
> See you again! :)
____________________________________________________________________________
```

<br>

### `todo` - Add Todo

Adds a todo to the task list.

Format: `todo TASKNAME`
* `TASKNAME` has to be filled in.

Example of usage:

`todo IP`

Expected outcome:

Shika will add the todo to the task list and print a message.

```
____________________________________________________________________________
> Added: 
	1. [T][ ] IP 
> You have 1 task on your list. -w-
____________________________________________________________________________
```
<br>

### `deadline` - Add Deadline

Adds a deadline to the task list.

Format: `deadline TASKNAME /at DATE`
* Date has to follow `ddMMyyyy`, `dd/MM/yyyy` or `dd-MM-yyyy` formats.
* Both `TASKNAME` and `DATE` have to be filled in.

Example of usage:

* `deadline Submit IP /by 01102021`
* `deadline Submit IP /by 01/10/2021`
* `deadline Submit IP /by 01-10-2021`

Expected outcome:

Shika will add the deadline to the task list and print a message.

```
____________________________________________________________________________
> Added: 
	1. [D][ ] Submit IP  (by: Oct 1 2021)
> You have 2 tasks on your list. -w-
____________________________________________________________________________
```
<br>

### `event` - Add Event

Adds an event to the task list.

Format: `event TASKNAME /at DATE`
* Date has to follow `ddMMyyyy`, `dd/MM/yyyy` or `dd-MM-yyyy` formats.
* Both `TASKNAME` and `DATE` have to be filled in.

Example of usage:

* `event Tales of Arise /at 10092021`
* `event Tales of Arise /at 10-09-2021`
* `event Tales of Arise /at 10/09/2021`

Expected outcome:

Shika will add the event to the task list and print a message.

```
____________________________________________________________________________
> Added: 
	3. [E][ ] Tales of Arise  (at: Sep 10 2021)
> You have 3 tasks on your list. -w-
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

Shika will delete the task and print a message.

```
____________________________________________________________________________
> You've removed: 
	1. [T][ ] IP 
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

Shika will mark the task as done and print a message.

```
____________________________________________________________________________
> You've done: 
	1. [T][X] IP 
____________________________________________________________________________
```
<br>

### `list` - List all Tasks

Lists all tasks.

Example of usage:
`list`

Expected outcome:

Shika will list all tasks.

```
____________________________________________________________________________
> Here is your list of tasks: 
	1. [T][X] IP 
	2. [D][ ] Submit IP  (by: Oct 1 2021)
	3. [E][ ] Tales of Arise  (at: Sep 10 2021)
> You have 3 tasks on your list. -w-
____________________________________________________________________________
```
<br>

### `find` - Finds Task

Lists all tasks that contain the search term.

Example of usage: `find rise`
* Search is case-insensitive.
* Lists all tasks that contain the search term.

Expected outcome:

```
____________________________________________________________________________
> Here are the matching tasks I've found: 
	3. [E][ ] Tales of Arise  (at: Sep 10 2021)
____________________________________________________________________________
```

Example of usage:

* `find ip`
* `find IP`

Expected outcome:


```
____________________________________________________________________________
> Here are the matching tasks I've found: 
	1. [T][X] IP 
	2. [D][ ] Submit IP  (by: Oct 1 2021)
____________________________________________________________________________
```
### `done` - Mark Task as Done

Marks the task with the given `INDEX` as done. `INDEX` has to be positive and refer to a valid task.

Format:
`done INDEX`
* `INDEX` has to be positive and refer to a valid task.

Example of usage:

* `done 1`

Expected outcome:

Shika will mark the task as done and print a message.

```
____________________________________________________________________________
> You've done: 
	1. [T][X] IP 
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

##References and Acknowledgements
- Text to ASCII Art Generator
  https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Shika%0A
- OOP Structure: Referenced from AddressBook
  https://github.com/se-edu/addressbook-level2/