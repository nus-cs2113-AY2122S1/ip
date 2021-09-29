# User Guide
Duke is an easy-to-use task management CLI app.

* [Quick Start](#quick-start)
* [Features](#features)
    * [Add a todo task : `todo`](#add-a-todo-task---todo)
    * [Add a deadline task : `deadline`](#add-a-deadline-task---deadline)
    * [Add an event task : `event`](#add-an-event-task---event)
    * [Delete a task : `delete`](#delete-a-task---delete)
    * [Mark a task as done : `done`](#mark-a-task-as-done---done)
    * [Find a task : `find`](#find-a-task---find)
    * [List all tasks : `list`](#list-all-tasks---list)
    * [Exit the application : `bye`](#exit-todo---bye)
* [FAQ](#faq)
* [Summary](#command-summary)

## Quick start
1. Download the  [`ip.jar`](https://github.com/fchensan/ip/releases/tag/A-Release).
2. Copy the jar file into a  folder you want to use as the home folder.
3. Using the terminal, navigate to the directory where the `ip.jar` file is.
4. Run `java -jar ip.jar`
5. Upon correct setup you should see an introductory message.

## Features

### Add a Todo task - `todo`
Adds a todo task to the list

Format: `todo TASK_DESCRIPTION`

Example:
`todo read`

```
Got it. I've added this task:
[T][ ] read
```

### Add a Deadline task - `deadline`

Adds a deadline task to the list

Format: `deadline DESCRIPTION /by TIME`

Example:
`deadline CS2113 HW /by Monday`

```
Got it. I've added this task:
[D][ ] CS2113 HW (by: Monday)
```
### Add an Event task - `event`

Adds an event task with the `TIME` to the list

Format: `event TASK_DESCRIPTION /at TIME`

Example:
`event lecture /at friday 4`

```
Got it. I've added this task:
[E][ ] lecture (at: friday 4)
```

### Delete a task - `delete`

Deletes a task with its index number as shown in `list`.

Format: `delete NUMBER_OF_TASK`

Example:

```
list
____________________________________________________________
Here are the tasks in your list:
1. [E][ ] lecture (at: friday)
2. [D][ ] HW (by: 29 Oct 2021)
3. [T][ ] read a book
____________________________________________________________
delete 2
____________________________________________________________
Got it! I've removed this task:
[D][ ] HW (by: 29 Oct 2021)
Now you have 2 tasks in the list.
```


### Mark a task as done - `done`

Marks a task as done, given its index number as shown in `list`.

Format: `done NUMBER_OF_TASK`

Example:

```
list
____________________________________________________________
Here are the tasks in your list:
1. [E][ ] lecture (at: friday)
2. [D][ ] HW (by: 29 Oct 2021)
____________________________________________________________
done 2
____________________________________________________________
Nice! I've marked this task as done:
[D][X] HW (by: 29 Oct 2021)
____________________________________________________________
```

### Find tasks in list with a keyword - `find`

Find tasks in list with a keyword

Format: `find KEYWORD`

Example:

```
find CS2113T
____________________________________________________________
1. [T][ ] finish CS2113T homework
2. [T][ ] finish CS2113T Lecture
____________________________________________________________
```

### List all tasks - `list`
Lists all tasks.

Example:

```
list
____________________________________________________________
Here are the tasks in your list:
1. [E][ ] lecture (at: friday)
2. [D][ ] HW (by: 29 Oct 2021)
3. [T][ ] read a book
____________________________________________________________
```

### Exit Application - `bye`

Exits the app and saves all data into `data/savedData.txt`.

Example of usage:
```
bye
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

## FAQ
**1. Why am I getting the error message "☹ OOPS!!! I'm sorry, but I don't know what that means :-("?**
Check whether the command entered is correct. The error message implies that you have not given the correct command as input.

## Summary

**Action** | **Format, Examples**
------------ | -------------
**todo**|`todo DESCRIPTION` <br>e.g. `todo CS1231 ip project`
**deadline**|`deadline DESCRIPTION /by TIME` <br>e.g. `deadline CS2113 HW /by MONDAY`
**event**|`event DESCRIPTION /at TIME`<br>e.g. `event CS2113 lecture /at Friday 4pm`
**delete**|`delete NUMBER_OF_TASK` <br>e.g. `delete 1`
**done**|`done NUMBER_OF_TASK` <br>e.g. `done 1`
**find**|`find SEARCH_KEYWORD`<br>e.g. `find read`
**list**|`list`
**bye**|`bye`