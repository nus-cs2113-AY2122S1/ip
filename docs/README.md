# Duke User Guide

# Quick Start
Ensure that you have Java ```11``` installed in your computer. 

Download the latest ip.jar from [here](https://github.com/theodorekwok/ip/releases/tag/A-Jar)

Copy the jar file to a folder that you want to run Duke from. 

At the folder where you copied the jar file run the command ```java -jar ip.jar``` in terminal. 

If Duke starts successfully, you should see the following output:
```
Hello from
____        _
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

------------------------------------------------------
Hello! I'm Duke
What can I do for you?
```
Refer the list of commands below under [Features](https://theodorekwok.github.io/ip/#features) for more details
on how to use Duke.

# Features

## Notes about command format
* Words in ```UPPER_CASE``` represent the parameters to be supplied by user. Below we have a list of parameters
that we would be referring to throughout this user guide.
  * ```TASK_NAME``` represents the name of task to be specified by user.
  * ```DATE``` represents the date specified by the user in ```YYYY MM DD HH:MM``` format.
  * ```INDEX``` represents the index value of a task. The index value would correspond to the task's 
  position in the list.
    * For example the first task in the list would have an index value of 1 while the 3rd task in the list would have a
    value of 3.
  * ```KEYWORD``` represents the keyword specified by the user.
* Extraneous parameters for commands ```list``` and ```bye``` would be ignored. 
  * For example the command ```bye 123```
  would be interpreted as ```bye```.

## Adding a Todo task

Adds a Todo task into your list of tasks. Todo tasks generally fall under the category of non-urgent items to complete
which may not have a deadline associated to it. Examples may include organising your work desk, sending the car to the
car wash etc.

Format: `todo TASK_NAME`

Usage example: `todo clean the dishes`

By following the format above, a new todo task would be added and an acknowledgement message shown below would appear.
Following the usage example above we would see the following message:
```
******************************************************
Noted! I've added a new TODO task
[T][ ] clean the dishes
Now you have 1 tasks in your list
******************************************************
```
Note: 
* A task name must be provided to successfully add a new Todo task.

## Adding a Deadline Task

Adds a deadline task into your list of tasks. Deadline tasks fall under the category of items that has 
to be completed by a certain date. Examples may include applying for an internship, submitting an assignment etc.

Format: `deadline TASK_NAME/DATE`

Usage example: `deadline submit essay/2021-10-10 23:59`

By following the format above, a new deadline task would be added and an acknowledgement message shown
below would appear.
Following the usage example above we would see the following message:
```
******************************************************
Noted! I've added a new DEADLINE task
[D][ ] submit essay (Oct 10 2021 23:59)
Now you have 2 tasks in your list
******************************************************
```
Note:
* A task name and date must be provided to successfully add a new deadline task.

## Adding an Event task

Adds an event task into your list of tasks. Event tasks fall under the category of events that happens on a
certain date. Examples may include a concert event, sports event etc.

Format: `event TASK_NAME/DATE`

Usage example: `event music concert/2021-10-10 23:59`

By following the format above, a new event task would be added and an acknowledgement message shown
below would appear.
Following the usage example above we would see the following message:
```
******************************************************
Noted! I've added a new EVENT task
[E][ ] music concert (Oct 10 2021 23:59)
Now you have 3 tasks in your list
******************************************************
```
Note: 
* A task name and date must be provided to successfully add a new event task.

## Updating a task completion status

Updates the completion status of a task as completed. This feature is used when you want to indicate that a task in your 
list has been completed. 

Format: `done INDEX`

Usage example: `done 1`

By following the format above, the task specified would be marked as completed and an acknowledgement message shown
below would appear.
Following the usage example above we would see the following message:
```
Nice! Marking clean the dishes as done!
[X] clean the dishes
```
Note: 
* An index number must be provided to successfully update the task completion status.

## Finding specific tasks by keyword

Find and list tasks that contains the keyword in its name. This feature is used when you want to find and filter the 
tasks based on its name.

Format: `find KEYWORD`

Usage example: `find clean`

By following the format above, tasks which contains the keyword in its name would appear as shown below.
Following the usage example above we would see the following message:
```
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
[T][X] clean the dishes
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
```
Note: 
* The find feature only accepts a single keyword and any additional keywords added after the first
keyword would be ignored.
For example the command: `find clean homework chores` is equivalent to `find clean`.
* If no keyword is provided all the tasks would be listed.

## Listing all tasks

Lists down all the tasks that has been added. This feature is used when you want to view all the tasks that you have.

Format: `list`

Usage example: `list`

By following the format above, all the tasks you have would appear as shown below.
Following the usage example above we would see the following message:
```
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
1. [T][X] clean the dishes
2. [D][ ] submit essay (Oct 10 2021 23:59)
3. [E][ ] music concert (Oct 10 2021 23:59)
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
```

## Deleting a task

Deletes a task in the list. This feature is used whenever you want to remove a task from your list.

Format: `delete INDEX`

Usage example: `delete 1`

By following the format above, the task specified would be removed and an acknowledgement message shown
below would appear.
Following the usage example above we would see the following message:
```
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
Noted! I've removed clean the dishes
[T][X] clean the dishes
Now you have 2 tasks in your list
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
```
Note:
* An index number must be provided to successfully delete a task.

### Exiting the program

Terminates the program. This feature is used when you have done all you needed to do with Duke. After exiting,
the remaining tasks in your list would be saved for you to reference again when you start Duke again.

Format: `bye`

Usage example: `bye`

By following the format above, this would terminate the program.
Following the usage example above we would see the following message:
```
------------------------------------------------------
Bye. Hope to see you again soon!

------------------------------------------------------
```
