# Duke User Guide

Duke is a **desktop app for managing tasks, optimized for use via a Command Line Interface (CLI)**. It is especially
efficient if you can type fast, and can be even faster than traditional Graphical User Interface (GUI) 
task managing apps.

---

## Quick Start

1. Ensure that you have `Java 11` or above installed in your computer.
2. Download the latest `duke.jar` from [here]().
3. Copy the jar file to the folder where you want to run Duke in.
4. Open your terminal app and make sure that you are in the same working directory as your duke file.
5. At the folder where you saved the jar file, run the command `java -jar duke.jar` in your terminal app.
6. In the case where Duke runs successfully, you should see the following output.


```
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
    ____________________________________________________________
        Hello! I'm Duke
        What can I do for you?
    ____________________________________________________________
```

7. Type your command in the blank line under the horizontal separator and press Enter to execute it.  
    (Ex. typing `list` and pressing Enter will display your current task list.)
8. Refer to the features section below for details on each command.

---

## Features

### Notes about the command format:
- Words in `UPPER-CASE` are parameters to be filled by the user. Here is a list of common parameters that will be used:
    - `TASK_NAME` represents the name of the task.
    - `DATE_TIME` represents the date to be specified by the user.
    - `INDEX` represents the index of the task in the task list.  
        - For example: index **1** corresponds to the **first task** in the task list, 
          index **3** corresponds to the **third task**.
    - `KEYWORD` represents the keyword to be specified by the user.
    - `TASK_TYPE_CHAR` represents the type of task.
        - `T` represents a `Todo` task.
        - `D` represents a `Deadline` task.
        - `E` represents an `Event` task.
    - `STATUS_MARKER_INT` represents the completion status of the task in the data text file.
        - `0` represents an **undone** task.
        - `1` represents a **done** task.
- Items with `...` after them can be used more than once.
    - For example: `done INDEX...` means `done 1 2 3` is a valid command.

### Feature List:
- [Adding Todo Task: `todo`](#todo---adding-a-todo-task)
- [Adding Deadline Task: `deadline`](#deadline---adding-a-deadline-task)
- [Adding Event Task: `event`](#event---adding-an-event-task)
- [Listing all tasks: `list`](#list---listing-all-tasks)  
- [Setting tasks as done: `done`](#done---setting-tasks-as-done)
- [Finding tasks by keyword: `find`](#find---finding-tasks-by-keyword)
- [Deleting tasks: `delete`](#delete---deleting-tasks)
- [Exiting the program: `bye`](#bye---exiting-the-program)
- [Saving the data](#saving-the-data)
- [Editing the data text file](#editing-the-data-text-file)

---

## `todo` - Adding a Todo Task

Adds a task of type `Todo` into your task list.  
  
Format: `todo TASK_NAME`
  
Example of usage: `todo read book`
  
When added successfully, you should see the following message:
```
    ____________________________________________________________
        Got it. I've added this task:
            [T][ ] read book
        Now you have 1 tasks in the list.
    ____________________________________________________________
```

---

## `deadline` - Adding a Deadline Task
Adds a task of type `Deadline` into your task list.
  
Format: `deadline TASK_NAME /by DATE_TIME`
  
Example of usage: `deadline return book /by Friday 6pm`

When added successfully, you should see the following message:
```
    ____________________________________________________________
        Got it. I've added this task:
            [D][ ] return book (by: Friday 6pm)
        Now you have 2 tasks in the list.
    ____________________________________________________________
```

---

## `event` - Adding an Event Task
Adds a task of type `Event` into your task list.

Format: `event TASK_NAME /at DATE_TIME`

Example of usage: `event meeting /at Thursday 5pm`

When added successfully, you should see the following message:
```
    ____________________________________________________________
        Got it. I've added this task:
            [E][ ] meeting (at: Thursday 5pm)
        Now you have 3 tasks in the list.
    ____________________________________________________________
```

---

## `list` - Listing all tasks
Lists all the tasks in your current task list.
  
Format: `list`
  
Example of usage: `list`
  
When successful, you should see the following message:
  
```
    ____________________________________________________________
        Here are the tasks in your list:
        1. [T][ ] read book
        2. [D][ ] return book (by: Friday 6pm)
        3. [E][ ] meeting (at: Thursday 5pm)
    ____________________________________________________________
```
***Note: The tasks displayed in this example may be different than the one you have.***

---

## `done` - Setting tasks as done
  
Set status of indexes of tasks specified as done.
  
Format: `done INDEX...`
  
Example of usage: `done 1 2`
  
When successful, you should see the following message:
```
    ____________________________________________________________
        Nice! I've marked these tasks as done:
            [T][X] read book
            [D][X] return book (by: Friday 6pm)
    ____________________________________________________________
```
***Note: The tasks displayed in this example may be different from the ones you have.***  
***Note: At least one index must be specified to run this command.***

---

## `find` - Finding tasks by keyword
Finds and displays all the tasks having the keyword in their names.
  
Format: `find KEYWORD`
  
Example of usage: `find book`
  
When successful, you should see the following message:
```
    ____________________________________________________________
        Here are the matching tasks in your list:
        1. [T][X] read book
        1. [D][X] return book (by: Friday 6pm)
    ____________________________________________________________
```

***Note: The keyword parameter cannot be empty.***

---

## `delete` - Deleting tasks
Delete indexes of tasks specified by the user.
  
Format: `delete INDEX...`
  
Example of usage: `delete 1 2`
  
When successful, you should see the following message:
```
    ____________________________________________________________
        Noted. I've removed this task:
            [T][X] read book
            [D][X] return book (by: Friday 6pm)
        Now you have 1 tasks in your list.
    ____________________________________________________________
```
***Note: The tasks displayed in this example may be different from the ones you have.***

---

## `bye` - Exiting the program
Exits the program.
  
Format: `bye`
  
Example of usage: `bye`
  
When successful, you should see the following message:
```
    ____________________________________________________________
        Bye. Hope to see you again soon!
    ____________________________________________________________
```

---

## Saving the data
Duke saves all the task data into a text file automatically after any command performed.  
There is no need to save data manually.

---

## Editing the data text file
Duke saves the task data into a text file `JAR_FILE_LOCATION/data/savedTasks.txt`. Although not recommended, you can
manually change the data in the text file.  

Be sure to follow this exact formatting:  
```
[TASK_TYPE_CHAR] | [STATUS_MARKER_INT] | TASK_NAME | DATE_TIME
```

---
