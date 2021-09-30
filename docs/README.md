# Duke User Guide
Duke is a Command Line Interface program that let users keep track of their tasks.

## Quick Start
1. Ensure Java `11` is installed.
2. Download [IP.jar](https://github.com/kum-wh/ip/releases).
3. Navigate to file path in terminal.
4. To launch **Duke**, enter `java -jar IP.jar`.
## Command List

Command | Use
------- | ---
`ToDo` | Add **ToDo** Task
`Deadline` | Add **Deadline** Task
`Event` | Add **Event** Task
`Done` | Set a Task as **completed**
`Delete` | Remove a Task
`List` | View Task
`Find` | Find Tasks with a specific **Keyword**
`Date` | Find Tasks with a specific **Date**
`Bye` | Exit Program
`Echo` | Repeat Input
`!Echo` | Repeat Input in Grafiti ASCII Art
`!help` | View commands functions
`!list` | View **list** commands description
`!event` | View **event** commands description
`!deadline` | View **deadline** commands description

## Notes

* Words in `UPPER_CASE` are parameters to be entered by user.
* Program is **not** case sensitive.
* `INPUT` represent input by user.
* `TASK_DESCRIPTION` represent description of a task.
* `TASK_DEADLINE` represent date of a task either in words or `DATE` and `TIME` format. 
* `TASK_TIMING` represent timing of a task either in words or `DATE` and `TIME` format.
* `TASK_INDEX` represent index number of a task. Index number can be viewed by viewing list.
* `KEYWORD` represent word input by user.
* `TIME` represents date input by user in `HH:MM` format.
   * `HH` represents hours.
   * `MM` represents minutes.
* `DATE` represents date input by user in `yyyy-mm-dd` fomat.
   * `yyyy` represents year.
   * `mm` represents months.
   * `dd` represents days.
* Tasks are represented as `TASK_TYPE`,`STATUS`,`TASK_DESCRIPTION`,`TASK_DATE`.
   * `TASK_TYPE` is `T` for ToDo, `D` for Deadline and `E` for Event.
   * `STATUS` is `X` if completed.
   * `TASK_DATE` is TASK_DEADLINE for Deadline and TASK_TIMING for Event.

## Features

### Adding a Task

Multiple tasks can be added in one line.

#### Adding a ToDo Task

Adds a task without any specific dates to list.

Command Format: `todo TASK_DESCRIPTION`

* TASK_DESCRIPTION cannot be empty.

Examples:
* `todo watch twitch videos`
* `todo watch youtube videos`

Demo:

```
todo watch twitch videos todo watch youtube videos

____________________________________________________________
Got it. I've added this task: 
[T][ ]watch twitch videos
Now you have 1 tasks in the list.
Got it. I've added this task: 
[T][ ]watch youtube videos
Now you have 2 tasks in the list.
____________________________________________________________
```

#### Adding a DeadLine Task

Adds a task with a duedate as deadline to list.

Command Format | Command
-------------- | -------
Without Date and Time | `deadline TASK_DESCRIPTION by TASK_DEADLINE`
Without Date | `deadline TASK_DESCRIPTION by TIME`
Without Time | `deadline TASK_DESCRIPTION by DATE`
With Date and Time | `deadline TASK_DESCCRIPTION by TIME DATE`


* TASK_DESCRIPTION and TASK_DEADLINE cannot be empty.
* Word `by` is required in input to indicated TASK_DEADLINE.
* No specific ordering of time and date as long as input pattern is followed.

Examples:
* `deadline homework1 by 23:59`
* `deadline homework2 by 2020-10-10`
* `deadline homework3 by 20:00 2021-10-20

Demo:

```
deadline homework1 by 23:59 deadline homework2 by 2020-10-10 deadline homework3 by 20:00 2021-10-20

____________________________________________________________
Got it. I've added this task: 
[D][ ]homework1(by: 11:59 PM )
Now you have 3 tasks in the list.
Got it. I've added this task: 
[D][ ]homework2(by:  Oct 10 2020)
Now you have 4 tasks in the list.
Got it. I've added this task: 
[D][ ]homework3(by: 08:00 PM Oct 20 2021)
Now you have 5 tasks in the list.
____________________________________________________________
```


View details on adding Deadline task in program.

Command Format: `!deadline`

Demo:

```
____________________________________________________________
deadline command requires a end time indicated using "by"[end time]
[timing]: HH:MM YYYY-MM-DD
____________________________________________________________
```

#### Adding a Event Task

Adds a task occuring at a specific date and time as event to list.

Command Format | Command
-------------- | -------
Without Date and Time | `event TASK_DESCRIPTION at TASK_TIMING`
Without Date | `event TASK_DESCRIPTION at TIME`
Without Time | `event TASK_DESCRIPTION at DATE`
With Date and Time | `event TASK_DESCCRIPTION at TIME DATE`


* TASK_DESCRIPTION and TASK_TIMING cannot be empty.
* Word `at` is required in input to indicated TASK_DEADLINE.
* No specific ordering of time and date as long as input pattern is followed.

Examples:
* `event g2 vs SEN at 21:00`
* `event 100T vs Gambit at 2021-08-01`
* `event paperex vs Bren at 2021-08-01 08:00`

Demo:

```
event g2 vs SEN at 21:00 event 100T vs Gambit at 2021-08-01 event paperex vs Bren at 2021-08-01 08:00

____________________________________________________________
Got it. I've added this task: 
[E][ ]g2 vs en(at: 09:00 PM )
Now you have 6 tasks in the list.
Got it. I've added this task: 
[E][ ]100t vs gambit(at:  Aug 01 2021)
Now you have 7 tasks in the list.
Got it. I've added this task: 
[E][ ]paperex vs bren(at: 08:00 AM Aug 01 2021)
Now you have 8 tasks in the list.
____________________________________________________________
```


View details on adding Event task in program.

Command Format: `!event`

Demo:

```
____________________________________________________________
event command requires a timing indicated using "at" [timing]
[timing]: HH:MM YYYY-MM-DD
____________________________________________________________
```

### Setting a task as complete

Sets tasks in list as done.

Command Format: `done TASK_INDEX`

* TASK_INDEX cannot be empty.
* TASK_INDEX must be a integer.
* Use `,` to seperate multiple tasks.

Examples:
* `done 1,2,3`

Demo:

```
done 1,2,3

____________________________________________________________
Nice! I've marked this task as done: 
[T][X]watch twitch videos
[T][X]watch youtube videos
[D][X]homework1(by: 11:59 PM )
Here are the tasks in your list:
1. [T][X]watch twitch videos
2. [T][X]watch youtube videos
3. [D][X]homework1(by: 11:59 PM )
4. [D][ ]homework2(by:  Oct 10 2020)
5. [D][ ]homework3(by: 08:00 PM Oct 20 2021)
6. [E][ ]g2 vs en(at: 09:00 PM )
7. [E][ ]100t vs gambit(at:  Aug 01 2021)
8. [E][ ]paperex vs bren(at: 08:00 AM Aug 01 2021)
____________________________________________________________
```

### Delete a Task

Deletes tasks from list

Command Format: `delete TASK_INDEX`

* TASK_INDEX cannot be empty.
* TASK_INDEX must be a integer.
* Use `,` to seperate multiple tasks.

Examples:
* `delete 2,4,5`

Demo:

```
delete 2,4,5

____________________________________________________________
Noted. I've removed this task: 
[D][ ]homework3(by: 08:00 PM Oct 20 2021)
Now you have 7 tasks in the list.
Noted. I've removed this task: 
[D][ ]homework2(by:  Oct 10 2020)
Now you have 6 tasks in the list.
Noted. I've removed this task: 
[T][X]watch youtube videos
Now you have 5 tasks in the list.
____________________________________________________________
```

### Delete all Task

Deletes all tasks from list.

Command Format: `clear`

Demo:

```
clear

____________________________________________________________
List Cleared. All tasks removed.
____________________________________________________________
```

### View Tasks

Lists all task in list.

Command Format: `list`

Demo:

```
list

____________________________________________________________
Here are the tasks in your list:
1. [T][X]watch twitch videos
2. [D][X]homework1(by: 11:59 PM )
3. [E][ ]g2 vs en(at: 09:00 PM )
4. [E][ ]100t vs gambit(at:  Aug 01 2021)
5. [E][ ]paperex vs bren(at: 08:00 AM Aug 01 2021)
____________________________________________________________
```
View all list commands in program.

Demo:

```
Command Format: `!list`

!list

____________________________________________________________
list displays all tasks
list todo displays all todo tasks
list event displays all event tasks
list deadline displays all deadline tasks
____________________________________________________________
```

#### Viewing Todo Tasks

Lists all ToDo task in list

Command Format: `list todo`

Demo:

```
list todo

____________________________________________________________
Here are the ToDo tasks in your list:
1. [T][X]watch twitch videos
____________________________________________________________
```

#### Viewing DeadLine Tasks

Lists all Deadline task in list.

Command Format: `list deadline`

Demo:

```
list deadline

____________________________________________________________
Here are the Deadline tasks in your list:
1. [D][X]homework1(by: 11:59 PM )
____________________________________________________________
```

#### Viewing Event Tasks

Lists all Event task in list.

Command Format: `list event`

Demo:

```
list event

____________________________________________________________
Here are the Event tasks in your list:
1. [E][ ]g2 vs en(at: 09:00 PM )
2. [E][ ]100t vs gambit(at:  Aug 01 2021)
3. [E][ ]paperex vs bren(at: 08:00 AM Aug 01 2021)
____________________________________________________________
```

### Finding Task By KeyWords

Lists all tasks with a specific keyword in its description in list.

Command Format: `find KEYWORD`

* KEYWORD must not be empty.

Examples:
* `find g2`

Demo:

```
find g2

____________________________________________________________
Here are the matching tasks in your list:
1. [E][ ]g2 vs en(at: 09:00 PM )
____________________________________________________________
```

### Finding Task By Date

Lists all tasks with a specific date in list.

Command Format: `date DATE`

* DATE must not be empty.
* DATE must be in the format of yyyy-mm-dd.

Examples:
* `date 2021-08-01`

Demo:

```
date 2021-08-01

____________________________________________________________
Here are the tasks with matching dates in your list:
1. [E][ ]100t vs gambit(at:  Aug 01 2021)
2. [E][ ]paperex vs bren(at: 08:00 AM Aug 01 2021)
____________________________________________________________
```

### Echo User Input

Prints user input on terminal

Command Format: `echo INPUT`

Demo:

```
echo hi

____________________________________________________________
hi
____________________________________________________________
```

Draws user input on terminial.

Command Format: `!echo INPUT`

!echo hi

Demo:

```
!echo hi

____________________________________________________________
HI
  ___ ___  .___
 /   |   \ |   |
/    ~    \|   |
\    Y    /|   |
 \___|___/ |___|
____________________________________________________________
```

### Command Guide

Command Format: `!help`

Demo:

```
!help

____________________________________________________________
List of Commands:
   echo - Repeat whatever was typed - !echo to repeat in art form
   list - Display List - !list for details
   todo - Add ToDo Task
   event - Add Event Task - !event for details
   deadline - Add Deadline Task - !deadline for details
   clear - Clear list
   find - Find Task With a specific word
   date - Find Task with a specific date
   bye - Shut Down
____________________________________________________________
```

### Exit Program

Exits program.

Command Format: `bye`

Demo:

```
bye

____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
_______________.___.___________
\______   \__  |   |\_   _____/
 |    |  _//   |   | |    __)_
 |    |   \\____   | |        \
 |________// ______|/_________/
```
