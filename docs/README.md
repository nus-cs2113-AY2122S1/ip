# User Guide
**Shima**, aka _Duke_ for its previous name, is an **assistance bot** that provides a platform for users to note down important to-do tasks, deadlines or events. It is a **Command Line Interface (CLI)** based application developed for deskptop user, meaning that **Shima** only accepts commands that are typed using keyboard. For users who frequently use it, **Shima** is fast, convenient and efficient compared to ordinary Graphic User Interface (GUI).

## Setup
To run this application on your PC, please ensure you have done the following:
1. Downloaded and set up [Java 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) on you PC
2. Downloaded the [jar file](https://github.com/tryyang2001/ip/releases/tag/A-Release) for the application (It is safe, no worry :)
3. Open **Terminal**, **Command Prompt** or equivalent on your PC and locate the directory of the **jar.file**
4. Run the command `java -jar ip.jar` at the directory of the jar file

### Quick start

This is the first screen you will see when you run the jar file:
```
	              ##*                                 
	   .      . .&####%                               
	    #%%%%((&%######%**.                           
	    %#############%#%&&%%%%%##(*.                 
	 . .%%#######%%%%%##############%%%%#(.           
	/##&@%####&%####&#####%&&&&&&&&&%%%####&/  .      
	&######%%######%&%%%#################%%%%&#       
	(@####&#####%&%########################%###@(     
	,&%#%%%%##%%############%%%%%%%%%%%##########%.   
	*&#&%%&#%&########&%#(%#/((((((/****#&%&%#####%,  
	  ,&#%%%%#####%%#(%(((&#(&(((((((((/%#(((%##%%#%. 
	  (%##%%####%%(((/%&((%&#%##((((((((&#(((%#(((&&, 
	  (%#%&####%#((##(#(/###.%#.*(##(((%, /##((%#(#( .
	  *&(&%###%#(((((#/@%%%&,. ...     &%%&%  (&###*  
	 . (%%%###%(((((#/#####&, .. .... /&(#(&,.%(((#(  
	    *&&##%%(((((%*(%###%. .. .. . *%(##&, (%((#(  
	      (&#%%((((#%/ .**,. ..... . ...,,,. . (#(%(  
	        .(%(((((&/ .   .  ...... .. .. . .(%/#%,  
	          .(%(((#&*,,.           . .,*/*%#((#&*   
	              ,/(#(    .,,******,.     ####%*     
   __          ________ _      _____ ____  __  __ ______    
   \ \        / /  ____| |    / ____/ __ \|  \/  |  ____|  
    \ \  /\  / /| |__  | |   | |   | |  | | \  / | |__     
     \ \/  \/ / |  __| | |   | |   | |  | | |\/| |  __|    
      \  /\  /  | |____| |___| |___| |__| | |  | | |____   
     __\/_ \/   |______|______\_____\____/|_|  |_|______|_ 
    / ____| |  | |_   _|  \/  |   /\   |  __ \|_   _| \ | |
   | (___ | |__| | | | | \  / |  /  \  | |__) | | | |  \| |
    \___ \|  __  | | | | |\/| | / /\ \ |  _  /  | | | . ` |
    ____) | |  | |_| |_| |  | |/ ____ \| | \ \ _| |_| |\  |
   |_____/|_|  |_|_____|_|  |_/_/    \_\_|  \_\_____|_| \_|

Hi there! My name is Shima Rin and I am a robot that can help you do some wonderful jobs!
--------------------------------------------------------------------------------------------------------------------------------------------------
* Version 9.0                                                                                                                                    *
* In this version, I am equipped with several functions, including PROFILE, HELP, TODO, DEADLINE, EVENT, LIST, DONE, DELETE, DATE, FIND and EXIT *
* To know more about how to use the command, you can type "help" to view the help menu written by me :P                                          *
* I am able to understand your command in any case, meaning that you are free to enter command with any case :D                                  *
--------------------------------------------------------------------------------------------------------------------------------------------------
```

### Features
Features below describe the syntax and behavior of each command. Notice that codes that are in bracket **()** are **not mandatory** field, while codes that are in square bracket **[]** are **mandatory**.  
### View bot profile - `profile`
To view the profile of the cute bot, type the command `profile`

### View help menu - `help`
To view the help menu and guidelines on how to use the command, type the command `help`

### Add a new to-do task - `todo`
To add a new to-do task, use the command `todo` with syntax: `todo [TASK_DESCRIPTION]`\
Eg: 
  ```
  todo finish assignment
  ```
Expected output:
  ```
    @-------------------------------------------------------------------------------------@
	    Class type [T] "finish assignment" has been added to the list! (1 tasks in total)
	@-------------------------------------------------------------------------------------@
  ```

### Add a new event task - `event`
To add a new event task, use the command `event` with syntax: `event [TASK_DESCRIPTION] /(at) [START_TIME] [-] [END_TIME]\
Eg:
```
event swimming /at 2 - 4 pm
```
Expected output:
```
	@-------------------------------------------------------------------------------------------@
	    Class type [E] "swimming (at: 2 - 4 pm)" has been added to the list! (2 tasks in total)
	@-------------------------------------------------------------------------------------------@
```
**Note:** The number of tasks in total depends on how many task you have saved. The blackslash `/` and the dash `-` are mandatory for adding a new event.

