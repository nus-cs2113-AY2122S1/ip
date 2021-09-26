# DUKEY-APPY User Guide 

## Contents
[Intro](#introducing-duke-cs2113t)

[How to get Started](#getting-started)

[Welcome-Screen](#welcome-screen)

[Features](#features)

- [active-update](#active-update)
- [anti-duplicate](#anti-duplicate)
- [types-of-task](#3-types-of-task)
- [marking-task-as-done](#marking-finished-task)
- [search-task](#search-for-task)

[Commands](#usage)

- [todo](#todo---adds-a-todo-type-of-task)
- [event](#event---adds-a-event-type-of-task)
- [deadline](#deadline---adds-a-deadline-type-of-task)
- [list](#list---list-out-all-the-current-task)
- [done](#done---marks-a-particular-task-as-done)
- [delete](#delete---delete-a-particular-task)
- [find](#find---allows-user-to-search-for-a-task)
- [list](#help---list-out-the-list-of-command)
- [bye](#bye---terminates-the-app)

[Summary](#summary)

## Introducing DUKE CS2113T
Hello there 👋 and welcome to **Duke**! **Duke** is a Task List designed to store various type of 
task like todo, event and deadline which can be displayed to the user.  

## Getting Started
- Download the [JAR file](https://github.com/YEOWEIHNGWHYELAB/ip/releases/download/A-Release/IndividualProject.jar).
- Ensure that you switch your jdk to java 11 first on your terminal.
- Please add an exception to the directory of your JAR on your anti-virus. 
- The "text-art" folder can be created by extracting the [compressed folder](https://github.com/YEOWEIHNGWHYELAB/ip/releases/download/A-Release/text-art.zip) 
(all text file in text-art folder must be of depth 1 from JAR base directory) that contains all the text-art 
needs to be included in the JAR directory. It can also be manually downloaded from 
[text-art](https://github.com/YEOWEIHNGWHYELAB/ip/tree/master/text-art). 
- data/duke.txt will be created in the JAR directory if it does not exist and the duke.txt must 
be kept empty (unless it is of the correct format - generated from this app).
- Run the command ```java -jar IndividualProject.jar``` at the JAR directory.

## Welcome Screen

3 welcome text art will be shown in sequence (as shown below).

```
____________________________________________________________
 ________  ___  ___  ___  __    _______       ___    ___ 
|\   ___ \|\  \|\  \|\  \|\  \ |\  ___ \     |\  \  /  /|
\ \  \_|\ \ \  \\\  \ \  \/  /|\ \   __/|    \ \  \/  / /
 \ \  \ \\ \ \  \\\  \ \   ___  \ \  \_|/__   \ \    / / 
  \ \  \_\\ \ \  \\\  \ \  \\ \  \ \  \_|\ \   \/  /  /  
   \ \_______\ \_______\ \__\\ \__\ \_______\__/  / /    
    \|_______|\|_______|\|__| \|__|\|_______|\___/ /     
                                            \|___|/      
____________________________________________________________
```
```
            .------.
           /  ~ ~   \,------.      ______
         ,'  ~ ~ ~  /  (@)   \   ,'      \
       ,'          /`.    ~ ~ \ /         \
     ,'           | ,'\  ~ ~ ~ X     \  \  \
   ,'  ,'          V--<       (       \  \  \
 ,'  ,'               (vv      \/\  \  \  |  |
(__,'  ,'   /         (vv   ""    \  \  | |  |
  (__,'    /   /       vv   """    \ |  / / /
      \__,'   /  |     vv          / / / / /
          \__/   / |  | \         / /,',','
             \__/\_^  |  \       /,'',','\
                    `-^.__>.____/  ' ,'   \
                            // //---'      |
          ===============(((((((=================
                                     | \ \  \
                                     / |  |  \
                                    / /  / \  \
                                    `.     |   \
```
```
____________________________________________________________

Hello! I'm Parrot Boi, What can I not do for you (I'm Lazy)? 

c(＞ω＜)ゞ

I'm currently in parrot to-do mode!

Give me something to remember and I will annoy you XD    
____________________________________________________________
```

## Features 

#### Active Update

The app will actively apply the last change to the duke.txt immediately after the command is 
entered in order to prevent the loss of data or changes when an error occur such as user 
accidentally pressing CTRL + C to the terminal... 

#### Anti-Duplicate

Everytime a task is added, it is checked and ensure that no 2 task is exactly the same. 

#### 3 Types of Task

Todo: Purely descriptive. 

Event: Description and time of occurrence in format of (yyyy-mm-dd HH:mm).

Deadline: Description and time which the task is due in format of (yyyy-mm-dd HH:mm). 

#### Marking finished Task

You can mark any particular task in the list as completed. 

#### Search for Task

Allows searching for task via find which can find a matching pattern of String according 
to the list of task. 

## Usage

### `todo` - Adds a todo type of Task

Adds a todo task with the specified description (after "todo") and a notification will be 
shown on the terminal to show its been successfully added. The task will also by default 
be marked as not "done".

Example of usage: `todo Play Apex and Valorant With Friends`

Expected outcome: Since "Play Apex and Valorant With Friends" is the description, it is 
shown on the notification below. And the Task is not "done" [ ]. It will also show the current 
number of task.

```
    ____________________________________________________________
    Got it. I've added this task:
      [T][ ] Play Apex and Valorant With Friends
    Now you have 3 tasks in the list.
    ____________________________________________________________
```

### `event` - Adds a event type of Task

Adds a event task with the specified description and at DateTime. The format must 
strictly be in the form of: event <description> /at <yyyy-mm-dd HH:mm>. 

Example of usage: `event go school for CCA /at 2021-04-23 13:50`

Expected outcome: Since "go school for CCA" is the description, and that "2021-04-23 13:50" 
is the at DateTime. It will be shown as the following below with the description 
along with "MMM d yyyy HH:mm a" format of displaying DateTime. The task marked as not
"done" by default [ ]. It is also of the type [E]. It will also show the current 
number of task.

```
    ____________________________________________________________
    Got it. I've added this task: 
      [E][ ] go school for CCA (at: Apr 23 2021 13:50 PM)
    Now you have 4 tasks in the list.
    ____________________________________________________________
```

### `deadline` - Adds a deadline type of Task

Adds a deadline task with the specified description and by DateTime. The format 
must strictly be in the form of: deadline <description> /by <yyyy-mm-dd HH:mm>. 

Example of usage: `deadline watch anime /by 2021-04-23 12:00`

Expected outcome: Since "watch anime" is the description, and that "2021-04-23 12:00" 
is the at DateTime. It will be shown as the following below with the description 
along with "MMM d yyyy HH:mm a" format of displaying DateTime. The task marked as 
not "done" by default [ ]. It is also of the type [D]. It will also show the current 
number of task.

```
    ____________________________________________________________
    Got it. I've added this task:
      [D][ ] watch anime (by: Apr 23 2021 12:00 PM)
    Now you have 6 tasks in the list.
    ____________________________________________________________
```

### `list` - List out all the current Task

Shows the user the whole list of current Task and is numbered. 

Example of usage: `list`

Expected outcome: List out all the task and numbered from 1 to the last task number. 
It also have type and done status displayed before the description of the task.

```
    ____________________________________________________________
     1.[T][ ] eat lunch 
     2.[E][ ] dinner (at: Apr 23 1997 23:33 PM)
     3.[T][X] Play Apex and Valorant With Friends
     4.[E][ ] go school for CCA (at: Apr 23 2021 13:50 PM)
     5.[D][ ] watch anime (by: Apr 23 2021 12:00 PM)
    ____________________________________________________________
```

### `done` - Marks a particular Task as done

Allows the user to mark a particular task as "done" via its index from the "list" display.
It must strictly be in the format of: done <Task Index>

Example of usage: `done 3`

Expected outcome: It will show a notification of the task being marked as done and this 
change will also be shown in the "list" command when it is entered. 

```
    ____________________________________________________________
    Nice! I've marked this task as done: 
     [T][X] Play Apex and Valorant With Friends
    ____________________________________________________________
```

### `delete` - Delete a particular Task 

Allows the user to delete a particular task via its index from the "list" display. 

Example of usage: `delete 5`

Expected outcome: It will show a notification of the task being deleted and the deletion 
will also take immediate effect. Calling "list" command will show that the task is no 
longer present. It will also show that the number of task have decreased to 4.

```
    ____________________________________________________________
     Noted. I've removed this task: 
     [D][X] watch anime (by: Apr 23 2021 12:00 PM)
     Now you have 4 tasks in the list.
    ____________________________________________________________
```

### `find` - Allows user to search for a Task 

Lets user choose a particular keyword they want to search in Task and return the list of matching
numbered Task to the terminal. 

Example of usage: `find [T]`

Expected outcome: Since the keyword is [T], the program will search for all todo type of Task 
and list it all out on the terminal like so. The rest of the Task will not be shown.

```
    ____________________________________________________________
     1.[T][ ] eat lunch 
     2.[T][X] do homework
    ____________________________________________________________
```

### `help` - List out the list of command 

Shows the list of command as shown on this README document. 

Example of usage: `help`

Expected outcome: A copy of this README document. 

### `bye` - Terminates the App

Shows a goodbye screen before the code terminates itself.

Example of usage: `bye`

Expected outcome: A goodbye art is shown...

```
____________________________________________________________

            .-'''-.        .-'''-.                                                        
           '   _    \     '   _    \ _______                                              
         /   /` '.   \  /   /` '.   \\  ___ `'.   /|                       __.....__      
  .--./).   |     \  ' .   |     \  ' ' |--.\  \  ||    .-.          .-.-''         '.    
 /.''\\ |   '      |  '|   '      |  '| |    \  ' ||     \ \        / /     .-''"'-.  `.  
| |  | |\    \     / / \    \     / / | |     |  '||  __  \ \      / /     /________\   \ 
 \`-' /  `.   ` ..' /   `.   ` ..' /  | |     |  |||/'__ '.\ \    / /|                  | 
 /("'`      '-...-'`       '-...-'`   | |     ' .'|:/`  '. '\ \  / / \    .-------------' 
 \ '---.                              | |___.' /' ||     | | \ `  /   \    '-.____...---. 
  /'""'.\                            /_______.'/  ||\    / '  \  /     `.             .'  
 ||     ||                           \_______|/   |/\'..' /   / /        `''-...... -'    
 \'. __//                                         '  `'-'`|`-' /                          
  `'---'                                                   '..'                           
____________________________________________________________

Bye. Hope to not see you again soon! Me go sleep. Don't wake me!
____________________________________________________________
```

## Summary

<!-- 
![Summary](https://github.com/YEOWEIHNGWHYELAB/ip/blob/master/docs/Summary.png) 
-->
 
| `no.` | `Command` | `Description` |
| --- | --- | --- |
| `1` | `todo` | `add todo task` |
| `2` | `event` | `add event task` |
| `3` | `deadline` | `add deadline task` |
| `4` | `list` | `list all task` |
| `5` | `done` | `select a task to mark as done` |
| `6` | `delete` | `delete a task` |
| `7` | `find` | `search for task` |
| `8` | `help` | `list out all command` |
| `9` | `bye` | `terminate app` |

