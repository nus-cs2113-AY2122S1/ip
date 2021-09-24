# User Guide

Duke is a **desktop app for managing daily tasks, optimized for use via the command-line interface (CLI)**. If you can type fast, Duke can manage your tasks faster than traditional GUI apps.

## Table of contents

* [Quick start](#quick-start)
* [Features](#features)
  * [Adding a task](#adding-a-task)
    * [Todo: `todo`](#todo-todo)
    * [Deadline: `deadline`](#deadline-deadline)
    * [Event: `event`](#event-event)
  * [Listing all tasks: `list`](#listing-all-tasks-list)
  * [Marking a task as done: `done`](#marking-a-task-as-done-done)
  * [Deleting a task: `delete`](#deleting-a-task-delete)
  * [Filtering tasks by date: `date`](#filtering-tasks-by-date-date)
  * [Filtering tasks by keyword: `find`](#filtering-tasks-by-keyword-find)
  * [Exiting the app: `exit`](#exiting-the-app-exit)
  * [Saving the data](#saving-the-data)
  * [Editing the data file](#editing-the-data-file)
* [FAQ](#faq)
* [Command summary](#command-summary)

## Quick start

1. Ensure you have **Java 11 or above** installed in your Computer.
2. Download the latest `Duke.jar` from [here](https://github.com/richwill28/ip/releases).
3. Copy the file to the folder you want to use as the **home folder** for your Duke.
4. Double-click the file to start the app. Alternatively, you can open a terminal window and type `java -jar Duke.jar`.
5. If you have successfully run the program, a greeting from Duke should appear as follows:

```
    ____________________________________________________________
     MMMMMMMMMMMMMMMMMMMMNmd+h/....`.omohMMMMMMMMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMMMMMNs.    `-`  `  `.  -sNMMMMMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMMMMN-  ` ` ...`.-````   `:oMMMMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMMN/   ` -+hmNNNNNNdh+.```  yMMMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMMN   `/mMMMNmNNNNMNNNNy:`  .hMMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMMh..-sNNMMNN+mNNNMMNNMNNd/ `NMMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMM:`.mNNMMmNs.+NMNNMNNNNNNNmmMMMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMMm`hNNNMd/N-..yNmNNdNNNNNNNmMMMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMMMdNNNNm-+o....N+yN+oNNNNMNNMMMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMMMNNNNN:.+-....--.:yyodNNmdNMMMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMMMNNMNN.+oo-.....-ydN+hNNo-NNMMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMMMNMMMo+-yNN......-yo.yNN/:NNMMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMMMMMMM/..-o+`.........:dm+NMmMMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMMMNNMMo:.............::hMNMMmNMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMMMMNNMMhy............+:NMNdMNmMMMMMMMMMMMMMMM
     MMMMMMMMMMMMMMyohMMMNNm+.........-hmMMNNh-/mdMMMMMMMMMMMMM
     MMMMMMMMMMMMmo-.`oNMNNMMMs:--..--/NmNMNy`    hMMMMMMMMMMMM
     MMMMMMMMMMMMN++o`.NNydNNy::::/:...ohNMs`    .hMMMMMMMMMMMM

     Konnichiwa! I'm your personal maid. Call me Maid-chan!
     What can I do for you? (//ω//)
    ____________________________________________________________

```

5. Type the command below the greeting and press `Enter` to execute it. Some example commands you can try:

    * `list`: Lists all tasks.
    * `exit`: Exits the app.

6. Refer to the [Features](#features) below for details of each command.

## Features

> ℹ️ **Notes:**
>   * All commands are case-sensitive. For example, `List` does not equal `list`.
>   * Words in `UPPER_CASE` are parameters. For example, `find KEYWORD`.

### Adding a task

* ### Todo: `todo`

  Adds a todo to the task list.<br>
  A todo is a task that contains only a description.<br>
  <br>
  **Format**: `todo DESCRIPTION`
  
  **Example**:
  ```
  todo watch anime
      ____________________________________________________________
       Noted. I've added this task:
         [T][ ] watch anime
       Now you have 1 tasks in the list.
      ____________________________________________________________
  
  ```

* ### Deadline: `deadline`

  Adds a deadline to the task list.<br>
  A deadline is a task that contains a description and a time period of when the task is due.<br>
  <br>
  **Format**: `deadline DESCRIPTION /by TASK_DEADLINE`
  
  **Example**:
  ```
  deadline do project /by next week
      ____________________________________________________________
       Noted. I've added this task:
         [D][ ] do project (by: next week)
       Now you have 2 tasks in the list.
      ____________________________________________________________
  
  deadline join club /by 2020-03-14
      ____________________________________________________________
       Noted. I've added this task:
         [D][ ] join club (by: Mar 14 2020)
       Now you have 3 tasks in the list.
      ____________________________________________________________
  
  ```

* ### Event: `event`

  Adds an event to the task list.<br>
  An event is a task that contains a description and a time period of when the task occurs.<br>
  <br>
  **Format**: `event DESCRIPTION /at TASK_PERIOD`
  
  **Example**:
  ```
  event project meeting /at 2020-03-14
      ____________________________________________________________
       Noted. I've added this task:
         [E][ ] project meeting (at: Mar 14 2020)
       Now you have 4 tasks in the list.
      ____________________________________________________________
  
  ```

  > 💡 **Tip:** If the correct ISO format `YYYY-MM-DD` is given, both `Deadline` and `Event` will automatically format it.

<br>

### Listing all tasks: `list`

**Format**: `list`

**Example**:
```
list
    ____________________________________________________________
     Here are the tasks in your list:
     1. [T][ ] watch anime
     2. [D][ ] do project (by: next week)
     3. [D][ ] join club (by: Mar 14 2020)
     4. [E][ ] project meeting (at: Mar 14 2020)
    ____________________________________________________________

```

<br>

### Marking a task as done: `done`

**Format**: `done TASK_NUMBER`

**Example**:
```
done 1
    ____________________________________________________________
     Good job! I've marked this task as done:
       [T][X] watch anime
    ____________________________________________________________

```

<br>

### Deleting a task: `delete`

**Format**: `delete TASK_NUMBER`

**Example**:
```
delete 1
    ____________________________________________________________
     Noted. I've removed this task:
       [T][X] watch anime
     Now you have 3 tasks in the list.
    ____________________________________________________________

```

<br>

### Filtering tasks by date: `date`

**Format**: `date YYYY-MM-DD`

**Example**:
```
date 2020-03-14
    ____________________________________________________________
     Here are the tasks on Mar 14 2020:
     1. [D][ ] join club (by: Mar 14 2020)
     2. [E][ ] project meeting (at: Mar 14 2020)
    ____________________________________________________________

```

<br>

### Filtering tasks by keyword: `find`

**Format**: `find KEYWORD`

**Example**:
```
find project
    ____________________________________________________________
     Here are the matching tasks in your list:
     1. [D][ ] do project (by: next week)
     2. [E][ ] project meeting (at: Mar 14 2020)
    ____________________________________________________________

```

<br>

### Exiting the app: `exit`

**Format**: `exit`

**Example**:
```
exit
    ____________________________________________________________
     Bye ❤ Hope to see you again soon! (≧▽≦)
    ____________________________________________________________

```

<br>

### Saving the data

Duke data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<br>

### Editing the data file

Duke data are saved as a `txt` file located at `[JAR file location]/data/duke.txt`. Advanced users are welcome to update data directly by editing that data file.

> 🚩 **Caution**: If your changes to the data file makes its format invalid, Duke will discard all data and start with an empty data file at the next run.

## FAQ

**Q:** How do I transfer my data to another Computer?<br>
**A:** Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke home folder.<br>

## Command summary

| Command    | Format                                   | Example                                |
| :--------- | :--------------------------------------- | :------------------------------------- |
| `todo`     | `todo DESCRIPTION`                       | `todo watch anime`                     |
| `deadline` | `deadline DESCRIPTION /by TASK_DEADLINE` | `deadline do project /by next week`    |
| `event`    | `event DESCRIPTION /at TASK_PERIOD`      | `event project meeting /at 2020-03-14` |
| `list`     | `list`                                   | `list`                                 |
| `done`     | `done TASK_NUMBER`                       | `done 1`                               |
| `delete`   | `delete TASK_NUMBER`                     | `delete 1`                             |
| `date`     | `date YYYY-MM-DD`                        | `date 2020-03-14`                      |
| `find`     | `find KEYWORD`                           | `find project`                         |
| `exit`     | `exit`                                   | `exit`                                 |
