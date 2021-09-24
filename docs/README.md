# Duke User Guide (UG)

Duke is a **Command Line Interface (CLI) app that helps you to better manage your tasks in life by being your personal assistant (PA).** If you have a preference for typing over clicking, then Duke would definitely be a great PA for you.

---

# Quick Start

1. Ensure you have Java version `11` or above installed on your computer.
    - If you haven't, you may download it from [here](https://www.oracle.com/java/technologies/downloads/#java11-linux).

Please download them relative to your Operating System.

1. Next, download the latest `Duke.jar` from [here](https://github.com/Kair0s3/ip/releases).
    - Simply click on the `Duke.jar` under Assets and the download should start.
2. After downloading, open up `command prompt` .

To open command prompt, `win` + `r` , then type and enter `cmd` .

1. Then, run the `Duke.jar` by typing in `java -jar Duke.jar`. Please make sure, that you are at the directory where `Duke.jar` is.
    - In the screenshot example below, I have kept `Duke.jar` in my Downloads folder.

![Untitled](images/Untitled.png)

1. Here on, you can type commands to tell Duke to do something for you. Below are some of sample commands you can try out.
    - `list` : List all tasks that you have added.
    - `todo Do my iP User Guide` : Adds a Todo task to the list of tasks.
    - `done 2` : Marks the 2nd task in the list as done.
    - `delete 2` : Deletes the 2nd task in the list.
    - `find iP` : Finds any task in list with name containing "iP".
    - `bye` : Exits the app.
2. For more information on Duke's features, please refer to the [Features Section]().

---

# Before continuing...

Before continuing, here are some of the things you should take note of, to better understand the terminologies used in this UG.

[Untitled](https://www.notion.so/11926973ff514fe3b262981e8c1a7130)

# Features
Please note that Duke is still in early stages, so that may be quite a number of bugs. However, if you find any bugs, you can let me know [here](https://github.com/Kair0s3/ip/issues).

## Adding a Task (3 types)

### Todo Task : `todo`

Adds a Todo task to the list of tasks.

Syntax - `todo <taskName>`

### Deadline Task : `deadline`

Adds a Deadline task to the list of tasks.

Syntax - `deadline <taskName> /by <byWhen>`

### Event Task : `event`

Adds a Event task to the list of tasks.

Syntax - `event <taskName> /at <atWhen>`

Sample output of Adding an Event

![Untitled](images/Untitled%201.png)

## List all tasks : `list`

Displays a list of tasks previously added. If no tasks exist , shows a message saying that the list of tasks is empty.

Syntax - `list`

Sample output

![Untitled](images/Untitled%202.png)

## Mark task as done : `done`

Marks a task as done given the index of the task in the list.

Syntax - `done <index>`

Sample output

![Untitled](images/Untitled%203.png)

## Delete task : `delete`

Deletes a task given the index of the task in the list.

Syntax - `delete <index>`

## Find task : `find`

Finds all tasks with task name containing a given keyword.

Syntax - `find <keyword>`

Sample output

![Untitled](images/Untitled%204.png)

# Command Summary

[Untitled](https://www.notion.so/57be9a15360a484f9050504a96c8fa16)