# User Guide
Hello there 👋 and welcome to **Duke**! **Duke** is a Task List designed to store various type of 
task like todo, event and deadline which can be displayed to the user.  

## Setting up
- The text-art folder that contains all the text-art needs to be included in the JAR directory. 
- data/duke.txt will be created in the JAR directory if it does not exist and the duke.txt must 
be kept empty.

## Features 

### Active Update

The app will actively apply the last change to the duke.txt immediately after the command is 
entered in order to prevent the loss of data or changes when an error occur such as user 
accidentally pressing CTRL + C to the terminal... 

### Anti-Duplicate

Everytime a task is added, it is checked and ensure that no 2 task is exactly the same. 

## Usage

### `todo` - Adds a todo type of Task

Adds a todo task with the specified description (after "todo") and a notification will be 
shown on the terminal to show its been successfully added. The task will also by default 
be marked as not "done".

Example of usage: `todo Play Apex and Valorant With Friends`

Expected outcome: Since "Play Apex and Valorant With Friends" is the description, it is 
shown on the notification below. And the Task is not "done" [ ]. It will also show the current 
number of task.

```
    ____________________________________________________________
    Got it. I've added this task:
      [T][ ] Play Apex and Valorant With Friends
    Now you have 3 tasks in the list.
    ____________________________________________________________
```

### `event` - Adds a event type of Task

Adds a event task with the specified description and at DateTime. The format must 
strictly be in the form of: event <description> /at <yyyy-mm-dd HH:mm>. 

Example of usage: `event go school for CCA /at 2021-04-23 13:50`

Expected outcome: Since "go school for CCA" is the description, and that "2021-04-23 13:50" 
is the at DateTime. It will be shown as the following below with the description 
along with "MMM d yyyy HH:mm a" format of displaying DateTime. The task marked as not
"done" by default [ ]. It is also of the type [E]. It will also show the current 
number of task.

```
    ____________________________________________________________
    Got it. I've added this task: 
      [E][ ] go school for CCA (at: Apr 23 2021 13:50 PM)
    Now you have 4 tasks in the list.
    ____________________________________________________________
```

### `deadline` - Adds a deadline type of Task

Adds a deadline task with the specified description and by DateTime. The format 
must strictly be in the form of: deadline <description> /by <yyyy-mm-dd HH:mm>. 

Example of usage: `deadline watch anime /by 2021-04-23 12:00`

Expected outcome: Since "watch anime" is the description, and that "2021-04-23 12:00" 
is the at DateTime. It will be shown as the following below with the description 
along with "MMM d yyyy HH:mm a" format of displaying DateTime. The task marked as 
not "done" by default [ ]. It is also of the type [D]. It will also show the current 
number of task.

```
    ____________________________________________________________
    Got it. I've added this task:
      [D][ ] watch anime (by: Apr 23 2021 12:00 PM)
    Now you have 6 tasks in the list.
    ____________________________________________________________
```

### `list` - List out all the current Task

Shows the user the whole list of current Task and is numbered. 

Example of usage: `list`

Expected outcome: List out all the task and numbered from 1 to the last task number. 
It also have type and done status displated before the description of the task.

```
    ____________________________________________________________
     1.[T][ ] eat lunch 
     2.[E][ ] dinner (at: Apr 23 1997 23:33 PM)
     3.[T][X] Play Apex and Valorant With Friends
     4.[E][ ] go school for CCA (at: Apr 23 2021 13:50 PM)
     5.[D][ ] watch anime (by: Apr 23 2021 12:00 PM)
    ____________________________________________________________
```

### `done` - Marks a particular Task as done

Allows the user to mark a particular task as "done" via its index from the "list" display.
It must strictly be in the format of: done <Task Index>

Example of usage: `done 3`

Expected outcome: It will show a notification of the task being marked as done and this 
change will also be shown in the "list" command when it is entered. 

```
    ____________________________________________________________
    Nice! I've marked this task as done: 
     [T][X] Play Apex and Valorant With Friends
    ____________________________________________________________
```

### `delete` - Delete a particular Task 

Allows the user to delete a particular task via its index from the "list" display. 

Example of usage: `delete 5`

Expected outcome: It will show a notification of the task being deleted and the deletion 
will also take immediate effect. Calling "list" command will show that the task is no 
longer present. It will also show that the number of task have decreased to 4.

```
    ____________________________________________________________
     Noted. I've removed this task: 
     [D][X] watch anime (by: Apr 23 2021 12:00 PM)
     Now you have 4 tasks in the list.
    ____________________________________________________________
```

### `find` - Allows user to search for a Task 

Lets user choose a particular keyword they want to search in Task and return the list of matching
numbered Task to the terminal. 

Example of usage: `find [T]`

Expected outcome: Since the keyword is [T], the program will search for all todo type of Task 
and list it all out on the terminal like so. The rest of the Task will not be shown.
```
    ____________________________________________________________
     1.[T][ ] eat lunch 
	 2.[T][X] do homework
    ____________________________________________________________
```
