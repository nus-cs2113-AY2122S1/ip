# Esteban User Guide

## Features 

### Track Tasks

Estaban helps users to track their tasks in the form of todos, deadlines and events

### Specify Dates

Dates can be added to easily track when something is occurring or when it is due

### Mark as Done

Tasks can be marked as completed

### Filerting of Tasks

Tasks can be easily filtered based on a search string. Tasks occuring today can also be easily listed!

### Persistent Storage

Program data is continuously updated and persists beyond exiting the program so no data is lost!

## Usage

### `/todo <description>` - Create a new ToDo

Add a todo task with a description.

Example of usage: 

`/todo complete readings`

Expected outcome:

Overview of newly added task and current number of tasks.

```
> /todo complete readings
  (+) Added: [t] [ ] complete readings
  (i) You have 1 tasks in the list
```

### `/deadline <description> -by <dd/mm/yyyy hhmm>` - Create a new Deadline

Add a deadline task with a description and due date.

Example of usage:

`/deadline assignment 1 -by 26/09/2021 2359`

Expected outcome:

Overview of newly added task and current number of tasks.

```
> /deadline assignment 1 -by 26/09/2021 2359
  (+) Added: [d] [ ] assignment 1 (By: 26/09/2021 2359)
  (i) You have 2 tasks in the list
```

### `/event <description> -from <dd/mm/yyyy hhmm> -to <dd/mm/yyyy hhmm>` - Create a new Event

Add an event task with a description and starting and ending times.

Example of usage:

`/event lecture -from 27/09/2021 1400 -to 27/09/2021 1600`

Expected outcome:

Overview of newly added task and current number of tasks.

```
> /event lecture -from 27/09/2021 1400 -to 27/09/2021 1600
  (+) Added: [e] [ ] lecture (27/09/2021 1400 to 27/09/2021 1600)
  (i) You have 3 tasks in the list
```

### `/list>` - Show all tasks

Show all tasks.

Example of usage:

`/list`

Expected outcome:

Overview of all tasks which include their types, status, description, due date and time (if applicatble), starting and
ending time (if applicable).

```
> /list
------------------------------------------------------------
1. [t] [ ] complete readings
2. [d] [ ] assignment 1 (By: 26/09/2021 2359)
3. [e] [ ] lecture (27/09/2021 1400 to 27/09/2021 1600)
------------------------------------------------------------
```

### `/today` - Show all tasks occurring today

Shows a list of deadlines that are due today and events that are starting today

Example of usage:

`/today`

Expected outcome:

Overview of tasks that are occuring today

```
> /today
------------------------------------------------------------
[d] [X] assignment 1 (By: 26/09/2021 2359)
[e] [ ] tutorial (26/09/2021 1500 to 26/09/2021 1630)
------------------------------------------------------------
```

### `/find <searchTerm>` - Show all tasks with descriptions containing the search term

Shows a list of tasks with descriptions containing the search term

Example of usage:

`/find asignment`

Expected outcome:

Overview of tasks that match the search term

```
> /find assignment
------------------------------------------------------------
[d] [X] assignment 1 (By: 26/09/2021 2359)
[d] [ ] assignment 2 (By: 01/11/2021 2359)
------------------------------------------------------------
```

### `/done <taskID>` - Mark as Done

Mark a task as complete based on their identifier (obtained from `/list`)

Example of usage:

`/done 3`

Expected outcome:

Overview of task that has been marked as done.

```
> /list
------------------------------------------------------------
1. [d] [X] assignment 1 (By: 26/09/2021 2359)
2. [e] [ ] lecture (27/09/2021 1400 to 27/09/2021 1600)
3. [e] [ ] tutorial (26/09/2021 1500 to 26/09/2021 1630)
4. [d] [ ] assignment 2 (By: 01/11/2021 2359)
------------------------------------------------------------
> /done 3
  (+) Marked as Done: [e] [X] tutorial (26/09/2021 1500 to 26/09/2021 1630)
```

### `/delete <taskID>` - Mark as Done

Delete a task based on their identifier (obtained from `/list`)

Example of usage:

`/delete 1`

Expected outcome:

Overview of task that has beendeleted.

```
> /list
------------------------------------------------------------
1. [t] [ ] complete readings
2. [d] [X] assignment 1 (By: 26/09/2021 2359)
3. [e] [ ] lecture (27/09/2021 1400 to 27/09/2021 1600)
4. [e] [ ] tutorial (26/09/2021 1500 to 26/09/2021 1630)
5. [d] [ ] assignment 2 (By: 01/11/2021 2359)
------------------------------------------------------------
> /delete 1
  (-) Removed: [t] [ ] complete readings
  (i) You have 4 tasks in the list
```

### `/bye` - Exits the program

Quit the program

Example of usage:

`/bye`

Expected outcome:

Goodbye message

```
> /bye
Gracias! Hope to see you again soon!
```