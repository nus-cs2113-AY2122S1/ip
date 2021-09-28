# User Guide

## Features 

### Add Interactive Custom Tasks

Choose from an array of customizable tasks (deadline, event, and to-do), mark them as done, delete tasks and more.

### Autosave and Autoload

Save and load tasks automatically without any hassle.

## Usage

### `done` - Marks a specific task as done.

### Format: 

```
done (index)
```

Example of usage: 

```
list
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][X] read book
     2.[T][ ] return book
     3.[T][ ] buy bread
    ____________________________________________________________

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
