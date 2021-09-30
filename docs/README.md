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
`Done` | Set a Task as completed
`Delete` | Remove a Task
`List` | View Task
`Find` | Find a Task with **Keyword**
`Date` | Find a Task with a specific **Date**
`Bye` | Exit Program
`Echo` | Repeat Input
`!Echo` | Repeat Input in Grafiti ASCII Art
`!help` | View commands functions
`!list` | View list commands description
`!event` | View event commands description
`!deadline` | View deadline commands description

## Notes

## Features

### Adding a Task

#### Adding a ToDo Task

Command Format: `todo TASK_DESCRIPTION`

#### Adding a DeadLine Task

Command Format | Command
-------------- | -------
Without Date and Time | `deadline TASK_DESCRIPTION at duedate`
Without Date | `deadline TASK_DESCRIPTION at HH:MM
Without Time | `deadline TASK_DESCRIPTION at yyyy-mm-dd
With Date and Time | `deadline TASK_DESCCRIPTION at HH:MM yyyy-mm-dd

#### Adding a Event Task

Command Format | Command
-------------- | -------
Without Date and Time | `event TASK_DESCRIPTION at event timing`
Without Date | `event TASK_DESCRIPTION at HH:MM
Without Time | `event TASK_DESCRIPTION at yyyy-mm-dd
With Date and Time | `event TASK_DESCCRIPTION at HH:MM yyyy-mm-dd

### Setting a task as complete

Command Format: `done TASK_INDEX`

### Delete a Task

Command Format: `delete TASK_INDEX`

### Delete all Task

Command Format: `clear`

### View Tasks

Command Format: `list`

#### Viewing Todo Tasks

Command Format: `list todo`

#### Viewing DeadLine Tasks

Command Format: `list deadline`

#### Viewing Event Tasks

Command Format: `list event`

### Finding Task By KeyWords

Command Format: `find KEYWORD`

### Finding Task By Date

Command Format: `date yyyy-mm-dd`

### Exit Program

Command Format: `bye`
