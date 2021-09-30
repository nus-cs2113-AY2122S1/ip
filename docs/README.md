# User Guide for Duke

## About Duke

Duke is a tasks management bot which helps you to manage your daily tasks. With a command-line interface, it is easy and intuitive to use. Furthermore, it is cross-platform and can be conveniently set up on your computer. 

## Set-up
1. Download the `ip.jar` file from Releases in this repository.
2. Put the `ip.jar` file into an empty foler.
3. Open a terminal/Command Prompt window and navigate to the folder containing the `ip.jar` file.
4. Make sure Java Development Kit 11 is installed on your computer. Key in the command `java -jar ip.jar` and press Enter.
5. You will see a command-line interface. You can now use it by keying commands (which will be introduced below).

## Features 

### Add different types of tasks

There are three types of tasks you can add to your list: Event, Deadline and Todo. 

### View and search for your tasks

You can view all your tasks with details such as their status (have been done or not) and their types (Event E, Deadline D and Todo T). You can even search for specific tasks using keywords. 

### Make changes to your tasks

You can mark your tasks as done and delete them after you finish them.

### Save the data locally

The list of tasks you added are automatically backed up in a local txt file. Even if you close the program, you will not lose your data. The next time you start the program, you will see them again. 

## Usage

### `event` - Add an event

Add a new Event task to the list. An event has start date/time and end date/time. 

Format: `event DESCRIPTION /at START_DATE_TIME to END_DATE_TIME` 

(`START_DATE_TIME` and `END_DATE_TIME` have the format `YYYY-MM-DD hhmm`)

Example of usage: 

`event MA2108 exam /at 2021-09-30 0800 to 2021-09-30 1000`

Expected outcome: 

A new event "MA2108 exam" from 8:00am to 10:00am on 30th Sep is added to the list.

```
event MA2108 exam /at 2021-09-30 0800 to 2021-09-30 1000
     ________________________________________________________________________________________________________
     added: [E][ ] MA2108 exam (at: 30 Sep 2021 08:00 to 30 Sep 2021 10:00)
     ________________________________________________________________________________________________________
```

### `deadline` - Add a deadline

Add a new Deadline task to the list. An deadline has a date/time by which it should be finished. 

Format: `deadline DESCRIPTION /by DATE_TIME`

(`DATE_TIME` have the format `YYYY-MM-DD hhmm`)

Example of usage: 

`deadline CS2113T ip /by 2021-10-01 2359`

Expected outcome: 

A new deadline "CS2113T ip" due on 1st Oct, 23:59pm is added to the list.

```
deadline CS2113T ip /by 2021-10-01 2359
     ________________________________________________________________________________________________________
     added: [D][ ] CS2113T ip (by: 01 Oct 2021 23:59)
     ________________________________________________________________________________________________________
```

### `todo` - Add a todo

Add a new Todo task to the list. An todo has no date/time. 

Format: `todo DESCRIPTION`

Example of usage: 

`todo prepare for CG2027 final`

Expected outcome: 

A new todo "prepare for CG2027 final" is added to the list.

```
todo prepare for CG2027 final
     ________________________________________________________________________________________________________
     added: [T][ ] prepare for CG2027 final
     ________________________________________________________________________________________________________
```

### `list` - list all tasks

All tasks with their details will be listed out. 

Format: `list`

Example of usage: 

`list`

Expected outcome: 

All tasks are listed out.

```
list
     ________________________________________________________________________________________________________
     1. [E][ ] MA2108 exam (at: 30 Sep 2021 08:00 to 30 Sep 2021 10:00)
     2. [D][ ] CS2113T ip (by: 01 Oct 2021 23:59)
     3. [T][ ] prepare for CG2027 final

     3 task(s) in total
     ________________________________________________________________________________________________________
```

### `done` - mark the task as done

Mark a selected task as done, indicating you have finished it. 

Format: `done INDEX_OF_SELECTED_TASK`

Example of usage: 

`done 1`

Expected outcome: 

The selected task is marked as done with a symbol "X" added.

```
done 1
     ________________________________________________________________________________________________________
     Nice! I've marked this task as done: 
     [E][X] MA2108 exam (at: 30 Sep 2021 08:00 to 30 Sep 2021 10:00)
     ________________________________________________________________________________________________________
```

### `find` - find specific tasks

Find tasks containing specific keywords.

Format: `find KEYWORDS`

Example of usage: 

`find MA2108`

Expected outcome: 

The tasks containing keyword "MA2108" are listed out.

```
find MA2108
     ________________________________________________________________________________________________________
     1. [E][X] MA2108 exam (at: 30 Sep 2021 08:00 to 30 Sep 2021 10:00)

     1 task(s) in total
     ________________________________________________________________________________________________________
```

### `delete` - delete selected tasks

Delete selected tasks.

Format: `delete INDEX_OF_SELECTED_TASK`

Example of usage: 

`delete 1`

Expected outcome: 

The selected task is deleted.

```
list
     ________________________________________________________________________________________________________
     1. [E][X] MA2108 exam (at: 30 Sep 2021 08:00 to 30 Sep 2021 10:00)
     2. [D][ ] CS2113T ip (by: 01 Oct 2021 23:59)
     3. [T][ ] prepare for CG2027 final

     3 task(s) in total
     ________________________________________________________________________________________________________

delete 1
     ________________________________________________________________________________________________________
     Noted! I've removed this task: 
     [E][X] MA2108 exam (at: 30 Sep 2021 08:00 to 30 Sep 2021 10:00)
     ________________________________________________________________________________________________________

list
     ________________________________________________________________________________________________________
     1. [D][ ] CS2113T ip (by: 01 Oct 2021 23:59)
     2. [T][ ] prepare for CG2027 final

     2 task(s) in total
     ________________________________________________________________________________________________________
```

### `exit` - exit the Duke program

Exit the Duke program.

Format: `exit`

Example of usage: 

`exit`

Expected outcome: 

Duke says goodbye and you exit Duke.

```
exit
     ________________________________________________________________________________________________________
     Bye. Hope to see you again soon!
     ________________________________________________________________________________________________________
```

## Summary of commands

| Command | Description |
----------|--------------
 `event DESCRIPTION /at START_DATE_TIME to END_DATE_TIME` |  Add a new event
  `deadline DESCRIPTION /by DATE_TIME` |  Add a new deadline
 `todo DESCRIPTION` |  Add a new todo
 `list` |  List all tasks
 `done INDEX_OF_SELECTED_TASK` |  Mark a task as done
  `find KEYWORDS` |  Find tasks using keyword
 `delete INDEX_OF_SELECTED_TASK` |  Delete a task
  `exit` |  Exit Duke








