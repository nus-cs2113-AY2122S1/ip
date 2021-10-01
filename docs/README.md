# User Guide

Hello There! This is Duke, your own personal Command Line Interface (CLI) bot to help manage your daily schedule!

## Quick Start

1. Please ensure Java ```11``` is already installed on your device.
2. Please ensure you have downloaded the latest version of ```Duke.jar``` [here](https://github.com/KaiserHuang88/ip).
3. Please copy ```Duke.jar``` over to the **folder** you plan to use for your bot.
4. Please use the ```cd``` command on any terminal interface to navigate to the **folder**.
5. Please execute the command `java -jar Duke.jar` to start the bot. Upon starting, you should see something like the below

```  
Hello from  
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|  

------------------------------------  
Hello! I'm Duke.  
Type Something:  
------------------------------------  
```

## Features


### Add Todo: ```todo```
Adds a Todo task into the tasklist.  
The todo command must be accompanied by a `task description` only.  
Format: `` todo <description> ``

Example
```

% todo eat tempura  
------------------------------------  
Got it! I've added this task:   
1. [T][ ] eat tempura  
Now you have 1 tasks in the list  
------------------------------------  

```

### Add Deadline: ```deadline```
Adds a Deadline task into the tasklist.  
The deadline command must be accompanied by both `task description` and `date` .  
Format: `` deadline <description> /by <date> ``

Example
```

% deadline drink milk /by 7pm
------------------------------------  
Got it! I've added this task:   
1. [T][ ] eat tempura  
2. [D][ ] drink milk (by: 7pm)
Now you have 2 tasks in the list  
------------------------------------  

```

### Add Event: ```event```
Adds a Event task into the tasklist.  
The event command must be accompanied by both `task description` and `date` .  
Format: `` event <description> /at <date> ``

Example
```

% event attend wedding /at 12pm
------------------------------------  
Got it! I've added this task:   
1. [T][ ] eat tempura  
2. [D][ ] drink milk (by: 7pm)
3. [E][ ] attend wedding (at: 12pm)
Now you have 3 tasks in the list  
------------------------------------  

```

### List all tasks: ```list```
Prints out the tasklist.  
The list command must not be accompanied by any arguments .  
Format: `` list ``

Example
```

% list
------------------------------------  
1. [T][ ] eat tempura  
2. [D][ ] drink milk (by: 7pm)
3. [E][ ] attend wedding (at: 12pm)
------------------------------------  

```

### Mark task as Done: ```done```
Mark the specified task in the tasklist with an X, signifying that it's done.   
The done command must be accompanied by an `index` of the specified task in the tasklist .  
Format: `` done <index> ``

Example
```

% done 2
% list
------------------------------------  
1. [T][ ] eat tempura  
2. [D][X] drink milk (by: 7pm)
3. [E][ ] attend wedding (at: 12pm)  
------------------------------------  

```

### Deleting a task: ```delete```
Deleting the specified task from the tasklist.   
The delete command must be accompanied by an `index` of the specified task in the tasklist .  
Format: `` delete <index> ``

Example
```

% delete 2
------------------------------------  
Noted, I've removed this task: 
[D][X] drink milk (by: 7pm)
------------------------------------  

```

### Finding tasks by keyword: ```find```
Find tasks in the tasklist using a keyword.   
The find command must be accompanied by a `keyword` .  
Format: `` find <keyword> ``

Example
```

% find wedding
------------------------------------  
Here are the matching tasks in your list:   
1. [E][ ] attend wedding (at: 12pm)  
------------------------------------  

```

### Exiting Duke: ```bye```
Terminate the program.   
The bye command must be not accompanied by any other arguments .  
Format: `` find ``

Example
```

% bye
------------------------------------  
Bye! Cya next time! 
------------------------------------  

```

### Save list in a local text file
Whenever a user exits Duke using ``bye``, the tasklist when he exits is automatically updated into a local text file at the file path ```data/dukedata.txt```.  
In the case where the directory or file required does not exist, Duke automatically creates the directory and file for the user. Whenever the same user starts up Duke again, Duke will automatically load the tasklist written in the text file.

## Command Summary

| **Command** | **Usage**| 
| ------- | -----| 
| Add Todo | `todo <description>`| 
| Add Deadline | `deadline <description> /by <date>`| 
| Add Event | `event <description> /at <date>`| 
| List all tasks | `list`|
| Mark task as done | `done <index>`|
| Deleting a task | `delete <index>`| 
| Finding tasks by keyword | `find <keyword>`|  
| Exiting Duke | `bye`| 



