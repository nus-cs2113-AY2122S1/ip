# User Guide
Alfred is a Command Line Application for task management, modelled after Alfred the Butler from Batman.

- Features
  - [Listing current tasks: `list`](#list)
  - Adding tasks:
    - [Todo: `todo`](#todo)
    - [Event: `event`](#event)
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

Expected outcome and examples of usage:

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
- :warning: `TODO_DESCRIPTION` cannot be empty.

Expected outcome and examples of usage:

Alfred will add the Todo to the current task list, and 
display the current number of tasks in the task list:
```
todo Meet Catwoman
____________________________________________________________
 I shall put this in your schedule, Master Wayne: 
    [T][ ] Meet Catwoman
 Sir, the number of Tasks you have scheduled currently amounts to 1.
____________________________________________________________
```

### <a name="event"></a>Adding Events: `event`
Adds an Event to the task list. <br />
Usage: `event EVENT_DESCRIPTION /at EVENT_DATE` <br />
- :warning: `EVENT_DESCRIPTION` cannot be empty.
- :warning: `EVENT_DATE` cannot be empty.
- :warning: `EVENT_DATE` must be formatted as `DDMMYYYY`, `DD/MM/YYYY`, or `DD-MM-YYYY`.

Expected outcome and examples of usage: 

Alfred will add the Event to the current task list, and
display the current number of tasks in the task list:
```
event OP1 /at 10092021
____________________________________________________________
 I shall put this in your schedule, Master Wayne:
    [E][ ] OP1 (at: Sep 10 2021)
 Sir, the number of Tasks you have scheduled currently amounts to 2.
____________________________________________________________
```


### <a name="deadline"></a>Adding Deadlines: `deadline`
Adds a Deadline to the task list. <br />
Usage: `deadline DEADLINE_DESCRIPTION /by DEADLINE_DATE` <br />
- :warning: `DEADLINE_DESCRIPTION` cannot be empty.
- :warning: `DEADLINE_DATE` cannot be empty.
- :warning: `DEADLINE_DATE` must be formatted as `DDMMYYYY`, `DD/MM/YYYY`, or `DD-MM-YYYY`.

Expected outcome and examples of usage:

Alfred will add the Deadline to the current task list, and
display the current number of tasks in the task list:
```
deadline Submit IP Final Version /by 01/10/2021
____________________________________________________________
 I shall put this in your schedule, Master Wayne: 
    [D][ ] Submit IP Final Version (by: Oct 01 2021)
 Sir, the number of Tasks you have scheduled currently amounts to 3.
____________________________________________________________
```

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