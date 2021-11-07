# User Guide
ToDo is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). If you can type fast, ToDo can get your task management done faster than traditional GUI apps.

* [Quick Start](#quick-start)
* [Features](#features)
    * [Add a Todo task : `todo`](#add-a-todo-task---todo)
    * [Add a Deadline task : `deadline`](#add-a-deadline-task---deadline)
    * [Add an Event task : `event`](#add-an-event-task---event)
    * [List all tasks : `list`](#list-all-tasks---list)
    * [Mark a task as done : `done`](#mark-a-task-as-done---done)
    * [Delete a task : `delete`](#delete-a-task---delete)
    * [Find a task : `find`](#find-a-task---find)
    * [Exit ToDo : `bye`](#exit-todo---bye)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick start
1. Make sure you have Java `11` or above installed.
2. Download the latest release of [`ip.jar`](https://github.com/fchensan/ip/releases/tag/A-Release).
3. Copy the jar file into a  folder you want to use as the home folder.
4. Using the terminal, navigate to the directory where the `ip.jar` file is.
5. Run `java -jar ip.jar`
6. If you have set up correctly you should see a welcome message.

## Features

### Add a Todo task - `todo`
Adds a todo task to the list

Format: `todo DESCRIPTION`

Example:
`todo finish my homework`

```
Got it. I've added this task:
[T][ ] finish my homework
```

### Add a Deadline task - `deadline`

Adds a deadline task with a date.

Format: `deadline DESCRIPTION /by yyyy-MM-dd`

Example:
`deadline submit CS2113T ip /by 2021-09-20`

```
Got it. I've added this task:
[D][ ] submit CS2113T ip (by: 20 Sep 2021)
```
### Add an Event task - `event` 

Adds an event task with the `EVENT_TIME` in no specific format.

Format: `event DESCRIPTION /at EVENT_TIME`

Example:
`event clay workshop /at sunday`

```
Got it. I've added this task:
[E][ ] clay workshop (at: sunday)
```

### List all tasks - `list`
Lists all the tasks.

Example:

```
list
____________________________________________________________
Here are the tasks in your list:
1. [E][ ] clay workshop (at: sunday)
2. [D][ ] CG2271 project video (by: 9 Nov 2021)
3. [T][ ] sleep 8hrs
____________________________________________________________
```

### Mark a task as done - `done`

Marks a task as done, given its index number as shown in `list`.

Format: `done TASK_INDEX`

Example:

```
list
____________________________________________________________
Here are the tasks in your list:
1. [E][ ] clay workshop (at: sunday)
2. [T][ ] sleep 8hrs
____________________________________________________________
done 2
____________________________________________________________
Nice! I've marked this task as done:
[T][X] sleep 8hrs
____________________________________________________________
```

### Delete a task - `delete` 

Deletes a task with its index number as shown in `list`.

Format: `delete TASK_INDEX`

Example:

```
list
____________________________________________________________
Here are the tasks in your list:
1. [E][ ] clay workshop (at: sunday)
2. [D][ ] CG2271 project video (by: 9 Nov 2021)
3. [T][ ] sleep 8hrs
____________________________________________________________
delete 2
____________________________________________________________
Got it! I've removed this task:
[D][ ] CG2271 project video (by: 9 Nov 2021)
Now you have 2 tasks in the list.
```

### Find a task - `find`

Find a task with a keyword.

Format: `find KEYWORD`

Example:

```
find CS2113T
____________________________________________________________
1. [T][ ] finish CS2113T homework
2. [T][ ] finish deliverables for CS2113T project
____________________________________________________________
```

### Exit ToDo - `bye`

Exits ToDo and saves all data into `data/savedData.txt`.

Example of usage:
```
bye
____________________________________________________________
Bye!
____________________________________________________________
```

## FAQ
**1. How do I move my data to another computer?**
Simply copy `data\savedData.txt` to the other computer. You should run `ip.jar` from the same directory
where `data\savedData.txt` is.

## Command Summary

**Action** | **Format, Examples**
------------ | -------------
**todo**|`todo DESCRIPTION` <br>e.g. `todo CS1231 homework`
**deadline**|`deadline DESCRIPTION /by yyyy-mm-dd` <br>e.g. `deadline GEQ1000 essay /by 2021-10-09`
**event**|`event DESCRIPTION /at EVENT_TIME`<br>e.g. `event NUS Libraries Event /at Monday noon`
**list**|`list`
**done**|`done TASK_INDEX` <br>e.g. `done 2`
**delete**|`delete TASK_INDEX` <br>e.g. `delete 7`
**find**|`find KEYWORD`<br>e.g. `find lecture`
**exit**|`bye`