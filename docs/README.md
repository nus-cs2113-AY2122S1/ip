# User Guide

Oberon is a **desktop app for managing tasks entered into a task list via a Command Line Interface (CLI)**. Tasks have 
3 types, namely Todos, Events and Deadlines. Oberon can manage your tasks as fast as you can type into the CLI, 
benefiting those that type fast the most.

## Features
* Accessing task list: `list`
* Adding a Todo: `todo`
* Adding an Event: `event`
* Adding a Deadline: `deadline`
* Marking task as done: `done`
* Deleting a task: `delete`
* Finding tasks based on keyword: `find`
* Exiting the program: `exit`
* Saving user data
* Editing user data

### Accessing task list: `list`
Displays the current task list.

Format: `list`

### Adding a Todo: `todo`
Adds a Todo into the task list.

Format: `todo TODO_DESCRIPTION`
* `TODO_DESCRIPTION` is the description of the Todo to be added.
* A description **must be specified** for a Todo to be successfully added.

Example:
* `todo Eat Lunch` adds a todo into the task list with a description `Eat Lunch`.

### Adding an Event: `event`
Adds an Event into the task list.

Format: `event EVENT_DESCRIPTION /at EVENT_AT`
* `EVENT_DESCRIPTION` is the description of the Event to be added.
* A description **must be specified** for an Event to be successfully added.
* `EVENT_AT` is used to specify when the Event is at.
* If `/at` or `EVENT_AT` is not specified by the user, an Event will still be added and `EVENT_AT` will be taken as 
  `???`.
* If specified in the **exact** format:`dd/MM/yyyy HH:mm`, when `list` is called, `EVENT_AT` will be 
  displayed in the format: `EEE, d MMM yyyy, HH:mm` where `EEE` gives the day of the week.

Examples:
* `event project meeting /at 12 midnight` Adds an Event with the description `project meeting` at `12 midnight`.
* `event project discussion /at 26/9/2021 20:00` Adds an Event with the description `project discussion` at 
  `26/09/2021 20:00`. When `list` is called, `26/09/2021 20:00` will be displayed as `Sun, 26 Sep 2021, 20:00`.
* `event interview /at` or `event interview` Adds an event with the description `interview` at `???`.

### Adding a Deadline: `deadline`
Adds a Deadline into the task list.

Format: `deadline DEADLINE_DESCRIPTION /by DEADLINE_BY`
* `DEADLINE_DESCRIPTION` is the description of the Deadline to be added.
* A description **must be specified** for a Deadline to be successfully added.
* `EVENT_AT` is used to specify when the Deadline is by.
* If `/by` or `DEADLINE_BY` is not specified by the user, a Deadline will still be added and `DEADLINE_BY` will be 
  taken as `???`.
* If `DEADLINE_BY` is specified in the **exact** format:`dd/MM/yyyy HH:mm`, when `list` is called, `DEADLINE_BY` will 
  be displayed in the format: `EEE, d MMM yyyy, HH:mm` where `EEE` gives the day of the week.

Examples:
* `deadline weekly quiz /by thursday midnight` Adds a Deadline with the description `weekly quiz` by 
  `thursday midnight`.
* `deadline User Guide /by 26/9/2021 23:59` Adds a Deadline with the description `User Guide` by
  `26/09/2021 23:59`. When `list` is called, `26/09/2021 20:00` will be displayed as `Sun, 26 Sep 2021, 23:59`.
* `deadline proposal /by` or `event proposal` Adds a Deadline with the description `proposal` by `???`.

### Marking task as done: `done`
Marks a task in the task list as done.

Format: `done TASK_NUMBER`
* `TASK_NUMBER` refers to a task's number seen in the task list displayed by `list`.
* The task number **must be a positive integer** e.g. 1,2,3,...
* The task number **must be within range of the total number of tasks** e.g. if there are 10 tasks in the task list, 
  task number must be between 1 and 10.
  
Example: 
* `done 3` Marks task number 3 in the task list as done **if there are at least 3 tasks in the list**.

### Deleting a task: `delete`
Deletes a task from the task list.

Format: `delete TASK_NUMBER`
* `TASK_NUMBER` refers to a task's number seen in the task list displayed by `list`.
* The task number **must be a positive integer** e.g. 1,2,3,...
* The task number **must be within range of the total number of tasks** e.g. if there are 10 tasks in the task list,
  task number must be between 1 and 10.

Example:
* `delete 10` Deletes task number 10 from the task list  **if there are at least 10 tasks in the list**.

### Finding tasks based on keyword: `find`
Finds all tasks in the task list that contain a specified keyword in their descriptions. These tasks are then displayed 
to the user if found.

Format: `find KEYWORD`
* `KEYWORD` refers to the keyword to search for in the task descriptions.
* The search is **case-sensitive** e.g. `exam` will not match `Exam`.

Example:
* `find test` Looks for all tasks that contain `test` in their descriptions and displays them if found.

### Exiting the program: `exit`
Exits the program

Format: `exit`

### Saving user data
Oberon's task data is automatically saved into the hard disk in a data file after every command that alters the task 
list such as adding and deleting tasks. For new users or existing users that may have accidentally deleted/lost their 
data file, a new data file will be created upon startup if Oberon is unable to locate the data file.

### Editing user data
Oberon's task data is saved as a txt file `[JAR file location]/data/duke.txt`. Advanced users are welcome to 
update data directly by editing that data file if they understand and know the data format. Take note that **if the 
edited data has an invalid format, Oberon will load the data incorrectly and may have issues at start up**. In such cases the data file has to be either formatted 
correctly again or wiped.

