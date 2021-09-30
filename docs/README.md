# User Guide

## Features 

### Feature-Add tasks

Add "todo", "deadline" or "event" tasks to the task list.

### Feature-Mark as done

Mark a specific task in the task list as done

### Feature-Delete tasks

Delete a specific task in the task list

### Feature-Find tasks

Find tasks with the keyword

## Usage

### `todo` - Add a todo task

Add a todo task to the task list.

`todo DESCRIPTION`

Example of usage: 

`todo make a call`

Expected outcome:

A todo task added.

```
      _________________________________
     |Got it. I've added this task:    |
     |[T][ ]make a call                |
     |Now you have 1 tasks in the list.|
     |_________________________________|
```

### `deadline` - Add a deadline task

Add a deadline task to the task list.

`deadline DESCRIPTION /by TIME`

Example of usage: 

`deadline finish the task /by tomorrow`

Expected outcome:

A deadline task added.

```
      ____________________________________
     |Got it. I've added this task:       |
     |[D][ ]finish the task(by:  tomorrow)|
     |Now you have 1 tasks in the list.   |
     |____________________________________|
```

### `event` - Add a event task

Add a event task to the task list.

`event DESCRIPTION /at TIME`

Example of usage: 

`event go for big meal /at 6pm`

Expected outcome:

A event task added.

```
      _________________________________
     |Got it. I've added this task:    |
     |[E][ ]go for big meal(at:  6pm)  |
     |Now you have 1 tasks in the list.|
     |_________________________________|
```

### `done` - Mark a task as done

Mark the task with a specific index number as done.

`done NUMBER`

Example of usage: 

`done 1`

Expected outcome:

The first task in the list marked as done.

```
      _____________________________________
     |Nice! I've marked this task as done: |
     |[E][X]go for big meal(at:  6pm)      |
     |_____________________________________|
```

### `delete` - Delete a task

Delete the task with the specific index number.

`delete NUMBER`

Example of usage: 

`delete 1`

Expected outcome:

The first task is deleted from the list.

```
      _________________________________
     |Noted. I've removed this task:   |
     |[E][X]go for big meal(at:  6pm)  |
     |Now you have 0 tasks in the list.|
     |_________________________________|
```

### `list` - List all the tasks

List all the tasks in the list.

`list`

Example of usage: 

`list`

Expected outcome:

The whole list of tasks.

```
      _______________________________________
     |Here are the tasks in your list:       |
     |1. [T][ ]make a call                   |
     |2. [D][ ]finish the task(by:  tomorrow)|
     |3. [E][ ]go for big meal(at:  6pm)     |
     |_______________________________________|
```

### `find` - Find tasks with the keyword

The tasks with the keyword will be found.

`find KEYWORD`

Example of usage: 

`find meal`

Expected outcome:

Description of the outcome.

```
      _________________________________________
     |Here are the tasks in your list:         |
     |1. [E][ ]go for big meal(at:  6pm)       |
     |_________________________________________|
```

### `bye` - Exit programme

The programme exit with saving the list to the file.

`bye`

Example of usage: 

`bye`

Expected outcome:

Greeting from the programme.

```
      ________________________________
     |Bye. Hope to see you again soon!|
     |________________________________|
```
