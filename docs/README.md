# User Guide

Project Kate is a **desktop application for recording different types of tasks**, 
optimised for use via a Command Line Interface (CLI) while having benefits of
a Graphic User Interface (GUI). If you can type fast, Kate is able
to record down your various tasks faster than traditional GUI applications.


## Contents

* [QuickStart](#quick-start)
* [Command Summary](#command-summary)
* [Features](#features)
  * [Add to-do](#adding-a-to-do-task-todo)
  * [Add deadline](#adding-a-task-with-deadline-deadline)
  * [Add event](#adding-a-task-for-an-event-event)
  * [Mark done](#marking-a-done-task-done)
  * [Delete task](#deleting-a-task-delete)
  * [List tasks](#listing-all-tasks-list)
  * [Find task](#finding-a-task-with-keyword-find)
  * [Help](#showing-help-page-help)
  * [Bye](#exiting-kate-bye)
  * [Save data](#saving-the-data)
  * [Edit data](#editing-the-data-file)


## Quick start

1. Ensure `Java 11` is installed in your computer.
   * Find the platform you are running on your computer and click on the corresponding 
     download link [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
2. Download the latest `kate.jar` from here.

## Command summary

**Notes about the command format**

* Parameters encased in `[]` are **compulsory** to be supplied by the user.
* Parameters encased in `<>` are **optional**.
* Parameters must be in a specific order specified by `help` command.
* `date` must be supplied in `dd-MM-yyyy` format.
* `time` uses 24-hour format and supplied in `HHmm`.

Command | Parameters
------- | ------
`todo` | `[description]`
`deadline` | `[description] /by [date] <time>`
`event` | `[description] /at [date] [time] <duration>`
`done` | `[task number]`
`delete` | `[task number]`
`list` | -
`find` | `[keyword]`
`help` | -
`bye`  | -


## Features

### Adding a to-do task: `todo`

Adds a to-do task to the task list.

Format: `todo [description]`

Example:
* `todo Buy groceries`

Expected output:
```
Okay, I have added this task!
  [T][ ] Buy groceries
You currently have (1) tasks in your list :)
```

### Adding a task with deadline: `deadline`

Adds a task with a deadline to the task list.

Format: `deadline [description] /by [date] <time>`
* `<time>` is defaulted to `23:59`

Examples:
* `deadline CS2113T Quiz /by 21-09-2021 1800`
* `deadline CS2106 Assignment /by 22-09-2021`

Expected output:
```
Okay, I have added this task!
  [D][ ] CS2106 Assignment (by: Sep 22 2021 23:59)
You currently have (2) tasks in your list :)
```

### Adding a task for an event: `event`

Adds an event into the task list.

Format: `event [description] /at [date] [time] <duration>`
* `<duration>` can only be supplied in complete hour.
  * Values such as `2.5` or `1.25` will not be accepted.
  
Examples: 
* `event CS2113T Lecture /at 24-09-2021 1600 2`
* `event CCA Fair /at 25-09-2021 0800`

Expected output:
```
Okay, I have added this task!
  [E][ ] CS2113T Lecture (at: Sep 24 2021 16:00 to Sep 24 2021 18:00)
You currently have (3) tasks in your list :)
```

### Marking a done task: `done`

Marks a specified task as done.

Format: `done [task number]`

Example: 
* `done 1`

Expected output:
```
Nice! I've marked this task as done:
  [T][X] Buy Groceries
```

### Deleting a task: `delete`

Deletes a specific task.

Format: `delete [task number]`

Example: 
* `delete 2`

Expected output:
```
Noted. I've removed this task:
  [E][ ] CS2113T Lecture (at: Sep 24 2021 16:00 to Sep 24 2021 18:00)
You currently have (2) tasks in your list :)
```
### Listing all tasks: `list`

Lists all the tasks in the task list.

Format: `list`

Expected output:
```
Here are the tasks in your list:
1. [T][X] Buy Groceries
2. [D][ ] CS2106 Assignment (by: Sep 22 2021 23:59)
```

### Finding a task with keyword: `find`

Lists the tasks with a specific keyword.

Format: `find [keyword]`

Examples:
* `find groceries`

Expected output:
```
Here are the filtered tasks with keyword: groceries
1. [T][X] Buy Groceries
```

### Showing help page: `help`

Shows the help page with the list of commands available.

Format: `list`

Expected output:
```
Please enter only the following commands: 
- todo [description]
- deadline [description] /by [date] <time>
- event [description] /at [date] [time] <duration>
- done [task number]
- delete [task number]
- list
- bye
```

### Exiting Kate: `bye`

Exits Kate.

Format: `bye`

Expected output:
```
Leaving already? Oh well see you again soon!
```

### Saving the data

Kate saves the tasks list **automatically** after running commands
that cause a modification in the tasks list.

### Editing the data file

Kate stores the data in a text file at
`[JAR file location]/data/kate.txt`. Advanced users can
modify the file directly by editing the file.

**Note**

In case the data file is modified incorrectly such that Kate
is unable to read, it will show the expected output below:
```
Did you tamper with the data file? It is CORRUPTED!
```

To continue using Kate, delete the file `kate.txt` and run the program as per normal