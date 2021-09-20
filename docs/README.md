# Duke User Guide

Duke is a chat-bot designed to help users manage their tasks.

## Commands and Features Summary

Command | Feature
-------- | ---------
`todo` | Adds a task to the list without attaching a specific date
`deadline` | Adds a task to the list with a specific deadline
`event` | Adds a task to the list as an event with the date and time
`list` | Lists all the tasks in the list
`done` | Marks a task in the list as done
`delete` | Deletes a task from the list
`clear` | Clears all the tasks in the list
`date` | Finds all the tasks in the list with the date specified
`find` | Finds all the tasks in the list with the keyword specified in its description
`echo` | Prints the input of the user
`help` | Prints the list of commands of Duke
`bye` | Exits Duke

## Quick start

1. Ensure you have Java 11 installed in your computer.
2. Download the latest `Duke.jar` from [here](https://github.com/powzx/ip/releases).
3. Copy `Duke.jar` to an empty folder.
4. On command prompt, navigate to the folder `Duke.jar` is stored.
5. Launch `Duke.jar` using `java -jar Duke.jar`

Demo:

```
java -jar duke.jar

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

## Usage of Commands

### `todo`

This command adds a task to the list that does not require a specific date attached.

Format of command:

`todo [task description]`

Demo:

```
todo go for a run

Yay! I have added the following task for you:
[T][ ] go for a run
You have 1 tasks in your list now!
____________________________________________________________
```

### `deadline`

This command adds a task to the list that has a specific deadline.

Format of command:

`deadline [task description] /by [task deadline]`

The task deadline should have the following format: `[yyyy-mm-dd]T[hh:MM]`

Demo:

```
deadline project submission /by 2021-09-20T23:59

Yay! I have added the following task for you:
[D][ ] project submission (by: Sep 20 2021, 23:59)
You have 2 tasks in your list now!
____________________________________________________________
```

### `event`

This command adds a task to the list as an event with its date and time.

Format of command:

`event [task description] /at [event date and time]`

The event date and time should have the following format: `[yyyy-mm-dd]T[hh:MM]`

Demo:

```
event Bob's wedding /at 2021-10-10T19:00

Yay! I have added the following task for you:
[E][ ] Bob's wedding (at: Oct 10 2021, 19:00)
You have 3 tasks in your list now!
____________________________________________________________
```

### `list`

This command lists all the tasks that are stored in the list.

Format of command:

`list`

Demo:

```
list

Wow! I found these tasks in your list:
1. [T][ ] go for a run
2. [D][ ] project submission (by: Sep 20 2021, 23:59)
3. [E][ ] Bob's wedding (at: Oct 10 2021, 19:00)
____________________________________________________________
```

### `done`

This command marks a task in the list as done.

Format of command:

`done [task number]`

The task number corresponding to the task can be found using `list`.

Demo:

```
done 3

Good job! You have finished the following:
[E][X] Bob's wedding (at: Oct 10 2021, 19:00)
____________________________________________________________
```

### `delete`

This command deletes a task from the list.

Format of command:

`delete [task number]`

The task number corresponding to the task can be found using `list`.

Demo:

```
delete 1

Alright, I have deleted the following task for you:
[T][ ] go for a run
You have 2 tasks in your list now!
____________________________________________________________
```

### `clear`

This command clears all the tasks from the list.

Format of command:

`clear`

Demo:

```
clear

Okay! Now your list is empty, you're FREE!
____________________________________________________________
```

### `date`

This command finds all the tasks from the list that has a specific date attached.

Format of command:

`date [date]`

The date specified should have the following format: `yyyy-mm-dd`.

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

### `find`

This command finds all the tasks from the list that has a specific keyword in its description.

Format of command:

`find [keyword]`

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

### `echo`

This command prints the input of the user onto the terminal.

Format of command:

`echo [input]`

Demo:

```
echo Hello World!

Hello World!
____________________________________________________________
```

### `help`

This command prints the help menu onto the terminal.

Format of command:

`help`

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

### `bye`

This command prompts to exit Duke.

Format of command:

`bye`

Demo:

```
bye

Bye! Have a great day ahead and see you again soon.
____________________________________________________________
```
