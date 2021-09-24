# User Guide
:older_man: Alfred is a Command Line Application for task management, modelled after Alfred the Butler from Batman.

- Features and Usage
  - [:scroll: Listing current tasks: `list`](#list)
  - Adding tasks:
    - [:bookmark: Todo: `todo`](#todo)
    - [:calendar: Event: `event`](#event)
    - [:pushpin: Deadline: `deadline`](#deadline)
  - [:white_check_mark: Marking task as complete: `done`](#done)
  - [:x: Delete a task: `delete`](#delete)
  - [:mag_right: Searching for tasks: `find`](#find)
  - [:door: Exiting the app: `bye`](#exit)
- [:floppy_disk: Saving the data](#save)
- [Command summary](#summary)

## Features and Usage

### <a name="list"></a>:scroll: `list` - Listing current tasks
Lists all current tasks stored in local storage task list.

Usage: `list`

Expected outcome and examples of usage:

- If there exists tasks, Alfred will enumerate the tasks and print them:
```
list
____________________________________________________________
 Your tasks, sir:
 1.[T][ ] Meet Catwoman
 2.[E][ ] Save Gotham (at: Sep 24 2021)
____________________________________________________________
```
- If task list is empty, Alfred will respond with the following message:
```
list
____________________________________________________________
 Your schedule is clear, Master Wayne.
____________________________________________________________
```
<br />

### <a name="todo"></a>:bookmark: `todo` - Adding a Todo
Adds a Todo to the task list.

Usage: `todo TODO_DESCRIPTION`
- :warning: `TODO_DESCRIPTION` cannot be empty.

Expected outcome and examples of usage:

Alfred will add the Todo to the current task list, and 
display the current number of tasks in the task list:
```
todo Meet Catwoman
____________________________________________________________
 I shall put this in your schedule, Master Wayne: 
    [T][ ] Meet Catwoman
 Sir, the number of Tasks you have scheduled currently amounts to 1.
____________________________________________________________
```
<br />

### <a name="event"></a>:calendar: `event` - Adding an Event
Adds an Event to the task list.

Usage: `event EVENT_DESCRIPTION /at EVENT_DATE`
- :warning: `EVENT_DESCRIPTION` cannot be empty.
- :warning: `EVENT_DATE` cannot be empty.
- :warning: `EVENT_DATE` must be formatted as `DDMMYYYY`, `DD/MM/YYYY`, or `DD-MM-YYYY`.

Expected outcome and examples of usage: 

Alfred will add the Event to the current task list, and
display the current number of tasks in the task list:
```
event OP1 /at 10092021
____________________________________________________________
 I shall put this in your schedule, Master Wayne:
    [E][ ] OP1 (at: Sep 10 2021)
 Sir, the number of Tasks you have scheduled currently amounts to 2.
____________________________________________________________
```
<br />

### <a name="deadline"></a>:pushpin: `deadline` - Adding a Deadline
Adds a Deadline to the task list.

Usage: `deadline DEADLINE_DESCRIPTION /by DEADLINE_DATE`
- :warning: `DEADLINE_DESCRIPTION` cannot be empty.
- :warning: `DEADLINE_DATE` cannot be empty.
- :warning: `DEADLINE_DATE` must be formatted as `DDMMYYYY`, `DD/MM/YYYY`, or `DD-MM-YYYY`.

Expected outcome and examples of usage:

Alfred will add the Deadline to the current task list, and
display the current number of tasks in the task list:
```
deadline Submit IP Final Version /by 01/10/2021
____________________________________________________________
 I shall put this in your schedule, Master Wayne: 
    [D][ ] Submit IP Final Version (by: Oct 01 2021)
 Sir, the number of Tasks you have scheduled currently amounts to 3.
____________________________________________________________
```
<br />

### <a name="done"></a>:white_check_mark: `done` - Marking task as complete
Marks a task as done/complete in the task list based on index specified.

Usage: `done TASK_INDEX`
- :warning: `TASK_INDEX` must be specified, and as an integer.
- :warning: `TASK_INDEX` must be within range of 1 - N, where N is the number of tasks in the task list.

Expected outcome and examples of usage:

Alfred will place a check on the Task to mark it as done:
```
done 1
____________________________________________________________
Duly noted on completion of task, sir.
    1.[T][X] Meet Catwoman
____________________________________________________________
```
<br />

### <a name="delete"></a>:x: `delete` - Delete a task
Deletes a task in the task list based on index specified.

Usage: `delete TASK_INDEX`
- :warning: `TASK_INDEX` must be specified, and as an integer.
- :warning: `TASK_INDEX` must be within range of 1 - N, where N is the number of tasks in the task list.

Expected outcome and examples of usage:

Alfred will delete the index of the task specified from the task list,
and display the current number of tasks in the task list:
```
delete 1
____________________________________________________________
 Very well, Master Wayne, I shall remove this: 
    1.[T][ ] Meet Catwoman
 Sir, the number of Tasks you have scheduled currently amounts to 2.
____________________________________________________________
```
<br />

### <a name="find"></a>:mag_right: `find` - Searching for tasks
Searches for tasks in the task list based on the query term specified.

Usage: `find QUERY`
- :warning: `QUERY` must be specified.
- :bulb: `QUERY` is not case-sensitive.

Expected outcome and examples of usage:

Alfred will search the task list using the query specified, enumerate the tasks found,
and print them out as a list:
```
find cs2113
____________________________________________________________
 I've procured the following tasks based on that query, sir:
 1.[T][ ] CS2113 IP
 2.[D][ ] CS2113 IP (by: Oct 01 2021)
 3.[T][ ] CS2113 TP
____________________________________________________________
```
<br />

### <a name="exit"></a>:door: Exiting the app: `bye`
Terminates the app session and exits programme.

Usage: `bye`
- :bulb: `bye` is not case-sensitive.

Expected outcome and examples of usage:

Alfred will print out the exit message and the app session will be terminated:
```
bye
____________________________________________________________
 Very well sir, I shall leave you to your own devices.
____________________________________________________________
```
<br />

## <a name="save"></a>:floppy_disk: Saving the data
:bulb: Alfred's task list is saved into the user's local storage automatically after every command
execution, and thus there is no explicit `save` command implemented.
<br />

## <a name="summary"></a>Command Summary

Command | Format, Examples
--------|--------------
List Tasks | `list`
Add Todo | `todo TODO_DESCRIPTION` <br /> eg.`todo CS2113T IP`
Add Event | `event EVENT_DESCRIPTION /at EVENT_DATE` <br /> eg.`event CS2101 OP1 /at 10092021`
Add Deadline | `deadline DEADLINE_DESCRIPTION /at DEADLINE_DATE` <br /> eg.`deadline CS2113T IP /by 01-10-2021`
Done Task | `done TASK_INDEX` <br /> eg.`done 1`
Delete Task | `delete TASK_INDEX` <br /> eg.`delete 2`
Find Task(s) | `find QUERY` <br /> eg.`find CS`
Exit App | `bye`