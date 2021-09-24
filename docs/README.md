# DUKE User Guide
DUKE is a task manager program that is made for use via a Command Line Interface (CLI)
```text
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

What can I do for you?

```

* **Features**
    * Add a Todo task item: `todo`
    * Add an Event task item: `event`
    * Add a Deadline task item: `deadline`
    * Show all tasks: `list`
    * Find specific tasks: `find`
    * Delete a task item: `delete`
    * Mark a task item as completed: `done`
    * Exiting the program: `bye`
* **Command Summary**
##Features
Code in `UPPER_CASE` represent input fields that must be supplied by users for commands to work
### Add a Todo task item: `todo`
Add a `todo` type task into the task list.

Format: `todo DESCPRIPTION` 

Examples:
`todo CS2113 ip`

Expected outcome:
```text
todo CS2113 ip
Got it. I've added this task:
  [T][ ] CS2113 ip
Now you have 1 task in the list
```
### Add an Event task item: `event`
Add an `event` type task into the task list.

Format: `event DESCPRIPTION /[at:] OCCURING_AT`
* `at :` is used only if the user wants to store `OCCURING_AT` in a `MMM-DD-YYYY` format

Examples:
* `event CS2113 lecture /tomorrow 4pm`
* `event CS2113 consultation /at: 2021-10-01`

Expected outcome:
```text
event CS2113 lecture /at: tomorrow 4pm
Got it. I've added this task:
  [E][ ] CS2113 lecture (at: tomorrow 4pm)
Now you have 2 task in the list
event CS2113 consultation /at: 2021-10-01
Got it. I've added this task:
  [E][ ] CS2113 consultation (Oct 01 2021)
Now you have 3 task in the list
```

### Add a Deadline task item: `deadline`
Add a `deadline` type task into the task list.

Format: `deadline DESCPRIPTION /[by:] DUE_BY`
* `by: ` is used only if the user wants to store `DUE_BY` in a `MMM-DD-YYYY` format

Examples:
* `deadline CS2113 Assignment /tomorrow 4pm`
* `deadline CS2113 tp /by: 2021-10-01`

Expected outcome:
```text
deadline CS2113 Assignment /tomorrow 4pm
Got it. I've added this task:
  [D][ ] CS2113 Assignment (tomorrow 4pm)
Now you have 4 task in the list
deadline CS2113 tp /by: 2021-10-01
Got it. I've added this task:
  [D][ ] CS2113 tp (Oct 01 2021)
Now you have 5 task in the list
```

### Show all tasks: `list`
Show a list of all the task recorded.

Format: `list`

Expected outcome:
```text
list
Here are the tasks in your list:
1. [T][ ] CS2113 ip
2. [E][ ] CS2113 lecture (at: tomorrow 4pm)
3. [E][ ] CS2113 consultation (Oct 01 2021)
4. [D][ ] CS2113 Assignment (tomorrow 4pm)
5. [D][ ] CS2113 tp (Oct 01 2021)
```
### Find specific tasks: `find`
Show a list of all tasks that matches a specific keyword or phrase in the `DESCRIPTION` field.

Format: `find KEYWORD`
* `KEYWORD` can be either a word or, a phrase

Example:
`find Assignment`

Expected outcome:
```text
find Assignment 
Here are the tasks in your list:
1. [D][ ] CS2113 Assignment (tomorrow 4pm)
```
### Delete a task item: `delete`
Removes a task from the list

Format: `delete TASK_INDEX`
* `TASK_INDEX` is the number corresponding to the task on the list. The number must be a positive integer.

Example:
`delete 2`

Expected outcome:
```text
delete 2 
Noted. I've removed this task:
  [E][ ] CS2113 lecture (tomorrow 4pm)
Now you have 2 task in the list.
```
### Mark a task item as completed: `done`
Use for marking a task as completed.

Format: `done TASK_INDEX`
* `TASK_INDEX` is the number corresponding to the task on the list. The number must be a positive integer.
* Each task can only be marked done once. Once a task is marked `done` it cannot be undone. 

Example:
`done 1`

Expected outcome:
```text
done 1
Nice! I've marked this task as done:
  [T][X] CS2113 ip
list
Here are the tasks in your list:
1. [T][X] CS2113 ip
2. [D][ ] CS2113 Assignment (tomorrow 4pm)
```
### Exiting the program: `bye`
Use for terminating the program

Format:
`bye`
* All the data in the task list will be saved in file in the same directory as the program for future access.

Expected outcome:
```text
bye
Bye. Hope to see you again soon!
```

## Command Summary
Action | Format,Examples
------ | ---------------
Add todo | `todo DESCPRIPTION` 
Add event | `event DESCPRIPTION /[at:] OCCURING_AT` eg.`event CS2113 consultation /at: 2021-10-01`
Add deadline | `deadline DESCPRIPTION /[by:] DUE_BY` eg. `deadline CS2113 tp /by: 2021-10-01`
List | `list`
Find | `find KEYWORD` eg. `find Assignment`
Delete task | `delete TASK_INDEX` eg. `delete 2`
Done | `done TASK_INDEX` eg. `done 1`
Terminate | `bye`

