# Individual Project (iP) User Guide

This iP, also known as DUKE, is a desktop application that helps users to keep track of 3 types of tasks via a 
Command Line Interface (CLI). The 3 types of tasks are:
- ToDos
- Deadlines
- Events

##Table of Contents

- [Setting Up](#setting-up)
- [Features](#features)
  - [Adding a ToDo Task: `todo`](#adding-a-todo-task-todo)
  - [Adding a Deadline Task: `deadline`](#adding-a-deadline-task-deadline)
  - [Adding an Event Task: `event`](#adding-an-event-task-event)
  - [Listing All Tasks: `list`](#listing-all-tasks-list)
  - [Setting a Task as Done: `done`](#setting-a-task-as-done-done)
  - [Deleting a Task: `delete`](#deleting-a-task-delete)
  - [Finding a Task: `find`](#finding-a-task-find)
  - [Ending the Program: `bye`](#ending-the-program-bye)
- [Command Summary](#command-summary)

## Setting Up

1. Ensure you have Java `11` installed in your computer.
2. Download the latest `ip.jar` from [here](https://github.com/AnShengLee/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your DUKE.
4. Run the application by opening a command line in that folder and entering the following command: `java -jar ip.jar`.
5. Type the available commands in the command line and press Enter to execute it.
6. Refer to [Features](#features) below for details of each command.

##Features
###Note
- Words in `UPPER_CASE` are parameters to be given by the user.

###Adding a ToDo Task: `todo`
Adds a ToDo task for DUKE to keep track. 

Format: `todo TASK_DESCRIPTION`

- If an empty or blank task description is given, the ToDo task will not be
added and a warning will be given.

Example: `todo read book`

###Adding a Deadline Task: `deadline`
Adds a Deadline task for DUKE to keep track. 

Format: `deadline TASK_DESCRIPTION /by DEADLINE`

- If any parameters given is empty or blank, the Deadline task will not be
  added and a warning will be given.

Example: `deadline read book /by 21 feb`

###Adding an Event Task: `event`
Adds an Event task for DUKE to keep track. 

Format: `event TASK_DESCRIPTION /at EVENT_DETAILS`

- If any parameters given is empty or blank, the Event task will not be
added and a warning will be given.

Example: `event read book /at 21 feb bishan library`

###Listing All Tasks: `list`
Shows a list of all tasks DUKE is currently tracking. 

Format: `list`

- All the tasks shown will be indexed starting from 1.
- If there are no tasks, DUKE will let the user know.

How the list will look like:

![list example](ip_list_example.png)

Each task has their index, done status and the task itself. A task that is set as done will have an `X` just like 
task 2.

###Setting a Task as Done: `done`
Sets a task that DUKE is tracking as done. 

Format: `done INDEX_OF_TASK`

- Sets task at specified `INDEX` as done.
- The index must be a **positive integer** 1, 2, 3, …
- If the index of task given is out of range, nothing will happen and a warning will be given.

Example: `done 2`

###Deleting a Task: `delete`
Deletes a task. DUKE will then stop tracking that particular task. 

Format: `delete INDEX_OF_TASK`

- Deletes the task at specified `INDEX`.
- The index refers to the index number shown in the displayed tasks list.
- The index must be a **positive integer** 1, 2, 3, …
- If the index of task given is out of range, nothing will happen and a warning will be given.

Example: `delete 3`

###Finding a Task: `find`
Finds tasks with `TASK_DESCRIPTION` that contain a keyword given by user.

Format: `find KEYWORD`

- The search is case-insensitive. e.g, `ball` will match `Ball`.
- Only accepts 1 keyword else a warning will be thrown.
- Only 'TASK_DESCRIPTION' is searched.
- Non-full words can be matched e.g., `ball` will match `football`.
- DUKE will notify user if there are no related tasks.

Example: `find ball` returns `pump ball`, `football`, `basketball` and `Ball race`

![find example](find_example.png)

###Ending the Program: `bye`
Ends the program.

Format: `bye`

###Saving Tasks to Hard Drive (Automatic)
When the program starts, it looks for a data file called `DukeData.txt` and loads all the data there into the program. 
If there is no `DukeData.txt` file, one will be created in the same folder as `ip.jar`.

After every command input by user, all the tasks in the program will be saved into `DukeData.txt` to ensure data 
integrity when DUKE crashes. If there are errors, user will be notified. No notification will be shown if there 
are no errors.

Right before the program ends, another final save will be done. User will be notified if it is 
a success or a failure.

###Change of Date Format (Automatic)
Dates given in the form of `yyyy-mm-dd` (e.g., 2121-11-12) will be printed in the form of `MMM dd yyyy`
(e.g., Nov 12 2121). This is done to increase readability for the user.

This feature is only available for dates keyed in as:
- `DESCRIPTION` for Deadline tasks
- `EVENT_DETAILS` for Event tasks

Here is how it will look like:

![date example](date_example.png)

##Command Summary
Action | Format, Examples
------------ | -------------
Add ToDo | `todo TASK_DESCRIPTION` <br /> e.g., `todo read book`
Add Deadline | `deadline TASK_DESCRIPTION /by DEADLINE` <br /> e.g., `deadline read book /by 21 feb`
Add Event | `event TASK_DESCRIPTION /at EVENT_DETAILS` <br /> e.g., `event read book /at 21 feb bishan library`
List | `list`
Set Done | `done INDEX_OF_TASK` <br /> e.g., `done 2`
Delete | `delete INDEX_OF_TASK` <br /> e.g., `delete 2`
Find | `find KEYWORD` <br /> e.g., `find ball`
End | `bye`
