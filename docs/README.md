# User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).
If you can type fast, Duke can get your task management done faster than traditional GUI apps.

- Features and Usage
  - Adding a task:
    - [Todo: `todo`](#todo)
    - [Deadline: `deadline`](#deadline)
    - [Event: `event`](#event)
  - [Listing current tasks: `list`](#list)
  - [Marking task as complete: `done`](#done)
  - [Delete a task: `delete`](#delete)
  - [Searching for tasks: `find`](#find)
  - [Exiting the app: `bye`](#exit)
- [Saving the task list](#save)
- [Command summary](#summary)

## Features

### Notes about the command format:
Words in UPPER_CASE are the parameters to be supplied by the user. <br />
e.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo read book`

<br />

### <a id="todo"></a>`todo` - Create todo task
Adds a todo task into your list.

Format: `todo TASK_DESCRIPTION`
* The `TASK_DESCRIPTION` cannot be empty

Example:
```
____________________________________________________________

todo read book
____________________________________________________________

Successfully added. Here's the added task:
   2. [T][ ] read book
Now you have 2 tasks in the list.
____________________________________________________________
```
<br />

### <a id="deadline"></a>`deadline` - Create deadline task
Adds a deadline task into your list.

Format: `deadline TASK_DESCRIPTION /by DEADLINE`
* The `TASK_DESCRIPTION` and `DEADLINE` cannot be empty

Example:
```
____________________________________________________________

deadline tutorial 1 /by monday 2359
____________________________________________________________

Successfully added. Here's the added task: 
    3. [D][ ] tutorial 1 (by: monday 2359)
Now you have 3 tasks in the list.
____________________________________________________________
```
<br />

### <a id="event"></a>`event` - Create event task
Adds an event task into your list.

Format: `event TASK_DESCRIPTION /at TIME_RANGE`
* The `TASK_DESCRIPTION` and `TIME_RANGE` cannot be empty

Example:
```
____________________________________________________________

event seminar /at monday 2-4pm
____________________________________________________________

Successfully added. Here's the added task:
   4. [E][ ] seminar (at: monday 2-4pm)
Now you have 4 tasks in the list.
____________________________________________________________
```
<br />

### <a id="list"></a> `list` - Lists current tasks
Lists all the tasks in your list.

Example:

```
____________________________________________________________

list
____________________________________________________________

1. [T][ ] homework
2. [D][ ] assignment (by: tuesday night)
____________________________________________________________
```
<br />

### <a id="done"></a>`done` - Mark task as done
Marks a task, based on its index, as done.

Format: `done TASK_INDEX`
* Marks the task at the specified `TASK_INDEX` as done.<br />
* The index refers to the index number shown in the displayed task list.<br />
* The index must be a positive integer 1, 2, 3, ...<br />

Example:

```
____________________________________________________________

done 1
____________________________________________________________

Awesome! You've completed the following task:
  [T][X] homework
____________________________________________________________
```
<br />

### <a id="delete"></a>`delete` - Delete task
Deletes a task, based on its index.

Format: `delete TASK_INDEX`<br />
* Deletes the task at the specified `TASK_INDEX`.<br />
* The index refers to the index number shown in the displayed task list.<br />
* The index must be a positive integer 1, 2, 3, ...<br />

Example:
```
____________________________________________________________

delete 2
____________________________________________________________

Successfully deleted the following task:
   [D][ ] assignment (by: tuesday night)
Now you have 1 tasks in the list.
____________________________________________________________
```
<br />

### <a id="find"></a>`find` - Find tasks
Find tasks that contains the keyword from the list.

Format: `find KEYWORD`
* The search is case-insensitive. e.g. `read book` will match `READ BOOK`
* Task description containing the keyword will be returned
  e.g. `read` will return `read book` and `read magazine`

Example:
```
____________________________________________________________

find assignment
____________________________________________________________

The following tasks contain the matching keyword:
1. [D][ ] cs2113t assignment (by: tuesday night)
2. [D][X] cs2040c assignment (by: monday night)
____________________________________________________________
```

<br />

### <a id = "exit"></a> `bye` - Exiting the program
Exits the program.

Format: `bye`

<br />

### <a id="save"></a>Saving task data
Duke saves your task list from the usage into your local storage as a text file.<br />
Upon reinitialization, it reloads that file, so you access previously added tasks.

<br />

## <a id="summary"></a>Command Summary

Command | Format, Examples
--------|--------------
Add Todo | `todo TODO_DESCRIPTION` <br /> eg.`todo CS2113T IP`
Add Deadline | `deadline DEADLINE_DESCRIPTION /by DEADLINE` <br /> eg.`deadline CS2113T IP /by 01-10-2021`
Add Event | `event EVENT_DESCRIPTION /at TIME_RANGE` <br /> eg.`event CS2101 OP1 /at Thursday 10am-12pm`
List Tasks | `list`
Done Task | `done TASK_INDEX` <br /> eg.`done 1`
Delete Task | `delete TASK_INDEX` <br /> eg.`delete 2`
Find Task(s) | `find KEYWORD` <br /> eg.`find CS`