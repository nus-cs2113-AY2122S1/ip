# User Guide

## Features 

### Work with multiple task types
Keep track of todo items, events and deadlines using simple commands.

**Todo** tasks only include a description

**Deadline** tasks have a description and a due by date

**Event** tasks have a description and a date at which they occur

#### Task representation by taro:
All tasks stored by taro are displayed using the following representation: `[TASK_TYPE][COMPLETION_STATUS] TASK_DESCRIPTION [DATE]`
- `TASK_TYPE`: either `T`, `D` or `E`, indicating if the task is a Todo, Deadline or Event task respectively
- `COMPLETION_STATUS`: either blank or `X` where a blank space represents an incomplete task and an `X` represents a completed task
- `TASK_DESCRIPTION`: the description of the task specified when adding the task
- `DATE`: only for Deadline and Event type tasks
  - `by` date for Deadline tasks as the date by which the task is due
  - `at` date for Event tasks as the date at which the event occurs

Example: `[D][ ] setup vet appointment (by: tomorrow evening)`

This indicates that the task is a Deadline type task that is incomplete, with the description "setup vet appointment" that is due by "tomorrow evening".

Please refer to this section when interpreting the example outputs found under the "Usage" section of this guide.


### Easy modification of task properties

Use specialised commands such as `done` and `delete` to quickly modify task properties. 
The usage of these commands is explained below.

### Locate relevant tasks quickly

Use the `find` command to get information about tasks containing a specified keyword.

## Usage

💡 `UPPER_CASE` words under the "Format" section of each command refer to parameters taken by that command.

### `todo` - Add a todo task to taro
Add a Todo type task to the list of tasks stored by taro.

Format: `todo TASK_DESCRIPTION`
- `TASK_DESCRIPTION`: the description of the task to be added

Example of usage: `todo buy dog food`

Expected outcome:
```
____________________________________________________________
 okay! I have added this task:
  [T][ ] buy dog food
 now you have 1 task(s) in the list
____________________________________________________________
```

### `deadline` - Add a deadline task to taro
Add a Deadline type task to the list of tasks stored by taro, with a date by which the task is due.

Format: `deadline TASK_DESCRIPTION /by DUE_DATE`
- `TASK_DESCRIPTION`: the description of the task to be added
- `DUE_DATE`: the date by which the task is due

Example of usage: `setup vet appointment /by tommorrow evening`

Expected outcome:
```
____________________________________________________________
 okay! I have added this task:
  [D][ ] setup vet appointment (by: tomorrow evening)
 now you have 2 task(s) in the list
____________________________________________________________
```

### `event` - Add an event task to taro
Add an Event type task to the list of tasks stored by taro, with a date on which the event occurs.

Format: `event TASK_DESCRIPTION /at EVENT_DATE`
- `TASK_DESCRIPTION`: the description of the task to be added
- `EVENT_DATE`: the date on which the event occurs

Example of usage: `event dog show /at Saturday 6pm`

Expected outcome:
```
____________________________________________________________
 okay! I have added this task:
  [E][ ] dog show (at: Saturday 6pm)
 now you have 3 task(s) in the list
____________________________________________________________
```

### `list` - Show all tasks
Print out a complete list of all tasks stored by taro.

Format: `list`

Example of usage: `list`

Expected outcome:
```
____________________________________________________________
 here are the tasks in your list:
 1. [T][ ] buy dog food
 2. [D][ ] setup vet appointment (by: tomorrow evening)
 3. [E][ ] dog show (at: Saturday 6pm)
____________________________________________________________
```

### `done` - Mark a task as complete
Indicate that a specified task is done.

Format: `done TASK_INDEX`
- `TASK_INDEX`: the index (indexed from 1 as seen from the output of `list`) of the task to be marked as completed

Example of usage: `done 2`
Expected outcome:
```
____________________________________________________________
 great work! I have marked this task as done:
  [D][X] setup vet appointment (by: tomorrow evening)
____________________________________________________________
```

### `delete` - Remove a task from taro
Deletes a specified task from the list of tasks stored by taro.

❗️The deleted task will be removed permanently

Format: `delete TASK_INDEX`
- `TASK_INDEX`: the index (indexed from 1 as seen from the output of `list`) of the task to be removed

Example of usage: `delete 2`

Expected outcome:
```
____________________________________________________________
 noted. I've removed this task:
  [D][X] setup vet appointment (by: tomorrow evening)
 now you have 2 tasks in the list.
____________________________________________________________
```

### `find` - Locate tasks containing a keyword
Prints out tasks containing a specified keyword. Keyword matching is case-insensitive.

Format: `find KEYWORD`
- `KEYWORD`: the word which all matching tasks should contain

Example of usage: `find dog`

Expected outcome:
```
____________________________________________________________
 here are the the matching tasks in your list
 1. [T][ ] buy dog food
 2. [E][ ] dog show (at: Saturday 6pm)
____________________________________________________________
```
### `bye` - Quit taro
Exit the programme and save all modifications to taro's task list to storage.

Format: `bye`

Example of usage: `bye`

Expected outcome:
```
____________________________________________________________
 bye. I hope to see you soon!
____________________________________________________________
```