# SMURF VILLAGE User Guide
Hello this is Smurf Village, your command line chatbot to get Smurfs managing your tasks for you!

## Quick start
1. Before starting, install the latest version of Java 11 on your computer.
2. Download the latest release of Smurf Village at [here](https://github.com/leowyy99/ip/releases/download/A-Release/ip.jar)
3. Move the .jar file to your desired folder.
4. Open a terminal in the folder with the .jar file and execute the command ```java -jar ip.jar```
5. If the program runs as intended, you should see the following output:
```
	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@@@@@@@@@@@@@@@@..............@@@@@@@@@@@@@@@@@@@@
	@@@@@@@@@@@@@(...................@@@@@@@@@@@@@@@@@
	@@@@@@@@@@@@@@.....................@@@@@@@@@@@@@@@
	@@@@@@@@@@@@@@@@@.................../@@@@@@@@@@@@@
	@@@@@@@@@@@@@@@@@@.................../@@@@@@@@@@@@
	@@@@@@@@@@@((#@.((((((((((............@@@@@@@@@@@@
	@@@@@@@@@#((((((((((((((/(((/.........@@@@@@@@@@@@
	@@@@@@@@@@@%#(((((((((((((((//*(/((/,@@@@@@@@@@@@@
	@@@@@@@@@@@@#####(((((((((((/(((/(((@@@@@@@@@@@@@@
	@@@@@@@@@@@@@@#########((((/(((((((@@@@@@@@@@@@@@@
	@@@@@@@@@@@@@@@@@%######(####%#&@@@@@@@@@@@@@@@@@@
	@@@@@@@@@@@@@@@@@(.,,*/(###((((#/##@@@@@@@@@@@@@@@
	@@@@@@@@@@@@@@@(...,,*//(((((((###/#@@@@@@@@@@@@@@
	@@@@@@@@@@@@@@@* ..,,((((((((((####/#@@@@@@@@@@@@@
	@@@@@@@@@@@@@@@((((((((((((((((####/#%@@@@@@@@@@@@
	@@@@@@@@@@@@@@@@@((((((((((((((#######@@@@@@@@@@@@
	@@@@@@@@@@@@@@@@@@@@@@%((((((((###&@@@@@@@@@@@@@@@
	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	 _______  __   __  __   __  ______    _______ 
	|       ||  |_|  ||  | |  ||    _ |  |       |
	|  _____||       ||  | |  ||   | ||  |    ___|
	| |_____ |       ||  |_|  ||   |_||_ |   |___ 
	|_____  ||       ||       ||    __  ||    ___|
	 _____| || ||_|| ||       ||   |  | ||   |    
	|_______||_|   |_||_______||___|  |_||___|    
	==============================================
	...la la la la la la sing a happy song

	Welcome to Smurf Village.
	Start smurfing now!!
	==============================================
	Call out a smurf to do a job for you!
```

## Features

### Add todo task: `todo [description]`
Add a todo task by providing description as an argument.

Example of usage: `todo make magic potion`

Expected output:
```
todo make magic potion
	==============================================
	Handy Smurf is here to give you a hand!
	I have added: 
	1. [T][ ] make magic potion
	==============================================
```

### Add a deadline task: `deadline [description] /by [due date]`
Add a deadline task by providing description as the first argument, followed by the `/by` flag and the due date. Note: date must be given in YYYY-MM-DD format.

Example of usage: `deadline collect berries /by 2121-12-12`

Expected output:
```
deadline collect berries /by 2121-12-12
	==============================================
	Handy Smurf is here to give you a hand!
	I have added: 
	2. [D][ ] collect berries by: Dec 12 2121
	==============================================
```

### Add an event task: `event [description] /at [event date]`
Add an event task by providing description as the first argument, followed by the `/at` flag and the event date. Note: date must be given in YYYY-MM-DD format.

Example of usage: `event cake party /at 2121-12-12`

Expected output:
```
event cake party /at 2121-12-12
	==============================================
	Handy Smurf is here to give you a hand!
	I have added: 
	3. [E][ ] cake party at: Dec 12 2121
	==============================================
```

### List out all tasks: `list`
Tasks will be list out in chronological order of addition.

Example of usage: `list`

Expected output:
```
list
	==============================================
	"Tracker Smurf!! I need you here!!"
	1. [T][ ] make magic potion
	2. [D][ ] collect berries by: Dec 12 2121
	3. [E][ ] cake party at: Dec 12 2121
	==============================================
```

### Mark task as done: `done [item number]`
Mark a task as done by specifying the item number as the argument.

Example of usage: `done 1`

Expected output:
```
done 1
	==============================================
	Brainy Smurf: aah another thing done
	1. [T][X] make magic potion
	==============================================
```

### Delete a task: `delete [item number]`
Delete a task by specifying the item number as the argument.

Example of usage: `delete 1`

Expected output:
```
delete 1
	==============================================
	I will get Weakling smurf to do it for you.
	   [T][X] make magic potion
	==============================================
```

### Search for tasks with keyword: `find [keyword]`
Search for tasks with specified keyword by providing the keyword as an argument.

Example of usage: `find party`

Expected output:
```
find party
	==============================================
	2. [E][ ] cake party at: Dec 12 2121
	==============================================
```

### Terminate the program: `bye`
Chat bot will be terminated and all data will be saved to local hard drive.

Example of usage: `bye`

Expected output:
```
bye
	==============================================
	oh shucks! Gargamel is here..we gotta hide
	==============================================
```

## Command Summary

Command | Format | Purpose
--------|---------|-----------
`todo` | `todo [description]` | Add a todo task
`deadline` | `deadline [description] /by [due date]` | Add a deadline task
`event` | `event [description] /at [event date]` | Add an event task
`list` | `list` | List out all tasks
`done` | `done [item index]` | Mark task as done
`delete` | `delete [item index]` | Delete task
`find`  |  `find [keyword]` | Find tasks with keyword
`bye` | `bye` | Terminate program

