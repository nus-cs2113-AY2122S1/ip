# Hello, it's Bob!

I am your personal task tracker. Use me to keep track of the tasks you need to complete!

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Bob.java` file, right-click it, and choose `Run Bob.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   ****************************
   *  ____             ____   *
   * |  _ \    ____   |  _ \  *
   * | |_| |  / __ \  | |_| | *
   * |  _  | | |  | | |  _  | *
   * | |_| | | |__| | | |_| | *
   * |____/   \____/  |____/  *
   ****************************
   _________________________________________
   Have no fear, Bob is here!
   What is it that you require?
   For a list of commands, type: help
   _________________________________________
   ```
## Supported Features
*Note: Exclude the carrot braces when entering commands*
### Add Tasks to be Tracked
There are 3 types of tasks that can be added.
1. Todo Tasks
   * Stores a todo task with description
   * Format: `todo <description>`
2. Deadline Tasks
   * Stores a deadline task with description and deadline
   * Format: `todo <description> /by <yyyy-MM-dd>T<hh:mm:ss>`
3. Event Tasks
   * Stores an event task with description and start time
   * Format: `event <description> /at <yyyy-MM-dd>T<hh:mm:ss>`

Examples:
   * `todo homework`
   * `deadline math homework /by 2021-11-11T19:00:00`
   * `event do math homework /at 2021-11-10T10:00:00`
### Viewing Tracked Tasks
You can view the tasks currently tracked
1. List all tasks
   * Format: `list`
2. List tasks containing a keyword
   * Format: `find <keyword>`
   * Example: `find homework`

### Edit the list
You can make certain edits to your tasks
1. Mark as Done
   * Marks a specified task as complete
   * Format: `done <index>`
2. Delete Tasks
   * Deletes a task from the list
   * Format: `delete <index>`

### Miscellaneous 
1. Exit
   * Exits the program
   * Format: `exit`
2. Help
   * Displays the command help
   * Format: `help`

## Enjoy Playing with Bob!