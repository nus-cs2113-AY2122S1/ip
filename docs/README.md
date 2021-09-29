# **IKAROS User Guide**

## Overview
**Ikaros is your very own desktop butler application, to managing tasks, deadlines and events,
optimized for use through a Command Line Interface (CLI).**
* [Overview](#Overview)
* [First Time Installation](#First-Time-Installation)
* [Quick Start](#Quick-Start)
* [Features](#Features)
* [Command Summary](#Command-Summary)
-------------------------------
## First Time Installation
Prerequisite - ensure you have Java 11 or above installed in your Computer

1. Download Ikaros.jar to your desired folder
2. In the same folder run *java -jar IP.jar* on the console. (Do ensure you have Java JDK 11)
3. Ikaros will create an *ikarosTaskData.txt* that will store your data for future reference when you launch the application for the first time.
-------------------------------
## Quick Start
Ensure you have completed the first time installation before proceeding with the running of this program

Upon Start up you will be greeted with a message similar to this displaying the day and the date

```
..........................................................
                         |
_______________     ___ -+- _______________
\______       /    /_  \ |  \       ______/
 \______      \_____/   \___/      ______/
   \______         IKAROS        ______/
       \____________     __________/
                  /_______\
              Welcome to IKAROS!
           Your one and only butler
           
             Monday, 27 Sept 2021
..........................................................
```

Following which, it will display the stored List  of tasks, and the user can begin to input their commands to Ikaros.
>> **Quick Notes about the CLI**
> - Data will be stored in IkarosTaskData.txt. If no such file exists, the program will display an error message. If it is your first time using the program proceed as per normal
> - Words in `<UPPERCASE_AND_ANGULAR_BRACKETS>` refers to the parameters required for the specific commands
> - Words in angular brackets `<SEPERATED_BY/A_SLASH>` refer to either our parameters where one or the othere parameter can be used.
> - The format for how each command should be keyed in will be `demarcated in this box`
-------------------------------
## Features
Below is a brief summary of the usable commands and features that can be utilised for Ikaros. 
Click on the separate links to find out more about each feature.
* Adding a Task
    * Todo task: `add todo`
    * Deadline task: `add deadline`
    * Event task: `add event`
* Removing a task: `remove`
* Marking a Task
  * Marking a task as done: `done`
  * Marking a task as undone: `undo`
* Displaying a list of tasks
    * Display list of all tasks: `list`
    * Display list of Todo: `list todo`
    * Display list of Deadlines: `list deadline`
    * Display list of Events: `list event`
* Update a Task: `update`
* Find a Task: `find`
* Help: `help`
* Exiting the application: `bye`


### Adding a Task
There are 3 different types of task that can be added (Todo, Deadline, Event). Tasks in the past cannot be added due to the lack of a time machine.
> #### `add todo` -> adds a todo task
>> * Format: `add todo <TASK_DESCRIPTION>`
>> * Example: `add todo wash clothes`
> #### `add deadline` -> adds a deadline task
>> * Format: `add deadline <TASK_DESCRIPTION> /by-<dd/MM/YYYY-HHmm>`
>> * Example: `add deadline complete assignment /by-30/09/2021-2359`
> #### `add event` -> adds a todo task
>> * Format: `add event <TASK_DESCRIPTION> /at-<dd/MM/YYYY-HHmm>`
>> * Example: `add event Dukes Birthday Celebration /at-10/12/2021-1900`

### Removing a Task
This command will remove the specific task corresponding with the input number from your tasks list. Only a single task can be removed at a time.
> #### `remove` -> removes task
>> * Format: `remove <TASK_NUMBER,TASK_NUMBER,...>`
>> * Example: `remove 3`

### Marking a Task
This command will allow you to mark a task as done or mark it as un-done. Multiple tasks can be marked at once. Take note that there is no space between the task_numbers.
> #### `done` -> mark task as done
>> * Format: `done <TASK_NUMBER,TASK_NUMBER,...>`
>> * Example: `done 3`
>> * Example: `done 3,2,1`
> #### `undo` -> mark task as undone
>> * Format: `undo <TASK_NUMBER,TASK_NUMBER,...>`
>> * Example: `undo 1`
>> * Example: `undo 3,2,1`

### Displaying a list of Tasks
This command can be used to view the different tasks in your list. Specifying which type of task you want to view will show you a list of those types of tasks arranged according to their date in ascending order.
> #### `list` -> lists all the Task in order of date and time added
>> * Format: `list`
> #### `list todo` -> lists all Todos in order of date and time added
>> * Format: `list todo`
> #### `list deadline` -> lists all the Deadline in order of date and time due (nearest date to furthest)
>> * Format: `list deadline`
> #### `list event` -> lists all Todos in order of date and time the event will be held at (nearest date to furthest)
>> * Format: `list event`

### Update a Task
This command allows you to update the description or date of a specific task base on its index in the main list. An update cannot be undone.
> #### `update` -> updates a specific task
>> * Format: `update <TASK_NUMBER> <DESCRIPTION/DATE>`
>> * Example: `update 2 date`
>> * Example: `update 5 description`

### Find a Task
This command allows you to find Tasks related to a specific keyword.
> #### `find` -> task that is related to the keyword
>> * Format: `find <KEYWORD>`
>> * Example: `find read`

### Help
This command shows you, the list and the format of the usable commands while in the application
> #### `help`
>> * Format: `help`

### Exiting the Application
This command allows you to exit and close the program
> #### 'bye'
>> * Format: `bye`
------------------------
## Command Summary

|Action|Command Format|
|---|---|
|Add Todo|`add todo <TASK_DESCRIPTION>`| 
|Add Deadline|`add deadline <TASK_DESCRIPTION> /by-<dd/MM/YYYY-HHmm>`|   
|Add Event|`add event TASK_DESCRIPTION /at-<dd/MM/YYYY-HHmm>`| 
|Remove task|`remove <TASK_NUMBER>`|
|Mark as done|`done TASK_NUMBER`|
|Mark as un-done|`undo TASK_NUMBER`|
|List|`list`|
|List Todo| `list todo`|
|List Deadline| `list deadline`|
|List Event|`list event`|
|Update Task|`update <TASK_NUMBER> <DESCRIPTION/DATE>`|
|Find keyword|`find <KEYWORD>`|
|Help|`help`|
|Exit|`bye`|
