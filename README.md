# User Guide

Duke is a **task scheduler to help you manage your tasks, to be used via a Command Line Interface** (CLI).

## Content Page

* [Quick start](#1-quick-start)
* [Features](#2-features)
   * [Adding a task](#21-add-task)
      * todo: `todo`
      * event: `event`
      * deadline: `deadline`
   * [Listing](#22-list): `list`
   * [Marking a task as done](#23-marking-task-as-done): `done`
   * [Deleting a task](#24-deleting-a-task): `delete`
   * [Searching for tasks](#25-searching-for-tasks): `find`
   * [Exit programme](#26-exiting-the-program): `bye`
* [Command Summary](#3-summary)

## 1 Quick start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest `ip.jar` file.
3. Copy the file to your desired folder.
4. Using command prompt, use `cd` to navigate to the folder.
5. Run the `.jar` file in command prompt by entering `java -jar ip.jar`
6. Refer to the guide below for details of each command.

## 2 Features

### 2.1 Add Task

Add 3 different kinds of tasks to the list.

### Usage:

### `todo [description]` - Add todo task

Add a todo task to the task list.

Example of usage:

`todo read book`

Expected outcome:

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list
--------------------------------------------   
```

### `event [description] /at [event time]` - Add event task

Add an event task in the task list.

Example of usage:

`event project meeting /at Thursday 3-6pm`

Expected outcome:

```
Got it. I've added this task:
  [E][ ] project meeting (at: Thursday 3-6pm)
Now you have 2 tasks in the list
--------------------------------------------
```

### `deadline [description] /by [deadline time]` - Add deadline task

Add a deadline task in the task list.

Example of usage:

`deadline return book /by June 6th`

Expected outcome:

```
Got it. I've added this task:
  [D][ ] return book (by: June 6th)
Now you have 3 tasks in the list
--------------------------------------------
```

### 2.2 List

Show all existing tasks from the list.

### Usage:

### `list` - View list of tasks

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] read book
2. [E][ ] project meeting (at: Thursday 3-6pm)
3. [D][ ] return book (by: June 6th)
--------------------------------------------
```

### 2.3 Marking Task as Done

Mark a task in the task list as done based on the index of the task.

### Usage:

### `done [index]` - Mark the task as done specify by index

Example of usage:

`done 2`

Expected outcome:

```
 Nice! I've marked this task as done:
  [E][X] project meeting (at: Thursday 3-6pm)
--------------------------------------------
```

### 2.4 Deleting a Task

### `delete [index]` - Deletes a task in the task list based on the index of the task.

### Usage:

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
  [T][ ] read book
--------------------------------------------
```

### 2.5 Searching for tasks

Find a list of tasks in the task list that containing the keyword given.

### Usage:

### `find [keyword]` - Find tasks by keyword

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][ ] return book (by: June 6th)
--------------------------------------------
```

### 2.6 Exiting the program

Print goodbye message and end the program.

### Usage:

### `bye` - Exit the program

Example of usage:

`bye`

Expected outcome:

```
--------------------------------------------
Bye! Have a nice day.
--------------------------------------------
```

## 3. Summary

Command | Description
---------|------------
todo **[description]** | Adds a todo task with description
deadline **[description]** /by **[deadline time]** | Adds a deadline task with description and deadline
event **[description]** /at **[event time]** | Adds an event task with description and event time
delete **[index]** | Deletes index of task
done **[index]** | Mark index of task as done
find **[keyword]** | Find the specified keyword from the list of tasks
list | Display the current list of tasks
bye | Exits the program
