# User Guide
Duke is a Personal Assistant Chatbot that helps a person to keep track of various things using a Command Line Interface(CLI). If you can type fast, Duke is able to track and manage your tasks faster compared to a hypoethetical Graphical User Interface (GUI) version of the app. 

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
**`Command Format`**
* ` blahblahblah`
* ` blahblahblah`
* ` blahblahblah`
* ` blahblahblah`

### Adding a Todo task: `todo`
Adds a task of class Todo into the task list.
Format: `todo <TASK_DESCRIPTION>`

### Adding an Event task: `event`
Adds a task of class Event into the task list.
Format: `event <TASK_DESCRIPTION>/<DATE_AND_TIME>`

### Adding a Deadline task: `deadline`
Adds a task of class Deadline into the task list.
Format: `deadline <TASK_DESCRIPTION>/<DATE_AND_TIME>`

### Listing all tasks: `list`
Shows a list of all tasks.
Format: `list`

### Mark as done: `done`
Marks a specific task from the list as completed.
Format: `done <TASK_NUMER>`

### Deleting task: `delete`
Deletes a specific task from the list.
Format: `delete <TASK_NUMBER>`

### Finding Tasks: `find`
Finds all tasks in the list containing a keyword.
Format: `find <KEYWORD>`

### Exiting program: `bye`
Exits from Duke ChatBot.
Format: `bye`

```
expected output
```

## Command Summary
**Action** | **Format** | **Examples**
---------- | ---------- | ------------
**todo** | `todo <TASK_DESCRIPTION>` | `todo Laundry`
**event** | `event <TASK_DESCRIPTION>/<DATE_AND_TIME>` | `event CS2113T Lecture /01-10-2021 16:00`
**deadline** | `deadline <TASK_DESCRIPTION>/<DATE_AND_TIME>` | `deadline IP /01-10-2021 23:59`
**list** | `list` | `list`
**done** | `done <TASK_NUMER>` | `done 1`
**delete** | `delete <TASK_NUMER>` | `delete 1`
**find** | `find <KEYWORD>` | `find CS2113T`
**bye** | `bye` | `bye`
