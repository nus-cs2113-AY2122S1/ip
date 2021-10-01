# User Guide
Project Duke is an application for keeping track of various types of tasks, 
with a Command Line Interface (CLI). For fast typists, this is a great application
to record your tasks quickly.

## Content Page
* [Quick Start](#Quick-Start)
* [Command Summary](#Command-Summary)
* [Features](#Features)
    * [Display Task List](#Display-Task-List)
    * [Add a todo task](#Add-a-todo-task)
    * [Add a deadline task](#Add-a-deadline-task)
    * [Add an event task](#Add-an-event-task)
    * [Mark a task as done](#Mark-a-task-as-done)
    * [Delete a task](#Delete-a-task)
    * [Search for a keyword](#Search-for-a-keyword)
    * [Exit the program](#Exit-the-program)

## Quick Start
1. Ensure `Java 11` is installed in your computer.
    * Find the platform you are running on your computer and click on the corresponding
      download link [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
2. Download the latest `duke.jar` [here](https://github.com/daknam2001/ip/releases/tag/A-Release).
3. Save it into an empty folder. This will be your *home folder* for Kate.
4. For Windows user, type `cmd` into the location bar at the top of the window of your *home folder* and tap enter.
   For Linux users, `cd` into your *home folder* on your terminal.
5. Run `java -jar duke.jar` and you should see the start page as shown below

## Command Summary
Command | Parameters
------- | ------
`list` | -
`todo` | `[description]`
`deadline` | `[description] /by [due time]`
`event` | `[description] /at [location]`
`done` | `[task number]`
`delete` | `[task number]`
`find` | `[keyword]`
`bye`  | -

## Features 

### <a name = "Display-Task-List"></a> Display Task List - `list`

Lists out all the tasks you have in your Task List.

Format:
`list`

Example of usage:
`list`

Expected outcome:

Task List is displayed.

```
____________________________________________________________
Here are the tasks in your list:
    1.[T][ ] homework
    2.[T][ ] english essay
____________________________________________________________
```

### <a name = "Add-a-todo-task"></a> Add a todo task - `todo`

Adds a task of type "todo" to your Task List. Ignores whitespace.

Format:
`todo [description]`

Example of usage:
`todo homework`

Expected outcome:

Adds a todo task with description "homework".

```
____________________________________________________________
Got it. I've added this task:
    [T][ ] homework
Now you have 1 tasks in the list
File "duke.txt" updated
____________________________________________________________
```

### <a name = "Add-a-deadline-task"></a> Add a deadline task - `deadline`

Adds a task of type "deadline" to your Task List. Ignores whitespace.

Format:
`deadline [description] /by [due time]`

Example of usage:
`deadline proposal /by tomorrow 9pm`

Expected outcome:

Adds a deadline task with description "proposal" and due time "tomorrow 9pm"

```
____________________________________________________________
Got it. I've added this task:
    [D][ ] proposal (by: tomorrow 9pm)
Now you have 3 tasks in the list
File "duke.txt" updated
____________________________________________________________
```

### <a name = "Add-an-event-task"></a> Add an event task - `event`

Adds a task of type "event" to your Task List. Ignores whitespace.

Format:
`event [description] /at [location]`

Example of usage:
`event business meeting /at office`

Expected outcome:

Adds an event task with description "business meeting" and location "office"

```
____________________________________________________________
Got it. I've added this task:
    [E][ ] business meeting (at: office)
Now you have 4 tasks in the list
File "duke.txt" updated
____________________________________________________________

```

### <a name = "Mark-a-task-as-done"></a> Mark a task as done - `done`

Marks a specified task as done in your Task List. Ignores whitespace.

Format:
`done [task number]`

Example of usage: 
`done 4`

Expected outcome:

Task 4 is marked with an X, to indicate that it is completed.

```
____________________________________________________________
Nice! I've marked this task as done:
    4.[D][X] coding assignment (by: today midnight)
File "duke.txt" updated
____________________________________________________________
```

### <a name = "Delete-a-task"></a> Delete a task - `delete`

Deletes a specified task from your Task List. Ignores whitespace.

Format:
`delete [task number]`

Example of usage:
`delete 3`

Expected outcome:

Task 3 is deleted from your Task List.

```
____________________________________________________________
Noted. I've removed this task:
    3.[D][ ] proposal (by: tomorrow 9pm)
Now you have 3 tasks in the list
File "duke.txt" updated
____________________________________________________________
```

### <a name = "Search-for-a-keyword"></a> Search for a keyword - `find`

Searches your Task List to find tasks that contains the keyword. Ignores whitespace.

Format:
`find [keyword]`

Example of usage:
`find book`

Expected outcome:

Display tasks that contain the keyword "book"

```
____________________________________________________________
Here are the matching tasks in the list:
    1.[T][ ] read textbook
    4.[D][ ] return library book (by: Friday)
____________________________________________________________
```

### <a name = "Exit-the-program"></a> Exit the program - `bye`

Terminates the Duke Program.

Format:
`bye`

Example of usage:
`bye`

Expected outcome:

Closes the program with a bye message.

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
