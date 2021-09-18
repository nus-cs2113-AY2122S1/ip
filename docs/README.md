# User Guide

## Features 
  
### Feature-Deadline

Stores a task with deadline in the tasklist.

### Feature-Event

Stores an Event with date and time in the task list.

### Feature-Find

Find all task with description matching the user's input.

### Feature-Todo

Creates a reminder in the tasklist.

### Feature-What's on

Lists all tasks occuring on the date provided by the user.


## Usage

### `Bye` - Quits the program.

Expected outcome:

The programs ends and saves the data.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Bye. Hope to see you again soon!
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```
  
### `Deadline` - Creates a task with deadline.

Creates a task with a deadline and adds it to the task list.

Example of usage: 

`deadline project /by 30/09/2021 1200`

Expected outcome:

A task named project is created with dateline 30/09/2021 1200.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Got it. I've added this task:
[D][ ] project (by: Sep 30 2021 1200)
Now you have 1 tasks in the list.
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `Delete` - Deletes a task.

Deletes a specifed task.

Example of usage: 

`delete 1`

Expected outcome:
The task selected is removed from the task list.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Noted. I've removed this task:
[D][ ] project (by: Sep 30 2021 1200)
Now you have 0 tasks in the list.
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `Done` - Marks a task complete.

Marks a specified task as completed.

Example of usage: 

`done 1`

Expected outcome:

The task selected is marked as completed.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Nice! I've marked this task as done:
[D][X] project (by: Sep 30 2021 1200)
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `Event` - Creates an Event.

Creates an event in the task list.

Example of usage: 

`event open house /at 15/10/2021 0900`

Expected outcome:

The event named open house is created with date and time 15/10/2021 0900.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Got it. I've added this task:
[E][ ] open house (at: Oct 15 2021 0900)
Now you have 2 tasks in the list.
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `Find` - Search for tasks.

Finds all tasks with the specified word.

Example of usage: 

`find open`

Expected outcome:

The event named open house is returned.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Here are the tasks in your list:
1.[E][ ] open house (at: Oct 15 2021 0900)
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `Help` - Displays the help message.

Shows the description and syntax of all commands.

Expected outcome:

The description and syntax of the commands are returned.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Welcome to the help page.
Bye: Exits the program.
Deadline: Adds a task with deadline. Command Syntax: deadline <name> /by <date in dd/MM/YYYY HHmm>
Delete: Deletes a task. Command Syntax: delete <task id>
Done: Marks a task as completed. Command Syntax: done <task id>
Event: Creates an event. Command Syntax: event <event name> /at <date in dd/MM/YYYY HHmm>
Find: Finds all tasks with the specified word. Command Syntax: find <string to search>
Help: Displays the help message.
List: Lists all tasks.
Todo: Creates a Todo task. Command Syntax: todo <task name>
What's On: Lists all tasks occurring on the specified day. Command Syntax: whatson <date in dd/MM/YYYY>
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `List` - List all tasks.

List all tasks in the task list.

Expected outcome:

All tasks in the task list are listed.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Here are the tasks in your list:
1.[D][X] project (by: Sep 30 2021 1200)
2.[E][ ] open house (at: Oct 15 2021 0900)
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `Todo` - Creates a Todo list.

Creates an Todo in the task list.

Example of usage: 

`todo buy bread`

Expected outcome:

The task buy bread is added to the task list.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Got it. I've added this task:
[T][ ] buy bread
Now you have 3 tasks in the list.
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```

### `What's On` - List tasks on a day.

Lists all tasks occuring on the specified date.

Example of usage: 

`whatson 15/10/2021`

Expected outcome:

The event open house is printed.

```
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Here are the tasks in your list:
1.[E][ ] open house (at: Oct 15 2021 0900)
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
```
