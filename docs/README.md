# User Guide

## Features 

### Listing
List out all tasks in the tasklist.

### Add/Delete tasks
Add different type of tasks into the tasklist.
Delete a task anytime.

### Find
Find a task using keywords.

### Mark done
Mark a task as done.

### Auto-saving
Tasks in the tasklist are saved automatically when program exits, ready to be loaded next time.

## Usage

### `list` 
List out all tasks in the tasklist.

Example of usage:

`list`

Expected outcome:

```
1.[T][X] do wct
2.[E][ ] finish inquiry(at: 6pm)
```

### `done`
Mark a task as done.

Example of usage:

`done 2`

Expected outcome:

```
Nice! I've marked this task as done:
  [X] finish inquiry
```

### `todo`
Add a "todo" type task.

Example of usage:

`todo do cs2113 ip`

Expected outcome:

```
Got it. I've added this task:
  [T][ ] do cs2113 ip
Now you have 3 tasks in the list
```

### `deadline`
Add a "deadline" type task.

Example of usage:

`deadline cs2113 ip /by tonight`

Expected outcome:

```
Got it. I've added this task:
  [D][ ] cs2113 ip(by: tonight)
Now you have 5 tasks in the list
```

### `event`
Add a "event" type task.

Example of usage:

`event go to party /at tonight`

Expected outcome:

```
Got it. I've added this task:
  [E][ ] go to party(at: tonight)
Now you have 6 tasks in the list
```

### `find`
Find a task in the tasklist that contains the keyword.

Example of usage:

`find cs2113`

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][ ] do cs2113 ip
2.[D][ ] cs2113 ip(by: tonight)
```

### `delete`
Delete a task in the task list using the index.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
  [T][X] do wct
Now you have 4 tasks in the list
```

### `bye`
Terminates the program, saves all changes.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```