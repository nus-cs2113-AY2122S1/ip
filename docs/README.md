# User Guide
Duke is your best friend when it comes to keeping track of important dates and tasks. 
Duke uses a command line interface (CLI) for you to efficiently input your 
important dates and tasks. 
****
## Features

### Add Task
#### Add a basic Task `Todo` 
Adds a basic Task to your  list. A basic Task only contains a description.

Format: `todo [TASK DESCRIPTION]`

Example of usage: `todo read book`

Expected outcome:

```
Got it. I've added this task: 
[T][ ]read book
Now you have 1 tasks in the list
```
### Add Event
#### Add a basic Event `event`
Adds an Event to your  list. An Event is a task with a Description as well as a 
start date and start time.

Format: `event [EVENT DESCRIPTION] /at [YEAR-MONTH-DAY] [TIME]`

Example of usage: `event study /at 2021-09-11 0800`

Expected outcome:

```
Got it. I've added this task: 
[E][ ] study at: Sep 11 2021 0800
Now you have 2 tasks in the list
```
### Add Deadline
#### Add a basic Deadline `deadline`
Adds a Deadline to your  list. A Deadline is a task with a Description as well as a
due date and due time.

Format: `deadline [DEADLINE DESCRIPTION] /by [YEAR-MONTH-DAY] [TIME]`

Example of usage: `deadline buy snacks /by 2021-09-11 0800`

Expected outcome:

```
Got it. I've added this task: 
[D][ ] buy snacks by Sep 11 2021 0800
Now you have 3 tasks in the list
```
### Mark tasks as done
#### Mark a task as done `done `
Marks selected task as done.

Format: `done [TASK INDEX]`

Example of usage: `done 1`

Expected outcome:

```
Got it. I've marked this task as done: 
[T][X]read book
Now you have 3 tasks in the list
```
#### See list of Tasks `list`
See full list of Tasks as well as their statuses.

Format: `list`

Example of usage: `list`

Expected outcome:

```
List so far: 
1[T][X]read book
2[E][ ] study at: Sep 11 2021 0800
3[D][ ] buy snacks by: Sep 11 2021 0800
```
### Removing a Task
#### Deletes a Task form the list `delete`
Deletes selected task from the list.

Format: `delete [TASK INDEX]`

Example of usage: `delete 1`

Expected outcome:

```
Got it. I've deleted this task: 
[T][X]read book
Now you have 2 tasks in the list
```
### Searching for a Task using a keyword
#### Searches for Task with contains keyword `find`
Finds a Task which description contains the keyword.

Format: `find [KEYWORD]`

Example of usage: `find study`

Expected outcome:

```
Tasks with keyword: study
1[E][ ] study at: Sep 11 2021 0800
2[T][ ]study econs
```
### Closing Duke
#### Closes the application `bye`
Shuts down Duke and saves all your tasks for the next time.

Format: `bye`

Example of usage: `bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
****
## Command Summary
Action | Format | Examples
--------|--------|--------
Add Task | `todo [TASK DESCRIPTION]` | `todo read book`
Add deadline | `deadline [DEADLINE DESCRIPTION] /by [YEAR-MONTH-DAY] [TIME]` | `deadline buy snacks /by 2021-09-11 0800`
Add event |`event [EVENT DESCRIPTION] /at [YEAR-MONTH-DAY] [TIME]` | `event study /at 2021-09-11 0800`
List | `list` | `list`
Remove task | `delete [TASK INDEX] ` |  `delete 1`
Mark task as done | `done [TASK INDEX]` | `done 1`
Find Task| `find [KEYWORD]` | `find study`
Bye | `bye` | `bye`