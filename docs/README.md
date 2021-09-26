# Duke User Guide

**Duke** is a Command Line Interface (CLI) app which helps you **manage tasks and keep track 
of events and deadlines**. It is a personalised **chatbox** that offers customised feedback 
and helps you stay ahead of the curve.

## Quick start guide

### To run the jar file
1. Ensure that you have `Java 11` installed on your computer.
2. Download the latest `ip.jar` file from 
[here](https://github.com/mayankp291/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for `Duke`.
4. Open the terminal (such as `powershell`) in this folder and launch by typing `java -jar ip.jar`.
6. A welcome message should be displayed like the one below:
```
____________________________________________________________
Howdy there! I'm Fluke
What can I do for you today master?
____________________________________________________________
```
7. Refer to Features for proper usage instructions and commands.


## Command Summary

Action | Command Format | Example
--- | --- | --- | 
List all valid commands and usage | `help` | `help`
Add todo task| `todo <TASK_DESCRIPTION>` | `todo do cs2113 homework`
Add deadline task | `deadline <TASK_DESCRIPTION> /by <YYYY-MM-DD HH:MM>` | `deadline submit ip jar /by 2021-10-02 22:20`
Add event task| `event <TASK_DESCRIPTION> /at <YYYY-MM-DD HH:MM>` | `event graduation party /at 2021-11-01 18:10`
List all the tasks| `list` | `list`
Mark a task done | `done <TASK_INDEX>` | `done 2`
Delete a task| `delete <TASK_INDEX>` | `delete 3`
Find matching tasks| `find <SEARCH_STRING>` | `find homework`
Exit duke | `bye` | `bye`
