# User guide for Duke 
This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Features of Duke
### Creating Tasks
These tasks include:
- Todo Tasks
- Event Tasks
- Deadline Tasks

### Marking tasks as Done
Tasks can be either unchecked, (i.e. `[ ]`) or checked (i.e. `[X]`).

### Finding tasks 
Tasks can be searched with certain keywords provided by the user. 

### Deleting tasks
Tasks can be removed from the list by the user.

### Listing tasks
The list of all tasks can be listed out by the user.

## Functions of Duke

Prerequisites: **JDK 11**, update Intellij to the most recent version.

### `Todo <taskname_here>` - This creates a simple Todo task.
###### Example of usage:
`Todo math homework`
###### Expected outcome:
```
Added a Todo task: math homework
Total unchecked items in your list: 1
```

### `Deadline <taskname_here> /by <date_here>` - This creates a Deadline task.
###### Example of usage:
`deadline math assignment /by 12 october, 2019`
###### Expected outcome:
```
Added a Deadline task: math assignment
Total unchecked items in your list: 2
```

### `Event <taskname_here> /at <date_here>` - This creates an Event task.
###### Example of usage:
`event Christmas Dinner /at 25 December, 2022`
###### Expected outcome:
```
Added an Event task: Christmas Dinner
Total unchecked items in your list: 3
```

###  `find <search_query_here>` - This prints out any task that contains `<search_query_here>`
###### Example of usage:
`find math`
###### Expected outcome:
```
Here is your list of tasks containing ' math ':
1. [ To do  ][ ] math homework 
2. [Deadline][ ] math assignment  (by: 12 october, 2019)
```
###  `done <task_number>` - This marks the task as done.
###### Example of usage:
`done 2`
###### Expected outcome:
```
You have marked item math assignment as done:
[X] math assignment
Total unchecked items in your list: 2
```
### `delete <task_number>` - This deletes the task from the task list.
###### Example of usage:
`delete 2`
###### Expected outcome:
```
You have deleted the item:
[Deadline][X] math assignment (by: 12 october, 2019)
Total unchecked items in your list: 2
```
### `list` - This lists out the whole list of tasks which have been input by the user.
###### Example of usage:
`list`
###### Expected outcome:
```
Here is your list:
1. [ To do  ][ ] math homework
2. [  Event ][ ] Christmas Dinner (at: 25 December, 2022)
Total unchecked items in your list: 2
```
### `bye` - This would exit the programme.
A quick farewell message would be printed after the user wants to exit.  
`It's over Anakin... I can finally eat my lun-`

First run of the programme should give an output like this:
   ```
    ___
 __/_  `.  .-"""-.
 \_,` | \-'  /   )`-')
  "") `"`    \  ((`"`
 ___Y  ,    .'7 /|
(_,___/...-` (_/_/ 
Hello! I'm Duke the Dancing Doggo.
Anything I can help you with, young Padawan?
_____________________________________________________
=>
   ```
