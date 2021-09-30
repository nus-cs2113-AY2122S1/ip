
# User Guide
_Dude_ is a **Command Line Application** that aims to provide users with a **convenient and fast** platform to **manage and track tasks**. 

With its bubbly personality and user-friendly commands, _Dude_ will surely become your best friend for task management! :smile: :ok_man: :sparkles:

- [Quick Start](#quick-start)
- [Features](#features)
  - [Add a Todo task : `todo`](#add-a-todo-task-todo)
  - [Add a Deadline task: `deadline`](#add-a-deadline-task-deadline)
  - [Add an Event task: `event`](#add-an-event-task-event)
  - [List all tasks: `list`](#list-all-tasks-list)
  - [Mark a task as done: `done`](#mark-a-task-as-done-done)
  - [Delete a task: `delete`](#delete-a-task-delete)
  - [Search for a task: `search`](#search-for-a-task-search)
  - [View list of commands: `commands`](#view-list-of-commands-commands)
  - [Exit the application: `bye`](#exit-the-application-bye)
- [Frequently Asked Questions (FAQ)](#frequently-asked-questions-faq)
- [Command Summary](#command-summary)
    
## Quick Start
1. Ensure that you have Java 11 installed on your computer. If not, [install Java from here](https://www.oracle.com/java/technologies/downloads/).
2. Download the latest release of `Dude.jar` from [here](https://github.com/tlyi/ip/releases).
3. Copy `Dude.jar` into an empty folder of your choice.
4. Open a command window in that folder. [_Not sure how to do this?_](https://www.groovypost.com/howto/open-command-window-terminal-window-specific-folder-windows-mac-linux/)
5. In the same window, run the command `java -jar Dude.jar`. 

All ready to go! Enjoy using _Dude_! 😃

## Features
#### Notes about the command format:
- `command` words are **case-sensitive** and are to be supplied in **lower case** only
- Words in `UPPER_CASE` are details to be provided by the user
- :exclamation: Date and time needs to be specified in `dd/MM/yyyy HHmm` format. E.g: `12/12/2021 2359` 


### Add a Todo task: `todo`
Adds a Todo task to the list. Todo tasks are general tasks that do not have any date and time attached to it.

> Format: `todo TASK_DESCRIPTION`

Example: `todo water the plants`
````
todo water the plants
_________________________________________________________________________________
Okie! Added to list:
[T][ ] water the plants
Current number of tasks: 1
_________________________________________________________________________________
````

### Add a Deadline task: `deadline`
Adds a Deadline task to the list. Deadline tasks can be used to keep track of tasks that need to be completed by a certain date and time.

> Format: `deadline TASK_DESCRIPTION /by DATE_AND_TIME`

Example: `deadline watch lecture 3 /by 12/10/2021 1200`
````
deadline watch lecture 3 /by 12/10/2021 1200
_________________________________________________________________________________
Okie! Added to list:
[D][ ] watch lecture 3 (by: Oct 12 2021 12.00PM)
Current number of tasks: 2
_________________________________________________________________________________
````

### Add an Event task: `event`
Adds an Event task to the list. Event tasks can be used to keep track of events that occur on a certain date and time.

> Format: `event TASK_DESCRIPTION /at DATE_AND_TIME`

Example: `event lunch with Jamie /at 11/11/2021 1300`
````
event lunch with Jamie /at 11/11/2021 1300
_________________________________________________________________________________
Okie! Added to list:
[E][ ] lunch with Jamie (at: Nov 11 2021 1.00PM)
Current number of tasks: 3
_________________________________________________________________________________
````

### List all tasks: `list`
Lists all the tasks you currently have.
>Format: `list`

Example: `list`
````
list
_________________________________________________________________________________
These are your current tasks:
1.[T][ ] water the plants
2.[D][ ] watch lecture 3 (by: Oct 12 2021 12.00PM)
3.[E][ ] lunch with Jamie (at: Nov 11 2021 1.00PM)
_________________________________________________________________________________
````

### Mark a task as done: `done`
Marks the task at the given task number as done.

> Format: `done TASK_NUMBER`

Example: `done 2`
````
done 2
_________________________________________________________________________________
Well done! I've marked this task as done. ^_^
[D][X] watch lecture 3 (by: Oct 12 2021 12.00PM)
_________________________________________________________________________________
````

### Delete a task: `delete`
Deletes the task at the given task number.

> Format: `delete TASK_NUMBER`

Example: `delete 1`
````
delete 1
_________________________________________________________________________________
Alrightys! I have removed the following task:
[T][ ] water the plants
Current number of tasks: 2
_________________________________________________________________________________
````

### Search for a task: `search`
Shows a list of tasks with descriptions that match a given search term, along with their respective task numbers.

:bulb: The search term can consist of a single word, or multiple words, and is **case-insensitive**.

> Format: `search SEARCH_TERM`

Example: `search lunch`
````
search lunch
_________________________________________________________________________________
Here are the matching tasks in your list:
2.[E][ ] lunch with Jamie (at: Nov 11 2021 1.00PM)
_________________________________________________________________________________
````

### View list of commands: `commands`
Shows the list of commands that Dude accepts and their respective formats.

> Format: `list`

### Exit the application: `bye`
Exits the application. Bye _Dude_! :frowning_face:


## Frequently Asked Questions (FAQ)
_**Q**: How do I save my tasks?_

**A**: No need to worry about that!  All tasks are automatically saved onto your computer everytime the list is modified. 
_Dude_ will then reload your data the next time you start it.

_**Q**: Can I edit my tasks directly without starting the application?_

**A**: Sure! The text file containing the data can be found at `/data/dude.txt` and can be directly edited using a text file editor. 
Each new task should be written on a new line. Note that you will need to strictly follow the format of previously stored data, 
else _Dude_ will be unable to load that line of data into your task list.



## Command Summary

Action | Format | Example
------ | ------ | -------
Add Todo task |  `todo TASK_DESCRIPTION` | `todo water the plants`
Add Deadline task | `deadline TASK_DESCRIPTION /by DATE_AND_TIME` | `deadline watch lecture 3 /by 12/10/2021 1200`
Add Event task | `event TASK_DESCRIPTION /at DATE_AND_TIME` |  `event lunch with Jamie /at 11/11/2021 1300`
List all tasks | `list` | `list`
Mark a task as done | `done TASK_NUMBER` | `done 2`
Delete a task | `delete TASK_NUMBER` | `delete 1`
Search for a task | `search SEARCH_TERM` | `search lunch with`
View list of commands | `command` | `command`
Exit | `bye` | `bye`

