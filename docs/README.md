# User Guide

Duke is a simple command line app to manage and store your tasks.

## Table of Contents

* [Quick Start](#quick-start)
* [Features](#features)
  * [Add a todo - `todo`](#todo---add-a-todo)
  * [Add an event - `event`](#event---add-an-event)
  * [Add a deadline - `deadline`](#deadline---add-a-deadline)
  * [List all tasks - `list`](#list---list-tasks)
  * [Search tasks - `find`](#find---search-tasks)
  * [Mark a task as done - `done`](#done---mark-a-task-as-done)
  * [Delete a task - `delete`](#delete---delete-a-task)
  * [List commands - `help`](#help---view-help)
  * [Exit the app - `bye`](#bye---say-goodbye-to-duke)

<br/>

## Quick Start

1. Ensure that you have Java `11` or above installed in your computer.
2. Download the latest `Duke.jar` from [here](https://github.com/rebchua39/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Write the command `java -jar Duke.jar` to start the application.

You should see the following output:

```
Hello! I'm Duke, your personal task assistant.
Get started by adding a task, or type help to see what I can do.
```

If you are starting the app for the first time, you may also see this:

```
You don't have any saved tasks!
```

<br/>

## Features

### `todo` - add a todo

Adds a todo to the list.

Usage:
`todo [task description]`

Example of usage:

`todo read lecture notes`

Expected outcome:

Shows the added todo and its description.

```
Got it. I've added this task:
   [T][ ] read lecture notes
Now you have 1 tasks in the list.
```

<br/>

### `event` - add an event

Adds an event to the list.

Usage:
`event [event description] /at [date]`

Example of usage:

`event attend lecture /at Friday 4pm`

Expected outcome:

Shows the added event and its description.

```
Got it. I've added this task:
   [E][ ] attend lecture (at: Friday 4pm)
Now you have 2 tasks in the list.
```

<br/>

### `deadline` - add a deadline

Adds a deadline to the list.

Usage:
`deadline [task description] /by [date]`

Example of usage:

`deadline attempt quiz /by Sunday 11pm`

Expected outcome:

Shows the added deadline and its description.

```
Got it. I've added this task:
   [D][ ] attempt quiz (by: Sunday 11pm)
Now you have 3 tasks in the list.
```

<br/>

### `list` - list tasks

Lists all tasks (to-dos, events, deadlines).

Usage: `list`

Expected outcome:

Shows the list of added tasks.

```
Here are the tasks in your list:
1. [T][ ] read lecture notes
2. [E][ ] attend lecture (at: Friday 4pm)
3. [D][ ] attempt quiz (by: Sunday 11pm)
```

<br/>

### `find` - search tasks

Finds tasks containing the given keyword(s).

Usage:`find [keywords]`

Example of usage:

`find lecture`

Expected outcome:

Shows a list of tasks containing the keyword(s). 
```
Here are the tasks in your list:
1. [T][ ] read lecture notes
2. [E][ ] attend lecture (at: Friday 4pm)
```

<br/>

### `done` - mark a task as done

Marks a task as completed.

Usage:
`done [task number]`

Example of usage:

`done 1`

Expected outcome:

Shows the completed task.
```
Nice! I've marked the task as done:
   [T][X] read lecture notes
```

<br/>

### `delete` - delete a task

Deletes a task from the list.

Usage:
`delete [task number]`

Example of usage:

`delete 1`

Expected outcome:

Shows the deleted task.
```
Noted. I deleted this task:
   [T][X] read lecture notes
Now you have 2 tasks in the list.
```

<br/>

### `help` - view help

Shows a list of commands

Usage: `help`

Expected outcome:

Shows a list of commands and their usage.
```
Here are a list of commands:

list - lists all tasks

todo - adds a new to-do task
usage: todo [task description]
example: todo organise my desk

deadline - adds a new task with a due date
usage: deadline [task description] /by [due date]
example: deadline English assignment /by Wed 5pm

:
:
```

<br/>

### `bye` - say goodbye to Duke

Exits the app.

Usage: `bye`

Alternatives: `exit`, `quit`

Expected outcome:

Shows a message and exits the app.
```
Bye. Hope to see you again soon!
```
