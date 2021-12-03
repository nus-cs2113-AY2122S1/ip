//@@author

# User Guide

## Features 

1. add tasks
2. save tasks 
3. load tasks 
4. mark task as done
5. delete tasks 
6. search tasks

### Add tasks

Duke can add various types of tasks:
1. todo
2. deadline
3. event

### save tasks

Duke can save list of tasks to your hard disk.

### load tasks

Duke can load list of tasks from your hard disk.

### mark task as done

Duke can mark a task as done in list of tasks.

### delete tasks

Duke can delete a task from list of tasks and update the data in your hard disk.

### search tasks

Duke can search all the tasks containing a keyword in the list of tasks.

## Usage

### `list` - show list of tasks

display the list of tasks

Example of usage: 

`list`

Expected outcome:

the content of the list.

```
1. [ ] todo 1
2. [ ] todo 2
```

### `delete (# of task)` - delete a task

delete a task in the list of tasks

Example of usage:

`delete 1`

Expected outcome:

Before
```
1. [ ] todo 1
2. [ ] todo 2
```
After
```
1. [ ] todo 2
```
### `done (# of task)` - mark a task as done

mark a task as done in the list of tasks

Example of usage:

`done 1`

Expected outcome:

Before
```
1. [ ] todo 1
2. [ ] todo 2
```
After
```
1. [X] todo 1
2. [ ] todo 2
```
