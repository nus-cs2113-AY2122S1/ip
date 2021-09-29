# CS2113T IP User Guide

# Introduction

Duke is a Command Line Interface (CLI) application that helps users to manage their tasks. With Duke, fast typers would be able to accomplish tasks faster then if they were to use an equivalent Graphical User Interface (GUI) application.

# Table of Contents
- Quick Start
- Features
  - Add Tasks
    - Todo
    - Deadline
    - Event
  - List all tasks
  - Mark a task as completed
  - Delete a task
  - Find tasks
  - Exit the program
  - Saving data to file
    - Automatic saving
    - Save file format
- Command Summary

# Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest release of `ip.jar` from [here](https://github.com/SeenFang/ip/releases).
3. In a command prompt, navigate to the directory containing the downloaded jar file.
4. Run the `ip.jar` program with the command `java -jar ip.jar`.

# Features

## Add Tasks

Add a task to be tracked. Supported task types: `Todo`, `Deadline` and `Event`.

### Add a task - Todo: `todo`

Todo tasks keep track of a single task.

Format: `todo <description>`

Examples: `todo order pizza`

Expected Outcome:

```
todo order pizza
============================================================
Got it. I've added this task:
  [T][ ] order pizza
There is now 1 task in the list
============================================================
```

### Add a task - Deadline: `deadline`

Deadline tasks keep track of a task that has a due date

Format: `deadline <description> /by <end_date>`

Examples: `deadline clean room /by 05-08-2022`

Dates can be entered in several formats including:
DD-MM-YYYY    e.g. 31-12-2021
YYYY/MM/DD    e.g. 2021/12/31

Expected Outcome:

```
deadline clean room /by 05-08-2022
============================================================
Got it. I've added this task:
  [D][ ] clean room (by: Aug 05 2022)
There are now 2 tasks in the list
============================================================
```

### Add a task - Event: `event`

Event tasks keep track of a task that has a start and end date

Format: `event <description> /at <end_date>`

Examples: `event rave party /at 20-04-2021`

Dates can be entered in several formats including:
DD-MM-YYYY    e.g. 31-12-2021
YYYY/MM/DD    e.g. 2021/12/31

Expected Outcome:

```
event rave party /at 20-04-2021
============================================================
Got it. I've added this task:
  [E][ ] rave party (at: Apr 20 2021)
There are now 3 tasks in the list
============================================================
```

## List all tasks: `list`

Prints a list of all the tasks currently being tracked.

Format: `list`

Expected Outcome:

```
list
============================================================
Here is the list of the things your feeble human mind is incapable of keeping track of:
1.[T][ ] order pizza
2.[D][ ] clean room (by: Aug 05 2022)
3.[E][ ] rave party (at: Apr 20 2021)
============================================================
```

## Mark a task as completed: `done`

Mark a task as completed and change the tasks status icon to `X`.

Format: `done <task_index>`

Example Usage: `done 2`

Expected Outcome:

```
done 2
============================================================
The task has been marked as done. No need to thank me.
  [D][X] clean room (by: Aug 05 2022)
============================================================
```

## Delete a task: `delete`

Remove a task from the currently tracked list of tasks.

Format: `delete <task_index>`

Example Usage: `delete 2`

Expected Outcome:

```
delete 1
============================================================
I have erased this task from existence:
  [T][ ] order pizza
============================================================
```

## Find tasks: `find`

Search for tasks in the task list with the search term in its description.

Format: `find <search_term>`

Example Usage: `find movie`

Expected Outcome:

```
find clean
============================================================
Here are the tasks tasks matching "clean"
1.[D][X] clean room (by: Aug 05 2022)
============================================================
```

### Exit the program: `bye`

Exits the program.

Format: `bye`

Expected Outcome:

```
bye
============================================================
My favourite feeling is schadenfreude. I also like hiraeth. It's the Welsh concept of longing for home.
============================================================
```

## Saving data to a file

### Automatic saving

The tasks in the task list will automatically be saved to a file `data/tasks.txt` in the current directory after every modification.

### Save file format

The save file saves tasks in the following formats:

```
<type>|<completion_status>|<description>
<type>|<completion_status>|<description>|<date>
```

## Command Summary

| Action                   | Format   | Example                            |
|--------------------------|----------|------------------------------------|
| Add a task - Todo        | todo     | todo order pizza                   |
| Add a task - Deadline    | deadline | deadline clean room /by 05-08-2022 |
| Add an task - Event      | event    | event rave party /at 20-04-2021    |
| Mark a task as completed | done     | done 2                             |
| Delete a task            | delete   | delete 1                           |
| Find tasks               | find     | find room                          |
| Exit the program         | bye      | bye                                |
