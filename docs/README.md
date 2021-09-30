# User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface. If you can type fast, Duke can
manage your tasks faster than traditional Graphical User Interface apps.

- [Features](#features)
- [Usage](#usage)
- [FAQ](#faq)
- [Command summary](#command-summary)

## Features

### Task Management

You can add and delete three kinds of tasks - todo, deadline, and event tasks. You can list all tasks and find certain
tasks based on a keyword or date. You can also mark tasks as done.

## Usage

### `todo` - Adding a todo task

Adds a todo task to the task list.

Format: `todo DESCRIPTION`

Example of usage: `todo borrow book`

Expected output:

```
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] borrow book
     Now you have 1 task(s) in the list
    ____________________________________________________________
```

### `deadline` - Adding a deadline task

Adds a task with a deadline (i.e. needs to be done before a specific date/time) to the task list.

Format: `deadline DESCRIPTION /by yyyy-MM-dd HHmm`

Example of usage: `deadline return book /by 2021-09-26 1800`

Expected output:

```
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] return book (by: Sep 26 2021 6.00pm)
     Now you have 2 task(s) in the list
    ____________________________________________________________
```

### `event` - Adding an event

Adds an event (i.e. a task that starts at a specific time).

Format: `event DESCRIPTION /at yyyy-MM-dd HHmm`

Example of usage: `event project meeting /at 2021-10-04 1400`

Expected output:

```
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] project meeting (at: Oct 4 2021 2.00pm)
     Now you have 3 task(s) in the list
    ____________________________________________________________
```

### `list` - Listing all tasks

Shows a list of all tasks along with their details.

Example of usage: `list`

Expected output:

```
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[D][ ] return book (by: Sep 26 2021 6.00pm)
     3.[E][ ] project meeting (at: Oct 4 2021 2.00pm)
    ____________________________________________________________
```

### `find` - Finding all tasks with a keyword

Shows a list of tasks that match the given keyword.

Format: `find KEYWORD`

Example of usage: `find project`

Expected output:

<small>Note that the indices shown are based on the indices of the task in the whole task list rather than based only on
the list of tasks that are displayed.</small>

```
    ____________________________________________________________
     Here are the matching tasks in your list:
     3.[E][ ] project meeting (at: Oct 4 2021 2.00pm)
    ____________________________________________________________
```

### `date` - Finding all events/deadlines occurring on a date

Shows a list of events and deadlines that occur on the given date.

Format: `date yyyy-MM-dd`

Example of usage: `date 2021-09-26`

Expected output:

<small>As before, note that the indices shown are based on the indices of the task in the whole task list rather than
based only on the list of tasks that are displayed.</small>

```
    ____________________________________________________________
     Here are the tasks in your list that fall on Sep 26 2021:
     2.[D][ ] return book (by: Sep 26 2021 6.00pm)
    ____________________________________________________________
```

### `delete` - Deleting a task

Deletes the task at the specified index from the task list. Note that the index is based on the index of the task in the
whole task list.

Format: `delete INDEX`

Example of usage: `delete 3`

Expected output:

```
    ____________________________________________________________
     Noted. I've removed this task:
       [E][ ] project meeting (at: Oct 4 2021 2.00pm)
     Now you have 2 task(s) in the list
    ____________________________________________________________
```

### `done` - Marking a task as done

Marks the task at the specified index as completed. Note that the index is based on the index of the task in the whole
task list.

Format: `done INDEX`

Example of usage: `done 1`

Expected output:

```
    ____________________________________________________________
     Nice! I've marked this task as done:
       [T][X] borrow book
    ____________________________________________________________
```

### `bye` - Exiting the program

Exits the program.

Example of usage: `bye`

Expected output:

```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

## FAQ

**Q**: Where is the data stored and how do I transfer data to a different computer?<br>
**A**: The data is stored in `data/duke.txt`, relative to your current working directory. You can copy the file to a
different computer and run Duke to get access to the same data.

## Command summary

Action | Format, Examples
--------|------------------
**Add todo** | `todo DESCRIPTION`<br>e.g. `todo borrow book`
**Add deadline** | `deadline DESCRIPTION /by yyyy-MM-dd HHmm`<br>e.g. `deadline return book /by 2021-09-26 1800`
**Add event** | `event DESCRIPTION /at yyyy-MM-dd HHmm`<br>e.g. `event project meeting /at 2021-10-04 1400`
**List** | `list`
**Find** | `find KEYWORD`<br>e.g. `find project`
**Date** | `date yyyy-MM-dd`<br>e.g. `date 2021-09-26`
**Delete** | `delete INDEX`<br>e.g. `delete 3`
**Done** | `done INDEX`<br>e.g. `done 1`
**Exit** | `bye`
