# User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).
If you can type fast, Duke can get your task management done faster than traditional GUI apps.

## Features

### Notes about the command format:
Words in UPPER_CASE are the parameters to be supplied by the user. <br />
e.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo read book`

<br />

### `list` - Lists current tasks
Lists all the tasks in your list.

Example:

```
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
1. [T][ ] homework
2. [D][ ] assignment (by: tuesday night)
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
done 1
____________________________________________________________
Nice! I've marked this task as done:[T][X] homework
____________________________________________________________
```
<br />

### `delete` - Delete task
Deletes a task, based on its index.

Format: `delete TASK_INDEX`<br />
* Deletes the task at the specified `TASK_INDEX`.<br />
* The index refers to the index number shown in the displayed task list.<br />
* The index must be a positive integer 1, 2, 3, ...<br />

Example:
```
____________________________________________________________
delete 1
____________________________________________________________
Noted. I've removed this task:  [D][ ] assignment (by: tuesday night)
Now you have 3 tasks in the list.
____________________________________________________________
```
<br />

### `todo` - Create todo task
Adds a todo task into your list.

Format: `todo TASK_DESCRIPTION`
* The `TASK_DESCRIPTION` cannot be empty

Example:
```
____________________________________________________________
todo take out trash
____________________________________________________________
Got it. I've added this task: 2. [T][ ] take out trash
Now you have 2 tasks in the list.
____________________________________________________________
```
<br />

### `deadline` - Create deadline task
Adds a deadline task into your list.

Format: `deadline TASK_DESCRIPTION /by DEADLINE_TIME`
* The `TASK_DESCRIPTION` and `DEADLINE_TIME` cannot be empty

Example:
```
____________________________________________________________
deadline tutorial 1 /by monday 2359
____________________________________________________________
Got it. I've added this task: 3. [D][ ] tutorial 1 (by: monday 2359)
Now you have 3 tasks in the list.
____________________________________________________________
```
<br />

### `event` - Create event task
Adds an event task into your list.

Format: `event TASK_DESCRIPTION /at EVENT_TIME`
* The `TASK_DESCRIPTION` and `EVENT_TIME` cannot be empty

Example:
```
____________________________________________________________
event seminar /at monday 2-4pm
____________________________________________________________
Got it. I've added this task: 4. [E][ ] seminar (at: monday 2-4pm)
Now you have 4 tasks in the list.
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
1. [D][ ] cs2113t assignment (by: tuesday night)
2. [D][X] cs 2040c assignment (by: monday night)
____________________________________________________________
```

### `bye` - Exits the program
Exits the program.

Format: `bye`

### Saving task data
Duke saves your task list from the usage into your local storage as a text file.<br />
Upon reinitialization, it reloads that file, so you access previously added tasks

##Command Summary
Action | Format/Examples |
------ | --------------- |
todo | `todo TASK_DESCRIPTION` <br /> e.g. `todo buy food`
deadline | `deadline TASK_DESCRIPTION /by DEADLINE_TIME`<br /> e.g. `deadline tutorial 1 /by monday`
event | `event TASK_DESCRIPTION /at EVENT_TIME` <br /> e.g. `event lecture /at Thursday 4pm`
done | `done TASK_INDEX` <br /> e.g. `done 1`
delete | `delete TASK_INDEX` <br /> e.g. `delete 1`
find | `find KEYWORD` <br /> e.g. `find homework`
list | `list`
bye | `bye`