# User Guide

Duke is a Personal Assistant Command Line Chatbot designed to help you manage your tasks.

* [Quick Start Guide](#Quick-Start-Guide)
* [Features](#Features)
* 

## Quick Start Guide

1. Ensure you have `Java 11` installed on your computer.
2. Download the latest `duke.jar` file from [here](https://github.com/justinfidelis/ip/releases).
3. Move the file to the folder that you want to use as the Duke's root folder.
4. Execute the `java -jar duke.jar` command in the terminal in the same folder as the `duke.jar` file to launch Duke. The following output should be observed:

![startup image](https://github.com/justinfidelis/ip/blob/master/docs/images/startup.png)

5. Type the command into the terminal and press Enter to execute it.
6. Refer to the [features](#Features) section below for details of each command

## Features 

### Viewing Help

Displays a list of all available commands.

Format: `help`

Example of usage:

```
help
```

Expected Output:

```
  ──────────────────────────────────────────────────────────
  Here is a list of commands:
    todo [task name] - adds todo task to task manager
    event [task name] /at [date] - adds event task to task manager
    deadline [task name] /by [date] - adds deadline task to task manager
    list - lists all tasks
    done [task index] - marks the specified task as completed
    delete [task index] - deletes the specified task
    find [keyword] - lists all tasks whose name contains the keyword
    bye - close the application
  ──────────────────────────────────────────────────────────
```

### Adding a Todo Task

Adds a Task 

### Feature-XYZ

Description of the feature.

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
