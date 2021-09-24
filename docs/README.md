# User Guide
Alfred is a Command Line Application for task management, modelled after Alfred the Butler from Batman.

- Features
  - [Listing current tasks: `list`](#list)
  - Adding tasks:
    - Todo: `todo`
    - Event: `event`
    - Deadline: `deadline`
  - Marking task as complete: `done`
  - Searching for tasks: `find`
  - Exiting the app: `bye`
- Saving the data
- Command summary

## Features and Usage

### <a name="list"></a>`list` - Listing current tasks
Lists all current tasks stored in local storage task list. <br />
Usage: `list` <br />

Examples of usage and expected outcome:
- If there exists tasks, Alfred will enumerate the tasks and print them:
```
list
____________________________________________________________
 Your tasks, sir:
 1.[T][ ] Meet Catwoman
 2.[E][ ] Save Gotham (at: Sep 24 2021)
____________________________________________________________
```
- If task list is empty, Alfred will respond with the following message:
```
list
____________________________________________________________
 Your schedule is clear, Master Wayne.
____________________________________________________________
```

### <a name="todo"></a>`todo` - Adding a Todo
Adds a Todo to the task list. <br />
Usage: `todo TODO_DESCRIPTION` <br />

Examples of usage and expected outcome:
```
todo Meet Catwoman
____________________________________________________________
 I shall put this in your schedule, Master Wayne: 
    [T][ ] Meet Catwoman
 Sir, the number of Tasks you have scheduled currently amounts to 1.
____________________________________________________________
```
> :warning: `TODO_DESCRIPTION` cannot be empty.

### <a name="event"></a>Adding Events: `event`


### <a name="deadline"></a>Adding Deadlines: `deadline`


### <a name="done"></a>Marking task as complete: `done`


### <a name="find"></a>Searching for tasks: `find`


### <a name="exut"></a>Exiting the app: `bye`

### Exiting the app: `bye`
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

### list