# Duke User Guide

Hello this is Duke, your friendly command-line-interface to help manage your tasks!
## Quick Start

Prerequisites: Latest version of Java 11 on your computer

1. Download Duke at [here](https://github.com/itsleeqian/ip/releases/download/A-Release/ip.jar)
2. Move the .jar file to your desired folder.
3. Open a terminal in the folder containing the .jar file, and execute the following command ```java -jar ip.jar```
4. If the program is running as intended, you should see the following:
   ```
   ____________________________________________________________
   Hello! I'm Duke
   ____        _        
   |  _ \ _   _| | _____
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   What can I do for you?
   Enter "help" if you need help using me!
   ____________________________________________________________
   ```
5. Don't worry if you see this:
   ```
   No preloaded file found! Please input your own data.
   ```
   It just means that Duke will create a file for you to store your data. You should only see this on your first use.

## Features

**Add todo task**: ``todo [description]``

Adds a new todo task by providing a description as an argument.

Example: `todo finish math homework chapter 2`

Expected output:
```
todo finish math homework chapter 2
____________________________________________________________
Got it! I've added this task: 
  [T][ ] finish math homework chapter 2
Now you have 1 tasks left in the list.
____________________________________________________________
```

**Add deadline task**: ``deadline [description] /by [due time and date]``

Adds a new deadline task by providing a description as the first argument, followed by the `/by` flag and the due date.
The due time and date can be in any format the user wishes.

Example: `deadline submit report /by this coming Friday 2pm`

Expected output:
```
deadline submit report /by this coming Friday 2pm
____________________________________________________________
Got it! I've added this task: 
  [D][ ] submit report (by: this coming Friday 2pm)
Now you have 2 tasks left in the list.
____________________________________________________________
```

**Add event task**: ``event [description] /at [event date and time]``

Adds a new event task by providing a description as the first argument, followed by the `/at` flag and the event date.
The event time and date can be in any format the user wishes.

Example: `event dinner & drinks with the boys /at Sunday 7pm, my place`

Expected output:
```
event dinner & drinks with the boys /at Sunday 7pm, my place
____________________________________________________________
Got it! I've added this task: 
  [E][ ] dinner & drinks with the boys (at: Sunday 7pm, my place)
Now you have 3 tasks left in the list.
____________________________________________________________

```

**List all tasks**: `list`

Lists all tasks in chronological order of addition.

Example: `list`

Expected output:
```
list
____________________________________________________________
Here are your remaining tasks:
1. [T][ ] finish math homework chapter 2
2. [D][ ] submit report (by: this coming Friday 2pm)
3. [E][ ] dinner & drinks with the boys (at: Sunday 7pm, my place)
____________________________________________________________
```

**Mark task as done**: `done [task number]`

Marks a task as done by specifying the task number.

Example: `done 1`

Expected output:
```
done 1
____________________________________________________________
Well done! I've marked this task as done: 
[T][X] finish math homework chapter 2
____________________________________________________________
```

**Delete a task**: `delete [task number]`

Deletes a task by specifying the task number.

Example: `delete 1`

Expected output:
```
delete 1
____________________________________________________________
Noted. I've removed this task: 
[T][X] finish math homework chapter 2
Now you have 2 tasks in the list.
____________________________________________________________
```

**Filtering tasks with keyword**: `find [keyword]`

Lists out all tasks with descriptions including the specified keyword.

Example: `find report`

Expected output:
```
find report
____________________________________________________________
Here are the matching tasks in your list:
1. [D][ ] submit report (by: this coming Friday 2pm)
____________________________________________________________
```

**Viewing instructions**: `help`

Displays a summary of instructions for using Duke.

Example: `help`

Expected output:
```
help
____________________________________________________________
Hello! Welcome to Duke. I am your personal task tracker.
As of now, I can help you track Todos, Deadlines and Events. Mark your tasks with either "todo", "deadline" or "event" at the start. 
For deadlines and events, after your task, please enter either "by (your deadline)" or "at (your event timing)".
To see all your tasks, enter "list". 
To filter your tasks using keywords, enter "find (keyword)". 
To mark a task as done, enter "done (task number)". 
To delete a task, enter "delete (task number)". 
To exit this program, enter "bye". 
And that's all! Hope you find me helpful! :) 
____________________________________________________________
```

**Ending the program**: `bye`

Terminates the Duke program. Your data will be saved into a file named `Duke.txt.`

Example: `bye`

Expected output:
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

## Command Summary

Command | Syntax | Function
--------|--------|---------
todo | `todo [description]` | Add a todo task
deadline | `deadline [description] /by [due date]` | Add a deadline task
event | `event [description] /at [event time]` | Add an event task
list | `list` | View list of all tasks
done | `done [task number]` | Marks task as done
delete | `delete [task number]` | Deletes task
find | `find [keyword]` | List out all tasks containing keyword
help | `help` | View summary of instructions while using Duke
bye | `bye` | Terminates the program
