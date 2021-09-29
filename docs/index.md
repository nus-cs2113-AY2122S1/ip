# User Guide

Welcome to *Duke*! *Duke* is a **desktop app for managing tasks, events and deadlines, optimized for use via a Command
Line Interface
(CLI)**. It functions as a Personal Assistant Chatbot, that helps a person keep track of things.

- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding a to-do](#adding-a-todo-todo)
    - [Adding an event](#adding-an-event-event)
    - [Adding a deadline](#adding-a-deadline-deadline)
    - [Listing all tasks](#listing-all-tasks-list)
    - [Locating all tasks by matching description](#locating-all-tasks-by-matching-description-find)
    - [Listing upcoming deadlines](#listing-upcoming-deadlines-upcoming)
    - [Mark task as completed](#marking-task-as-completed-done)
    - [Deleting a task](#deleting-a-task-delete)
    - [Exiting](#exiting-the-program-bye)
    - [Saving the tasks](#saving-the-tasks)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java 11 installed in your Computer.
2. Download *CS2113_IP.jar* from the repo.
3. Copy the file into a *home folder* of your choice for Duke.
4. In the command line, navigate to Duke's *home folder* using the terminal command to change directory, `cd`.

```
cd Documents
cd Duke
```

5. Run Duke by entering the following line of code:

```
java -jar CS2113_IP.jar
```

6. Type the command in the command line and press *Enter* to execute the command. Some examples of commands you can try:
    - `list` List all tasks stored in the database
    - `todo TASK_NAME` Adds a task to Duke
    - `event EVENT_NAME /at Sunday, 2pm` Adds an event and the event's time to Duke.
    - `delete 2` Deletes the 2nd task in the list.
    - `done 1` Marks the 1st task in the list as completed.
    - `bye` Exits the app.

7. Refer to the *Features* below for details of each command.

________

## Features

### Adding a todo: `todo`

Adds a todo to Duke.

Format: `todo TASK_NAME`

Examples:

- `todo CS2113 Level-2`

Expected outcome:

```
Got it. I've added this task:
 [T][] CS2113 Level-2
Now you have 2 items in the list.
```

________

### Adding an event: `event`

Adds an event to Duke, and required descriptive fields of the event (time/location).

Format: `event EVENT_NAME /at EVENT_DESCRIPTION`

Examples:

- `event Mum's birthday celebration /at Sunday 2pm`
- `event CS2113 Mid-terms /at NUS LT51`

Expected outcome:

```
Got it. I've added this task:
 [E][] Mum's birthday celebration (at: Sunday 2pm)
Now you have 3 items in the list.
```

________

### Adding a deadline: `deadline`

Adds a task with deadline to Duke. Date-time of deadline is required as an input.

Format: `deadline EVENT_NAME /by d/M/yyyy HHmm`

Examples:

- `deadline IP submission /by 1/10/2021 2359`
- `deadline GES Essay /by 20/10/2021 1200`

Expected outcome:

```
Got it. I've added this task:
 [D][] IP submission (by: Oct 01 2021 23:59)
Now you have 4 items in the list.
```

________

### Listing all tasks: `list`

Shows a list of all the tasks in Duke's task list.

Format: `list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ] CS2113 Level-2
2.[E][ ] Mum's birthday celebration (at: Sunday 2pm)
3.[D][ ] IP submission (by: Oct 01 2021 23:59)
```

________

### Locating all tasks by matching description: `find`

Shows a list of all the tasks with description that matches the search key input by the user.

Format: `find SEARCH_KEY`

Example:

- find submission

Expected outcome:

```
Here are the tasks in your list:
1.[D][ ] IP submission (by: Oct 01 2021 23:59)
2.[T][X] SEP documents submission
```

Explanation of outcome:

- Duke checks whether the search key is a substring in the task description for each task.
- Tasks that contains the search key are listed.

________

### Listing upcoming deadlines: `upcoming`

Shows a list of all the uncompleted tasks with deadlines that are within three days.

Format: `upcoming`

Expected outcome (assuming current date - 26 Sep 2021):

```
Here are the upcoming deadlines in your list within the next three days:
>>> Java coding assignment (by: Sep 28 2021 23:59)
>>> CCA signups (by: Sep 27 2021 16:00)
```

________

### Marking task as completed: `done`

Marks specified task within Duke's task list as done.

Format: `done TASK_INDEX`

- Marks the task at the specified `TASK_INDEX`.
- `TASK_INDEX` refers to the index number shown in the displayed list of tasks.
- The index **must be a positive integer** and **within** the number of tasks.

Example:

- done 1

Expected outcome:
1st task in list is marked as done (represented by a `X`).

```
Nice! I've marked this task as done:
[T][X] CS2113 Level-2
```

________

### Deleting a task: `delete`

Deletes specified task within Duke's task list.

Format: `delete TASK_INDEX`

- Deletes the task at the specified `TASK_INDEX`.
- `TASK_INDEX` refers to the index number shown in the displayed list of tasks.
- The index **must be a positive integer** and **within** the number of tasks.

Example:

- delete 2

Expected outcome:
2nd task in list is deleted.

```
Noted. I've removed this task:
 [T][ ] SEP documents submission
Now you have 3 items in the list.
```

________

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the tasks

Duke automatically saves the data in a text file in the same directory of Duke. Hence, there is no need for to **save
manually**.
________

## Command Summary

Feature | Format | Example
------  | ------ | -------
**Add todo** |`todo TASK_NAME`| `todo CS2113 Level-2`
**Add event** |`event EVENT_NAME /at EVENT_DESCRIPTION`| `event Mum's birthday celebration /at Sunday 2pm`
**Add deadline** |`deadline EVENT_NAME /by d/M/yyyy HHmm`| `deadline IP submission /by 1/10/2021 2359`
**List tasks** |`list` | `list`
**Mark task** | `done TASK_INDEX` | `done 1`
**Delete task** | `delete TASK_INDEX` | `delete 1`
**Find task** | `find SEARCH_KEY` | `find submission`
**List upcoming deadlines** | `upcoming` | `upcoming`
**Exit Duke** | `bye` | `bye`
