# User Guide
Duke is a Personal Assistant ChatBot that helps a 
person to keep track of various things via a Command Line Interface (CLI).
* Quick Start
* Features
  * Viewing help
  * Adding a Todo Task
  * Adding a Deadline Task
  * Adding an Event Task
  * Listing all Tasks
  * Marking a Task as done
  * Deleting a Task
  * Finding Tasks
  * Exiting the program
* FAQ
* Command Summary

## Quick Start
1. Ensure that you have [Java 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) or above installed in your computer.
2. Download the latest `IP.jar` file from here (add link after released).
3. Copy the file to the folder you want to use as the home folder for your ChatBot.
4. Write the following line of code to start the application.
```
java -jar IP.jar
```
5. On startup, you should see a terminal output similar to below.
![](https://github.com/rachelkeh/ip/blob/master/docs/ip%20jar%20screenshots/1.JPG)
6. Type a command and press Enter to execute it.
   Some example commands you can try:
   * `help`: Lists all the performable commands.
   * `list`: Lists all tasks.
   * `todo return book`: Adds a Todo task called `return book` into the task list.
   * `done 1`: Marks the first task in the task list as done.
   * `delete 3`: Deletes the third task in the task list.
   * `bye`: Exits the application.

## Features
**Note about the command formats:**
* Extraneous parameters for commands with only one parameter (such as `help` `list` and `bye`) will be ignored.
> `help me` will be interpreted as `help`.

### Viewing Help
Lists all the valid commands that Duke accepts.\
Format: `help`\
Example:
```
help
```
Expected output:
```
_____________________________________________________________________________
Here are the performable actions:
 1. Add a new To Do by typing "todo {content of your to do}".
 2. Add a new Deadline by typing "deadline {content of your deadline} /by {date of deadline}".
 3. Add a new Event by typing "event {content of your event} /at {date of event}".
 4. Mark a task as done by typing in "done" and the index of the task on the list.
 5. Check all the tasks you have added by typing in "list". Done tasks will be marked with an X.
 6. Delete a task by typing in "delete" and the index of the task on the list.
 7. Find a task with a particular keyword by typing in "find {keyword}". Tasks with that keyword will be listed.
 8. Show this list of performable actions again by typing "help".
 9. End the program by typing in "bye".
_____________________________________________________________________________
```
### Adding a Todo Task
Adds the most basic task type to the task list.\
Format: `todo <TASK_DESCRIPTION>`\
Example:
````
todo return book
````
Expected output:
````
_____________________________________________________________________________
 Got it. I've added this task: 
   [T][ ] return book
 Now you have 3 tasks in the list.
_____________________________________________________________________________
````

### Adding a Deadline Task
Adds a task that should be completed before a time specified by the user.\
Format: `deadline <TASK_DESCRIPTION> /by <TIME>`
* The command must be formatted this way, with the `/by` in between `<TASK_DESCRIPTION>` and `<TIME>`.\
Example:
````
deadline return book /by Aug 6th
````
Expected output:
````
_____________________________________________________________________________
 Got it. I've added this task: 
   [D][ ] return book (by: Aug 6th)
 Now you have 5 tasks in the list.
_____________________________________________________________________________
````

### Adding an Event Task
Adds a task with a duration that starts and ends at a specific time.\
Format: `event <TASK_DESCRIPTION> /at <DURATION>`
* The command must be formatted this way, with the `/at` in between `<TASK_DESCRIPTION>` and `<DURATION>`.\
Example:
````
event attend tutorial /at Aug 6th 2-4pm
````
Expected output:
````
_____________________________________________________________________________
 Got it. I've added this task: 
   [E][ ] attend tutorial (at: Aug 6th 2-4pm)
 Now you have 6 tasks in the list.
_____________________________________________________________________________
````

### Listing all Tasks
Shows a list of all the tasks in the task list.\
Format: list\
Example:
````
list
````
Expected output:
````
_____________________________________________________________________________
 Here are the tasks in your list:
  1.[D][ ] return book (by: Aug 6th)
  2.[E][ ] attend tutorial (at: Aug 6th 2-4pm)
_____________________________________________________________________________
````

### Marking a Task as Done
Marks a task as completed.\
Format: `done <TASK_NUMBER>`
* `<TASK_NUMBER>` corresponds to the index number of the task on the task list when listed using `list`.\
Example:
````
done 1
````
Expected output:
````
_____________________________________________________________________________
 Nice! I've marked this task as done:
  [D][X] return book (by: Aug 6th)
_____________________________________________________________________________
````

### Deleting a Task
Deletes a task.\
Format: `delete <TASK_NUMBER>`
* `<TASK_NUMBER>` corresponds to the index number of the task on the task list when listed using `list`.\
Example:
````
delete 1
````
Expected output:
````
_____________________________________________________________________________
 Noted. I've removed this task:
  [D][X] return book (by: Aug 6th)
 Now you have 1 tasks in the list.
_____________________________________________________________________________
````

### Finding Task(s)
Finds task(s) with a specified keyword.\
Format: find <KEYWORD>\
Example:
````
find tutorial
````
Expected output:
````
_____________________________________________________________________________
 Here are the matching tasks in your list:
  1.[E][ ] attend tutorial (at: Aug 6th 2-4pm)
  2.[T][ ] do geh tutorial
_____________________________________________________________________________
````

### Exiting the program
Exits the ChatBot.\
Format: `bye`\
Example:
````
bye
````
Expected output:
````
_____________________________________________________________________________
Bye. Hope to see you again soon!
_____________________________________________________________________________
````

## FAQ
*Q:* How do I transfer my data from my computer to another computer?\
*A:* Copy over the `IP.jar` file and `data` folder containing the `duke.txt` file that are within the same directory.

## Command Summary
 Command | Action
------------ | -------------
Help | Lists all the valid commands that Duke accepts.
Todo | Adds the most basic task type to the task list.
Deadline | Adds a task that should be completed before a time specified by the user.
Event | Adds a task with a duration that starts and ends at a specific time.
List | Shows a list of all the tasks in the task list.
Done | Marks a task as completed.
Delete | Deletes a task.
Find | Finds task(s) with a specified keyword.
Exit | Exits the ChatBot.
