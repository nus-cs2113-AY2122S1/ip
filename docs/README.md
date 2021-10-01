# User Guide

Duke is a desktop app for managing your to-do list, optimized for use via a Command Line Interface (CLI). It has the benefits of a Graphical User Interface(GUI), which is extremely beneficial to people who can type fast.

## Quick start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest duke.jar.
3. Copy duke.jar to the folder you want to use as the home folder for your to-do list.
4. Double click the file to start the app. The GUI should look like a terminal.
5. Type the command in the terminal and press Enter to execute the command. E.g. typing bye and pressing Enter will close the window. Some example commands you can try:
   1. list: list the current to-do tasks in your system.
   2. todo "name of task" : Adds a todo task into your to-do list.
   3. delete "index": deletes the corresponding index of your to-do list.
   4. bye: Exits the app.
7. Refer to the Features below for details of each command.

## Features 

### Notes about the command format:

    1) Words in LOWER_CASE are the commands to be supplied by the user. All COMMANDS are in LOWER_CASE.
        i) E.g. in delete 3, delete is the command that you will like to use.
    2) For the command deadline and event, the back-slash is compulsory to separate taskname and task dateline.
        i) E.g. event go to birthday party /2021-12-20 is the right way to write out the event and deadline command.
    3) The deadline and event dates should be written in a yyyy-mm-dd format.
        i) E.g. yyyy-mm-dd = 1998-03-21.
    4) Add one task at a time.
        i) E.g. Add a todo task, hit ENTER before adding the next task.
    5) Extraneous parameters for commands that do not take in parameters(such as list and exit) will be ignored.
        i) E.g. If the command is list 456, the program will only recognise the list command. 

### Adding a todo task: `todo`

Adds a todo task.

Format: `todo task_name`

Example:
    i) `todo homework`

### Adding a deadline task: `deadline`

Adds a deadline task. There should be no space between "/", and the date input.

Format: `deadline deadline_task_name /yyyy-mm-dd`

Example:
    i) `deadline submit math homework /2021-12-03`

### Adding a event task: `event`

Adds a event task. There should be no space between "/", and the date input.

Format: `event event_task_name /yyyy-mm-dd`

Example:
i) `event math quiz /2021-11-05`

### Delete a task: `delete`

Deletes a task from the list.

Format: `delete INDEX`

Example:
    i) `delete 2`; Task at index 2 will be deleted.

### Marking a task as completed: `done`

Marks a task as completed in the list.

Format: `done INDEX`

Example:
i) `done 2`; Task in list with index 2 is mark as completed.

### Finding tasks by keyword: find

Finds all the task with the keyword and list it out.

Format: `find KEYWORD`

    i) _keyword_ is case insensitive. MATH matches with math.
    ii) All the task in the current task list will be searched.
    iii) Any task that matches the keyword will be returned. E.g. Math will return todo Math quiz, deadline Math Exam / 2021-03-23

Example:
    i) `find KEYWORD`

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data

Duke data are saved in the hard disk automatically in the same folder as where you placed your duke.jar at. There is no need to save manually.

### Editing the data file

The file is saved as a txt file. It is saved under the same folder as where you placed the duke.jar file. Advanced users are welcome to update data directly by editing that data file.

!CAUTION: If your data is invalid, the program will not accept the data, and it will ignore the data.

------

## FAQ

Q: How do I transfer my data to another Computer?

A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke home folder.

----

## Command Summary

|Action|Format/Examples|
---|---
|todo| todo `task_name`; E.g. `todo Math Homework`|
|deadline| deadline `deadline_task_name /yyyy-mm-dd`; E.g. `deadline Lab Report Submission /2021-12-15`|
|event| event `event_task_name /yyyy-mm-dd`; E.g. `event Birthday Party /2022-12-10`|
|delete| delete `INDEX`; E.g. `delete 2`|
|done| done `INDEX`; E.g. `done 2`|
|find| find `KEYWORD`; E.g. `find homework`|
|bye| `bye`|
|list|`list`|
