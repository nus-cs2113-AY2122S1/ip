---
layout: page
title: User Guide
---

Duke is a **Personal Assistant ChatBot** that allows a person to **keep track of various tasks** through a Command Line Interface (CLI). A fast typist can manage her tasks more efficiently with Duke than with traditional Graphical User Interface (GUI) applications.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

- [Set Up](#set-up)
- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing Help](#viewing-help)
  - [Adding a Todo Task](#adding-a-todo-task)
  - [Adding a Deadline Task](#adding-a-deadline-task)
  - [Adding an Event Task](#adding-an-event-task)
  - [Listing all Tasks](#listing-all-tasks)
  - [Mark as Done](#mark-as-done)
  - [Delete a Task](#delete-a-task)
  - [Find Tasks](#find-tasks)
  - [Exiting the Program](#exiting-the-program)




# Set Up

# Quick Start

# Features 

## Viewing help: help OR h
- Displays all available commands
- Format: `help` OR `h`

Example of usage:
```
help
```

Expected outcome:
```
List of commands:
1. l OR list 
2. todo [TASK DESCRIPTION]
3. deadline [TASK DESCRIPTION] /by [DEADLINE]
4. event [TASK DESCRIPTION] /at [DATE/TIME]
5. delete [TASK NUMBER]
6. done [TASK NUMBER]
7. find [PART OF TASK DESCRIPTION]
8. h OR help
9. bye
```

## Adding a Todo Task

Adds a todo task without any date/time details _eg. tidy up room

Format: `todo <TASK_NAME>`

Example of usage:
```
todo apply for badminton club
```

Expected outcome:
```
Got it. I've added this task:
[T][ ] apply for badminton club
Now you have 1 task in the list.
```

## Adding a Deadline Task

Adds a deadline task that has to be completed by a certain date/time _eg. Submit Assignment 1 by Friday 2359

Format: `deadline <TASK_NAME> /by <DATE_AND_OR_TIME>`

- The `/by` must be inserted between `TASK_NAME` and `DATE_AND_OR_TIME`
- `DATE_AND_OR_TIME` is any date and or time the user wishes to specify in any format

Example of usage:
```
deadline Submit Assignment 1 /by Friday 2359
```

Expected outcome:
``` 
Got it. I've added this task:
[D][ ] Submit Assignment 1 (by: Friday 2359)
Now you have 2 tasks in the list.
```

## Adding an Event Task

Adds an event task that will happen at a specifc time in the future _eg. Dental Appointment at Saturday 1pm

Format: `event <TASK_NAME> /at <DATE_AND_OR_TIME>`

- The `/at` must be inserted between `TASK_NAME` and `DATE_AND_OR_TIME`
- `DATE_AND_OR_TIME` is any date and or time the user wishes to specify in any format

Example of usage:
```
event Dental Appointment /at 19 July 1pm
```

Expected outcome:
```
Got it. I've added this task:
[E][ ] Dental Appointment (at: 19 July 1pm)
Now you have 3 tasks in the list.
```






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
