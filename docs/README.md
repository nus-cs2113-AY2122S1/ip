# User Guide

**Duke** is a desktop app to **manage tasks and keep a track of things**.
It is a Personal Assistant Chatbot that can store data and help you keep a track of the tasks.
It is optimized for use via a Command Line Interface (CLI) that can help you manage your tasks faster than the traditional GUI apps.



## Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
    * [Viewing help](#viewing-help--help)
    * [Adding a Todo](#adding-a-todo-todo-description)
    * [Adding a Deadline](#adding-a-deadline-deadline-description-by-datetime)
    * [Adding an Event](#adding-an-event-event-description-at-datetime)
    * [Listing the Task List](#listing-tasks-list)
    * [Marking a task as done](#marking-a-task-as-done-done-idx)
    * [Deleting a task](#deleting-a-task-delete-idx)
    * [Searching for a task](#querying-tasks-find-regex-type-tasktype-limit-querylimit)
    * [Saving the tasks]()
    * [Exiting the program](#exit-the-application--bye)

- [FAQ](#faq)
- [Command Summary]()

-------------------------------------------------

## Quick Start

1. Ensure that you have Java `11` or above installed in your computer
2. Download the latest `ip.jar` from [here](https://github.com/aditichadha1310/ip)
3. Copy the file to the folder you want to use as the home folder for your `Task Scheduler`.
4. Double-click the file to start the app

You should see the following output :
```bash
____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________

```
5. Note the sample tasks already present! Type a command and press `Enter` to execute it.
   e.g. typing `help` will display all possible commands that **DUKE** accepts.

Some example commands you can try:
- `list` : Lists all tasks.
- `todo <description>` : Adds a TODO task in the task list.
- `deadline <description> /by <DateAndTime>` : Adds a DEADLINE task to be completed by `<datetime>` in the task list.
- `event <description> /at <DateAndTime>` : Adds an EVENT task that will take place at `<datetime>` in the task list.
- `delete <5>` : Deletes the 5th task from the current list.
- `bye` : Exits the application.

---
## Features

>:information_source: **Notes about the command formats**
> - Words in `<UPPER_CASE>` are the parameters to be given by the user. <br />
> e.g. in `todo <DESCRIPTION>`, <DESCRIPTION> is a parameter which can be used as : `todo Read Novel`
> <br /><br />
> - Items in square brackets are optional <br />
> e.g. find `find <REGEX> [/type TASKTYPE]` 
> can be called as `find eat /type todo` OR `find eat`.
> <br /><br />
> - Parameters can be in any order for optional flags <br />
> e.g. `find <REGEX> [/type TASKTYPE] [/limit QUERYLIMIT]` is equivalent to `find <REGEX> [/limit QUERYLIMIT] [/type TASKTYPE] `
> <br /><br />
> - Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `bye`) will not be ignored.<br />
> e.g. `help 123` will not be interpreted as  `help`. Make sure to give the correct commands.

### Feature-ABC

Description of the feature.

### Feature-XYZ

Description of the feature.

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
  
expected output
```

## FAQ

**Q:** Why am I unable to create a task in the past?

**A:** The tasks are generally scheduled for the future, thus Duke does not allow users to schedule a task for a past date.
<br />

**Q:** How can I delete multiple tasks or mark multiple tasks as done in one command?

**A:** Deleting multiple tasks and marking multiple tasks as done in one single command is currently unsupported, but an alternative option is to delete/mark the tasks as done one by one.
<br />

**Q:** How do I transfer my data to another PC?

**A:** Install the app in the other computer and overwrite the empty data file 
it creates with the file that contains the data from your previous Duke home folder.

<br />

---

## Command Summary

| Command Format,Examples                    | Meaning                                                                        |
| --------------                             | ----------                                                                     |
| `help`                                     | Lists all the valid commands                                                   |
| `list`                                     | Lists all the scheduled tasks from the task list                               |
| `find <SEARCH_KEYWORD>`, `find Read`       | Searches in the task list for tasks which contain the keyword                  |
| `todo <DESCRIPTION>`, `todo eat food`      | Adds a todo task with description                                              |
| `deadline <DESCRIPTION> /by <DATE_TIME>`,  |                                                                                |
   `deadline Eat Food /by 2021-10-13 16:00`  | Adds a deadline task with description and a deadline                           |
| `event <DESCRIPTION> /at <DATE_TIME>` ,    |                                                                                |
   `event join lecture /at 2021-09-09 09:45` | Adds an event task with description and event timing                           |
| `delete <NUMBER>`, `delete 3`              | Deletes a task from the task list according to the index number provided       |
| `done <NUMBER>`, `done 4`                  | Marks a task as done in the task list according to the index number provided   |
| `bye`                                      | Exits the application                                                          |
----------------------------------------------------------------------------------------------------------------------------------------------------------------------  
