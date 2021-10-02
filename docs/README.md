
![](https://i.imgur.com/nWV4Y9h.png)

# DUKE : Task Scheduler

**Duke Task Scheduler** (DTS) is a desktop app for **managing tasks and todos**,
optimized for use via a **Command Line Interface (CLI)**. DTS is the simple, fast, and elegant solution
for all your task scheduling needs.

[![Build Status](http://img.shields.io/travis/badges/badgerbadgerbadger.svg?style=flat-square)](https://travis-ci.org/badges/badgerbadgerbadger)

> Tested and ran on Windows 10, Java 11

---

## Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
    * [Viewing help](#viewing-help--help)
    * [Listing tasks](#listing-tasks-list)
    * [Querying tasks](#querying-tasks-find-regex-type-tasktype-limit-querylimit)
    * [Adding a Todo](#adding-a-todo-todo-description)
    * [Adding a Deadline](#adding-a-deadline-deadline-description-by-datetime)
    * [Adding an Event](#adding-an-event-event-description-at-datetime)
    * [List valid DateTime Formats](#list-valid-datetime-formats-dates)
    * [Removing a task](#removing-a-task-delete-idx)
    * [Marking a task as done](#marking-a-task-as-done-done-idx)
    * [Exit](#exit-the-application--bye)
    * [Local Save](#local-save)
- [FAQ](#faq)
- [Command Summary]()

---

## Quick Start

1. Ensure that you have Java `11` or above installed in your computer
2. Download the latest `duke.jar` from [here]()
3. Copy the file to the folder you want to use as the home folder for your `Task Scheduler`.
4. Double-click the file to start the app.

You should see the following output :
```bash
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

_________________________
Hello! I'm Duke.Duke
Standby while I load up your schedule
Loading...
Here are your scheduled tasks!
1.[T][ ] read book 2
2.[T][ ] read book
3.[D][ ] return book (by: May 29 1998 18:00)
4.[D][ ] eat food (by: Sep 18 2021 23:59)
You now have (4) tasks!
------------------------------------------
What can I do for you?

```
5. Note the sample tasks already present! Type a command and press `Enter` to execute it.
   e.g. typing `help` will display all possible commands that **DUKE** accepts.

Some example commands you can try:
- `list` : lists all tasks
- `todo <description>` : adds a TODO task into the task list
- `deadline <description> /by <datetime>` : adds a DEADLINE task to be completed by `<datetime>` into task list
- `event <description> /at <datetime>` : adds a EVENT task that will take place at `<datetime>` into task list
- `delete <idx>` : deletes a task by its index

---
## Features

>:information_source: **Notes about the command formats**
> - Words in `<UPPER_CASE>` are the parameters to be given by the user. <br />
> e.g. in `todo <DESCRIPTION>`, <DESCRIPTION> is a parameter and be called like so : `todo eat breakfast`
> <br /><br />
> - Items in square brackets are optional <br />
> e.g. find `find <REGEX> [/type TASKTYPE]` 
> can be called as `find eat /type todo` OR `find eat`.
> <br /><br />
> - Parameters can be in any order for optional flags <br />
> e.g. `find <REGEX> [/type TASKTYPE] [/limit QUERYLIMIT]` is equivalent to `find <REGEX> [/limit QUERYLIMIT] [/type TASKTYPE] `
> <br /><br />
> - Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `bye`) will be ignored.<br />
> e.g. `help abc` will be interpreted as  `help`

### Viewing help : `help`
This will list all the valid commands that DUKE accepts.
```shell
~$ help
------------------------------------------
These are the valid commands: 
 > bye
 > help
 > dates
 > list
 > find
 > todo
 > deadline
 > event
 > delete
 > done
------------------------------------------
```
<br />

### Listing tasks: `list`
This command lists all the tasks current registered in the the task list
```shell
list
------------------------------------------
Here are your scheduled tasks!
1.[T][ ] read book 2
2.[T][ ] read book
3.[D][ ] return book (by: May 29 1998 18:00)
4.[D][ ] eat food (by: Sep 18 2021 23:59)
------------------------------------------
```
The format for each Task printed is:
`[TaskType][isDone] Description`

**TaskType:**
* T -> TODO task
* D -> DEADLINE Task
* E -> EVENT Task

**isDone:**
* \[ ]  -> not done
* \[X] -> done

<br />

### Querying tasks: `find <REGEX> [/type TASKTYPE] [/limit QUERYLIMIT]`
Queries the task list by a word or phrase.
- The **task type** can be specified for query by using the optional flag `/type`
    * e.g. `/type todo` will filter the query to only return TODO tasks


- The **number of tasks** returned can be limited by the optional flag `/limit`
    * e.g `/limit 5` will filter the query to only return the top 5 tasks


- Any remaining tasks that contains the `REGEXx` will be displayed

Example 1:
```shell
~$ find book
------------------------------------------
Your query returned the following results: 
1.[T][ ] read book
2.[D][ ] return book (by: May 29 1998 18:00)
3.[T][ ] read book 2
------------------------------------------
```

Example 2:
```shell
~$ find book /type todo
------------------------------------------
Your query returned the following results: 
1.[T][ ] read book
2.[T][ ] read book 2
------------------------------------------
```

<br />

### Adding a TODO: `todo <DESCRIPTION>`
Adds a *TODO* task to the task list. It is set to **not done** by default
- **DESCRIPTION** : the Task description

Example: `todo eat dinner`
```shell
~$ todo eat dinner
------------------------------------------
Got it. I've added this task: 
[T][ ] eat dinner
You now have (5) tasks!
------------------------------------------
```
<br />

### Adding a DEADLINE: `deadline <DESCRIPTION> /by <DATETIME>`
Adds a *DEADLINE* task to the task list. It is set to **not done** by default
- **DESCRIPTION** : the Task description
- **/by** : is a *REQUIRED* clause when adding a deadline
- **DATETIME** : the *date* and *time* that the task is due by. This **CANNOT BE NULL/EMPTY**.
> :warning: Note: datetime needs to be given in a valid format.
> See [Dates](#list-valid-datetime-formats-dates) for more details

Example: `deadline eat lunch /by today 1200`
```shell
~$ deadline eat lunch /by today 1200
------------------------------------------
Got it. I've added this task: 
[D][ ] eat lunch (by: Sep 20 2021 12:00)
You now have (6) tasks!
------------------------------------------

```
<br />

### Adding an EVENT: `event <DESCRIPTION> /at <DATETIME>`
Adds a *EVENT* task to the task list. It is set to **not done** by default
- **DESCRIPTION** : the Task description
- **/at** : is a *REQUIRED* clause when adding a deadline
- **DATETIME** : the *date* and *time* that the task takes place. This **CANNOT BE NULL/EMPTY**.
> :warning: Note: datetime needs to be given in a valid format.
> See [Dates](#list-valid-datetime-formats-dates) for more details

Example: `event family dinner /at today 1930`
```shell
~$ event family dinner /at today 1930
------------------------------------------
Got it. I've added this task: 
[E][ ] family dinner (at: Sep 20 2021 19:30)
You now have (7) tasks!
------------------------------------------
```

<br />

### List valid DateTime Formats: `dates`
Lists all the valid datetime formats to be used when
adding a [Deadline](#adding-a-deadline-deadline-description-by-datetime)
or [Event](#adding-an-event-event-description-at-datetime).

Example: `help`
```shell
~$ dates
------------------------------------------
Here are the valid DateTime formats to use: 
-> MMM d yyyy HH:mm
-> MMM d yyyy HHmm
-> MMM d yy HH:mm
-> MMM d yy HHmm
-> dd/M/yyyy HH:mm
-> dd/M/yyyy HHmm
-> dd/M/yy HH:mm
-> dd/M/yy HHmm
-> today HH:mm
-> today HHmm
------------------------------------------

```
> :bulb: Refer to the following Table for example of valid date time formats:

| Pattern Syntax | Example    |
| -------------- | ---------- |
| MMM d yyyy     | Oct 10 2015|
| MMM d yy       | Oct 10 15  |
| dd/M/yyyy      | 15/10/2015 |
| dd/M/yy        | 15/10/15   |
| today          | today      |
| HH:mm          | 18:00      |
| HHmm           | 1800       |

<br />

### Removing a task: `delete <IDX>`
Removes a task from the task list, by their **idx**
- **IDX** : index as displayed in the full list (**NOT QUERIED LIST**)

Example: `list` -> `delete 6`
```shell
~$ list
------------------------------------------
Here are your scheduled tasks!
1.[T][ ] eat dinner
2.[T][ ] read book 2
3.[T][ ] read book
4.[D][ ] return book (by: May 29 1998 18:00)
5.[D][ ] eat food (by: Sep 18 2021 23:59)
6.[D][ ] eat lunch (by: Sep 20 2021 12:00)
7.[E][ ] family dinner (at: Sep 20 2021 19:30)
------------------------------------------

~$ delete 6
------------------------------------------
Noted. I've removed this task: 
[D][ ] eat lunch (by: Sep 20 2021 12:00)
You now have (6) tasks!
------------------------------------------
```

<br />

### Marking a task as done: `done <IDX>`

Marks a task as done, by their **IDX**.

- **IDX** : index as displayed in the full list (**NOT QUERIED LIST**)

Example: `list` -> `done 5`
```shell
~$ list
------------------------------------------
Here are your scheduled tasks!
1.[T][ ] eat dinner
2.[T][ ] read book 2
3.[T][ ] read book
4.[D][ ] return book (by: May 29 1998 18:00)
5.[D][ ] eat food (by: Sep 18 2021 23:59)
6.[E][ ] family dinner (at: Sep 20 2021 19:30)
------------------------------------------

~$ done 5
------------------------------------------
Nice! I've marked this task as done: 
[T][X] eat dinner
Here are your scheduled tasks!
1.[T][ ] eat dinner
2.[T][ ] read book 2
3.[T][ ] read book
4.[D][ ] return book (by: May 29 1998 18:00)
5.[D][X] eat food (by: Sep 18 2021 23:59)
6.[E][ ] family dinner (at: Sep 20 2021 19:30)
------------------------------------------
```

<br />

### Exit the application : `bye`
Exits and closes **DUKE**.

Example: `bye`
```shell
~$ bye
------------------------------------------
Bye. Hope to see you again soon!
------------------------------------------
```
<br />

### Local Save
All updates made to the task list is automatically saved to a local file
> Note: Save will be automatically loaded up on application start up, if valid

<br />

---

## FAQ

**Q:** Why am I getting DukeExceptions with my input

**A:** The input syntax is likely invalid, check with the documentation listed in [Features](#features)
        to make sure that your input is valid. The exception likely shows what went wrong too!

<br />

**Q:** How do I edit a task I previously registered?

**A:** Edit is currently unsupported, but an alternative option is to delete the task
        and re-add the updated task back to the list

<br />

**Q:** How do I transfer my data to another PC?

**A:** Install the app in the other computer and overwrite the empty data file 
it creates with the file that contains the data of your previous Duke home folder.

<br />

---

## Command Summary

| Command                                               | Meaning                                                               |
| --------------                                        | ----------                                                            |
| `help`                                                | Lists all accepted commands                                           |
| `list`                                                | Lists all registered tasks in the task list                           |
| `find <REGEX> [/type TASKTYPE] [/limit QUERYLIMIT]`   | Queries the task list with a keyword and *type / limit* flags         |
| `todo <DESCRIPTION>`                                  | Adds a todo task with <DESCRIPTION>                                   |
| `deadline <DESCRIPTION> /by <DATETIME>`               | Adds a deadline task with <DESCRIPTION> and deadline                  |
| `event <DESCRIPTION> /at <DATETIME>`                  | Adds an event task with <DESCRIPTION> and time of event               |
| `dates`                                               | Lists the valid datetime formats for use in adding deadline/event     |
| `delete <IDX>`                                        | Removes a task from the task list by its sorted index                 |
| `done <IDX`                                           | Marks a task as done in the task list by its sorted index             |
| `bye`                                                 | Exits the application                                                 |
    