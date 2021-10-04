# Duke Individual Project

Duke is to-do list manager that handle different types of tasks and perform various functions.
Saving and loading of data has also been implemented.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
   
## Command Format
* Words in `UPPER_CASE` are parameters to be supplied by the user.
   > e.g. in `todo TASK` TASK is the description of the task supplied by the user.
* Extraneous parameters for commands that do not take in parameters is not acceptable.
   > e.g. if the command specifies `list 123`, there will be an error.

## Features

1. ToDos: Add task without any date attached
   ```
   todo TASK
   ```
2. Deadlines: Add tasks that need to be done before a specific date/time
   ```
   deadline TASK /by DD/MM/YYYY HHMM
   ```
3. Events: Add tasks that start at a specific time
   ```
   event TASK /at DD/MM/YYYY HHMM
   ```
4. Display list
   ```
   list
   ```
5. Mark task complete
   ```
   done TASKNUMBER 
   ```
6. Delete task
   ```
   delete TASKNUMBER
   ```
7. Find tasks that has date before time specified
   ```
   before DD/MM/YYYY HHMM
   ```
8. Find tasks that has date after time specified
   ```
   after DD/MM/YYYY HHMM
   ```
9. Find tasks with substring
   ```
    find WORD
    ```
10. Exit Duke
    ```
     bye
    ```

## Contact

Zeng Jiexiong - jiexiong123@gmail.com

Project Link: [https://github.com/jiexiong-zeng/ip](https://github.com/jiexiong-zeng/ip)