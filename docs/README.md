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

Example of usage: 

`todo Play Apex and Valorant With Friends`

Expected outcome:

Since "Play Apex and Valorant With Friends" is the description, it is shown on the 
notification below. And the Task is not "done" [ ].
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

Example of usage: 
`event go school for CCA /at 2021-04-23 13:50`

Since "go school for CCA" is the description, and that "2021-04-23 13:50" is the 
at DateTime. It will be shown as the following below with the description along 
with "MMM d yyyy HH:mm a" format of displaying DateTime. The task marked as not
"done" by default [ ].
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

Example of usage: 
`event watch anime /at 2021-04-23 12:00`

Since "watch anime" is the description, and that "2021-04-23 12:00" is the 
at DateTime. It will be shown as the following below with the description along 
with "MMM d yyyy HH:mm a" format of displaying DateTime. The task marked as not
"done" by default [ ].
```
    ____________________________________________________________
    Got it. I've added this task: 
      [E][ ] watch anime (at: Apr 23 2021 12:00 PM)
    Now you have 5 tasks in the list.
    ____________________________________________________________
```

### `list` - List out all the current Task

Shows the user the whole list of current Task. 

### `done` - Marks a particular Task as done

Allows the user to mark a particular task as "done" via its index from the "list" display. 

### `delete` - Delete a particular Task 

Allows the user to delete a particular task via its index from the "list" display. 

### `find` - Allows user to search for a Task 

Lets user choose a particular keyword they want to search in Task and return the list of matching
numbered Task to the terminal. 

Example of usage: 

`find [T]`

Expected outcome:

Since the keyword is [T], the program will search for todo type of Task and list it all out on 
the terminal like so. 
```
    ____________________________________________________________
     1.[T][ ] eat lunch 
	 2.[T][X] do homework
    ____________________________________________________________
```
