# User Guide

Duke is a **desktop app for managing tasks meant for use via a Command Line Interface**.
If you can type fast and want to manage your tasks, then this is the App for you. 

---

## **1. Quick Start**
* Ensure you have `Java 11` installed in your computer
* Download the v0.2 JAR file (ip.jar) from [here](https://github.com/naijie2108/ip/releases/tag/A-Release).
* Copy the file to your desired folder. This folder will act as the home folder for the JAR file.
* Then, open your terminal in the folder storing the JAR file, and run the following command `java -jar ip.jar` You should be able to see a welcome message
like this:

```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

What can I do for you?
----------------------------------------------------------------------------------------------------
```

---

## **2. Key Features**

A brief summary of available features in Duke. For more detailed instructions, go to the `Commands` section.


### **Manage tasks**
Add/Delete 3 different types of tasks to your task list, namely:
* Todo 
* Deadline
* Event  

### **List tasks**
List all current tasks to keep track of tasks that need to be done

### **Mark tasks**
Mark tasks when you are done with them to better keep track of your existing tasks.


### **Find tasks**
Search for tasks from your existing list of tasks by using keywords.


### **Auto-save feature**
All changes made to the task list are saved automatically, so the user does not have to
worry about losing data from the task list when exiting the program.


---
## **3. Commands**

List of all the commands available in Duke.
<br/>

### **`todo` - Add Todo task**

Adds a Todo task to your task list with a description of the task. If no description is given, 
it will return an error message and the task will not be added.

If the command is successful, the task will be printed out together with the total number of tasks currently in the task list

**Command format:**

`todo [description]`

Parameters:
* `[description]` - Description of the Todo task, can include spaces

**Example:**

`todo CS2113 Lecture Quiz`

Expected output:
```
----------------------------------------------------------------------------------------------------
The following task has been added:
	[T][ ] CS2113 Lecture Quiz
You now have 1 task(s)
----------------------------------------------------------------------------------------------------
```
`[T]` shows that the task added is a Todo task. There are 
other types of tasks, namely Deadline and Event tasks.  
  
<br/>

### **`deadline` - Add Deadline task**

Adds a Deadline task to your task list with a description and deadline for the task. If no description or deadline
is given, it will return an error message and the task will not be added.

If the command is successful, the task will be printed out together with the total number of tasks currently in the task list

**Command format:**

`deadline [description] /by [deadline]`

Parameters:
* `[description]` - Description of the task, can include spaces
* `[deadline]` - Deadline of the task, needs to be in the format `yyyy-mm-dd HH:mm`

**Example:**

`deadline CG2027 Assignment 3 /by 2021-09-22 13:00`

Expected output:
```
----------------------------------------------------------------------------------------------------
The following task has been added:
	[D][ ] CG2027 Assignment 3 (by: Sep 22 2021 13:00)
You now have 2 task(s)
----------------------------------------------------------------------------------------------------
```
`[D]` shows that the task added is a Deadline task. There are
other types of tasks, namely Todo and Event tasks.

<br/>

### **`event` - Add Event task**

Adds an Event task to your task list with a description and time for the task. If no description or time
is given, it will return an error message and the task will not be added.

If the command is successful, the task will be printed out together with the total number of tasks currently in the task list

**Command format:**

`event [description] /at [time]`

Parameters:
* `[description]` - Description of the task, can include spaces
* `[time]` - Time of the event, needs to be in the format `yyyy-mm-dd HH:mm`

**Example:**

`event CS2113 Lecture /at 2021-09-30 16:00`

Expected output:
```
----------------------------------------------------------------------------------------------------
The following task has been added:
	[E][ ] CS2113 Lecture (at: Sep 30 2021 16:00)
You now have 3 task(s)
----------------------------------------------------------------------------------------------------
```
`[E]` shows that the task added is an Event task. There are
other types of tasks, namely Todo and Deadline tasks.

<br/>

### **`list` - List all tasks available**

Lists all tasks that have been added to your task list. If there are no tasks, simply shows that no tasks are available.

**Command format:**

`list`

**Example:**

`list`

Expected output:
```
----------------------------------------------------------------------------------------------------
Here are all your tasks:
	1. [T][X] CS2113 Lecture Quiz
	2. [D][ ] CG2027 Assignment 3 (by: Sep 22 2021 13:00)
	3. [E][ ] CS2113 Lecture (at: Sep 30 2021 16:00)
----------------------------------------------------------------------------------------------------
```
The first `[]` in each task represents the type of the task:
* `[T]` - Todo 
* `[E]` - Event
* `[D]` - Deadline

The second `[]` represents whether the task is done:
* `[X]` - Task is done
* `[]` - Task not done yet

For example, task 1 `CS2113 Lecture Quiz` is a todo task that has been marked as done

<br/>

### **`done` - Mark task as done**

Marks a previously added task as done. If an invalid task number is provided, it will return an error
and the command is stopped.

**Command format:**

`done [task number]`

Parameters:
* `[task number]` - Number representing the task to mark. A list of available tasks and their corresponding task number can be found by using the command `list`

**Example:**

`done 1`

Expected output:
```
----------------------------------------------------------------------------------------------------
The following task has been marked as done:
	[T][X] CS2113 Lecture Quiz
----------------------------------------------------------------------------------------------------
```
`[X]` represents that the task has been marked as done

<br/>

### **`delete` - Delete a task**

Deletes a previously added task from your task list. If an invalid task number is provided, it will return an error
and the command is stopped.

**Command format:**

`delete [task number]`

Parameters:
* `[task number]` - Number representing the task to delete. A list of available tasks and their corresponding task number can be found by using the command `list`

**Example:**

`delete 1`

Expected output:
```
----------------------------------------------------------------------------------------------------
The following task has been deleted:
	[T][X] CS2113 Lecture Quiz
----------------------------------------------------------------------------------------------------
```

<br/>

### **`find` - Find tasks**

Search for tasks from text provided. Returns all tasks with description containing the text provided. For example, if a task has a description 'Assignment', 
results from a query 'Assign' will contain that task in the results. Take note to use the correct letter-case when searching for tasks,
as the search is case-sensitive.

**Command format:**

`list [query]`

* `[query]` - Text to query for

**Example:**

`find CG`

Expected output:
```
----------------------------------------------------------------------------------------------------
Here are all the matching tasks
	1. [D][ ] CG2027 Assignment 3 (by: Sep 22 2021 13:00)
	2. [T][ ] CG2027 Revision
----------------------------------------------------------------------------------------------------
```

<br/>

### **`save` - Save tasks**

Saves your task list so that you will still be able to view the tasks the next time you
use the program. 

This feature acts as a safeguard, as there is already an auto-save feature which activates every time your task list is modified.
Users can use this feature in case there is an error with the auto-save feature.

**Command format:**

`save`


**Example:**

`save`

Expected output:
```
----------------------------------------------------------------------------------------------------
You task list has been saved successfully
----------------------------------------------------------------------------------------------------
```

<br/>

### **`bye` - Exit program**

Exits and terminates the program.

**Command format:**

`bye`


**Example:**

`bye`

Expected output:
```
----------------------------------------------------------------------------------------------------
Thank you for using our application. We hope to see you again soon
----------------------------------------------------------------------------------------------------
```

