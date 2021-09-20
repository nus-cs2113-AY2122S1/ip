# User Guide
Hello there! This is Duke, your personal **Command Line Interface (CLI) chatbot for you to manage your daily tasks**.

##Quick Start 
1. Ensure you have Java ```11``` or above installed in your computer.
2. Download the latest version of ```Duke.jar``` here.
3. Copy the file over to the **home folder** you intend to use for your bot.
4. Navigate to the home folder specified previously using any terminal interface.
5. Execute the command ```java -har Duke.jar``` and the bot will start running. You should be seeing something similar to the example given below.
```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

------------------------------------
Hello! I'm duke.Duke
What can I do for you?
------------------------------------
```

## Features 

### Adding a Todo: ```todo```
Add a Todo task into the task list.  
Todo tasks should only contain a task description.  
Format: ```todo <description>```  
Example: 
```
$todo study for CS2113T
------------------------------------
Got it! I've added the following task:
[T][ ]study for CS2113T
Now you have 1 tasks in your list
------------------------------------

```

### Adding a Deadline: ```deadline```
Add a Deadline into the task list.  
Deadlines should contain the task description and date.
Format: ```deadline <description>/by <date>```  
Example: 
```
$deadline submit CS2101 reflection /by 21 Sept
------------------------------------
Got it! I've added the following task:
[D][ ]submit CS2101 reflection(by: 21 Sept)
Now you have 2 tasks in your list
------------------------------------

```

### Adding an Event: ```event```
Add an Event into the task list.  
Events should contain the task description and event time.  
Format: ```event <description>/at <time>```  
Example:
```
$event attend online career fair /at Sunday 2-4pm
------------------------------------
Got it! I've added the following task:
[E][ ]attend online career fair(at: Sunday 2-4pm)
Now you have 3 tasks in your list
------------------------------------
```

###Listing all tasks: ```list```
List all tasks in the task list.  
Each entry includes the following information: task type, whether it is done, task description, and date (if any).  
Format: ```list```  
Example:
```
$list
------------------------------------
Here are all the tasks in your list:
1.[T][ ]study for CS2113T
2.[D][ ]submit CS2101 reflection(by: 21 Sept)
3.[E][ ]attend online career fair(at: Sunday 2-4pm)
------------------------------------
```

###Marking a task as completed: ```done```  
Mark a specific task as completed.   
Index from the user should be a valid value in the list of tasks.
Format: ```done <taskIndex>```  
Example: 
```
$done 1
------------------------------------
Nice! I have marked this task as done:
[T][X]study for CS2113T
------------------------------------

```

###Removing a task: ```delete```
Remove a specific task from the task list.  
Index from the user should be a valid value in the list.  
Format: ```delete <taskIndex>```  
Example:
```
$delete 2
------------------------------------
Noted. I've removed this task:
[D][ ]submit CS2101 reflection(by: 21 Sept)
Now you have 2 tasks in your list
------------------------------------
```

###Searching for a task: ```find```
Looks for a ```keyword``` input by the user among all tasks.  
Format: ```find <keyword>```  
Example: 
```
$find study
------------------------------------
Here are the matching tasks in your list:
1.[T][X]study for CS2113T
------------------------------------
```

###Exiting the program: ```bye```
Terminates the bot.  
Format: ```bye```  
Example:
```
$bye
------------------------------------
Bye. Hope to see you again soon!
------------------------------------
```

###Saving data locally
Tasks stored in the task list will be stored into a text file under ```<home_directory>/data.duke.txt``` everytime the 
bot gets terminated.  
Similarly, the bot will also read from the same file everytime the bot starts to run and load the task list with all 
the previously saved tasks.  
In the scenario where the directory or file required is missing, the bot will automatically create such a directory and 
file for the user.

##Command Summary
| Action  | Format | Example  |
| ------------- | ------------- | ------------- |
| Adding a Todo  | ```todo <description>```  | ```todo study for CS2113T```  |
| Adding a Deadline  | ```deadline <description>/by <date>```  | ```deadline submit CS2101 reflection /by 21 Sept```  |
| Adding an Event  | ```event <description>/at <time>```  | ```deadline submit CS2101 reflection /by 21 Sept```  |
| Listing all tasks  | ```list```  | ```list```  |
| Marking task as completed  | ```done <taskIndex>```  | ````done 1````  |
| Removing a task  | ```delete <taskIndex>```  | ```delete 2```  |
| Searching for a task  | ```find <keyword>```  | ````find study```` |
| Terminating the bot  | ```bye```  | ```bye```  |