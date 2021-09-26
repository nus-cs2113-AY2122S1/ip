# Duke User Guide

**Duke** is a Command Line Interface (CLI) app which helps you **manage tasks and keep track 
of events and deadlines**. It is a personalised **chatbox** that offers customised feedback 
and helps you stay ahead of the curve.

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
