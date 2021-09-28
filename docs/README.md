# User Guide

## Features 

### Add Interactive Custom Tasks

Choose from an array of customizable tasks (deadline, event, and to-do), mark them as done, delete tasks and more.

### Autosave and Autoload

Save and load tasks automatically without any hassle.

## Usage

### `list` - Shows all tasks.

Example of usage: 

```
list
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][X] read book
     2.[D][X] return book (by: June 6th)
     3.[E][ ] project meeting (at: Aug 6th 2-4pm)
     4.[T][X] join sports club
     5.[T][ ] borrow book
    ____________________________________________________________
```

### `done` - Marks a specific task as done.

### Format: 

```
done (index)
```

Example of usage: 

```
done 2
    ____________________________________________________________
     Nice! I've marked this task as done: 
       [T][X] return book
    ____________________________________________________________
```

### `todo` - Adds a task without any date/time attached to it.

### Format: 

```
todo (task)
```

Example of usage: 

```
todo borrow book
    ____________________________________________________________
     Got it. I've added this task: 
       [T][ ] borrow book
     Now you have 5 tasks in the list.
    ____________________________________________________________
```

### `deadline` - Adds a task that needs to be done before a specific date/time.

### Format: 

```
deadline (task) /by (date/time)
```

Example of usage: 

```
deadline return book /by Sunday
    ____________________________________________________________
     Got it. I've added this task: 
       [D][ ] return book (by: Sunday)
     Now you have 6 tasks in the list.
    ____________________________________________________________
```

### `event` - Adds a task that starts at a specific time and ends at a specific time.

### Format: 

```
event (task) /at (date/time)
```

Example of usage: 

```
event project meeting /at Mon 2-4pm
    ____________________________________________________________
     Got it. I've added this task: 
       [E][ ] project meeting (at: Mon 2-4pm)
     Now you have 7 tasks in the list.
    ____________________________________________________________
```

### `find` - Finds tasks containing a specific keyword.

### Format: 

```
find (keyword)
```

Example of usage: 

```
find book
    ____________________________________________________________
     Here are the matching tasks in your list:
     1.[T][X] read book
     2.[D][X] return book (by: June 6th)
    ____________________________________________________________
```

### `delete` - Deletes a task.

### Format: 

```
delete (index)
```

Example of usage: 

```
delete 3
    ____________________________________________________________
     Noted. I've removed this task: 
       [E][ ] project meeting (at: Aug 6th 2-4pm)
     Now you have 4 tasks in the list.
    ____________________________________________________________
```

### `bye` - Terminates the program.

Example of usage: 

```
bye
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```
