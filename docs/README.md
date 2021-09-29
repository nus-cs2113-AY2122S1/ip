# User Guide

Duke is an application for users to manage and track Tasks via a Command Line Interface (CLI). It is useful for users that wish to have an organized and consolidated view of list of things that they plan to do. Duke is also built with data persistence, meaning data is stored such that information is not lost between sessions.

- [Quick Start](#quick-start)
- [Features](#features)
  - [View help: `help`](#help---display-help-message)
  - [List all Tasks: `list`](#list---list-all-tasks)
  - [Add Todo Task: `todo`](#todo---adds-a-todo-task)
  - [Add Deadline Task: `deadline`](#deadline---adds-a-deadline-task)
  - [Add Event Task: `event`](#event---adds-an-event-task)
  - [Mark Task as Done: `done`](#done---mark-task-as-done)
  - [Delete Task: `delete`](#delete---deletes-a-task)
  - [Check Schedule on Date: `schedule`](#schedule---list-all-tasks-on-specific-date)
  - [Search for Keyword in Tasks: `find`](#find---search-tasks)
  - [Purge all Tasks: `purge`](#purge---purge-all-tasks)
  - [Quit the program: `bye`](#bye---quits-the-program)
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

_________________________________________________________________
 Hello! I'm Duke
 What can I do for you?
 Type `help` to display help message
_________________________________________________________________

```


## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words that are enclosed by `<>` are the parameters to be supplied by the user.
  
* Must follow specific format for command to work.


</div>

--------------------------------------------------------------------------------------------------------------------
  
  
  
### `Help` - Display help message

Display command usage

Format: `help`

Expected outcome:

```
help
_________________________________________________________________
 Command   |   Usage:
 list      |   list
 todo      |   todo <TASK_DESCRIPTION>
 deadline  |   deadline <TASK_DESCRIPTION> /by <yyyy-MM-dd HH:mm>
 event     |   event <TASK_DESCRIPTION> /at <yyyy-MM-dd HH:mm>
 done      |   done <TASK_NUMBER>
 delete    |   delete <TASK_NUMBER>
 schedule  |   schedule <yyyy-MM-dd>
 find      |   find <KEYWORD>
 help      |   help
 purge     |   purge
 bye       |   bye

 Detailed User Guide at https://remusteo.github.io/ip/
_________________________________________________________________
```

  
  
### `List` - List all tasks.

List all tasks in the task list.

Format: `list`

Expected outcome:

```
list
_________________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[D][ ] return book (by: Sep 27 2021 18:00)
 3.[E][ ] attend book event (at: Sep 28 2021 16:00)
_________________________________________________________________
```

  
  
### `Todo` - Adds a todo task.

Adds a todo task into the task list

Format: `todo <TASK_DESCRIPTION>`

Expected outcome:

```
todo read book
_________________________________________________________________
 Got it. I've added this task:
   [T][ ] read book
 Now you have 1 tasks in the list.
_________________________________________________________________
```

  

### `Deadline` - Adds a deadline task.

Adds a deadline task into the task list

Format: `deadline <TASK_DESCRIPTION> /by <yyyy-MM-dd HH:mm>`

Excepted outcome:

```
deadline return book /by 2021-09-27 18:00
_________________________________________________________________
 Got it. I've added this task:
   [D][ ] return book (by: Sep 27 2021 18:00)
 Now you have 2 tasks in the list.
_________________________________________________________________
```

  

### `Event` - Adds an event task.

Adds an event task into the task list

Format: `event <TASK_DESCRIPTION> /at <yyyy-MM-dd HH:mm>`

Excepted outcome:

```
event attend book event /at 2021-09-28 16:00
_________________________________________________________________
 Got it. I've added this task:
   [E][ ] attend book event (at: Sep 28 2021 16:00)
 Now you have 3 tasks in the list.
_________________________________________________________________
```


  
### `Event` - Adds an event task.

Adds an event task into the task list

Format: `event <TASK_DESCRIPTION> /at <yyyy-MM-dd HH:mm>`

Excepted outcome:

```
event attend book event /at 2021-09-28 16:00
_________________________________________________________________
 Got it. I've added this task:
   [E][ ] attend book event (at: Sep 28 2021 16:00)
 Now you have 3 tasks in the list.
_________________________________________________________________
```

  

### `Done` - Mark task as done.

Mark a task as done by specifying task number

Format: `done <TASK_NUMBER>`

Excepted outcome:

```
done 1
_________________________________________________________________
 Nice! I've marked this task as done:
   [T][X] read book
_________________________________________________________________
```

  

### `Delete` - Deletes a task.

Delete a task by specifying task number

Format: `delete <TASK_NUMBER>`

Excepted outcome:

```
delete 1
_________________________________________________________________
 Noted. I've removed this task:
   [T][X] read book
 Now you have 2 tasks in the list.
_________________________________________________________________
```

  

### `Schedule` - List all tasks on specific date.

Check schedule on specific date

Format: `schedule <yyyy-MM-dd>`

Excepted outcome:

```
schedule 2021-09-27
_________________________________________________________________
 Here is your schedule on Sep 27 2021:
 1.[D][ ] return book (by: Sep 27 2021 18:00)
 2.[D][ ] buy new book (by: Sep 27 2021 10:00)
 3.[E][ ] book conference (at: Sep 27 2021 15:30)
_________________________________________________________________
```



### `Find` - Search tasks.

Search for keyword in task description

Format: `find <KEYWORD>`

Excepted outcome:

```
find buy
_________________________________________________________________
 Here are the matching tasks in your list:
 1.[D][ ] buy new book (by: Sep 27 2021 10:00)
_________________________________________________________________
```


### `Purge` - Purge all tasks.

Deletes all tasks

Format: `purge`

Excepted outcome:

```
purge
_________________________________________________________________
 All tasks purged!
_________________________________________________________________
```

### `Bye` - Quits the program.

Terminate Duke application safely 

Format: `bye`

Excepted outcome:

```
bye
_________________________________________________________________
 Bye. Hope to see you again soon!
_________________________________________________________________
```


### Data Storage
Duke will automatically save data in 'data/duke.txt' after any operation that modifies the task list. Duke will automatically load from 'data/duke.txt' on startup if it exists. Data is saved in a specifc format with fields delimited by a pipe `|`are saved by a pipe. [TASK_TYPE|IS_DONE|TASK_DESCRIPTION|DATETIME(if applicable)]
  
### Data Editing
It is possible to directly edit the data file but it is **NOT** recommended unless you know exactly what you are doing. It may result in unintended behaviour if data file is tampered with while the program is running.

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
| **Purge** | `purge` |
| **Bye** | `bye` |
