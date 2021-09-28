# User Guide

## Features 

### Adding Tasks

Allows you to add different types of tasks to your list

### marking Tasks as complete

Allows you to mark tasks as complete.

### Searching for Tasks

Allows you to search for specific tasks within your list

## Usage

### `list` - lists all the tasks in your list

Displays all the tasks stored in your list.

Example of usage: 

`list`

Expected outcome:

    Here are the tasks in your list:
    1.[E][ ] run (at: 04:30 Jan 15 2030)
    2.[D][ ] submit homework (by: 08:00 Jan 13 2020)

Description of the outcome.

```
expected output
```
------------------------------------------------------
### `todo` - Adding a todo

Describe the action and its outcome.

Example of usage: 

`todo (activity description)`

Expected outcome:

    Got it. I've added this task:
      [T][ ] rturn book
    Now you have 7 tasks in the list.
    
Description of the outcome.

```
expected output
```
------------------------------------------------------
### `event` - Adding an event

Adds a event task to the list.

Example of usage: 

`event run /at 07-02-1999 12:00`

Expected outcome:

    Got it. I've added this task:
      [E][ ] run (at: 12:00 Feb 07 1999)
    Now you have 8 tasks in the list.

Description of the outcome.

```
expected output
```
------------------------------------------------------
### `deadline` - Adding a deadline

Adds a deadline task to the list.

Example of usage: 

`deadline submit homework /by 07-02-1999 12:00`

Expected outcome:

    Got it. I've added this task:
      [D][ ] submit homework (by: 12:00 Feb 07 1999)
    Now you have 9 tasks in the list.0)

Description of the outcome.

```
expected output
```
------------------------------------------------------
### `done` -

Marks a task in the list as completed.

Example of usage: 

`done 1`

Expected outcome:

    Nice! I've marked this task as done:
    [T][X] jump
    
Description of the outcome.

```
expected output
```
------------------------------------------------------
### `delete` - Deletes a task from your list

Deletes a task from your list

Example of usage: 

`delete 1`

Expected outcome:

    Noted. I've removed this task:
      [T][X] jump
    Now you have 7 tasks in the list.

Description of the outcome.

```
expected output
```
------------------------------------------------------
### `find` - lists all the tasks in your list

Displays all the tasks stored in your list.

Example of usage: 

`find run`

Expected outcome:

    Here are the matching tasks in your list:
    1.[E][ ] run (at: 04:30 Jan 15 2030)
    2.[T][ ] run
    3.[E][ ] run (at: 12:00 Feb 07 1999)

Description of the outcome.

```
expected output
```
