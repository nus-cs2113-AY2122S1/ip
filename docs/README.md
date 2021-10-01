
# User Guide
Duke is your friendly **app for keeping track of tasks via a Command Line Interface (CLI)**. If you can type quickly, Duke can fulfil your task-tracking needs faster than traditional GUI apps.

* **[Quick Start](#quick-start)**
* **[Features](#features)**
    * **[Adding a Todo task](#todo)**
    * **[Adding a Deadline task](#deadline)**
    * **[Adding an Event task](#event)**
    * **[Listing tasks](#list)**
    * **[Marking a task as done](#done)**
    * **[Deleting a task](#delete)**
    * **[Deleting all tasks](#delete)**
    * **[Finding tasks](#find)**
    * **[Exiting Duke](#bye)**
* **[Command Summary](#command-summary)**

<a id="quick-start"></a>
## Quick Start
1. Ensure you have Java ```11``` or above installed in your computer.
2. Download the latest ```duke.jar``` from [here](https://github.com/astralum/ip/releases).
3. Copy the file to the folder you want to use as the  _home folder_  for your Duke.
4. On your _command line interface_, navigate to the folder containing Duke and type in ```java -jar duke.jar```. Press ```Enter```.
5. Type the command in the command line and press ```Enter``` to execute it.
   Some example commands you can try:
    -   **```list```**  : Lists all tasks.
    -   ```todo example task``` : Adds a task named ```example task``` to Duke.
    -   **```delete```**```1```  : Deletes the 1st task shown in the current list.
    -   **```clear```**  : Deletes all tasks.
    -   **```bye```**  : Exits the app.

2.  Refer to the  [Features](#features)  below for details of each command.

<a id="features"></a>
## Features
**Notes about the command format:**
* Words in UPPER_CASE are the parameters to be supplied by the user.
  e.g. in ```todo EXAMPLE_TASK```, EXAMPLE_TASK is a parameter.
* Parameters have to follow the specified order.

<a id="todo"></a>
### Adding a Todo task: ```todo```
Adds a Todo task to Duke.

Format: ```todo DESCRIPTION```

<a id="deadline"></a>
### Adding a Deadline task: ```deadline```
Adds a Deadline task with the given deadline to Duke.

Format: ```deadline DESCRIPTION`/by TIME```

<a id="event"></a>
### Adding an Event task: ```event```
Adds an Event task to Duke.

Format: ```event DESCRIPTION /at TIME_OR_PLACE```

<a id="list"></a>
### Listing tasks: ```list```
Shows a list of all tasks stored in Duke.

Format: ```list```

<a id="done"></a>
### Marking a task as done: ```done```
Marks the task as done.

Format: ```done INDEX```

* Marks the task the specified ```INDEX``` done.
* The index refers to the index number shown in the task list.
* The index **must be a positive integer** 1, 2, 3, …​

<a id="delete"></a>
### Deleting: ```delete```
Deletes the task as done.

Format: ```delete INDEX```

* Deletes the tasks at the specified ```INDEX```.
* The index refers to the index number shown in the task list.
* The index **must be a positive integer** 1, 2, 3, …​

<a id="clear"></a>
### Deleting all tasks: ```clear```
Marks the task with the specified number as done.
Format: ```clear```

<a id="find"></a>
### Finding tasks: ```find```
Shows a list of all tasks matching the search query.
Format: ```find SEARCH_QUERY```

<a id="exit"></a>
### Exiting Duke: ```bye```
Exits Duke.
Format: ```bye```

<a id="command-summary"></a>
## Command Summary
|**Action** | **Format** |
|-----|-----|
| Add Todo  | ```todo DESCRIPTION``` |
| Add Deadline | ```deadline DESCRIPTION`/by TIME``` |
| Add Event | ```event DESCRIPTION /at TIME_OR_PLACE``` |
| List | ```list``` |
| Mark done | ```done INDEX``` |
| Delete a task | ```delete INDEX``` |
| Delete all tasks | ```clear``` |
| Find | ```find SEARCH_QUERY``` |
| Exit | ```bye``` |
