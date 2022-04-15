# Duke User Guide

`Duke` is a Personal Assistant Chat bot that is designed 
to help users manage their tasks on the Command Line Interface (CLI).

* [Quick Start](#quick-start)
* [List of Commands](#list-of-commands)
  * [Notes about command format](#notes-about-command-format)
* [Features](#features-and-usage-of-commands)
  * [`todo`](#adding-a-task-without-any-date-and-time-parameters-todo)
  * [`deadline`](#adding-a-task-with-a-deadline-deadline)
  * [`event`](#adding-a-task-with-an-event-timing-event)
  * [`find`](#finding-a-task-from-task-list--find)
  * [`list`](#listing-all-tasks-list)
  * [`done`](#marking-a-task-as-done-done)
  * [`delete`](#deleting-a-task-delete)
  * [`bye`](#exiting-duke-bye)
* [Data Storage](#data-storage-in-duketxt)
  * [Warning: Editing storage file](#editing-duketxt)

## Quick Start

1. Ensure you have Java 11 installed in your computer.
1. Download the latest version of `Duke` [here!](https://github.com/saravanamani1999/ip/releases/tag/A-Release)
1. Copy the `ip.jar` to an empty folder
1. On command line, navigate to the folder where `Duke` is stored
1. Launch the `ip.jar` folder by running `java -jar ip.jar`

### Demo:

```
java -jar ip.jar

Hello from

 DDDDD           kk
 DD  DD  uu   uu kk  kk   eee
 DD   DD uu   uu kkkkk  ee   e
 DD   DD uu   uu kk kk  eeeee
 DDDDDD   uuuu u kk  kk  eeeee

____________________________________________________________
 Hello! I'm Duke, your friendly neighbourhood task manager!
 How can I help you?
____________________________________________________________
```

## List of Commands

| Command    	| Action                                       	        | Command Format                              	|
|------------	|----------------------------------------------------	|---------------------------------------------	|
| `todo`     	| Add a task without any deadline or timing         	| `todo TASK_DESCRIPTION`                     	|
| `deadline` 	| Add a task with a deadline (Date and Time)         	| `deadline TASK_DESCRIPTION /by DATE_TIME`   	|
| `event`    	| Add a task with a timing (Date and Time)     	        | `event TASK_DESCRIPTION /at DATE_TIME`      	|
| `find`     	| Finds a task in the task list based on user query 	| `find QUERY`                                	|
| `list`     	| Lists all the task found in the task list         	| `list`                                      	|
| `done`     	| Marks a specified task as done                      	| `done TASK_INDEX`                           	|
| `delete`   	| Deletes a specified task from the task list  	        | `delete TASK_INDEX`                         	|
| `bye`      	| Exit Duke                                    	        | `bye`                                       	|

### Notes about Command Format:
* Words in `UPPER_CASE` are the parameters to be supplied by the user.
* If a command expects parameters, then the user has to key it in the correct order and only once.
* If a command (`list` and `bye`) does not expect any parameters, it will be ignored.

## Features and Usage of Commands

### Adding a task without any date and time parameters: `todo`  

Adds a task without any date and time parameters to the task list.

Format of command: `todo TASK_DESCRIPTION`

* Ensure `TASK_DESCRIPTION` is not empty.

Examples:

* `todo read a book`
* `todo return book to library`

Expected Outcome:
```
todo read a book

____________________________________________________________
 Got it. I've added this task:
  [T][ ] read a book
 Now you have 1 tasks in the list.
____________________________________________________________
```
### Adding a task with a deadline: `deadline` 

Adds a task with a specified deadline where it includes date and time of the deadline.

Format of command: `deadline TASK_DESCRIPTION /by DATE_TIME`

* Ensure `TASK_DESCRIPTION` and `DATE_TIME` is not empty.
* Ensure the `DATE_TIME` follows the format: `dd-mm-yyyy HH:mm`.
  * `dd` is the day
  * `mm` is the month
  * `yyyy` is the year
  * `HH` is the hour part of the timing in 24-hour format
  * `mm` is the minute part of the timing

Examples:
* `deadline finish assignment /by 20-02-2021 16:30`
* `deadline return book /by 23-12-2021 10:30`

Expected Outcome:
```
deadline finish assignment /by 20-02-2021 16:30

____________________________________________________________
 Got it. I've added this task:
  [D][ ] finish assignment (by: Feb 20 2021 04:30 PM)
 Now you have 2 tasks in the list.
____________________________________________________________
```
### Adding a task with an event timing: `event` 

Adds a task with a specified event timing where it includes date and time of the event.

Format of command: `event TASK_DESCRIPTION /at DATE_TIME`

* Ensure `TASK_DESCRIPTION` and `DATE_TIME` is not empty.
* Ensure the `DATE_TIME` follows the format: `dd-mm-yyyy HH:mm`.
  * `dd` is the day
  * `mm` is the month
  * `yyyy` is the year
  * `HH` is the hour part of the timing in 24-hour format
  * `mm` is the minute part of the timing

Examples:

* `event attend project meeting /at 21-10-2021 23:59`
* `event attend cousin's wedding /at 22-11-2021 10:00`

Expected Outcome:
```
event attend project meeting /at 21-10-2021 23:59

____________________________________________________________
 Got it. I've added this task:
  [E][ ] attend project meeting (at: Oct 21 2021 11:59 PM)
 Now you have 3 tasks in the list.
____________________________________________________________
```
### Finding a task from task list : `find` 

Finds a task in the task list based on user's query of the task description.

Format of command: `find QUERY`
* The search is case-sensitive. e.g. `Book` will not match `book`.
* Only the description of task is searched.
* Only full words will be matched e.g. `book` will not match `books`.
* The order of the query is important e.g. `team meeting` will not match `meeting team`.

Examples:

* `find book`
* `find finish assignment`

Expected Outcome:
```
find book

____________________________________________________________
 Here are the matching tasks in your list:
 1.[T][ ] read a book
 2.[T][ ] finish book
____________________________________________________________
```

### Listing all tasks: `list`  

Lists all the task found in the task list.

Format of command: `list`

Expected Outcome:
```
list

____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] read a book
 2.[D][ ] finish assignment (by: Feb 20 2021 04:30 PM)
 3.[E][ ] attend project meeting (at: Oct 21 2021 11:59 PM)
 4.[T][ ] finish book
____________________________________________________________
```

### Marking a task as done: `done` 

Marks a specified task as done.

Format of command: `done TASK_INDEX`

* Marks the task at the specified `TASK_INDEX` as done.
* The task index refers to the index number shown in the displayed tasks list.
* The index must be a positive integer and more than 0.

Examples:
* `done 1` marks the 1st task in the task list as done.

Expected Outcome:
```
list

____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] read a book
 2.[D][ ] finish assignment (by: Feb 20 2021 04:30 PM)
 3.[E][ ] attend project meeting (at: Oct 21 2021 11:59 PM)
 4.[T][ ] finish book
____________________________________________________________

done 1

____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] read a book
____________________________________________________________

list

____________________________________________________________
 Here are the tasks in your list:
 1.[T][X] read a book
 2.[D][ ] finish assignment (by: Feb 20 2021 04:30 PM)
 3.[E][ ] attend project meeting (at: Oct 21 2021 11:59 PM)
 4.[T][ ] finish book
____________________________________________________________
```
### Deleting a task: `delete` 

Deletes a specified task from the task list.

Format of command: `delete TASK_INDEX`

* Deletes the task at the specified `TASK_INDEX`.
* The task index refers to the index number shown in the displayed tasks list.
* The index must be a positive integer and more than 0.

Examples:
* `delete 2` deletes the 2nd task in the task list.

Expected Outcome:
```
list

____________________________________________________________
 Here are the tasks in your list:
 1.[T][X] read a book
 2.[D][ ] finish assignment (by: Feb 20 2021 04:30 PM)
 3.[E][ ] attend project meeting (at: Oct 21 2021 11:59 PM)
 4.[T][ ] finish book
____________________________________________________________

delete 2
 
____________________________________________________________
 Noted. I've removed this task:
   [D][ ] finish assignment (by: Feb 20 2021 04:30 PM)
Now you have 3 tasks in the list.
____________________________________________________________

list

____________________________________________________________
 Here are the tasks in your list:
 1.[T][X] read a book
 2.[E][ ] attend project meeting (at: Oct 21 2021 11:59 PM)
 3.[T][ ] finish book
____________________________________________________________
```

### Exiting Duke: `bye` 

Exits the program.

Format: `bye`

Expected Outcome:
```
bye

____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```

## Data Storage in `duke.txt`

Duke automatically saves all the tasks to a text file after each command which modifies the task list.
This means the users can recover their tasks even after relaunching `Duke`.

### Warning: Editing `duke.txt`

> ❗It's highly advisable that beginner users do not edit any part of this file as if the data is not stored in the 
required format it might cause `Duke` to crash.


