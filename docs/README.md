# User Guide

Jarvis is a **desktop app for managing tasks via a Command Line Interface (CLI)**.

* **[Quick Start](#start)**
* **[Features](#features)**
    * **[Adding a Todo task](#todo)**
    * **[Adding a Deadline task](#deadline)**
    * **[Adding an Event task](#event)**
    * **[Listing all tasks](#list)**
    * **[Marking a task as done](#done)**
    * **[Deleting a task](#delete)**
    * **[Finding task by keyword](#find)**
    * **[Exiting the program](#bye)**
    * **[Saving the data](#save)**
* **[Command Summary](#summary)**

<a name="start"></a>
## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `jarvis.jar` from [here](https://github.com/ashrafjfr/ip/releases).

1. Copy the file to the folder you want to use as the _home folder_ for **Jarvis**.

1. Open your desired _Command Line Interface_ from the folder with `jarvis.jar` and enter the following code: `java -jar jarvis.jar`

1. Type a command in the command box and press Enter to execute it.<br>
   Some example commands you can try:
    * **`todo read book`** : Adds a `read book` task to your list of tasks.

    * **`deadline return book /by 01/11/2021 1800`** : Adds a `return book` **deadline** task with a **specific date and time** to your list of tasks.*

    * **`event attend CS2113T lecture /at 30/10/2021 1600`** : Adds an `attend CS2113T lecture` **event** task with a **specific date and time** to your list of tasks.*

    * **`list`** : Lists all tasks.

    * **`done 1`** : Marks the 1st task in the current list as done.

    * **`delete 2`** : Deletes the 2nd task in the current list.

    * **`bye`** : Exits the app.

      ***Note:** date and time must be entered in either of these formats **`DD/MM/YYYY hhmm`** or **`DD-MM-YYYY hhmm`** <br>
      where **`D: Date, M: Month, Y: Year, h: Hour, m: Minute`** and time must be in **`24-hour notation`**.

1. Refer to the [Features](#features) below for details of each command.

<a name="features"></a>
## Features

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.

* Parameters have to be in correct order.<br>
  e.g. if the command specifies `deadline DESCRIPTION /by DATE TIME`, the order of command and parameters have to be followed.

* Parameters have to be in correct format.<br>
  e.g. for date and time, it must be entered in either of these formats **`DD/MM/YYYY hhmm`** or **`DD-MM-YYYY hhmm`** <br>
  where **`D: Date, M: Month, Y: Year, h: Hour, m: Minute`** and time must be in **`24-hour notation`**.

* Parameters have to be the correct type (such as `done` and `delete` must be followed by an integer).<br>
  e.g. in `done 2`, `2` has to be an integer character, `two` will not be recognised.

* Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be not be recognised and you will have to input your `list` command again.

<a name="todo"></a>
### Adding a Todo task: `todo`

Adds a todo task to current list of tasks.

Format: `todo DESCRIPTION`

Examples:
* `todo read book`
* `todo rewatch CS2113T lecture`

<a name="deadline"></a>
### Adding a Deadline task: `deadline ... /by ...`

Adds a deadline task with a **specific date and time** to your current list of tasks.

Format: `deadline DESCRIPTION /by DATE TIME`

Examples:
* `deadline return book /by 31/10/2021 1800`
* `deadline submit assignment /by 01/11/2021 2359`

<a name="event"></a>
### Adding an Event task: `event ... /at ...`

Adds an event task with a **specific date and time** to your current list of tasks.

Format: `event DESCRIPTION /at DATE TIME`

Examples:
* `event lecture quiz /at 30/09/2021 1200`
* `event attend concert /at 24/11/2021 2000`

<a name="list"></a>
### Listing all tasks : `list`

Shows a list of all tasks in your current list of tasks.

Format: `list`

<a name="done"></a>
### Marking a task as done : `done`

Marks the specified task as done.

Format: `done INDEX`

* Marks the task at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed list of tasks.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
* `list` followed by `done 2` marks the 2nd task as done in the current list of tasks.

<a name="delete"></a>
### Deleting a task : `delete`

Deletes the specified task from the current list of tasks.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed list of tasks.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
* `list` followed by `delete 2` deletes the 2nd task in the current list of tasks.

<a name="find"></a>
### Finding task by keyword: `find`

Find tasks containing the given keyword.

Format: `find KEYWORD`

* The search is case-insensitive. e.g `book` will match `BOOK`
* Only the description is searched
* Partial words can be matched e.g. `boo` will match `book`
* Tasks matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `book` will return `read book`, `return book`

Examples:
* `find book` returns `read book` and `return book`

<a name="bye"></a>
### Exiting the program : `bye`

Prints a `bye` message and exits the program.

Format: `bye`

<a name="save"></a>
### Saving the data

Jarvis' data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<a name="summary"></a>
## Command Summary

Action | Format, Examples
--------|------------------
**Add Todo** | `todo DESCRIPTION` <br> e.g. `todo read book`
**Add Deadline** | `deadline DESCRIPTION /by DATE TIME` <br> e.g. `deadline return book /by 31/10/2021 2100`
**Add Event** | `event DESCRIPTION /by DATE TIME` <br> e.g. `event attend lecture /at 27/10/2021 1400
**List** | `list`
**Done Task** | `done INDEX`<br> e.g. `done 2`
**Delete Task** | `delete INDEX`<br> e.g. `delete 2`
**Find** | `find KEYWORD`<br> e.g. `find book`
**Exit** | `bye`
