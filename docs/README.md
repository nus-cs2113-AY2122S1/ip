# User Guide

## What is Herrekt?

Herrekt is a task managing bot
which is able to take in 3 types of tasks,
todos, deadlines, and events. 

He keeps tracks of your current tasks, 
adding new ones when you ask for it, 
marking them done when you inform him, 
and deleting the ones you no longer need.

Furthermore, if you're swamped with work,
he comes with the ability to search for specific tasks, 
and is also remembers them even after shutting down.

## Features

* Notes about the command format:
	* Words in `UPPER_CASE` are the parameters supplied by the user. 
	  For example, `todo TASK`, where `TASK` is the parameter which can be used as `todo cry`.

### Feature - Adding a Todo task

* Adds a Task of the Todo format. 
* Format: `todo DESCRIPTION`.
* Examples:
	* `todo cry`:
	  * Output: `Task added: [T][ ] cry`.
	* `todo User Guide`:
	  * Output: `Task added: [T][ ] User Guide`.

### Feature - Adding a Deadline task

* Adds a Task of the Deadline format.
* Format: `deadline DESCRIPTION /by DATE_OR_DATE_AND_TIME`.
  * `DATE` can be input in a `YYYY-MM-DD` format and output as `MMM DD YYYY`
  * `DATE_AND_TIME` can be input in a `YYYY-MM-DD HHMM` format, 
	`HHMM` follows the 24 hour clock. 
	The output will be as `MMM DD YYYY HH:MM (am/pm)`.
* Examples:
	* `deadline cry /by midnight`:
		* Output: `Task added: [D][ ] cry (by: midnight)`.
	* `deadline User Guide /by 2021-09-25`:
		* Output: `Task added: [D][ ] User Guide (by: SEP 25 2021)`.
	* `deadline ip submission /by 2021-10-01 2359`
		* Output: `Task added: [D][ ] ip submimssion (by: OCT 1 2021 11:59pm)`

### Feature - Adding an Event task

* Adds a Task of the Event format.
* Format: `event DESCRIPTION /at DETAILS`.
  * Similar to deadlines, you can add `DETAILS` as date or date and time.
* Examples:
	* `event cry /at work`:
		* Output: `Task added: [E][ ] cry (at: work)`.
	* `deadline celebrate birthday /at 2021-12-23`:
		* Output: `Task added: [E][ ] celebrate birthday (at: DEC 23 2021)`.

### Feature - Listing the tasks

* List all currently managed tasks.
* Format: `list`.
* Example:
  * `list`:
	* Output:
	  
	  `Here are the tasks in your list:`
	  
	  `1. [T][ ] start crying`
	  
	  `2. [D][ ] stop crying (by: midnight)`
	  
	  `3. [E][ ] cry again (at: work)`

### Feature - Marking a task as done

* Marks the specified task as done.
* Format: `done NUMBER`.
* Example:
  * Using the above list and inputting: `done 1`
	* Output:
	  
	  `Nice! I've marked this task as done:`
		
	  `[T][X] start crying`

### Feature - Deleting a task

* Delete the specified task.
* Format: `delete NUMBER`.
* Example:
  * Using the above list and inputting: `delete 3`
	* Output: 
	  
	  `Alright. I've removed this task:`
	  
	  `[E][ ] cry again (at: work)`
	  
	  `Now you have 2 tasks left to do`

### Feature - Finding a task

* Search for a specified task and its corresponding task number in the list by inputting keywords/phrases.
* Input is case-insensitive. For example:
	* `find cry` is the same as `find cRy`.
* Other than searching for keywords, 
  the task list can be filtered 
  via type and status via find commands. 
  
Command | Output
-------- | -------
`find t` | shows all todos
`find d` | shows all deadlines
`find e` | shows all events
`find done` | shows all tasks marked as done
`find undone` | shows all tasks not marked as done

* Example 1:
  * Using the above list and inputting: `find d`
	* Output: 
	
	  `Here are the matching tasks in your list: `

	  `2. [D][ ] stop crying (by: midnight)`
	
* Example 2:
  * Using the above list and inputting: `find Start`
	* Output:

	  `Here are the matching tasks in your list: `

	  `1. [T][ ] start crying`	

### Feature - Exiting the app

* To switch off the app, input: `bye`.
* Output: 
  * `Bye. Hope you will complete everything for today!`
	
## Overall Usage (Example)

```
Hi! I'm Herrekt, your task manager.
What would you like to add to your timetable?
```
Input: `deadline essay /by 2021-10-03`
```
Task added: [D][ ] essay (by: OCT 3 2021)`
For now, you have 1 task on the list
```
Input: `event exam /at LT27 on Monday`
```
Task added: [E][ ] exam (at: LT27 on Monday)
For now, you have 2 tasks on the list
```
Input: `todo User Guide`
```
Task added: [T][ ] User Guide
For now, you have 3 tasks on the list
```
Input: `todo Java Doc`
```
Task added: [T][ ] Java Doc
For now, you have 4 tasks on the list
```
Input: `delete 4`
```
Alright. I've removed this task: 
  [T][ ] Java Doc
Now you have 3 tasks left to do
```
Input: `todo javadoc`
```
Task added: [T][ ] javadoc
For now, you have 4 tasks on the list
```
Input: `list`
```
Here are the tasks in your list:
1. [D][ ] essay (by: OCT 3 2021)
2. [E][ ] exam (at: LT27 on Monday)
3. [T][ ] User Guide
4. [T][ ] javadoc 
```
Input: `find t`
```
Here are the matching tasks in your list:
3. [T][ ] User Guide
4. [T][ ] javadoc
```
Input: `done 4`
```
Nice! I've marked this task as done:
  [T][X] javadoc
```
Input: `done 3`
```
Nice! I've marked this task as done:
  [T][X] User Guide
```
Input: `list`
```
Here are the tasks in your list:
1. [D][ ] essay (by: OCT 3 2021)
2. [E][ ] exam (at: LT27 on Monday)
3. [T][X] User Guide
4. [T][X] javadoc
```
Input: `find done`
```
Here are the matching tasks in your list:
3. [T][X] User Guide
4. [T][X] javadoc
```
Input: `find undone`
```
Here are the matching tasks in your list:
1. [D][ ] essay (by: OCT 3 2021)
2. [E][ ] exam (at: LT27 on Monday)
```
Input: `deadline submit ip /by 2021-10-01 2359`
```
Task added: [D][ ] essay (by: OCT 1 2021 11:59pm)`
For now, you have 3 task on the list
```
Input: `bye`
```
Bye. Hope you will complete everything for today!
```
