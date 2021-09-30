# User Guide
C-3PO is a chat-bot that allows users to store and keep track of three different types of tasks: deadlines, events and todo tasks.
It also allows them to mark different tasks as done and search for specific tasks by filtering out tasks based on different parameters.

* [Quick Start](#quick-start)
* [Features](#features)
  * [List all tasks](#list-all-tasks-list)
  * [Add a deadline task](#add-a-deadline-task-deadline)
  * [Add an event task](#add-an-event-task-event)
  * [Add a todo task](#add-a-todo-task-todo)
  * [Mark a task as done](#mark-a-task-as-done-done)
  * [Delete a task](#delete-a-task-delete)
  * [Filter tasks by keywords](#filter-tasks-by-keywords-find)
  * [Filter tasks by date](#filter-tasks-by-date-date)
  * [Exit the program](#exit-the-program-bye)
* [FAQ](#FAQ)
* [Command Summary](#command-summary)

## Quick start

1. Ensure you have Java 11 or above installed in your Computer.


2. Download the latest iP.jar from [here](https://github.com/VishalJeyaram/ip/releases)


3. Copy the iP.jar file to the folder you want to use as the home folder for C-3PO.


4. **If you are using Windows**, click the Windows Start button and type in 'cmd' to open your command prompt. Then, navigate to the directory where iP.jar file is located.


5. **If you are using Mac or Linux or other systems**, open the terminal of your respective system and navigate to the directory where iP.jar file is located.


6. Run the command "java -jar iP.jar".


7. If the aforementioned steps are done properly, you should see the following.

```
File not found. Automatic text file creation initiated master!
____________________________________________________________
Hello! I am C-3P0! Human-cyborg relations! 
 
       /~\
      |oo )
      _\=/_
     /     \
    //|/.\|\\
   ||  \_/  ||
   || |\ /| ||
    # \_ _/  #
      | | |
      | | |
      []|[]
      | | |
     /_]_[_\

What can I do for you my master?

____________________________________________________________

Type something: 
```

8. Refer to the Features below and enter the associated commands and press Enter to execute them.

##Features
### List all tasks: `list`
Shows a list of all the user's tasks.
#### Format: `list`
Example of usage:

`list`

Expected outcome:
```
Accessing archives...
1. [T][X] buy groceries
2. [D][X] finish up CS2113T coding exercises (by: Sep 25 2021 4PM)
3. [E][ ] birthday celebration (at: Sep 29 2021 12PM)
```

### Add a deadline task: `deadline`
Adds a deadline task along with the date and time of the deadline to the user's list of tasks.
#### Format: `deadline {task description} /by {YYYY-MM-DD HH:MM}`
Example of usage:

`deadline finish up CS2113T coding exercises /by 2021-09-25 16:00`

Expected outcome:
```
Added to Galactic database:
[D][ ] finish up CS2113T coding exercises (by: Sep 25 2021 4PM)
```

### Add an event task: `event`
Adds an event task along with the date and time of the event to the user's list of tasks.
#### Format: `event {task description} /at {YYYY-MM-DD HH:MM}`
Example of usage:

`event birthday celebration /at 2021-09-29 12:00`

Expected outcome:
```
Added to Galactic database:
[E][ ] birthday celebration (at: Sep 29 2021 12PM)
```

### Add a todo task: `todo`
Adds a todo task to the user's list of tasks.
#### Format: `todo {task description}`
Example of usage:

`todo buy groceries`

Expected outcome:
```
Added to Galactic database:
[T][ ] buy groceries
```

### Mark a task as done: `done` 
Marks a specified task as done.
#### Format: `done {task index in list}`
Example of usage:

`done 2`

Expected outcome:
```
The following task has been marked as done Master!
2. [D][X] finish up CS2113T coding exercises (by: Sep 25 2021 4PM)
```

### Delete a task: `delete`
Deletes a specified task and shows how many tasks are remaining.
#### Format: `delete {task index in list}`
Example of usage:

`delete 3`

Expected outcome:
```
Taking one last look Master, at this Task. Removing the following from my memory
3. [E][ ] birthday celebration (at: Sep 29 2021 12PM)
Goodbye Task, may the force be with you. You have 2 task(s) left Master
```

### Filter tasks by keywords: `find`
Shows a list of all the user's tasks that contain keywords provided by the user.
#### Format: `find {keyword(s)}`
Example of usage:

`find buy`

Expected outcome:

```
Accessing archives...
Generating all the tasks that contain "buy"...
1. [T][X] buy groceries
2. [T][ ] buy notebooks
3. [D][ ] buy membership card (by: Oct 21 2021 7PM)
```

### Filter tasks by date: `date`
Shows a list of all the user's tasks that occur on a specific date provided by the user.

#### Format: `date {YYYY-MM-DD}`
Example of usage:

`date 2021-10-21`

Expected outcome:
```
Accessing archives...
Generating all the tasks that occur on "2021-10-21"...
1. [D][ ] buy membership card (by: Oct 21 2021 7PM)
2. [E][ ] marathon (at: Oct 21 2021 8AM)
```

### Exit the program: `bye`
Exits the program.
#### Format: `bye`

## FAQ
Q: Are the commands case-sensitive?

A: Yes, please follow the specified casing. All commands are in lower-case. However, your task descriptions can be in any case.

Q: How do I transfer my list of tasks and all associated data to another computer?

A: Transfer the text file named "Tasks.txt", located in the same directory as your iP.jar file over 
to your other computer. Place it in the same directory as the iP.jar file on your other computer. Your 
tasks will then be loaded from this text file when you run iP.jar on your new computer.

## Command Summary

Description | Syntax | Example
------------|--------|--------
Add a deadline task | `deadline {task description} /by {YYYY-MM-DD HH:MM}` | `deadline submit 2113T assignment /by 2021-10-21 18:00` 
Add an event task | `event {task description} /at {YYYY-MM-DD HH:MM}` | `event run marathon /at 2021-12-03 09:00`
Add a todo task | `todo {task description}` | `todo buy new phone`
Mark a task as done | `done {task index in list}` | `done 2`
Delete a task | `delete {task index in list}` | `delete 4`
Filter tasks by keywords | `find {keyword(s)}` | `find monday`
Filter tasks by date | `date {YYYY-MM-DD}` | `date 2022-05-17`
Exit the program | `bye` | `bye`
