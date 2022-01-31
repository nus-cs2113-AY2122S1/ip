# User Guide for Project Duke
Project Duke is a **Command Line Interface application (CLI)** to manage your tasks, which include todo, deadline and events

## Features 

### Add a task: `todo`, `deadline`, `event`

Add a new task in the list, which could be a todo, deadline or event task.

### Delete a task: `delete`

Delete a specific task in the list.

### Mark a task as done: `done`

Mark a specific task as done in the list.

### Find tasks with keyword in the list: `find`

Find tasks in the list with user's given keyword.

### List all tasks currently in the list: `list`

List all tasks currently in the list.

### Help: `help`

See all available commands and the usage of specific command.

### Exit: `exit`

Exits the current program.

## Usage

### `todo` - Add a todo task

> Adds new todo task in the list.

#### Syntax:

`todo [task description]`

#### Example of usage: 

`todo tidy up the room`

#### Expected outcome:

A todo task is added in the list.

#### Example output:

```
Gotcha. Do this while you're at it:
    [T][ ] tidy up the room
Now you have 9 tasks in the list.
```

### `deadline` - Add a deadline task

> Adds a deadline task in the list

#### Syntax:

`deadline [deadline description] /by [DD MM yyyy HH:mm]`

#### Example of usage:

`deadline CS2113T draft final version of iP /by 01 Oct 2021 23:59`

#### Expected outcome: 

A deadline task with due date is added in the list.

#### Example output:

```
Gotcha. Do this while you're at it:
    [D][ ] CS2113T draft final version of iP (by: 01 Oct 2021 23:59)
Now you have 10 tasks in the list.
```

### `event` - Add an event task

> Adds an event task with a date in the list

#### Syntax:

`event [event description] /at [DD MM yyyy HH:mm]`

#### Example of usage:

`event NUS CCA join rehearsal /at 02 Oct 2021 12:30`

#### Expected outcome:

An event with a date is added in the list.

#### Example output:

```
Gotcha. Do this while you're at it:
    [E][ ] NUS CCA join rehearsal (at: 02 Oct 2021 12:30)
Now you have 11 tasks in the list.
```

### `done` - Mark task as done

> Marks a specific task number done in the list

#### Syntax

`done [task number]`

#### Example of usage:

`done 9`

#### Expected outcome:

A task with the given number is marked as done

#### Example output:

```
Good job. You may now enjoy the rest of your suffering:
[T][X] tidy up the room
```

### `delete` - Delete a task

> Deletes a specific task number away from the list

#### Syntax

`delete [task number]`

#### Example of usage:

`delete 9`

#### Expected outcome:

A task with the given number is removed from the list

#### Example output:

```
Noted. I have removed this thing:
[T][X] tidy up the room
```

### `find` - Find task with a keyword

> Find tasks with matching user's input keyword

#### Syntax

`find [keyword]`

#### Example of usage:

`find CCA`

#### Expected outcome:

Tasks with matching keyword is displayed

#### Example output:

```
Here are the matching tasks with you query:
1.[E][ ] go CCA band practice (at: 23 Oct 2021 15:00)
2.[E][ ] NUS CCA join rehearsal (at: 02 Oct 2021 12:30)
```

### `help` - List available commands and usage

> A list of available commands or its usage is displayed

#### Syntax

`help` or `help [command]`

#### Example of usage:

* Listing **all** commands:

`help`

* Show a **specific** command and its usage:

`help deadline`

#### Expected outcome

The information of available commands are displayed

#### Example output

* Listing **all** commands:

```
List of available commands:
	todo     - a todo task without deadline
	event    - a task with a date to attend
	deadline - a task with a hard due date
	find     - find tasks in the list with a keyword
	done     - mark a task as done in the list
	delete   - delete a task from the list
	list     - list all tasks currently in the list
	bye      - exits the program
To find out more about the usage of each command,
type "help [command]"
```

* Show a **specific** command and its usage:

```
Adds a task with a hard due date
Syntax: deadline [deadline description] /by DD MM yyyy HH:mm
Example: "deadline CS2113T homework /by 01 Oct 2021 23:59"
```