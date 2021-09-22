# Kitty User Guide
## Overview
Kitty is a **desktop application for managing your tasks, optimized for use via a Command Line Interface (CLI).**
If you are facing any difficulties with managing your busy schedule, look no further, **Kitty is your one-stop solution
to sort your life out.**

* Quick Start
* Features
    * Adding a Todo task: `todo`
    * Adding a Deadline task: `deadline`
    * Adding an Event task: `event`
    * Listing out all tasks: `list`
    * Finding tasks matching specific keyword: `find`
    * Marking a task as done: `done`
    * Deleting a task: `delete`
    * Exiting the programme: `bye`
* Command Summary

------------------------
## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `Kitty.jar`.
3. Copy the file to the folder you want to use as the home folder for Kitty.
4. Open your preferred CLI and navigate to the directory where `Kitty.jar` is in.
5. Type `java -jar Kitty.jar` to start the app. You should see the app run in a similar fashion as below.
   ![Startup Image](https://github.com/kengjit/ip/blob/master/data/intro.PNG?raw=true)
6. Type a command in the command line and press Enter to execute it. (e.g. Typing `list` and pressing Enter will show all tasks at hand.)

Some example commands you can try:

`list` : Lists all tasks at hand.

`todo Complete Assignment`: Adds a Todo task *Complete Assignment*.

`deadline Submit Assignment /by 31/12/2021` : Adds a Deadline *Submit Assignment* with deadline 31 December 2021.

`done 2` : Mark 2nd task as done.

`bye` : Exits the app.

Refer to the Features below for details of each command.

------------------------
## Features

|Words in UPPER_CASE are the parameters to be supplied by the user. (e.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo Task`.)| 
|---------------------------------------------------------------------------------------------------------------------------------------------------|

### Adding a Todo task: `todo`
Adds a Todo task to total tasks.

Format: `todo TASK_DESCRIPTION`

Example: `todo Send cousin off at airport`

### Adding a Deadline task: `deadline`
Adds a Deadline task to total tasks.

Format: `deadline TASK_DESCRIPTION /by DEADLINE_DATE`

Example: `deadline buy gift for Mother's Day /by 8/5/2022
`
### Adding an Event task: `event`
Adds an Event task to total tasks.

Format: `event TASK_DESCRIPTION /at EVENT_DATE`

Example: `event Christmas Celebration /at 25/12/2021`

### Listing out all tasks: `list`
Displays all tasks.

Format: `list`

Example:
![List Image](https://github.com/kengjit/ip/blob/master/data/list.PNG?raw=true)

### Finding tasks matching specific keyword: `find`
Lists out all tasks that matches the keyword provided.

Format: `find KEYWORD`
* The search is case-sensitive. (e.g. book will **NOT** match Book)

Example: `find submit`
![Find Image](https://github.com/kengjit/ip/blob/master/data/find.PNG?raw=true)

### Marking a task as done: `done`
Mark specified task as done.

Format: `done TASK_NUMBER`
* The task number refers to the index number shown in the list of tasks.
  The task number must be a positive integer 1, 2, 3, …

Example: `done 2`
![Done Image](https://github.com/kengjit/ip/blob/master/data/done.PNG?raw=true)

### Deleting a task: `delete`
Deletes a task from total tasks.

Format: `delete TASK_NUMBER`
* The task number refers to the index number shown in the list of tasks.
  The task number must be a positive integer 1, 2, 3, …

Example: `delete 1`
![Delete Image](https://github.com/kengjit/ip/blob/master/data/delete.PNG?raw=true)

### Exiting the programme: `bye`
Exits the programme.

Format: `bye`

------------------------
## Command Summary

|Action|Format, Examples|
|---|---|
|Add Todo|`todo TASK_DESCRIPTION`| 
|Add Deadline|`deadline TASK_DESCRIPTION /by DEADLINE_DATE`|   
|Add Event|`event TASK_DESCRIPTION /at EVENT_DATE`| 
|List|`list`|
|Find keyword|`find KEYWORD`|
|Mark as done|`done TASK_NUMBER`|
|Delete task|`delete TASK_NUMBER`|
|Exit|`bye`|
