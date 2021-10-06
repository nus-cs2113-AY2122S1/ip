# Patchi the Tasks Fairy (ovo)B

Patchi is a cute and helpful **scheduler + todo list** that runs on the command line interface (CLI).

## Quick Start 

1. Download the latest release [here](https://github.com/aaapppsss/ip/releases/tag/A-Release) by clicking on `patchi.jar` under Assets.
2. Move `patchi.jar` into your desired folder.
3. Open your CLI and `cd` into the folder containing `patchi.jar`.
4. Run the command `java -jar patchi.jar`.
5. You should see the Welcome message as follows:
```
--- .o * : *. + .* : + o. ---
Patchi: Hello! I'm Patchi the tasks fairy (owo)B
 What can I do for you today? (ovo)B
--- .o * : typing... : + o. ---
Me:
```

## Features 

### Add Todo

Add a Todo to the task list.

### Add Deadline

Add a Deadline to the task list.

### Add Event

Add an Event to the task list.

### List Tasks

List all tasks in the task list.

### Mark Task as Done

Mark a task as done.

### Delete Task

Delete a task.

### Find Task

Find tasks that match a search term.

## Notes about command format 

- All dates are in the format `yyyy-mm-dd`

## Usage

### `todo` - Add todo

Adds a Todo to the task list.

Example of usage: 

`todo homework`

Expected outcome:

Shows the Todo added and the total number of tasks in the task list.

```
Patchi: Got it! I have added [T][ ] read book to your task list! (^O^)B
Patchi: You have 1 tasks now! Too much work... (ono)B
```

### `deadline` - Add deadline

Adds a Deadline to the task list.

Format: `deadline DESCRIPTION /by DATE`

Example of usage:

`deadline homework /by 2021-04-13`

Expected outcome:

Shows the Deadline added and the total number of tasks in the task list.

```
Patchi: Got it! I have added [D][ ] homework (by: Apr 13 2021) to your task list! (^O^)B
Patchi: You have 2 tasks now! Too much work... (ono)B
```
### `event` - Add event

Adds an Event to the task list.

Format: `event DESCRIPTION /at DATE`

Example of usage:

`event concert /at 2021-05-13`

Expected outcome:

Shows the Event added and the total number of tasks in the task list.

```
Patchi: Got it! I have added [E][ ] concert (at: May 13 2021) to your task list! (^O^)B
Patchi: You have 3 tasks now! Too much work... (ono)B
```

### `list` - List tasks

List all tasks in the task list.

Format: `list`

Expected outcome:

Shows all tasks in the task list.

```
Patchi: Here is the list of tasks you currently have! Work hard~ (owo)B
1. [T][ ] read book
2. [D][ ] homework (by: Apr 13 2021)
3. [E][ ] concert (at: May 13 2021)
```
### `done` - Mark task as done

Marks a task as done.

Format: `done TASK_INDEX`

Example of usage:

`done 1`

Expected outcome:

Shows success message after marking task as done.
Upon listing tasks, shows that the task is marked as done.

```
Patchi: Good job! I've marked this task as done on your list. Time for a break? (owo)B
list
Patchi: Here is the list of tasks you currently have! Work hard~ (owo)B
1. [T][¤] read book
```
### `find` - Find tasks

Finds tasks that match a search term.

Format: `find SEARCH_TERM`

Example of usage:

`find book`

Expected outcome:

Shows tasks that match the search term.

```
Patchi: Here is the list of tasks that match your search! Did you find what you were looking for? (omo)B
1. [D][ ] return book (by: Apr 21 2021)
2. [T][ ] read book
```

### `bye` - Exit Patchi

Exits the application.

Format: `bye`

Expected outcome:

Shows goodbye message.

```
Patchi: Bye! Hope to see you again soon! (~owo~)B
```
