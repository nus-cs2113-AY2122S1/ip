# User Guide

## Introduction

Duke is a desktop app for **keeping a record for all the tasks you want to note down** via a **Command Line Interface (CLI)**. You can save your paper note pad and go digital starting from Duke.

## Quick Start

1. Ensure you have **Java 11** installed in your Computer.
2. Download the latest **ip.jar** from [here](https://github.com/Xuefei2001/ip/releases/tag/v0.1).
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Use *command prompt* on your computer to navigate to where the **ip.jar** file is stored.
5. Run `java -jar ip.jar` to start the app. A greeting message with instructions on the commands you can use should be displayed.
6. Refer to the Features below for more details.

## Features

*NOTE:* 
* Words in **UPPER_CASE** in the formats are the parameters to be supplied by the user. 
* Words in **lower_case** in the formats should not be changed.
* Follow all spacing in the formats

### Viewing Help
Shows a message of all the commands you can use.  
Format: `help`  
Example:  
```
help
____________________________________________________________
Here are what I can do:
echo DESCRIPTION: I will repeat your description
todo DESCRIPTION: I will add a task with no time constraint to your task list
deadline DESCRIPTION /by TIME: I will add a task with deadline to your task list
event DESCRIPTION /at TIME: I will add a task with its time to your task list
done TASKNUMBER: I will mark this task as done
delete TASKNUMBER: I will delete this task
find KEYWORD: I will search for tasks containing the keyword
list: I will show your task list
help: view the commands you can use
bye: finish using Duke
```

### Viewing Your List
Displays all the tasks you have added(not including the tasks deleted), following the order you add each task.  
i.e. The task added first is task number one  
Format: `list`  
Example:  
```
list
____________________________________________________________
1.[D][X] print book (by: 9)
2.[E][ ] return book (at: Wed 6pm after lecture)
3.[T][ ] read a book
4.[D][ ] review a book (by: 5pm Monday 14 Aug)
5.[E][ ] Book lovers' party (at: tomorrow night)
```
* The first square bracket indicates types of the task(T for todo, D for deadline, E for event)
* The second square bracket indicates whether the task is done([ ] for not done, [X] for done)

### Add a task

Duke supports you to add three different types of tasks.  

#### Add a task without time restriction (todo)
Add a task without any time restrictions  
Format: `todo DESCRIPTION`  
Example:  
```todo read a book
____________________________________________________________
Got it. I've added this task:
[T][ ] read a book
Now you have 3 tasks in the list.
```

#### Add a task with deadline (deadline)
Add a task that needs to be done by certain time.  
Format: `deadline DESCRIPTION /by TIME`  
Example:  
```
deadline review a book /by 5pm Monday 14 Aug
____________________________________________________________
Got it. I've added this task: 
	[D][ ] review a book (by: 5pm Monday 14 Aug)
Now you have 4 tasks in the list.
```

#### Add a task with starting time (event)
Add a task that starts at certain time.  
Format: `event DESCRIPTION /at TIME`  
Example:  
```
event Book lovers' party /at tomorrow night
____________________________________________________________
Got it. I've added this task: 
	[E][ ] Book lovers' party (at: tomorrow night)
Now you have 5 tasks in the list.
```

### Mark a task as done
You can mark a task as done without deleting it, note that you need to use **the number of the task in the task list**.  
Format: `done TASKNUMBER`  
Example:  
```
done 3
____________________________________________________________
Nice! I've marked this task as done: 
	[T][X] read a book
```

### Delete a task
You can delete any task regardless of task type and if the task is marked as done, note that you need to use **the number of the task in the list**.  
Format: `delete TASKNUMBER`  
Example:  
```
delete 2
____________________________________________________________
Noted! I've deleted this task: 
	[E][ ] return book (at: Wed 6pm after lecture)
Now you have 4 tasks in the list
```

### Find by keyword(s)
Display a list of tasks containing the keyword(s), note that the search is **case-sensitive**.    
Format: `find KEYWORD`  
Example:  
```
find book
____________________________________________________________
Here are the matching tasks in your list:
1.[D][X] print book (by: 9)
2.[T][X] read a book
3.[D][ ] review a book (by: 5pm Monday 14 Aug)
____________________________________________________________
find a book
____________________________________________________________
Here are the matching tasks in your list:
1.[T][X] read a book
2.[D][ ] review a book (by: 5pm Monday 14 Aug)
____________________________________________________________
find Book
____________________________________________________________
Here are the matching tasks in your list:
1.[E][ ] Book lovers' party (at: tomorrow night)
```

### Echo
Echo what you typed.  
Format: `echo DESCRIPTION`  
Example:  
```
echo hi
____________________________________________________________
hi
```

### Exit Duke
Format: `bye`  
Example:  
```
bye
____________________________________________________________
Bye. Hope to see you again soon!
```

## Storage of data

1. A folder named "data" containing a file named "duke.txt" is automatically created if no existing one is detected in the folder you store **ip.jar** upon running Duke. This file is to store your task list.
2. Existing task list will be displayed if you have the "duke.txt" file upon running the app.
3. All changes are automatically saved after any command that makes any change.  
  
*Important:* Do not change anything of the "data" folder or "duke.txt" file in case you lose your existing task list.  
  
Example:  
* "duke.txt" first created:  
```
Folder created for storing task list
duke.txt created inside data folder for storing task list
Reading your List......
____________________________________________________________
```
* "duke.txt" already created:  
```
Nice! You already have a folder for storing task list
Nice! You already have a file inside data folder for storing task list
Reading your List......
____________________________________________________________
1.[D][X] print book (by: 9)
2.[E][ ] return book (at: Wed 6pm after lecture)
```

## Error Message
If you do follow the command formats strictly, an error message is displayed to tell you what has gone wrong. You can follow the instructions in the error message to adjust your command.  
These "false" commands do not make any changes to your task list.

## Command Summary
  
**Action** | **Format**
------------ | -------------
See all commands | `help`
View task list | `list`
Add task with no restrictions (todo)| `todo DESCRIPTION`
Add task with deadline (deadline) | `deadline DESCRIPTION /by TIME`
Add task with starting time (event) | `event DESCRIPTION /at TIME`
Mark task as done | `done TASKNUMBER`
Delete task | `delete TASKNUMBER`
find by keyword(s) | `find KEYWORD`
Echo | `echo DESCRIPTION`
Exit the app | `bye`

