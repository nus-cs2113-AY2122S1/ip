# Duke 

**Duke** is a desktop app for managing **todo tasks, deadlines, and events**, optimised for use via a Command Line Interface (CLI).

## Quick Start

Prerequisites: JDK 11.

1. Ensure you have Java 11 installed on your computer. 
1. Download the latest `Duke.jar` from [here]().
1. Copy the file to the folder you want to use as the *home folder* for Duke.
1. In the terminal, navigate to Duke's *home folder* and run the following command:
   ```
   java -jar Duke.jar
   ```
   After the command is run, the program starts and you should see this as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
	   __________________________________________________
	   Hello! I'm Duke, your friendly agenda chatbot!
	   Is there anything I can do for you today?
	   __________________________________________________
   ```
1. Type the command in the command line and press Enter to execute it.<br>
   Some example commands you could try:
   * `list`: Lists all tasks.
   * `todo Task`: Adds a task named `Task` to the task list.
   * `delete 2`: Deletes the 2nd task shown in the current list.
   * `done 4`: Marks the 4th task shown in the current list as done.
   * `bye`: Exits the app.
1. Refer to the Features below for details of each command.

## Features

### Adding a todo task: `todo`
Adds a todo task to the task list. <br>
Format: `todo TASKNAME` <br>
Example: <br>
   ```
   todo Read Book
	   __________________________________________________
	   Added todo: Read Book
	   __________________________________________________
   
   ```

### Adding a deadline task: `deadline`
Adds a deadline task to the task list. <br>
Format: `deadline TASKNAME /by YYYY/MM/DD HHMM` <br>
Example: <br>
   ```
   deadline Return Book /by 2021/10/01 1200
	   __________________________________________________
	   Added deadline: Return Book (by: 2021/10/01 1200)
	   __________________________________________________
   
   ```

### Adding an event task: `event`
Adds an event task to the task list. <br>
Format: `event TASKNAME /at YYYY/MM/DD HHMM` <br>
Example: <br>
   ```
   event Book Club /at 2021/09/25 1630
	   __________________________________________________
	   Added event: Book Club (at: 2021/09/25 1630)
	   __________________________________________________
   ```
   
### Listing all tasks: `list`
Shows a list of all tasks in the task list. <br>
Format: `list` <br>
Example: <br>
   ```
   list
	   __________________________________________________
	   Here are the tasks in your list:
	   1. [T][ ] Read Book
	   2. [D][ ] Return Book (by: 01 October 2021 Fri 00:00 pm)
	   3. [E][ ] Book Club (at: 25 September 2021 Sat 04:30 pm)
	   __________________________________________________
   ```

### Marking a task as done: `done`
Marks the `INDEX`th task as done. <br>
Format: `done INDEX` <br>
Example: <br>
   ```
   done 2
	   __________________________________________________
	   Nice! You completed this task:
	   [X] Return Book
	   __________________________________________________
   
   ```
   
### Deleting a task: `delete`
Deletes the `INDEX`th task from the list. <br>
Format `delete INDEX` <br>
Example: <br>
   ```
   delete 3
	   __________________________________________________
	   Seems like you didn't want this task:
	   [E][ ] Book Club (at: 25 September 2021 Sat 04:30 pm)
	   __________________________________________________
   ```
   
### Finding all tasks containing a keyword: `find`
Finds tasks with descriptions which contain all of the given keywords. <br>
Format: `find KEYWORD [MORE_KEYWORDS]` <br>
Example: <br>
   ```
   find book
	   __________________________________________________
	   Here are the matching tasks in your list: 
	   1. [T][ ] Read Book
	   2. [D][X] Return Book (by: 01 October 2021 Fri 00:00 pm)
	   __________________________________________________

   ```
   
### Filtering all tasks with a certain date: `filter`
Finds tasks with a certain date. <br>
Format: `filter YYYY/MM/DD` <br>
Example: <br>
   ```
   filter 2021/10/01
	   __________________________________________________
	   These are the tasks for 2021-10-01: 
	   1. [D][X] Return Book (by: 01 October 2021 Fri 00:00 pm)
	   __________________________________________________
   ```
   
### Exiting the program: `bye`
Exits the program. Have a productive day!<br>
Format: `bye` <br>

## Command Summary
Action | Format, Examples
-------|-----------------
Add todo | `todo TASKNAME` <br> e.g.,`todo Read Book`
Add deadline | `deadline TASKNAME /by YYYY/MM/DD HHMM`<br> e.g., `deadline Return Book /by 2021/10/01 1200`
Add event | `event TASKNAME /at YYYY/MM/DD HHMM` <br> e.g., `event Book Club /at 2021/09/25 1630`
List | `list`
Mark as done | `done INDEX` <br> e.g., `done 2`
Delete | `delete INDEX`<br> e.g., `delete 3`
Find | `find KEYWORD [MORE_KEYWORDS]` <br> e.g., `find book`
Filter | `filter YYYY/MM/DD` <br> e.g., `filter 2021/09/25`
Exit | `bye`
