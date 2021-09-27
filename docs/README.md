# Duke User Guide
Duke is a **desktop application for managing daily tasks, optimized for usage via a Command Line Interface (CLI).**
If you are someone who can type fast, Duke is the perfect application for you to efficiently manage your tasks.

## User Guide Content
- [1. Quick Start](#1-quick-start)
- [2. Features](#2-features)
  - [2.1 Viewing help: `help`](#21-viewing-help-help)
  - [2.2 Adding a task](#22-adding-a-task-todo-deadline-event)
    - [2.2.1 Todo: `todo`](#221-todotodo)
    - [2.2.2 Deadline: `deadline`](#222-deadline-deadline)
    - [2.2.3 Event: `event`](#223-event-event)
  - [2.3 Listing all tasks: `list`](#23-listing-all-tasks-list)
  - [2.4 Marking a task as done: `done`](#24-marking-a-task-as-done-done)
  - [2.5 Deleting a task: `delete`](#25-deleting-a-task-delete)
  - [2.6 Searching for tasks containing a specific keyword: `find`](#26-searching-for-tasks-containing-a-specific-keyword-find)
  - [2.7 Exiting the program: `bye`](#27-exiting-the-program-bye)
  - [2.8 Saving data](#28-saving-data)  
- [3. Frequently Asked Questions (FAQ)](#3-frequently-asked-questions-faq)
- [4. Command Summary](#4-command-summary)

## 1. Quick Start

1. Ensure **Java 11 or above** is installed on your computer.
2. Download the latest `Duke.jar` from [here](https://github.com/theeugenechong/ip/releases).
3. Copy the file to an empty folder. This folder will be used as the *home folder* for your Duke application.
4. [Open a terminal window](https://www.lifewire.com/open-command-prompt-in-a-folder-5185505)
   in the home folder.
5. Run Duke by entering `java -jar Duke.jar`. 
6. A folder `dukeData` will be created by default in the home folder containing `Duke.jar`. The folder will contain a
   `tasks.txt` file which is Duke's storage file.
> ####📝 Note about Duke's storage file:
> If you wish to create Duke's storage file in a specific folder, you can do this by specifying the file path along with the 
> command run in Step 5. 
> 
> For example, if you wish to create Duke's storage file in `folder1\folder2\tasks.txt`, the command in
> Step 5 should be run as `java -jar Duke.jar folder1\folder2\tasks.txt`
7. A greeting from Duke should appear as such:
```
=====================================================================
 Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

=====================================================================
 Hey there! I'm Duke.
 How may I help you?
=====================================================================
```
7. You can now type commands in the space below Duke's greeting and press *'Enter'* to execute the command. 
7. Here are some example commands you can try:
    - `help`: Displays all commands recognized by Duke and their respective formats.
    - `list`: Lists all tasks.
    - `todo Watch lecture`: Adds a todo with a description `Watch lecture` to Duke's task list.
7. Refer to the [Features](#2-features) section below for more details of each command.    

[**Back to top**](#user-guide-content)

## 2. Features
> #### 📝 **Some notes about the command format**:
> - Extraneous parameters for commands that do not take in parameters (*e.g.* `help`, `list`, and `bye`) will be ignored.
> For example, `help lalala` will be interpreted as `help`.
>- All commands are case-insensitive. For example, `list` is the same as `List` or `LIST`

### 2.1 Viewing help: `help`
Shows a list of all the commands recognized by Duke along with their respective formats.

Format: `help`

[**Back to top**](#user-guide-content)

### 2.2 Adding a task: `todo`, `deadline`, `event`
### 2.2.1 Todo:`todo`
Adds a todo to Duke's task list. A todo is a basic task containing only a description.

**Format:** `todo [description]`
- `[description]` is a string describing the todo

**Example:**
- `todo Watch lecture webcast`

**Example output:**
```
=====================================================================
 I have added a task:
   [T][ ] Watch lecture webcast
 You now have 1 task(s) in the list.
=====================================================================
```
[**Back to top**](#user-guide-content)

### 2.2.2 Deadline: `deadline`
Adds a deadline to Duke's task list. A deadline is slightly more complex and stores detail of when the deadline is due.

**Format:** `deadline [description] /by [due date]`
- `[description]` is a string describing the deadline
- `[due date]` is a string describing the date and time at which the deadline is due. The date and time **must be of the format** 
`dd-MM-yyyy HH:mm`
- Refer [here](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/format/DateTimeFormatter.html)
  for more details on what each alphabet represents in the date and time format.
   
**Example:**
- `deadline Coding assignment /by 01-10-2021 23:59`

**Expected output:**
```
=====================================================================
 I have added a task:
   [D][ ] Coding assignment (by: Fri, Oct 01 2021, 11:59 PM)
 You now have 2 task(s) in the list.
=====================================================================
```
[**Back to top**](#user-guide-content)

### 2.2.3 Event: `event`
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
=====================================================================
 I have added a task:
   [E][ ] Online internship briefing (at: Wed, Dec 29 2021, 12:00 PM)
 You now have 3 task(s) in the list.
=====================================================================
```
[**Back to top**](#user-guide-content)

### 2.3 Listing all tasks: `list`
Shows a list of all the tasks currently stored in Duke's task list.

**Format:** `list`

[**Back to top**](#user-guide-content)

### 2.4 Marking a task as done: `done`
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
=====================================================================
 Here are the tasks in your list:
 1. [T][ ] Watch lecture webcast
 2. [D][ ] Coding assignment (by: Fri, Oct 01 2021, 11:59 PM)
 3. [E][ ] Online internship briefing (at: Wed, Dec 29 2021, 12:00 PM)
=====================================================================
```
```
=====================================================================
 Great job! The following task is done:
   [T][X] Watch lecture webcast
=====================================================================
```
[**Back to top**](#user-guide-content)

### 2.5 Deleting a task: `delete`
Deletes the specified task from Duke's task list.

>💡 **Tip:** Use the `list` feature before this feature to know the index of the task you want to delete.
 
**Format:** `delete [task ID]`
- `[task ID]` represents the index of the task to delete. The task ID must be a **positive integer** 
  and also an index of a **task in the list**.

**Example (used together with `list`):**
- `delete 1`

**Expected output:**
```
=====================================================================
 Here are the tasks in your list:
 1. [T][X] Watch lecture webcast
 2. [D][ ] Coding assignment (by: Fri, Oct 01 2021, 11:59 PM)
 3. [E][ ] Online internship briefing (at: Wed, Dec 29 2021, 12:00 PM)
=====================================================================
```
```
=====================================================================
 Got it! I have deleted this task:
   [T][X] Watch lecture webcast
 You now have 2 task(s) in the list.
=====================================================================
```
[**Back to top**](#user-guide-content)

### 2.6 Searching for tasks containing a specific keyword: `find`
Shows a list of tasks containing the given keyword.

**Format:** `find [keyword]`

- A task is said to *contain* `[keyword]` if `[keyword]` is a **consecutive substring** in its description, *e.g.*
  Based on the current task list, `find Online briefing` returns no results but `find internship briefing` returns a result.
- The search is **case-insensitive**, *e.g.* `Coding` will match `coding`
- The **order** of the keywords matter, *e.g.* `internship briefing` will not match `briefing internship`.

**Example:** `find assignment`

**Expected output:**
```
=====================================================================
 Here are tasks containing "assignment"
 1. [D][ ] Coding assignment (by: Fri, Oct 01 2021, 11:59 PM)
=====================================================================
```
[**Back to top**](#user-guide-content)

### 2.7 Exiting the program: `bye`
Shows a *goodbye* message and exits the program.

**Format:** `bye`

**Expected output:**
```
=====================================================================
 Goodbye! Hope to see you again soon.
=====================================================================
```
[**Back to top**](#user-guide-content)

### 2.8 Saving data
- Duke's data is automatically saved in the computer's hard disk (in a folder `dukeData` located in the home folder) upon receiving a command that changes the data, *e.g.*
adding a task, deleting a task and marking a task as done. 
- You are **strongly advised** not to edit Duke's storage file as it 
may lead to errors when Duke reads from the file.

[**Back to top**](#user-guide-content)

## 3. Frequently Asked Questions (FAQ)
**Q:** How do I transfer my data to another computer?

**A:** Download `Duke.jar` in the other computer and copy the `dukeData` folder from your old computer to the folder containing `Duke.jar`
on the new computer. Running Duke should load your old data. 😄


**Q:** Do I need to know any programming language to use Duke?

**A:** No, Duke is programmed to be non-programmer friendly. You just need to be able to type fast and follow the specific
command formats given for the smoothest experience. 😄

[**Back to top**](#user-guide-content)

## 4. Command Summary
**Command** | **Format** | **Example**
------------|------------|------------
`help` | `help` | `help`
`todo` | `todo [description]` | `todo Watch lecture webcast`
`deadline` |`deadline [description] /by [due date]` | `deadline Coding assignment 01-10-2021 23:59`
`event` | `event [description] /at [event time]` | `event Online internship briefing 29-12-2021 12:00`
`list` | `list` | `list`
`done` | `done [task ID]` | `done 1`
`delete` | `delete [task ID]` | `delete 1`
`find` | `find [keyword]` | `find assignment`
`bye` | `bye` | `bye`

[**Back to top**](#user-guide-content)