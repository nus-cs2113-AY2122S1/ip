# User Guide

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
* Format: `deadline DESCRIPTION /by DATE`.
  * `DATE` can be input in a `YYYY-MM-DD` format and output as `MON DD YYYY`
* Examples:
	* `deadline cry /by midnight`:
		* Output: `Task added: [D][ ] cry (by: midnight)`.
	* `deadline User Guide /by 2021-09-25`:
		* Output: `Task added: [D][ ] User Guide (by: SEP 25 2021)`.

### Feature - Adding an Event task

* Adds a Task of the Event format.
* Format: `event DESCRIPTION /at DETAILS`.
* Examples:
	* `event cry /at work`:
		* Output: `Task added: [E][ ] cry (at: work)`.
	* `deadline Finish User Guide /at home`:
		* Output: `Task added: [E][ ] User Guide (at: home)`.

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

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
