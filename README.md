# Individual Project (iP) User Guide

This iP, also known as BATMAN, is a desktop application that helps users to keep track of 3 main types of tasks via a 
Command Line Interface (CLI). The 3 types of tasks are:
- ToDos
- Deadlines
- Events

##Table of Contents

- Setting Up
- Features
- Command Summary

## Setting Up

1. Ensure you have Java `11` installed in your computer.
2. Download the latest `ip.jar` from [here](https://github.com/AnShengLee/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your AddressBook.
4. Run the application by opening a command line in that folder and entering the following command: `java -jar ip.jar`.
5. Type the available commands in the command line and press Enter to execute it.
6. Refer to Features below for details of each command.

##Features
###Note
- Words in `UPPER_CASE` are parameters to be given by the user.

###Adding a ToDo Task
Adds a ToDo task for BATMAN to keep track. If an empty or blank task description is given, the ToDo task will not be 
added and a warning will be given.  

Format: `todo TASK_DESCRIPTION`

Example: `todo read book`

###Adding a Deadline Task
Adds a Deadline task for BATMAN to keep track. If any parameters given is empty or blank, the Deadline task will not be
added and a warning will be given.

Format: `deadline TASK_DESCRIPTION /by DEADLINE`

Example: `deadline read book /by 21 feb`

###Adding an Event Task
Adds an Event task for BATMAN to keep track. If any parameters given is empty or blank, the Event task will not be
added and a warning will be given.

Format: `Event TASK_DESCRIPTION /at DEADLINE`

Example: `Event read book /at 21 feb bishan library`

###Listing All Tasks
Shows a list of all tasks BATMAN is tracking. All the tasks shown will be indexed starting from 1.

Format: `list`

How the list will look like:

![list example](ip_list_example.png)
Each task has their index, done status and the task itself. A task that is set as done will have an `X` just like 
task 2.

### Setting a Task as Done
Sets a task that BATMAN is tracking as done. If the index of task given is out of range, nothing will happen and a 
warning will be given.

Format: `done INDEX_OF_TASK`

Example: `done 2`

###Deleting a Task
Deletes a task. BATMAN will then stop tracking that particular task. If the index of task given is out of range, nothing 
will happen and a warning will be given.

Format: `delete INDEX_OF_TASK`

Example: `delete 3`

###Finding a Task
Finds task with description that contains keyword given by users. If there are multiple tasks that match the given 
keyword, they will all be shown.

Format: `find KEYWORD`

Example: `find ball`




