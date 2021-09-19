# User Guide

Duke is a **desktop app for users to quickly manage their to-do list** via Command Line Interface(CLI) and
Graphical Line Interface (currently not available, but coming soon...).

## Features 
1. Add a task into the list
2. Delete a task
3. List all the tasks
4. Complete a task
5. Find task(s) containing certain keyword
6. Sort tasks
7. Get help
8. Exit

### Add a task into the list: 

There are three possible task types that can be added.
* `todo`: A task without time constraint
* `deadline`: A task needs to be completed **before certain time**.
* `event`: A task needs to be complete **at certain time**.

#### format: 
1. add a `todo` task: `todo <task description>`
2. add a `deadline` task: `deadline <task description> /<YYYY-MM-DD> [HH:MM]`
3. add a `event` task: `event <task description> /<YYYY-MM-DD> <HH:MM>`

### Delete tasks
delete some task inside the list according to the index value.

#### format: 
`delete <task index>`


### List all tasks
Show a list of all tasks.
####format:
`list`


###Complete a task
If user has completed a task, he/she can set it done, which is according to the index value.
####format
`done <task index>`

###Find some task(s)
A user can search some task(s) containing certain keywords
####format
`find <keyword>`

###Sort tasks
Tasks can be sorted based on emergency. Tasks have closer deadline will be put in front.
####format
`sort`

###Get help
List all the commands format if users forget them
####format
`help`

###Exit
Exit the program and automatically save all the tasks.
####format
`bye`

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
