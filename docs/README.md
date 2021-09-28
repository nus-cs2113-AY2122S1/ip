# User Guide
C-3PO is a chat-bot that allows users to store and keep track of three different types of tasks: deadlines, events and todo tasks.
It also allows them to mark different tasks as done and search for specific tasks by filtering out tasks based on different parameters.

## Features 
* List all tasks 
* Add a task 
  * Add a deadline 
  * Add an event
  * Add a todo 
* Mark a task as done 
* Delete a task
* Filter tasks by keyword(s)
* Filter tasks by date 
* Exit the program

### Quick start
1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest iP.jar from here.

3. Copy the iP.jar file to the folder you want to use as the home folder for C-3PO.

4. **If you are using Windows**, click the Windows Start button and type in 'cmd' to open your command prompt. Then, navigate to the directory where iP.jar file is located.

5. **If you are using Mac or Linux or other systems**, open the terminal of your respective system and navigate to the directory where iP.jar file is located.
6. Run the command "java -jar {filename}.jar".
7. If the aforementioned steps are done properly, you should see the following.

```
File not found. Automatic text file creation initiated master!
____________________________________________________________
Hello! I am C-3P0! Human-cyborg relations! 
 
       /~\
      |oo )
      _\=/_
     /     \
    //|/.\|\\
   ||  \_/  ||
   || |\ /| ||
    # \_ _/  #
      | | |
      | | |
      []|[]
      | | |
     /_]_[_\

What can I do for you my master?

____________________________________________________________

Type something: 
```
8. Refer to the Features below and enter the associated commands and press Enter to execute them. 

### List all tasks: `list`
Shows a list of all the user's tasks.
#### Format: `list`
Example of usage:

`list`

Expected outcome:

```
Accessing archives...
1. [T][X] buy groceries
2. [D][X] finish up CS2113T coding exercises (by: Sep 25 2021 4PM)
3. [E][ ] birthday celebration (at: Sep 29 2021 12PM)
```

### Add a deadline: `deadline`
Adds a deadline task along with the date and time of the deadline to the user's list of tasks.
#### Format: `deadline {task description} /by {YYYY-MM-DD HH:MM}`
Example of usage:

`deadline finish up CS2113T coding exercises /by 2021-09-25 16:00`

Expected outcome:

```
Added to Galactic database:
[D][ ] finish up CS2113T coding exercises (by: Sep 25 2021 4PM)
```

### Add an event: `event`
Adds an event task along with the date and time of the event to the user's list of tasks.
#### Format: `event {task description} /at {YYYY-MM-DD HH:MM}`
Example of usage:

`event birthday celebration /at 2021-09-29 12:00`

Expected outcome:

```
Added to Galactic database:
[E][ ] birthday celebration (at: Sep 29 2021 12PM)
```
### Add a todo: `todo`
Adds a todo task to the user's list of tasks.
#### Format: `todo {task description}`
Example of usage:

`todo buy groceries`

Expected outcome:

```
Added to Galactic database:
[T][ ] buy groceries
```

### Mark a task as done 
Marks a specified task as done.
#### Format: `done {task index in list}`
Example of usage:

`done 2`

Expected outcome:

```
The following task has been marked as done Master!
2. [D][X] finish up CS2113T coding exercises (by: Sep 25 2021 4PM)
```

### Delete a task
Deletes a specified task and shows how many tasks are remaining.
#### Format: `delete {task index in list}`
Example of usage:

`delete 3`

Expected outcome:

```
Taking one last look Master, at this Task. Removing the following from my memory
3. [E][ ] birthday celebration (at: Sep 29 2021 12PM)
Goodbye Task, may the force be with you. You have 2 task(s) left Master
```

### Filter tasks by keywords
Shows a list of all the user's tasks that contain keywords provided by the user.
#### Format: `find {keyword(s)}`
Example of usage:

`find buy`

Expected outcome:

```
Accessing archives...
Generating all the tasks that contain "buy"...
1. [T][X] buy groceries
2. [T][ ] buy notebooks
3. [D][ ] buy membership card (by: Oct 21 2021 7PM)
```

### Filter tasks by date
Shows a list of all the user's tasks that occur on a specific date provided by the user.

#### Format: `date {YYYY-MM-DD}`
Example of usage:

`date 2021-10-21`

Expected outcome:

```
Accessing archives...
Generating all the tasks that occur on "2021-10-21"...
1. [D][ ] buy membership card (by: Oct 21 2021 7PM)
2. [E][ ] marathon (at: Oct 21 2021 8AM)
```

### Exit the program
Exits the program.
#### Format: `bye`
