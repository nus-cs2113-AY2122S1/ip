# User Guide
Duke is a **desktop app for users to quickly manage their to-do list** via <a href="#CLI-Guide">Command Line Interface(CLI) </a> and <a href="#GUI-Guide">Graphical User Interface (GUI)</a>.

## Quick Start
1. Ensure `Java 11` are installed in your computer.
2. Download the latest `ip.jar` from here.
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Type the following command in your terminal to run this program: java -jar ip.jar (You should change directory to where the ip.jar file is located or provide the absolute path of ip.jar).
5. Duke will ask for you to choose CLI mode or GUI mode.
6. Duke will ask for your name. If you are a new user, Duke will initialize an empty task list for you. If you have run Duke before, it will restore your saved tasks for you.
7. After Duke starts serving, type the command in the command box and press Enter to execute it (CLI mode), or press buttons to give corresponding commands (GUI mode).  


## <span id="CLI-guide">Command Line Interface(CLI) Guide</span>
- <a href="#features"> Features </a>
- <a href="#FAQ"> FAQ </a>
- <a href="#summary"> Command Summary </a>

### <span id="features">Features</span>
1. <a href="#help">Get help</a>
2. <a href="#add-task">Add task</a>
3. <a href="#list-task">List all tasks</a>
4. <a href="#complete-task">Complete a task</a>
5. <a href="#find-task">Find task(s)</a>
6. <a href="#sort-task">Sort tasks</a>
7. <a href="#delete-task">Delete task</a>
8. <a href="#exit">Exit</a>

> Notes:
> - words in <> are required parameters from users
> - words in [] are optional parameters from users
<br/> e.g. for `deadline <task description> /<YYYY-MM-DD> [HH:MM]`, \<task description\>, and \<YYYY-MM-DD\> are two required parameters, while \[HH:MM\] can be omitted. For example, `deadline cs2113 ip /2021-09-28` and `deadline cs2113 ip /2021-09-28 18:00` are both valid.

#### <span id="help">Get help</span>
List all the commands format if users forget them
##### format: `help`
![img](help.png)

<br/>

#### <span id="add-task">Add task</span>
There are three possible task types that can be added.
* `todo`: A task without time constraint
* `deadline`: A task needs to be completed **before certain time**.
* `event`: A task needs to be complete **at certain time**.
##### format:
1. add a `todo` task: `todo <task description>`
###### example:
![img](todo.png)<br/>
2. add a `deadline` task: `deadline <task description> /<YYYY-MM-DD> [HH:MM]`
###### example:
![img](deadline.png) <br/>
3. add a `event` task: `event <task description> /<YYYY-MM-DD> \<HH:MM\>`
###### example: 
![img](event.png)

<br/>  

#### <span id="list-task">List all tasks</span>
Show a list of all tasks.
##### format: `list`
![img](list.png)

<br />

#### <span id="complete-task">Complete a task</span>
User can set a task at specific index after he/she has completed it.
##### format: `done <task index>`
- The index refers to the index number in the list.
- The index must be a positive number, and should not exceed the total number of tasks inside the list. Otherwise, Duke may throw a warning.
##### example:
![img](done.png)

<br/>  

#### <span id="find-task">Find some task(s)</span>
A user can search some task(s) containing certain keywords
##### format: `find <keyword>`
##### example:
![img](find.png)

<br/>  

#### <span id="sort-task">Sort tasks</span>
Tasks can be sorted based on emergency. Tasks have closer deadline will be put in front.
##### format: `sort`
##### example:
![img](sort.png)


#### <span id="delete-task">Delete tasks</span>
delete a task at specific index.
##### format: `delete <task index>`
- The index refers to the index number in the list.
- The index must be a positive number, and should not exceed the total number of tasks inside the list. Otherwise, Duke may throw a warning.
##### example:
![img](delete.png)

  
<br/>  

#### <span id="exit">Exit</span>
Exit the program and automatically save all the tasks.
##### format: `bye`
##### example:
![img](exit.png)

<br/>  

#### Edit the data file
Tasks data are saved as a `txt` file `[JAR File location]/UserStatus/[Username].txt`.  
Advanced users are welcome to update data directly by editing that data file.

> ❕**Caution**  
If your changes to the data file makes its format invalid, Duke will discard all data and start with an empty task list.



### <span id="FAQ">FAQ</span>
Q: Where is my task list data stored? </br>
A: There is a directory called `UserStatus` under where you store `ip.jar`. Your task list is named as `<your_username>.txt` and stored inside it.


### <span id="summary">Command Summary</span>

Action | Command Format | Example
--- | --- | --- | 
Add a todo task | todo <task description>  | `todo return book`
Add a deadline task | deadline <task description> /<YYYY-MM-DD> \[HH:MM\] | `deadline cs2113 ip /2021-09-28 18:00` <br/> `deadline cs2113 ip /2021-09-28`
Add an event task | event <task description> /<YYYY-MM-DD> \<HH:MM\> | `event cs2113 lecture /2021-10-01 14:00`
Delete a task | delete taskIndex | `delete 2`
Complete a task | done taskIndex | `done 1`
find a task | find <keyword> | `find cs2113`
List all tasks | list | `list`
Sort tasks | sort | `sort`
Get help | help | `help`
Exit | bye | `bye`

## <span id="GUI-Guide">Graphical User Interface(GUI) Guide</span>
