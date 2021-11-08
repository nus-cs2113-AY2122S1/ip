# Duke User Guide
Duke is a command line task tracking application that helps you keep track of your daily tasks.

## Getting Started
1. Download JRE11.
2. Download the latest release `Duke.jar` file.
3. In the directory containing the `Duke.jar` file, run `java -jar Duke.jar`.

## Features
* Add a task
* Delete a task
* Mark a task as done
* Find a task
* List all tasks
* Exit Duke

### List

Calls a function to list all current tasks, deadlines and events.


### Remove

Removes a current task from the list by calling the appropriate index.


### Done

Marks a current task from the list as completed by calling the appropriate index.


### Find

Finds a particular phrase or sub-phrase among all current tasks.


### Bye

Exits Duke.



## Usage

### `t` - Creates a Task

Creates an task alongside a task name.

Example of usage:

`t eat bread`

Expected outcome:

Description of the outcome.

```
t eat bread
Gotcha! I've added this todo

list
Here are the tasks in your list:
1. [T] [ ]  eat bread
```

### `d` - Creates a Deadline

Creates a deadline alongside a due time.

Example of usage:

`d complete 2113T code /by Sun 11pm`

Expected outcome:

Description of the outcome.

```
d complete 2113T code /by Sun 11pm
Gotcha! I've added this deadline

list
Here are the tasks in your list:
1. [T] [ ]  eat bread
2. [D] [X]  complete 2113T code  (by:  Sun 11pm)
```


### `e` - Creates an Event

Creates an event alongside a time when the event occurs.

Example of usage:

`e meet friends for dinner /at Sat 6pm`

Expected outcome:

Description of the outcome.

```
e meet friends for dinner /at Sat 6pm
Gotcha! I've added this event

list
Here are the tasks in your list:
1. [T] [ ]  eat bread
2. [D] [X]  complete 2113T code  (by:  Sun 11pm)
3. [E] [ ]  meet friends for dinner  (at:  Sat 6pm)
```


### `List` - Lists all current tasks

Lists all current tasks.

Example of usage: 

`list`

Expected outcome:

Description of the outcome.

```
Here are the tasks in your list:
1. [T] [ ]  eat bread
2. [D] [ ]  complete 2113T code  (by:  Sun 11pm)
3. [E] [ ]  meet friends for dinner  (at:  Sat 6pm)
```


### `Done` - Marks a task as done

Marks a current task from the list as completed by calling the appropriate index.

Example of usage:

`done 2`


Expected outcome:

Description of the outcome.

```
done 2
Nice! I've marked the task as done!

list
Here are the tasks in your list:
1. [T] [ ]  eat bread
2. [D] [X]  complete 2113T code  (by:  Sun 11pm)
3. [E] [ ]  meet friends for dinner  (at:  Sat 6pm)

```

### `Find` - Finds a particular task keyword or phrase

Finds a particular phrase or sub-phrase among all current tasks.

Example of usage:

`find bread`

Expected outcome:

Description of the outcome.

```
find bread
Here are the matching tasks in your list!
bread
1. [T] [ ]  eat bread

```

### `Remove` - Removes a task

Removes a current task from the list by calling the appropriate index, 
and shifts all subsequent tasks one index up.

Example of usage:

`remove 2`

Expected outcome:

Description of the outcome.

```
remove 2
Removing to do list item boss!

list
Here are the tasks in your list:
1. [T] [ ]  eat bread
2. [E] [ ]  meet friends for dinner  (at:  Sat 6pm)

```

### `Bye` - Exits Duke

Exits Duke.

Example of usage:

`bye`

Expected outcome:

Description of the outcome.

```
bye
Bye. Hope to see you again soon!
```

