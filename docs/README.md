# User Guide

**Duke** is a command-line-based task management utility that allows one to input and organise different kinds of tasks effortlessly.

## Features

### Local storage

All transactions are automatically saved to a local data file in real time. Therefore, there is very little possibility of data loss even in abrupt power outages.

### Cross platform

This application works out of the box wherever a Java 11 distribution is available. You can even transfer/sync your data file onto a different machine and seamlessly continue working from there.

## Setup

1. Obtain a runtime for Java 11, such as [Oracle's](https://www.oracle.com/java/technologies/downloads/#java11) or [Amazon's](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).
2. If not done automatically by the installer, add the binary installation path containing the `java` executable to the PATH environment variable of the operating system. Refer to the manual of the operating system for detailed instructions.
3. Obtain the JAR bundle from [release](https://github.com/SuibianP/ip/releases/latest).
4. Run the bundle with `java -jar <filename>.jar`, where `<filename>.jar` is the name of the downloaded file from the previous step.

On successful launch of Duke, you should be greeted with something like,
```
Hello! I'm Duke
What can I do for you?
```

## Usage

### `todo` - add a todo task
Add a task of type todo, which is a basic task type with a name and a status of done/not done.

```
todo <title>
```

Example usage:
```
todo Have lunch
```

Expected outcome:
```
added: [T][ ] Have lunch
```

### `deadline` - add a deadline task
Add a task of type deadline, which records the date of deadline in addition to those properties of todo.

```
deadline <title> /by <deadline>
```

where `<deadline>` is a date following the format of `YYYY-MM-DD`, e.g. `2020-02-02`.

Example usage:
```
deadline Send postcard /by 2021-10-02
```

Expected outcome:
```
added: [D][ ] Send postcard (by: Oct 02 2021)
```


### `event` - add a event task
Add a task of type event, which records the date of happening in addition to those properties of todo.

```
event <title> /at <day>
```

where `<day>` is a date following the format of `YYYY-MM-DD`, e.g. `2020-02-02`.

Example of usage:

```
event Commencement /at 2021-11-11
```

Expected outcome:
```
added: [E][ ] Commencement (at: Nov 11 2021)
```

### `list` - list all tasks

List all tasks currently being stored, together with their properties such as completion. This command takes no arguments and silently ignores any trailing arguments being provided.

Example of usage:
```
list
```

Expected outcome:

All tasks being stored at the moment. Each task is prefixed by a index starting from 1 and indications of its type and done status. Other properties of the task, if any, are suffixed in the line.
```
1: [E][x] Wedding (at: Feb 03 2021)
2: [D][ ] Homework (by: Oct 02 2021)
3: [T][ ] Swimming
```

### `bye` - exit program

Gracefully shut down the program.

An end-of-file situation from the standard input, such as issuing <kbd>Ctrl-D</kbd> in Bash, acts the same as this command.

Example of usage:
```
bye
```
Expected outcome:

A friendly farewell message should be printed to the user.
```
Bye. Hope to see you again soon!
```

### `delete` - delete task at specified index

This command removes a task from storage. The task is identified by its unique index, as shown by a previous `list` command.

```
delete <index>
```

Example of usage:
```
delete 3
```
Expected outcome:
```
Noted. I've removed this task:
[T][ ] Swimming
```

### `done` - mark task at specified index as done

This command marks a task as done. The task is identified by its unique index, as shown by a previous `list` command.
```
done <index>
```
Example of usage:

```
done 2
```

Expected outcome:

The task being marked as done is echoed back for the user.
```
Nice! I've marked this task as done:
[D][x] Homework (by: Oct 02 2021)
```
