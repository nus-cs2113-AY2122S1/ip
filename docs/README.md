# User Guide

**Duke** is a desktop application designed to manage tasks for busy students.

This application uses a Command Line Interface (CLI) for quick and easy task management activities.

## Quick Start

1. Ensure you have installed Java 11 or above on your computer.
2. Download the latest version of `duke.jar` [here](https://github.com/Journeyman1998/ip/releases/tag/v0.2).
3. Open your terminal (Mac/Linux) or your command prompt (Windows).
4. Navigate to the folder where you have placed your `duke.jar` and run `java -jar duke.jar`.
   The standard greeting message will be shown if there is no error.
  

## Features 

In the **Duke** application, there are several features.
1. Add different types of tasks
2. List all the tasks added
3. Remove tasks
4. Set a task to be completed
5. Find a task by keyword
6. Find all the tasks that are due

After every command is executed, the task data is automatically saved on the computer. The next time the application is launched, all the tasks will be loaded automatically.

  

## Usage
### Adding todo task: `todo`

Add a todo task. A todo task is a general task with a name, and no other parameters.

Format: `todo [NAME]`

Arguments:
- NAME
    - The name of the todo task can contain any characters.

Example: `todo Watch anime`
  
---

### Adding deadline task: `deadline`

Add a task with a defined deadline.

Format: `deadline [NAME] /by [DATE]`

Arguments:
- NAME
    - The name of the deadline task can contain any characters, except for the slash character `/`.
- DATE
    - The date must be in the format of `yyyy-MM-dd HH:mm`.

Example: `deadline Eat lunch /by 2021-09-21 19:00`
  
---

### Adding event task: `event`

Add a task with a date-time.

Format: `deadline [NAME] /at [DATE]`

Arguments:
- NAME
    - The name of the event task can contain any characters, except for the slash character `/`.
- DATE
    - The date must be in the format of `yyyy-MM-dd HH:mm`.

Example: `event Soccer tournament /at 2021-08-10 09:00`
  
---

### List tasks: `list`

List all the tasks, showing their types and completion status.

Format: `list`

Sample output:
```
1. [T][ ] Do tutorial
2. [D][ ] Finish project (by: 19-09-2021 18:00)
3. [E][X] CS2113 quiz (at: 21-09-2021 16:00) 
```
  
---

### Remove task: `delete` or `remove`

Remove a task of the specified index.

Format: `delete [INDEX]` or `remove [INDEX]`

Argument:
- INDEX
    - The index must be a positive integer, between 1 and the number of tasks stored.

Example: `delete 2`

Sample output:
```
Success: I've removed this task:
[D][ ] Finish project (by: 19-09-2021 18:00)
```
  
---

### Set task completed: `do` or `done`

Set a task of a specified index to be completed.

Format: `do [INDEX]` or `done [INDEX]`

Argument:
- INDEX
    - The index must be a positive integer, between 1 and the number of tasks stored.

Example: `do 1`

Sample output:
```
Success: I've marked this task as done:
[T][X] Do tutorial
```
  
---

### Find task by keyword: `find` or `match`

Find all the tasks that have names containing the specified keyword.

Format: `find [KEYWORD]` or `match [KEYWORD]`

Argument:
- KEYWORD
    - The keyword can contain any characters.

Example: `find tutorial`

Sample output:
```
Success: Here are the matching tasks in your list
1. [T][X] Do tutorial
```
  
---

### Find due tasks: `due`

Find all the tasks that are due, i.e their deadline/date-time are before the current machine date-time, and they are not completed yet.

Format: `due`

Sample output:
```
1. [D][ ] Eat lunch (by: 10-08-2021 19:00)
```
  
---

### Exit program: `bye` or `exit`

Exit the program.
  
---

## Command summary

|Action|Format|
|------|----------------|
|todo|`todo [NAME]`|
|deadline|`deadline [NAME] /by [DATE]` |
|event|`event [NAME] /at [DATE]`|
|list|`list`|
|delete/remove|`delete [INDEX]` or `remove [INDEX]`|
|do/done|`do [INDEX]` or `delete [INDEX]`|
|find/match|`find [KEYWORD]` or `match [KEYWORD]`|
|due|`due`|
|bye/exit|`bye` or `exit`|
