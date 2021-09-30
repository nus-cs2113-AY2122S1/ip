# User Guide


                     |___  | | | |_____|  / / -- \ \
                        / /  | |    / /  | |      | | 
                       / /   | |   / /   | |      | |
                      / /___ | |  /_/__  | |  --  | | 
                     |_____| | | |_____|  \ \____/ /

Zizo is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).
If you can type fast, Zizo can get your task management done faster than traditional GUI apps.
## Features
    private static final int CMD_FIND = 8;
    private static final int CMD_TERMINATE = 0;
### Add Todo

Adds a **Todo** task

### Add Event

Adds an **Event** task

### Add Deadline

Adds a **Deadline** task

### List

Show all the tasks in a ordered list
### Done

Mark a specific task as completed
### Delete

Remove a specific task from the tasks list
### Show Date

Show all tasks with the given deadline
### Find

Search for a tasks given a keyword
### Terminate

Ends the program

## Usage

### `todo` - Adds a todo task to the tasks list

Example of usage: 

`todo Read a book`


###Expected output
```
____________________________________________________________

Got it. I've added this task:
[T][ ]Read a book
Now you have 1 tasks in the list.
____________________________________________________________
```
### `deadline` - Adds a deadline task to the tasks list


Example of usage:

`deadline Read a book /by 2021/09/25 1800`


###Expected output
```
____________________________________________________________

Got it. I've added this task:
[D][ ]Read a book (by: 25 Sep 2021, 6 00 PM)
Now you have 1 tasks in the list.
____________________________________________________________
```
### `event` - Adds a event task to the tasks list


Example of usage:

`event Read a book /at 2021/09/25 1800`

###Expected output
```
____________________________________________________________

Got it. I've added this task:
[E][ ]Read a book (by: 25 Sep 2021, 6 00 PM)
Now you have 1 tasks in the list.
____________________________________________________________
```
### `list` - Shows a list of tasks


Example of usage:

`list`


###Expected output
```
____________________________________________________________

Here are the task in your list:
1.[D][ ]Read a book (by: 25 Sep 2021, 6 00 PM)
____________________________________________________________
```
### `done` - Mark a task as done

Selects which task base on its index in the list to mark as done

Format: done (Task index)
* Index must be a positive integer

Example of usage:

`done 1`

Expected outcome:
```
   Here are the task in your list:
1. [D][X]Read a book (by: 25 Sep 2021, 6 00 PM)
```

###Expected output
```
____________________________________________________________

Nice! task is done 
____________________________________________________________
```

### `delete` - Delete task

Selects which task to delete based on its index in the tasks list

Format: delete (Task Index)
* Index must be a positive integer


Example of usage:

`delete 1`


###Expected output
```
____________________________________________________________
Noted. I've removed this task:
[D][X]Read a book (by: 25 Sep 2021, 6 00 PM)
Now you have 0 tasks in the list.
____________________________________________________________
```

### `show date` Show tasks based on date

Show tasks with the specific deadline

Example of usage:

`show date (date in yyyy/MM/dd format)`

`show date 2012/12/12`


###Expected output
```
____________________________________________________________
Here are the all the task in your list to be done by:13 Mar 1999
[E][X] collect book (at: 13 Mar 1999, 01 00 PM)
____________________________________________________________
```

### `find` - Search for a task

Show tasks with the specific deadline

Example of usage:

`find (keyword of task)`

`find book`

###Expected output
```
____________________________________________________________
Here are the all the task in your list with keyword: book
1.[D][ ] write book (by: 12 Mar 1999, 12 00 PM)
2.[T][ ] read book

____________________________________________________________
____________________________________________
```
### `Bye` - Ends the program

End the program

Example of usage:

`bye`

###Expected output
```
____________________________________________________________
chat again next time!
____________________________________________________________
```
