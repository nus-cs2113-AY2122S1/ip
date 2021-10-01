# User Guide

## Setting Up
Place `"ip.jar"` in an empty folder.
### Launching Duke
Launch the terminal and change the directory to that of the folder "ip.jar" is in.
Enter the command below into the terminal to run the program.
```
java -jar ip.jar
```
## Features 

### Add task

Add a task. A task can be one of the following:
1. Todo - A simple task.
2. Deadline - A task with a date to complete by.
3. Event - A task with a start and end datetime.


### Delete task

Delete a task.

### Find task

Find tasks with which includes the provided substring.

### Mark done

Mark the task as complete.

### List

List all the tasks.


## Usage

### `todo` - Add a todo.

Add a todo for duke to track.

Example of usage: 

`todo read book`

Expected outcome:

```
Got it. I've added this task:
    [T][ ] read book
Now you have 1 tasks in the list.
```
### `deadline` - Add a deadline.

Add a deadline for duke to track.

Example of usage:

`deadline return book /by 11 August`

Expected outcome:

```
Got it. I've added this task:
    [D][ ] return book (by: 11 August)
Now you have 2 tasks in the list.
```
### `event` - Add an event.

Add an event for duke to track.

Example of usage:

`event project meeting /at 11 August from 9pm to 11pm`

Expected outcome:

```
Got it. I've added this task:
    [E][ ] project meeting (at: 11 August from 9pm to 11pm)
Now you have 3 tasks in the list.
```

### `done` - Mark a task as done.

Mark a task as complete.

Example of usage:

`done 1`

Expected outcome:

```
Nice. I've marked this task as done:
    [T][X] read book
```
### `delete` - Delete a task.

Delete a task. After delete, the task will no longer be tracked by Duke.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
    [T][X] read book
Now you have 2 tasks in the list.
```

### `list` - List all tasks.

List all tasks currently tracked by Duke.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list
1.[D][X] return book (by: 11 August)
Here are the tasks in your list
2.[E][ ] project meeting (at: 11 August from 9pm to 11pm)
```

### `find` - Find tasks which contains given substring.

Find, among tasks that are currently tracked by duke, those that contain a given substring.

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1.[D][X] return book (by: 11 August)
```
### `bye` - Close duke.

End the duke session, tasks tracked at point of exit will still exist in the next launch.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
