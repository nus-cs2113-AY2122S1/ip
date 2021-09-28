# User Guide
Duke is an interaction program that used for managing tasks via Command Line Interface (CLI).
## Features 

### Add a Todo Task

Add a Todo task to the task list.

### Add a Deadline Task

Add a Deadline task to the task list.

### Add an Event Task

Add an Event task to the task list.

### Mark Task Status as Done

Mark a task's status as done in the task list.

### List the Whole Task List

List out all tasks with status in the order they were added.

### Delete a Task

Delete a task from the task list.

### Find Tasks

Find all related tasks in the task list based on the keyword typed in by user.

### Save All Changes to the Task List

All changes made by user each time will be automatically saved to a file.

### Exit the Program

Exit the program.

## Usage

### Adding a Todo Task: `todo`

Add a Todo task to the task list.

Format: `todo TASK_DESCRIPTION`

Examples: 

- `todo read book`
- `todo return book`

Expected Outcome:

```
------------------------------------------------------------
Got it. I've added this task
[T][0] read book
Now you have 1 task in the list
------------------------------------------------------------
```

### Adding a Deadline Task: `Deadline`

Add a Deadline task to the task list.

Format: `deadline TASK_DESCRIPTION /by DATETIME`

Examples:

- `deadline finish reading book /by Jan 7th`
- `deadline return book /by 5pm today`

Expected Outcome:

```
------------------------------------------------------------
Got it. I've added this task
[D][0] return book (by: 5pm today)
Now you have 2 task in the list
------------------------------------------------------------
```

### Adding an Event Task: `Event`

Add an Event task to the task list.

Format: `event TASK_DESCRIPTION /at DATETIME`

Examples:

- `event attend CS2113T lecture /at 4 - 6 pm on Friday`
- `event basketball training /at 7 - 10 pm on Monday`

Expected Outcome:

```
------------------------------------------------------------
Got it. I've added this task
[E][0] attend CS2113T lecture (at: 4 - 6 pm on Friday)
Now you have 3 task in the list
------------------------------------------------------------
```

### Marking a Task's Status as Done: `done`

Mark a task's status as done in the task list.

Format: `done INDEX`

Examples:
- `done 1`
- `done 2`

Expected Outcome:

```
------------------------------------------------------------
Nice! I've marked this task as done:
[T][1] read book
------------------------------------------------------------
```

### Listing Out the Whole Task List: `list`

List out all tasks in the list based on the order they were added.

Format: `list`

Expected Outcome:

```
------------------------------------------------------------
Here are the tasks in your list:
1.[T][1] read book
2.[D][1] return book (by: 5pm today)
3.[E][0] attend CS2113T lecture (at: 4 - 6 pm on Friday)
4.[E][0] event basketball training (at: 7 - 10 pm on Monday)
------------------------------------------------------------
```

### Deleting a Task From Task List: `delete`

Delete a task from the task list.

Format: `delete INDEX`

Examples:
- `delete 1`
- `delete 2`

Expected Outcome:

```
------------------------------------------------------------
Noted. I've removed this task:
[T][1] read book
Now you have 3 tasks in the list
------------------------------------------------------------
```

### Finding Tasks: `find`

Find out all the related tasks in the task list according to the keyword user typed in.

Format: `find KEYWORD`

Example:
- `find book`

Expected Outcome:

```
------------------------------------------------------------
Here are the matching tasks in your list:
1.[T][0] read book
2.[D][0] return book (by: 5pm today)
------------------------------------------------------------
```

### Exiting the Program: `bye`

Exit the program with a bye message.

Format: `bye`

Expected Outcome:

```
------------------------------------------------------------
Bye. Hope to see you again soon!
------------------------------------------------------------
```

## Command List

Command | Format
------- | ------
Todo    | `todo DESCRIPTION`
Deadline| `deadline DESCRIPTION /by DATETIME`
Event   | `event DESCRIPTION /at DATETIME`
List    | `list`
Done    | `done INDEX`
Delete  | `delete INDEX`
Find    | `find KEYWORD`
Exit    | `bye`
