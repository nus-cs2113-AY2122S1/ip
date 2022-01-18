# User Guide

ShellRPG is a **Command Line Interface (CLI) application for you to manage your daily tasks while
having a console based game like interactions.**
If you can type fast and enjoy a game like interface, ShellRPG is the perfect way to manage your
tasks and get more done during the day!

- [QuickStart](#quick-start)
- [Features](#features)
    - [Adding a Todo: `todo`](#adding-a-todo-todo)
    - [Adding a Deadline: `deadline`](#adding-a-deadline-deadline)
    - [Adding a Event: `event`](#adding-a-event-event)
    - [Listing all tasks: `list`](#listing-all-tasks-list)
    - [Marking a task as done: `done`](#marking-a-task-as-done-done)
    - [Deleting a task: `delete`](#deleting-a-task-delete)
    - [Searching for a task: `find`](#searching-for-a-task-find)
    - [Exiting the program: `bye`](#exiting-the-program-bye)
    - [Automatic Saving](#automatic-saving)
    - [Editing the save file](#editing-the-save-file)
- [FAQ](#faq)
- [Command Summary](#command-summary)

___

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest copy of `rpgshell.jar`
   from [here](https://github.com/3m0W33D/ip/releases/tag/A-Release).
3. Copy the file to the _home_ folder that you want to use for your ShellRPG
4. Open a command prompt `cmd.exe` or `powershell.exe`
   and navigate to the _home_ folder where the `rpgshell.jar` is located.
5. Execute the command in the terminal `java -jar rpgshell.jar` and the application will start
   running!
   You should see something similar to below.

```
____________________________________________________________
[+] Welcome to Shell RPG
[+] Searching for Character........
[+] Character Newbie Found!
[+] Character Level: 100
[+] Access Granted! Welcome to: 
 #####  #     # ####### #       #          ######  ######   #####  
#     # #     # #       #       #          #     # #     # #     # 
#       #     # #       #       #          #     # #     # #       
 #####  ####### #####   #       #          ######  ######  #  #### 
      # #     # #       #       #          #   #   #       #     # 
#     # #     # #       #       #          #    #  #       #     # 
 #####  #     # ####### ####### #######    #     # #        #####  
____________________________________________________________
┌─[ShellRPG@Newbie]-[~]
└──╼ $ 
```

___

## Features

### Adding a Todo: `todo`

Adds a Todo task to ShellRPG list of tasks. Todo tasks only contains a task description.

Format: `todo <Task Description>`

Example usage and outcome:

```
┌─[ShellRPG@Newbie]-[~]
└──╼ $ todo complete daily quest
____________________________________________________________
Got it. I've added this task:
[T][ ] complete daily quest
Now you have 1 tasks in the list.
____________________________________________________________
```

### Adding a Deadline: `deadline`

Adds a Deadline task to ShellRPG list of tasks. Deadline tasks contains a task description and the
date to indicate when the deadline should be completed by.

Format: `deadline <Task Description> /by <Date>`
> ⚠️`<Date>` needs to be in the format of `yyyy-MM-dd`. For example, `2021-09-01`

Example usage and outcome:

```
┌─[ShellRPG@Newbie]-[~]
└──╼ $ deadline CS2113T IP Submission /by 2021-10-01
____________________________________________________________
Got it. I've added this task:
[D][ ] CS2113T IP Submission (by: Oct 01 2021)
Now you have 2 tasks in the list.
____________________________________________________________
```

### Adding a Event: `event`

Adds an Event task to ShellRPG list of tasks. Event tasks contains a task description and the date
and the time to indicate when the event should be happening.

Format: `event <Task Description> /at <Datetime>`
> ⚠️`<Datetime>` needs to be in the format of `yyyy-MM-dd hhmm`. For example, `2021-09-01 2359`

Example usage and outcome:

```
┌─[ShellRPG@Newbie]-[~]
└──╼ $ event Midterms /at 2021-09-30 1400
____________________________________________________________
Got it. I've added this task:
[E][ ] Midterms (at: Sep 30 2021 14:00)
Now you have 3 tasks in the list.
____________________________________________________________
```

### Listing all tasks: `list`

Show the list of all task stored by ShellRPG.

Format: `list`

Example usage and outcome:

```
┌─[ShellRPG@Newbie]-[~]
└──╼ $ list
____________________________________________________________
Here are your tasks in your list:
1. [T][ ] complete daily quest
2. [D][ ] CS2113T IP Submission (by: Oct 01 2021)
3. [E][ ] Midterms (at: Sep 30 2021 14:00)
____________________________________________________________
```

### Marking a task as done: `done`

Update a task as completed in ShellRPG list of tasks.

Format: `done <Task Index>`
> ⚠️`<Task Index>` needs to be a valid index. Must be an already created task.

Example usage and outcome:

```
┌─[ShellRPG@Newbie]-[~]
└──╼ $ done 1
____________________________________________________________
Nice! I've marked this task as done:
[T][X] complete daily quest
____________________________________________________________
```

### Deleting a task: `delete`

Delete a task from ShellRPG list of tasks.

Format: `delete <Task Index>`
> ⚠️`<Task Index>` needs to be a valid index. Must be an already created task.

Example usage and outcome:

```
┌─[ShellRPG@Newbie]-[~]
└──╼ $ delete 1
____________________________________________________________
Noted. I've removed this task:
[T][X] complete daily quest
Now you have 2 tasks in the list.
____________________________________________________________
```

### Searching for a task: `find`

Search for the `Keyword` in all of ShellRPG list of task's description.

Format: `find <Keyword>`

Example usage and outcome:

```
┌─[ShellRPG@Newbie]-[~]
└──╼ $ find 2113T
____________________________________________________________
Here are the matching tasks in your list:
[D][ ] CS2113T IP Submission (by: Oct 01 2021)
____________________________________________________________
```

### Exiting the program: `bye`

Terminates ShellRPG.

Format: `bye`

Example usage and outcome:

```
┌─[ShellRPG@Newbie]-[~]
└──╼ $ bye
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### Automatic saving

The tasks in ShellRPG are automatically saved to a save file `[JAR_LOCATION]/data/duke.txt` after
the execution of every command. Advanced users are welcome to update data directly by editing
that `duke.txt`

> ❗ **Caution**: If the save file is detected to be corrupted
> or does not conform to ShellRPG's saved file format, ShellRPG will not run.

### Editing the save file

Advanced users can edit the `duke.txt` so that the modified data can be imported into the program.
Every line is one task and the fields are seperated via `|`.

> ❗ **Caution**: If the save file does not conform to the standards mentioned below
> the program will not run!   
> Only do this if you know what you are doing.

Todo: `<Type>|<Completion status>|<Description>`

E.g. `T | 0 | Complete Homework`

Deadline and Event: `<Type>|<Completion status>|<Description>|<Date/Datetime>`

E.g. `Deadline: D | 0 | Assignment Submission | 2021-10-10`

E.g. `Event: E| 0 | Midterms Test|2021-10-11 2359`

> 💡 `Completion Status` must be either `0` for incomplete or `1` for completed
>
> 💡 `<Date/Datetime>` must be in the format `yyyy-MM-dd/yyyy-MM-dd hhmm`
>
> 💡 `Todo` must have exactly 2 `|` and `Deadline/Events` must have exactly 3 `|`
> or else the file will fail to parse.  
> Read above to check for failure to parse.

___

## FAQ

**Question:** What happens if my save file is corrupted?  
**Answer:** You have 2 options. First you can copy your data file out and re-add the data back via
the program. Second you can re-edit the file until it parses be sure to check the above document for
the correct formats of dates.

**Question:** How do I transfer my data to another Computer?  
**Answer:** Install the application in another computer and overwrite the empty data file after
running the program at least once.

___

## Command Summary

| Action | Format | Example |  
|--------|--------|---------|
|Adding a Todo|`todo <task description>`|`todo complete daily quest` |
|Adding a Deadline|`deadline <Task Description> /by <Date>`|`deadline CS2113T IP Submission /by 2021-10-01`| 
|Adding a  Event|`event <Task Description> /at <Datetime>`|`event Midterms /at 2021-09-30 1400`|
|Listing all tasks| `list` | `list`|
|Marking a task as done| `done <Task Index>`| `done 1`|
|Deleting a task|`delete <Task Index>`|`delete 1`|
|Searching for a task | `find <Keyword>` | `find 2113T`|
|Exiting the program| `bye`| `bye`|
