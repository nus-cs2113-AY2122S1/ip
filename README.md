
![DukeLogo](https://user-images.githubusercontent.com/69495787/134464789-b4193e01-2537-41b2-b782-8abff7851e53.png)
<img src="https://user-images.githubusercontent.com/69495787/134465925-5eaf528b-fdb8-48b3-a085-afd7dbf5450f.JPG" width="368" height="213">

# User Guide

**Duke** is a productivity desktop app that is used through a **Command Line Interface (CLI)**. It is a fast and simple way to keep track of your schedule and tasks on the go.

# Table of Content

- [Quick Start](#quick-start)
- [Features](#Features)
    * [Help](#Help---Show-All-Valid-Commands)
    * [List](#List---List-Down-All-Current-Tasks)
    * [Add Task](#Add---Add-a-Task-to-Your-List)
        * [Todo](#A-Todo-Task)
        * [Deadline](#A-Deadline-Task)
        * [Event](#An-Event-Task)
    * [Find](#Find---Show-All-Task-Related-to-Search-Terms)
    * [Done](#Done---Mark-a-Task-as-Done)
    * [Delete](#Delete---Remove-a-Task)
    * [Save](#Save---Save-All-Task-Into-Local-File)
    * [Bye](#Bye---Exit-the-Application)
- [FAQ](#FAQ---Frequently-Asked-Questions)
- [Command Summary](#Command-Summary)

# Quick Start

1. Ensure that you have Java `11` in your computer
2. Download `Poopies-iP.jar` from [here](https://github.com/Poopies99/ip/tree/master/release)
3. Copy file into Desktop
4. Open Command Prompt(Windows Users) or Terminal (MacOS Users)
5. Type `cd Desktop` followed by `java -jar Poopies-iP.jar`

Expected Outcome:

```
________________________________________________________________
     Hello! I'm
     ____        _
    |  _ \ _   _| | _____
    | | | | | | | |/ / _ \
    | |_| | |_| |   <  __/
    |____/ \__,_|_|\_\___|
________________________________________________________________
I am your very own schedule assistant here to enhance your everyday life
Type help to see what i can do!
```

________________________________________________________________

# Features

________________________________________________________________

## Help - Show All Valid Commands

List down all valid commands and their formats

Format: `help`

Expected Outcome:

```
________________________________________________________________
     Please input a valid request
     Duke can do the follow instructions
     1. Record Todo Task: todo (description)
     2. Record Deadline Task: task (description) /by (date)
     3. Record Event Task: event (description) /at (date)
     4. List Down Recorded Tasks: list
     5. Set Task After Completion: done (index on list)
     6. Exit From Program: bye
________________________________________________________________
```

## List - List Down All Current Tasks

List down all current tasks in the task list

Format `list`

Expected Outcome:

```
These are the list of tasks you have
________________________________________________________________
     1.[E][ ] Read (at: Mon)
     2.[E][ ] Bible Study (at: Tues)
     3.[E][ ] Dinner (at: Wed)
     4.[ ] CS2113T iP Submission (by: Thurs)
     5.[T][ ] Read Micah
     6.[ ] Study for CS2113T (by: Saturday 2pm)
     7.[E][ ] Dinner Date (at: Thursday 7pm)
________________________________________________________________
```

## Add - Add a Task to Your List

Add Task into task list

### A Todo Task

Add Todo Task into task list

Format `todo <DESCRIPTION>`

* **DESCRIPTION:** The Todo description

Example: `todo Read Micah`

Expected Outcome:

```
________________________________________________________________
     Got it. I've added this task:
     [T][ ] Read Micah
     Now you have 5 tasks in the list.
________________________________________________________________
```

### A Deadline Task

Add Deadline Task into task list

Format `deadline <DESCRIPTION> /by <DATE>`

* **DESCRIPTION:** The Deadline description

* **DATE:** Date and Time that the deadline is due by

Example: `deadline Study for CS2113T /by Saturday 2pm`

Expected Outcome: 

```
________________________________________________________________
     Got it. I've added this task:
     [ ] Study for CS2113T (by: Saturday 2pm)
     Now you have 6 tasks in the list.
________________________________________________________________
```

### An Event Task

Add Event Task into task list

Format `event <DESCRIPTION> /at <DATE>`

* **DESCRIPTION:** The Event description

* **DATE:** Date and Time that the deadline is due by

Example: `event Dinner Date /at Thursday 7pm`

Expected Outcome: 

```
________________________________________________________________
     Got it. I've added this task:
     [E][ ] Dinner Date (at: Thursday 7pm)
     Now you have 7 tasks in the list.
________________________________________________________________
```

## Find - Show All Task Related to Search Terms

Show related task to search terms

Format `find <DESCRIPTION>`

* **DESCRIPTION:** Description of the Task to search

Example: `find Read`

Expected Outcome:

```
________________________________________________________________
     Here are the matching tasks in your list:
     1.[E][ ] Read (at: Mon)
     2.[T][ ] Read Micah
________________________________________________________________
```

## Done - Mark a Task as Done

Mark a Task as done base on Index in List

Format `done <INDEX>`

* **INDEX:** Index of Task as shown from the task list

Example `done 3`

Expected Outcome: 

```
________________________________________________________________
     Nice! I've marked this task as done:
     [X] Dinner
________________________________________________________________
```
## Uncheck - Mark a Task as Undone

Mark a Task as undone base on Index in List

Format `uncheck <INDEX>`

* **INDEX:** Index of Task as shown from the task list

Example `uncheck 3`

Expected Outcome: 

```
________________________________________________________________
     Alright! I've marked this task as undone:
     [ ] Dinner
________________________________________________________________
```

## Delete - Remove a Task

Delete a Task from the list base on Index

Format `delete <INDEX>`

* **INDEX:** Index of Task as shown from the task list

Example `delete 3`

Expected Outcome:

```
________________________________________________________________
     Noted. I've removed this task:
     [E][X] Dinner (at: Wed)
     Now you have 6 tasks in the list.
________________________________________________________________
```

## Save - Save All Task Into Local File

Save all current task into local file

Format `save`

Expected Outcome:

```
________________________________________________________________
     Your tasks has been saved
________________________________________________________________
```

## Bye - Exit the Application

Exit from Duke

Format `bye`

Expected Outcome:

```
File has been saved!
________________________________________________________________
     Bye. Hope to see you again soon!
________________________________________________________________
```

________________________________________________________________

## FAQ - Frequently Asked Questions

**Q:** I would like to edit the description of my Task

**A:** Currently editing task is not supported, feel free to delete and add task

**Q:** How do I import task list

**A:** Simply deleting local SavedTask.txt file and import date by copying file into data folder

## Command Summary

| Command, Format                                       | Meaning, Examples                                                     |
| --------------                                        | ----------                                                            |
| `help`                                                | Show All Valid Commands                                               |
| `list`                                                | List Down All Current Tasks                                           |
| `todo <DESCRIPTION>`                                  | Add a todo task                                                       |
| `deadline <DESCRIPTION> /by <DATE>`                   | Add a deadline task                                                   |
| `event <DESCRIPTION> /at <DATE>`                      | Add an event task                                                     |
| `find <DESCRIPTION>`                                  | Show all task related to search terms                                 |
| `done <INDEX>`                                        | Mark a task as done                                                   |
| `uncheck <INDEX>`                                     | Mark a task as undone                                                 |
| `delete <INDEX>`                                      | Remove a task                                                         |
| `save`                                                | Save all task into local file                                         |
| `bye`                                                 | Exits the application                                                 |
