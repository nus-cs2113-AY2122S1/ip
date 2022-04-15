# Hal2113: User Guide

Hal2113 is an intelligent bot that can schedule and manage tasks on the command line interface (CLI).

___

## Setting up Hal 2113

---
1. Press `View on Github` at the top of this page. 
2. Click on `Release` tab on the right of the repo. 
3. Click on the latest release version
4. Click on `ip.jar` under the Assets tab. This will download the file to your computer.
5. Open up a CLI on your computer.
6. Enter `java -jar ip.jar` to start Hal 2113 in the CLI.
7. If you have successfully run the programme, a greeting from Hal2113 should appear as follows:


````

Hello! I'm HAL 2113
  _(\    |@@|
(__/\__ \--/ __
   \___|----|  |   __
       \ }{ / )_ / _\
       /\__/\ \__O (__
      (--/\--)    \__/
      _)(  )(_
     `---''---```

____________________________________________________________
What can I do for you? You can enter the following commands
1. 'todo <task>' (replace task with any agenda you wish to add to the list)
2. 'deadline <task> /by <yyyy-mm-dd>' (eg. deadline task1 /by 2021-09-31)
3. 'event <task> /at <yyyy-mm-dd>' (eg. event running /at 2021-09-31)
4. 'list' (to list all saved tasks)
5. 'done <task index>' (to mark a completed task; eg. done 2)
6. 'delete <task index>' (to delete a saved task; eg. delete 2)
7. 'find <task>' (to find a saved task; eg find homework)
8. 'bye' (to exit the program!)
Enter command:

````




Congrats! You are now ready to use Hal 2113! Enjoy!\
Refer to the features below to explore what you can do in detail
## Features summary

Feature | Description
--- | ---
`todo` | Add new todo task
`deadline` | Add new deadline task
`event` | Add new event task
`done` | Mark task as done
`delete` | Delete task
`find` | Find task by keyword
`list` | List all tasks
`bye` | Exit Hal2113

----
## Features 
### Add Todo: `todo`

Add a Todo to the task list.\
A Todo is a task that only contains a description.\
To add a todo, type `todo <task name>`.
\
\
**Format:** `todo <task name>`\
**Example**
```
todo task 1
```

### Add Deadline: `deadline` 

Add a Deadline task to the task list.\
A deadline is a task that contains a description and a time period of when the task is due.\
To add a deadline, type `deadline <task name> /by <yyyy-mm-dd>`, where `yyyy-mm-dd` represents a date.\
If the date is specified in the wrong format, today's date will be used as default.\
\
**Format:** `deadline <task name> /by <yyyy-mm-dd>`\
**Example**
```
deadline assignment 3 /by 2021-09-30
```

### Add Event: `event`

Add an Event task to the task list.\
An event is a task that contains a description and a time period of when the task happens.\
To add an event, type `event <task name> /at <yyyy-mm-dd>`, where `yyyy-mm-dd` represents a date.\
If the date is specified in the wrong format, today's date will be used as default.\
\
**Format:** `event <task name> /at <yyyy-mm-dd>`\
**Example**
```
event final examinations /at 2021-11-30
```

### Mark task as done: `done`

Mark a specified task as done. To mark a task as done, type\
`done <task index>`, where `task index` is the position of the task in the list.

**Format:** `done <task index>`\
**Example**
```
todo 1
```

### Delete task: `delete`
To delete a specified task, type\
`delete <task index>`, where `task index` is the position of the task in the list.

**Format:** `delete <task index>`\
**Example**
```
delete 1
```

### Find task by keyword: `find`
To find a task, type`find <keyword>`, where `keyword` is the search term you wish to search.\
You will find all tasks which contain that keyword. The search term is case-sensitive.

**Format:** `find <keyword>`\
**Example**
```
find assignment
```

### List all tasks: `list`
Type `list` for me to list all tasks saved. The tasks will be listed in the same order as they are entered.

**Format:** `list`\
**Example**
```
list
```

### Exit programme: `bye`
To exit me, type `bye`. Hope to see you again soon!

**Format:** `bye`\
**Example**
```
bye
```

---

## Saving Data
Hal2113 auto saves your data every time you interact with the bot and enter a command.
The program will automatically create a data directory and a `hal.txt` file where the data will be saved. 

> ⚠️  **Do not edit the hal.txt file!** 
> - The program will crash if it detects an unknown format inside the file. The hal.txt file will then have to be manually deleted, causing all saved data to be lost.
