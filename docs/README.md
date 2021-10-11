# User Guide

## Features

### Storing of tasks

Allows the user (you!!) to store tasks into a list. Information about your tasks are saved to a txt
file in the harddrive. 

Tasks are divided into 3 subcategories: Todo, Deadline, Event

### Marking of tasks as done

Once a task is completed by you, you can choose to mark it as done. 
It will be shown that the task is done when the list of all tasks are shown.

### Deleting of tasks

If you want to delete a certain task, you can simply do so. The task will be deleted from 
the program, and also from the file in your harddrive.

### Filtering of tasks

You can input search words that will allow the program to print out the tasks that contain
the given search words in order. 

## Usage

### `list` - Lists all the tasks in sequential order

The tasks that have been added will be printed out in sequential order, showing information
that each and every task contains, and whether the task has been completed. 

Example of usage:

`list`

Expected outcome:

Shows the sequential order of tasks. Information included are: Task type, whether it is completed, 
task description, date.

```
1.[D][X] say hi (by: Sep 3 2020)
2.[E][ ] marathon (at: Sep 25 2020)
```

### `done` - Marks the task as completed

Compulsory parameter [index of task] must be included as a parameter.
This marks the task as done by including a [X] at the start of task. 

Example of usage:

`done 2`

Expected outcome:

A done message will be printed out, followed by the done task at the bottom.
(Notice the [X], which indicates completion)

```
Nice... I guess I will mark this task as done...:
        [E][X] marathon (at: Sep 25 2020)
```

### `delete` - Deletes the task

Compulsory parameter [index of task] must be included as a parameter.
This deletes the given task from the list of all tasks, and also from the harddrive.

Example of usage:

`delete 2`

Expected outcome:

A delete message will be printed out, showing the task that has been deleted from the list.
The total remaining number of tasks will be printed out as well.

```
Are you sure about this? I'll just delete this anyway...
        [E][X] marathon (at: Sep 25 2020)
    Now you have 3 tasks in the list...
```

### `todo` - Adds a todo task

Adds a todo task in the list of all tasks. Todo is a task type that only 
requires 1 parameter [description].

Example of usage:

`todo get coffee`

Expected outcome:

A todo task added message will be printed out, alongside the todo task that has been added.
The current total number of tasks will be shown.

```
Okay... I guess I'll add this task... 
        [T][ ] get coffee
    Now you have 4 tasks in the list...
```

### `deadline` - Adds a deadline task

Adds a deadline task in the list of all tasks. Deadline is a task type that
requires 2 parameters [description] and [date]. The input will be split by `/by`. 
[date] is required to have the format YYYY-MM-DD. 

Example of usage:

`deadline CS2113 Assignment /by 2021-09-28`

Expected outcome:

A deadline task added message will be printed out, alongside the deadline task that has been added.
The current total number of tasks will be shown.

```
Okay... I guess I'll add this task... 
        [D][ ] CS2113 Assignment (by: Sep 28 2021)
    Now you have 5 tasks in the list...
```

### `event` - Adds an event task

Adds an event task in the list of all tasks. Event is a task type that
requires 2 parameters [description] and [date]. The input will be split by `/at`.
[date] is required to have the format YYYY-MM-DD.

Example of usage:

`event Full Marathon /at 2021-09-30`

Expected outcome:

An event task added message will be printed out, alongside the event task that has been added.
The current total number of tasks will be shown.

```
Okay... I guess I'll add this task... 
        [E][ ] Full Marathon (at: Sep 30 2021)
    Now you have 6 tasks in the list...
```

### `find` - Filters out tasks

Filters out all tasks with the given string. Requires 1 parameter [string].
Tasks with the given string will be printed out in sequential order.

Example of usage:

`find Sep`

Expected outcome:

Tasks with the given string will be printed out in sequential order,
together with a found message. 

```
Yay... These tasks match your find...
      1.[D][X] say hi (by: Sep 3 2020)
      2.[D][ ] CS2113 Assignment (by: Sep 28 2021)
      3.[E][ ] Full Marathon (at: Sep 30 2021)
```

### `bye` - Ends the program

Ends the program and stops it from running.

Example of usage:

`bye`

Expected outcome:

A bye message will be printed out, and the program will be terminated.

```
Please don't go... I'll miss you...
```