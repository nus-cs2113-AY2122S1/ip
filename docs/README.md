# User Guide

**Duke** is a desktop app to **manage tasks and keep a track of things**.
It is a **Personal Assistant Chatbot** that can store data and help you keep a track of your scheduled tasks.
It is optimized for use via a Command Line Interface (CLI) that can help you manage your tasks faster than the traditional GUI apps.



## Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
    * [Viewing help](#viewing-help--help)
    * [Adding a Todo](#adding-a-todo-todo-description)
    * [Adding a Deadline](#adding-a-deadline-deadline-description-by-date_time)
    * [Adding an Event](#adding-an-event-event-description-at-date_time)
    * [Listing all Tasks](#listing-all-tasks)
    * [Marking a task as done](#marking-a-task-as-done-done-number)
    * [Deleting a task](#deleting-a-task-delete-number)
    * [Searching for tasks](#querying-tasks-find-regex-type-tasktype-limit-querylimit)
    * [Saving the tasks](#saving-the-tasks)
    * [Exiting the program](#exit-the-application--bye)

- [FAQ](#faq)
- [Command Summary]()

-------------------------------------------------

## Quick Start

1. Ensure that you have Java `11` or above installed in your computer.
2. Download the latest `ip.jar` from [here](https://github.com/aditichadha1310/ip)
3. Copy the file to the folder you want to use as the home folder for your `Personal Assistant Chatbot- Duke`.
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
- `deadline <description> /by <Date_Time>` : Adds a DEADLINE task to be completed by `<date_time>` in the task list.
- `event <description> /at <Date_Time>` : Adds an EVENT task that will take place at `<date_time>` in the task list.
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
 > help
 > todo
 > deadline
 > done
 > event
 > list
 > delete
 > find
 > bye
------------------------------------------
```
<br />
   
### Adding a TODO: `todo <DESCRIPTION>`
Adds a *TODO* task to the task list. By default, the task is set to **not done**.
- **DESCRIPTION** :  is the description of the Task

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
Adds a *DEADLINE* task to the task list. By default, the task is set to **not done**.
- **DESCRIPTION** : is the description of the Task
- **/by** : is a *REQUIRED* clause when adding a deadline
- **DATE_TIME** : are the *date* and *time* that the task is due by. This **CANNOT BE NULL/EMPTY**.
> :warning: Note: date_time needs to be given in a valid format.
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
Adds a *EVENT* task to the task list. By default, the task is set to **not done**.
- **DESCRIPTION** :  is the description of the Task
- **/at** : is a *REQUIRED* clause when adding an event
- **DATE_TIME** : are the *date* and *time* for which the event is scheduled. This **CANNOT BE NULL/EMPTY**.
> :warning: Note: date_time needs to be given in a valid format.
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
`[TaskType][isDone] Description (Deadline/Event Time)`

**TaskType:**
* T -> TODO task
* D -> DEADLINE Task
* E -> EVENT Task

**isDone:**
* \[ ]  -> not done
* \[X] -> done
 
**Deadline/Event Time**
* \ *null* -> In case of a Todo there is no deadline or event time. 
* \ (by:MONTH_NAME DAY_OF_MONTH, YEAR TIME) -> In case of a Deadline
* \ (at:MONTH_NAME DAY_OF_MONTH, YEAR TIME) -> In case of an Event

<br />


### Marking a task as done: `done <INDEX_NUMBER>`

Marks a task as done, by their **INDEX_NUMBER**.

- **INDEX_NUMBER** : index as displayed in the task list.

Example: `list` -> `done 2` -> `list`
```shell
list
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] Complete CS2113T Assignment
2.[D][ ] read book  (by:OCTOBER 13, 2021 04:00 p.m.)
3.[E][ ] attend lecture  (at:NOVEMBER 21, 2021 08:00 a.m.)
____________________________________________________________
done 2
____________________________________________________________
Nice! I have marked this task as done:
[D][X] read book  (by:OCTOBER 13, 2021 04:00 p.m.)
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] Complete CS2113T Assignment
2.[D][X] read book  (by:OCTOBER 13, 2021 04:00 p.m.)
3.[E][ ] attend lecture  (at:NOVEMBER 21, 2021 08:00 a.m.)

```

<br />
   
### Deleting a task: `delete <IDEX_NUMBER>`
Deletes a task from the task list, by their **index_number**
- **INDEX_NUMBER** : index as displayed in the TASK list.
   
Example: `list` -> `delete 2` -> `list` 
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

### Searching for tasks: `find <KEYWORD>`
Queries the task list by the keyword passed.
   
- All tasks that contains the `KEYWORD` in their task description will be displayed. 

Example: `list` -> `find Attend`
```shell
list
____________________________________________________________
Here are the tasks in your list:
1.[E][ ] Attend CS2113T lecture  (at:NOVEMBER 13, 2021 08:00 a.m.)
2.[D][X] Complete CG2271 Labs  (by:DECEMBER 4, 2021 09:00 a.m.)
3.[T][X] Attend carnival
4.[D][ ] Finish all assignments  (by:DECEMBER 25, 2021 11:59 p.m.)
____________________________________________________________
find Attend
[E][ ] Attend CS2113T lecture  (at:NOVEMBER 13, 2021 08:00 a.m.)
[T][X] Attend carnival
____________________________________________________________
```

<br />
   
### Saving the Tasks
All updates made to the task list are automatically saved to a local file `duke.txt`
> Note: There is no need to save manually.

<br />
   
   
### Exit the application : `bye`
Exits and closes **DUKE**.

Example: `bye`
```shell
bye
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________

```
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

| Command Format                             | Action                                                        |
| -----                                      | -------                                                       |
| `help`                                     | Lists all the valid commands                                  |
| `list`                                     | Lists all the scheduled tasks from the task list              |
| `find <SEARCH_KEYWORD>`                    | Searches in the task list for tasks which contain the keyword |
| `todo <DESCRIPTION>`                       | Adds a todo task with description                             |
| `deadline <DESCRIPTION> /by <DATE_TIME>`   | Adds a deadline task with description and a deadline          |
| `event <DESCRIPTION> /at <DATE_TIME>` ,    | Adds an event task with description and event timing          |
| `delete <INDEX_NUMBER>`                    | Deletes a task from the specified index                       |
| `done <INDEX_NUMBER>`                      | Marks the task at the specified index as done                 |
| `bye`                                      | Exits the application                                         |
----------------------------------------------------------------------------------------------------------------------------------------------------------------------  
