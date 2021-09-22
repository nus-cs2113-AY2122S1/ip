# User Guide
___
## Duke
Duke is a simple task management software which utilises the Command Line Interface **(CLI)**, to store and find tasks.
Through consistent effort, the management of tasks will be more efficient.
___
## Getting Started
Ensure that Java 11 and latest edition of IP.jar is installed.

1. Copy the jar file into a new folder for usage as your home folder for Duke.
2. Open the command prompt interface, navigate to the folder containing the jar file and run `java -jar IP.jar`
3. Upon launching the jar file, the output can be seen as:
````
=======================================================================================================
 ______    __                     __
/  ____|  |  |                   |__|
|  |      |  |_____    _______    __
|  |      |   __   \  /  __   |  |  |
|  |____  |  |  |  |  | |__|  |  |  |
\______|  |__|  |__|  \____/\_|  |__|
=======================================================================================================
=======================================================================================================
Hey there! I am Chai!
What are you doing today?
=======================================================================================================
Input command:
=======================================================================================================
````
4. Input commands into the command line interface after the prompt, `Input command: `. Some list of available commands include:
    * `deadline submit assignment /by 2021-10-01`
    * `done 1`
    * `bye`
5. Refer to feature list below for full details of available commands.
___

## Command Summary
Feature | Command Format
---|---
Add  | `todo <task name>` or `deadline <task name> </by> <date: yyyy-mm-dd>` or `event <task name> </at> <date: yyyy-mm-dd>`
Done| `done <task number>`
Delete| `delete <task number>`
Find| `find <keyword>`
Date| `date <date: yyyy-mm-dd>`
List| `list`
Help| `help`
Bye| `bye`
___
## Feature List
### Adding a task
There are a total of 3 different task type, *Todo*, *Deadline* and *Event*.
1. *Todo* task type is the most basic task.  To add a *Todo* task, you can enter the command in the following format: `todo <task name>`
2. *Deadline* task type has an additional attribute date. *To add a *Deadline* task, you can enter the command in the following format: `deadline <task name> </by> <date: yyyy-mm-dd>`
3. *Event* task type has an additional attribute date. *To add a *Event* task, you can enter the command in the following format: `event <task name> </at> <date: yyyy-mm-dd>`

Sample output after adding task:
````
Input command: deadline submit assignment /by 2021-10-01
=======================================================================================================
Alright! Added to the list:
  [D][ ] submit assignment (by: 01 Oct 2021)
You currently have 3 task recorded in your list.
=======================================================================================================
````
Task added will automatically be saved to a storage text file named dukeTask.txt.

### Marking task as completed
To mark a task as completed, you will need to know the task number associated with the specific task.
The format to mark task as done is : `done <task number>`. An asterisk will appear if task is done when list is printed out.

Sample output after marking a task:
````
Input command: done 1
=======================================================================================================
Well done! Task marked:
  [T][*] exercise
=======================================================================================================
````

### Deleting a task
To delete a task from task list, you will need to know the task number associated with the specific task.
The format to delete a task is : `delete <task number>`. The task will be removed from the list.

Sample output after deleting a task:
````
Input command: delete 2
=======================================================================================================
Roger! Task removed from list:
  [E][ ] attend lecture (at: 01 Oct 2021)
You currently have 3 left in the list.
=======================================================================================================
````

### Finding tasks
To find a task from task list, you will have to input specific keywords associated with the task name.
The format to find a task is : `find <keyword>`. Tasks containing keywords will be printed.

Sample output after finding task:
````
Input command: find assignment
=======================================================================================================
Listing task found:
1.[D][ ] submit assignment (by: 01 Oct 2021)
=======================================================================================================
````

### Finding tasks associated to a date
To find tasks happening on a given date, you will have to input a date in the format YYYY-MM-DD.
The format to find task associated with this date is : `date <date: yyyy-mm-dd>`. Tasks associated to this date will be printed.

Sample output after finding task associated to date:
````
Input command: date 2021-10-01
=======================================================================================================
Listing task associated to this date:
1.[D][ ] submit assignment (by: 01 Oct 2021)
2.[E][ ] attend lecture (at: 01 Oct 2021)
=======================================================================================================
````

### Listing out all tasks
List out all the task stored in the task list. The format to list task : `list`

Sample output for listing task:
````
Input command: list
=======================================================================================================
Tasks list so far:
1.[T][ ] exercise
2.[T][ ] running
3.[D][ ] submit assignment (by: 01 Oct 2021)
4.[E][ ] attend lecture (at: 01 Oct 2021)
=======================================================================================================
````

### Listing out useful commands
List out useful commands to use. The format to list commands : `help`

Sample output of listing commands:
````
Input command: help
=======================================================================================================
List of commands available:

todo <type your task here> [eg. todo swimming]
-> Adds a todo task to the task list.

deadline <type your deadline here> <SPACE> </by> <SPACE> <type a date with format yyyy-mm-dd> [eg. deadline assignment /by 2021-10-01]
-> Adds a deadline task to the task list.

event <type your event here> <SPACE> </at> <SPACE> <type a date with format yyyy-mm-dd> [eg. lecture /at 2021-10-01]
-> Adds an event task to the task list.
````

### Exiting the program
Exit from the program. The format to exit: `bye`

Sample output of exiting:
````
Input command: bye
=======================================================================================================
GoodBye! Please finish up your task!
=======================================================================================================
````
___
## Things To Note
1. Input commands are not case-sensitive.
2. All tasks and task status are directly stored in the directory data then dukeTask.txt file.
3. When prompt for input of task number, it should be strictly an integer value.