# User Guide

Jim is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).
If you can type fast, Jim can get your task management done faster than traditional GUI apps.

## Features

### Notes about the command format:
Words in UPPER_CASE are the parameters to be supplied by the user. <br />
e.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo read book`

<br />

### `todo` - Create todo task
Adds a todo task into your list.

Format: `todo TASK_DESCRIPTION`
* The `TASK_DESCRIPTION` cannot be empty

Example:
```
____________________________________________________________
 How can I help you: todo homework
____________________________________________________________
 Got it. I've added this task:
   [T][ ] homework
 Now you have 1 tasks in the list.
____________________________________________________________
```
<br />

### `deadline` - Create deadline task
Adds a deadline task into your list.

Format: `deadline TASK_DESCRIPTION /by DEADLINE`
* The `TASK_DESCRIPTION` and `DEADLINE` cannot be empty

Example:
```
____________________________________________________________
How can I help you: deadline assignment /by tuesday night
____________________________________________________________
 Got it. I've added this task:
   [D][ ] assignment (by: tuesday night)
 Now you have 2 tasks in the list.
____________________________________________________________
```
<br />

### `event` - Create event task
Adds an event task into your list.

Format: `event TASK_DESCRIPTION /at TIME_RANGE`
* The `TASK_DESCRIPTION` and `TIME_RANGE` cannot be empty

Example:
```
____________________________________________________________
 How can I help you: event cca/at 6pm-8pm
____________________________________________________________
 Got it. I've added this task:
   [E][ ] cca (at: 6pm-8pm)
 Now you have 3 tasks in the list.
____________________________________________________________
```
<br />

### `list` - Lists current tasks
Lists all the tasks in your list.

Example:

```
____________________________________________________________
 How can I help you: list
____________________________________________________________
1. [T][ ] homework
2. [D][ ] assignment (by: tuesday night)
3. [E][ ] cca (at: 6pm - 8pm)
____________________________________________________________
```
<br />

### `done` - Mark task as done
Marks a task, based on its index, as done.

Format: `done TASK_INDEX`
* Marks the task at the specified `TASK_INDEX` as done.<br />
* The index refers to the index number shown in the displayed task list.<br />
* The index must be a positive integer 1, 2, 3, ...<br />
* The task should not be done already

Example:

```
____________________________________________________________
 How can I help you: done 1
____________________________________________________________
Nice! You're a real champ for finishing this: 
[T][X] homework
____________________________________________________________
```
<br />

### `remove` - Remove task
Removes a task, based on its index.

Format: `remove TASK_INDEX`<br />
* Removes the task at the specified `TASK_INDEX`.<br />
* The index refers to the index number shown in the displayed task list.<br />
* The index must be a positive integer 1, 2, 3, ...<br />

Example:
```
____________________________________________________________
 How can I help you: remove 1
____________________________________________________________
 This task has been spirited away:
   [T][X] homework
 Now you have 2 tasks in the list!
____________________________________________________________
```
<br />

### `find` - Find tasks
Find tasks that contains the keyword from the list.

Format: `find KEYWORD`
* The search is case-insensitive. e.g. `read book` will match `READ BOOK`
* Task description containing the keyword will be returned 
e.g. `read` will return `read book` and `read magazine`

Example:
```
____________________________________________________________
find assignment
____________________________________________________________
Here are the matching tasks in your list:
1. [D][ ] cs2113t ip (by: thursday night)
2. [D][X] st2334 assignment (by: monday night)
____________________________________________________________
```

### `clear database` - Clears the database files
Clears the tasklist and database.

Format: `clear database`

<br />

### `help` - Displays help message
Displays the available commands and the input format for them.

Format: `help`

<br />

### `bye` - Exits the program
Exits the program.

Format: `bye`

### Saving task data
Duke saves your task list from the usage into your local storage as a text file.<br />
Upon reinitialization, it reloads that file, so you access previously added tasks

--------------------------
##Command Summary
Action | Format/Examples |
------ | --------------- |
todo | `todo TASK_DESCRIPTION` <br /> e.g. `todo do homework`
deadline | `deadline TASK_DESCRIPTION /by DEADLINE` <br /> e.g. `deadline assignment /by tuesday night`
event | `event TASK_DESCRIPTION /at TIME_RANGE` <br /> e.g. `event cca/at 6pm-8pm`
done | `done TASK_INDEX` <br /> e.g. `done 1`
remove | `remove TASK_INDEX` <br /> e.g. `remove 1`
find | `find KEYWORD` <br /> e.g. `find assignment`
list | `list`
help | `help`
clear | `clear`
bye | `bye`
