# User Guide

## Initialising the chat bot

In order to initialise the xRoss chat bot, enter the following command into the terminal.

```
java -jar ip.jar
```

## Features and Usage

### Adding todos `todo`
Adds a new todo to the current task list. 
A todo is a task with a named description.

Arguments:

1. name - named description of todo

Example of usage:
```
todo <name>
```

### Adding deadlines `deadline`
Adds a new deadline to the current task list.
A deadline is a task with a named description and a set deadline.

Arguments:

1. name - named description of todo
2. by - DateTime by which deadline should be completed
   * Should be in the format: "dd-MM-yyyy HHmm"

Example of usage:
```
deadline <name> /by <by>
```

### Adding events `event`
Adds a new event to the current task list.
An event is a task with a named description and a specified datetime at which it is occurring.

Arguments:

1. name - named description of todo
2. at - DateTime at which event is occurring
    * Should be in the format: "dd-MM-yyyy HHmm"

Example of usage:
```
event <name> /at <at>
```
### Listing tasks `list`
Prints the current task list to the System output.

Example of usage:
```
list
```

### Marking tasks as done `done`
Marks a specified task in the current task list as done.

Arguments:

1. taskNumber - Corresponding task number in current task list to be marked as done

Example of usage:
```
done <taskNumber>
```

### Deleting tasks `delete`
Deletes a specified task from the current task list.

Arguments:

1. taskNumber - Corresponding task number to be deleted from current task list

Example of usage:
```
delete <taskNumber>
```

### Finding tasks `find`
Finds all tasks in the current task list containing a specified keyword.

Arguments:

1. exp - Keyword to be found in tasks in current task list

Example of usage:
```
find <exp>
```

### Exiting chat bot `bye`
Exits the xRoss chat bot

Example of usage:
```
bye
```


