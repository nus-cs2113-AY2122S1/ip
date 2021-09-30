# Duke User Guide

## Features 

### Track tasks

Duke helps users to track their tasks, be it todos, deadlines or events.

### Filtering of tasks

Duke enables users to quickly and easily search for specific tasks using keywords.

### Easy update

Duke allows for easy update of the existing list of tasks 
through mark as done and deletion functions.

### Storage 

Duke keeps a record of the existing list of tasks even after the 
program is stopped and loads it up each time it is run so that no data is lost. 


## Usage

### `list` - Lists all the tasks

Print out the list of tasks 

Example of usage: 

`list`

Expected outcome:

List of all the tasks 

```
____________________________________________________________
 Here are the tasks in your list:
 1. [T][ ] borrow book
 2. [D][X] return book (by: Sunday)
 3. [E][ ] project meeting (at: Mon 2-4pm)
____________________________________________________________
```
### `todo <description>` - Create a todo task

Add a new todo task to the list

Example of usage:

`todo read book`

Expected outcome:

Overview of the newly added task and current number of tasks 

```
____________________________________________________________
 Got it. I've added this task:
 [T][ ] borrow book
 Now you have 1 tasks in the list.
____________________________________________________________
```
### `deadline <description> /by <time>` - Create a deadline task

Add a new deadline task to the list

Example of usage:

`deadline return book /by Sunday`

Expected outcome:

Overview of the newly added task and current number of tasks

```
____________________________________________________________
 Got it. I've added this task:
 [D][ ] return book (by: Sunday)
 Now you have 2 tasks in the list.
____________________________________________________________
```
### `event <description> /at <time>` - Create an event task

Add a new event task to the list

Example of usage:

`event project meeting /at Mon 2-4pm`

Expected outcome:

Overview of the newly added task and current number of tasks

```
____________________________________________________________
 Got it. I've added this task:
 [E][ ] project meeting (at: Mon 2-4pm)
 Now you have 3 tasks in the list.
____________________________________________________________
```
### `done <index>` - Mark a task as done

Update status of task at the index position of the list 

Example of usage:

`done 2`

Expected outcome:

Overview of task marked as done 

```
____________________________________________________________
 Nice! I've marked this task as done:
 [D][X] return book (by: Sunday)
____________________________________________________________
```
### `find <keyword>` - Find tasks by searching for a keyword

Print a list of tasks containing the keyword

Example of usage:

`find book`

Expected outcome:

A list of tasks containing the keyword

```
____________________________________________________________
 Here are the matching tasks in your list:
 1.[T][X] read book
 2.[D][X] return book (by: June 6th)
____________________________________________________________
```

### `delete <index>` - Remove a task

Delete a task at the index position from the list 

Example of usage:

`delete 3`

Expected outcome:

Overview of recently deleted task and current number of tasks

```
____________________________________________________________
 Noted. I've removed this task: 
 [E][ ] project meeting (at: Aug 6th 2-4pm)
 Now you have 4 tasks in the list.
____________________________________________________________
```
