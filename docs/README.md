# User Guide

Duke is a **task managing desktop app optimised for usage via the Command Line Interface**(CLI). It is possible for task tracking and management to be done faster on Duke as compared to using a GUI app for those who are able to type fast.

* Quick Start
* Features
  * Adding new task: 
    * Todo task type: `todo`
    * Deadline task type: `deadline`
    * Event task type: `event`
  * Deleting a task: `delete`
  * Finding tasks: `find`
  * Marking a task as done: `done`
  * Viewing all task: `list`
  * Exiting the program: `bye`
* Command Summary

## Quick Start
1. Ensure that Java 11 or above is installed in your Computer.
2. Download the latest JAR file from the latest release.
3. Copy the file to a folder where you want to run it from. 
4. Open a command window in that folder and run the command `java -jar ip.jar`.
5. Type the command and press Enter to execute it. Here are some commands you can try:
  * `todo buy bread` : adds a todo task type with a description to buy bread.
  * `done 1` : marks the first task as done.
  * `list` : view all task in the tasks list.
6. Refer to the *Features* below for details of each command.

## Features 

### Adding new task
Duke is able to handle 3 different types of task: 
1. __Todo__ : tasks without any date/time attached to it. i.e. visit new theme park
2. __Deadline__: tasks that need to be done before a specific date/time. i.e. submit report by 11-10-2019 5pm
3. __Event__: tasks that start at a specific time and ends at a specific time. i.e. team project meeting on 02-10-2019 2-4pm   
  
#### &nbsp;&nbsp;&nbsp;&nbsp;Todo task type: `todo`
&nbsp;&nbsp;&nbsp;&nbsp; Adds a new Todo task
  * Format: `todo TASK_NAME`
  * Example: `todo sell bread` 
       
    ![Todo Screenshot](https://github.com/markuslyq/ip/blob/master/images/todo%20screenshot.png?raw=true) 
    
#### &nbsp;&nbsp;&nbsp;&nbsp;Deadline task type: `deadline`
&nbsp;&nbsp;&nbsp;&nbsp; Adds a new Deadline task
  * Format: `deadline TASK_NAME /preposition DATE/TIME`
  * The `DATE` has to be in format of `dd-mm-yyyy`
  * Example: `deadline submit report /by 11-10-2019 5pm` 
       
    ![Deadline Screenshot](https://github.com/markuslyq/ip/blob/master/images/deadline%20screenshot.png?raw=true) 
    
#### &nbsp;&nbsp;&nbsp;&nbsp;Event task type: `event`
&nbsp;&nbsp;&nbsp;&nbsp; Adds a new Event task
  * Format: `event TASK_NAME /preposition DATE/TIME`
  * The `DATE` has to be in format of `dd-mm-yyyy`
  * Example: `event team project meeting /on 02-10-2019 2-4pm` 
       
    ![Event Screenshot](https://github.com/markuslyq/ip/blob/master/images/event%20screenshot.png?raw=true) 
    
### Deleting a task: `delete`
Deletes a task from the tasks list.
* Format: `delete TASK_INDEX`
* The `TASK_INDEX` refers to the integer index of a particular task shown in the displayed tasks list.
* `TASK_INDEX` __must be a positive integer__ 1, 2, 3, …
* Example: `list` followed by `delete 1` deletes the task of index '1'.

    ![Delete Screenshot](https://github.com/markuslyq/ip/blob/master/images/delete%20screenshot.png?raw=true) 

### Finding task: `find`
Finds tasks by searching for a keyword.
* Format: `find SEARCH_PARAMETERS`
* The query is case sensitive, which means that `Oct` will only search for `Oct` and not for `OCT` or `oct`.
* Example: `find Oct`  

    ![Find Screenshot](https://github.com/markuslyq/ip/blob/master/images/find%20screenshot.png?raw=true) 

### Marking a task as done: `done`
Marks a particular task in the tasks list as done.
* Format: `done TASK_INDEX`
* The `TASK_INDEX` refers to the integer index of a particular task shown in the displayed tasks list.
* `TASK_INDEX` __must be a positive integer__ 1, 2, 3, …
* Example: `list` followed by `done 2` marks the task of index '2' as done.

    ![Done Screenshot](https://github.com/markuslyq/ip/blob/master/images/done%20screenshot.png?raw=true) 

### Viewing all task: `list`
Displays a list of all tasks in the tasks list.
* Format: `list`
* Example: `list`

    ![List Screenshot](https://github.com/markuslyq/ip/blob/master/images/list%20screenshot.png?raw=true) 

### Exiting the program: `bye`
Exits the program.
* Format: `bye`
* Example: `bye`

    ![Bye Screenshot](https://github.com/markuslyq/ip/blob/master/images/bye%20screenshot.png?raw=true) 

## Command Summary 
|Action | Second Header|
|------------ | -------------|
|Add Todo task | `todo TASK_NAME` [ i.e. `todo sell bread` ]|
|Add Deadline task | `deadline TASK_NAME /preposition DATE/TIME` [ i.e. `deadline submit report /by 11-10-2019 5pm` ]|
|Add Event task | `event TASK_NAME /preposition DATE/TIME` [ i.e. `event team project meeting /on 02-10-2019 2-4pm` ]|
|Delete | `delete TASK_INDEX` [ i.e. `delete 1` ]|
|Find | `find SEARCH_PARAMETERS` [i.e. `find Oct` ]|
|Mark task as done | `done TASK_INDEX` [ i.e. `done 2` ]|
|View | `list`|
