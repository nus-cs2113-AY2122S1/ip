# User Guide

Duke Bot is a **desktop app for managing Tasks through a Command Line Interface (CLI)**. If you can
type fast, Duke can get your management tasks done faster than traditional GUI apps.

- [User Guide](#user-guide)
    * [Quick Start](#quick-start)
    * [Features](#features)
        + [Adding Todo Task: `todo`](#adding-todo-task)
        + [Adding Deadline Task: `deadline`](#adding-deadline-task)
        + [Adding Event Task: `event`](#adding-event-task)
        + [Display all tasks in task list: `list`](#display-all-tasks-in-task-list)
        + [Remove task from task list: `delete`](#remove-task-from-task-list)
        + [Mark a task as done: `done`](#mark-a-task-as-done)
        + [Display all task that falls on a specified date: `date`](#display-all-task-that-falls-on-a-specified-date)
        + [Display all tasks that contains a keyword: `find`](#display-all-tasks-that-contains-a-keyword)
        + [Exit the program: `bye`](#exit-the-program)
        + [Saving the data](#saving-the-data)
    + [Common Errors](#common-errors)
    * [Command summary](#command-summary)

---

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest ip.jar from [here](https://github.com/Woolicious98/ip/releases/tag/A-Release)
   .
3. Copy the file to the folder you want to use as the home folder for your Duke Bot.
4. Open your command prompt and go into the directory containing the `ip.jar` file.
    1. You can do so by `win+R` and run `cmd`.
    2. In the console type `cd C:/directoryContainingIp` where directoryContainingIp is your folder
       containing the jar file.
5. In the command prompt, run `java -jar ip.jar`
6. To exit the program, run `bye`
7. Refer to the [Features](#features) below for details of each command.

---

## Features<a name="features"></a>

Notes about the commands format

* All datetime inputs must be in `yyyy-MM-dd HH:mm` format. For example
  `2021-09-24 11:09` represents the `24 September 2021 11:09pm`.
* All date inputs must be in `yyyy-MM-dd` format. For example
  `2021-09-24` represents the `24 September 2021`.
* Parameters must be given in the format specified.

### Adding Todo Task: `todo`<a name="adding-todo-task"></a>

Adds a todo task into the task list.

Format: `todo <task description>`

Tags: None

Examples:

* `todo read book`
* `todo return book`

Expected Outcomes:

```
>>> todo read book
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list
____________________________________________________________
```

Error Messages:

1. Missing `<task description>` information.

```
Error: Incorrect format detected.
todo <task description>
```

### Adding Deadline Task: `deadline`<a name="adding-deadline-task"></a>

Adds a deadline task into task list.

Format: `deadline <task description> /by <due date & time>`

Tags: `/by`

Examples:

* `deadline read book /by 2021-09-24 00:00`

Expected Outcomes:

```
>>> deadline read book /by 2021-09-24 00:00
Got it. I've added this task:
[D][ ] read book (by: Sep 24 2021, 00:00)
Now you have 1 tasks in the list
____________________________________________________________
```

Error Messages:

1. Missing/Incorrect `/by` tag or missing `<task description>` information.

```
Error: Incorrect format detected.
deadline <task description> /by <due date>
```

2. Invalid Date & Time Format `yyyy-MM-dd HH:mm`.

```
Error: Incorrect date time format. Format: yyyy-MM-dd HH:mm
```

### Adding Event Task: `event`<a name="adding-event-task"></a>

Add an event task into task list.

Format: `event <task description> /at <start date & time>`

Tags: `/at`

Content from cell 1 | Content from cell 2

Examples:

* `event read book /at 2021-09-24 00:00`

Expected Outcomes:

```
>>> event read book /at 2021-09-24 00:00
Got it. I've added this task:
[E][ ] read book (at: Sep 24 2021, 00:00)
Now you have 1 tasks in the list
____________________________________________________________
```

Error Messages:

1. Missing/Incorrect `/at` tag or missing `<task description>` information.

```
Error: Incorrect format detected.
event <task description> /at <start date>
```

2. Invalid Date & Time Format `yyyy-MM-dd HH:mm`.

```
Error: Incorrect date time format. Format: yyyy-MM-dd HH:mm
```

### Display all tasks in task list: `list`<a name="display-all-tasks-in-task-list"></a>

Prints all task information inside the task list.

Format: `list`

Tags: None

Examples:

- `list`

Expected Outcomes:

- Task List is not empty.

```
>>> list
Here are the tasks in your list:
1.[E][ ] read book (at: Sep 24 2021, 00:00)
2.[T][ ] return book
3.[D][ ] copy book (by: Sep 24 2021, 11:15)
There are currently 3 tasks in your list.
____________________________________________________________
```

- No task has been added to task list yet.

```
>>> list
Here are the tasks in your list:
There are currently 0 tasks in your list.
____________________________________________________________
```

### Remove task from task list: `delete`<a name="remove-task-from-task-list"></a>

Delete existing task in the task list.

Format: `delete <task number>`

Tags: None

Usage:

1. You will need to use `list` command to get the task number associated with that task to be
   deleted.
2. Run `delete <number you see from list>`. <b style="color:red">Notice:</b> **no confirmation** is
   prompt, upon running **task is immediately removed** and **cannot be recovered**.
3. This will delete the task from the task list.

Examples:

* `delete 1`

Expected Outcomes:

```
>>> delete 1
Noted. I've removed this task:
[E][ ] read book (at: Sep 24 2021, 00:00)
Now you have 2 tasks in the list
____________________________________________________________
```

Error Messages:

1. Missing / Invalid task number provided.

```
Error: Invalid Task Number.
```

2. Valid task number provided but number out of range in task list.

```
Error: task not found.
```

### Mark a task as done: `done`<a name="mark-a-task-as-done"></a>

Set a task to done. This is for tasks that has been completed by the user.

Format: `done <task number>`

Tags: None

Usage:

1. You will need to use `list` command to get the task number associated with that task to be marked
   as done.
2. Run `done <number you see from list>`. <b style="color:red">Notice:</b> **no confirmation** is
   prompt, upon running, **task is marked as done** and **action cannot be reverted**.
3. This will mark the task as done, displaying a `[X]` status when using the `list` command again.

Examples:

* `done 1`

Expected Outcomes:

```
>>> done 3
Nice! I've marked this task as done:
[T][X] read book
____________________________________________________________
```

Error Messages:

1. Missing / Invalid task number provided.

```
Error: Invalid Task Number.
```

2. Valid task number provided but number out of range in task list.

```
Error: task not found.
```

### Display all task that falls on a specified date: `date`<a name="display-all-task-that-falls-on-a-specified-date"></a>

User will provide a date and the program will print all task that happens on the given date.

Format: `date <date>`

Tags: None

Example:

* `date 2021-09-24`

Expected Outcomes:

1. There exist 1 or more task that falls on that given date.

```
>>> date 2021-09-24
The date "2021-09-24" have:
[D][X] return book (by: Sep 24 2021, 23:59)
[E][ ] read book (at: Sep 24 2021, 00:10)

____________________________________________________________
```

2. No task found that falls on that given date.

```
>>> date 1990-09-09
The date "1990-09-09" have:

____________________________________________________________
```

Error Messages:

1. Missing / Invalid date format given. Date format: `yyyy-MM-dd`.

```
Error: Incorrect format detected.
date yyyy-MM-dd
```

### Display all tasks that contains a keyword: `find`<a name="display-all-tasks-that-contains-a-keyword"></a>

Print all tasks whom task description contains the given keyword.

Format: `find <keyword>`

Tags: None

Examples:

* `find book`

Expected Outcomes:

1. There exists 1 or more task whose description contains the given keyword.

```
>>> find book
The keyword "book" you provided found:
[D][X] return book (by: Sep 24 2021, 23:59)
[T][ ] read book
[E][ ] read book (at: Sep 24 2021, 00:10)

____________________________________________________________
```

2. No task is found whose description contains the given keyword.

```
>>> find study
The keyword "study" you provided found:

____________________________________________________________
```

Error Messages:

1. Missing / Empty`""` keyword provided.

```
Error: keyword is non existent.
```

### Exit the program: `bye`

Exits the program.

Format: `bye`

Tags: None

Examples:

* `bye`

Expected Outcomes:

```
>>> bye
My time has come...
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### Saving the data

Saving the data Task data are saved in the hard disk automatically after any command that changes
the data. There is no need to save manually.

The file directory and file name are hardcoded in the program, and they are `data` and `duke.txt`
respectively.

<b style="color:red;">Notice:</b> the data saved **will overwrite the existing file** provided.

----

## Common Errors

### Invalid command

Current existing Commands are `list`, `todo`, `deadline`, `event`, `date`, `delete`, `done`, `find`
and `bye`.

When you run any invalid commands, this error message will appear:

```
Error: Command not found.
```

### Data input from file

The `duke.txt` file you provided need to follow a specific format. 

The format is as follows: 

`<task type>|<done status>|<task description>|<other additional arguments>`

For example:
`[D]|1|return book|2021-09-24 23:59` where `[D]` stands for `deadline` task and `1` stands for done.

Error message you could encounter:

* duke.txt:
```
[D]|1|return book|2021-09-24 23:78
[X]|0|fin project|2021-09-30 00:00
[T]|4|read book
[E]|0|||||
```

```
Loading data from duke.txt...
____________________________________________________________
Error: Invalid date input. "[D]|1|return book|2021-09-24 23:78"
Error: Task type does not exist. "[X]|0|fin project|2021-09-30 00:00"
Error: done status is not correct. "[T]|4|read book"
Error: Invalid input format, missing arguments. "[E]|0|||||"
____________________________________________________________
Finish Loading.
```

----

## Command summary

Action | Format, Examples
------|------------
todo|`todo <task description>` eg. `todo read book`
deadline|`deadline <task description> /by <datetime yyyy-MM-dd HH:mm>` eg. `deadline read book /by 2021-09-24 10:10`
event|`event <task description> /at <datetime yyyy-MM-dd HH:mm>` eg. `event read book /at 2021-09-24 10:10`
list|`list`
delete|`delete <task number>` eg. `delete 1`
done|`done <task number>` eg. `done 1`
find|`find <keyword>` eg. `find book`
date|`date <date yyyy-MM-dd>` eg. `date 2021-09-24`
bye|`bye`