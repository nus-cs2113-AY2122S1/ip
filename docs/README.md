# Duke User Guide

Duke is a Command Line Interface chat-bot application designed to assist the user in scheduling and keeping track of todos, deadlines and events.

### Quick Start

1. Ensure you have Java 11 installed in your computer.
2. Download the latest release from this repository (likely v0.2).
3. Copy the file into your desired folder.
4. Right-click on the folder and select "Open on Windows terminal" (Open in terminal on Linux) if you are on Windows. (On macOS navigate to the folder from terminal, alternatively could see https://www.groovypost.com/howto/open-command-window-terminal-window-specific-folder-windows-mac-linux/ )
5. Enter ```java -jar Duke.jar```
6. Press enter to see a list of Valid Commands

### Features

### Summary

Command | Format
------- | --------
todo | `todo {description}` eg. `todo sleep`
event | `event {description} /at {time}` eg. `event sleep /at 25/9/2021 2330`
deadline | `deadline {description} /by {time}`
list | `list`
agenda | `agenda`
find | `find {query}` eg. `find sleep`
done | `done {index}` eg. `done 2`
delete | `delete {index}` eg. `delete 2`
bye | `bye`

#### 1. Add Todo

Format : `todo {description of task}`

Examples:
* `todo Read Book`
* `todo Excersise`
#### 2. Add Event
Format: `event {description of event} /at {time event is at}`

*Note: Entering the time of event in `dd/MM/yyyy HHmm` will enable Duke to perform `agenda` operation on those events*

Examples:
* `event Lecture /at 25/9/2021 2030` _-- Duke will be able to use agenda on this event_
* `event Lecture /at Today 2pm`      _-- Duke simply stores 'Today 2pm' as a String and this event does not show up in the agenda_

#### 3. Add Deadline
Format: `deadline {description of deadline} /by {time deadline is due by}`

*Note: Entering the time of deadline in `dd/MM/yyyy HHmm` will enable Duke to perform `agenda` operation on those events*

Examples:
* `deadline Assignment /by 25/9/2021 2030` _-- Duke will be able to use agenda on this deadline_
* `deadline Assignment /by today 8:30pm` _-- Duke simply stores 'today 8:30pm' as a String and this deadline does not show up in the agenda_

#### 4. List all tasks - View all tasks you have added
Format: `List`

#### 5. View Agenda - View all events/deadlines that are at/due today
Format: `agenda`

*Note: This functions only shows events/deadlines whose times were entered in the 'dd/MM/yyyy HHmm' format*

#### 6. Find Task - find a task by searching for a keyword
Format: `find {query}`

Examples:
* `find Book`
* `find Assignment`
* `find Lecture`

#### 7. Set task as done
Format: `done {index of task done}`

*Note: the index is the index of the task when you enter `list`*

Example interaction:
```text
User: list
Duke : 
____________________________________________________________
1.[E][ ] Lecture (at: today 8pm)
2.[T][ ] Something
3.[D][ ] assignment (by: today 2359)
____________________________________________________________
User: done 1
Duke:
 ____________________________________________________________
Good Job!! I've marked this task as done:
[E][X] Lecture (at: today 8pm)
____________________________________________________________
User: list
Duke: 
____________________________________________________________
1.[E][X] Lecture (at: today 8pm)
2.[T][ ] Something
3.[D][ ] assignment (by: today 2359)
____________________________________________________________
```

#### 8. Delete a task

Format: `delete {index of task you want to delete}` - Similar to Setting task as done

#### 9. Exit
Format: `bye`

## Acknowledgements
This project takes inspiration from https://github.com/nus-cs2113-AY2122S1/personbook for the structure of the OOP code
