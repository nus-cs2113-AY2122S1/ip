# Duke User Guide

Duke is an application designed for task management via Command Line Interface.

## Launching Duke Application

1. Download `ip.jar` file from [**here**](https://github.com/NonRNP/ip/releases).
2. Copy `ip.jar` to an empty folder.
3. Launch command window.
4. Navigate to the folder where `ip.jar` is stored.
5. Enter command `java -jar ip.jar`.
6. The output below will be shown.

```
    ____        _
   |  _ \ _   _| | _____
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|

------------------------------------------------
    Hello!, I'm Duke
    How can I help you?
------------------------------------------------
```



## Duke Commands 

Command | Format | Description
------- | ------ | -----------
**help** | **help** | Show all commands
**list** | **list** | List all tasks
**find** | **find** *keyword* | List tasks containing the keyword
**todo** | **todo** *task description* | Add task
**deadline** | **deadline** *task description* **/by** *deadline date* | Add task with deadline
**event** | **event** *event description* **/at** *event date* | Add event at specific date
**done** | **done** *task number* | Mark task as done
**delete** | **delete** *task number* | Delete task
**bye** | **bye** | Exit Duke

## Commands Usage

### **`Help`:** Show all commands

Show all available commands from Duke.

Command format: `help`

Example:

```
help
------------------------------------------------
    COMMAND LIST:
      help: show command list
      list: show all the stored tasks
      find: show all the stored tasks that
            contains a specific keyword
      todo: add a task as todo
      deadline: add a task as deadline
      event: add a task as event
      done: mark a task as done
      delete: delete a task from the list
------------------------------------------------
```

### **`List`:** List all tasks

Show all tasks stored by Duke.

Command format: `list`

Example:

```
list
------------------------------------------------
    1.[T][ ] attend lecture
    2.[T][ ] finish assignment
    3.[D][ ] submit assignment (by: Sep 24 2021)
    4.[E][ ] final exam (at: Oct 2 2021)
------------------------------------------------
```

### **`Find`:** List tasks containing the keyword

Show all tasks which contain a specific keyword.

Command format: `find *keyword*`

Example:

```
find assignment
------------------------------------------------
    Finding tasks with keyword "assignment":
    1.[T][ ] finish assignment
    2.[D][ ] submit assignment (by: Sep 24 2021)
------------------------------------------------
```

### **`Todo`:** Add task

Add task to Duke.

Command format: `todo *task description*`

Example:

```
todo prepare for final
------------------------------------------------
    Task added: 
      [T][ ] prepare for final
    You have 5 tasks in the list.
------------------------------------------------
```

### **`Deadline`:** Add task with deadline

Add task with a specific deadline to Duke.

Command format: `deadline *task description* /at *deadline date`

Example:

```
deadline submit iP final version /by 2021-10-01
------------------------------------------------
    Task added: 
      [D][ ] submit iP final version (by: Oct 1 2021)
    You have 6 tasks in the list.
------------------------------------------------
```

Note: Date must be in form of yyyy-mm-dd where:
* yyyy is a four digits year
* mm is a two digits month
* dd is a two digits date

### **`Event`:** Add event at specific date

Add event happening on a specific date to Duke.

Command format: `event *event description* /at *event date`

Example:

```
event project meeting /at 2021-10-19
------------------------------------------------
    Task added: 
      [E][ ] project meeting (at: Oct 19 2021)
    You have 7 tasks in the list.
------------------------------------------------
```

Note: Date must be in form of yyyy-mm-dd where:
* yyyy is a four digits year
* mm is a two digits month
* dd is a two digits date

### **`Done`:** Mark task as done

Mark a task in Duke as done.

Command format: `done *task number*`

Example:

```
done 2
------------------------------------------------
    The following task is now marked as done:
      [T][X] finish assignment
------------------------------------------------
```

### **`Delete`:** Delete task

Remove a task from Duke.

Command format: `delete *task number*`

Example:

```
delete 4
------------------------------------------------
    The following task is removed from the list: 
      [E][ ] final exam (at: Oct 2 2021)
    You now have 6 tasks in the list.
------------------------------------------------
```

### **`Bye`:** Exit Duke

Exit the program.

Command format: `bye`

Example:

```
bye
------------------------------------------------
    Bye, see you again!
------------------------------------------------
```
