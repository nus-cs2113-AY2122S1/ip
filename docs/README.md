# User Guide

## Features 

### 1. Add tasks

User can add 3 different types of tasks: Todo, Event, Deadline.

### 2. Show task list

Duke can show the task list which edited by User

### 3. Delete tasks

User can delete tasks which is added before

### 4. Find tasks

User can find tasks which existed in the task list by searching keywords.

### 5. Mark tasks as Done

User can mark tasks as done, which will display a tick in the box in front of the description of that task.

Description of the feature.

## Usage

### `todo/deadline/event / yyyy-mm-dd` - Add tasks to the list

User can add tasks to the the list.

Example of usage: 

`deadline return book / 2002-02-01`

```
Got it. I've added this task:
[D][] return book 2002-02-01
Now you have 1 tasks in the list
Description of the outcome.
```
### `list` - Show the current task list

User get the current list.

Example of usage: 

`list`

```
[D] [ ] return book  (Feb 1 2002)

```

### `delete [order number]` - delete the task

User delete a task in the task list.

Example of usage: 

`delete 1`

```
Noted I've already delete it

```
### `find [keywords]` - find tasks which include keywords

User can find tasks by keywords.

Example of usage: 

`find book`

```
find book
[T] [ ] return book
[T] [ ] buy book

```
### `done [order number]` - mark a task as done

User can mark a task as done

Example of usage: 

`done 2`

```
Nice Work! I've marked it as done
```
