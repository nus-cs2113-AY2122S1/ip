# User Guide
##Getting Started
### Preparations
Ensure that `ip.jar` is located within a folder of its own. 
Open up a Terminal and ensure to the directory is the same as the folder `ip.jar` is located in.
### Initialising Duke
Enter the following into the terminal to initialise Duke application.
```
java -jar ip.jar
```
## Features and Usage
### Feature List
- `help`
- `todo`
- `deadline`
- `event`
- `list`
- `done`
- `delete`
- `bye`

*All commands are not case-sensitive:*

`dEAdLine`, `deadline`, and `Deadline` All mean: `deadline`.
___
---

### List Commands: `help`
Prints all commands available.

**Example of usage:**

*input:*
```
help
```
---
---

### Add ToDo: `todo`
Adds a new ToDo to the task list. A ToDo is a task with a description of the task.

**Arguments:**
1. `<desc>` - Description of the ToDo task.

**Format:** `todo <desc>`

**Example of usage:**

*input:*
```
todo Eat apples
```
*output:*
```
Got it. I've added this task:
1.[T][ ] Eat apples
Now you have 1 tasks in the list.
```
---
---
### Add Deadline: `deadline`
Adds a new Deadline to the task list. A Deadline is a task with a description of the task and a deadline.

**Arguments:**
1. `<desc>` - Description of the Deadline task.
2. `<by>` - DateTime the deadline should be done by.

**Format:**
- `deadline <desc> /by <by>`

`<by>` *is accepted in the following formats:*
- `Custom input`
- `dd/mm/yyyy`
- `dd/mm/yyyy hhmm`

**Example of usage:**

*input:*
```
deadline IEM2 Assignment /by This Friday
```
*output:*
```
Got it. I've added this task:
2.[D][ ] IEM2 Assignment (by: This Friday)
Now you have 2 tasks in the list.
```
___
*input:*
```
deadLine CS2113T ip submission /by 01/10/2021 2359
```
*output:*
```
Got it. I've added this task:
3.[D][ ] CS2113T ip Submission (by: Oct 1 2021 11:59 PM)
Now you have 3 tasks in the list.
```
___
---
### Add Event: `event`
Adds a new Event to the task list. An Event is a task with a description of the task and a event.

**Arguments:**
1. `<desc>` - Description of the Event task.
2. `<at>` - DateTime or Location the Event should be happening at.

**Format:**
- `event <desc> /at <at>`

`<at>` is accepted in the following formats:
- `Custom input`
- `dd/mm/yyyy`
- `dd/mm/yyyy hhmm`

**Example of usage:**

*input:*
```
event Dance show /at Esplanade
```
*output:*
```
Got it. I've added this task:
4.[E][ ] Dance show (at: Esplanade)
Now you have 4 tasks in the list.
```
---
*input:*
```
Event No Time To Die premiere /at 30/09/2021
```
*output:*
```
Got it. I've added this task:
5.[E][ ] No Time To Die premiere (at: Sep 30 2021)
Now you have 5 tasks in the list.
```
___
---
### List Tasks: `list`
Prints all tasks in the task list.

**Arguments:**
None

**Format:** `list`

**Example of usage:**

*input*
```
list
```
*output:*
```
Here are the tasks in your list:
1.[T][ ] Eat apples
2.[D][ ] IEM2 Assignment (by: This Friday)
3.[D][ ] CS2113T ip Submission (by: Oct 1 2021 11:59 PM)
4.[E][ ] Dance show (at: Esplanade)
5.[E][ ] No Time To Die premiere (at: Sep 30 2021)

```
---
---
### Mark Task as Done: `done`
Marks a Task in the Task List as done.

**Arguments:**
1. `<index>` - Task index with respect to the list.

**Format:**
- `done <index>`

`<index>` *should only be a number from the list*

**Example of usage:**

*input:*
```
done 4
```
*output:*
```
Nice, I've marked this task as Done:
 [E][X] Dance show (at: Esplanade)
```
___
---
### Remove Task from Task List: `delete`
Delete a Task from the Task List.

**Arguments:**
1. `<index>` - Task index with respect to the list.

**Format:**
- `delete <index>`

*`<index>` should only be a number from the list*

**Example of usage:**

*input:*
```
delete 3
```
*output:*
```
Noted! I've removed this task:
3.[D][ ] CS2113T ip Submission (by: Oct 1 2021 11:59PM)
Now you have 4 tasks in the list.
```
___
---
### Remove Task from Task List: `bye`
Exits Duke application.

**Arguments:**
 None

**Format:**
- `bye`

**Example of usage:**

*input:*
```
bye
```
*output:*
```
Bye. try not to procrastinate!
```
___