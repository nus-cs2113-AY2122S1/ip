# User Guide

Unker is a **Command Line Interface (CLI) application** for you to let your favourite uncle manage your daily tasks.
If you can type fast,  Unker will be able to help you manage your tasks quicker and easier!

- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding a Todo: `todo`](#adding-a-todo-todo)
  - [Adding a Deadline: `deadline`](#adding-a-deadline-deadline)
  - [Adding a Event: `event`](#adding-a-event-event)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Marking a task as done: `done`](#marking-a-task-as-done-done)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Searching for a task: `find`](#searching-for-a-task-find)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
  - [Save data to file](#save-data-to-file)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start
1. Do ensure you have at least `Java 11` installed on your computer.
    - You can download Java from [Oracle's website](https://java.com/en/download/).
2. Download the latest copy of `Unker.jar` from the [Releases](https://github.com/kelvneo/ip/releases) page of this repository.
3. Create a folder to a place of your liking, and place `Unker.jar` inside the folder.
4. Open your Command Prompt (Windows) or Terminal (Mac/Linux), and navigate to the folder you created.
5. Run the command `java -jar Unker.jar` and you should see the following if you are successful:
    ```
    Loading your digital
    _    _       _             
    | |  | |     | |            
    | |  | |_ __ | | _____ _ __ 
    | |  | | '_ \| |/ / _ \ '__|
    | |__| | | | |   <  __/ |   
    \____/|_| |_|_|\_\___|_|   

    --// Unker //----------------------------------------------
    Harlo, you can call me Unker.
    What can Unker do for you today?
    > 
    ```
6. You can now type commands! See below for the list of things you can run.

## Features

### Adding a Todo: `todo`

Adds a to-do task for Unker to track for you.

**Format:** `todo <description>`

**Example Input:**
```
> todo Hang clothes out to dry
```

**Example Output:**
```
--// Unker //----------------------------------------------
Okay Unker help you add this to your to-do list:
	[T] [ ] Hang clothes out to dry
--
Anything else you wan Unker to help you with?
> 
```

### Adding a Deadline: `deadline`

Adds a task with an end date for Unker to track for you.

**Format:** `deadline <description> /by <date> <time>`

> **✏️ Note:**  
> `<date> <time>` needs to be in the format of `yyyy-MM-dd HH:mm`.  
> For example: `2021-09-01 16:00`  

**Example Input:**
```
> deadline CS2113T project /by 2021-09-23 23:59
```

**Example Output:**
```
--// Unker //----------------------------------------------
Okay Unker help you add this to your to-do list:
	[D] [ ] CS2113T project (by: 23 Sep 2021, 11:59 PM)
--
Anything else you wan Unker to help you with?
> 
```

### Adding a Event: `event`

Adds an event with a date, start time and end time for Unker to track for you.

**Format:** `event <description> /at <date> <start time>-<end time>`

> **✏️ Note:**  
> `<date> <start time>-<end time>` needs to be in the format of `yyyy-MM-dd HH:mm-HH:mm`.  
> For example: `2021-09-01 16:00-18:00`  

**Example Input:**
```
> event Google Fireside Chat /at 2021-09-15 16:00-18:45
```

**Example Output:**
```
--// Unker //----------------------------------------------
Okay Unker help you add this to your to-do list:
	[E] [ ] Google Fireside Chat (at: 15 Sep 2021, 4:00 PM to 6:45 PM)
--
Anything else you wan Unker to help you with?
> 
```

### Listing all tasks: `list`

Shows a list of all tasks tracked by Unker

**Format:** `list`

**Example Output:**
```
--// Unker //----------------------------------------------
This is what you give me:
1. [T] [ ] Hang clothes out to dry
2. [D] [ ] CS2113T project (by: 23 Sep 2021, 11:59 PM)
3. [E] [ ] Google Fireside Chat (at: 15 Sep 2021, 4:00 PM to 6:45 PM)

You still got 3 task(s) left to do.
--
Anything else you wan Unker to help you with?
> 
```

### Marking a task as done: `done`

Tell the Unker that you are done with the task.

**Format:** `done <index>`

> **✏️ Note:**  
> `<index>` can be referenced from the `list` command.


**Example Input:**
```
> done 2
```

**Example Output:**
```
--// Unker //----------------------------------------------
Good job, this task finish already:
	[D] [X] CS2113T project (by: 23 Sep 2021, 11:59 PM)

--
Anything else you wan Unker to help you with?
> 
```

### Deleting a task: `delete`

Tell the Unker that you want to delete which task.

**Format:** `delete <index>`

> **✏️ Note:**  
> `<index>` can be referenced from the `list` command.

**Example Input:**
```
> delete 1
```

**Example Output:**
```
--// Unker //----------------------------------------------
Okay Unker help you remove this task for you already:
	[T] [X] Hang clothes out to dry

--
Anything else you wan Unker to help you with?
> 
```

### Search for tasks: `find`

Ask Unker to help you find a certain task based on a `<keyword>`.

**Format:** `find <keyword>`

**Example Input:**
```
> find project
```

**Example Output:**
```
--// Unker //----------------------------------------------
Here is what Unker found for you:
- [D] [X] CS2113T project (by: 23/09/2021 2359)
--
Anything else you wan Unker to help you with?
> 
```

### Exiting the program: `bye`

Quits the program.

**Format:** `bye`

**Output:**
```
--// Unker //----------------------------------------------
Bye bye, see you soon again arh!
```

### Save data to file

Unker will save all the tasks you gave him to a file in the folder under `data/unker.csv` together with your JAR file. You may change the contents inside the file as you wish when the program is not running.

The data is stored in a CSV format: `<task type>,<is done>,<description>`

> ❗ **Caution**:  
> If there is a **syntax or parsing error** in any line in the save file, Unker will **silently ignore** the line, and may even **delete** it if any changes are made to the file.
> 
> If you **change the file** as the program is **running**, your changes will be **overridden** by the program when the program exits or updates the file.

___

## FAQ
**Question:** Why can't I see certain tasks after I edited my save file?  
**Answer:** Do check your save file for any syntax issues, the `<description>` follows the command format stated above.

**Question:** How do I transfer my data to another computer?  
**Answer:** Install the application in another computer 
and overwrite the empty data file after running the program at least once.  

___

## Command Summary

| Action | Format | Example |  
|--------|--------|---------|
|Adding a Todo|`todo <description>`|`todo Hang clothes out to dry` |
|Adding a Deadline|`deadline <description> /by <date> <time>`|`deadline CS2113T project /by 2021-09-23 23:59`| 
|Adding an Event|`event <description> /at <date> <start time>-<end time>`|`event Google Fireside Chat /at 2021-09-15 16:00-18:45`|
|Listing all tasks| `list` | `list`|
|Marking a task as done| `done <index>`| `done 1`|
|Deleting a task|`delete <index>`|`delete 1`|
|Searching for a task | `find <keyword>` | `find project`|
|Exiting the program| `bye`| `bye`|
