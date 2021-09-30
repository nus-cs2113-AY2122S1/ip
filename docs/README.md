# Duke User Guide
Duke is a Command Line Interface program that let users keep track of their tasks.

## Quick Start
1. Ensure Java `11` is installed.
2. Download [IP.jar](https://github.com/kum-wh/ip/releases).
3. Navigate to file path in terminal.
4. To launch **Duke**, enter `java -jar IP.jar`.
## Command List

Command | Use
------- | ---
`ToDo` | Add **ToDo** Task
`Deadline` | Add **Deadline** Task
`Event` | Add **Event** Task
`Done` | Set a Task as **completed**
`Delete` | Remove a Task
`List` | View Task
`Find` | Find a Task with **Keyword**
`Date` | Find a Task with a specific **Date**
`Bye` | Exit Program
`Echo` | Repeat Input
`!Echo` | Repeat Input in Grafiti ASCII Art
`!help` | View commands functions
`!list` | View **list** commands description
`!event` | View **event** commands description
`!deadline` | View **deadline** commands description

## Notes

TASK_DESCRIPTION TASK_DEADLINE TASK_TIMING TASK_INDEX KEYWORD HH:MM yyyy-mm-dd

## Features

### Adding a Task

#### Adding a ToDo Task

Add a task without any specific dates to list.

Command Format: `todo TASK_DESCRIPTION`

Demo:

```
todo
```

#### Adding a DeadLine Task

Add a task with a deadline to list.

Command Format | Command
-------------- | -------
Without Date and Time | `deadline TASK_DESCRIPTION at TASK_DEADLINE`
Without Date | `deadline TASK_DESCRIPTION at HH:MM`
Without Time | `deadline TASK_DESCRIPTION at yyyy-mm-dd`
With Date and Time | `deadline TASK_DESCCRIPTION at HH:MM yyyy-mm-dd`

Demo:

```
deadline
```

#### Adding a Event Task

Add a task occuring at a specific date and time to list.

Command Format | Command
-------------- | -------
Without Date and Time | `event TASK_DESCRIPTION at TASK_TIMING`
Without Date | `event TASK_DESCRIPTION at HH:MM`
Without Time | `event TASK_DESCRIPTION at yyyy-mm-dd`
With Date and Time | `event TASK_DESCCRIPTION at HH:MM yyyy-mm-dd`

Demo:

```
event
```

### Setting a task as complete

Command Format: `done TASK_INDEX`

Demo:

```
done
```

### Delete a Task

Delete a task from list

Command Format: `delete TASK_INDEX`

Demo:

```
delete
```

### Delete all Task

Delete all tasks from list.

Command Format: `clear`

Demo:

```
clear

____________________________________________________________
List Cleared. All tasks removed.
____________________________________________________________
```

### View Tasks

List all task in list.

Command Format: `list`

Demo:

```
list
```

#### Viewing Todo Tasks

List all ToDo task in list

Command Format: `list todo`

Demo:

```
list todo
```

#### Viewing DeadLine Tasks

List all Deadline task in list.

Command Format: `list deadline`

Demo:

```
list deadline
```

#### Viewing Event Tasks

List all Event task in list.

Command Format: `list event`

Demo:

```
list event
```

### Finding Task By KeyWords

List all tasks with a specific keyword in its description in list.

Command Format: `find KEYWORD`

Demo:

```
find
```

### Finding Task By Date

List all tasks with a specific date in list.

Command Format: `date yyyy-mm-dd`

Demo:

```
date
```

### Echo User Input

Print user input on terminal

Command Format: `echo INPUT`

Demo:

```
echo
```

### Command Guide

Command Format: `!help`

Demo:

```
!help

____________________________________________________________
List of Commands:
   echo - Repeat whatever was typed - !echo to repeat in art form
   list - Display List - !list for details
   todo - Add ToDo Task
   event - Add Event Task - !event for details
   deadline - Add Deadline Task - !deadline for details
   clear - Clear list
   find - Find Task With a specific word
   date - Find Task with a specific date
   bye - Shut Down
____________________________________________________________
```

### Exit Program

Exits program.

Command Format: `bye`

Demo:

```
bye

____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
_______________.___.___________
\______   \__  |   |\_   _____/
 |    |  _//   |   | |    __)_
 |    |   \\____   | |        \
 |________// ______|/_________/
```
