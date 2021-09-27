# User Guide
Duke is a **desktop application for managing tasks, optimised for usage
via a Command Line Interface (CLI).** If you can type fast, Duke can help
you manage your daily tasks faster than traditional GUI application.

- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help--help)
  - [Adding a task:](#adding-a-task)
    - [Todo: `todo`](#todo-todo)
    - [Deadline: `deadline`](#deadline-deadline)
    - [Event: `event`](#event-event)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Marking a task as done: `done`](#marking-a-task-as-done-done)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Finding a task with a keyword: `find`](#finding-a-task-with-a-keyword-find)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
- [FAQ]()
- [Command Summary]()

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `Duke.jar` from [here](www.google.com)
3. Copy the file to the folder you want to use as the _home folder_ for Duke.
4. Open a terminal window in the _home folder_.
5. Type `java -jar Duke.jar` into the terminal to start Duke. 
6. The following display should appear in a few seconds:
```
+++++++++++++++++++++++++++++++++++++++++++++
  __         __
 /  \.-"""-./  \     ____        _
 \    -   -    /    |  _ \ _   _| | _____
  |   o   o   |     | | | | | | | |/ / _ \
  \  .-'''-.  /     | |_| | |_| |   <  __/
   '-\__Y__/-'      |____/ \__,_|_|\_\___|
      `---`
[A NUS CS2113T Project by: Peh Zhenhao, Amos]
+++++++++++++++++++++++++++++++++++++++++++++

[Duke]:
=> Hello! I'm Duke :)
=> I'm so hungry, would you mind feeding me tasks?

[You]:
=> 
```
7. You may now enter commands into Duke. Type a command beside the cursor `=>`
and press `Enter` on your keyboard to execute the command.
8. Some example commands you can try:
    - **`help`** : Displays all commands available for the Duke application.
    - **`todo read book`** : Adds a `Todo` task with description `read book` to the tasklist.
    - **`list`** : Lists all tasks in the tasklist.
    - **`bye`** : Exit the Duke application.
9. Refer to the [Features](#features) below for details of each command.

## Features
### Viewing help : `help`
Displays all commands available for the Duke application. 

Format: `help`

### Adding a task:
### Todo: `todo`
Adds a **todo** task to the tasklist.

Format: `todo [description]`
- `[description]` specifies the details of the todo.

Example: `todo read book`
- Adds a todo task with description `read book` to the tasklist.

Expected Outcome:
```
[You]:
=> todo read book

[Duke]:
=> Chomp-chomp! I've added this new task:
   <[T][ ] read book>
=> Now you have 1 tasks in your list.
```

### Deadline: `deadline`
Adds a **deadline** task to the tasklist.

Format: `deadline [description] /by [due date]`
- `[description]` specifies the details of the deadline.
- `[due date]` specifies when the deadline is due.

Example: `deadline return book /by June 6th`
- Adds a deadline task with description `return book` and due date `June 6th` to the tasklist.

Expected Outcome:
```
[You]:
=> deadline return book /by June 6th

[Duke]:
=> Chomp-chomp! I've added this new task:
   <[D][ ] return book (by: June 6th)>
=> Now you have 2 tasks in your list.
```

### Event: `event`
Adds a **event** task to the tasklist.

Format: `event [description] /at [occurence]`
- `[description]` specifies the details of the event.
- `[occurence]` specifies when the event occurs.

Example: `event project meeting /at Aug 6th 2-4pm`
- Creates an event task with description `project meeting` and occurs at `Aug 6th 2-4pm` to the tasklist.

Expected Outcome:
```
[You]:
=> event project meeting /at Aug 6th 2-4pm

[Duke]:
=> Chomp-chomp! I've added this new task:
   <[E][ ] project meeting (at: Aug 6th 2-4pm)>
=> Now you have 3 tasks in your list.
```

### Listing all tasks: `list`
Lists all tasks in the tasklist.

Format: `list`
- The command displays the following information for each task:
  - **Task id**: 
    - A positive integer i.e. `1.` to identify the task
  - **Task type**:
    - A task can be either Todo, Deadline or Event and is represented with the first letter of their task type
    - `[T]` represents a Todo
    - `[D]` represents a Deadline
    - `[E]` represents a Event
  - **Whether the task is done**:
    - A completed task is identified as `[X]`
    - An incomplete task is identified as `[ ]`
  - **Task description**:
    - General details of the task

Expected Outcome:
```
[You]:
=> list

[Duke]:
=> Ahh! Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: June 6th)
3.[E][ ] project meeting (at: Aug 6th 2-4pm)
=> You have done 0/3 tasks in your list.
```

### Marking a task as done: `done`
Marks an existing task in the tasklist as **done**.

Format: `done [task ID]`
- Mark a task with the specified `[task ID]` as done.
- The task ID refers to the index number shown in the displayed tasklist when the `list` command is entered.
- The task ID **must be a positive integer 1, 2, 3...**

Example: `done 1`
- Marks the task with task ID equals to `1` as done in the tasklist.

Expected Outcome:
```
[You]:
=> done 1

[Duke]:
=> Burrrp! I've marked this task as done:
   <[T][X] read book>
=> You have done 1/3 tasks in your list.
```

### Deleting a task: `delete`
Deletes an existing task in the tasklist.

Format: `delete [task ID]`
- Deletes a task with the specified `[task ID]`.
- The task ID refers to the index number shown in the displayed tasklist when the `list` command is entered.
- The task ID **must be a positive integer 1, 2, 3...**

Example: `delete 1`
- Deletes the task with task ID equals to `1` in the tasklist.

Expected Outcome:
```
[You]:
=> delete 1

[Duke]:
=> Blaargh! I've deleted this task from the list:
   <[T][X] read book>
=> Now you have 2 tasks in your list.
```

### Finding a task with a keyword: `find`
Find tasks whose description contains a given keyword.

Format: `find [keyword]`
- The search is case-sensitive. e.g. `book` will not match `Book`
- The order of the keywords matter. e.g. `return book` will not match `book return`
- The keyword will match substrings. e.g. `oo` will match with `book`
- Only the task description is searched.

Example: `find book`
- Finds all tasks that contains the substring `book`

Expected Outcome:
```
[You]:
=> find book

[Duke]:
=> Ahh! Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: June 6th)
=> It has successfully returned 2 result/s.
```

### Exiting the program: `bye`
Displays goodbye message and exits the program.

Format: `bye`

Expected Outcome:
```
[You]:
=> bye

[Duke]:
=> Come back soon, I'm still hungry!
```

## FAQ


## Command Summary
Action | Format | Example
------------ | ------------- | -------------
help | `help` | `help`
todo | `todo [description]` | `todo read book`
deadline | `deadline [description] /by [due date]` | `deadline return book /by June 6th`
event | `event [description] /at [occurence]` | `event project meeting /at Aug 6th 2-4pm`
list | `list` | `list`
done | `done [task ID]` | `done 1`
delete | `delete [task ID]` | `delete 1`
find | `find [keyword]` | `find book`
bye | `bye` | `bye`






