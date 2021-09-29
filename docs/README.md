# User Guide

## Features 

### Add Task

Allows you to add different kinds of tasks such as ToDos, Deadlines and Events
* ToDo - A task that can be done anytime
* Deadline - A task that needs to be done before a certain time
* Events - A task that needs to be done at a certain period of time

### List

Shows you the list of tasks you have and their status of completion.

### Find

Allows you to find tasks with a specific description or date.

### Done

Allows you to mark a task as done

### Delete

Allows you to remove a task from the list

### Load

Loads tasks from the "duke.txt" file if it exists. Creates a new "duke.txt" file if it does not

### Save

Saves the tasks to the "duke.txt" file when the program is terminated

## Usage

### `todo` - todo *description* 

* Adds a ToDo task with its description to the list. 
* If successful, the system will print out a success message, the task added, and the number of tasks in the list

Example of usage: 

`todo read book`

Expected outcome:

* The success message is shown, along with the description of the task, the type of task added and the number of tasks in the list.

```
      ___________________________________________________________
      I've added this to your list :D
      [T][ ] read book
      Now you have 1 task(s) in the list
      ___________________________________________________________
```

### `deadline` - deadline *description* /by *date*

* Adds a Deadline task with its description and the date and time of the deadline to the list. 
* If successful, the system will print out a success message, the task added, and the number of tasks in the list. 
* The date of deadline is in the format of *yyyy-MM-ddTHH:mm* , where *yyyy* is the year, *MM* is the month, *dd* is the day, *HH* is the hour and *mm* is the minute.

Example of usage: 

`deadline finish homework /by 2021-09-30T23:00`

Expected outcome:

* The success message is shown, along with the description of the task, date and time of the deadline, the type of task added, and the number of tasks in the list.

```
      ___________________________________________________________
      I've added this to your list :D
      [D][ ] finish homework (by: Sep 30 2021 23:00)
      Now you have 2 task(s) in the list
      ___________________________________________________________
```


### `event` - event *description* /at *start time* /to *end time*

* Adds an Event task with its description and the date and time of the event to the list. 
* If successful, the system will return a success message, the task added, and the number of tasks in the list. 
* The date and time of the start of the event should be added before */to* and the date and time of the end of the event should be added after */to*
* The date of deadline is in the format of *yyyy-MM-ddTHH:mm* , where *yyyy* is the year, *MM* is the month, *dd* is the day, *HH* is the hour and *mm* is the minute.

Example of usage: 

`event celebrate birthday /at 2021-09-30T00:00 /to 2021-09-30T02:00`

Expected outcome:

* The success message is shown, along with the description of the task, date and time of the event, the type of task added, and the number of tasks in the list.

```
      ___________________________________________________________
      I've added this to your list :D
      [E][ ] celebrate birthday (at: Sep 30 2021 00:00 to Sep 30 02:00)
      Now you have 3 task(s) in the list
      ___________________________________________________________
```

### `list` - list

* Displays the list of all tasks and their status of completion.

Example of usage: 

`list`

Expected outcome:

* The list of tasks with their status of completion.
* If **X** appears in the second box, it means the task is completed.
* If the second box is empty, it means the task is not completed.

```
      ___________________________________________________________
      Here are the things you need to do :
      1.[T][ ] read book
      2.[D][ ] finish homework (by: Sep 30 2021 23:00)
      3.[E][ ] celebrate birthday (at: Sep 30 2021 00:00 to Sep 30 02:00)
      ___________________________________________________________
```

### `find` - find *keyword* **or** find /d *date*

* Searches through the list for tasks containing the keyword or with the same date, then displays all such tasks to the user if there is any.
* The date should be typed in the format of *yyyy-MM-dd* , where *yyyy* is the year, *MM* is the month and *dd* is the day

Example of usage: 

1. `find read`
2. `find /d 2021-09-30`

Expected outcome:

1. A list of tasks containing the keyword given is shown to the user
2. A list of tasks with the same date is shown to the user

```
      ___________________________________________________________
      Here are the matching tasks in your list : 
      1.[T][ ] read book
      ___________________________________________________________

      ___________________________________________________________
      Here are the matching tasks in your list : 
      1.[D][ ] finish homework (by: Sep 30 2021 23:00)
      2.[E][ ] celebrate birthday (at: Sep 30 2021 00:00 to Sep 30 02:00)
      ___________________________________________________________
```

### `done` - done *integer*

* Marks the task with the specific number on the list as done.
* If successful, the system will print out the task marked as done and the task will have an **X** in the second box.

Example of usage: 

`done 1`

Expected outcome:

* The first task in the list is marked as done.

```
      ___________________________________________________________
      You've completed the task! Well done!
      [T][X] read book
      ___________________________________________________________
```

### `delete` - delete *integer*

* Deletes the task with the specific number on the list.
* If successful, the system will print out a success message, the deleted task, and the number of remaining tasks.

Example of usage: 

`delete 1`

Expected outcome:

* The first task is deleted. The deleted task and the number of remaining tasks are shown.

```
      ___________________________________________________________
      Alright, I've removed this task:
      [T][X] read book
      Now you have 2 task(s) in the list 
      ___________________________________________________________
```

### `help` - help

* Asks Duke to show the list of commands.

Example of usage: 

`help`

Expected outcome:
* The list of all the commands is printed out.

```
      ___________________________________________________________
      list
      - Shows you the list of tasks you have

      todo (description)
      - Saves a general todo task

      deadline (description) /by yyyy-MM-ddTHH:mm
      - Saves a task with a given deadline
      - yyyy is the year, MM is the month, dd is the day, HH is the hour,
        and mm is the minute 

      event (description) /at yyyy-MM-ddTHH:mm /to yyyy-MM-ddTHH:mm
      - Saves an event happening at a certain time period
      - yyyy is the year, MM is the month, dd is the day, HH is the hour,
        and mm is the minute 

      delete (integer)
      - Deletes a task with the corresponding number on the list

      done (integer)
      - Marks the task with the corresponding number as done

      find (keyword)
      - Finds tasks that contains the String given

      find /d yyyy-MM-dd
      - Finds tasks with the same date as the date given
      - yyyy is the year, MM is the month and dd is the day
      ___________________________________________________________
```

### `bye` - bye

* Terminates the program and saves the tasks to the file "duke.txt".

Example of usage: 

`bye`

Expected outcome:

* The exit message is printed.

```
      ___________________________________________________________
      Bye, hope to see you again soon! :)
      ___________________________________________________________
```
