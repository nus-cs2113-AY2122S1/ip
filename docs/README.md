# User Guide
Lennox is a command-line based personal assistant chatbot that helps users keep track of their tasks.
It is easy to use with just a handful of commands and has functionalities to store todo, deadline, and event items.
Their respective completion status can be tracked and items can also be removed as and when the user deems fit.

## Quick Start
1. Make sure you have **Java 11** installed running on your computer.
2. Download the **lennox.jar** file [here]().
3. Store the jar file to your preferred directory locally.
4. To run file, open a Terminal at this directory and enter `java -jar lennox.jar`.
5. Follow the on-screen instructions and select the appropriate mode for the chatbot.
6. Enjoy your session with Lennox personal assistant!

## Features 
### Modes
```
#########################################################
	Hello from 					
	,--.
	|  |   ,---.,--,--,,--,--, ,---,--.  ,--
	|  |  | .-. |      |      | .-. \  `'  /
	|  '--\   --|  ||  |  ||  ' '-' /  /.  \
	`-----'`----`--''--`--''--'`---'--'  '--
		 +"""""+ 	 +"""""+ 	 +"""""+ 
		[| o o |]	[| o o |]	[| o o |]
		 |  ^  | 	 |  ^  | 	 |  ^  | 
		 | '-' | 	 | '-' | 	 | '-' | 
		 +-----+ 	 +-----+ 	 +-----+ 
#########################################################
	____________________________________________________________
	Hi there! I'm Lennox - your personal chatbot
	What can I do for you today?
	* Enter 1 for Echo mode.
 	* Enter 2 for Task mode.
```

The chatbot can be operated in 2 different modes:-
* _**ECHO mode**_ (Option '1' in startup menu): 
  * Type in your messages or task description for chatbot to display them back to you.
  * Acts as temporary post-it sticky notes for you to complete message/task actions soon.
  * Post-it notes keyed in ECHO mode are not stored by program when exiting.


* _**TASK mode**_ (Option '2' in startup menu):
  * Register todo, event, and deadline tasks.
  * More powerful compared to ECHO mode. Allows you to mark tasks as done or remove them from database.
  * Tasks are stored locally when exiting and are loaded back into program when relaunching.

## Usage
### *Note On Command Format*
> Required user inputs are represented as phrases in UPPER_CASE throughout this user guide, such as:
> * `DESCRIPTION` Description or name of task/note.
> * `TIMING` Time or date at which an event is happening.
> * `DEADLINE` Date and/or time by which a task is due.
> * `INDEX` Index number of task in TASK mode inventory when displayed with `list` command.
> * `STRING` Phrase or word user would like to search for in tasks inventory with `find` command.
> 
> Appropriate on-screen error messages or instructions are also displayed by Lennox if user inputs wrongly
> formatted command.

### Adding a sticky note
In ECHO mode, type in description of urgent items and they will be displayed back to you for quick reference.
For instance, you can take note of a phone number to call, or remind yourself to send out an email soon.

Format:

```
Listening> DESCRIPTION
````

1. Example of usage:

```
Listening> Go get coffee at 3pm
````

*Expected outcome:*

```
____________________________________________________________
You have entered: Go get coffee at 3pm
____________________________________________________________
```

2. Example of usage 

```
Listening> Pass file to John when he is back
```

*Expected outcome*

```
____________________________________________________________
You have entered: Pass file to John when he is back
____________________________________________________________
```


_**Note: Post-it messages are lost when chatbot is quit.**_

### `switch` - Switching between modes

Key in `switch` keyword in either TASK or ECHO mode to switch chatbot to the other mode.

**1. Format in ECHO mode:**

```
Listening> switch
```

*Expected outcome:*

Chatbot enters TASK mode and waits for your input.

```
        ____________________________________________________________
	TASK MODE - Enter items to include in to-do list.

TellMe> 
```

**2. Format in TASK mode:**

```
TellMe> switch
```

*Expected outcome:*

Chatbot enters ECHO mode and waits for your input.

```
        ____________________________________________________________
	ECHO MODE - Commands entered will be echoed back.

Listening> 
```

### `todo` - Adding a todo task

A todo task can be added to the chatbot's tasks inventory, and it does not need to contain a deadline or event time.

**Format:**

```
TellMe> todo DESCRIPTION
```

**1. Example of usage:**

```
TellMe> todo Wash dishes
```

*Expected outcome:*

Lennox prints out confirmation message, and the number of tasks currently in inventory.

```
        ____________________________________________________________
	Got it. I've added this task: 
	[T][ ] Wash dishes
	Now there are 1 tasks in the list.
	____________________________________________________________
```

**2. Example of usage:**

```
TellMe> todo Iron clothes
```

*Expected outcome:*

```
        ____________________________________________________________
	Got it. I've added this task: 
	[T][ ] Iron clothes
	Now there are 2 tasks in the list.
	____________________________________________________________
```

### `event` - Adding an event task

An event task is that which has a specific timing or date to it. An event task can be added to the chatbot's inventory together with its time/date.

**Format:**

```
TellMe> event DESCRIPTION /at TIMING
```

**1. Example of usage:**

```
TellMe> event sarah birthday party /at 4pm
```

*Expected outcome:*

Lennox prints out confirmation message and the number of tasks currently in inventory.

```
        ____________________________________________________________
	Got it. I've added this task: 
	[E][ ] sarah birthday party (at: 4pm)
	Now there are 3 tasks in the list.
	____________________________________________________________
```

**2. Example of usage:**

```
TellMe> event attend colleague marriage /at Tuesday 6pm
```

*Expected outcome:*

```
        ____________________________________________________________
	Got it. I've added this task: 
	[E][ ] attend colleague marriage (at: Tuesday 6pm)
	Now there are 4 tasks in the list.
	____________________________________________________________
```

### `deadline` - Adding a deadline task

A deadline task to be included in the chatbot's inventory should include a deadline for the task.

**Format:**

```
TellMe> deadline DESCRIPTION /by DEADLINE
```

**1. Example of usage:**

```
TellMe> deadline Complete CS1010 assignment /by 16/9 2359
```

*Expected outcome:*

Lennox prints out confirmation message and the number of tasks currently in inventory.

```
        ____________________________________________________________
	Got it. I've added this task: 
	[D][ ] Complete CS1010 assignment (by: 16/9 2359)
	Now there are 5 tasks in the list.
	____________________________________________________________
```

**2. Example of usage:**

```
TellMe> deadline finish project report /by asap
```

*Expected outcome:*

```
        ____________________________________________________________
	Got it. I've added this task: 
	[D][ ] finish project report (by: asap)
	Now there are 6 tasks in the list.
	____________________________________________________________
```

### `list` - Display all tasks stored in chatbot

Lennox displays all the tasks that you have created, in an easy-to-view format.
It contains the task type (eg, [T] - Todo, [D] - Deadline, [E] - Event) and their completion status marked by an [X].

**Example of usage:**

```
TellMe> list
```

*Expected outcome:*

```
        ------------------------------------------------------------
	CURRENT ADDED LIST
	1. [T][ ] Wash dishes
	2. [T][ ] Iron clothes
	3. [E][ ] sarah birthday party (at: 4pm)
	4. [E][ ] attend colleague marriage (at: Tuesday 6pm)
	5. [D][ ] Complete CS1010 assignment (by: 16/9 2359)
	6. [D][ ] finish project report (by: asap)
	------------------------------------------------------------
```

### `done` - Marking a task as complete

Lennox provides you the ability to mark a specific task as complete or incomplete
with the index of the task in the inventory.

**Format:**

```
TellMe> done INDEX
```

**Example of usage:**

```
TellMe> done 1
```

*Expected output:*

Checks the first item in the inventory as complete, by indicating it with [X].

```
        ____________________________________________________________
	That's great! Wash dishes has been checked as completed!
	____________________________________________________________
```

In this example, the first task is `Wash dishes` so it has been marked as complete.

### `delete` or `remove` - Clearing a task from inventory

You can remove a task from the inventory as you deem fit with the index number of the task in inventory.

**Format:**

```
TellMe> delete INDEX
```

**Example of usage:**

```
TellMe> delete 2
```

*Expected outcome:*

The 2nd task in list is removed and the remaining task's index numbers are updated accordingly.
Updated list is printed out too.

```
        ------------------------------------------------------------
	Iron clothes removed from list!
	Now you have 5 tasks in the list.
	------------------------------------------------------------

	------------------------------------------------------------
	CURRENT ADDED LIST
	1. [T][X] Wash dishes
	2. [E][ ] sarah birthday party (at: 4pm)
	3. [E][ ] attend colleague marriage (at: Tuesday 6pm)
	4. [D][ ] Complete CS1010 assignment (by: 16/9 2359)
	5. [D][ ] finish project report (by: asap)
	------------------------------------------------------------
```

Originally, the second task in the inventory was `Iron clothes`. This task is removed and 
the remaining tasked are pushed up in the inventory. Tasks' index are re-numbered accordingly.

### `find` - Searching for tasks using a keyword

You can find specific tasks in the inventory by searching for them through `find` command.

**Format:**

```
TellMe> find STRING
```

**1. Example of usage:**

```
TellMe> find wash dishes
```

*Expected outcome:*

Any task which contains the search STRING will be returned, regardless of character case.

```
        ------------------------------------------------------------
	Tasks found for keyword, WASH DISHES
	1| [T][X] Wash dishes
	------------------------------------------------------------
```

**2. Example of usage:**

```
TellMe> find sarah
```

*Expected outcome:*

If there are more than one tasks containing the input `STRING`,
all of them will be displayed alongside the index at which the task appears in master inventory.

```
        ------------------------------------------------------------
	Tasks found for keyword, SARAH
	2| [E][ ] sarah birthday party (at: 4pm)
	6| [T][ ] Return book to Sarah
	------------------------------------------------------------
```

### `bye` or `exit` - Exiting the chatbot

Chatbot can be exit from either ECHO mode or TASK mode. The confirmation response from chatbot will be the same.

**Example of usage in ECHO mode:**

```
Listening> bye
```

**Example of usage in TASK mode:**

```
TellMe> bye
```

*Expected outcome:*

Lennox will confirm if you really want to exit.

```
Do you really want to exit chatbot (type y or n)?
```

If you type in y(es), program ends. Else if n(o), you are returned back to where you left off in ECHO or TASK mode.

```
        Do you really want to exit chatbot (type y or n)? y
	____________________________________________________________
	Bye. Hope to see you again soon!
	____________________________________________________________

===== PROGRAM ENDED =====
```

_**Note: Once quit, the tasks created during TASK mode session will be stored and retrieved back
by Lennox once it is relaunched. However, all messages keyed in during ECHO mode session will be lost
when quiting.**_