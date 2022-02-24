# User Guide

## Features 

### Add Tasks

Allows user to add different type of tasks to the list.

### Mark Tasks as Done

Allows user to mark tasks as completed.

### Delete Tasks

Allows user to remove tasks from the list.

### Search for Tasks

Allows user to search for some specific tasks within the list using a keyword.

### Data Storage

Allows the user to read and store the tasks from and to the directory `data/duke.txt`.

## Usage

### `todo` - Add ToDo

Add ToDo type of task to the list and display a completion message.

Example of usage: 

`todo workout`

Expected outcome:

```
Ok, I've added this task:
[T][ ] workout
Now you have 6 tasks in the list. Anything else?
```

### `deadline` - Add Deadline

Add Deadline type of task with the date of format `yyyy-MM-dd` to the list and display a completion message with a date of format `MMM d yyyy`.

Example of usage:

`deadline finish ip /by 2021-10-01`

Expected outcome:

```
Ok, I've added this task:
[D][ ] finish ip /by Oct 1 2021
Now you have 7 tasks in the list. Anything else?
```

### `event` - Add Event

Add Event type of task with the date of format `yyyy-MM-dd` to the list and display a completion message with a date of format `MMM d yyyy`.

Example of usage:

`event attend meeting /at 2021-10-03`

Expected outcome:

```
Ok, I've added this task:
[E][ ] attend meeting /at Oct 3 2021
Now you have 8 tasks in the list. Anything else?
```

### `list` - Display Tasks

Print all the tasks in the list.

Example of usage:

`list`

Expected outcome:

```
  1.[T][X] read book
  2.[D][ ] return book /by Jun 1 2021
  3.[E][ ] project meeting /at Aug 6 2021
  4.[T][X] join sports club
```

### `done` - Mark Task as Done

Mark completed task as done using the number of the respective task in the list.

Example of usage:

`done 2`

Expected outcome:

```
Congratulations! You have completed the task: [D][X] return book /by Jun 1 2021
```

### `delete` - Delete Task

Remove task from the list using the number of the respective task in the list.

Example of usage:

`delete 7`

Expected outcome:

```
Noted. I've removed this task:
[D][ ] finish ip /by Oct 1 2021
Now you have 7 tasks in the list. Anything else?
```

### `find` - Find Tasks

Find some tasks in the list that contain a keyword.

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][X] return book /by Jun 1 2021
```