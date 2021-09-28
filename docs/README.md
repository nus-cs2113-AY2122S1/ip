# Duke User Guide

----
Duke is a **Command Line Interface Application(CLI) which manages tasks, deadlines and events**. If you can type fast, Duke is the perfect way to manage your tasks.

* [QuickStart](#quick-start)
* [Features](#Features)
  * [`todo` - Adding a Todo task](#todo---adding-a-todo-task)
  * [`deadline` - Adding a deadline task](#deadline---adding-a-deadline-task)
  * [`event` - Adding a event task](#event---adding-a-event-task)
  * [`list` - Listing all tasks](#list---listing-all-tasks)
  * [`done` - Marking a task as done](#done---marking-a-task-as-done)
  * [`delete` - Deleting a task](#delete---deleting-a-task)
  * [`find` - Search for tasks](#find---search-for-tasks)
  * [`bye` - Exiting the program](#bye---exiting-the-program)
  * [Saving data to file](#saving-data-to-file)
*[Command Summary](#command-summary)
  
##Quick Start 

---
1. Ensure that you have Java ```11``` installed in your computer.
2. Download the lastest ```duke.jar``` from [here](https://github.com/deonchung/ip/releases).
3. Copy the file to the folder you want to use for your task list.
4. Open command prompt and navigate to the folder where ```duke.jar``` is located.
5. Run the application in the terminal using command ```java -jar duke.jar```.

```
 ____        _
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

_____________________________________________
Hello! I'm Duke
What can I do for you?
_____________________________________________
```
## Features <a name="paragraph1"></a>

---

### ```todo``` - Adding a Todo task
Creates a Todo in the task list. Todo tasks only contains task description of todo

Format: ```todo <Task Description>```

Example of usage:

```todo read book```

Expected Outcome:
A todo task named read book is created.
```
_____________________________________________
Got it!! I've added this task:
[T][ ] read book 
Now you have 1 tasks in the list.
_____________________________________________
```

### `deadline` - Adding a deadline task

Creates a task with deadline in the task list. Deadline tasks contains task description and date which shows the deadline of the task.

Format: `deadline <Task Description> /by <Date>`

Example of usage:
`deadline CS2113T project /by <27/09/2021>`

Expected outcome:

A deadline task named CS2113T project is created with deadline by 27/09/21.

```
_____________________________________________
Got it!! I've added this task:
[D][ ] CS2113T project (by: 27/09/21) 
Now you have 1 tasks in the list.
_____________________________________________
```

### `event` - Adding a event task

Creates a event task in the task list. Event tasks contains task description and date and time of the task.

Format: `event <Task Description> /at <DateTime>`

Example of usage:
`event project meeting /at <27/09/2021 2359>`

Expected outcome:

A event task named project meeting is created with date and time as 27/09/2021 1100.

```
_____________________________________________
Got it!! I've added this task:
[E][ ] project meeting (at: 27/09/21 1100) 
Now you have 1 tasks in the list.
_____________________________________________
```
### `list` - Listing all tasks

Show a list with all the tasks in the task list.

Format: `list`

Example of usage:
`list`

Expected outcome:

All tasks in task list will be listed

```
_____________________________________________
Here are the taks in your list:
1.[T][ ] read book 
2.[D][ ] CS2113T project (by: 27/09/21) 
3.[E][ ] project meeting (at: 27/09/21 1100) 
_____________________________________________
```

### `done` - Marking a task as done

Mark a task as completed. 

Format: `done <Task ID>`

Example of usage:
`done 1`

Expected outcome:

The task with task id 1 is marked as completed.

```
_____________________________________________
Nice! I've marked this task as done:
[E][X] project meeting (at: 27/09/21 1100) 
_____________________________________________
```

### `delete` - Deleting a task

Deletes a task from the task list.

Format: `delete <Task ID>`

Example of usage:
`delete 1`

Expected outcome:

The task with task id 1 is deleted.

```
_____________________________________________
Noted! I've removed this task:
[D][ ] CS2113T project (by: 27/09/21) 
Now you have 0 tasks in the list.
_____________________________________________
```
### `find` - Search for tasks

Finds all tasks with the search term.

Format: `find <Search term>`

Example of usage:
`find book`

Expected outcome:

The task with task description return book will be shown.

```
_____________________________________________
Here are the matching tasks in your list:
1.[T][ ] return book 
_____________________________________________
```

### `bye` - Exiting the program

Terminates the program.

Format: `bye`

Example of usage:
`bye`

Expected outcome:

Program terminates.

```
_____________________________________________
Bye. Hope to see you again soon!
_____________________________________________
```
### Saving data to file
The tasks in Duke will be saved automatically to `[Jar file location]/data/duke.txt`.
Advanced users are welcome to update data directly by editing the data file.

`**⚠Caution**: If the format is invalid or the save file is corrupted, Duke will discard all data and start with an empty file`

## Command Summary

----

|        Action       |                 Command                  |                  Example                  |
|:-------------------:|:----------------------------------------:|:-----------------------------------------:|
| Adding todo         | `todo <TaskDescription>`                 | `todo return book`                        |
| Adding deadline     | `deadline <TaskDescription> /by <Date>`  | `deadline CS2113T project /by 27/09/21`   |
| Adding event        | `event <TaskDescription> /at <DateTime>` | `event project meeting /at 27/09/21 1100` |
| List all task       | `list`                                   | `list`                                    |
| Mark a task as done | `done <Task ID>`                         | `done 1`                                  |
| Deleting a task     | `delete <Task ID>`                       | `delete 1`                                |
| Search for tasks    | `find <Search term>`                     | `find book`                               |
| Exit program        | `bye`                                    | `bye`                                     |

