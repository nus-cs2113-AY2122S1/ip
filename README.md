# User Guide

This is a project named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up

### Using Intellij
Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE).

### Using JAR file

1. Download `Duke.jar` into the selected directory
2. Open up `Terminal` or `Command prompt` and `cd` to the directory `Duke.jar` file is in
3. Run the JAR file by typing `java -jar Duke.jar`. If you are setting up for the first time, a `data` folder will be created in the directory `Duke.jar` is in and a text file `Duke.txt` will be created in the `data` folder.

## Using Duke
Upon running the program, the user will be greeted with the following message:
```
 ____        _
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

Hello! I'm Duke
What can I do for you?
_____________________________
```

After which, the user is able to explore the different features Duke has to offer:
>Notes about command formatting:
> `parameters` are in **UPPERCASE** which are compulsory
> `additional items` are in **[square brackets]** which are needed in some commands

### Adding a ToDo Task: `todo`
Format: `todo DESCRIPTION`

Adds a ToDo task to the list of tasks. A ToDo task is a basic task type with only a `description` and no additional parameters.

Examples:
- `todo homework`
- `todo CS2113 lecture quiz`

### Adding a Deadline Task: `deadline`
Format: `deadline DESCRIPTION /by [time]`

Adds a Deadline task to the list of tasks. A Deadline task is a task with `description` and a `deadline` to complete it by.

>Additional formatting to save the deadline with a date/time:
>- `deadline DESCRIPTION /by [dd-MM-yyyy]` saves the _**date**_ of the deadline as a Date object.
>- `deadline DESCRIPTION /by [HH:mm]` saves the _**time**_ of the deadline as a Time object.
>- `deadline DESCRIPTION /by [dd-MM-yyyy HH:mm]` saves the _**date**_ and _**time**_ of the deadline as a DateTime object.

Examples: 
- `deadline homework /by monday 3pm`
- `deadline CS2113 iP /by 01-10-2021 23:59`

### Adding an Event Task: `event`
Format: `event DESCRIPTION /at [time]`

Adds an Event task to the list of tasks. An Event task is a task happening at a specific date with `description` and a `timing`.

>Additional formatting to save the event with a date/time:
>- `event DESCRIPTION /by [dd-MM-yyyy]` saves the _**date**_ of the event as a Date object.
>- `event DESCRIPTION /by [HH:mm]` saves the _**time**_ of the event as a Time object.
>- `event DESCRIPTION /by [dd-MM-yyyy HH:mm]` saves the _**date**_ and _**time**_ of the event as a DateTime object.

Examples: 
- `event meeting /at tuesday`
- `event CS2113 tP meeting /at 02-10-2021 22:00`

### Listing the task list: `list`
Format: `list`

Lists the task list so far with all the tasks and their completion status.

### Mark a task as done: `done`
Format: `done TASK_INDEX`

Marks a task with the specified index as done. The task that is done will appear in the list as `[TASK_TYPE][X] DESCRIPTION`.

### Delete a task: `delete`
Format: `delete TASK_INDEX`

Deletes a task from the task list.

### Find a task: `find`
Format: `find KEYWORD`

Finds a task from the existing task list with the keyword specified. 

> The search is case-sensitive. If there is no task found, an empty list will be returned.

Examples:
- `find book`

### Exit the program: bye
Format: `bye`

Exits Duke. A farewell message will be printed before Duke terminates.