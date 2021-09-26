# User Guide

Duke is an application for users to manage and track Tasks via a Command Line Interface (CLI). Duke is built with data persistence, meaning data is stored such that information is not lost between sessions.

- [Quick Start](#quick-start)
- [Features](#features)
  - [List all Tasks: `list`](#list---list-all-tasks)
  - [Add Todo Task: `todo`](#todo---adds-a-todo-task)
  - [Add Deadline Task: `deadline`](#deadline---adds-a-deadline-task)
  - [Add Event Task: `event`](#event---adds-an-event-task)
  - [Mark Task as Done: `done`](#done---mark-task-as-done)
  - [Delete Task: `delete`](#delete---deletes-a-task)
  - [Check Schedule on Date: `schedule`](#schedule---list-all-tasks-on-specific-date)
  - [Search for Keyword in Tasks: `find`](#find---search-tasks)
  - [Quit the program: `bye`](#bye---quits-the-program)
  - [View help: `help`](#help---displays-the-help-message)
  - [Data Storage](#data-storage)
  - [Data Editing](#data-editing)
- [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick Start
1. Ensure you have Java `11` installed in your computer.
2. Download the latest `duke.jar` from [here]().
3. Copy the file to the folder you want to use as the *home folder* for your Duke application.
4. Run the application using the command `java -jar duke.jar`.

```
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

____________________________________________________________
 Hello! I'm Duke
 What can I do for you?
____________________________________________________________

```


## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words that are enclosed by `<>` are the parameters to be supplied by the user.
  
* Must follow specific format for command to work.


</div>

--------------------------------------------------------------------------------------------------------------------

### `List` - List all tasks.

List all tasks in the task list.

Format: `list`

Expected outcome:

```
list
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[D][ ] return book (by: Sep 27 2021 18:00)
 3.[E][ ] attend book event (at: Sep 28 2021 16:00)
____________________________________________________________
```

### `Todo` - Adds a todo task.

Adds a todo task into the task list

Format: `todo <TASK_DESCRIPTION>`

Expected outcome:

```
todo read book
____________________________________________________________
 Got it. I've added this task:
   [T][ ] read book
 Now you have 1 tasks in the list.
____________________________________________________________
```



### `Deadline` - Adds a deadline task.

Adds a deadline task into the task list

Format: `deadline <TASK_DESCRIPTION> /by <yyyy-MM-dd HH:mm>`

Excepted outcome:

```
deadline return book /by 2021-09-27 18:00
____________________________________________________________
 Got it. I've added this task:
   [D][ ] return book (by: Sep 27 2021 18:00)
 Now you have 2 tasks in the list.
____________________________________________________________
```



### `Event` - Adds an event task.

Adds an event task into the task list

Format: `event <TASK_DESCRIPTION> /at <yyyy-MM-dd HH:mm>`

Excepted outcome:

```
event attend book event /at 2021-09-28 16:00
____________________________________________________________
 Got it. I've added this task:
   [E][ ] attend book event (at: Sep 28 2021 16:00)
 Now you have 3 tasks in the list.
____________________________________________________________
```



### `Event` - Adds an event task.

Adds an event task into the task list

Format: `event <TASK_DESCRIPTION> /at <yyyy-MM-dd HH:mm>`

Excepted outcome:

```
event attend book event /at 2021-09-28 16:00
____________________________________________________________
 Got it. I've added this task:
   [E][ ] attend book event (at: Sep 28 2021 16:00)
 Now you have 3 tasks in the list.
____________________________________________________________
```



### `Done` - Mark task as done.

Mark a task as done by specifying task number

Format: `done <TASK_NUMBER>`

Excepted outcome:

```
done 1
____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] read book
____________________________________________________________
```



### `Delete` - Deletes a task.

Delete a task by specifying task number

Format: `delete <TASK_NUMBER>`

Excepted outcome:

```
delete 1
____________________________________________________________
 Noted. I've removed this task:
   [T][X] read book
 Now you have 2 tasks in the list.
____________________________________________________________
```



### `Schedule` - List all tasks on specific date.

Check schedule on specific date

Format: `schedule <yyyy-MM-dd>`

Excepted outcome:

```
schedule 2021-09-27
____________________________________________________________
 Here is your schedule on Sep 27 2021:
 1.[D][ ] return book (by: Sep 27 2021 18:00)
 2.[D][ ] buy new book (by: Sep 27 2021 10:00)
 3.[E][ ] book conference (at: Sep 27 2021 15:30)
____________________________________________________________
```



### `Find` - Search tasks.

Search for keyword in task description

Format: `find <KEYWORD>`

Excepted outcome:

```
find buy
____________________________________________________________
 Here are the matching tasks in your list:
 1.[D][ ] buy new book (by: Sep 27 2021 10:00)
____________________________________________________________
```



### `Bye` - Quits the program.

Terminate Duke application safely 

Format: `bye`

Excepted outcome:

```
bye
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```


### Data Storage
Duke will automatically saves the data in 'data/duke.txt' after safely terminating Duke through the `bye` command. Duke will automatically load from 'data/duke.txt' on startup if it exists.

### Data Editing
Duke's data is saved in `data/duke.txt`. It is saved in a specifc format with fields delimited by a pipe `|`are saved by a pipe. It is **NOT** recommended to directly edit from this file unless you know exactly what you are doing. 

<div markdown="span" class="alert alert-warning">:exclamation: CAUTION:
If your changes to the data file makes its format invalid, Duke will crash and will not be able to start up. In that case, either fix the format or in the worst case, discard all data by deleting data/duke.txt`
</div>

--------------------------------------------------------------------------------------------------------------------

## Command Summary
  
| Action | Format |
| --- | --- |
| **List** | `list` |
| **Todo** | `todo <TASK_DESCRIPTION>` |
| **Deadline** | `deadline <TASK_DESCRIPTION> /by <yyyy-MM-dd HH:mm>` |
| **Event** | `event <TASK_DESCRIPTION> /at <yyyy-MM-dd HH:mm>` |
| **Done** | `done <TASK_NUMBER>` |
| **Delete** | `delete <TASK_NUMBER>` |
| **Schedule** | `schedule <yyyy-MM-dd>` |
| **Find** | `find <KEYWORD>` |
| **Bye** | `bye` |
