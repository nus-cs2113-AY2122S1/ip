# Task Manager: User Guide

This is a program developed to help you organise your tasks. Deadlines, Events and anything Todo, we've got you covered!


Prerequisite: [JDK](https://www.oracle.com/java/technologies/downloads/ "Java Development Kit") 11 or above.

##Outline
* [Getting Started](#Getting-Started)
* [Task Format](#Task-Format)
* [Adding Tasks](#Adding-Tasks)
* [Commands](#Commands)
* [Save File Format](#Save-File-Format)

-------------------------------
### Getting Started
1. Take the Java Archive and move it to a folder where you have permission to create files and directories.
2. Open a command prompt in the same folder and run the command `java -jar [program_name].jar`
3. There will be a save file `duke.txt` created in the directory `[current directory]/data`.

### Task Format
tasks are displayed in the following format when listed:  
`[T|D|E][X| ] <description> (time)` 
* `[T|D|E]`: task will show 1 character, either 'T', 'D' or 'E' based on whether the task is a Todo, 'Deadline' or 'Event'.  
* `[X| ]`: task will show a blank for a task that has not been done and a 'X' for a task that has been marked as done. 
* `(time)`: 'time' is only applicable for Deadlines and Events.  
  e.g.  
`[T][X] Get Vegetables` is a Todo that is done.  
`[D][ ] CS2106 Lab 2 (/by: 28/09/2021)` Is a Deadline that has not been done yet!

### Adding Tasks
* `todo <description>`: Inserts a new Todo into your program to track.
  * e.g. `todo buy vegetables`
* `deadline <description> /by <time>`: Inserts a new Deadline into your program to track to be done by a specific <time>.
   * e.g. `deadline CS2106 Lab 2 Submission /by 9/30/2021`
* `event <description> /at <time>`: Inserts a new Event into your program to track to be done at a specific <time>.
   * e.g. `event Zoukout /at 1/12/2018 12am`
<div style="text-align: left"><i>Note* there is no strict format for &lt;time&gt;.</i></div>

### Commands
* `list`: Lists out all the tasks you currently have saved.
* `find <description>`: Lists out all the tasks you currently have saved that contain <description>.
  * e.g. `find exam`
* `done <list number>`: Mark a task as done based on the index in the `list` command.
  * e.g. `done 3`
* `delete <list number>`: Delete a task based on the index in the `list` command.
  * e.g. `delete 3`
* `bye`: Exits the program.

###Save File Format
The program save file is stored in `[program directory]/data/duke.txt` so if you move your program, remember to move your save file with it!  
The tasks are stored in the following format:  
`<T|D|E>|<0|1>|<description>|<time>` **excluding '<' and '>'*
* `<T|D|E>`: task will store 1 character, either 'T', 'D' or 'E' based on whether the task is a Todo, 'Deadline' or 'Event'.
* `<0|1>`: task will store a '0' for a task that has not been done and a '1' for a task that has been marked as done.
* `<time>`: 'time' is only applicable for Deadlines and Events.  
  e.g. how the Tasks Added from the [Adding Tasks](#Adding-Tasks) section are stored
`T|0|buy vegetables`  
`D|0|CS2106 Lab 2 Submission|9/30/2021`  
`E|0|Zoukout|1/12/2018 12am`  
You can modify your task program by directly changing your save file though remember to make a backup beforehand!
