# User Guide
Duke is a **desktop app for users to quickly manage their to-do list** via Command Line Interface(CLI) and
Graphical Line Interface (currently not available, but coming soon...).

## Quick Start
1. Ensure `Java 11` are installed in your computer.
2. Download the latest `ip.jar` from here.
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Type the following command in your terminal to run this program: 
java -jar ip.jar (You should change directory to where the ip.jar file is located or provide the absolute path of ip.jar).
5. Duke will ask for your name. If you are a new user, Duke will initialize an empty task list for you.
If you have run Duke before, it will restore your saved tasks for you.
6. After Duke starts serving, type the command in the command box and press Enter to execute it. 
e.g. typing `help` and pressing Enter will open the help window. <br/>
   Some example commands you can try:
     - `todo return book`: add "return book" task.
     - `deadline cs2113 project /2021-09-28 23:59`: add "cs2113 project" task, which is due at 23:59, Sep 28th 2021.
     - `event cs2113 lecture /2021-10-01 16:00`: add "cs2113 lecture" task, which will happen at 16:00, Oct 1st 2021.
     - `list`: list all the tasks.
     - `delete 3`: delete the 3rd task of the list.
     - `done 1`: set the 1st task as done.
     - `sort`: sort all the tasks based on emergency.
     - `find cs2113`: find all the tasks having the keyword "cs2113".

## Features 
1. Add a task into the list
2. Delete a task
3. List all the tasks
4. Complete a task
5. Find task(s) containing certain keyword
6. Sort tasks
7. Get help
8. Exit

### Add a task into the list:
There are three possible task types that can be added.
* `todo`: A task without time constraint
* `deadline`: A task needs to be completed **before certain time**.
* `event`: A task needs to be complete **at certain time**.
#### format: 
1. add a `todo` task: `todo <task description>`
2. add a `deadline` task: `deadline <task description> /<YYYY-MM-DD> [HH:MM]`
3. add a `event` task: `event <task description> /<YYYY-MM-DD> <HH:MM>`


### Delete tasks
delete a task at specific index.
#### format: `delete <task index>`
- The index refers to the index number in the list.
- The index must be a positive number, and should not exceed the total number of tasks inside the list.
Otherwise, Duke may throw a warning.



### List all tasks
Show a list of all tasks.

#### format: `list`


### Complete a task

User can set a task at specific index after he/she has completed it.

#### format: `done <task index>`
- The index refers to the index number in the list.
- The index must be a positive number, and should not exceed the total number of tasks inside the list.
  Otherwise, Duke may throw a warning.


### Find some task(s)
A user can search some task(s) containing certain keywords
#### format: `find <keyword>`

### Sort tasks
Tasks can be sorted based on emergency. Tasks have closer deadline will be put in front.
#### format: `sort`

### Get help
List all the commands format if users forget them
#### format: `help`

### Exit
Exit the program and automatically save all the tasks.
#### format: `bye`

### Edit the data file
Tasks data are saved as a `txt` file `[JAR File location]/UserStatus/[Username].txt`.
Advanced users are welcome to update data directly by editing that data file.


:exclamation:**Caution**
<span style="background-color:#fff4d4">If your changes to the data file makes its format invalid,
Duke will discard all data and start with an empty task list.
</span>

