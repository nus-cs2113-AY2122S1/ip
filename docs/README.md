# User Guide

Manage your tasks with Duke! Here's a quick and easy guide to get started with using Duke.

## Contents

* [Quick Start](#quick-start)
* [Features and Usage](#features-and-usage)
    * [Add Tasks](#add-tasks)
    * [List Tasks](#list-tasks)  
    * [Delete Tasks](#delete-tasks)
    * [Set status of tasks](#set-status-of-task)
    * [Search for tasks](#search-for-tasks)
    * [Search by date](#search-for-tasks-by-date)
    * [View possible commands](#view-possible-commands)
    * [Exit the program](#exit-the-program)

## Quick Start

1. Ensure you have `Java 11` installed on your device.
2. Download the latest jar file [here](https://github.com/nishantrai-nus/ip/releases/tag/A-Release)
3. Save it to an empty folder.
4. Open any terminal and navigate to the folder the jar is saved in.
5. Enter `java -jar Duke.txt` into the terminal to run Duke. You should see the following output:
```
________________________________________________________________________________
Hi! I'm Duke. I've created your data file for you!
What would you like me to do? Use "help" if you're unsure!
________________________________________________________________________________
```

## Features and Usage

### Add tasks

You can add three types of tasks; `todo`, `deadline` and `event`.

#### Usage

For `todo`: `todo <description>`

Example: `todo UG draft`

Expected outcome:

```
________________________________________________________________________________
Okay, I've added that task to your list:
[T][ ] UG draft
Now you have 1 task(s) in the list.
________________________________________________________________________________
```
For `deadline`: `deadline <description> /by <date and time>`

**NOTE**: `<date and time>` must be in the format `dd/mm/yyyy hhmm` with the time in 24 hour format.

Example: `deadline project report /by 28/10/2021 2359`

Expected outcome:

```
________________________________________________________________________________
Okay, I've added that task to your list:
[D][ ] project report (by: 28 October 2021 11:59 PM)
Now you have 2 task(s) in the list.
________________________________________________________________________________
```

For `event`: `event <description> /at <date and time>`

**NOTE**: `<date and time>` must be in the format `dd/mm/yyyy hhmm` with the time in 24 hour format.

Example: `event team meeting /at 05/10/2021 1400`

Expected outcome:

```
________________________________________________________________________________
Okay, I've added that task to your list:
[E][ ] team meeting (at: 05 October 2021 02:00 PM)
Now you have 3 task(s) in the list.
________________________________________________________________________________
```
### List tasks

You can view your current task list using `list`.

#### Usage

`list`

Expected outcome:
```
________________________________________________________________________________
Here are your current tasks and their status:
1. [T][ ] UG draft
2. [D][ ] project report (by: 28 October 2021 11:59 PM)
3. [E][ ] team meeting (at: 05 October 2021 02:00 PM)
________________________________________________________________________________
```

### Delete tasks

You can delete tasks from the list using `delete`.

#### Usage

`delete <task number>`

Example: `delete 2`

Expected outcome: 

```
> delete 2
________________________________________________________________________________
Okay, I've deleted that task!
[D][ ] project report (by: 28 October 2021 11:59 PM)
Now you have 2 task(s) in the list.
________________________________________________________________________________
> list
________________________________________________________________________________
Here are your current tasks and their status:
1. [T][ ] UG draft
2. [E][ ] team meeting (at: 05 October 2021 02:00 PM)
________________________________________________________________________________
```
### Set status of task

You can toggle the status of a task between done and incomplete using `done`.

#### Usage

`done <task number>`

Example: `done 1`

Expected outcome:

```
________________________________________________________________________________
Nice! I've marked this task as done:
[T][X] UG draft
________________________________________________________________________________
> done 1
________________________________________________________________________________
I've unmarked this task as done:
[T][ ] UG draft
________________________________________________________________________________
```

### Search for tasks

You can search for tasks using keywords using `find`.

#### Usage

`find <keyword(s)>`

**NOTE**: The number of keywords can be 1 or more.

Example: `find UG`

With current list being:
```
________________________________________________________________________________
Here are your current tasks and their status:
1. [T][ ] UG draft
2. [D][ ] project report (by: 28 October 2021 11:59 PM)
3. [E][ ] team meeting (at: 05 October 2021 02:00 PM)
4. [D][ ] final UG (by: 05 October 2021 11:59 PM)
5. [T][ ] project presentation
________________________________________________________________________________
```

Expected outcome:
```
________________________________________________________________________________
I found these tasks for UG:
1. [T][ ] UG draft
4. [D][ ] final UG (by: 05 October 2021 11:59 PM)
________________________________________________________________________________
```

Example: `find UG project`

Expected outcome:
```
________________________________________________________________________________
I found these tasks for UG:
1. [T][ ] UG draft
4. [D][ ] final UG (by: 05 October 2021 11:59 PM)
________________________________________________________________________________
I found these tasks for project:
2. [D][ ] project report (by: 28 October 2021 11:59 PM)
5. [T][ ] project presentation
________________________________________________________________________________
```

### Search for tasks by date

You can search for tasks due by or happening on a certain date using `date`.

#### Usage

`date <date>`

**NOTE**: `<date>` must be in the format `dd/mm/yyyy`.

Example: `date 05/10/2021`

Expected outcome:
```
________________________________________________________________________________
Here are the tasks happening on/ due by 05/10/2021:
3. [E][ ] team meeting (at: 05 October 2021 02:00 PM)
4. [D][ ] final UG (by: 05 October 2021 11:59 PM)
________________________________________________________________________________
```
### View possible commands

You can see a condensed list of the above commands and how to use them using `help`.

#### Usage

`help`

Expected outcome:
```
________________________________________________________________________________
Here are the commands I can currently execute:

    todo XXX - Adds a Todo task XXX to the tasklist
    deadline XXX /by YYY - Adds a Deadline task XXX to the list, due by YYY
    event XXX /at YYY - Adds an Event task XXX to the list, happening on YYY
    list - Shows all current tasks with their ID and status (done or incomplete)
    done XXX - Toggles the status of the task with ID XXX between done and incomplete
    delete XXX - Deletes task with ID XXX from the list
    find XXX - Shows tasks containing the given keyword(s) (can be more than or equal to 1)
    date YYY - Shows tasks due by or happening on the date YYY (without time)
    bye - Exits Duke

NOTE: All dates and times YYY must be entered in the following format:
dd/mm/yyyy hhmm (time is in 24-hour format)
________________________________________________________________________________
```

### Exit the program

Exit the program using `bye`.

#### Usage 

`bye`

Expected outcome:
```
________________________________________________________________________________
Bye! I hope to see you again soon :)
________________________________________________________________________________
```
