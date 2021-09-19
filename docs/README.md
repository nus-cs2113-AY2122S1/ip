# User Guide
Duke is a desktop app, task tracker that allows you to... well, track all your tasks.
As Duke is optimized for use in the Command Line (CLI), it can greatly benefit users who
type fast.
## Quick Start
1. Ensure that you have Java 11 or above installed.
2. Download the JAR file from GitHub here (Link).
3. Place the Jar file in a folder where you wish to run it from. Note that
the datafile will be created wherever you place your jar file.
4. Open the CLI at where your jar file is placed and run it using the command
`java -jar abcd.jar`
5. Type the commands in the box and press enter to execute it. Some commands you can try are
   ```
   - list : Lists all current tasks.
   - todo Read Book : Adds the todo task of reading book.
   - delete 1 : Deletes the 1st task in the list. 
   ```
## Features
```
- Note that the parameters of all the commands must be in order.
- Words in UPPER_CASE are parameters to be supplied by the user.
    . E.g) The command [todo TASK_NAME] can be used as [todo Read book]
```
* Adding a new task:
    * Todo typed task: `todo`
    * Deadline typed task: `deadline`
    * Event typed task: `event`
* Marking a task as done: `done`
* Deleting a task: `delete`
* Finding a task: `find`
* Listing all tasks: `list`
* Exiting the program: `end`
### Features - Adding a new task
To add a task, the task has to be one of 3 types, `todo`,`deadline` or `event`.
Each of the task types has its own format to follow. The main difference between these 3
task types is that the `todo` task does not have a date attached to it, whilst both the `deadline`
and `event` do.

* Todo
    * Format : `todo TASK_NAME`
    * Example : todo Have lunch
* Event
    * Format : `event TASK_NAME /at DATE`
    * The format of `DATE` **must be in** `yyyy-mm-dd` E.g. For the date `29th of Oct, 2004` you would key in `2004-10-29`
    * Example : `event ZoukOut /at 2021-12-09` - This adds an event called ZoukOut for the 9th of Dec, 2021.
  * ![Adding Event](/images/addEvent.jpg)
* Deadline
    * Format : `deadline TASK_NAME /by DATE`
    * The format of `DATE` follows the same format as in `Event` task.
    * Example : `deadline Return books /by 2021-09-19` - This adds a deadline called Return Books with the date of 19th of Sep, 2021.
### Feature - Marking a task as done
Marks a task as done.
* Format : `done TASK_INDEX`
* The `TASK_INDEX` refers to the index of the task shown in the list of task.
  * The index **must be a positive integer** 1,2,3,...
  * ![Done Task](/images/doneEvent.jpg)
* Example : `done 3` - Marks the 3rd task in the list as done.

### Feature - Deleting a task
Deletes a task from the list.

* Format : `delete TASK_INDEX`
* The `TASK_INDEX` refers to the index of the task shown in the list of task.
    * The index **must be a positive integer** 1,2,3,...
    * ![Delete Task](/images/deleteTask.jpg)
* Example : `delete 2` - Deletes the 2nd task in the list.

### Feature - Finding a task
Find all the task that contains the given keyword and print it out.
* Format : `find KEY_WORD`
* As long as the `KEY_WORD` is a sub-string of the task's description, it will match. (E.g Finding `od` will match `food`)  
* The search **is case-sensitive** such that `ABC` will only match `ABC` and not `abc` nor `AbC`.
* ![Find Task](/images/findEvent.jpg)
### Feature - Listing all the tasks

List all the tasks currently stored in the list.
* Format : `list`
* ![List Task](/images/listEvent.jpg)

### Feature - Exiting the program
Stops the program.
* Format : `end`

##Command Summary
Action|Format & Examples
------|-------
Add todo task|`todo TASK_NAME` (E.g. `todo Read Book`)
Add event task|`event TASK_NAME /at DATE` (E.g. `event Movie /at 2020-10-29`)
Add deadline task|`deadline TASK_NAME /by DATE` (E.g. `deadline Project work /by 2021-19-09`)
Mark as done|`done TASK_INDEX` (E.g. `done 3`)
Delete|`Delete TASK_INDEX` (E.g. `delete 2`)
Find|`find KEY_WORD` (E.g. `find Book`)
List|`list`
