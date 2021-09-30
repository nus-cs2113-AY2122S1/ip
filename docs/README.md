# User Guide

## About

Duke is a task manager chatbot designed to help you to manage your daily tasks.

## Features 

### Add Todo Task: `todo`

Adds a Todo task to the Duke task list. 
Todo tasks only contain the task description.

Format: `todo <your task description>`

Example inputs: `todo read book` `todo make dinner`

Example output:
```
-----------------------------------------------
Got it. I've added this task:
[T][ ] read book
-----------------------------------------------
```

### Add Deadline Task: `deadline`

Adds a Deadline task to the Duke task list. 
Deadline tasks contain the task description and a deadline (date and time).

Format: `deadline <your task description> /by <your deadline date and time>`

Example input: `deadline english essay /by Thursday 10pm`

Example output:
```
-----------------------------------------------
Got it. I've added this task:
[D][ ] english essay (by: Thursday 10pm)
-----------------------------------------------
```

### Add Event Task: `event`

Adds an Event task to the Duke task list.
Event tasks contain the task description and the event duration (date and time).

Format: `event <your task description> /at <your event date and time>`

Example input: `event science project meeting /at Sunday 4-6pm`

Example output:
```
-----------------------------------------------
Got it. I've added this task:
[E][ ] science project meeting (at: Sunday 4-6pm)
-----------------------------------------------
```


### Show Task List: `list`

Lists all the current tasks in the task list.

Format: `list`

Example input: `list`

Example output:
```
-----------------------------------------------
Here are the tasks in your list:
1. [T][ ] read book
2. [T][ ] make dinner
3. [D][ ] english essay (by: Thursday 10pm)
4. [E][ ] science project meeting (at: Sunday 4-6pm)
5. [T][X] buy lightbulbs

-----------------------------------------------
```

### Mark Task As Done: `done`

Marks a particular task as done on the task list.
Completed tasks are marked with an [X] in the list.

Format: `done <task number as indicated in task list>`

Example input: `done 3`

Example output:
```
-----------------------------------------------
Nice! I've marked this task as done:
[D][X] english essay (by: Thursday 10pm)
-----------------------------------------------
```

### Delete Task: `delete`

Deletes a particular task from the task list.

Format: `delete <task number as indicated in task list>`

Example input: `delete 2`

Example output:
```
-----------------------------------------------
Noted. I've removed this task:
[T][ ] make dinner
Now you have 4 task(s) in the list.
-----------------------------------------------
```

### Search For Task: `find`

Searches for particular tasks from the task list using the user's entered keyword.
The keyword can only be a single word.
All tasks that contain the matching keyword will be displayed.

Format: `find <your keyword here>`

Example input: `find science`

Example output:
```
-----------------------------------------------
Here are the matching tasks in your list:
1. [E][ ] science project meeting (at: Sunday 4-6pm)
-----------------------------------------------
```

### Exit Program: `bye`

Stops and exits the program. The Duke chatbot will stop running.

Format: `bye`

Example input: `bye`

Example output:
```
-----------------------------------------------
Bye. Hope to see you again soon!
-----------------------------------------------

Process finished with exit code 0
```

### Saving and Loading Tasks

After every command, the tasks in Duke are automatically saved to a 
file '/data/duke.txt', located in the same directory as your JAR file.
These tasks are also loaded into the Duke program every time Duke is run.

You are free to add or edit tasks in the txt file as you wish.

However, do note that tasks in the duke.txt file STRICTLY follow this format
and will not work otherwise:

Todo tasks: `<task type> | <task status> | <task description>`

Example: `T | false | read book`

Deadline or Event tasks: 
`<task type> | <task status> | <task description> | <date and time>`

Examples: `D | true | english essay | Thursday 10pm` 
`E | false | science project meeting | Sunday 4-6pm`

`<task type>` - `T` for Todo, `D` for Deadline, `E` for Event.

`<task status>` - `true` if task is completed, `false` if task is not completed.

`<task description>` - User's task details.

`<date and time>` - Only for Deadline and Event tasks.


