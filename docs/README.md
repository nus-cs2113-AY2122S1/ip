# User Guide

Duke is a java application to manage tasks via command line interface (CLI). This app is especially beneficial to users who can type fast.

## Contents of User Guide
- [Quick start](#user-content-quick-start)
- [Features](#features)
  1. [`help` - View help](#user-content-help---view-help)
  2. [`list` - List all the tasks created](#user-content-list---list-all-the-tasks-created)
  3. [`todo` - Create a todo task](#user-content-todo---create-a-todo-task)
  4. [`deadline` - Create a deadline task](#user-content-deadline---create-a-deadline-task)
  5. [`event` - Create an event task](#user-content-event---create-an-event-task)
  6. [`done` - Check corresponding task as done](#user-content-done---check-corresponding-task-as-done)
  7. [`delete` - Delete corresponding task from list](#user-content-delete---delete-corresponding-task-from-list)
  8. [`find` - Find description tasks with the given input](#user-content-find---find-description-tasks-with-the-given-input)
  9. [`sort time` - Sort tasks based on date](#sort-time---sort-tasks-based-on-date)
  10. [`bye` - Exit program](#user-content-bye---exit-program)
- [Frequently Asked Questions(FAQ)](#frequently-asked-questionsfaq)
- [Command Summary](#command-summary)
---

## Quick Start
1. Ensure that you have [java](https://aws.amazon.com/corretto/) (version 11 and above) installed in your computer.
2. Download duke.jar from A-Release tag and store it into a folder.
3. Store duke.jar file to the file that you would want to store
4. Open command prompt(for windows) or terminal(for mac and linux) and change directory to the folder .jar file is stored
5. Run command ```java -jar duke.jar```
6. Upon start, if you manage to see the message below in your console, you have successfully installed and run duke.jar

```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
There are too many students changing this logo away.
Let's keep it this way to show the contribution Duke has done to
thousands of students. (No matter positive or negative)
F
    _________________________________________________________________
     Hello! I'm Duke
     What can I do for you?
    _________________________________________________________________
```
## Features 

1. Words in anchor tags <> are parameters required to be provided by the user.
   - The description needed will be placed in the anchor tag. e.g. <time>
   - Those commands that do not have anchor tags <> will take in no parameters. Extra words after the command word will be omitted.
     - Example of such commands are `help` ,`list` and `bye`

2. Some commands require additional keywords to ensure correct input. Without those additional keywords, the command will be deemed as invalid.
   - Example of commands that require additional keywords are `deadline` and `event`
   
3. All time input &lt;time> will require YYYY-MM-DD or YYYY-MM-DD HHMM as input.

### `help` - View help
Description: `help` function will print out all available commands and the command format

>Format: `help`

Example:

Input:
```text
help
```
Output:
```
     Hi user. Great to meet you.
     Let me show you all the commands we have.
     1. help -- helps you to list down all the commands available like now
     2. list -- shows you the list of all the task you have entered
     3. bye -- exits the program
     4. done <index> -- marks task with the input index as done
     5. delete <index> -- delete task with the input index
     6. sort time -- sort tasks with timeframe by date/time in ascending order
     7. find <text> -- find all tasks with that contains the input text
     8. todo <description> -- creates a todo task with description
     9. deadline <description> /by <time> where <time> is YYYY-MM-DD or YYYY-MM-DD HHMM
     -- creates a deadline task with description as task name and the date as deadline
     10. event <description> /at <time1> to <time2> where <time1> could be YYYY-MM-DD
     or YYYY-MM-DD HHMM while <time2> could be YYYY-MM-DD or YYYY-MM-DD HHMM or even HHMM.
     If <time2> is HHMM, it will inherit YYYY-MM-DD from <time1>
     -- creates an event task with the description given and store 2 time given by the input
```

### `list` - List all the tasks created
Description: This command will show all the tasks in the list

>Format: `list`
Example: 

if there is no tasks in the list:

Input:
```text
list
```
Output:
```
    _________________________________________________________________
     You have no tasks in the list at the moment.
     Please add a new task to begin.
    _________________________________________________________________
```
if there are tasks in the list:
```text
list
    _________________________________________________________________
     Here are the tasks in your list:
     1. [T][ ] create a task
     2. [D][X] create a deadline task (by: 01-10-2021 1159)
     3. [E][ ] this is what happens when time1 is later than time2 (at: 01-10-2021 0300 to 02-10-2021 1300)
    _________________________________________________________________
```

### `todo` - Create a todo task
Description: Todo is a type of task that store only description.
Create a todo by following the format below:
>Format: `todo` &lt;description&gt;

Example:
```text
todo create a task
    _________________________________________________________________
     [T][ ] create a task
     Now you have 1 tasks in the list.
    _________________________________________________________________
```
```text
todo create another task
    _________________________________________________________________
     [T][ ] create another task
     Now you have 2 tasks in the list.
    _________________________________________________________________
```

### `deadline` - Create a deadline task
Description: Deadline is a type of task that takes in a description and time. 
Create a deadline by following the format below:
>Format: `deadline` &lt;description&gt; /by &lt;time>

Example:
```text
deadline create a deadline task /by 2021-10-01 1159
    _________________________________________________________________
     [D][ ] create a deadline task (by: 01-10-2021 1159)
     Now you have 3 tasks in the list.
    _________________________________________________________________
```

### `event` - Create an event task
Description: Event is a type of task that takes in a description and 2 time.
Create a deadline by following the format below:

>Format: `event` &lt;description&gt; /at &lt;time1> to &lt;time2>

>:grey_exclamation: Note: if &lt;time1> is later than &lt;time2>, the time will switch around

Example: 
```
event this is what happens when time1 is later than time2 /at 2021-10-02 1300 to 2021-10-01 0300
```
```
    _________________________________________________________________
     [E][ ] this is what happens when time1 is later than time2 (at: 01-10-2021 0300 to 02-10-2021 1300)
     Now you have 4 tasks in the list.
    _________________________________________________________________
```

### `done` - Check corresponding task as done
Description: will mark a particular task as done, by showing an X on the second square bracket [ ].

>Format: `done` &lt;number>

> :grey_exclamation: Note: Please provide a positive whole number that is tagged to the task as shown with the list command.

Example: 
```text
done 3
    _________________________________________________________________
     Nice! I've marked this task as done:
     [D][X] create a deadline task
    _________________________________________________________________
```

### `delete` - Delete corresponding task from list
Description: Delete a task with that has the corresponding number

>Format: `delete` <number>

> :grey_exclamation: Note: Please provide a positive whole number that is tagged to the task as shown with the list command.

Example:
```text

delete 2
    _________________________________________________________________
     Noted. I've removed this task:
     [T][ ] create another task
     Now you have 3 tasks in the list.
    _________________________________________________________________
```

### `find` - Find description tasks with the given input
Description: Returns all the list that contains the following text

>Format: `find`

Example:
```text
find create a
    _________________________________________________________________
     [T][ ] create a task
     [D][X] create a deadline task (by: 01-10-2021 1159)
    _________________________________________________________________
```

### `sort time` - Sort tasks based on date
Description: Sort tasks with time in ascending order. Tasks without time will be ignored.

> Format: `sort time`

Example:
```text
sort time
    _________________________________________________________________
     [E][ ] this is what happens when time1 is later than time2 (at: 01-10-2021 0300 to 02-10-2021 1300)
     [D][X] create a deadline task (by: 01-10-2021 1159)
    _________________________________________________________________
```

### `bye` - Exit program

Description: Exits the program

>Format: `bye'

Example:
```text
bye
    _________________________________________________________________
     Bye. Hope to see you again soon!
    _________________________________________________________________

```

### Frequently Asked Questions(FAQ)

1. **Q:** Is it possible to save all the task and access the tasks again? 
    
   **A:** Yes, all the tasks will be saved in the duke.txt file in the folder that you have run your command.
      Make sure you run the duke.jar in the same folder everytime you start the application, so that you can
      access to all the last saved file.

      If you want to shift the application to other computer, be sure to copy the duke.jar and duke.txt file
      to the new computer. Losing the duke.txt file means that all saved tasks would be gone.

3. **Q:** Is there a way to edit tasks without CLI?

   **A:** Yes, you cna edit the tasks before you start or after you end the application. There will be a text file
      that stores all the files in certain format. However, please ensure that all tasks are in the correct format,
      or you might have the risk of the task list being corrupted.

4. **Q:** There is a file called duke.txt being generated. Should I treat it as an unwanted file and delete it?
   
   **A:** The file is used to store all the tasks you have recorded down in the application. Deleting the file means that
      all the tasks saved by the program will be deleted. Unless you want a clean task list, it is not advisable to perform
      that action.

## Command Summary

|  Commands   |                      Description                       |
|-------------|--------------------------------------------------------|
bye| Exits the program
deadline| Create a deadline task
delete| Delete corresponding task from list
done| Check corresponding task as done
event| Create an event task
find| Find tasks that contains the relevant keyword
help | Shows all available commands for the program
list| show all the tasks in the list
sort| sort all tasks with date and time based on date and time ascending
todo| create a todo task
