# User Guide
Duke is a **desktop application for managing tasks, optimised for usage
via a Command Line Interface (CLI).** If you can type fast, Duke can help
you manage your daily tasks faster than traditional GUI application.

- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help--help)
  - [Adding a task:](#adding-a-task)
    - [Todo:](#todo-todo)
    - [Deadline:](#deadline-deadline)
    - [Event:](#event-event)
  - [Listing all tasks:](#listing-all-tasks-list)
  - [Marking a task as done:](#marking-a-task-as-done-done)
  - [Deleting a task:]()
  - [Finding a task:]()
  - [Exiting the application:]()

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

Format: `todo <task description>`
- `task description`: details of the todo task

Example: `todo read book`
- Adds a todo task with description `read book` to the tasklist

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

Format: `deadline <task description> /by <task date&time>`
- `<task description>`: details of the deadline
- `<task date&time>`: due date of the deadline

Example: `deadline return book /by June 6th`
- Adds a deadline task with description `return book` and due date `June 6th` to the tasklist

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

Format: `event <task description> /at <task date&time>`
- `<task description>`: details of the event
- `<task date&time>`: occurrence of the event

Example: `event project meeting /at Aug 6th 2-4pm`
- Creates an event task with description `project meeting` and occurs at `Aug 6th 2-4pm` to the tasklist

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
- This command displays the following information for each task:
  - **Task ID**: a number that identifies a task
  - **Task Type**: an alphabet that identifies a task type
    - Todo is represented by [T]
    - Deadline is represented by [D]
    - Event is represented by [E]
  - **Task Completed Status**: an 'X' will be marked if the task is completed, else it will be empty.
  - **Task Description**: details of the task
  - **Task Due-dates**: due date or occurrence of a task (only applicable for deadline/event)

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

Format: `done <task ID>`
- `<task ID>`: a numerical ID of task to be marked as done

Example: done 1
- Marks the task with task ID = 1 as done in the tasklist

Expected Outcome:
```
[You]:
=> done 1

[Duke]:
=> Burrrp! I've marked this task as done:
   <[T][X] read book>
=> You have done 1/3 tasks in your list.

[You]:
=> list

[Duke]:
=> Ahh! Here are the tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: June 6th)
3.[E][ ] project meeting (at: Aug 6th 2-4pm)
=> You have done 1/3 tasks in your list.
```

### Deleting a task: `delete`
Deletes an existing task in the tasklist.

Format: `delete <task ID>`
- `<task ID>`: a numerical ID of task to be deleted

Example: delete 1
- Deletes the task with task ID = 1 in the tasklist

Expected Outcome:
```
[You]:
=> delete 1

[Duke]:
=> Blaargh! I've deleted this task from the list:
   <[T][X] read book>
=> Now you have 2 tasks in your list.

[You]:
=> list

[Duke]:
=> Ahh! Here are the tasks in your list:
1.[D][ ] return book (by: June 6th)
2.[E][ ] project meeting (at: Aug 6th 2-4pm)
=> You have done 0/2 tasks in your list.
```









