# User Guide

## Command list 

### `todo` - adds todo task
### `deadline` - adds deadline task
### `event` - adds event task
### `list` - lists all task
### `find` - find matching tasks
### `done` - completes task
### `delete` - deletes task
### `bye` - exits program

## Usage

### `todo` - adds todo task

Adds todo task to the list

Example of usage: 

`todo Complete CS2113 lecture quiz`

Expected outcome:

Displays successfully addition of todo task to the list and the total number of tasks in the list.

```
Kao got it! (  ｡•̀ᴗ-)✧ Kao has added this task:
Complete CS2113 lecture quiz
Now you have 1 tasks in the list.
```

### `deadline` - adds deadline task

Adds deadline task to the list

Example of usage:

`deadline Complete iP increment /by Thursday 2359`

Expected outcome:

Displays successfully addition of deadline task to the list and the total number of tasks in the list.

```
Kao got it! (  ｡•̀ᴗ-)✧ Kao has added this task:
Complete iP increment 
Now you have 2 tasks in the list.
```

### `event` - adds event task

Adds event task to the list

Example of usage:

`event Attend CS2113 lecture /at Friday 1400`

Expected outcome:

Displays successfully addition of event task to the list and the total number of tasks in the list.

```
Kao got it! (  ｡•̀ᴗ-)✧ Kao has added this task:
Attend CS2113 lecture 
Now you have 3 tasks in the list.
```

### `list` - lists all task

Lists the entire task list

Example of usage:

`list`

Expected outcome:

Displays the entire task list

```
Right away! ε=ε=┌( >_<)┘ 
Kao retrieved the tasks in your list:
1. [T][ ] Complete CS2113 lecture quiz
2. [D][ ] Complete iP increment  (by: Thursday 2359)
3. [E][ ] Attend CS2113 lecture (at: Friday 1400)
```

### `find` - find matching tasks

Finds matching tasks in the list

Example of usage:

`find CS2113`

Expected outcome:

Display tasks in the task list with the matching keyword

```
Kao got it! ε=ε=┌( `ー´)┘ 
Kao retrieved the matching tasks in your list:
1. [T][ ] Complete CS2113 lecture quiz
3. [E][ ] Attend CS2113 lecture (at: Friday 1400)
```

### `done` - completes task

Marks task as complete

Example of usage:

`done 1`

Expected outcome:

Displays successful completion of the corresponding task

```
Nice job! (  >ω<)☆ Kao marked this task as done:
Complete CS2113 lecture quiz
```

### `delete` - deletes task

Deletes task from the list

Example of usage:

`delete 1`

Expected outcome:

Displays successful deletion of the corresponding task

```
Alright! ( ・∀・ )ノ Kao removed this task:
Complete CS2113 lecture quiz
Now you have 2 tasks in the list.
```

### `bye` - exits program

Exits and closes the program

Example of usage:

`bye`

Expected outcome:

Shows successful termination of the program

```
Bye bye! Kao will be waiting for you ｡:ﾟ( ´°ω°｀ )ﾟ:｡
```