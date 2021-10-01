# User Guide

Karlett is a desktop app for keeping track of task lists, optimized for use via a Command Line Interface (CLI).

- Features and Usage
  - Loading the data
  - Adding a task of type todo: `todo`
  - Adding a task of type deadline: `deadline ... /by`
  - Adding a task of type event: `event ... /at`
  - Listing all tasks: `list`
  - Locating tasks by a key word: `find`
  - Marking a task as done: `done`
  - Deleting a task: `delete`
  - Exiting the program: `bye`
  - Saving the data
- Command Summary

## Features and Usage

> Notes about the command format:
>
> 1. Words in UPPER_CASE are the parameters to be supplied by the user.
> <br />e.g. in `todo TASK DESCRIPTIONS`, `TASK DESCRIPTIONS` is a parameter which can be used as `todo run 5km every day`.
>
> 2. Extraneous parameters for commands that do not take in parameters (i.e. `list`, `exit`) will be ignored.
> <br />e.g. if the command specifies `list 123`, it will be interpreted as `list`.

### Loading the data

Karlett automatically finds a file named "karlett.txt" and load data from it upon opening. If no matching file is found, user has the option to ask Karlett to create it for data storage.

### Adding a task of type todo: `todo`

Adds a task of type todo in the task list.

Format: `todo TASK DESCRIPTIONS`

Example of usage: 

`todo run 5km every day`

Expected outcome:

Karlett displays a task added confirmation message.

```
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
Karlett now remembers:
  [T] [ ] run 5km every day
You have 1 task in the list now meow (((;꒪ꈊ꒪;)))
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
```

### Adding a task of type deadline: `deadline ... /by`

Adds a task of type deadline in the task list.

Format: `deadline TASK DESCRIPTIONS /by DATE TIME`

- `DATE TIME` must follow the format *yyyy-MM-dd HH:mm*.

Example of usage: 

`deadline finish iP /by 2021-10-01 23:59`

Expected outcome:

Karlett displays a task added confirmation message.

```
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
Karlett now remembers:
  [D] [ ] finish iP (by: Oct 01 2021 23:59)
You have 2 tasks in the list now meow (((;꒪ꈊ꒪;)))
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
```

### Adding a task of type event: `event ... /at`

Adds a task of type event in the task list.

Format: `event TASK DESCRIPTIONS /at DATE TIME`

- `DATE TIME` must follow the format *yyyy-MM-dd HH:mm*.

Example of usage: 

`event take a shower /at 2021-09-30 08:00`

Expected outcome:

Karlett displays a task added confirmation message.

```
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
Karlett now remembers:
  [E] [ ] take a shower (at: Sep 30 2021 08:00)
You have 3 tasks in the list now meow (((;꒪ꈊ꒪;)))
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
```

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

Example of usage: 

`list`

Expected outcome:

Karlett displays all tasks in the task list.

```
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
ฅ1 [T] [ ] run 5km every day
ฅ2 [D] [ ] finish iP (by: Oct 01 2021 23:59)
ฅ3 [E] [ ] take a shower (at: Sep 30 2021 08:00)
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
```

### Locating tasks by a key word: `find`

Finds all tasks that contain an input key word.

Format: `find WORD`

- Karlett is case-sensitive.

Example of usage: 

`find iP`

Expected outcome:

Karlett display task(s) that contain(s) the word "iP".

```
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
Yayyy! Karlett found these matching tasks in your list:
ฅ1 [D] [ ] finish iP (by: Oct 01 2021 23:59)
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
```

### Marking a task as done: `done`

Updates the task status of the specified task as done in the task list.

Format: `done INDEX`

- Marks the task at the specified INDEX as done.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Example of usage: 

`done 2`

Expected outcome:

Karlett displays a Task 2 status updated message.

```
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
Meow~ Karlett has marked this task as done:
  [D] [V] finish iP (by: Oct 01 2021 23:59)
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
```

### Deleting a task: `delete`

Delete the specified task in the task list.

Format: `delete INDEX`

- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Example of usage: 

`delete 1`

Expected outcome:

Karlett displays Task 1 deleted message.

```
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
Meow~ Karlett has deleted this task:
  [T] [ ] run 5km every day
You have 2 tasks in the list now meow (((;꒪ꈊ꒪;)))
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
```

### Exiting the program: `bye`

Exits the program.

Format: `bye`

Example of usage:

`bye`

Expected outcome:

Karlett displays a farewell message.

```
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
Bye~Bye~ヾ(￣▽￣) Hope to see you again soon meow.
ﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟﾟ･:*｡(ꈍᴗꈍ)ε｀*)~｡*:･ﾟ
```

### Saving the data

Karlett data are saved in a file named "karlett.txt" in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Command Summary

Action | Format, Examples
------------ | -------------
Add todo | `todo TASK DESCRIPTIONS`<br />e.g., `todo run 5km every day`
Add deadline | `deadline TASK DESCRIPTIONS /by DATE TIME`<br />e.g., `deadline finish iP /by 2021-10-01 23:59`
Add event | `event TASK DESCRIPTIONS /at DATE TIME`<br />e.g., `event take a shower /at 2021-09-30 08:00`
List | `list`
Find | `fine WORD`<br />e.g., `find iP`
Mark as done | `done INDEX`<br />e.g., `done 2`
Delete | `delete INDEX`<br />e.g., `delete 1`
Exit | `bye`
