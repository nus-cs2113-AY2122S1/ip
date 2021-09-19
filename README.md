# Duke

Duke is your personalized CLI (Command Line Interface) task management system.

## Quick start

1. Install *Java SE Development Kit 11* or any newer version
2. Download the latest `duke.jar` from [releases](https://github.com/yuejunfeng0909/ip/releases)
3. Open *Terminal* on your desktop and navigate to the folder where `duke.jar` is located.
4. Run `java -jar duke.jar`
5. Type the command in the command box and press Enter to execute it.
   
Some example commands you can try:

`list` : Lists all tasks.

`deadline CS2113T coding practice /by 21/9/2021 1900` : Create a deadline named "CS2113T coding practice" that needs to be completed by 19:00 on 21 Sep 2021

`done 1` : Mark the 1st task on the task list as done.

`delete 1` : Deletes the 1st task on the task list.

`bye` : Exits the app.

## Features

1. Creating Tasks:
   1. New to-do: `todo` 
      <br/> Add new to-do task to the task list.
      <br/>Format: `todo DESCRIPTION`
      <br/>Example: `todo Write code` will create a to-do named *Write code*
      ```
      Got it. I've added this task:
       1:[T][ ] Write code
      Now you have 1 tasks in the list.
      ```
   2. New deadline: `deadline`
      <br/> Add new deadline task to the task list.
      <br/> Format: `deadline DESCRIPTION /by TIME` where `TIME` follows `d/M/yyyy kk[mm]` format
      <br/> Example: `deadline CS2113T coding practice /by 21/9/2021 1900` will create a deadline named *CS2113T coding practice* that needs to be completed by 19:00 on 21 Sep 2021
      ```
      Got it. I've added this task:
       2:[D][ ] CS2113T coding practice (by 19:00, Sep 21 2021)
      Now you have 2 tasks in the list.
      ```
   3. New event: `event`
      <br/> Add new event to the task list.
      <br/> Format: `event DESCRIPTION /at START_TIME /to /END_TIME` where `START_TIME` and `END_TIME` follow `d/M/yyyy kk[mm]` format
      <br/> Example: `event CS2113T tP meeting /at 3/1/2022 21 /to 3/1/2022 2230` will create an event named *CS2113T tP meeting* that runs from 21:00 to 22:30 on 3 Jan 2022
      ```
      Got it. I've added this task:
       3:[E][ ] CS2113T tP meeting (from 21:00, Jan 03 2022 to 22:30, Jan 03 2022)
      Now you have 3 tasks in the list.
      ```
2. View all tasks: `list` 
   <br/>Display all tasks by the order of creation
   <br/>Example:
   ```
   Here are the tasks in your list:
    1:[T][ ] Write code
    2:[D][ ] CS2113T coding practice (by 19:00, Sep 21 2021)
    3:[E][ ] CS2113T tP meeting (from 21:00, Jan 03 2022 to 22:30, Jan 03 2022)
   ```
3. Mark tasks as done: `done` and `undone`
   <br/> Mark status of tasks as *done* or *not done*
   <br/> Format: `done INDEX`
   <br/> Example: `done 1` will mark the task with index *1* as done and `undone 2` will mark the task with index *2* as not done. Now task 1 becomes
   ```
   1:[T][X] Write code
   ```
4. Delete tasks: `delete`
   <br/> Remove task from the task list
   <br/> Format `delete INDEX`
   <br/> Example: `delete 1` will delete the task with index *1*. Now the task list becomes
   ```
   Here are the tasks in your list:
    1:[D][ ] CS2113T coding practice (by 19:00, Sep 21 2021)
    2:[E][ ] CS2113T tP meeting (from 21:00, Jan 03 2022 to 22:30, Jan 03 2022)
   ```
5. Find task: `find`
   <br/> Search through the task list and display all tasks that has description containing the required keyword
   <br/>Format: `find KEYWORD`
   <br/>Example: `find tp` will display all tasks that has *tp* in its description
   ```
   Here are the matching tasks in your list:
    2:[E][ ] CS2113T tP meeting (from 21:00, Jan 03 2022 to 22:30, Jan 03 2022)
   ```
6. Exit Duke: `bye`
   <br/>Exit the application
   <br/>Example:
   ```
   Bye. Hope to see you again soon!
   ```
