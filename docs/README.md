# User Guide v0.2
Duke is an all-in-one task tracker to help you manage your daily events and duties.

## Summary of commands
* Adding todos: `todo`
* Adding events: `event`
* Adding deadlines: `deadline`
* Listing tasks: `list`
* Marking tasks: `done`
* Searching tasks: `find`
* Deleting tasks: `delete`
* Display help: `help`
* Exiting the program : `bye`

## Features

### Adding todos: `todo`

Adds a todo to your task list.

#### Format: `todo NAME`

Adds a todo of the specified `NAME`.
`NAME` can be any string of characters

#### Examples:

`todo eat cheese`

#### Expected Output:

```
Got it. I've added this task:
1. [T] [ ] eat cheese
Now you have 1 tasks in the list.
```

### Adding events: `event`

Adds an event to your task list.

#### Format: `event NAME /at DATE`

Adds an event of the specified `NAME` and `DATE`.
`NAME` can be any string of characters.
`DATE` must be in the format YYYY-MM-DD.

#### Examples:

`event meteor shower /at 2019-06-09`

#### Expected Output:

```
Got it. I've added this task:
2. [E] [ ] meteor shower (at: 9 Jun 2019)
Now you have 2 tasks in the list.
```

### Adding deadlines: `deadline`

Adds a deadline to your task list.

#### Format: `deadline NAME /by DATE`

Adds a deadline of the specified `NAME` and `DATE`.
`NAME` can be any string of characters.
`DATE` must be in the format YYYY-MM-DD.

#### Examples:

`deadline Final version /at 2021-10-01`

#### Expected Output:

```
Got it. I've added this task:
3. [D] [ ] Final version (by: 1 Oct 2021)
Now you have 3 tasks in the list.
```

### Listing tasks: `list`

Shows all existing tasks.

#### Format: `list`

#### Examples:

`list`

#### Expected Output:

```
1. [T] [ ] eat cheese
2. [E] [ ] meteor shower (at: 9 Jun 2019)
3. [D] [ ] Final version (by: 1 Oct 2021)
```

### Marking tasks: `done`

Marks the task as completed.

#### Format: `done INDEX`

Marks a task of a specific `INDEX` to show that it has been completed.
`INDEX` must be an integer corresponding to the index of an existing task.

#### Examples:

`done 2`

#### Expected Output:

```
Nice! I've marked this task as done:
[X] meteor shower
```

### Searching tasks: `find`

Looks through the list of tasks and lists out any tasks that contains the given keyword.

#### Format: `find KEYWORD`

Searches for any existing tasks that contains the given `KEYWORD` and lists them out.
`KEYWORD` can be any string of characters.

#### Examples:

`find eat`

#### Expected Output:

```
Here are the matching tasks in your list:
1. [T] [ ] eat cheese
```

### Deleting tasks: `delete`

Deletes a task.

#### Format: `delete INDEX`

Deletes a task of the specified `INDEX`.
`INDEX` must be an integer corresponding to the index of an existing task.

#### Examples:

`delete 1`

#### Expected Output:

```
Noted. I've removed this task:
[T] [ ] eat cheese
```

### Display help: `help`

Shows a helpful summary of the commands and syntax.

#### Format: `help`

### Exiting the program: `bye`

Exits the program

#### Format: `bye`

#### Examples:

`bye`

#### Expected Output:

```
Bye. Hope to see you again soon!
```
