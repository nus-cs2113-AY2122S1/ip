# User Guide

Bob is a **task manager** for use via a Command Line Interface.

* [Features](#features)
  * [Viewing help: `help`](#viewing-help-help)
  * [Add Todo Tasks: `todo`](#add-todo-tasks-todo)
  * [Add Deadline Tasks: `deadline`](#add-deadline-tasks-deadline)
  * [Add Event Tasks: `event`](#add-event-tasks-event)
  * [List All Tasks: `list`](#list-all-tasks-list)
  * [Find Tasks with Keyword: `find`](#find-tasks-with-keyword-find)
  * [Mark Task as Done: `done`](#mark-task-as-done-done)
  * [Delete Task: `delete`](#delete-task-delete)
  * [Exiting the Program: `exit`](#exiting-the-program-exit)
  
## Features 

*Note: All items in carrot brackets are to be filled by the user*

### Viewing help: `help`
Displays a list of commands and their usage.
* Format: `help`

### Add Todo Tasks: `todo`
Adds a todo task with task description
* Format: `todo <description>`
* Example: `todo programming assignments`
* Expected output:
``` 
_________________________________________
The following todo task has been added
T |   | programming assignments
_________________________________________
```

### Add Deadline Tasks: `deadline`
Adds a deadline task with task description and deadline
* Format: `deadline <description> /by <deadline>`
  * Deadline must be formatted as *yyyy-MM-ddThh:mm:ss*
* Example: `deadline math homework /by 2021-11-10T19:00:00`
* Expected Output:
```
_________________________________________
The following deadline task has been added
D |   | math homework | by: 10/11/2021 07:00:00
_________________________________________
```

### Add Event Tasks: `event`
Adds an event task with task description and start time
* Format: `event <description> /at <start time>`
  * Start time must be formatted as *yyyy-MM-ddThh:mm:ss*
* Example: `event do math homework /at 2021-11-09T10:00:00`
* Expected Output:
```
_________________________________________
The following event task has been added
E |   | do math homework | at: 09/11/2021 10:00:00
_________________________________________
```

### List All Tasks: `list`
Displays all tasks currently stored
* Format: `list`

### Find Tasks with Keyword: `find`
Displays all tasks currently stored with description containing the keyword
* Format: `find <keyword>`
* Example: `find homework`
* Expected Output:
```
_________________________________________
The following task have been found:
2. D |   | math homework | by: 10/11/2021 07:00:00
3. E |   | do math homework | at: 09/11/2021 10:00:00
_________________________________________
```

### Mark Task as Done: `done`
Marks the task with the specified index as complete.
* Format: `done <task index>`
* Example: `done 1`
* Expected Output
```
_________________________________________
The following task has been marked as:
T | X | programming assignments
_________________________________________
```

### Delete Task: `delete`
Deletes the task of the specified index
* Format: `delete <task index>`
* Example: `delete 1`
* Expected Output:
```
_________________________________________
The following task has been deleted:
T | X | programming assignments
_________________________________________
```

### Exiting the Program: `exit`
Exits the program
* Format: `exit`






