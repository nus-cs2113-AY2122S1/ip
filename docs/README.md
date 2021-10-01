# User Guide


A personal assistant bot that can help you record your tasks

* [Quick Start](#Quick-Start)
* [Features](#Features)
* [Command summary](#Command-summary)
* [Notes](#Notes)

## Quick Start

1. Download [Java 11](https://www.oracle.com/java/technologies/downloads/#java11) on your computer.
2. Download the latest Duke release file (`ip.jar`) from [here](https://github.com/jushg/ip/releases).
3. Move the file into the folder to be used as the root folder for Duke
4. On the terminal run the command `java -jar ip.jar`
5. Enjoy your very own personal assistant

## Features 

### Add a task 
Add in a task with a general description and a date-time description (only for `Deadline` or `Event` tasks)

Format:
1. Normal task: `todo [description]`
2. Deadline task: `deadline [description] /by [yyyy-mm-dd HH:mm]`
3. Event task: `event[description] /at [yyyy-mm-dd HH:mm]`

Example of usage:

```
todo study for CS2113
deadline ip /by 2021-10-01 23:59
```

Expected output:

```
 ──────────────────────────────────────────────────────────
 Got it. I've added this task:
   [T][ ] study for CS2113
 Now you have 1 tasks in the list.
 ──────────────────────────────────────────────────────────
```
### Find a task

Find tasks with some keywords

Format: `find [keywords]`

Example of usage:

```
find study
```

Expected output:

```
 ──────────────────────────────────────────────────────────
 Here is the list of task with matching keywords:
   [T][ ] study for CS2113
 ──────────────────────────────────────────────────────────
```
### List all current task
List all current tasks in the list

Format: `list`

Example of usage:

```
list
```

Expected output:

```
 ──────────────────────────────────────────────────────────
 Here is your list at the moment:
   [T][ ] study for CS2113
   [D][X] complete the ip (by:1800 1-10-2021)
 ──────────────────────────────────────────────────────────
```
### Delete a task
Delete a task by specifying the task number

Format: `delete [task number]`

Example of usage:

```
delete 1
```

Expected output:

```
 ──────────────────────────────────────────────────────────
 Noted. I've removed this task:
   [T][ ] study for CS2113
 Now you have 1 tasks in the list
 ──────────────────────────────────────────────────────────
```

### Mark a task done
Mark a task as done by specifying the task number

Format: `done [task number]`

Example of usage:

```
done 1
```

Expected output:

```
──────────────────────────────────────────────────────────
 I have marked "study for CS2113" as done
──────────────────────────────────────────────────────────
```
## Command summary

1. `todo/deadline/event` - add a task
2. `done` - mark a task as done
3. `find` - find tasks with keywords
4. `list` - list all current tasks
5. `delete` - delete a task
6. `bye` - exit the application

## Notes

1. The tasks are saved and will be reloaded everytime the program is run
2. To copy the saved data to another location, find the `data` folder and copy it into the same folder as the `jar` file