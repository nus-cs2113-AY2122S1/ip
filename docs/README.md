# User Guide

Duke is a Command Line Application to help you manage your tasks.

## Start Guide

1. Ensure you have `Java 11` on your local machine (computer).
2. Create a new folder on your local machine.
3. Download the `jar` file [here](https://github.com/EricBryann/ip/releases/tag/A-Release).
4. Move the `jar` file to the newly created folder.
5. Execute `java -jar ip.jar` in a terminal in the folder. Alternatively, try to double-click on the `jar` file. The following output should be observed:

``` 
    ____________________________________________________________
     Hello! I'm Duke
     What can I do for you?
    ____________________________________________________________
```
6. Enter the command into the terminal to interact with Duke. Refer to the [features](#features) section below for the various supported commands.


## Features

* [Add a Task](#add-a-todo-task--todo)
  * [Add a Todo](#add-a-todo-task--todo)
  * [Add a Deadline](#add-a-deadline-task--deadline)
  * [Add an Event](#add-an-event-task--event)
* [Mark Task as Done](#mark-task-as-done--done)
* [List All Tasks](#list-all-tasks--list)
* [Delete a Task](#delete-a-task--delete)
* [Find a Task](#find-a-task--find)
* [Exit Duke](#exit-the-program--bye)

## Usages
### Add a Todo Task : `todo`
Add a Todo task into the list.
<br>
Format: `todo TASK_NAME`
* `TASK_NAME` must be present.

Example of usage:
<br>
`todo Read Book`

Expected output:
```
    ____________________________________________________________
     Got it. I've added this task:
      [T][ ] Read Book
     Now you have 1 task in the list.
    ____________________________________________________________
```

<br>

### Add a Deadline Task : `deadline`
Add a Deadline task that has a deadline date/time into the list.
<br>
Format: `deadline TASK_NAME /by TASK_DEADLINE`
* `TASK_NAME`, `/by` and `TASK_DEADLINE` must be present.
* `/by` must be surrounded by one space both on its left and right.

Example of usage:
<br>
`deadline Do CS2113 homework /by Sunday 2359`

Expected output:
```
    ____________________________________________________________
     Got it. I've added this task:
      [D][ ] Do CS2113 homework (by: Sunday 2359)
     Now you have 2 tasks in the list.
    ____________________________________________________________
```

<br>

### Add an Event Task : `event`
Add an event task that has additional information into the list.
<br>
Format: `event TASK_NAME /at TASK_INFO`
* `TASK_NAME`, `/at` and `TASK_INFO` must be present.
* `/at` must be surrounded by one space both on its left and right.

Example of usage:
<br>
`event Career Fair /at NUS`

Expected output:
```
    ____________________________________________________________
     Got it. I've added this task:
      [E][ ] Career Fair (at: NUS)
     Now you have 3 tasks in the list.
    ____________________________________________________________
```

<br>

### Mark Task as Done : `done`
Mark any task in the list as done.
<br>
Format: `done TASK_INDEX`
* `TASK_INDEX` must be present.
* `TASK_INDEX` must be an integer between 1 and tasks list size inclusive.

Example of usage:
<br>
`done 2`

Expected output:
```
    ____________________________________________________________
     Nice! I've marked this task as done:
       [D][X] Do CS2113 homework (by: Sunday 2359)
    ____________________________________________________________
```

<br>

### List All Tasks : `list`
List all the tasks.
<br>
Format: `list`

Example of usage:
<br>
`list`

Expected output:
```
    ____________________________________________________________
     1.[T][ ] Read Book
     2.[D][X] Do CS2113 homework (by: Sunday 2359)
     3.[E][ ] Career Fair (at: NUS)
    ____________________________________________________________
```
<br>

### Delete a Task : `delete`
Delete a task in the list.
<br>
Format: `delete TASK_INDEX`
* `TASK_INDEX` must be present.
* `TASK_INDEX` must be an integer between 1 and tasks list size inclusive.

Example of usage:
<br>
`delete 1`

Expected output:
```
    ____________________________________________________________
     Noted. I've removed this task: 
       [T][ ] Read Book
     Now you have 2 tasks in the list.
    ____________________________________________________________
```

<br>

### Find a Task : `find`
Find all tasks in the list that match the filter word(s) given.
<br>
Format: `find [FILTER_WORD]`
* `FILTER_WORD` is optional, but it is more meaningful to include a filter word.

Example of usage:
<br>
`find Career Fair`

Expected output:
```
   ____________________________________________________________
     Here are the matching tasks in your list:
     1.[E][ ] Career Fair (at: NUS)
    ____________________________________________________________
```

<br>

### Exit the Program : `bye`
Exit Duke program.
<br>
Format: `bye`

Example of usage:
<br>
`bye`

Expected output:
```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```