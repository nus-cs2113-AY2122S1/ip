# User Guide

## Features 

### 1. Add and Delete Tasks

There are three different types of tasks that can be tracked - 
todo, deadline and event.

### 2. List Tasks

A list of all the tasks will show. Details such as 
which category of task, completion of task and description
of tasks will be shown.

### 3. Complete Tasks

Once you are done with a task, you can mark
the task as completed.

### 4. Find Tasks

Search for tasks with a certain keyword. This 
will allow you to see specific tasks that
contains the keyword of your choice.

## Usage

### `todo` - Adds todo task

Begin your input with a 'todo' followed by
the description of the todo task.

Example of usage: 

`todo borrow book`

Expected outcome:

A new todo task with the corresponding description
will be added.

```
--------------------
Got it. I've added this task: 
[T] [ ] borrow book
Now you have 1 tasks in the list.
--------------------
```
### `deadline` - Adds deadline task

Begin your input with a 'deadline' followed by
the description of the deadline task. Remember to include '/by'
followed by the deadline description at the end.

Example of usage:

`deadline return book /by tonight`

Expected outcome:

A new deadline task with the corresponding description
and deadline will be added.

```
--------------------
Got it. I've added this task: 
[D] [ ] return book (by: tonight)
Now you have 1 tasks in the list.
--------------------
```
### `event` - Adds event task

Begin your input with a 'event' followed by
the description of the event task. Remember to include '/at'
followed by the event timing description at the end.

Example of usage:

`event library party /at tonight 10pm`

Expected outcome:

A new event task with the corresponding description
and event timing will be added.

```
--------------------
Got it. I've added this task: 
[E] [ ] library party (at: tonight 10pm)
Now you have 1 tasks in the list.
--------------------
```
### `delete` - Deletes a task

Removes a specific task from the list.

Example of usage:

`delete 1`

Expected outcome:

A specified task will be removed.

```
--------------------
Noted. I've removed this task:
1.[ ] library party 
Now you have 0 tasks in the list.
--------------------
```
### `list` - Lists all tasks

A list of all tasks will be shown.

Example of usage:

`list`

Expected outcome:

A list of all tasks will be shown according to 
the sequence that they were added.

```
--------------------
Here are the tasks in your list:
1.[T] [ ] eat meat
2.[D] [ ] drink water (by: tonight)
3.[E] [ ] workout (at: midnight)
--------------------
```
### `done` - Mark a task as completed

A specified task will be marked as completed.

Example of usage:

`done 1`

Expected outcome:

A specified task will be marked as completed. This
task will still remain on the list.

```
--------------------
Nice! I've marked this task as done:
1.[X] eat meat
--------------------
```
### `find` - Find tasks containing keyword

All tasks in the list containing the specific
keyword will be shown.

Example of usage:

`find water`

Expected outcome:

All tasks in the list containing the specific
keyword will be shown.

```
--------------------
Here are the matching tasks in your list:
2.[D] [ ] drink water (by: tonight)
--------------------
```
### `bye` - Terminates program safely

Duke will be terminated safely.

Example of usage:

`bye`

Expected outcome:

A goodbye message will be shown.

```
--------------------
Bye. Hope to see you again soon!
 
--------------------
```


