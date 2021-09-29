# User Guide
Duke is a **Personal Assistant Chatbot** that helps a person to **keep track of various things** using a Command Line Interface(CLI). If you can type fast, Duke is able to track and manage your tasks faster compared to a hypoethetical Graphical User Interface (GUI) version of the app. 

* [Quick Start](https://xingjie99.github.io/ip/#quick-start)
* [Features](https://xingjie99.github.io/ip/#features)
  - [Adding a Todo task](https://xingjie99.github.io/ip/#adding-a-todo-task-todo)
  - [Adding an Event task](https://xingjie99.github.io/ip/#adding-an-event-task-event)
  - [Adding a Deadline task](https://xingjie99.github.io/ip/#adding-a-deadline-task-deadline)
  - [Listing all tasks](https://xingjie99.github.io/ip/#listing-all-tasks-list)
  - [Mark as done](https://xingjie99.github.io/ip/#mark-as-done-done)
  - [Deleting task](https://xingjie99.github.io/ip/#deleting-task-delete)
  - [Finding Tasks](https://xingjie99.github.io/ip/#finding-tasks-find)
  - [Exiting program](https://xingjie99.github.io/ip/#exiting-program-bye)
* [Command Summary](https://xingjie99.github.io/ip/#command-summary)

## Quick Start 
1. Description of the Quick start.
2. test
3. test
4. test
5. test
6. test

## Features
**Command Format** 
 * Words in upper case within the arrow brackets (e.g `<TASK_DESCRIPTION>`) are known as parameters; values to be provide by users.

### Adding a Todo task: `todo`
Adds a task of class Todo into the task list.
Format: `todo <TASK_DESCRIPTION>`

Example of input:
```
todo Laundry
```
Expected output:
```
Got it. I've added this task:
 [T][ ] Laundry
Now you have 1 tasks in the list.
```

### Adding an Event task: `event`
Adds a task of class Event into the task list.
Format: `event <TASK_DESCRIPTION>/<DATE_AND_TIME>`
* `/` must be present in between `<TASK_DESCRIPTION>` and `DATE_AND_TIME>`
* `<DATE_AND_TIME>` must be in `DD-MM-YYYY HH:MM`

Example of input:
```
event CS2113T Lecture /01-10-2021 16:00
```
Expected output:
```
Got it. I've added this task:
 [E][ ] CS2113T Lecture (1 Oct 2021, 4:00:00 PM)
Now you have 2 tasks in the list.
```

### Adding a Deadline task: `deadline`
Adds a task of class Deadline into the task list.
Format: `deadline <TASK_DESCRIPTION>/<DATE_AND_TIME>`
* `/` must be present in between `<TASK_DESCRIPTION>` and `DATE_AND_TIME>`
* `<DATE_AND_TIME>` must be in `DD-MM-YYYY HH:MM`

Example of input:
```
deadline IP /01-10-2021 23:59
```
Expected output:
```
Got it. I've added this task:
 [D][ ] IP (1 Oct 2021, 11:59:00 PM)
Now you have 3 tasks in the list.
```

### Listing all tasks: `list`
Shows a list of all tasks.
Format: `list`

Example of input:
```
list
```
Expected output:
```
1. [T][ ] Laundry
2. [E][ ] CS2113T Lecture (1 Oct 2021, 4:00:00 PM)
3. [D][ ] IP (1 Oct 2021, 11:59:00 PM)
```

### Mark as done: `done`
Marks a specific task from the list as completed.
Format: `done <TASK_NUMER>`
* `<TASK_NUMER>` must be a valid integer value

Example of input:
```
done 1
```
Expected output:
```
Nice! I've marked this task as done:
[X] Laundry
```

### Deleting task: `delete`
Deletes a specific task from the list.
Format: `delete <TASK_NUMBER>`
* `<TASK_NUMER>` must be a valid integer value

Example of input:
```
delete 2
```
Expected output:
```
Noted. I've removed this task:
 [E][ ] CS2113T Lecture (1 Oct 2021, 4:00:00 PM)
Now you have 2 tasks in the list.
```

### Finding Tasks: `find`
Finds all tasks in the list containing a keyword.
Format: `find <KEYWORD>`

Example of input:
```
find Laundry
```
Expected output:
```
Here are the matching tasks in your list:
1. [T][X] 
```

### Exiting program: `bye`
Exits from Duke ChatBot.
Format: `bye`

Example of input:
```
bye
```
Expected output:
```
Bye. Hope to see you again soon!
```

## Command Summary
| **Action** | **Format** | **Examples** |
| ---------- | ---------- | ------------ |
|            |            |              |
| **todo** | `todo <TASK_DESCRIPTION>` | `todo Laundry` |
| **event** | `event <TASK_DESCRIPTION>/<DATE_AND_TIME>` | `event CS2113T Lecture /01-10-2021 16:00` |
| **deadline** | `deadline <TASK_DESCRIPTION>/<DATE_AND_TIME>` | `deadline IP /01-10-2021 23:59` |
| **list** | `list` | `list` |
| **done** | `done <TASK_NUMER>` | `done 1` |
| **delete** | `delete <TASK_NUMER>` | `delete 2` |
| **find** | `find <KEYWORD>` | `find Laundry` |
| **bye** | `bye` | `bye` |
