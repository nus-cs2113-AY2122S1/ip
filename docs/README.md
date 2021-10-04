<link rel="stylesheet" href="styles.css">

# User Guide

Duke is a **desktop app for managing daily tasks, optimized for use via the command-line interface (CLI)**. If you can type fast, Duke can manage your tasks faster than traditional GUI apps.

<br>

## Table of contents

* [Quick start](#quick-start)
* [Features](#features)
  * [Viewing help: `help`](#viewing-help-help)
  * [Adding a task](#adding-a-task)
    * [Todo: `todo`](#todo-todo)
    * [Deadline: `deadline`](#deadline-deadline)
    * [Event: `event`](#event-event)
  * [Listing all tasks: `list`](#listing-all-tasks-list)
  * [Marking a task as done: `done`](#marking-a-task-as-done-done)
  * [Deleting a task: `delete`](#deleting-a-task-delete)
  * [Filtering tasks by date: `date`](#filtering-tasks-by-date-date)
  * [Filtering tasks by keyword: `find`](#filtering-tasks-by-keyword-find)
  * [Exiting the app: `exit`](#exiting-the-app-exit)
  * [Saving the data](#saving-the-data)
  * [Editing the data file](#editing-the-data-file)
* [FAQ](#faq)
* [Command summary](#command-summary)

<br>

## Quick start

1. Ensure you have **Java 11 or above** installed in your Computer.
2. Download the latest `Duke.jar` from [here](https://github.com/richwill28/ip/releases).
3. Copy the file to the folder you want to use as the **home folder** for your Duke.
4. Open a terminal window and type `java -jar Duke.jar`.
5. If you have successfully run the program, a greeting from Duke should appear as follows:

    ```
        __________________________________________________________________________________
                        -/ /mmddddmNmh` .:`              
                  `/sdydNNmhmNMMMMMMMMhymMm+`            
                -sNMMMMMMNNddNMMMNMMMMmmMMMMms.          
               /NMMMNMMNMMmmdNMNMmMMNMmNMMMMMNyy-        
             .:oNMMMMNNMmhos+so+/::ohdmMMMMMmmMMm.       
            /mNMMMNNNdy+.````````````../ydNNNMMMMy       
            /MMMMNmy/.`  `````````  `````.sdNMMMMm+      
            sNNNNNy.``  ```.:`` ``` ```````./dmNMMh`     
           `ommdy:```  ` ``sy`` ```  `````````:mmN:      
           +dNNh.```` ````:dm+` ````  `````````--:       
           -dMm.````  `s``hdmm-`````````````````-:       
            -N+````` `sm`/mmmmh`.-```-.``````````-       
            .:```````+mh.dmmmmm/`y:-`-d/`````` ```       
            ````````:md/oymmmmmh/ymh+/mmo-````  ```      
             `` ````dmmymmmmmmmmmmmmd+`.++-````o. .      
            ``` `` `mmsssdmmmmmmmmmh-s `.m.```-dy .      
             .`  ` +h:sy.`/dmmmmmmmdy`./-N-` `+dm`.      
             .`   `mdsN-```dmmmmmmmmNy+smm-- `hds ``     
              `   -dmmNd/:+mmmmmmmmmmmmmmmy/ .d+  ``     
             ``   .hdmmNmddmmmmmmmmmmmmmmyd+ ...  .``    
             `.`   yddmmmdmmmmmmmmmmmmmmm+mo`  `` `..    
              ```  `/+hmmmmmmmmmmmmmmmmmm/m:` ``- . .`   
            `` ` `  ```ymmmmmmmmmmmmmmmmm/: ` `.``-//`   
           :hho- `  ``  :sdmmmmmmmmmmmmd+`.    .`:dMm-::.
         `/hmmdN:`` ` `   `:shmmmmmmmdds..``   `/NMMMNMMs
         +ddddmMy```. ``   odyhhhhhhhdmdy..`   .mNMMMMMMd
          os//mMm```+o-``-odddhyhhhdmmdMN/:`` oMMMMMMMNm+
    
         Konnichiwa! I'm your personal maid. Call me Maid-chan!
         What can I do for you? (//ω//)
        __________________________________________________________________________________
    
    ```

6. Type the command below the greeting and press <kbd>Enter</kbd> to execute it. Some example commands you can try:

    * `help`: Displays a short manual of all the available commands.
    * `exit`: Exits the app.

7. Refer to the [Features](#features) below for details of each command.

<br>

## Features

<div class="notes box" markdown="1">

📝 **Notes:**
  * All commands are case-sensitive. For example, `List` does not equal `list`.
  * Words in `UPPER_CASE` are parameters. For example, `find KEYWORD`.

</div>

<br>

### Viewing help: `help`

Shows a short manual of all the available commands.<br>
<br>
**Format**: `help`

**Example**:
```
help
    __________________________________________________________________________________
     COMMAND   FORMAT                                  PURPOSE                                 
     --------  --------------------------------------  ------------------------------          
     todo      todo DESCRIPTION                        To add a todo                           
     deadline  deadline DESCRIPTION /by TASK_DEADLINE  To add a deadline                       
     event     event DESCRIPTION /at TASK_PERIOD       To add an event                         
     list      list                                    To list all tasks                       
     done      done TASK_NUMBER                        To mark a task as done                  
     delete    delete TASK_NUMBER                      To delete a task                        
     date      date YYYY-MM-DD                         To filter the tasks by date             
     find      find KEYWORD                            To filter the tasks by keyword          
     exit      exit                                    To exit the app                         

     Note: Words in UPPER_CASE are parameters
           For more info, visit https://richwill28.github.io/ip/
    __________________________________________________________________________________

```

<br>

### Adding a task

* ### Todo: `todo`

  Adds a todo to the task list.<br>
  A todo is a task that contains only a description.<br>
  <br>
  **Format**: `todo DESCRIPTION`
  
  **Example**:
  ```
  todo watch anime
      ________________________________________________________________________________
       Noted. I've added this task:
         [T][ ] watch anime
       Now you have 1 tasks in the list.
      ________________________________________________________________________________
  
  ```

* ### Deadline: `deadline`

  Adds a deadline to the task list.<br>
  A deadline is a task that contains a description and a time period of when the task is due.<br>
  <br>
  **Format**: `deadline DESCRIPTION /by TASK_DEADLINE`
  
  **Example**:
  ```
  deadline do project /by next week
      ________________________________________________________________________________
       Noted. I've added this task:
         [D][ ] do project (by: next week)
       Now you have 2 tasks in the list.
      ________________________________________________________________________________
  
  deadline join club /by 2020-03-14
      ________________________________________________________________________________
       Noted. I've added this task:
         [D][ ] join club (by: Mar 14 2020)
       Now you have 3 tasks in the list.
      ________________________________________________________________________________
  
  ```

* ### Event: `event`

  Adds an event to the task list.<br>
  An event is a task that contains a description and a time period of when the task occurs.<br>
  <br>
  **Format**: `event DESCRIPTION /at TASK_PERIOD`
  
  **Example**:
  ```
  event project meeting /at 2020-03-14
      ________________________________________________________________________________
       Noted. I've added this task:
         [E][ ] project meeting (at: Mar 14 2020)
       Now you have 4 tasks in the list.
      ________________________________________________________________________________
  
  ```

  <div class="tip box" markdown="1">

  💡 **Tip:** If the correct ISO format `YYYY-MM-DD` is given, both `Deadline` and `Event` will automatically format it.

  </div>

<br>

### Listing all tasks: `list`

**Format**: `list`

**Example**:
```
list
    ________________________________________________________________________________
     Here are the tasks in your list:
     1. [T][ ] watch anime
     2. [D][ ] do project (by: next week)
     3. [D][ ] join club (by: Mar 14 2020)
     4. [E][ ] project meeting (at: Mar 14 2020)
    ________________________________________________________________________________

```

<br>

### Marking a task as done: `done`

**Format**: `done TASK_NUMBER`

**Example**:
```
done 1
    ________________________________________________________________________________
     Good job! I've marked this task as done:
       [T][X] watch anime
    ________________________________________________________________________________

```

<br>

### Deleting a task: `delete`

**Format**: `delete TASK_NUMBER`

**Example**:
```
delete 1
    ________________________________________________________________________________
     Noted. I've removed this task:
       [T][X] watch anime
     Now you have 3 tasks in the list.
    ________________________________________________________________________________

```

<br>

### Filtering tasks by date: `date`

**Format**: `date YYYY-MM-DD`

**Example**:
```
date 2020-03-14
    ________________________________________________________________________________
     Here are the tasks on Mar 14 2020:
     1. [D][ ] join club (by: Mar 14 2020)
     2. [E][ ] project meeting (at: Mar 14 2020)
    ________________________________________________________________________________

```

<br>

### Filtering tasks by keyword: `find`

**Format**: `find KEYWORD`

**Example**:
```
find project
    ________________________________________________________________________________
     Here are the matching tasks in your list:
     1. [D][ ] do project (by: next week)
     2. [E][ ] project meeting (at: Mar 14 2020)
    ________________________________________________________________________________

```

<br>

### Exiting the app: `exit`

**Format**: `exit`

**Example**:
```
exit
    ________________________________________________________________________________
     Bye ❤ Hope to see you again soon! (≧▽≦)
    ________________________________________________________________________________

```

<br>

### Saving the data

Duke data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<br>

### Editing the data file

Duke data are saved as a `txt` file located at `[JAR file location]/data/duke.txt`. Advanced users are welcome to update data directly by editing that data file.

The format of the save file is as follows: `TYPE | STATUS | DESCRIPTION | SCHEDULE`

* `TYPE` is either `T` for `Todo`, `D` for `Deadline`, or `E` for `Event`.
* `STATUS` is the status of the task. The value is either `0` (not done) or `1` (done).
* `DESCRIPTION` is the description of the task.
* `SCHEDULE` is the schedule of the task. Only applicable for `Deadline` and `Event`.

Example:

```
T | 0 | read book
D | 1 | return book | June 6th
E | 0 | project meeting | Aug 6th 2-4pm
```

<div class="caution box" markdown="1">

🚩 **Caution**: If your changes to the data file makes its format invalid, Duke will discard all data and start with an empty data file at the next run.

</div>

<br>

## FAQ

**Q:** How do I transfer my data to another Computer?<br>
**A:** Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke home folder.<br>

<br>

## Command summary

| Command    | Format                                   | Example                                |
| :--------- | :--------------------------------------- | :------------------------------------- |
| `help`     | `help`                                   | `help`                                 |
| `todo`     | `todo DESCRIPTION`                       | `todo watch anime`                     |
| `deadline` | `deadline DESCRIPTION /by TASK_DEADLINE` | `deadline do project /by next week`    |
| `event`    | `event DESCRIPTION /at TASK_PERIOD`      | `event project meeting /at 2020-03-14` |
| `list`     | `list`                                   | `list`                                 |
| `done`     | `done TASK_NUMBER`                       | `done 1`                               |
| `delete`   | `delete TASK_NUMBER`                     | `delete 1`                             |
| `date`     | `date YYYY-MM-DD`                        | `date 2020-03-14`                      |
| `find`     | `find KEYWORD`                           | `find project`                         |
| `exit`     | `exit`                                   | `exit`                                 |
