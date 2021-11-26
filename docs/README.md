# Itachi User Guide

Itachi is an **interactive desktop app for managing tasks, optimized for use via Command Line Interface (CLI)**. 
Itachi is inspired from an anime character who is very reliable and knowledgeable.

- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding a Todo task: `todo`](#adding-a-todo-task-todo)
  - [Adding a Deadline task: `deadline`](#adding-a-deadline-task-deadline)
  - [Adding an Event task: `event`](#adding-an-event-task-event)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Marking a task as done: `done`](#marking-a-task-as-done-done)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Searching for a task: `find`](#searching-for-a-task-find)
  - [Exit the program: `bye`](#exit-the-program-bye)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Command Summary](#command-summary)

--------------------------------------

## Quick Start

1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `iP.jar` under v0.2 from [here](https://github.com/KishorKumar11/ip/releases/)
3. Go to the folder you saved `iP.jar` and note the **absolute file path**.
4. If you are using **Windows**, open up a Command prompt terminal `cmd.exe` or `powershell.exe` and 
for **Mac** and **Linux** users, do the same with the terminal of your respective systems.
5. Navigate to the folder where `iP.jar` is stored.
6. Execute `java -jar iP.jar`in the terminal and the application will start running. 

You should be able to see something like this:
```
THE GREATEST SHINOBI
 _____  ________    _        ______  ___  ___  ______
|_   _||  _   _ |  / \    .' ___  | |_  ||  _||__   _|
  | |     | |     / _ \  / .'   \_   | |__| |    | |
  | |     | |    / ___ \ | |         |  __  |    | |
 _| |_   _| |_ _/ /     \ \ `.___.'\_| |  | |   _| |_
|_____| |_____|____| |____ `.____ .|____||____||_____|

Welcome! You have entered my illusion where I will be your partner
and I will make you productive in order for you to reach your goals
so that I can fulfill my dream of making someone great.
Go ahead, give your command

________________________________________________________
What can I do for you?
________________________________________________________

```

--------------------------------------

## Features 

### Adding a Todo task: `todo`

Adds a Todo task to the task list. A Todo task only contains a task description.  

Format: `todo {task description}`

Example:
```
todo Project Research
________________________________________________________
Got it. I've added this task:
[T][ ] Project Research
Now you have 1 tasks in the list.
________________________________________________________
```

### Adding a Deadline task: `deadline`

Adds a Deadline task to task list. A Deadline task contains a task description and a due date.

Format: `deadline {task description} /by {due date}`

⚠️  `due date` needs to be in format `yyyy-MM-dd`.

Example:
```
deadline Project Report /by 2021-09-25
________________________________________________________
Got it. I've added this task:
[D][ ] Project Report (by: Sep 25 2021)
Now you have 2 tasks in the list.
________________________________________________________
```

### Adding an Event task: `event`

Adds an Event task to task list. An Event task contains a task description and the event information.

Format: `event {task description} /at {event information}`

Example:
```
event project meeting /at NUS Engin Room 30 Sep 2pm
________________________________________________________
Got it. I've added this task:
[E][ ] project meeting (at: NUS Engin Room 30 Sep 2pm)
Now you have 3 tasks in the list.
________________________________________________________
```

### Listing all tasks: `list`

Shows a list of all tasks stored.

Format: `list`

Example:
```
list
________________________________________________________
Here are the tasks in your list:
1. [T][ ] Project Research
2. [D][ ] Project Report (by: Sep 25 2021)
3. [E][ ] project meeting (at: NUS Engin Room 30 Sep 2pm)
________________________________________________________
```

### Marking a task as done: `done`

Marks a task as completed. 
Index given should be a **valid number** from the task list.

Format: `done {task index}`
- Marks the task with a [X] for the given `task index`. 
- `task index` refers to the index number shown in the displayed task list.

Example:
```
done 1
________________________________________________________
Nice! I've marked this task as done:
[T][X] Project Research
________________________________________________________
```

### Deleting a task: `delete`

Deletes a task from the task list. 
Index given should be a **valid number** from the task list.

Format: `delete {task index}`
- Delete the task at the specified `task index`.
- `task index` refers to the index number shown in the displayed task list.

Example:
- After `delete 1` the tasks indices will decrease by one. The second task will now become the first task and so on.
```
delete 1
________________________________________________________
Noted. I've deleted this task:
[T][X] Project Research
Now you have 2 tasks in the list.
________________________________________________________
```

### Searching for a task: `find`

Find tasks which contains the keyword.

Format: `find {keyword}`
- Finds every task that contains the searched `keyword`.
- Both the name and the date parameters are searched.

Example:
- `find Sep` returns the same as the following.
```
find project
________________________________________________________
Here are the matching tasks in your list:
1. [D][ ] Project Report (by: Sep 25 2021)
2. [E][ ] project meeting (at: NUS Engin Room 30 Sep 2pm)
________________________________________________________
```

### Exit the program: `bye`

Exits the Itachi application.

Format:`bye`

Example:
```
bye
________________________________________________________
Farewell. Come back when you need more help
________________________________________________________
```

### Saving the data

Task data in Itachi are saved in the hard disk automatically after any command that changes the data. There is no need to 
save manually.

### Editing the data file

Itachi data are saved as a txt file `[JAR file location]/data/tasks.txt`. Advanced users are welcome to update data 
directly by editing that data file.

❗  **Caution**: If your changes to the data file is detected to be corrupted or does not conform to Itachi's 
saved file format (txt), Itachi will not run.

-----------------------------------------------

## FAQ

**Question:** How do I transfer my data to another Computer?

**Answer:** Install the app in the other computer and overwrite the empty data file it creates with the file that
contains the data of your previous Itachi home folder.

-----------------------------------------------

## Command Summary

Action | Format | Example
------ | ------ | -------
Adding a Todo task | `todo {task description}` | `todo Project Research`
Adding a Deadline task | `deadline {task description} /by {due date}` | `deadline Project Report /by 2021-09-25`
Adding an Event task | `event {task description} /at {event information}` | `event project meeting /at NUS Engin Room 30 Sep 2pm`
Listing all tasks | `list` | `list`
Marking a task as done | `done {task index}` | `done 1`
Deleting a task | `delete {task index} ` | `delete 1`
Searching for a task | `find {keyword}` | `find project`
Exit the program | `bye` | `bye`

-----------------------------------------------