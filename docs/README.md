# User Guide

## Introduction

Ricky the Task Racoon is a **Task Management Application** that allows one to **keep track of various tasks** through a
Command Line Interface (CLI). A fast typist can manage her tasks more efficiently with Ricky than with traditional
Graphical User Interface (GUI) applications.

## Start Guide

1. Ensure that Java Runtime Environment (JRE) 11 is installed on your system
2. Download the `ip.jar` file from [here](https://github.com/CrimsonTitan79/ip/releases/download/A-Release/ip.jar) to a directory of your choice on your system.
3. Execute `java -jar ip.jar` in a terminal in the directory chosen previously. The expected output should be:

```
___________________________________________________________________________________________

	                ▄▄▄█▄▄▄▄▄▄▄▄▄▄▄██▄▄▄                                                     
	          ▄▄████████████████▄███████▄▄▄▄▄                                                
       ▄▄▄████████▄████████▄█████████████▄▄▄                                             
    ▄▄▄██████████████████████████████████████▄                                           
   █████████████████████████████████████▄██████▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄                         
 ▄████████▄██████████████▄████████████████████▄▄████████████████▄▄▄▄                     
   ▀▀▀▀▀▀▀█████████████████████▄████████████▄███████████████████████▄▄▄                  
             ▀██████████▄█████████████▄████▄███████████████████████████▄▄           ▄▄   
                ▀▀▄▄▄█████████████████████▄██████████████████████████████▄▄      ▄▄█▄█   
                     ▀▀▀███████▄▄▄▄▄▄▄█████████████████████████████████████▄▄  ▄▄█████   
                                        ▀▄███████████████████▄████▄▄▄▄▄▄▄█▄▄▄▄▄▄▄█▄██    
                                         ██████████▄▄█████▄███████▄████████▄██████▄▄█    
                                         ███████████▄▄██████████▄██▄█████▄████▄▄▄████    
                                         ▀▄██████████████████████▄▄█████▄█▄██▄▄█████▄    
                                          ████████████▄▄██████████▄▄██▄▄█▄█▄▄██▄▄███▄    
                                          ▀▄████████████████▄▄▄█▄███▄▄████▄▄██▄▄▄▄▄▄▄   
                                            ▀▄██████▄▄███████▄▄▄▄▄██▄█▄▄█████▄▄▄▄█████▄▄ 
                                              ████▄▄████▄▄▀▀▄█▄█▀ ▀▄▄▄▄▀   ▀▄▄▄▄██▄▄▄▄▄██
                                               █▄▄██▄▀▀▀     ▀▀▀▀   ▀▄▄▄       ▀▀▀▀▀     
                                              ▄▄██▄▄▄▄              ▄███                 
                                              ██▄▀▄▀▄               ▄█▀█                 
                                              █▀█▀▀                   ▀▀                 
                                              ▀  ▀                                       

Hello! I'm Ricky the Task Racoon!
How can I help you?
___________________________________________________________________________________________
```

4. Start adding your tasks to Ricky!

## Features

### Viewing help: help OR h

- Displays all available commands
- Format: `help` OR `h`

Example of usage:

```
h
```

Expected outcome:

```
List of commands:
1. l OR list 
2. todo [TASK DESCRIPTION]
3. deadline [TASK DESCRIPTION] /by [DATE_AND_OR_TIME]
4. event [TASK DESCRIPTION] /at [DATE_AND_OR_TIME]
5. delete [TASK NUMBER]
6. done [TASK NUMBER]
7. find [PART OF TASK DESCRIPTION]
8. h OR help
9. bye
```

### Adding a Todo Task

Adds a todo task without any date/time details

Format: `todo <TASK_NAME>`

Example of usage:

```
todo apply for badminton club
```

Expected outcome:

```
Got it. I've added this task:
[T][ ] apply for badminton club
Now you have 1 task in the list.
```

### Adding a Deadline Task

Adds a deadline task that has to be completed by a certain date/time

Format: `deadline <TASK_NAME> /by <DATE_AND_OR_TIME>`

- The `/by` must be inserted between `TASK_NAME` and `DATE_AND_OR_TIME`
- `DATE_AND_OR_TIME` is any date and or time the user wishes to specify in any format

Example of usage:

```
deadline Submit Assignment 1 /by Friday 2359
```

Expected outcome:

``` 
Got it. I've added this task:
[D][ ] Submit Assignment 1 (by: Friday 2359)
Now you have 2 tasks in the list.
```

### Adding an Event Task

Adds an event task that will happen at a specifc time in the future

Format: `event <TASK_NAME> /at <DATE_AND_OR_TIME>`

- The `/at` must be inserted between `TASK_NAME` and `DATE_AND_OR_TIME`
- `DATE_AND_OR_TIME` is any date and or time the user wishes to specify in any format

Example of usage:

```
event Dental Appointment /at 19 July 1pm
```

Expected outcome:

```
Got it. I've added this task:
[E][ ] Dental Appointment (at: 19 July 1pm)
Now you have 3 tasks in the list.
```

### List all Tasks

Displays a list of all tasks.

Format: `list`

Example of usage:

```
list
```

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ] apply for badminton club
2.[D][ ] Submit Assignment 1 (by: Friday 2359)
3.[E][ ] Dental Appointment (at: 19 July 1pm)
```

### Mark as Done

Marks a task as completed.

Format: `done <TASK_NUMBER>`

Example of usage:

```
done 3
```

Expected outcome:

```
Nice! I've marked this task as done:
[E][X] Dental Appointment (at: 19 July 1pm)
```

### Find Tasks

Displays tasks with a keyword that the user searches for.

Format: `find <KEYWORD>`

Example of usage:

```
find club
```

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][ ] apply for badminton club
```

### Delete a Task

Deletes a task from the list.

Format: `delete <TASK_NUMBER>`

Example of usage:

```
delete 2
```

Expected outcome:

```	
Noted. I've removed this task:
[D][ ] Submit Assignment 1 (by: Friday 2359)
Now you have 2 tasks in the list.
```

### Exiting the Program

Ends and exits Ricky.

Format: `bye`

Expected outcome:

```	
Bye. Hope to see you again soon!
```

## Notes:

The data in the task list is automatically saved in DukeData/data.txt, in the directory where ip.jar is.

## Command Summary

| Action       | Format, Examples                                                                                                   |
| :---         | :---                                                                                                               |
| ** Help**     | `help`                                                                                                             |
| ** List**     | `list`                                                                                                             |
| ** Todo**     | `todo <TASK_NAME>`<br/>eg. `todo play guitar`                                                                     |
| ** Deadline** | `deadline <TASK_NAME> /by <DATE_AND_OR_TIME>`<br/>eg. `deadline return library book /by Friday`                   |
| ** Event**    | `event <TASK_NAME> /at <DATE_AND_OR_TIME>`<br/>eg. `event circus /at Saturday 3pm`                                |
| ** Done**     | `done <TASK_NUMBER>`<br/>eg. `done 1`                                                                             |
| ** Find**     | `find <KEYWORD>`<br/>eg. `find circus`                                                                            |
| ** Delete**   | `delete <TASK_NUMBER>`<br/>eg. `delete 4`                                                                         |
| ** Exit**     | `bye`                                                                                                              |
