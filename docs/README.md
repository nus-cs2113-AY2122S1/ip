# User Guide
```
                              _     _ 
                             ( \\---/ )
                              ) . . (
________________________,--._(___Y___)_,--._______________________
                        `--'           `--'
```
Author: Li Xi Yuan

## Overview
Duke is a simple CLI-based task planner
<br/><br/>
## Features 

### Add Todos, Deadlines, or Events
Supports 3 types of tasks

### Save and load task list
Save and load your task list on your computer automatically,
in the same directory as the duke.jar file.

### Mark tasks as done
You can mark tasks as done and delete them

### Find
Find tasks by their description
<br/><br/>
## Usage

### `list` - Displays your task list
Displays your task list. Includes type of task, and task status.

Syntax:`list`

Expected outcome:

```
____________________________________________________________
	Here is your task list:
		1. [T][ ] buy lunch
		2. [E][X] attend sports meet (at: 6am)
		3. [D][ ] finish quiz (by: 7am)
____________________________________________________________
```
<br/><br/>
### `bye` - Quits the program
Syntax: `bye`
<br/><br/>
### `todo` - Adds a todo
Todos are tasks that only contain a description

Syntax:
`todo [description]`

Example:

`todo Buy lunch`
```
	Task Added:
		[T][ ] buy lunch
	You now have 1 tasks
```
<br/><br/>
### `deadline` - Adds a deadline
Deadlines are tasks that contains a description and do-by date

Syntax: `deadline [description] /by [date]`

Example:

`deadline study for math test /by 7pm today`
````
	Task Added:
		[D][ ] study for math test (by: 7pm today)
	You now have 2 tasks
````
<br/><br/>
### `event` - Adds an event
Events are tasks that contains a description and a date the event is happening at

Syntax: `event [description] /at [date]`

Example:

`event attend concert /at 6 June 6pm`
````
	Task Added:
		[E][ ] attend concert (at: 6 June 6pm)
	You now have 3 tasks
````
<br/><br/>
### `done` - Marks a task as done
Use `list` to decide which task number you wish to mark as done

Syntax: `done [task number]`

Example:

`done 1`
````
	Nice! I have marked this task as done:
		[T][X] buy lunch
````
<br/><br/>
### `delete` - Deletes a task
Use `list` to decide which task number you wish to mark as delete

Syntax: `delete [task number]`

Example:

`delete 2`
````
	Item deleted:
		[D][ ] study for math test (by: 7pm today)
____________________________________________________________
	Here is your task list:
		1. [T][X] buy lunch
		2. [E][ ] attend concert (at: 6 June 6pm)
____________________________________________________________
````
<br/><br/>
### `find` - Finds a task by its description
Query is case-insensitive

Syntax: `find [query]`

Example:

`find lun`
````
____________________________________________________________
	Here are the matching tasks in your list:
		[T][X] buy lunch
____________________________________________________________
````
