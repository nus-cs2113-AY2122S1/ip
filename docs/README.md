# User Guide
Duke is a **Command Line Interface (CLI) application for managing your tasks**. If you can type fast, Duke can help manage your tasks faster than traditional Graphical User Interface (GUI) apps.
* [Quick start](#quick-start)
* [Features](#features)
   * [Listing all tasks: `list`](#listing-all-tasks-list)
   * [Adding a todo task: `todo`](#adding-a-todo-task-todo)
   * [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
   * [Adding a event task: `event`](#adding-a-event-task-event)
   * [Marking a task as done: `done`](#marking-a-task-as-done-done)
   * [Deleting a task: `delete`](#deleting-a-task-delete)
   * [Searching for a task: `find`](#searching-for-a-task-find)
   * [Exiting the program: `bye`](#exiting-the-program-bye)
* [FAQ](#faq)
* [Command summary](#command-summary)

---

## Quick start
1. Ensure you have Java `11` installed in your Computer.
2. Download the latest `Duke.jar` from [here](https://github.com/leyondlee/ip/releases).
3. Create a folder and place `Duke.jar` inside.
4. Open Command Prompt/Terminal and navigate to the folder containing `Duke.jar`.
5. Run `java -jar Duke.jar` to start the app. The following message should appear:
   ```
   ------------------------------------------------------------
   Hello! I'm Duke
   What can I do for you?
   ------------------------------------------------------------
   ```
6. Type in your command. See [Features](#features) for more information.

---

## Features 

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`
<br />
<br />

### Adding a todo task: `todo`

Adds a todo task to the task list.

Format: `todo <DESCRIPTION>`

Examples:
* `todo Homework`
* `todo Assignment`
<br />

### Adding a deadline task: `deadline`

Adds a deadline task to the task list.

Format: `deadline <DESCRIPTION> /by <DATETIME>`
> :bulb: **Note:**<br />
> `<DATETIME>` format is `dd/MM/yyyy HHmm`.

Examples:
* `deadline Assignment 1 /by 10/10/2021 2359`
* `deadline Assignment 2 /by 01/01/2022 0000`
<br />

### Adding a event task: `event`

Adds a event task to the task list.

Format: `event <DESCRIPTION> /at <DATETIME>`
> :bulb: **Note:**<br />
> `<DATETIME>` format is `dd/MM/yyyy HHmm`.

Examples:
* `event Lecture /at 21/11/2021 1600`
* `event Appointment /at 02/02/2022 0830`
<br />

### Marking a task as done: `done`

Marks a task in the task list as done.

Format: `done <INDEX>`
> :bulb: **Note:**<br />
> `<INDEX>` refers to the index of the task displayed in the `list` command.

Examples:
* `done 1` <- Marks the 1st task in the list as done.
* `done 3` <- Marks the 3rd task in the list as done.
<br />

### Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete <INDEX>`
> :bulb: **Note:**<br />
> `<INDEX>` refers to the index of the task displayed in the `list` command.

Examples:
* `delete 1` <- Deletes the 1st task in the list.
* `delete 3` <- Deletes the 3rd task in the list.
<br />

### Searching for a task: `find`

Searches for a task in the task list.

Format: `find <TEXT>`
> :bulb: **Note:**<br />
> `<TEXT>` is the string to search for.

Examples:
* `find Assignment`
* `find Lecture`
<br />

### Exiting the program: `bye`

Exits the program.

Format: `bye`

---

### FAQ

**Q:** How do I transfer my data to another system?<br />
**A:** Install the app in the new system and copy the `data` folder from the old system to the folder containing `Duke.jar` in the new system.

---

### Command summary
**Action** | **Format**
---------- | ----------
Listing all tasks | `list`
Adding a todo task | `todo <DESCRIPTION>`
Adding a deadline task | `deadline <DESCRIPTION> /by <DATETIME>`
Adding a event task | `event <DESCRIPTION> /at <DATETIME>`
Marking a task as done | `done <INDEX>`
Deleting a task | `delete <INDEX>`
Searching for a task | `find <TEXT>`
Exiting the program | `bye`
