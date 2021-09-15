# User Guide
Duke is a task manager app for keeping track of tasks. It utilizes a Command Line Interface (CLI) to 
maximize performance and minimize clutter.
## Features
### Add a to-do: `todo`  
Adds a to-do to the list of tasks.  
Format: `todo TASKNAME`  
Examples:
* `todo water the plants`
* `todo walk the dog`
### Add a deadline: `deadline`
Adds a deadline to the list of tasks.  
Format: `deadline TASKNAME /by DATE`  
* `DATE` _must_ have a format of `yyyy-MM-dd`.

Examples:
* `deadline CS2113T homework /by 2021-09-25`
* `deadline wrap Christmas presents /by 2021-12-22`
### Add an event: `event`
Adds an event to the list of tasks.  
Format: `event TASKNAME /at DATE`
* `DATE` **must** have a format of `yyyy-MM-dd`.

Examples:
* `event Pi day circonference /at 2021-07-22`
* `event Christmas dinner /at 2021-12-25`
### List all tasks: `list`
Prints the list of tasks to the command line.  
Format: `list`
### Mark as done: `done`
Marks an existing task as done.  
Format: `done INDEX`  
* Marks the task at the specified INDEX done. The index refers to the index number shown in the task list. 
* The index **must** be a positive integer and should not exceed the number of tasks in the list.

Examples:
* `done 3` marks the third task in the list as done.
* `done 5` marks the fifth task in the list as done.
### Delete a task: `delete`
Deletes an existing task.  
Format: `delete INDEX`
* Deletes the task at the specified INDEX. The index refers to the index number shown in the task list.
* The index **must** be a positive integer and should not exceed the number of tasks in the list.

Examples:
* `delete 1` deletes the first task in the list.
* `delete 4` deletes the fourth task in the list.
### Find tasks by description: `find`
Find tasks whose descriptions contain the given substring.  
Format: `find KEYWORD`
* The search is case-sensitive, e.g. `isothermally` will not match `IsOtherMally`
* Partial words will be matched e.g. `edict` matches `valedictorian`  
* The date is not included in the search.

Examples:
* `find meet` returns `Pi day meeting` and `meet my mom`.
* `find red car` returns `Drive red car` and `Administered carnival`.
### End the program: `bye`
Exits the program.  
Format: `bye`
### Saving and loading
The list of tasks within Duke are saved to the hard disk automatically after any changes are made to the list. 
Manual saving is not necessary.
### Editing the data file
Duke's data is saved as a .txt file `[JAR file location]/data/saveDuke.txt`.
Advanced users are welcome to directly modify the data file.  
| :exclamation: **Warning:** If your changes to the file are in the wrong format, Duke will erase all tasks from memory and start a new save data file. Edit with caution.  |
|-|