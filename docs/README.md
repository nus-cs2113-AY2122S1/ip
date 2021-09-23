# DAHNAM User Guide
DAHNAM is your ~~robot~~ human friend who is here to help you keep track of your upcoming tasks and get your life in ~~order~~ better shape!

## Features
What can DAHNAM do for you?

### Creating and Storing Tasks

DAHNAM can help you to keep track of any tasks you may have, and can split them into 3 sub-categories!

**ToDo** : ToDo tasks are any tasks that do not have a deadline to meet

**Event** : Events are upcoming tasks that take place at a specific time

**Deadline** : Deadlines are tasks that must be done by a certain timing

*Tasks are saved on your disk as `tasks.txt`*

### Mark as done

DAHNAM helps you to mark any tasks you have completed, but do not wish to delete just yet (be it for book-keeping purposes or what not).

### Filter

Give DAHNAM a keyword and it will sift through your tasks and find them for you.

### Delete

Keep your tasks list tidy by deleting tasks you no longer have to worry about.

_____________________________________________________________________________________________________________________________________________
## Usage

### `list` - List all tasks currently recorded

Tasks recorded by DAHNAM will be printed out sequentially.

Example: 

`list`

Expected outcome:

If there are tasks in your list, DAHNAM will print them out. 

	* ToDos are listed as [T], Events are listed as [E] and Deadlines are listed as [D]. 
	* Tasks that are done will be marked as [X] and each Task will print out a description and timing (if applicable)

```
____________________________________________________________
1. [T][X] Eat, sleep, study!
2. [E][ ] Attend CS2113T Lectures (at: Every Friday 4pm-6pm)
3. [D][X] Submit your iP (by: 1 Oct 2021)
____________________________________________________________
```

### `todo` - Create and store a ToDo Task 

ToDo Tasks are any tasks without a specific deadline to meet.

Format: `todo <DESCRIPTION>`

	* <DESCRIPTION> is a String that describes the task to complete
	* Take note that `todo` is in lowercase

Example:

`todo eat`

Expected output:

A ToDo will be added to your list of tasks. The current nummber of tasks recorded will also be shown.

```
____________________________________________________________

Added: [T][ ] eat
 You currently have 4 tasks
____________________________________________________________
```

### `event` - Create and store an Event Task

Events are tasks which take place within a specific timeframe. It could be a day, an hour or a minute. 

Format: `event <DESCRIPTION> /at <TIME>`

	* <DESCRIPTION> is a String that describes your event
	* <TIME> is a String that indicates *when* the event is happening
	* Separate your <DESCRIPTION> and <TIME> using `/at`
	* Take note that `event` is in lowercase

Example:

`event Interview practice /at Every Friday 4pm-6pm`

Expected output:

```
____________________________________________________________

Added: [E][ ] Interview practice  (at: Every Friday 4pm-6pm)
 You currently have 5 tasks
____________________________________________________________
```

### `deadline` - Create and store a Deadline Task

Deadlines are tasks which must be completed by a certain time.

Format: `deadline <task description> /by <time>`

	* <DESCRIPTION> is a String that describes your task
	* <TIME> is a String that indicates *by when* you should complete your task
	* Separate your <DESCRIPTION> and <TIME> using `/by`
	* Take note that `deadline` is in lowercase

Example:

`deadline update my resume /by end of September 2021`

Expected output:

```
____________________________________________________________

Added: [D][ ] update my resume  (by: end of September 2021)
 You currently have 6 tasks
____________________________________________________________
```

### `done` - Mark a task as completed

Once you have successfully completed a task, you can mark it as done by calling `done`.

Format: `done <INDEX>`

	* <INDEX> is the index of the task in your task list. When you call `list`, <INDEX> is the value that is displayed on the left next to your task.
	* Tasks marked successfully will be indicated with `[X]` instead of `[ ]`

Example:

`done 4`

Expected output:
```
____________________________________________________________

Bueno! The following task is marked as done: 
[T][X] eat
____________________________________________________________
```

Notice that the second `[ ]` is now marked with `[X]`

### `find` - Search for tasks using a keyword

Search for tasks you have recorded by using keywords. DAHNAM will print *every* tasks you have recorded that contains the keyword.

Format: `find <KEYWORD>`

	* <KEYWORD> is a String that represents the keyword you want to search for
	* Note that <KEYWORD> is case-senstive

Example:

`find lecture`

`find Lecture`

Expected output:
```
____________________________________________________________

Below are a list of tasks matching your description

No matches found

____________________________________________________________

____________________________________________________________

Below are a list of tasks matching your description

1. [E][X] Attend CS2113T Lectures  (at: Every Friday 4pm-6pm)
____________________________________________________________
```
*Notice that the search is case-sensitive*

### `delete` - Remove a task from your list based on index

Remove a task from your list by calling its index

Format: `delete <INDEX>`

	* <INDEX> is the index of the task in your task list.

Example:

`delete 4`

Expected output:
```
____________________________________________________________

The following task has been deleted!
[T][X] eat

____________________________________________________________
```

_____________________________________________________________________________________________________________________________________________

## Command Summary

Action | Syntax | Example
------ | ------ | -------
list | `list` | `list`
todo | `todo <DESCRIPTION>` | `todo eat`
event | `event <DESCRIPTION> /at <TIME>` | `event playing games /at 10pm-12am`
deadline | `deadline <DESCRIPTION> /by <TIME>` | `deadline coding assignment /by next monday`
find | `find <KEYWORD>` | `find games`

_____________________________________________________________________________________________________________________________________________