# User Guide
Duke :robot: is a task manager to organize your list of *todos, deadlines and events* via 
the Command Line Interface (*CLI*).

- <ins>[Features & Usage](#featuresheader)</ins>
    - [Listing all commands: `@help`](#help)
    - <ins>[Displaying Stored Tasks:](#displayingheader)</ins>
        - [Listing all stored tasks: `list`](#list)
        - [Finding stored tasks by keyword: `find`](#find)
    - <ins>[Task Management:](#taskmanagementheader)</ins>
        - [Adding a Todo: `todo`](#todo)
        - [Adding a Deadline: `deadline`](#deadline)
        - [Adding an Event: `event`](#event)
        - [Marking task as done: `done`](#done)
        - [Deleting a task: `delete`](#delete)
    - [Exiting the program: `exit`](#exit)
    - [Saving the data](#save)
- [Summary of commands](#soc)

---
## Features & Usage <a name="featuresheader"></a>



<p class= "callout info"> 

> - Words in `UPPER_CASE` are parameters to be input by the user.
> - Parameters must be in the order as listed in the specified format.
> - Extraneous parameters for commands that do not take in parameters (such as `@help`,
    `list` and `exit`) will **NOT** be ignored. An unrecognized command error will be thrown
> when there are extraneous parameters for such commands. 

</p>

###  Listing all commands: <a name="help">`@help`</a>
Shows all commands that Duke can use.

Format: `@help`

<br/>

<a name="displayingheader"></a>
###Listing all stored tasks: <a name="list">`list`</a> 
Shows a list of all tasks stored in Duke.

Format: `list`

<br/>

###Finding stored tasks by keyword: <a name="find">`find`</a>
Shows a list of tasks that contain the keyword in their description.

Format: `find KEYWORD`
- The search is case-sensitive. e.g. `rope` will **NOT** match `Rope`.
- `KEYWORD` cannot be empty.

Example usage & expected output:
```
find rope
____________________________________________________________
As requested, here are the matching tasks in your list:
1. [T][ ] tie rope
2. [E][ ] jump rope (at: Sep 27 2021)
____________________________________________________________
```
<br/>

<a name="taskmanagementheader"></a>
###Adding a Todo: <a name="todo">`todo`</a>
Adds a Todo to the list of tasks.

Format: `todo TODO_DESCRIPTION`
- `TODO_DESCRIPTION` cannot be empty.

Example usage & expected output:
```
todo wash the dishes
____________________________________________________________
Alright! I've successfully added this task:
[T][ ] wash the dishes
You now have 2 tasks in the list!
____________________________________________________________
```

<br/>

###Adding a Deadline: <a name="deadline">`deadline`</a>
Adds a Deadline to the list of tasks.

Format: `deadline DEADLINE_DESCRIPTION /by DEADLINE_DATE`
- `DEADLINE_DESCRIPTION` and `DEADLINE_DATE` cannot be empty.
- `DEADLINE_DATE` **must be in the format** of 'yyyy-MM-dd', and will be reformatted
to 'MMM dd yyyy'.

Example usage & expected output:
```
deadline complete homework /by 2021-09-27
____________________________________________________________
Alright! I've successfully added this task:
[D][ ] complete homework (at: Sep 27 2021)
You now have 3 tasks in the list!
____________________________________________________________
```
<br/>

###Adding an Event: <a name="event">`event`</a>
Adds an Event to the list of tasks.

Format: `event EVENT_DESCRIPTION /by EVENT_DATE`
- `EVENT_DESCRIPTION` and `DEVENT_DATE` cannot be empty.
- `EVENT_DATE` **must be in the format** of 'yyyy-MM-dd', and will be reformatted
  to 'MMM dd yyyy'.

Example usage & expected output:
```
event jump rope /by 2021-06-14
____________________________________________________________
Alright! I've successfully added this task:
[E][ ] jump rope (at: Jun 14 2021)
You now have 4 tasks in the list!
____________________________________________________________
```

###Marking a task as done: <a name="done">`done`</a>
Marks a selected task as completed.

Format: `done TASK_NUMBER`
- `TASK_NUMBER` cannot be empty.
- `TASK_NUMBER` must be a positive integer from 1 to N, where N is the 
  total number of tasks in the list.

Example usage & expected output:
```
done 2
____________________________________________________________
Great work! I've marked this task as done:
[T][X] wash the dishes
____________________________________________________________
```
<br/>

###Deleting a task: <a name="delete">`delete`</a>
Deletes a selected task from the list of tasks.
Format: `delete TASK_NUMBER`
- `TASK_NUMBER` cannot be empty.
- `TASK_NUMBER` must be a positive integer from 1 to N, where N is the
  total number of tasks in the list.

Example usage & expected output:
```
delete 2
____________________________________________________________
Alright! I've removed this task:
[T][X] wash the dishes
Now you have 3 tasks remaining in the list.
____________________________________________________________
```

<br/>

###Exiting the program: <a name="exit">`exit`</a>
Exits the program.

Format: `exit`

<br/>

###Saving the data <a name="save"></a>
The list of tasks are saved in the hard disk automatically after the program is
exited successfully using the `exit` command.



> **Warning:** You must exit the program using the `exit` command for the data to save
successfully. Exiting the program without using the `exit` command will **NOT** save your data.



---

## Summary of commands <a name="soc"></a>
| Action | Format, Examples |
| --- | --- |
| List Commands | `@help` |
| Display Stored Tasks | `list` |
| Find Tasks by Keyword | `find KEYWORD` <br /> e.g.`find project` |
| Add a Todo   | `todo TODO_DESCRIPTION` <br /> e.g.`todo cs2113t project` |
| Add a Deadline | `deadline DEADLINE_DESCRIPTION /by DEADLINE_DATE` <br /> e.g.`deadline tP Tasks /by 2021-06-14` |
| Add a Event | `event EVENT_DESCRIPTION /at EVENT_DATE` <br /> e.g.`event tP Meeting /at 2021-09-27` |
| Mark Task as Done | `done TASK_NUMBER` <br /> e.g.`done 3` |
| Delete a Task | `delete TASK_NUMBER` <br /> e.g.`delete 1` |
| Exit the Program | `exit` |
