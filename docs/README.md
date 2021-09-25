# User Guide

## What is Duke?
Duke is a **task management tool** designed to help individuals keep track of their tasks. 
This **command-line based app** features a personal assistant, Lizzy, that you can interact with in order
to perform various actions.

## Starting the program

(Adapted start-up instructions from [addressbook-level2](https://github.com/se-edu/addressbook-level2/blob/master/docs/UserGuide.adoc))

### Using IntelliJ

- Find the project in the `Project Explorer` (usually located at the left side)

  - If the `Project Explorer` is not visible, press `ALT+1` for Windows/Linux, `CMD+1` for macOS to open the Project Explorer tab

- Go to the `src` folder and locate the `Duke` file

- Right click the file and select `Run Duke.main()`

- The program now should run on the `Console` (usually located at the bottom side)

- Now you can interact with the program through the `Console`

### Using Command Line

- 'Build' the project using IntelliJ
- Open the `Terminal`/`Command Prompt`
- `cd` into the project's `out\production\ip` directory
- Type `java duke.program.Duke`, then Enter to execute
- Now you can interact with the program through the CLI

## Features 

### Listing all tasks: `list`

Shows a list of all tasks in your task list.

Format: `list`

Example Usage:
```
list
```
Outcome:

```
Here are the tasks in your list:
1.[T][ ] do laundry
2.[D][ ] complete assignment (by: Oct 01 2021 11:59 PM)
3.[E][ ] career fair (at: Nov 01 2021 12:00 PM to Nov 01 2021 05:00 PM)
```

### Adding a task: `todo`, `deadline`, `event`

In Duke, you can add 3 different types of tasks: Todos, Deadlines, and Events.

- ToDos: tasks without any date/time attached to it *e.g., visit new theme park*
- Deadlines: tasks that need to be done before a specific date/time *e.g., submit report by 11/10/2019 5pm*
- Events: tasks that start at a specific time and ends at a specific time *e.g., team project meeting on 2/10/2019 2-4pm*

#### Adding a Todo Task:

Format: `todo <TASK>`

Example Usage:
```
todo do laundry
```
Outcome:

```
Got it. I've added this task:
[T][ ] do laundry
Now you have 1 tasks in the list.
```

#### Adding a Deadline Task:

Format: `deadline <TASK> /by <DATE_TIME>`

`<DATE_TIME>` must be in the exact format as specified (`dd/mm/yyyy hhmm`), or the task will
not be added.

Example Usage:
```
deadline complete assignment /by 01/10/2021 2359
```
Outcome:

```
Got it. I've added this task:
[D][ ] complete assignment (by: Oct 01 2021 11:59 PM)
Now you have 2 tasks in the list.
```

#### Adding an Event Task:

Format: `event <TASK> /at <START_DATE_TIME> to <END_DATE_TIME>`
`<START_DATE_TIME>` `<END_DATE_TIME>`and  must be in the exact format as specified (`dd/mm/yyyy hhmm`), or the task will
not be added.

Example Usage:
```
event career fair /at 01/11/2021 1200 to 01/11/2021 1700
```
Outcome:

```
Got it. I've added this task:
[E][ ] career fair (at: Nov 01 2021 12:00 PM to Nov 01 2021 05:00 PM)
Now you have 3 tasks in the list.
```

### Mark a task as done: `done`

Marks a task as completed. This process is irreversible; trying to mark
a already completed task as done will bring up an error message.

Format: `done <TASK_INDEX>`

`TASK_INDEX` refers to the index number of the task in the most recent list.

Example Usage:
```
done 2
```
Outcome:

*If task is incomplete:*
```
Nice! I've marked this task as done:
[D][X] complete assignment (by: Oct 01 2021 11:59 PM)
Now you have 3 tasks in the list.
```
*If task was already completed:*
```
Slow down there bud! You've already completed this task!
```

### Delete a task: `delete`

Removes a task from the task list. This process is irreversible.

Format: `delete <TASK_INDEX>`

`TASK_INDEX` refers to the index number of the task in the most recent list.

Example Usage:
```
delete 2
```
Outcome:

```
Noted. I've removed this task:
[D][X] complete assignment (by: Oct 01 2021 11:59 PM)
Now you have 2 tasks in the list.
```

### Find all tasks containing a keyword: `find`

Finds tasks whose description contains the given keyword.

Format: `find <KEYWORD>`

Example Usage:
```
find career
```
Outcome:

```
Here are the matching tasks in your list:
1.[E][ ] career fair (at: Nov 01 2021 12:00 PM to Nov 01 2021 05:00 PM)
```

### Exit the program: `bye`

Exits Duke.

Format: `bye`

Example Usage:
```
bye
```
Outcome:

```
Bye. Hope to see you again soon!
```

### View list of commands: 

The list of commands is displayed whenever you type in an incorrect command.

Example Usage:
```
abcd
```
Outcome:

```
Sorry bud, but that command is gibberish to me. I can only read 8 words!
The eight words are:
list
done
delete
find
todo
deadline
event
bye
```

### Saving the data:

The task list data is saved in the hard disk automatically after the execution
of any command that changes data, so there is no need to save manually.

## Command Summary

| Action       | Format + Examples                                                                                                   |
| :---         | :---                                                                                                               |
| **List**     | `list`                                                                                                             |
| **Todo**     | `todo <TASK_NAME>`<br/>eg., `todo do laundry`                                                                      |
| **Deadline** | `deadline <TASK_NAME> /by <DATE_TIME>`<br/>eg., `deadline complete assignment /by 01/10/2021 2359`                       |
| **Event**    | `event <TASK_NAME> /at <START_DATE_TIME> <END_DATE_TIME>`<br/>eg., `event career fair /at 01/10/2021 1200 01/10/2021 1700` |
| **Done**     | `done <TASK_INDEX>`<br/>eg., `done 1`                                                                             |
| **Find**     | `find <KEYWORD>`<br/>eg., `find career`                                                                              |
| **Delete**   | `delete <TASK_INDEX>`<br/>eg., `delete 2`                                                                         |
| **Exit**     | `bye` |