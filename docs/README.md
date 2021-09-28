# User Guide

## Add a Todo task

### `Keyword` - todo

Add a new Todo task into the task list.

Example of usage: 

`todo CS2113 iP`

Expected outcome:

```
Got it. I've added this task: 
    [T][ ] CS2113 iP
```

Description of the outcome.

```
Added a new todo task to the task list
```

## Add an Event task

### `Keyword` - event

Add a new Event task into the task list.

Example of usage:

`event go to reddit.com /at 5pm`

Add a new Event task into the task list.

Example of usage:

`event go to reddit.com /at 5pm`

Expected outcome:

```
Got it. I've added this task: 
  [E][ ] go to reddit.com (at: 5pm)
```

Description of the outcome.

```
Added a new event task to the task list
```

## Add a Deadline task

### `Keyword` - deadline

Add a new Deadline task into the task list.

Example of usage:

`deadline submit CS2113 iP /by 5pm`

Expected outcome:

```
Got it. I've added this task: 
  [D][ ] submit CS2113 iP (by: 5pm)
```

Description of the outcome.

```
Added a new deadline task to the task list
```

## Mark a task as done

### `Keyword` - done

Mark a task as done in the task list.

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done: 
  [T][X] CS2113 iP
```

Description of the outcome.

```
Mark a task as done based on the task number given.
```

## Delete a task

### `Keyword` - delete

Delete a task in the task list.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
  [T][X] CS2113 iP
```

Description of the outcome.

```
Delete a task based on the task number given.
```

## View all tasks

### `Keyword` - list

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[D][ ] submit CS2113 iP (by: 5pm)
```

Description of the outcome.

```
View all tasks in the task list.
```