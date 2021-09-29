# User Guide
Duke is a CLI optimised bot that helps you create and manage 3 types of tasks - Deadlines, Events
and todo tasks. Users have the ability to create these tasks, mark them as done, delete
and find them. Users can also view their tasks in a list format.
* [Quick Start](#quick-start)
* [Features](#features)
* [Usage](#usage)
  * [Adding a task](#adding-a-task)
  * [Marking a task](#marking-a-task)
  * [Deleting a task](#deleting-a-task)
  * [Finding a task](#finding-a-task)
  * [Listing all tasks](#listing-all-tasks)
  * [Exiting the program](#exiting-the-program)
* [Command Summary](#command-summary)    
    
## Quick Start
1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest iP.jar under v2.0 from [here](https://github.com/pragyan01/ip/releases).
3. Go to the folder you saved iP.jar and note the absolute file path.
4. If you are using Windows, open up a Command prompt terminal cmd.exe or powershell.exe and for 
   Mac and Linux users, do the same with the terminal of your respective systems.
5. Navigate to the folder where iP.jar is stored.
6. Execute java -jar iP.jar in the terminal, and the application will start running.

You should be able to see something like this:
```
------------------------------------------------------------------------------------------
Hello! I'm Duke.
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
What can i do for you?
------------------------------------------------------------------------------------------

Enter your wish: 
------------------------------------------------------------------------------------------
```
## Features 
1. Add any of the 3 task types: todo, deadline and events
2. Mark a task as done
3. Delete a task
4. Find a specific task or tasks containing the searched keyword
5. List out all tasks
6. Exit the program
## Usage
### Adding a task
Add tasks from any 3 types available: Todo, deadline and event

1. A todo task only has a description associated with it. 
   This command allows you to add a new todo task to your list.

`todo {task description}` - Adds a new todo task

Example of usage:

`todo buy dinner`

Expected outcome:

```
That's the spirit! I've added this task:

[T][ ] buy dinner

Now you have 1 tasks in the list.
```

2. A deadline task has a description, and a deadline associated with it. 
   This command allows you to add a new deadline task to your list.

`deadline {task description} /by {yyyy-mm-dd}` - Adds a new deadline task

Example of usage:

`deadline submit assignment /by 2021-10-30`

Expected outcome:

```
Deadline Entered: Oct 30 2021

Got it. I've added this task:

[D][ ] submit assignment (by: 2021-10-30)

Now you have 2 tasks in the list.
```

3. An event task has a description, and a duration associated with it. 
   This command allows you to add a new event task to your list.

`event {task description} /at {duration}` - Adds a new event task

Example of usage:

`event team meeting /at Monday 5-6pm`

Expected outcome:

```
Got it. I've added this task:

[E][ ] team meeting (at: Monday 5-6pm)

Now you have 3 tasks in the list.
```
### Marking a task
Marks a task's status as done.

`done {task number}` - Updates the task as completed

Example of usage:

`done 1`

Expected outcome:

```
Kudos! One less thing to stress about!

  [T][X] buy dinner
```
### Deleting a task
Deletes a specific task from the list.

`delete {task number}` - Removes the specific task

Example of usage:

`delete 1`

Expected outcome:

```
One more thing outta your life as always...

  [T][X] buy dinner

You now have 2 tasks left.
```
### Finding a task
Searches for a specific task from the list or tasks containing the keyword.

`find {keyword}` - Searches for tasks containing keyword

Example of usage:

`find assingment`

Expected outcome:

```
Lucky for you, i'm really good at digging through your mess:
------------------------------------------------------------------------------------------

1. [D][ ] submit assignment (by: 2021-10-30)

Enter your wish: 
```
### Listing all tasks
Prints all existing tasks in the list.

`list` - Lists everything

Example of usage:

`list`

Expected outcome:

```
1. [D][ ] submit assignment (by: 2021-10-30)

2. [E][ ] team meeting (at: Monday 5-6pm)
```
### Exiting the program
Terminates the program and exits.

`bye` - Terminates program

Example of usage:

`bye`

Expected outcome:

```
------------------------------------------------------------------------------------------

Ciao! More tasks to do later!
------------------------------------------------------------------------------------------
```
## Command Summary
Description | Syntax | Example
------------|--------|--------
Add todo task | todo {task description} | todo buy dinner
Add deadline task | deadline {task description} /by {yyyy-mm-dd} | deadline submit assignment /by 2021-10-30
Add event task | event {task description} /at {duration} | event team meeting /at Monday 5-6pm
Mark task as done | done {task number} | done 1
Delete a task | delete {task number} | delete 1
Find a task | find {keyword} | find assignment
Exit the program | bye | bye

