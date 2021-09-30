# User Guide

## Features 

### Personal Schedule 

You can tell Duke to add some tasks to the Todo list. You can also mark the task as done or even delete the item. A neat list will help
you to ensure everything is on track, and you will not miss out on the important events.

### Local Storage

Duke uses local storage to store the information of your Todo list, so do worry about someone else may steal your trip information
online. But to be careful not to mess with the data files, or else the duke may no longer remember your trip information.

### InSeNSitIVE tO CASes

Duke is not sensitive to upper or lower cases so that you do not need to worry about the casing when communicate with Duke.

## Usage

### `Hi` - Interact with Duke.

Use command `Hi` to interact with Duke by saying "Hi".

Example of usage:

`Hi`

Expected outcome:

Duke will greet you back!

```
______________________________________________________________________
HI
                                                Hello! I'm Duke
                                                What can I do for you?
______________________________________________________________________

```
### `Bye` - End the conversation with Duke.

Use command `bye` or `end` to end the conversation with Duke, but do not worry about your Todo list, it is stored locally,
the information will remain the same as long as you do not mess with the data files.

Example of usage:

`Bye`

Expected outcome:

Duke will save all the data to data files locally, and terminate the program.

```
______________________________________________________________________
bye
                                      Bye. Hope to see you again soon!
______________________________________________________________________

```

### `Todo [description]` - Add a task to Todo list.

Use command `todo` followed by the description of the task.

Example of usage: 

`todo CS2113 Tutorial`

Expected outcome:

Duke will take note of the task and add it to the Todo list.

```
______________________________________________________________________
todo CS2113 Tutorial
                                     Got it. I've added this task:
                                     [T][ ]cs2113 tutorial
                                     Now you have 1 tasks in the list.
______________________________________________________________________

```

### `Event [description] at [date]` - Add a event with date to Todo list.

Use command `event` followed by the description of the task, and `at` followed by the date.

Example of usage:

`event CS2113 Lecturte at Friday 2pm`

Expected outcome:

Duke will take note of the event and its time, then add it to the Todo list.

```
______________________________________________________________________
event CS2113 Lecturte at Friday 2pm
                                    Got it. I've added this task:
                                    [E][ ]cs2113 lecturte (friday 2pm)
                                    Now you have 1 tasks in the list.
______________________________________________________________________

```

### `Deadline [description] by [deadline]` - Add a deadline with date to Todo list.

Use command `deadline` followed by the description of the task, and `by` followed by the date.

Example of usage:

`deadline CS2113 Project by 2020-10-01`

Expected outcome:

Duke will take note of the deadline and its time, then add it to the Todo list.

```
______________________________________________________________________
deadline CS2113 Project by 2020-10-01
                                    Got it. I've added this task:
                                    [D][ ]cs2113 project (01 Oct 2020)
                                    Now you have 1 tasks in the list.
______________________________________________________________________

```

### `List` - list out to view the entire Todo list.

Use command `list` .

Example of usage:

`list`

Expected outcome:

Duke will list out the Todo List

```
______________________________________________________________________
list
                                 Now you have 3 tasks in the list.
                                 1. [T][ ]cs2113 tutorial
                                 2. [E][ ]cs2113 lecturte (friday 2pm)
                                 3. [D][ ]cs2113 project (01 Oct 2020)
______________________________________________________________________

```

### `Delete [index]` - delete a specific task in the Todo list.

Use command `delete` followed by the number index of the task.

Example of usage:

`delete 3`

Expected outcome:

Duke will remove the specific task from the Todo list.

```
______________________________________________________________________
delete 3
                                     I have deleted the following task
                                     [D][ ]cs2113 project (2020-10-1)
                                     Now you have 3 tasks in the list.
______________________________________________________________________

```

### `Done [index]` - Mark a specific task as done.

Use command `done` followed by the index of the task.

Example of usage:

`done 2`

Expected outcome:

Duke will mark the specific task as done, shown as `[X]` in the list.

```
______________________________________________________________________
done 2
                            Congrats! you have done the following task
                            [E][X]cs2113 lecturte (friday 2pm)
______________________________________________________________________

```
### `Find [description]` - Find task that contains description.

Use command `find` followed by the description or the information of the item

Example of usage:

`find lecture`

Expected outcome:

Duke will search for the tasks that contains information of the item and list out all the matches.

```
______________________________________________________________________
find lecture
                             Here are the matching tasks in your list:
                             Now you have 1 tasks in the list.
                             1. [E][X]cs2113 lecturte (friday 2pm)
______________________________________________________________________

```

### `Clear` - Clear all the tasks in the Todo list.

Use command `clear`.

Example of usage:

`clear`

Expected outcome:

Duke will take clear the entire Todo list. Now the Todo list is empty.

```
______________________________________________________________________
clear
                                                 Cleared all the tasks
______________________________________________________________________

```
