# Duke User Guide

Duke is a local task manager

## How To Run

download the ip.jar file from releases <br />
run `java -jar ip.jar`

## Features 

### Tasks

Duke comes with 3 types of tasks (todo, deadline, event), with the ability to mark each as done
<br />

### Storage

Duke saves created tasks in a local text file as storage, allowing tasks to be maintained between sessions
<br />

### User Interface

Duke displays information in an easy-to-read manner
<br />

### Search

Duke can search for specific tasks
<br />

## Usage

### `list` - Describe action

lists all tasks details recorded in numbered format

Example of usage: 

`list`

Expected outcome:

Lists all tasks and their corresponding details in numbered format

```
Here are the tasks in your list:
     1.[T][ ] borrow book
     2.[D][ ] return book (by: June 6th)
     3.[E][ ] project meeting (at: Aug 6th 2-4pm)
```

<br />

### `todo <description>` - Adds a todo task item

Adds a todo task item to the list of tasks

Example of usage: 

`todo borrow book`

Expected outcome:

Duke shows a success message along with the details of the todo task added and cumulative number of tasks

```
Got it. I've added this task: 
    [T][ ] borrow book
Now you have 1 task in the list.
```

<br />

### `deadline <description> /by <datetime>` - Adds a deadline task item

Adds a deadline task item to the list of tasks

Example of usage: 

`deadline return book /by June 6th`

Expected outcome:

Duke shows a success message along with the details of the deadline task added and cumulative number of tasks

```
Got it. I've added this task: 
    [D][ ] return book (by: June 6th)
Now you have 2 tasks in the list.
```

<br />

### `event <description> /at <datetime>` - Adds an event task item

Adds a deadline task item to the list of tasks

Example of usage: 

`event project meeting /at Aug 6th 2-4pm`

Expected outcome:

Duke shows a success message along with the details of the event task added and cumulative number of tasks

```
Got it. I've added this task: 
    [E][ ] project meeting (at: Aug 6th 2-4pm)
Now you have 3 tasks in the list.
```

<br />

### `done <index>` - Marks a task as completed

Marks a task as completed, specified by index counting from 1

Example of usage: 

`done 2`

Expected outcome:

Duke shows a success message along with the details of the completed task (makred with an "X")

```
Nice! I've marked this task as done: 
    [D][X] return book (by: June 6th)
```

<br />

### `delete <index>` - Removes a task from the list of tasks

Removes a task from the list of tasks, specified by index counting from 1

Example of usage: 

`delete 1`

Expected outcome:

Duke shows a success message along with the details of the task removed and remaining number of tasks

```
Noted. I've removed this task:
    [T][ ] borrow book
Now you have 2 tasks in the list.
```

<br />

### `find <phrase>` - Search for matching tasks

Search for tasks whose decriptions match the search phrase

Example of usage: 

`search book`

Expected outcome:

Duke shows a success message along with the details of matching tasks in numbered format

```
Here are the matching tasks in your list:
    1.[T][ ] borrow book
    2.[D][X] return book (by: June 6th)
```

<br />

### `bye` - Exits Duke

Example of usage: 

`bye`

Expected outcome:

Duke shows a goodbye message

```
Bye. Hope to see you again soon!
```