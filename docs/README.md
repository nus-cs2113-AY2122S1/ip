# User Guide for Duke

## What is Duke?

Duke is a command line interface application
for users to keep track of tasks that need to be done.
These tasks include to-dos, deadlines and events. You can
search for specific tasks using keywords, and mark them as
done, or delete them entirely. 

## Features 

### Add To-Do

Adds a to-do to your list of tasks. 

### Add Deadline

Adds a deadline to your list of tasks. 

### Add Event

Adds an event to your list of tasks. 

### List Tasks

Lists out all the tasks you have. 

### Search for Tasks

Finds task by searching for a keyword. 

### Mark Task as Done

Marks a task as done. 

### Remove Task

Removes a task from your list of tasks. 

### Exit Application

Exits the application. 

## Usage

### `todo <description>` - Add To-Do

Adds a to-do to your list of tasks and shows you the to-do added. 

Example of usage: 

`todo read book`

Expected outcome:

```
Got it. I've added this task: 
  [T][ ] read book
Now you have 1 task in the list. 
```

### `deadline <description> /by <due date>` - Add Deadline

Adds a deadline with a due date to your list of tasks
and shows you the deadline added.

Example of usage:

`deadline return book /by 2 Oct 6pm`

Expected outcome:

```
Got it. I've added this task: 
  [D][ ] return book (by: 2 Oct 6pm)
Now you have 2 tasks in the list. 
```

### `event <description> /at <event time>` - Add Event

Adds an event with an event time to your list of tasks
and shows you the event added.

Example of usage:

`event CS1010 midterms /at 4 Oct 10am-12pm`

Expected outcome:

```
Got it. I've added this task: 
  [E][ ] CS1010 midterms (at: 4 Oct 10am-12pm)
Now you have 3 tasks in the list. 
```

### `list` - List Tasks

Lists out all the tasks you have. 

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list: 
1.[T][ ] read book
2.[D][ ] return book (by: 2 Oct 6pm)
3.[E][ ] CS1010 midterms (at: 4 Oct 10am-12pm)
```

### `find <keyword>` - Search for Tasks

Finds task by searching for a keyword and shows you
all tasks containing the keyword. 

Example of usage:

`find book`

Expected outcome: 

```
Here are the matching tasks in your list: 
1.[T][ ] read book
2.[D][ ] return book (by: 2 Oct 6pm)
```

### `done <task number>` - Mark Task as Done

Marks a task as done and shows you that the task is checked. 

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done: 
  [T][X] read book
```

### `delete <task number>` - Remove Task

Removes a task from your list of tasks and shows the task removed.

Example of usage:

`delete 3`

Expected outcome:

```
Noted. I've removed this task: 
  [E][ ] CS1010 midterms (at: 4 Oct 10am-12pm)
Now you have 2 tasks in the list. 
```

### `bye` - Exit Application

Exits the application. 

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```