# User Guide

Duke is a task manager that makes use of the command line interface. Users can add, delete, search and save different types of tasks in this application.

## Table of Contents

* [Quick Start](#quick-start)
* [Features and Usage](#features-and-usage)
    * [Add Tasks](#add-tasks)
    * [List Tasks](#list-tasks)
    * [Set status of tasks](#set-status-of-task)
    * [Search for tasks](#search-for-tasks)
    * [Delete Tasks](#delete-tasks)
    * [Exit the program](#exit-the-program)

## Quick Start

1. Ensure you have `Java 11` installed on your device.
2. Download the latest jar file [here](https://github.com/ngoivanessa/ip/releases/tag/A-Release)
3. Save it to an empty folder.
4. Open any terminal and navigate to the folder the jar is saved in.
5. Enter `java -jar duke.jar` into the terminal to run Duke. You should see the following output:
```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___
Hello! I'm Duke
What can I do for you?
------------
```

## Features and Usage

### Add tasks

You can add three types of tasks; `todo`, `deadline` and `event`.

#### Usage

For `todo`: `todo <task name>`

Example: `todo return book`

Expected outcome:

```
------------
Got it. I've added this task:
[T] [ ] return book
Now you have 1 tasks in the list
------------
```
For `deadline`: `deadline <task name> /by <date and time>`

Example: `deadline return book /by Sunday 2pm`

Expected outcome:

```
------------
Got it. I've added this task:
[D] [ ] return book  (by: Sunday 2pm)
Now you have 2 tasks in the list
------------
```

For `event`: `event <task name> /at <date and time>`

Example: `event go to library /at Saturday 3pm`

Expected outcome:

```
------------
Got it. I've added this task:
[E] [ ] go to library  (at: Saturday 3pm)
Now you have 3 tasks in the list
------------
```
### List tasks

You can view your current task list using `list`.

#### Usage

`list`

Expected outcome:
```
------------
Here are the tasks in your list:
1.[T] [ ] return book
2.[D] [ ] return book  (by: Sunday 2pm)
3.[E] [ ] go to library  (at: Saturday 3pm)
------------
```

### Set status of task

You can change the status of a task from not done to done using `done`.

#### Usage

`done <task number>`

Example: `done 2`

Expected outcome:

```
------------
Nice, I've marked this task as done:
[D] [X] return book  (by: Sunday 2pm)
------------
```

### Search for tasks

You can search for tasks using keywords using `find`.

#### Usage

`find <keyword(s)>`

**NOTE**: Multiple words / numbers can be searched.

Example: `find book`

With current list being:
```
------------
Here are the tasks in your list:
1.[T] [ ] return book
2.[D] [X] return book  (by: Sunday 2pm)
3.[E] [ ] go to library  (at: Saturday 3pm)
------------
```

Expected outcome:
```
------------
Here are the matching tasks in your list
[T] [ ] return book
[D] [X] return book  (by: Sunday 2pm)
------------
```

### Delete tasks

You can delete tasks from the list using `delete`.

#### Usage

`delete <task number>`

Example: `delete 2`

Expected outcome:

```
> delete 1
------------
Noted, I've removed this task:
[T] [ ] return book
Now you have 2 tasks in the list
------------
> list
------------
Here are the tasks in your list:
1.[D] [X] return book  (by: Sunday 2pm)
2.[E] [ ] go to library  (at: Saturday 3pm)
------------
```

### Exit the program

Exit the program using `bye`.

#### Usage

`bye`

Expected outcome:
```
------------
Bye. Hope to see you again soon!
------------
```
