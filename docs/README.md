# User Guide

Duke is a **desktop app for managing tasks**, optimized for use via a **Command Line Interface (CLI)**.
  There is no Graphical Interface (GUI), but the program will better than traditional apps if you can type fast.
<br />
 ----------------
## Features

### Notes about command format:
1. Words in UPPER_CASE are the parameters to be supplied by the user. <br />
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`

2. Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `bye` and `clear`) will be ignored.
e.g. if the command specifies `help lmao`, it will be interpreted as `help`.

<br />

### Viewing help: `help`
Displays usage information on all available commands.

Format: `help`

<br />

### Adding a todo task: `todo`
Adds a todo task to the task list.

Format: `todo DESCRIPTION`
* `DESCRIPTION` cannot be empty.

Example:
```
|| _____________________________________________________________________
|| What would you like to do?  Type 'help' if you're confused!
|| todo do homework
|| _____________________________________________________________________
|| Got it! I've added this task: 
|| [T][ ] do homework
|| Now you have 1 tasks in the list.
|| _____________________________________________________________________
```
<br />

### Adding a task with deadline: `deadline`
Adds a deadline task to the task list.

Format: `deadline DESCRIPTION /by DEADLINE`
* `DESCRIPTION` and `DEADLINE` cannot be empty
* `DEADLINE` should be formatted as `yyyy-mm-dd`, but other formats are also accepted.

Example:
```
|| _____________________________________________________________________
|| What would you like to do?  Type 'help' if you're confused!
|| deadline CS2113T iP /by 2021-10-01
|| _____________________________________________________________________
|| Got it! I've added this task: 
|| [D][ ] CS2113T iP (by: Oct 1 2021)
|| Now you have 2 tasks in the list.
|| _____________________________________________________________________
```
<br />

### Adding an event task: `event`
Adds an event task to the task list.

Format: `event DESCRIPTION /at DATE_TIME`
* `DESCRIPTION` and `DATE_TIME` cannot be empty

Example:
```
|| _____________________________________________________________________
|| What would you like to do?  Type 'help' if you're confused!
|| event birthday party /at Tomorrow 6pm
|| _____________________________________________________________________
|| Got it! I've added this task: 
|| [E][ ] birthday party (at: Tomorrow 6pm)
|| Now you have 3 tasks in the list.
|| _____________________________________________________________________
```
<br />

### Listing all tasks: `list`
Shows a list of all the tasks in the task list.

Example:

```
|| What would you like to do?  Type 'help' if you're confused!
|| list
|| _____________________________________________________________________
|| Here are the tasks in your list:
|| 1.[T][ ] do homework
|| 2.[D][ ] CS2113T iP (by: Oct 1 2021)
|| 3.[E][ ] birthday party (at: Tomorrow 6pm)
|| _____________________________________________________________________
```
<br />

### Marking a task as done: `done`
Marks a task in the task list as done.

Format: `done TASK_INDEX`
* Marks the task at the specified `TASK_INDEX` as done.<br />
* `TASK_INDEX` refers to the index number shown in the task list.<br />
* `TASK_INDEX` must be a positive integer (e.g. 1, 2, 3, ...). <br />
* This command can only be performed on an undone task.

Example:

```
|| _____________________________________________________________________
|| What would you like to do?  Type 'help' if you're confused!
|| done 1
|| _____________________________________________________________________
|| Nice! You did the following task:
|| [X] do homework
|| _____________________________________________________________________
```
<br />

### Deleting a task: `delete`
Deletes the specified task from the task list.

Format: `delete TASK_INDEX`
* Deletes the task at the specified `TASK_INDEX`.<br />
* `TASK_INDEX` refers to the index number shown in the task list.<br />
* `TASK_INDEX` must be a positive integer (e.g. 1, 2, 3, ...). <br />

Example:
```
|| _____________________________________________________________________
|| What would you like to do?  Type 'help' if you're confused!
|| delete 2
|| _____________________________________________________________________
|| Got it. I've removed this task for you: 
|| [D][ ] CS2113T iP (by: Oct 1 2021)
|| Now you have 2 tasks in the list.
|| _____________________________________________________________________
```
<br />

### Finding tasks: `find`
Finds and displays tasks that match a specified keyword.

Format: `find KEYWORD`
* Will only search and match task descriptions.
* Search is case-insensitive. (e.g. `read book` will match `READ BOOK`)

Example:
```
|| _____________________________________________________________________
|| What would you like to do?  Type 'help' if you're confused!
|| find homework
|| _____________________________________________________________________
|| Here are the matching tasks in your list: 
|| 1.[T][X] do homework
|| _____________________________________________________________________
```
<br />

### Clearing all tasks: `clear`
Clears all tasks from the task list.

Format: `clear`

<br />

### Exiting the program: `bye`
Exits the program.

Format: `bye`

 <br />

### Saving task data
Tasks in the task list are saved automatically after every command by Duke. <br />
There is no need to save manually.

<br />

 --------------
##Command Summary

Action | Format/Examples |
------ | --------------- |
todo | `todo DESCRIPTION` <br /> e.g. `todo do homework`
deadline | `deadline DESCRIPTION /by DEADLINE` <br /> e.g. `deadline assignment /by 2021-10-20`
event | `event DESCRIPTION /at DATE_TIME` <br /> e.g. `event party /at Tomorrow 6pm`
done | `done TASK_INDEX` <br /> e.g. `done 1`
delete | `delete TASK_INDEX` <br /> e.g. `delete 1`
find | `find KEYWORD` <br /> e.g. `find assignment`
list | `list`
help | `help`
clear | `clear`
bye | `bye`
