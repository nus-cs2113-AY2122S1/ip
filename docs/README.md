# User Guide
Duke is a **Java Command Line Interface (CLI) program for managing your daily tasks, deadline, and events.** 
<br>If you are a fast typer, Duke is a perfect program for you to organize your tasks efficiently. 

* [Quick Start](#Quick-Start)
* [Features](#Features)
    * [Get Duke Manual : `help`](#help---get-duke-manual)
    * [Add New Todo Item : `todo`](#todo---add-new-todo-item)
    * [Add New Deadline Item : `deadline`](#deadline---add-new-deadline-item)
    * [Add a new Event item : `event`](#event---add-new-event-item)
    * [List all items : `list`](#list---list-all-tasks)
    * [Mark an item as done : `done`](#done---mark-task-as-done)
    * [Delete an item : `delete`](#delete---delete-a-task)
    * [Find an item : `find`](#find---search-related-word)
    * [Exit out of Duke : `bye`](#bye---exit-duke-program)
* [FAQ](#faq)
* [Command Summary](#Command-Summary)

## Quick Start
1. Make sure you have Java 11 or above installed.
2. Download the latest [`ip.jar`](https://github.com/LouisLouis19/ip/releases/download/A-Jar/ip.jar).
3. Copy the file to a folder where you want to use.
4. Using your terminal, navigate to the directory where you save the `ip.jar` file.
5. Run `java -jar ip.jar`
6. You should see a welcome message.

## Features

### `help` - Get Duke Manual
Get a summary of user manual for Duke Program

Example of usage and outcome:
`help`

```
 Got it. I've added this task:
   [T] [ ] buy bread
```

### `todo` - Add New Todo Item
Adds a todo task to the manager.

Format: `todo DESCRIPTION`

Example of usage and outcome:
`todo buy bread`

```
 Got it. I've added this task:
   [T] [ ] buy bread
```

### `deadline` - Add New Deadline Item
Adds a new Deadline task with Date and Time to the manager.

Format: `deadline DESCRIPTION /by yyyy-MM-dd HH:mm`

Example of usage and outcome:
`deadline submit code /by 2021-11-11 18:00`

```
 Got it. I've added this task:
  [D] [ ] submit code (by: Nov 11 2021 18:00PM)
```

### `Event` - Add New Event Item
Adds a new Event task with a Date amd Time to the manager.

Format: `event DESCRIPTION /at yyyy-MM-dd HH:mm`

Example of usage and outcome:

`event college party /at 2021-11-11 22:00`

```
 Got it. I've added this task:
   [E] [ ] college party (at: Nov 11 2021 22:00PM)
```

### `list` - List All Tasks
Displays all the current tasks in the manager

Example outcome:

```
 1. [D] [ ] submit code (by: Nov 11 2021 18:00PM)
 2. [E] [ ] college party (at: Nov 11 2021 22:00PM)
 3. [T] [ ] buy bread
```

### `done` - Mark Task As Done
Marks a task as done, according to its index on the list.

Format: `done TASK_INDEX`

Example of usage and outcome:

```
>> list
 1. [D] [ ] submit code (by: Nov 11 2021 18:00PM)
 2. [E] [ ] college party (at: Nov 11 2021 22:00PM)
 3. [T] [ ] buy bread
 
>> done 2
 Nice! I've marked this task as done:
   [E] [X] college party (at: Nov 11 2021 22:00PM)
```

### `delete` - Delete A Task
Delete a task from the manager, according to its index on the list.

Format: `delete TASK_INDEX`

Example of usage and outcome:

```
>> list
 1. [D] [ ] submit code (by: Nov 11 2021 18:00PM)
 2. [E] [X] college party (at: Nov 11 2021 22:00PM)
 3. [T] [ ] buy bread
 
>> delete 3
 Noted! I've removed this task:
   [T] [ ] buy bread
 Now you have 2 tasks in the list.
```


### `find` - Search Related Word
Find the tasks containing the queried word.

Format: `find WORD`

Example of usage and outcome:

```
>> find party
Here are some matching tasks:
Here are the matching tasks in your list to "party"
 1. [E] [X] college party (at: Nov 11 2021 22:00PM)
```

### `bye` - Exit Duke Program
Terminates Duke and saves all user data into `savedData\duke.txt`.

Example of usage and outcome:
```
>> bye
 Bye. Hope to see you again soon!
```

## FAQ
**1. How do I access my data from another device?**

Copy your `savedData\duke.txt` file to the other device. Then, run `ip.jar` from the same directory
where `savedData\duke.txt` is.

## Command Summary

**Action** | **Format, Examples**
------------ | -------------
**todo**|`todo DESCRIPTION` <br>e.g. `todo buy bread`
**deadline**|`deadline DESCRIPTION /by yyyy-mm-dd hh:mm` <br>e.g. `deadline submit code /by 2021-11-11 18:00`
**event**|`event DESCRIPTION /at yyyy-mm-dd hh:mm`<br>e.g. `event college party /at 2021-11-11 22:00`
**list**|`list`
**done**|`done TASK_INDEX` <br>e.g. `done 2`
**delete**|`delete TASK_INDEX` <br>e.g. `delete 3`
**find**|`find WORD`<br>e.g. `find party`
**exit**|`bye`
