# "Duke" User Guide

## Introduction
Duke is a program which can help you to manage the tasks. It will automatically save the tasks you add and reload when you reopen this program.

## Quick Start:
Prerequisites: Latest version of Java 11 on your computer
1. Download Duke at [here](https://github.com/Vincent6896/ip/releases/download/v2.0/individual.project.jar) 
2. Move the .jar file to your desired folder.
3. Open a terminal and move to that folder which containing the .jar file, and execute the following command: java -jar individual.project.jar
   (If you download the jar file from scr/out/artifacts/ip_jar, please execute the following command: java -jar individual\ project.jar)
4. If the program is successfully running, you should see the following:

```
____________________________________________________________
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Hello! I'm Duke
    What can I do for you?
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Enter 'help' for detailed information

    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
____________________________________________________________
```

## Features 
#### 1. Add tasks
   1. Add todo task:
         - Format: ````todo [task]````
         - Add a new todo task
         - Example: todo homework
         - Expected output:

   ```
       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
       Got it. I've added this task:
        [T][ ] homework
       Now you have 1 task(s) in the list.
       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   ```
   2. Add deadline task: 
       - Format: ````deadline [task /by [time]]````
       - Add a new deadline task
       - Example: deadline return book /by Sunday
       - Expected output: 
   ```
       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
       Got it. I've added this task:
        [D][ ] return book (by: Sunday)
       Now you have 2 task(s) in the list.
       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   ```
   3. Add event task: 
       - Format: ````event [task /at [time]]````
        - Add a new event task
       - Example: event project meeting /at Mon 2-4pm
       - Expected output:
   ```
       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
       Got it. I've added this task:
        [E][ ] project meeting (at: Mon 2-4pm)
       Now you have 3 task(s) in the list.
       ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   ```

#### 2. List all tasks
- Print all task in the list
- Format: list
- Expected output:
```
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Here are the tasks in your list:
    1.[T][ ] homework
    2.[D][ ] return book (by: Sunday)
    3.[E][ ] project meeting (at: Mon 2-4pm)
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

#### 3. Mark one task as done and print all finished tasks in list
- Format: `done [index]`
- Example: `done 1`
- Expected output:
````
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Nice! I've marked this task as done:
    [T][X] homework
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Congratulations! Now you have done 1 task(s):
    1.[T][X] homework
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
````

#### 4. Delete one task in list
- Format: `delete [index]`
- Example: `delete 3`
- Expected output:
````
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Noted. I've removed this task:
    [E][ ] project meeting (at: Mon 2-4pm)
    Now you have 2 tasks in the list.
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
````

#### 5. Find tasks with keyword
- Format: `find [keyword]`
- Example: `find book`
- Expected output:
````
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Here are the tasks found in the list:
    1.[D][ ] return book (by: Sunday)
    2.[T][ ] read book
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
````

#### 6. View the help information
- Format: `help`
- Expected output:
````
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Hello! Welcome to Duke.
    I will help you track three types of tasks: Todo, Deadline and Event.
    To add task in list, start with "todo", "deadline" or "event"and followed with the description of your task
    (For deadlines and events, please enter either "by (your deadline)"or "at (your event time)")
    To view all your tasks, enter "list".
    To mark one task as done and print all finished tasks in list, enter "done (task index)". 
    To delete a task in the list, enter "delete (task index)". 
    To find your tasks with keywords, enter "find (keyword)". 
    To exit duke, enter "bye". 
    To view the help information, enter "help". 
    Thank you for choosing Duke :) Please enter command:
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
````

#### 7. Exit the program
- Format: `bye`
- Expected output:
````
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Bye. Hope to see you again soon!
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
````

## Command Summary

 Action |  Format | Function
 ------------ | ------------- | -------------
 todo | todo [task_description] | Add todo task
 event | event [task_description /by [task_time]] | Add event task
 deadline | deadline [task_description /by [task_time]] | Add deadline task
 list | list | List all tasks
 done | done [index] | Mark one task as done and print all remaining finished tasks in list
 delete | delete [index] | Delete one task in list
 find | find [keyword] | Find tasks with keyword
 help | help | View the help information
 bye | bye | Exit the program


