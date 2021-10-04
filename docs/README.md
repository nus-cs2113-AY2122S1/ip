# User Guide

Task_Tracker is a user-friendly application to help you with task management!

## Features 

### Todo

Tracks a task with no date required.

### Event

Tracks a task with a date at which it occurs.

### Deadline

Tracks a task with a date at which it is due.


## Usage

### `helpme` - displays list of commands that Task_Tracker uses

type helpme in the command line interface to obtain the list of commands to use Task_Tracker

Example of usage: 

`helpme`

Expected outcome:

you will see a list of commands that you use with Task_Tracker


Description of the outcome.

```
____________________________________________________________
todo <task> - Adds a todo task!
deadline <task> /by <due date> - Adds a deadline task!
event <task> /at <when> - Adds an event task!
list - Lists all tasks tracked!
done <task number> - Marks selected task number as done with X !
bye - Exits Task_Tracker!
helpme - Get help!
____________________________________________________________
```
### `todo` - Tracks a task with no date required

type todo followed by a task in the command line interface to begin tracking a todo task

Example:

`todo sleep`

Expected outcome:

tracking of one todo task will begin. todo has no dates

Output:

```
____________________________________________________________
Great! This todo task has been added!!
[T][ ] sleep
You now have 1 tasks in the list!
____________________________________________________________
```
### `event` - Tracks a task with a date at which it occurs

type event followed by a task, /at followed by the date in YYYY-MM-DD format in the command line interface to begin tracking an event task

Example:

`event wake up /at 2021-09-30
`

Expected outcome:

tracking of one event task will begin. event has a date at which it occurs

Output:

```
____________________________________________________________
Great! This event task has been added!
[E][ ] wake up (at: 2021-09-30)
You now have 1 tasks in the list!
____________________________________________________________
```
### `deadline` - Tracks a task with a date at which it is due

type deadline followed by a task, /by followed by the date in YYYY-MM-DD format in the command line interface to begin tracking a deadline task

Example:

`deadline finish homework /by 2021-10-01
`

Expected outcome:

tracking of one deadline task will begin. deadline has a date at which it is due
Output:
```
____________________________________________________________
Great! This deadline task has been added!
[D][ ] finish homework (by: 2021-10-01)
You now have 1 tasks in the list!
____________________________________________________________

```
### `list` - gives the list of tasks being tracked currently

type list to see a list of all tasks currently being tracked

Example:

`list`

Expected outcome:

a list will show up to display all tasks in the list

Output:

```
____________________________________________________________
Here are the tasks in your list!
1.[T][ ] sleep
2.[E][ ] wake up (at: 09 30 2021)
3.[D][ ] finish homework (by: 10 01 2021)
____________________________________________________________

```
### `done` - marks a task from the list as done

type done followed by an integer number to mark a task from the list as done

Example:

`done 1`

Expected outcome:

marks the task with corresponding number with a 'X'

Output:

```
____________________________________________________________
Great! This task has been marked as done!!
[T][X] sleep
____________________________________________________________
```

### `find` - searches the current list of tasks for existing tasks

type find followed by one keyword to retrieve the tasks with the keyword in the list

Example of usage:

`find sleep`

Expected outcome:

gives a list of tasks containing keyword

Output:

```
____________________________________________________________
Here are the tasks in your list!
1. [T][X] sleep
2. [T][ ] sleep on the car
3. [T][ ] sleep on the bed
4. [T][ ] sleep on the chair
____________________________________________________________
```
### `delete` - deletes a task in the list

type delete followed by an integer to delete the task with respective integer

Example of usage:

`delete 1`

Expected outcome:

deletes the task of corresponding number in the list

Output:

```
____________________________________________________________
This task has been removed!
[T][X] sleep
You now have 3 tasks in the list!
____________________________________________________________
```
