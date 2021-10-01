# User Guide
Pepepopo is a desktop app for **managing tasks**, optimized for use via a Command Line Interface (CLI).
* [Quick Start](#quick-start)
* [Features](#features)
  * [Adding a task](#adding-a-task)
    * [Todo: `todo`](#todo-todo)
    * [Deadline: `deadline`](#deadline-deadline)
    * [Event: `event`](#event-event)
  * [Listing all tasks: `list`](#listing-all-tasks-list)
  * [Set task as done: `done`](#set-task-as-done-done)
  * [Deleting a task: `delete`](#deleting-a-task-delete)
  * [Finding task by name: `find`](#finding-task-by-name-find)
  * [Listing deadlines within given date: `date`](#listing-deadlines-within-given-date-date)
  * [Exiting the program: `bye`](#exiting-the-program-bye)
  * [Saving the data](#saving-the-data)
  * [Editing the data file](#editing-the-data-file)
* [Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest pepepopo.jar from [here](https://github.com/swongts/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for Pepepopo.
4. Go to your command prompt and change to the directory of the home folder
5. Run pepepopo by typing `java -jar ip.jar` and press enter
6. Refer to the [Features](#features) below for the details of each command.

## Features

### Adding a task
Adds a task to your tasklist.

###Types of task:

#### Todo: `todo`
Adds a todo task.

Format: `todo DESCRIPTION`
* DESCRIPTION cannot be left blank

Example:
* `todo read book`

####Deadline: `deadline`
Adds a task with a due date.

Format: `deadline DESCRIPTION /byDATETIME`
* DESCRIPTION cannot be left blank
* Format of parameter input DATETIME should be `D-M-YYYY HHmm`

Example:
* `deadline read book /by 8-8-2020 1230`

####Event: `event`
Adds a task that happens on a specific date and time.

Format: `event DESCRIPTION /atDATETIME`
* DESCRIPTION cannot be left blank
* Format of parameter input DATETIME should be `D-M-YYYY HHmm`

Example:
* `event read book /at 8-8-2020 1230`

###Listing all tasks: `list`
Lists all tasks within tasklist.

Format: `list`

### Set task as done: `done`
Marks task in tasklist as done.

Format: `done TASK_NUM`
* Marks task at specified TASK_NUM as done
* TASK_NUM is same numbering as seen from `list` command
* TASK_NUM must be an integer within the number of tasks in the list

Example: 
* `done 1` marks first task in the list as done

###Deleting a task: `delete`
Deletes specified task from the task list.

Format: `delete TASK_NUM`
* Deletes task at specified TASK_NUM
* TASK_NUM must be an integer within the number of tasks in the list

Example:
* `delete 1` deletes first task in the list

###Finding task by name: `find`
Finds task with description matching any of the given keywords.

Format: `find KEYWORD`

Example: 
* `find book` will return all tasks with book in the description

###Listing deadlines within given date: `date`
Lists all deadlines that are before or on the given date and time

Format: `date DATETIME`
* Format of parameter input DATETIME should be `D-M-YYYY HHmm`

Example:
* `date 8-8-2020` returns all deadlines that ends before 8th August 2020 at 12.30

###Exiting the program: `bye`
Exits the program.

###Saving the data
Pepepopo data will automatically be saved in a file with filepath `/data/tasks.txt`.

###Editing the data file
Advanced users are welcome to update data directly by editing the data file.
* However, if the changes to the data file do not follow the formatting of how the tasks are saved, pepepopo will not be able to run.
Users may have to delete the file and start again.

##Command Summary
Command | Format
------------ | -------------
todo | `todo DESCRIPTION`
deadline | `deadline DESCRIPTION /byDATETIME`
event | `event DESCRIPTION /atDATETIME`
list | `list`
done | `done TASK_NUM`
delete | `delete TASK_NUM`
find | `find KEYWORD`
date | `date DATETIME`
bye| `bye`
