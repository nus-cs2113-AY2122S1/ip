# User Guide
Duke is a **desktop application for managing tasks, optimised for usage
via a Command Line Interface (CLI).** If you can type fast, Duke can help
you manage your daily tasks faster than traditional GUI application.

- [1. Quick Start](#1-quick-start)
- [2. Features](#2-features)
  - [2.1 Viewing help: `help`](#2-1-viewing-help--help)
  - [2.2 Adding a task:](#22-adding-a-task)
    - [2.2.1 Todo: `todo`](#221-todo-todo)
    - [2.2.2 Deadline: `deadline`](#222-deadline-deadline)
    - [2.2.3 Event: `event`](#223-event-event)
  - [2.3 Listing all tasks: `list`](#23-listing-all-tasks-list)
  - [2.4 Marking a task as done: `done`](#24-marking-a-task-as-done-done)
  - [2.5 Deleting a task: `delete`](#25-deleting-a-task-delete)
  - [2.6 Finding a task with a keyword: `find`](#26-finding-a-task-with-a-keyword-find)
  - [2.7 Exiting the program: `bye`](#27-exiting-the-program-bye)
  - [2.8 Saving the data](#28-saving-the-data)
- [3. FAQ](#3-faq)
- [4. Command Summary](#4-command-summary)

## 1. Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `Duke.jar` from [here](www.google.com)
3. Copy the file to the folder you want to use as the _home folder_ for Duke.
4. Open a terminal window in the _home folder_.
5. Type `java -jar Duke.jar` into the terminal to start Duke. 
6. The following display should appear in a few seconds:

    ```
    +++++++++++++++++++++++++++++++++++++++++++++
      __         __
     /  \.-"""-./  \     ____        _
     \    -   -    /    |  _ \ _   _| | _____
      |   o   o   |     | | | | | | | |/ / _ \
      \  .-'''-.  /     | |_| | |_| |   <  __/
       '-\__Y__/-'      |____/ \__,_|_|\_\___|
          `---`
    [A NUS CS2113T Project by: Peh Zhenhao, Amos]
    +++++++++++++++++++++++++++++++++++++++++++++
    
    [Duke]:
    => Hello! I'm Duke :)
    => I'm so hungry, would you mind feeding me tasks?
    
    [You]:
    => 
    ```

7. You may now enter commands into Duke. Type a command beside the cursor `=>`
and press `Enter` on your keyboard to execute the command.
8. Some example commands you can try:
    - **`help`** : Displays all commands available for the Duke application.
    - **`todo read book`** : Adds a `Todo` task with description `read book` to the tasklist.
    - **`list`** : Lists all tasks in the tasklist.
    - **`bye`** : Exit the Duke application.
9. Refer to the [Features](#2-features) below for details of each command.

## 2. Features
### 2. 1 Viewing help : `help`
Displays all commands available for the Duke application. 

**Format:** `help`

### 2.2 Adding a task:
### 2.2.1 Todo: `todo`
Adds a **todo** task to the tasklist.

**Format:** `todo [description]`
- `[description]` specifies the details of the todo.

**Example:** `todo read book`
- Adds a todo task with description `read book` to the tasklist.
- Specifies new number of tasks in tasklist.

**Expected Outcome:**
```
[You]:
=> todo read book

[Duke]:
=> Chomp-chomp! I've added this new task:
   <[T][ ] read book>
=> Now you have 1 tasks in your list.
```

### 2.2.2 Deadline: `deadline`
Adds a **deadline** task to the tasklist.

**Format:** `deadline [description] /by [due date]`
- `[description]` specifies the details of the deadline.
- `[due date]` specifies when the deadline is due.

**Example:** `deadline return book /by June 6th`
- Adds a deadline task with description `return book` and due date `June 6th` to the tasklist.
- Specifies new number of tasks in tasklist.

**Expected Outcome:**
```
[You]:
=> deadline return book /by June 6th

[Duke]:
=> Chomp-chomp! I've added this new task:
   <[D][ ] return book (by: June 6th)>
=> Now you have 2 tasks in your list.
```

### 2.2.3 Event: `event`
Adds a **event** task to the tasklist.

**Format:** `event [description] /at [occurence]`
- `[description]` specifies the details of the event.
- `[occurence]` specifies when the event occurs.

**Example:** `event project meeting /at Aug 6th 2-4pm`
- Creates an event task with description `project meeting` and occurs at `Aug 6th 2-4pm` to the tasklist.
- Specifies new number of tasks in tasklist.

**Expected Outcome:**
```
[You]:
=> event project meeting /at Aug 6th 2-4pm

[Duke]:
=> Chomp-chomp! I've added this new task:
   <[E][ ] project meeting (at: Aug 6th 2-4pm)>
=> Now you have 3 tasks in your list.
```

### 2.3 Listing all tasks: `list`
Lists all tasks in the tasklist.

**Format:** `list`
- The command displays the following information for each task:
  - **Task id**: 
    - A positive integer i.e. `1.` to identify the task
  - **Task type**:
    - A task can be either Todo, Deadline or Event and is represented with the first letter of their task type
    - `[T]` represents a Todo
    - `[D]` represents a Deadline
    - `[E]` represents a Event
  - **Whether the task is done**:
    - A completed task is identified as `[X]`
    - An incomplete task is identified as `[ ]`
  - **Task description**:
    - General details of the task
- And specifies number of completed tasks.

**Expected Outcome:**
```
[You]:
=> list

[Duke]:
=> Ahh! Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: June 6th)
3.[E][ ] project meeting (at: Aug 6th 2-4pm)
=> You have done 0/3 tasks in your list.
```

### 2.4 Marking a task as done: `done`
Marks an existing task in the tasklist as done.

**Format:** `done [task ID]`
- Mark a task with the specified `[task ID]` as done.
- The task ID refers to the index number shown in the displayed tasklist when the `list` command is entered.
- The task ID **must be a positive integer 1, 2, 3...**

**Example:** `done 1`
- Marks the task with task ID equals to `1` as done in the tasklist.
- Specifies number of completed tasks.

**Expected Outcome:**
```
[You]:
=> done 1

[Duke]:
=> Burrrp! I've marked this task as done:
   <[T][X] read book>
=> You have done 1/3 tasks in your list.
```

### 2.5 Deleting a task: `delete`
Deletes an existing task in the tasklist.

**Format:** `delete [task ID]`
- Deletes a task with the specified `[task ID]`.
- The task ID refers to the index number shown in the displayed tasklist when the `list` command is entered.
- The task ID **must be a positive integer 1, 2, 3...**

**Example:** `delete 1`
- Deletes the task with task ID equals to `1` in the tasklist.
- Specifies new number of tasks in tasklist.

**Expected Outcome:**
```
[You]:
=> delete 1

[Duke]:
=> Blaargh! I've deleted this task from the list:
   <[T][X] read book>
=> Now you have 2 tasks in your list.
```

### 2.6 Finding a task with a keyword: `find`
Find tasks whose description contains a given keyword.

**Format:** `find [keyword]`
- The search is case-sensitive. e.g. `book` will not match `Book`
- The order of the keywords matter. e.g. `return book` will not match `book return`
- The keyword will match substrings. e.g. `oo` will match with `book`
- Only the task description is searched.

**Example:** `find book`
- Finds all tasks that contains the substring `book` in their task description.
- Specifies number of successful search results returned.

**Expected Outcome:**
```
[You]:
=> find book

[Duke]:
=> Ahh! Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: June 6th)
=> It has successfully returned 2 result/s.
```

### 2.7 Exiting the program: `bye`
Displays goodbye message and exits the program.

**Format:** `bye`

**Expected Outcome:**
```
[You]:
=> bye

[Duke]:
=> Come back soon, I'm still hungry!
```

### 2.8 Saving the data
For first time users, a default folder `data` is created upon running the Duke program. 
A text file `duke.txt` is also generated in the `data` folder to serve as the local storage
to store the user's task data.

> **Note**: The `data` folder is created in the same directory as `Duke.jar`.

The user's tasklist data are saved to `duke.txt` automatically after any command
that modifies the tasklist. e.g. adding a task, deleting a task, marking a task as done.

There is no need to manually save the data.

## 3. FAQ
**Q:** How do I transfer my data to another computer?

**A:** Install the program on the other computer and replace the new empty `duke.txt`
it creates with the `duke.txt` that you wish to transfer from your previous `Duke.jar`
home folder.

## 4. Command Summary
Action   | Format                                  | Example                                   |
-------- | --------------------------------------- | ----------------------------------------- |
help     | `help`                                  | `help`                                    |
todo     | `todo [description]`                    | `todo read book`                          |
deadline | `deadline [description] /by [due date]` | `deadline return book /by June 6th`       |
event    | `event [description] /at [occurence]`   | `event project meeting /at Aug 6th 2-4pm` |
list     | `list`                                  | `list`                                    |
done     | `done [task ID]`                        | `done 1`                                  |
delete   | `delete [task ID]`                      | `delete 1`                                |
find     | `find [keyword]`                        | `find book`                               |
bye      | `bye`                                   | `bye`                                     |