# User Guide - Duke

## Introduction

Duke is your personalized CLI (Command Line Interface) tasks management system.

## Quick start

1. Install *Java SE Development Kit 11* or any newer version
2. Download the latest `duke.jar` from [releases](https://github.com/yuejunfeng0909/ip/releases)
3. Open *Terminal* on your desktop and navigate to the folder where `duke.jar` is located.
4. Run `java -jar duke.jar`
5. Type the command in the command box and press Enter to execute it.

Some example commands you can try:

`list` : Lists all tasks.

`deadline CS2113T coding practice /by 21/9/2021 1900` : Create a deadline named "CS2113T coding practice" that needs to
be completed by 19:00 on 21 Sep 2021

`done 1` : Mark the 1st task on the task list as done.

`delete 1` : Deletes the 1st task on the task list.

`bye` : Exits the app.

## Features

### Feature - Multiple types of task

You are able to add many types of tasks: todo, deadline and event.

* Todo: a simple task that has a description
    ```
    [T][ ] Prepare shopping list
    ```
* Deadline: a task with a description and the latest date and time by which it should be completed
    ```
    [D][X] CS2113T coding practice (by 19:00, 21 Sep 2021)
    ```
* Event: a task with a description, a starting and an ending time.
    ```
    [E][ ] CS2113T tP meeting (from 21:00, 03 Jan 2022 to 22:30, 03 Jan 2022)
    ```

### Feature - Search

You can search through your task list and find out all tasks that contains the keyword.

```
find tp
          ...................................................................
          : Here are the matching tasks in your list:                       :
          :  2:[E][ ] CS2113T tP meeting (from 21:00, 03 Jan 2022 to 22:30, :
          :  03 Jan 2022)                                                   :
          ......................................................................
```

### Feature - Auto save

All changes to the task list will be automatically stored, and will be automatically loaded the next time you open Duke.

### Feature - User-friendly interface

All outputs are formatted in message bubbles to be easily distinguished from user inputs.

```
list
          ...................................................................
          : Here are the tasks in your list:                                :
          :  1:[D][ ] CS2113T coding practice (by 19:00, 21 Sep 2021)       :
          :  2:[E][ ] CS2113T tP meeting (from 21:00, 03 Jan 2022 to 22:30, :
          :            03 Jan 2022)                                         :
          :  3:[T][ ] aakldjflasdklfjlkajsdklfjlkajweklrjkljckljlkvjxckljlk :
          :           ajklejrlwr                                            :
          ......................................................................

```

## Usage

### `todo` - Add a todo task

Add new to-do task to the task list.

Format: `todo DESCRIPTION`

Example of usage: `todo Write code`

Expected outcome: A new todo named "Write code" will be added to your task list.

```
.....................................
: Got it. I've added this task:     :
:  1:[T][ ] Write code              :
: Now you have 1 tasks in the list. :
........................................
```

### `deadline` - Add a deadline task

Add new deadline task to the task list.

Format: `deadline DESCRIPTION /by TIME`(`TIME` follows d/M/yyyy kk[mm] format)

Example of usage: `deadline CS2113T coding practice /by 21/9/2021 1900`

Expected outcome: A new deadline named "CS2113T coding practice" that needs to be completed by 19:00 on 21 Sep 2021 will
be added to your task list.

```
.............................................................
: Got it. I've added this task:                             :
:  2:[D][ ] CS2113T coding practice (by 19:00, 21 Sep 2021) :
: Now you have 2 tasks in the list.                         :
................................................................
```

### `event` - Add an event

Add new event to the task list.

Format: `event DESCRIPTION /at START_TIME /to /END_TIME`(`START_TIME` and `END_TIME` follow `d/M/yyyy kk[mm]` format)

Example of usage: `event CS2113T tP meeting /at 3/1/2022 21 /to 3/1/2022 2230`

Expected outcome: A new event named "CS2113T tP meeting" that runs from 21:00 to 22:30 on 3 Jan 2022 will be added to
your task list.

```
...................................................................
: Got it. I've added this task:                                   :
:  3:[E][ ] CS2113T tP meeting (from 21:00, 03 Jan 2022 to 22:30, :
:  03 Jan 2022)                                                   :
: Now you have 3 tasks in the list.                               :
......................................................................
```

### `list` - View all tasks

Display all tasks by the order of creation.

Example outcome:

```
...................................................................
: Here are the tasks in your list:                                :
:  1:[T][ ] Write code                                            :
:  2:[D][ ] CS2113T coding practice (by 19:00, 21 Sep 2021)       :
:  3:[E][ ] CS2113T tP meeting (from 21:00, 03 Jan 2022 to 22:30, :
:  03 Jan 2022)                                                   :
......................................................................
```

### `done` or `undone` - Change task status

Mark the status of a task as "done" or "not done"

Format: `done INDEX`/`undone INDEX`

Example of usage: `done 1`

Expected outcome: The todo "Write code" will be marked as done.

```
done 1
                                  ...........................................
                                  : Ok, I have marked "Write code" as done. :
                                  ..............................................
list
          ...................................................................
          : Here are the tasks in your list:                                :
          :  1:[T][X] Write code                                            :
          :  2:[D][ ] CS2113T coding practice (by 19:00, 21 Sep 2021)       :
          :  3:[E][ ] CS2113T tP meeting (from 21:00, 03 Jan 2022 to 22:30, :
          :  03 Jan 2022)                                                   :
          ......................................................................
```

### `delete` - Delete task

Remove a task from the task list.

Format: `delete INDEX`

Example of usage: `delete 2`

Expected outcome: The deadline "CS2113T coding practice" will be deleted from the task list.

```
delete 2
              ...............................................................
              : Ok, I have deleted "CS2113T coding practice" from your list :
              ..................................................................
list
          ...................................................................
          : Here are the tasks in your list:                                :
          :  1:[T][X] Write code                                            :
          :  2:[E][ ] CS2113T tP meeting (from 21:00, 03 Jan 2022 to 22:30, :
          :  03 Jan 2022)                                                   :
          ......................................................................
```

### `find` - Find task

Search through the task list and display all tasks that has description containing the required keyword.

Format: `find KEYWORD`

Example of usage: `find tp`

Expected outcome: Since the event "CS2113T tP meeting" is the only task that contains tp in its description, only it
will be displayed.

```
find tp
          ...................................................................
          : Here are the matching tasks in your list:                       :
          :  2:[E][ ] CS2113T tP meeting (from 21:00, 03 Jan 2022 to 22:30, :
          :  03 Jan 2022)                                                   :
          ......................................................................
```

### `bye` - Exit Duke

Close the Duke application.

## Command summary

Action | Format
------------ | -------------
**Add** | `todo DESCRIPTION`<br> `deadline DESCRIPTION /by TIME`<br> `event DESCRIPTION /at START_TIME /to /END_TIME`
**List** | `list`
**Change status** | `done INDEX` <br> `undone INDEX`
**Delete** | `delete INDEX`
**Find** | `find KEYWORD`
**Exit** | `bye`


