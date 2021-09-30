# CS2113T iP User Guide

:robot: Duke is a command line task manager. It is modelled after the Java Mascot, Duke.

- Features and Usage
    - [:notebook_with_decorative_cover: Listing all tasks: `list`](#list)
    - Adding tasks:
        - [:memo: Todo: `todo`](#todo)
        - [:pushpin: Deadline: `deadline`](#deadline)
        - [:calendar: Event: `event`](#event)
    - [:heavy_check_mark: Marking task as completed: `done`](#done)
    - [:x: Deleting a task: `delete`](#delete)
    - [:mag_right: Searching for tasks: `find`](#find)
    - [:end: Exiting the app: `bye`](#exit)
- [:floppy_disk: Saving the data](#save)
- [Command summary](#summary)

## Features and Usage

### <a name="list"></a>:notebook_with_decorative_cover: Listing all tasks: `list`

Shows a list of all tasks in the task list.

Usage: `list`

Expected output and example of usage

```
list
____________________________________________________________
You have 2 tasks
1.[T][ ] Borrow book
2.[E][X] Google Day (at: Sep 15 2021)
____________________________________________________________
```

<br/>

### <a name="todo"></a>:memo: Adding a Todo : `todo`

Adds a Todo to the task list.

Usage: `todo TODO_DESCRIPTION`

- :warning: `TODO_DESCRIPTION` cannot be empty.

Expected output and example of usage

```
todo buy new earpiece
____________________________________________________________
Got it. I've added this task:
[T][ ] buy new earpiece
Now you have 3 tasks in the list
____________________________________________________________
```

<br/>

### <a name="deadline"></a>:pushpin:  Adding a Deadline : `deadline`

Adds a Deadline to the task list.

Usage: `deadline DEADLINE_DESCRIPTION /by DEADLINE_DATE`
-if `DEADLINE_DATE` is formatted as `yyyy-MM-dd`, it will be reformatted to `MMM dd yyyy`

- :warning: `DEADLINE_DESCRIPTION` cannot be empty.
- :warning: `DEADLINE_DATE` cannot be empty.

Expected output and example of usage

- If user enter date in `yyyy-MM-dd`, it will be reformatted to `MMM dd yyyy`

```
deadline 2113 iP /by 2021-10-01
____________________________________________________________
Got it. I've added this task:
[D][ ] 2113 iP (by: Oct 01 2021)
Now you have 4 tasks in the list
____________________________________________________________
```

- It is also possible for user to enter date in any other format, or in their own style

```
deadline 2113 iP /by whenever I feel like it :D
____________________________________________________________
Got it. I've added this task:
[D][ ] 2113 iP (by: whenever I feel like it :D)
Now you have 4 tasks in the list
____________________________________________________________
```

<br/>

### <a name="event"></a>:calendar:  Adding an Event : `event`

Adds an Event to the task list.

Usage: `event EVENT_DESCRIPTION /at EVENT_DATE`
-if `EVENT_DATE` is formatted as `yyyy-MM-dd`, it will be reformatted to `MMM dd yyyy`

- :warning: `EVENT_DESCRIPTION` cannot be empty.
- :warning: `EVENT_DATE` cannot be empty.

Expected output and example of usage

- If user enter date in `yyyy-MM-dd`, it will be reformatted to `MMM dd yyyy`

```
event Squid Game watch party /at 2021-09-26
____________________________________________________________
Got it. I've added this task:
[E][ ] Squid Game watch party (at: Sep 26 2021)
Now you have 5 tasks in the list
____________________________________________________________
```

- It is also possible for user to enter date in any other format, or in their own style

```
event Squid Game watch party /at tonight
____________________________________________________________
Got it. I've added this task:
[E][ ] Squid Game watch party (at: tonight)
Now you have 5 tasks in the list
____________________________________________________________
``` 

<br/>

### <a name="done"></a>:heavy_check_mark:  Marking task as completed : `done`

Marks a task as completed in the task list based on task number.

Usage: `done TASK_NUMBER`

- :warning: `TASK_NUMBER` must be specified, and as a positive integer.
- :warning: `TASK_NUMBER` must be within range of 1 - N, where N is the number of tasks in the task list.

Expected outcome and examples of usage:

```
done 1
____________________________________________________________
Nice! I've marked this task as done:
[T][✘] Borrow book
____________________________________________________________
```

<br/>

### <a name="delete"></a>:x: Deleting a task : `delete`

Deletes a task in the task list based on task number.

Usage: `delete TASK_NUMBER`

- :warning: `TASK_NUMBER` must be specified, and as a positive integer.
- :warning: `TASK_NUMBER` must be within range of 1 - N, where N is the number of tasks in the task list.

Expected outcome and examples of usage:

```
delete 1
____________________________________________________________
Noted. I've removed this task:
[T][ ] Borrow book
Now you have 4 tasks in the list
____________________________________________________________
```

<br/>

### <a name="find"></a>:mag_right: Searching for tasks: `find`

Searches for tasks in the task list based on the user query

Usage: `find QUERY`

- :warning: `QUERY` must be specified.
- :bulb: `QUERY` is not case-sensitive.

Expected outcome and example of usage:

```
find CS2113
____________________________________________________________
There are 2 tasks containing "CS2113"
[D][ ] cs2113 IP (by: Oct 01 2021)
[T][ ] cs2113 post-lecture quiz
____________________________________________________________
```

<br/>

### <a name="exit"></a>:end: Exiting the app: `bye`

Exits the program

Expected outcome and example of usage

```
bye
____________________________________________________________

Data is saved to hard drive.
Bye. Hope to see you again soon!
____________________________________________________________
```

<br/>

### <a name="save"></a>:floppy_disk: Saving the data

Task list data are saved in the hard disk automatically when user exits app. There is no need to save manually.

<br/>

## <a name="summary"></a>Command summary

| Command    | Format, Examples |
| --- | --- |
| List Tasks | `list` |
| Add Todo   | `todo TODO_DESCRIPTION` <br /> eg.`todo cs2113 iP` |
| Add Deadline | `deadline DEADLINE_DESCRIPTION /by DEADLINE_DATE` <br /> eg.`deadline cs2113 iP /by 2021-10-01` |
| Add Event | `event EVENT_DESCRIPTION /at EVENT_DATE` <br /> eg.`event Halloween /at 2021-10-31` |
| Completed Task | `done TASK_NUMBER` <br /> eg.`done 1` |
| Delete Task | `delete TASK_NUMBER` <br /> eg.`delete 2` |
| Find Task(s) | `find QUERY` <br /> eg.`find cs2113` |
| Exit App | `bye` |

## References

- User Guide reference: AddressBook (Level 3) <br />
  https://se-education.org/addressbook-level3/UserGuide.html
- Emoji Cheat Sheet for Github Markdown: <br />
  https://github.com/ikatyang/emoji-cheat-sheet/blob/master/README.md
