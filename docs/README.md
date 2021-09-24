# User Guide
Duke is a Command Line Interface (CLI) application that assists you in
keeping track of your everyday tasks. 

## Quick start guide
### To set up in IntelliJ
- Please follow the instructions 
[here](https://github.com/brendanlsz/ip#readme)
to set up Duke on your computer.

### To run the jar file
1. Install `Java 11` on your computer.
2. Download the `ip.jar` file from 
[here](https://github.com/brendanlsz/ip/releases/tag/A-Jar).
3. Create a new folder and copy the `ip.jar` into this folder.
4. Open a terminal (such as `cmd`) in this folder.
5. To launch Duke, type `java -jar ip.jar` into your terminal.
6. You should now see a welcome message like this:
```
____________________________________________________________
 Hello! I'm Duke
 What can I do for you?
____________________________________________________________
```
7. Please refer below for the list of features and usage instructions.

## Features 

### Add `Task`

Add a new task to the list. \
Duke supports 3 types of `Task` objects namely:
- `Todo`
- `Deadline`
- `Event`

### List `Task`

List out all the current `Task` objects in the list.

### Mark `Task` as done

Once you have completed a `Task`, you can mark it as `done`.

### Delete `Task`

Delete a `Task` from the list.


### Find `Task`

List all matching `Task` objects that contain a keyword.


### Exit 

Terminates the Duke program.

### Save data to file

Converts all current `Task` data to String form and saves this to
`duke.txt`. This is done automatically.



## Usage

### Adding a `Todo`: `todo`

Adds a Todo task to the list.

Format: `todo TASK_NAME`

Example of usage: 

`todo shopping`

Expected outcome:
```
____________________________________________________________
 Got it. I've added this task:
  [T][ ] shopping
 Now you have 1 tasks in the list.
____________________________________________________________
```


### Adding a `Deadline`: `deadline`

Adds a Deadline task to the list.

Format: `deadline TASK_NAME /by DATE_TIME`

Example of usage:

- `deadline assignment /by tonight`
- `deadline submit report /by 2021-10-01 11:59`

Expected outcome:
```
____________________________________________________________
 Got it. I've added this task:
  [D][ ] submit report (by: Oct 01 2021 11:59)
 Now you have 2 tasks in the list.
____________________________________________________________
```


### Adding an `Event`: `event`

Adds an Event task to the list.

Format: `event TASK_NAME /at DATE_TIME`

Example of usage:

- `event dinner /at tomorrow night 7pm`
- `event concert /at 2021-12-11 19:00`

Expected outcome:
```
____________________________________________________________
 Got it. I've added this task:
  [E][ ] concert (at: Dec 11 2021 19:00)
 Now you have 3 tasks in the list.
____________________________________________________________
```

> **_NOTE:_**  If you wish to store DATE_TIME as a LocalDateTime
> object, please use the format `YYYY-MM-DD HH:MM`

### List all `Task`: `list`

Lists out all current `Task` objects in the list.

Format: `list`

Example of usage:

- `list`

Expected outcome:
```
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] shopping
 2.[D][ ] submit report (by: Oct 01 2021 11:59)
 3.[E][ ] concert (at: Dec 11 2021 19:00)
____________________________________________________________
```

### Marking a `Task` as done: `done`

Marks a `Task` as done. To obtain the index of the `Task` you wish to mark
as done, first use the `list` command.

Format: `done TASK_INDEX`

Example of usage:

- `done 2`

Expected outcome:
```
____________________________________________________________
 Nice! I've marked this task as done:
   [D][X] submit report (by: Oct 01 2021 11:59)
____________________________________________________________
```

### Delete a `Task`: `delete`

Deletes a `Task` from the list. To obtain the index of the `Task` you 
wish to delete, first use the `list` command.

Format: `delete TASK_INDEX`

Example of usage:

- `delete 3`

Expected outcome:
```
____________________________________________________________
 Got it! I've removed this task:
   [E][ ] concert (at: Dec 11 2021 19:00)
 Now you have 2 tasks in the list.
____________________________________________________________
```

### Find `Task`: `find`

Finds all the `Task` objects that contain a keyword, and prints
them.

Format: `find KEYWORD`

Example of usage:

- `find report`

Expected outcome:
```
____________________________________________________________
 Here are the matching tasks in your list:
 1.[D][X] submit report (by: Oct 01 2021 11:59)
____________________________________________________________
```

### Exit: `bye`

Terminates the Duke program. Upon terminating the program, the current
`Task` data is automatically saved to the file.

Format: `bye`

Example of usage:

- `bye`

Expected outcome:
```
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________

____________________________________________________________
Current tasks successfully saved at: 
C:\Users\Brendan Lau\Desktop\ip\data\duke.txt
____________________________________________________________
```
