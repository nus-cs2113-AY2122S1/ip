# User Guide

**Duke** is a desktop app to **manage tasks and keep a track of things**.
It is a **Personal Assistant Chatbot** that can store data and help you keep a track of your scheduled tasks.
It is optimized for use via a Command Line Interface (CLI) that can help you manage your tasks faster than the traditional GUI apps.



## Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
    * [Viewing help](#viewing-help--help)
    * [Adding a Todo](#adding-a-todo-todo-description)
    * [Adding a Deadline](#adding-a-deadline-deadline-description-by-datetime)
    * [Adding an Event](#adding-an-event-event-description-at-datetime)
    * [Listing all Tasks](#listing-tasks-list)
    * [Marking a task as done](#marking-a-task-as-done-done-idx)
    * [Deleting a task](#deleting-a-task-delete-idx)
    * [Searching for a task](#querying-tasks-find-regex-type-tasktype-limit-querylimit)
    * [Saving the tasks](#saving-the-tasks)
    * [Exiting the program](#exit-the-application--bye)

- [FAQ](#faq)
- [Command Summary]()

-------------------------------------------------

## Quick Start

1. Ensure that you have Java `11` or above installed in your computer
2. Download the latest `ip.jar` from [here](https://github.com/aditichadha1310/ip)
3. Copy the file to the folder you want to use as the home folder for your `Task Scheduler`.
4. Double-click the file to start the app

You should see the following output :
```bash
____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________

```
5. Note the sample tasks already present! Type a command and press `Enter` to execute it.
   e.g. typing `help` will display all possible commands that **DUKE** accepts.

Some example commands you can try:
- `list` : Lists all tasks.
- `todo <description>` : Adds a TODO task in the task list.
- `deadline <description> /by <Date_Time>` : Adds a DEADLINE task to be completed by `<datetime>` in the task list.
- `event <description> /at <Date_Time>` : Adds an EVENT task that will take place at `<datetime>` in the task list.
- `delete <5>` : Deletes the 5th task from the current list.
- `bye` : Exits the application.

---
## Features

**Notes about the command formats**
> - Words in `<UPPER_CASE>` are the parameters to be given by the user. <br />
> e.g. in `todo <DESCRIPTION>`, <DESCRIPTION> is a parameter which can be used as : `todo Read Novel`
> <br /><br />
> - Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `bye`) will not be ignored.<br />
> e.g. `help 123` will not be interpreted as  `help`. Make sure to give the correct commands.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
   
### Adding a TODO: `todo <DESCRIPTION>`
Adds a *TODO* task to the task list. It is set to **not done** by default
- **DESCRIPTION** : the Task description

Example: `todo Complete CS2113T Assignment`
```shell
todo Complete CS2113T Assignment
____________________________________________________________
Got it. I've added this task:
 [T][ ] Complete CS2113T Assignment
Now you have 1 tasks in the list.
____________________________________________________________

```
<br />
   
### Adding a DEADLINE: `deadline <DESCRIPTION> /by <DATE_TIME>`
Adds a *DEADLINE* task to the task list. It is set to **not done** by default
- **DESCRIPTION** : the Task description
- **/by** : is a *REQUIRED* clause when adding a deadline
- **DATETIME** : the *date* and *time* that the task is due by. This **CANNOT BE NULL/EMPTY**.
> :warning: Note: datetime needs to be given in a valid format.
> See [Dates](#list-valid-datetime-formats-dates) for more details

Example: `deadline read book /by 2021-10-13 16:00`
```shell
deadline read book /by 2021-10-13 16:00
____________________________________________________________
Got it. I've added this task:
 [D][ ] read book  (by:OCTOBER 13, 2021 04:00 p.m.)
Now you have 2 tasks in the list.
____________________________________________________________
```
<br />
   
### Adding an EVENT: `event <DESCRIPTION> /at <DATE_TIME>`
Adds a *EVENT* task to the task list. It is set to **not done** by default
- **DESCRIPTION** : the Task description
- **/at** : is a *REQUIRED* clause when adding a deadline
- **DATETIME** : the *date* and *time* that the task takes place. This **CANNOT BE NULL/EMPTY**.
> :warning: Note: datetime needs to be given in a valid format.
> See [Dates](#list-valid-datetime-formats-dates) for more details

Example: `event attend lecture /at 2021-11-21 08:00`
```shell
event attend lecture /at 2021-11-21 08:00
____________________________________________________________
Got it. I've added this task:
 [E][ ] attend lecture  (at:NOVEMBER 21, 2021 08:00 a.m.)
Now you have 3 tasks in the list.
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] Complete CS2113T Assignment
2.[D][ ] read book  (by:OCTOBER 13, 2021 04:00 p.m.)
3.[E][ ] attend lecture  (at:NOVEMBER 21, 2021 08:00 a.m.)
____________________________________________________________

```

<br />  
   
### Listing all tasks: `list`
This command lists all the tasks currently scheduled in the the task list
```shell
list
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] Complete CS2113T Assignment
2.[D][X] read book  (by:OCTOBER 13, 2021 04:00 p.m.)
3.[E][ ] attend lecture  (at:NOVEMBER 21, 2021 08:00 a.m.)
____________________________________________________________

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


### List valid DateTime Formats: `dates`
Lists all the valid datetime formats to be used when
adding a [Deadline](#adding-a-deadline-deadline-description-by-datetime)
or [Event](#adding-an-event-event-description-at-datetime).

Example: `help`
```shell
~$ dates
------------------------------------------


<br />

### Removing a task: `delete <IDX>`
Removes a task from the task list, by their **idx**
- **IDX** : index as displayed in the full list (**NOT QUERIED LIST**)

Example: `list` -> `delete 2`
```shell
list
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] Complete CS2113T Assignment
2.[D][X] read book  (by:OCTOBER 13, 2021 04:00 p.m.)
3.[E][ ] attend lecture  (at:NOVEMBER 21, 2021 08:00 a.m.)
____________________________________________________________
delete 2
____________________________________________________________
Noted. I've removed this task:
[D][X] read book  (by:OCTOBER 13, 2021 04:00 p.m.)
Now you have 2 tasks in the list.
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] Complete CS2113T Assignment
2.[E][ ] attend lecture  (at:NOVEMBER 21, 2021 08:00 a.m.)
____________________________________________________________

```

<br />

### Marking a task as done: `done <IDX>`

Marks a task as done, by their **IDX**.

- **IDX** : index as displayed in the full list (**NOT QUERIED LIST**)

Example: `list` -> `done 5`
```shell
done 2
____________________________________________________________
Nice! I have marked this task as done:
[D][X] read book  (by:OCTOBER 13, 2021 04:00 p.m.)
____________________________________________________________

```

<br />

### Exit the application : `bye`
Exits and closes **DUKE**.

Example: `bye`
```shell
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________

```
<br />

### Saving the Tasks
All updates made to the task list are automatically saved to a local file `duke.txt`
> Note: There is no need to save manually.

<br />

-----------------------------------------------------------------------------------------------------------------------------------------

## FAQ

**Q:** Why am I unable to create a task in the past?

**A:** The tasks are generally scheduled for the future, thus Duke does not allow users to schedule a task for a past date.
<br />

**Q:** How can I delete multiple tasks or mark multiple tasks as done in one command?

**A:** Deleting multiple tasks and marking multiple tasks as done in one single command is currently unsupported, but an alternative option is to delete/mark the tasks as done one by one.
<br />

**Q:** How do I transfer my data to another PC?

**A:** Install the app in the other computer and overwrite the empty data file 
it creates with the file that contains the data from your previous Duke home folder.

<br />

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Command Summary

| Command Format                             | Meaning                                                               |
| -----                                      | -------                                                               |
| `help`                                     | Lists all the valid commands                                          |
| `list`                                     | Lists all the scheduled tasks from the task list                      |
| `find <SEARCH_KEYWORD>`                    | Searches in the task list for tasks which contain the keyword         |
| `todo <DESCRIPTION>`                       | Adds a todo task with description                                     |
| `deadline <DESCRIPTION> /by <DATE_TIME>`   | Adds a deadline task with description and a deadline                  |
| `event <DESCRIPTION> /at <DATE_TIME>` ,    | Adds an event task with description and event timing                  |
| `delete <NUMBER>`, `delete 3`              | Deletes a task from the task list according to the index provided     |
| `done <NUMBER>`                            | Marks a task as done in the task list according to the index provided |
| `bye`                                      | Exits the application                                                 |
----------------------------------------------------------------------------------------------------------------------------------------------------------------------  
