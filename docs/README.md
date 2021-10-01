# User Guide

Duke is a **ChatBot** that helps **record and organize the user's tasks**. It is a Command Line Interface (CLI) based program.

- [Features and Usage](#features)
  - [Adding Todo Tasks](#todo)
  - [Adding Event Tasks](#event)
  - [Adding Deadline Tasks](#deadline)
  - [Marking Tasks as Done](#done)
  - [Deleting Tasks](#delete)
  - [Finding Tasks](#find)
  - [Viewing All Tasks](#list)
  - [Getting Help](#help)
  - [Ending the Program](#bye)
- [Command Summary](#summary)

## <a name="features"></a>Features and Usage 

### <a name="todo"></a>Adding Todo Tasks: `todo`

Adds a Todo task that does not require a due date.

Format: `todo DESCRIPTION`

* `DESCRIPTION` can be any string of characters, but should not be empty.

Example of usage:
```
todo Do 50 squats
```
Expected Output:
```
Got it. I've added this task:
 [T][ ] Do 50 squats
Now you have 2 tasks in the list.
```
### <a name="event"></a> Adding Event Tasks: `event`

Adds an Event task that includes event date.

Format: `event DESCRIPTION /on DATE`

* `DESCRIPTION` can be any string of characters, but should not be empty.
* `/on` should be in between `DESCRIPTION` and `DATE`.
* `DATE` can be any string of characters, but should not be empty.

Example of usage:
```
event Go to Univeral Studio /on 7 Oct
```
Expected Output:
```
Got it. I've added this task:
 [E][ ] Go to Univeral Studio (at: 7 Oct)
Now you have 3 tasks in the list.
```

### <a name="deadline"></a>Adding Deadline Tasks: `deadline`
Adds a Deadline task that includes due date.

Format: `deadline DESCRIPTION /by DATE`

* `DESCRIPTION` can be any string of characters, but should not be empty.
* `/by` should be in between `DESCRIPTION` and `DATE`.
* `DATE` can be any string of characters, but should not be empty.

Example of usage:
```
deadline Submit CS2113T assignment /by 1 Oct
```
Expected Output:
```
Got it. I've added this task:
 [D][ ] Submit CS2113T assignment (by: 1 Oct)
Now you have 4 tasks in the list.
```
### <a name="done"></a>Marking Tasks as Done: `done`
Marks a task as done.

Format: `done INDEX`

* `INDEX` should be an integer
* `INDEX` should be in between 1 and the number of all tasks.

Example of usage:
```
done 3
```
Expected Output:
```
Nice! I've marked this task as done: 
 [D][X] Submit CS2113T assignment (by: 1 Oct)
```

### <a name="delete"></a>Deleting Tasks: `delete`
Deletes a task.

Format: `delete INDEX`

* `INDEX` should be an integer
* `INDEX` should be in between 1 and the number of all tasks.

Example of usage:
```
delete 2
```
Expected Output:
```
Got it. I've removed this task:
 [T][ ] Do 50 squats
Now you have 3 tasks in the list.
```

### <a name="find"></a>Finding Tasks with Keyword: `find`
Finds all tasks that contains a keyword in their description.

Format: `find KEYWORD`

* `KEYWORD` can be any string of characters
* `INDEX` should be in between 1 and the number of all tasks.

Example of usage:
```
find CS2113T
```
Expected Output:
```
Here are the matching tasks in your list:
 1. [T][ ] CS2113T topics revision
 2. [D][X] Submit CS2113T assignment (by: 1 Oct)
```

### <a name="list"></a>Viewing All Tasks: `list`
Lists all tasks.

Format: `list`

Example of usage:
```
list
```
Expected Output:
```
Here are the tasks in your list:
 1. [T][ ] CS2113T topics revision
 2. [E][ ] Go to Univeral Studio (at: 7 Oct)
 3. [D][X] Submit CS2113T assignment (by: 1 Oct)
```

### <a name="help"></a>Getting Help: `help`
Shows how to use all the features.

Format: `help`

Example of usage:
```
help
```
Expected Output:
```
Learn how to use Duke:
  To add a todo: todo task_name
  To add an event: event /on event_date
  To add a deadline: deadline /by deadline_date
  To mark a task as done: done task_index
  To delete a task: delete task_index
  To find a task using keyword: find keyword_name
  To view the list: list
  To get help: help
  To end the program: bye
```

### <a name="bye"></a>Ending the Program: `bye`
Terminates the program Duke.

Format: `bye`

Example of usage:
```
bye
```
Expected Output:
```
Bye. Hope to see you again soon!
```

## <a name="summary"></a>Command Summary

Action | Format | Example
------------ | ------------- | -------------
`todo` | `todo DESCRIPTION` | `todo Take vitamin C`
`event` | `event DESCRIPTION /on DATE` | `event Google Day /on 15 Sep`
`deadline` | `deadline DESCRIPTION /by DATE` | `deadline Return books /by Tomorrow`
`event` | `event DESCRIPTION /on DATE` | `event Google Day /on 15 Sep`
`done` | `done INDEX` | `done 3`
`delete` | `delete INDEX` | `delete 3`
`find` | `find KEYWORD` | `find assignment`
`list` | `list` | `list`
`help` | `help` | `help`
`bye` | `bye` | `bye`
