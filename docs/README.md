# Ryan

Welcome to Ryan! A task-tracker on a CLI interface that is able to keep your tasks locally.

[Quick Start](#quick-start)
[Features](#features)
- [Viewing list of commands: help](#viewing-list-of-commands-help)
- [Input to-do task: todo](#input-to-do-task-todo)
- [Input event task: event](#input-event-task-event)
- [Input deadline task: deadline](#input-deadline-task-deadline)
- [View list of tasks: list](#viewing-list-of-tasks-list)
- [Set task as done: done](#set-task-as-done-done)
- [Delete a task: delete](#delete-a-task-delete)
- [Find tasks: find](#find-tasks-find)
- [Exit the program: bye](#exit-the-program-bye)

[Command Summary](#command-summary)



## Quick Start

1. Ensure that you have Java 11 or above installed on your computer.
2. Download the latest *ryan.jar* from the repo.
3. Copy the file to the folder that you would like to use as the home folder for your Ryan
4. Go to the command prompt and change the directory to the file's location.
5. Type java -jar ryan.jar into the command prompt and press enter to start the program.

## Features
### Viewing list of commands: `help`
Lists the commands currently available in Ryan.
### Input to-do task: `todo`
Adds a to-do task.
Format: `todo DESCRIPTION`
* Records the to-do item into the list
* DESCRIPTION can be of any alphanumerical digit or special character

Example:
```
	todo do laundry
	____________________________________________________________
	Added task:
	    [T] [ ] do laundry
	Now you have 1 task in the list.
	____________________________________________________________

```
### Input event task: `event`
Adds a todo task.
Format: `event DESCRIPTION /at EVENT_TIMING`
* Records the event into the list
* DESCRIPTION can be of any alphanumerical digit or special character except */at* or */by*
* EVENT_TIMING can be of any input as defined by your own choice of words

Example:
```
	collect parcel at post office /at 9pm
	____________________________________________________________
	Added task:
	    [E] [ ] collect parcel at post office (at: 9pm)
	Now you have 2 tasks in the list.
	____________________________________________________________

```
### Input deadline task: `deadline`
Adds a todo task.
Format: `deadline DESCRIPTION /by DEADLINE_TIMING`
* Records the deadline into the list
* DESCRIPTION can be of any alphanumerical digit or special character except */at* or */by*
* EVENT_TIMING can be of any input as defined by your own choice of words

Example:
```
	deadline finish assignment 4 /by tomorrow 10pm
	____________________________________________________________
	Added task:
	    [D] [ ] finish assignment 4 (by: tomorrow 10pm)
	Now you have 3 tasks in the list.
	____________________________________________________________

```
### Viewing list of tasks: `list`
Lists the tasks currently in Ryan.

Example:
```
	list
	____________________________________________________________
	1. [T] [ ] do laundry
	2. [E] [ ] collect parcel at post office (at: 9pm)
	3. [D] [ ] finish assignment 4 (by: tomorrow 10pm)
	Now you have 3 tasks in the list.
	____________________________________________________________
```
### Set task as done: `done`
Marks a task as done (completed)
Format: done TASK_NUM
* Marks the task with its respective task number as done.
* TASK_NO can only be an integer that is within the number of tasks in the list

Example:
```
	done 3
	____________________________________________________________
	I have marked
	     [D] [X] finish assignment 4 (by: tomorrow 10pm)
	as done!
	____________________________________________________________
	list
	____________________________________________________________
	1. [T] [ ] do laundry
	2. [E] [ ] collect parcel at post office (at: 9pm)
	3. [D] [X] finish assignment 4 (by: tomorrow 10pm)
	Now you have 3 tasks in the list.
	____________________________________________________________
	
```
### Delete a task: `delete`
Deletes the task from the list
Format: delete TASK_NUM
* Marks the task with its respective task number as done.
* TASK_NO can only be an integer that is within the number of tasks in the list

Example:
```
	delete 2
	____________________________________________________________
	Task removed : 
	     [E] [ ] collect parcel at post office (at: 9pm)
	Now you have 2 tasks in the list.
	____________________________________________________________
	list
	____________________________________________________________
	1. [T] [ ] do laundry
	2. [D] [X] finish assignment 4 (by: tomorrow 10pm)
	Now you have 2 tasks in the list.
	____________________________________________________________
	
```
### Find tasks: `find`

Finds tasks that contains the keyword in the list
Format: find KEYWORD
* Searches the list for tasks that contains the keyword given
* KEYWORD can only be a single word

Example:
```
	find assignment
	____________________________________________________________
	1. [D] [X] finish assignment 4 (by: tomorrow 10pm)
	We have found 1 task that match!
	____________________________________________________________
```

### Exit the program: `bye`
Exits the program

## Command Summary
Action | Format | Example
-------| -------|-------
bye|-|-
deadline|deadline DESCRIPTION /by DEADLINE_TIMING|deadline finish assignment 4 /by Thursday 2359
delete |delete TASK_NUM | delete 2
done | done TASK_NUM | done 2
event | event DESCRIPTION /at EVENT_TIMING|event collect laundry /at 10pm
find | find KEYWORD | find laundry
help | - | -
list | - | -
todo | todo DESCRIPTION | todo look up prices of salmon