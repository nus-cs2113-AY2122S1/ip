# Duke User Guide

This is Duke user guide. It implements a simple to do list which has some simple functions. Given below are instructions on how to use it.

## Setting up Duke

1. You can download the latest release containing the `ip.jar` file.
2. Ensure that you have Java 11 installed.
3. Execute the ip.jar by running the following command: `java -jar ip.jar`
4. If you see the following welcome screen, you have succesfully ran Duke.
```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

____________________________________________________________

 Hello! I'm Duke
 What can I do for you?

____________________________________________________________
```

## Features

### Adding a todo: `todo`
Adds a todo containing a task description to the task list.

Format: ```todo DESCRIPTION```
* `DESCRIPTION` is a string that cannot be whitespace or empty.

Input:
`todo task 1`

Output:
```
Got it. I've added this task:
[T][] task 1
____________________________________________________________

You have 1 tasks in the list.
1 tasks are undone.
____________________________________________________________
```
### Adding a deadline: `deadline`
Adds a deadline containing a description and date to the task list.

Format: ```deadline DESCRIPTION /by DATE```

* `DESCRIPTION` is a string that cannot be whitespace or empty.
* `DATE` is a string that cannot be whitespace of empty.

Input:
`deadline task 2 /by Sunday 4pm`

Output:
```
Got it. I've added this task:
[D][] task 2 (by: Sunday 4pm)
____________________________________________________________

You have 2 tasks in the list.
2 tasks are undone.
____________________________________________________________

```
### Adding an event: `event`
Adds a event containing a task description and a date to the task list.

Format: ```event DESCRIPTION /at DATE```

* `DESCRIPTION` is a string that cannot be whitespace or empty.
* * `DATE` is a string that cannot be whitespace of empty.

Input:
`event task 3 /at Sunday 4pm`

Output:
```
Got it. I've added this task:
[E][] task 3 (at: Sunday 4pm)
____________________________________________________________

You have 3 tasks in the list.
3 tasks are undone.
____________________________________________________________
```
### Listing all tasks: `list`
Lists all tasks that are current available. A display message will be shown if there are no tasks available.

Format: `list`

Input:
`list`

Output:
```
Here are the tasks in your list:
____________________________________________________________

1. [T][] task 1
2. [D][] task 2 (by: Sunday 4pm)
3. [E][] task 3 (at: Sunday 4pm)
You have 3 tasks in the list.
3 tasks are undone.
____________________________________________________________
```

input:
`list`

Output:
`You have no tasks at the moment!`

### Deleting a task: `delete`
Deletes a task of with index number `INDEX` from the task list. A display message will be shown if index does not exist.

Format: `delete INDEX`

Input:
```
delete 3
```

Output: 
```
This task index does not exist
```

Input:
```
delete 2
```

Output:
```
Noted. I've removed this task:
[E][] task 3 (at: Sunday 4pm)
____________________________________________________________

You have 2 tasks in the list.
2 tasks are undone.
____________________________________________________________
```

### Finishing a task: `done`
Mark a task as done. A display message will be shown if task index is not found.

Format: `done INDEX`

Input:
`done 5`

Output:
```
____________________________________________________________

This task index does not exist
____________________________________________________________
```

Input:
`done 1`

Output:
```
Got it. I've marked this task as complete:
____________________________________________________________

[T][X] task 1
You have 2 tasks in the list.
1 tasks are undone.
____________________________________________________________
```
### Finding a task: `find`
Finds a task containing `WORD`. A display message will be shown if no such tasks exist.

Format: `find WORD`

Input: 
`find 1`

Output:
```
____________________________________________________________

Here are the matching tasks in your list:
1. [T][X] task 1
____________________________________________________________
```

Input:
`find 3`

Output:
```
____________________________________________________________

There are no tasks containing such words!
____________________________________________________________
```

### Saving all tasks: `save`
Saves all tasks into a .txt file with a specified file path in Duke.

Format: `save`

Input:
`save`

Output:
```
____________________________________________________________

Tasks saved successfully
____________________________________________________________
```

### Exiting the program: `bye`
Exits the program.

Format: `bye`

Input:
`bye`

Output:
```
____________________________________________________________

Bye. Hope to see you again soon!
____________________________________________________________
```
