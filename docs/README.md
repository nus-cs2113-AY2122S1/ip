# Austin User Guide

Hi! Austin is a **Command Line Interface(CLI)**, friendly chat-bot
application which helps the user in keeping track and scheduling todos, 
events and deadlines.
## Contents
* __Quick Start__
* __Features__
    1. Viewing list of valid commands: ```help```
    2. Adding a todo task: ```todo```
    3. Adding an event task: ```event```
    4. Adding a deadline task: ```deadline```
    5. Listing all tasks: ```list```
    6. Viewing agenda: ```agenda```
    7. Finding tasks by keyword: ```find```
    8. Deleting a task: ```delete```
    9. Clearing the task list: ```clear```
    10. Mark a task item as completed: ```done```
    11. Mark a task item as incomplete: ```undo```
    12. Exiting the program: ```bye```
    13. Saving the tasks
* __Command Summary__
## Quick Start
Ensure that you have Java ```11``` installed in your computer.

Download the latest ```ip.jar``` from [here](https://github.com/madhanse/ip/releases/tag/A-Release)

Copy your .jar file into your desired folder.

In your preferred Command Line Interface(CLI), navigate to the directory where you stored the .jar file,
and run the command ```java -jar ip.jar``` to start the application.

If Austin is executed successfully, you will see this output:
```
Hello from
        ___      __    __       _______.___________.__  .__   __.
       /   \    |  |  |  |     /       |           |  | |  \ |  |
      /  ^  \   |  |  |  |    |   (----`---|  |----|  | |   \|  |
     /  /_\  \  |  |  |  |     \   \       |  |    |  | |  . `  |
    /  _____  \ |  `--'  | .----)   |      |  |    |  | |  |\   |
   /__/     \__\ \______/  |_______/       |__|    |__| |__| \__|  
Hello! I'm Austin.
What can I do for you?
In case, if you are unsure of any commands, please type "help".
Currently, you have no tasks in your list.
```
## Features 

### Viewing list of valid commands: ```help```

Prints out the list of all valid commands.

Format: ```help```

### Adding a todo task: ```todo```

Adds a todo task into the task list. A todo task is a type of task which is not urgent to complete. 

*Note: There are no date and time details for todo task.*

Format: ```todo {description}```

Example: ```todo Exercise``` 
### Adding an event task: ```event```

Adds an event task into the task list. An event task represents the type of task which happens on a specific date
and time.

Format: ```event {description} | {date_and_time}```

*Note: Austin only accepts date and time in the format: ```d/mm/yyyy hhmm```.*

Example: ```event CS2113T lecture | 1/10/2021 1600```

### Adding a deadline task: ```deadline```

Adds a deadline task into the task list. A deadline task is a type of task which is not urgent to complete. There are
no date and time details for todo task.

Format: ```deadline {description} | {date_and_time}```

*Note: Austin only accepts date and time in the format: ```d/mm/yyyy hhmm```.*

Example: ```deadline CS2113T A-Release | 1/10/2021 2359```

### Listing all tasks: ```list```

Displays a list of all the tasks in the task list.

Format: ```list```

### Viewing agenda: ```agenda```

Displays a list of event tasks scheduled today and deadline tasks due today.

Format: ```agenda```

### Finding tasks by keyword: ```find```

Displays a list of tasks which contains the keyword input by the user in the command.

Format: ```find {keyword}```

### Deleting a task: ```delete```

Deletes a task from the task list.

Format: ```delete {task_index}```

Example: ```delete 1```

*Note: Task index starts from 1 to the current size of the task list.*

### Clearing the task list: ```clear```

Deletes all the tasks in the task list.

Format: ```clear```

### Mark a task item as completed: ```done```

Marks the specific task as completed.

Format: ```done {task_index}```

Example: ```done 1```

*Note: Task index starts from 1 to the current size of the task list.*

### Mark a task item as incomplete: ```undo```

Marks the specific task as incomplete. This is used when the user marks the wrong task as complete, so that it
can be reverted by using this command.

Format: ```undo {task_index}```

Example: ```undo 1```

*Note: Task index starts from 1 to the current size of the task list.*

### Exiting the program: ```bye```

Exits the program after this command is keyed in.

Format: ```bye```

### Saving the tasks

All the tasks are stored in the text file. After every operation (add, delete, mark), the file will be 
automatically updated after updating the task list.
## Command Summary
| **Command** | **Format**| 
| ------- | -----| 
| Add todo | `todo {description}`| 
| Add deadline | <code> deadline {description} &#124; {date_and_time} </code> | 
| Add event | <code> event {description} &#124; {date_and_time} </code> | 
| List |`list`| 
| Delete | `delete {task_index}`| 
| Clear | `clear` |
| Find | `find {keyword}`| 
| Agenda | `agenda` |
| Mark a task as done | `done {task_index}`| 
| Mark a task as not done | `undo {task_index}`|
| Help | `help` |
| Exit | `bye`| 