### Add a new deadline task - `deadline`
To add a new deadline task, use the command `deadline` with syntax: `deadline [TASK_DESCRIPTION] /(by) [END_DATE of format yyyy-MM-dd] [END_TIME of utc time HH:mm]\
Eg:
```
deadline submit project /by 2021-10-1 23:59
```
Expected output:
```
	@-------------------------------------------------------------------------------------------------------------@
	    Class type [D] "submit project (by: 01 Oct 2021 11.59 pm)" has been added to the list! (3 tasks in total)
	@-------------------------------------------------------------------------------------------------------------@
```
**Note:** The number of tasks in total depends on how many task you have saved. The blackslash `/` is mandatory for adding a new deadline. The format for **_end date_** must be `year-month-day` and the format for **_end time_** must be in utc format `hour:minute`

### View the to-do list - `list`
To view the to-do list of the tasks added, use the command `list`
Eg:
```
list
```
Example of one possible output:
```
	/-------------------------------------------------------------\
	| My to-do list:                                              |
	| [E][ ] 1. swimming (at: 2 - 4 pm)                           |
	| [D][ ] 2. submit project (by: 01 Oct 2021 11.59 pm)         |
	| [T][ ] 3. finish assignment                                 |
	\-------------------------------------------------------------/
	For your knowledge, 
	the first [ ] indicates the type of the task ('T' for to-do, 'D' for deadline, 'E' for event)
	the second [ ] indicates whether the task is completed:
	[X] when the task is marked completed	[ ] when the task is not done.
```
### Mark task as done - `done`
To mark task(s) as done , use the command `done` with syntax: `done [TASK_INDICES]`\
Eg:
```
done 1 3
```
Example of one possible output:
```
	Hooray! Task number 1 has been marked completed!
	[✔] swimming
	Hooray! Task number 3 has been marked completed!
	[✔] finish assignment
```
**Note:** It is possible to mark single or multiple tasks as done with just one command.\

### Delete task from to-do list - `delete`
To delete unused task from the to-do list, use the command `delete` with syntax: `delete [TASK_INDICES]`\
Eg:
```
delete 1
```
Example of one possible output:
```
	@------------------------------------------------------------@
	   I have removed this task: [E][X] swimming (at: 2 - 4 pm)
	@------------------------------------------------------------@
You have left 2 tasks to do!
```
Eg:
```
delete all
```
Example of one possible output:
```
	@-----------------------------------------------@
	   All tasks have been removed! Time to chill?
	@-----------------------------------------------@
```
**Note:** It is possible to delete single/multiple task with just one command. In addition, it is also possible to delete all task by using the keyword `all`.\

### Find task with deadline - `date`
To find tasks in to-do list which has a deadline date same as a specific date, use the command `date` with syntax: `date [DATE_TO_FIND of format yyyy-MM-dd]`\
Eg:
```
date 2021-10-1
```
Example of one possible output:
```
@--------------------------------------------------------------------@
	Here are the tasks that I found with the deadline you given:
		1. submit project (by: 01 Oct 2021 11.59 pm)
	There are 1 tasks matched with the deadline
@--------------------------------------------------------------------@
```
### Find task with keyword - `find`
To find tasks in to-do list which contains a specific keyword, use the command `find` with syntax: `find [KEYWORD]`\
Eg:
```
find submi
```
Example of one possible output:
```
	@----------------------------------------------------------------------@
		Here are the tasks that matched the keyword
			1. [D][ ] submit project (by: 01 Oct 2021 11.59 pm)
			2. [T][ ] presentation slide submission
			3. [E][ ] submit clothes (at: 10 am - 12 pm)
	@----------------------------------------------------------------------@
```

### Ends the program - `exit`
To terminate the program, use the command `exit` or `bye`
Expected output:
```
	@---------------------------------@
	   Bye! Hope to see you again :D
	@---------------------------------@

Process finished with exit code 0
```

### Command Code Summary
The table below summarizes all the commands available in this application.
Command | Usage | Syntax
--------|-------|-------
`profile`| views the profile with cute picture of the bot character | `profile`
`help`|views the help menu from the application | `help`
`todo`|adds a new to-do task to the to-do list | `todo [TASK_DESCRIPTION]`
`event`|adds a new event task to the to-do list | `event [TASK_DESCRIPTION] /(at) [START_TIME] [-] [END_TIME]`
`deadline`|adds a new deadline task to the to-do list | `deadline [TASK_DESCRIPTION] /(by) [END_DATE yyyy-MM-dd] [END_TIME utc HH:mm]`
`list` | views the to-do list | `list`
`done` | marks tasks as done | `done [TASK_INDEX/INDICES]`
`delete` |deletes unused tasks | `delete [TASK_INDEX/INDICES]/all`
`date` |finds deadline task with the input date as deadline | `date [DATE yyyy-MM-dd]`
`find` |finds tasks that contain a specific keyword | `find [KEYWORD]`
`exit`/`bye` |exit or terminate the program | `exit` / `bye`
