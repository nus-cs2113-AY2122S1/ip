# Atlas User Guide
Atlas is a personal task manager which manages tasks, deadlines and events, optimised for use via a Command
Line Interface (CLI).


## Setting up

Prerequisites: JDK 11, update IntelliJ to the most recent version.


## Using Atlas

When you run Atlas, you should see the following message:
```
Hello from
    #    ####### #          #     #####
   # #      #    #         # #   #     #
  #   #     #    #        #   #  #
 #     #    #    #       #     #  #####
 #######    #    #       #######       #
 #     #    #    #       #     # #     #
 #     #    #    ####### #     #  #####
____________________________________________________________
Hello! I'm Atlas!
What can I do for you today?
____________________________________________________________
```

## Features

>Notes about the command format:
> 
>- Words in `UPPER_CASE` are the parameters to be supplied by the user.
>- Items in square brackets are optional.


### Adding a todo task: `todo`

Adds a todo task to the list.

Format: `todo DESCRIPTION`

Example of usage:

- `todo buy bread`


### Adding a deadline task: `deadline`

Adds a deadline task to the list. Deadlines contain a task description and a date and/or time representing a deadline.

Format: `deadline DESCRIPTION /by DATE [TIME]`

`DATE` format: **dd/mm/yyyy**

`TIME` format: **hh:mm**

Example of usage:
- `deadline return book /by 30/12/2021`
- `deadline return book /by 30/12/2021 12:00`


### Adding an event task: `event`

Adds an event task to the list. Events contain a task description and a date and/or time representing the starting time.

Format: `event DESCRIPTION /at DATE [TIME]`

`DATE` format: **dd/mm/yyyy**

`TIME` format: **hh:mm**

Example of usage:
- `event project meeting /at 30/12/2021`
- `event project meeting /at 30/12/2021 12:00`


### Marking a task as done: `done`

Marks an undone task at a specific index as done.

Format: `done TASK_INDEX`

- `TASK_INDEX` must be an integer greater than 1 and less than or equal to the total number of tasks.

Example of usage:
- `done 1`


### Marking a task as undone: `undo`

Marks a done task at a specific index as undone.

Format: `undo TASK_INDEX`

- `TASK_INDEX` must be an integer greater than 1 and less than or equal to the total number of tasks.

Example of usage:
- `undo 1`

### Listing all tasks: `list`

Shows a list of all tasks and their given fields.

Format: `list`

Expected outcome:
```
list
____________________________________________________________
Here are the tasks in your list: 
1.[T][ ] buy bread
2.[D][ ] return book (by: Dec 30 2021 12:00)
3.[E][ ] project meeting (at: Dec 30 2021 12:00)
____________________________________________________________
```

### Finding a task: `find`

Finds all tasks containing given keyword(s).

Format: `find TASK_KEYWORDS`

- All user input will be treated as lowercase e.g. `Bread` will be treated as `bread`

Example of usage:

- `find bread`


### Deleting a task: `delete`

Deletes a task at a specific index.

Format: `delete TASK_INDEX`

- `TASK_INDEX` must be an integer greater than 1 and less than or equal to the total number of tasks.

Example of usage: 

- `delete 1`


### Deleting all tasks: `delete all`

Deletes all tasks from the list.

Format: `delete all`

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data

Tasks are saved in the hard disk when you exit Atlas. They will be loaded again the next time you run Atlas and you
can view them by typing `list` after the program starts.

