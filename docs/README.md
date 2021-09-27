# Duke User Guide

Duke is a Personal Assistant Chatbot on the Command Line Interface that helps a person to keep track of various things, named after the Java Mascot.

## Setup

Prerequisites: Java 11

1. Download the latest release
1. Navigate to the path containing the jar file on your terminal
1. Launch the application using the `java -jar` command on the terminal eg. `java -jar ip.jar`

If the application has launched correctly, a welcome message will be displayed in the terminal.

## Features

# `list ` List all tasks
Shows a list of all tasks in the task list.

Format: `list`

# `done` Mark task as done
Marks the task at the given index as completed.

The index refers to the index number of the task shown in the list of tasks. The index must be a positive integer and it cannot exceed the current maximum number of tasks.

Format: `done INDEX`

Example:

`done 1` - marks the first task as completed

# `delete` Delete task
Removes the task at the specified index from the task list.

The index refers to the index number of the task shown in the list of tasks. The index must be a positive integer and it cannot exceed the current maximum number of tasks.

Format `done INDEX`

Example:

`delete 2` - removes the second entry in the task list.

# `find` Search for tasks
Find and list all tasks with descriptions containing the given search query.

A valid search query must be entered. The search query is case-insensitive. Only full words will be matched, and only the task description will be searched. If there are no tasks containing the search query, the following message will be displayed:
`No results found`

Format: `find QUERY`

Example: `find assignment` - find and lists all tasks containing "assignment"

# `todo` Add new ToDo
Add a new ToDo type to the task list.

The task description cannot be empty.

Format: `todo TASK_DESCRIPTION`

Example: `todo assignment 2` - adds a new ToDo with the description "assignment 2" to the task list.

# `deadline` Add new Deadline
Add a new Deadline to the task list.

The deadline description and due date cannot be empty. The description and due date should be separated with a `/` character. The due date should be in the format `DDMMYYY`, `DD/MM/YYYY` or `DD-MM-YYYY`, otherwise an error message will be displayed and the deadline will not be added to the task list.

Format: `deadline DEADLINE_DESCRIPTION / DEADLINE_DUE_DATE`

Example: `deadline tutorial 3 / 02042022` - adds a new Deadline with the description "tutorial 3" to the task list, with a due date of 2 April 2022.

# `event` Add new Event

Add a new Event to the task list.

The event description and date cannot be empty. The description and date should be separated with a `/` character. The date should be in the format `DDMMYYY`, `DD/MM/YYYY` or `DD-MM-YYYY`, otherwise an error message will be displayed and the event will not be added to the task list.

Format: `deadline EVENT_DESCRIPTION / EVENT_DUE_DATE`

Example: `event christmas / 25122021` - adds a new Event with the description "christmas" to the task list on 25 December 2021.


# `bye` Quit application
Exits the application.

Format: `bye`
