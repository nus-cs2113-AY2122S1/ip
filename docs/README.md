# User Guide

Duke is a *desktop app for managing your tasks, optimized for use via a Command Line Interface* (CLI). If you can type
fast, Duke can get your task management done faster than traditional GUI apps. It is the perfect app for busy people!

1. Features
   1. Adding a todo: `todo`
   2. Adding an event: `event`
   3. Adding a deadline: `deadline`
   4. List all tasks: `list`
   5. Marking a task as done: `done`
   6. Deleting a task: `delete`
   7. Finding a task with a keyword: `find`
2. Command Summary

## Features


####Command format:


* Words in `UPPER_CASE` are the parameters to be supplied by the user. (eg. `todo` `TASK_DESCRIPTION`, `TASK_DESCRIPTION` is 
the description of the 
task supplied by the user.)
* Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) is not acceptable.
   e.g. if the command specifies `list 123`, there will be an error.


###Adding a todo


Adds a todo to your list of tasks.

format: `todo TASK_DESCRIPTION`

Example input:

`todo lunch`

`todo deliver parcel`


###Adding an Event


Adds an event to your list of tasks.

format: `event TASK_DESCRIPTION /at TIME_DESCRIPTION`

Example input:

`event family dinner /at sunday`

`event concert /at friday night`


###Adding a Deadline


Adds a deadline to your list of tasks.

format: `deadline TASK_DESCRIPTION /by TIME_DESCRIPTION`

Example input:

`deadline assignment /by sunday night`

`deadline complete report /by monday`


###Listing Tasks


Shows a list of all tasks.

Format: `list`


###Mark Task as Done


Marks the task with the corresponding number in the list as done.

Format: `done TASK_NUMBER`

Example input:

`done 1`


###Delete Task


Deletes the task with the corresponding number in the list.

Format: `delete TASK_NUMBER`

Example input:

`delete 1`


###Find Task


Finds all tasks containing user input keyword in their descriptions

Format: `find KEYWORD`

Example input:

`find lunch`


###Bye


Exits the program.

Format: `bye`


## Command Summary


Action | Format, Examples
------ | ----------------
todo | `todo TASK_DESCRIPTION` eg. `todo deliver parcel`
event | `event TASK_DESCRIPTION /at TIME_DESCRIPTION` eg. `event concert /at friday night`
deadline | `deadline TASK_DESCRIPTION /by TIME_DESCRIPTION` eg. `deadline assignment /by sunday night`
list | `list`
done | `done TASK_NUMBER` eg. `done 1`
delete | `delete TASK_NUMBER` eg. `delete 1`
find | `find KEYWORD` eg. `find lunch`
bye | `bye`