# Duke User Guide

Duke is an application designed to help users **manage their tasks via a Command Line Interface (CLI**).

## Quick Start

1. Ensure you have Java 11 installed in your computer.
2. Download the latest `Duke.jar` from [here](https://github.com/powzx/ip/releases).
3. Copy `Duke.jar` to an empty folder.
4. On command prompt, navigate to the folder `Duke.jar` is stored.
5. Launch `Duke.jar` using `java -jar Duke.jar`
6. Enter commands to interact with Duke.

Demo:

```
java -jar Duke.jar

Welcome to
 ______        _
(______)      | |
 _     _ _   _| |  _ _____
| |   | | | | | |_/ ) ___ |
| |__/ /| |_| |  _ (| ____|
|_____/ |____/|_| \_)_____)
Hello there! I'm Duke, your very helpful personal assistant chat bot.
Enter "help" to see what I can do for you!
____________________________________________________________
```

## Commands List

Action | Command Format
-------- | ---------
Add a task without a date | `todo TASK_DESCRIPTION`
Add a task with a deadline | `deadline TASK_DESCRIPTION /by TASK_DEADLINE`
Add an event with date and time | `event TASK_DESCRIPTION /at DATE_AND_TIME`
List all tasks | `list`
Mark a task as done | `done INDEX`
Delete a task | `delete INDEX`
Clear all tasks | `clear`
Find tasks with a specific date | `date DATE`
Find tasks with a specific keyword | `find KEYWORD`
Echo the user input | `echo INPUT`
View the help menu | `help`
Exit Duke | `bye`

### Notes about Command and Display Format

1. Words in uppercase are parameters that need to be entered by the user for the 
command to execute.
2. If a command expects parameters, then the user must supply them only once and in the correct order.
3. If a command does not expect parameters, supplying unnecessary parameters will not 
result in the execution of the command.
4. Tasks are displayed in the following format:
    * `[TASK_TYPE][DONE_STATUS] TASK_DESCRIPTION (TASK_DATE)` where:
       * `TASK_TYPE` is `T`, `D`, or `E` representing ToDo, Deadline, and Event respectively.
       * `DONE_STATUS` is `X` if the task is marked as done, or ` ` otherwise.
       * `TASK_DESCRIPTION` is the description of the task when it was added.
       * `TASK_DATE` is either the deadline or the event date and time (Not applicable to ToDo).

## Features and Usage of Commands

### Add a task without a date: `todo`

Adds a task to the list that does not require any specific date to be attached.

Format of command: `todo TASK_DESCRIPTION`
* `TASK_DESCRIPTION` must not be empty, otherwise the task will not be added.

Examples:
* `todo go for a run`
* `todo buy gift for mom`

Demo:

```
todo go for a run

Yay! I have added the following task for you:
[T][ ] go for a run
You have 1 tasks in your list now!
____________________________________________________________
```

### Add a task with a deadline: `deadline`

Adds a task to the list that has a specific deadline attached.

Format of command: `deadline TASK_DESCRIPTION /by TASK_DEADLINE`
* `TASK_DESCRIPTION` and `TASK_DEADLINE` must not be empty, otherwise the task will not be added.
* `/by` is required in the user input.
* `TASK_DEADLINE` needs to have the format `yyyy-mm-ddThh:MM`, otherwise the task will not be added where:
    * `yyyy` represents the four-digit year
    * `mm` represents the two-digit month
    * `dd` represents the two-digit day
    * `hh` represents the two-digit hour
    * `MM` represents the two-digit minute
    * `T` is required between the date and time

Examples:
* `deadline project submission /by 2021-09-20T23:59`
* `deadline final report submission /by 2021-10-10T17:00`

Demo:

```
deadline project submission /by 2021-09-20T23:59

Yay! I have added the following task for you:
[D][ ] project submission (by: Sep 20 2021, 23:59)
You have 2 tasks in your list now!
____________________________________________________________
```

### Add an event with date and time: `event`

Adds a task to the list as an event with its associated date and time.

Format of command: `event TASK_DESCRIPTION /at DATE_AND_TIME`
* `TASK_DESCRIPTION` and `DATE_AND_TIME` must not be empty, otherwise the task will not e added.
* `/at` is required in the user input
* `DATE_AND_TIME` needs to have the format `yyyy-mm-ddThh:MM`, otherwise the task will not be added where:
    * `yyyy` represents the four-digit year
    * `mm` represents the two-digit month
    * `dd` represents the two-digit day
    * `hh` represents the two-digit hour
    * `MM` represents the two-digit minute
    * `T` is required between the date and time

Examples:
* `event Bob's wedding /at 2021-10-10T19:00`
* `event countdown party /at 2021-12-31T20:00`

Demo:

```
event Bob's wedding /at 2021-10-10T19:00

Yay! I have added the following task for you:
[E][ ] Bob's wedding (at: Oct 10 2021, 19:00)
You have 3 tasks in your list now!
____________________________________________________________
```

### List all tasks: `list`

Lists all the tasks that are stored in the list.

Format of command: `list`

Demo:

```
list

Wow! I found these tasks in your list:
1. [T][ ] go for a run
2. [D][ ] project submission (by: Sep 20 2021, 23:59)
3. [E][ ] Bob's wedding (at: Oct 10 2021, 19:00)
____________________________________________________________
```

### Mark a task as done: `done`

Marks a task in the list as done.

Format of command: `done INDEX`
* `INDEX` corresponding to the task can be found using `list`.
* `INDEX` must not be empty and must be an integer corresponding to a task, otherwise the 
operation will not be executed.

Example:
* `done 3`

Demo:

```
list

Wow! I found these tasks in your list:
1. [T][ ] go for a run
2. [D][ ] project submission (by: Sep 20 2021, 23:59)
3. [E][ ] Bob's wedding (at: Oct 10 2021, 19:00)
____________________________________________________________
done 3

Good job! You have finished the following:
[E][X] Bob's wedding (at: Oct 10 2021, 19:00)
____________________________________________________________
```

### Delete a task: `delete`

Deletes a task from the list.

Format of command: `delete INDEX`
* `INDEX` corresponding to the task can be found using `list`.
* `INDEX` must not be empty and must be an integer corresponding to a task, otherwise the 
operation will not be executed.

Example:
* `delete 1`

Demo:

```
list

Wow! I found these tasks in your list:
1. [T][ ] go for a run
2. [D][ ] project submission (by: Sep 20 2021, 23:59)
3. [E][X] Bob's wedding (at: Oct 10 2021, 19:00)
____________________________________________________________
delete 1

Alright, I have deleted the following task for you:
[T][ ] go for a run
You have 2 tasks in your list now!
____________________________________________________________
```

### Clear all tasks: `clear`

Clears all the tasks from the list.

Format of command: `clear`

Demo:

```
clear

Okay! Now your list is empty, you're FREE!
____________________________________________________________
```

### Find tasks with a specific date: `date`

Finds all the tasks from the list that has a specific date attached.

Format of command: `date DATE`
* `DATE` must not be empty, otherwise the operation will not be executed.
* `DATE` must have the following format `yyyy-mm-dd` where:
    * `yyyy` represents the four-digit year
    * `mm` represents the two-digit month
    * `dd` represents the two-digit day

Example:
* `date 2021-09-18`

Demo:

```
list

Wow! I found these tasks in your list:
1. [T][X] buy gift
2. [D][X] final essay submission (by: Aug 22 2021, 23:59)
3. [E][ ] Julia's wedding (at: Sep 18 2021, 19:00)
4. [E][ ] team meeting (at: Sep 18 2021, 09:30)
5. [D][X] project report submission (by: Oct 10 2021, 14:00)
____________________________________________________________
date 2021-09-18

Here are your tasks that are occurring on the date specified:
1. [E][ ] Julia's wedding (at: Sep 18 2021, 19:00)
2. [E][ ] team meeting (at: Sep 18 2021, 09:30)
____________________________________________________________
```

### Find tasks with a specific keyword: `find`

Finds all the tasks from the list that has a specific keyword in its description.

Format of command: `find KEYWORD`
* `KEYWORD`must not be empty, otherwise the operation will not be executed.
* `KEYWORD` is not case-sensitive, hence `find something` and `find SomEthinG` will yield the 
same results.

Example:
* `find submission`

Demo:

```
list

Wow! I found these tasks in your list:
1. [T][X] buy gift
2. [D][X] final essay submission (by: Aug 22 2021, 23:59)
3. [E][ ] Julia's wedding (at: Sep 18 2021, 19:00)
4. [E][ ] team meeting (at: Sep 18 2021, 09:30)
5. [D][X] project report submission (by: Oct 10 2021, 14:00)
____________________________________________________________
find submission

Here are the matching tasks in your list:
1. [D][X] final essay submission (by: Aug 22 2021, 23:59)
2. [D][X] project report submission (by: Oct 10 2021, 14:00)
____________________________________________________________
```

### Echo the user input: `echo`

Prints the input of the user onto the terminal.

Format of command: `echo INPUT`
* `INPUT` must not be empty, otherwise the operation will not be executed.

Demo:

```
echo Hello World!

Hello World!
____________________________________________________________
```

### View the help menu: `help`

Prints the help menu onto the terminal.

Format of command: `help`

Demo:

```
help

Below is the list of commands and input formats I am currently able to understand:
1. list - Lists all your current tasks.
2. clear - Clears all your existing tasks in your list.
3. todo [task description] - Adds a task to your list.
4. deadline [task description] /by [due date] - Adds a task with a due date to your list.
5. event [event description] /at [date and time] - Adds an upcoming event to your list.
6. done [task number] - Marks the task as done. Use the list to check the task number!
7. delete [task number] - Deletes the task. Use the list to check the task number!
8. date [yyyy-mm-dd] - Finds tasks with the date specified attached to it.
9. find [keyword] - Finds tasks with the keyword specified in its description.
10. echo [input] - Echoes whatever your input is.
11. help - View this menu again.
12. bye - Stop talking to me and exit the program.
____________________________________________________________
```

### Exit Duke: `bye`

Exits the program.

Format of command: `bye`

Demo:

```
bye

Bye! Have a great day ahead and see you again soon.
____________________________________________________________
```

## Data Storage

Duke automatically saves the list of tasks to a text file after every command. This means that
users can recover their task list even after exiting and starting Duke again.

### Editing the data file

Advanced users can edit the data file `duke.txt` that is stored in the same folder as `Duke.jar`.

**CAUTION: Edits that make the data format invalid will cause a data corruption and
all data will be wiped and reset upon restarting `Duke.jar`.**
