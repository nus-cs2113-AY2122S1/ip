# Duke User Guide
Duke is a **desktop application for managing daily tasks, optimized for usage via a Command Line Interface (CLI).**
If you are someone who can type fast, Duke is the perfect application for you to efficiently manage your tasks.

## User Guide Content
- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help-help)
  - [Adding a task](#adding-a-task-todo-deadline-event)
    - [Todo: `todo`](#todotodo)
    - [Deadline: `deadline`](#deadline-deadline)
    - [Event: `event`](#event-event)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Marking a task as done: `done`](#marking-a-task-as-done-done)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Searching for tasks containing a specific keyword: `find`](#searching-for-tasks-containing-a-specific-keyword-find)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
  - [Saving data](#saving-data)  
- [Frequently Asked Questions (FAQ)](#frequently-asked-questions-faq)
- [Command Summary](#command-summary)

##Quick Start

1. Ensure **Java 11 or above** is installed on your computer.
1. Download the latest `Duke.jar` from [here](https://github.com/theeugenechong/ip/releases).
1. Copy the file to an empty folder. This folder will be used as the *home folder* for your Duke application.
1. [Open a terminal window](https://superuser.com/questions/339997/how-to-open-a-terminal-quickly-from-a-file-explorer-at-a-folder-in-windows-7/340051)
   in the home folder.
1. Run Duke by entering `java -jar Duke.jar`.  
1. A greeting from Duke should appear as such:
```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

____________________________________________________________
 Hey there! I'm Duke.
 How may I help you?
____________________________________________________________
```
7. You can now type commands in the space below Duke's greeting and press *'Enter'* to execute the command. 
7. Here are some example commands you can try:
    - `help`: Displays all commands recognized by Duke and their respective formats.
    - `list`: Lists all tasks.
    - `todo Watch lecture`: Adds a todo with a description `Watch lecture` to Duke's task list.
7. Refer to the Features section below for more details of each command.    

[**Back to top**](#user-guide-content)

## Features
> #### 📝 **Some notes about the command format**:
> - Extraneous parameters for commands that do not take in parameters (*e.g.* `help`, `list`, and `bye`) will be ignored.
> For example, `help lalala` will be interpreted as `help`.
>- All commands are case-insensitive. For example, `list` is the same as `List` or `LIST`

### Viewing help: `help`
Shows a list of all the commands recognized by Duke along with their respective formats.

Format: `help`

[**Back to top**](#user-guide-content)

### Adding a task: `todo`, `deadline`, `event`
### Todo:`todo`
Adds a todo to Duke's task list. A todo is a basic task containing only a description.

**Format:** `todo [description]`
- `[description]` is a string describing the todo

**Example:**
- `todo Watch lecture webcast`

**Example output:**
```
____________________________________________________________
 I have added a task:
   [T][ ] Watch lecture webcast
 You now have 1 task(s) in the list.
____________________________________________________________
```
[**Back to top**](#user-guide-content)

### Deadline: `deadline`
Adds a deadline to Duke's task list. A deadline is slightly more complex and stores detail of when the deadline is due.

**Format:** `deadline [description] /by [due date]`
- `[description]` is a string describing the deadline
- `[due date]` is a string describing the date and time at which the deadline is due. The date and time **must be of the format** 
`dd-MM-yyyy HH:mm`
- Refer [here](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/format/DateTimeFormatter.html)
  for more details on what each alphabet represents in the date and time format.
   
**Example:**
- `deadline Coding assignment /by 29-12-2021 23:59`

**Expected output:**
```
____________________________________________________________
 I have added a task:
   [D][ ] Coding assignment (by: Dec 29 2021 11:59 PM)
 You now have 2 task(s) in the list.
____________________________________________________________
```
[**Back to top**](#user-guide-content)

### Event: `event`
Adds an event to Duke's task list. An event is similar to a deadline and also stores details of when the event is happening.

**Format:** `event [description] /at [event time]`
- `[description]` is a string describing the event
- `[event time]` is a string describing the date and time at which the event is happening. The date and time **must be of the format**
`dd-MM-yyyy HH:mm`
- Refer [here](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/format/DateTimeFormatter.html)
  for more details on what each alphabet represents in the date and time format.
  
**Example:**
- `event Online internship briefing /at 29-12-2021 12:00`

**Expected output:**
```
____________________________________________________________
 I have added a task:
   [E][ ] Online internship briefing (at: Dec 29 2021 12:00 PM)
 You now have 3 task(s) in the list.
____________________________________________________________
```
[**Back to top**](#user-guide-content)

### Listing all tasks: `list`
Shows a list of all the tasks currently stored in Duke's task list.

**Format:** `list`

[**Back to top**](#user-guide-content)

### Marking a task as done: `done`
Marks the task specified as done.

>💡 **Tip:** Use the `list` feature before this feature to know the index of the task you want to mark as done.

**Format:** `done [task ID]`
- `[task ID]` represents the index of the task to be marked done. The task ID must be a **positive integer** 
and also an index of a **task in the list**.
- The box beside each task shows if the task has been completed or not.
  The box for a completed task looks like `[X]` while the box for an uncompleted task looks like `[ ]`.  

**Example (used together with `list`):**
- `done 1`

**Expected output:**
```
____________________________________________________________
 Here are the tasks in your list:
 1. [T][ ] Watch lecture webcast
 2. [D][ ] Coding assignment (by: Dec 29 2021 11:59 PM)
 3. [E][ ] Online internship briefing (at: Dec 29 2021 12:00 PM)
____________________________________________________________
```
```
____________________________________________________________
 Great job! The following task is done:
   [T][X] Watch lecture webcast
____________________________________________________________
```
[**Back to top**](#user-guide-content)

### Deleting a task: `delete`
Deletes the specified task from Duke's task list.

>💡 **Tip:** Use the `list` feature before this feature to know the index of the task you want to delete.
 
**Format:** `delete [task ID]`
- `[task ID]` represents the index of the task to delete. The task ID must be a **positive integer** 
  and also an index of a **task in the list**.

**Example (used together with `list`):**
- `delete 1`

**Expected output:**
```
____________________________________________________________
 Here are the tasks in your list:
 1. [T][ ] Watch lecture webcast
 2. [D][ ] Coding assignment (by: Dec 29 2021 11:59 PM)
 3. [E][ ] Online internship briefing (at: Dec 29 2021 12:00 PM)
____________________________________________________________
```
```
____________________________________________________________
 Got it! I have deleted this task:
   [T][X] Watch lecture webcast
 You now have 2 task(s) in the list.
____________________________________________________________
```
[**Back to top**](#user-guide-content)

### Searching for tasks containing a specific keyword: `find`
Shows a list of tasks containing the given keyword.

**Format:** `find [keyword]`

- A task is said to *contain* `[keyword]` if `[keyword]` is a **consecutive substring** in its description, *e.g.*
  Based on the current task list, `find Online briefing` returns no results but `find internship briefing` returns a result.
- The search is **case-insensitive**, *e.g.* `Coding` will match `coding`
- The **order** of the keywords matter, *e.g.* `internship briefing` will not match `briefing internship`.

**Example:** `find assignment`

**Expected output:**
```
____________________________________________________________
 Here are tasks containing "assignment"
 1. [D][ ] Coding assignment (by: Dec 29 2021 11:59 PM)
____________________________________________________________
```
[**Back to top**](#user-guide-content)

### Exiting the program: `bye`
Shows a *goodbye* message and exits the program.

**Format:** `bye`

**Expected output:**
```
____________________________________________________________
 Goodbye! Hope to see you again soon.
____________________________________________________________
```
[**Back to top**](#user-guide-content)

### Saving data
- Duke's data is automatically saved in the computer's hard disk (in a folder `dukeData` located in the home folder) upon receiving a command that changes the data, *e.g.*
adding a task, deleting a task and marking a task as done. 
- You are **strongly advised** not to edit Duke's storage file as it 
may lead to errors when Duke reads from the file.

[**Back to top**](#user-guide-content)

## Frequently Asked Questions (FAQ)
**Q:** How do I transfer my data to another computer?

**A:** Download `Duke.jar` in the other computer and copy the `dukeData` folder from your old computer to the folder containing `Duke.jar`
on the new computer. Running Duke should load your old data. 😄


**Q:** Do I need to know any programming language to use Duke?

**A:** No, Duke is programmed to be non-programmer friendly. You just need to be able to type fast and follow the specific
command formats given for the smoothest experience. 😄

[**Back to top**](#user-guide-content)

## Command Summary
**Command** | **Format** | **Example**
------------|------------|------------
`help` | `help` | `help`
`todo` | `todo [description]` | `todo Watch lecture webcast`
`deadline` |`deadline [description] /by [due date]` | `deadline Coding assignment 29-12-2021 23:59`
`event` | `event [description] /at [event time]` | `event Online internship briefing 29-12-2021 12:00`
`list` | `list` | `list`
`done` | `done [task ID]` | `done 1`
`delete` | `delete [task ID]` | `delete 1`
`find` | `find [keyword]` | `find assignment`
`bye` | `bye` | `bye`

[**Back to top**](#user-guide-content)