# User Guide

## Features 

### 1. Add tasks

 Add three types of tasks to the list (todo, deadline,event)

### 2. List tasks

 Display all the tasks in the list

### 3. Delete task
 Delete a task from the list with a given index

### 4. Mark task as done
 Mark a task as done with a given index

### 5. Find task
 Find a task in the list with a given key word

### 6. clear all the tasks
 Clear all the tasks in the list


## Usage

### 1. `todo` - add a simple task to the list

Format: `todo DESCRIPTION`

Example of usage: 

`todo do the online quiz`

Expected outcome:

```
Got it. I have added the task to your list.
[T][ ] do the online quiz
```

You have added the task to the list successfully.

### 2. `deadline` - add a deadline to the list

Format: `deadline DESCRIPTION /by TIME`

Example of usage:

`deadline submit individual project /by 1 Oct 23:59`

Expected outcome:

```
Got it. I have added the task to your list.
[D][ ] submit individual project (by: 1 Oct 23:59)
```

You have added the deadline to the list successfully.

### 3. `event` - add a event to the list

Format: `event DESCRIPTION /at TIME`

Example of usage:

`event online programming workshop /at Tuesday`

Expected outcome:

```
Got it. I have added the task to your list.
[E][ ] online programming workshop (by: Tuesday)
```

You have added the event to the list successfully.

### 4. `list` - list all the tasks

Format: `list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] read books
2. [E][X] online meeting (at: 30 Sept 22:00)
```

### 5. `done` - mark a task as done

Format: `done TARGET_INDEX`

Example of usage:

`done 1`

Expected outcome:

```
Nice, I have marked this task as done:
[T][X] read books
```
The first task in the list has been marked as done

### 6. `delete` - delete a task from the list

Format: `delete TARGET_INDEX`

Example of usage:

`delete 2`

Expected outcome:

```
Got it. I have deleted the tasks in your list!
[E][X] online meeting (at: 30 Sept 22:00)
```
The second task in the list has been deleted

### 7. `clear` - clear all the tasks

Format: `clear`

Expected outcome:

```
The tasks in your list have been cleared.
```
The list is empty now

### 8. `find` - find a task containing the given key word(s)

Format: `find TARGET`

Example of usage:

`find read`

Expected outcome:

```
Here are the matching tasks in your list:
[T][ ] read books
[D][X] read lab manual (by: today 22:00)
```
All the tasks containing the key word have been displayed

### 9. `help` - show help information

Format: `help`

Expected outcome:

```
Here are the usage of all commands:

deadline: add a deadline task to the current list.
 Parameters: DESCRIPTION, BY_TIME
 Example: deadline take the quiz /by Tuesday

...(omitted)

list: display all tasks in the current list.
 Example: list
```
The usages of all commands have been displayed

### 10. `bye` - exit the program

Format: `bye`

Expected outcome:

```
Bye. Hope to see you soon~
```
The program have been terminated, and the changes in each step have been saved, so you can
load the task list record when you start the program next time : )

