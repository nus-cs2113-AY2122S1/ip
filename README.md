# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
   1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
      ```
   
           ____        _        
          |  _ \ _   _| | _____ 
          | | | | | | | |/ / _ \
          | |_| | |_| |   <  __/
          |____/ \__,_|_|\_\___|
       Why are you here again. What do you want
       _______________________________
4. Type the command and press Enter to execute it. Complete list of commands:
   1. `list`: Lists all tasks.
   2. `todo homework`: Adds a todo with a description `homework`.
   3. `event lecture d/2020-12-31 t/15:00`: Adds an event `lecture` at `31 Dec 2020 3pm`.
   4. `deadline submit assignment d/2020-12-31 t/15:00`: Adds a deadline `submit assignment` at `31 Dec 2020 3pm`.
   5. `done 2`: Marks the 2nd task shown in the current list as completed.
   6. `delete 2`: Deletes the 2nd task shown in the current list.
   7. `clear`: Deletes all tasks.
   8. `bye`: Exits the program.
   9. `help`: Displays user guide
   10. `find books`: Search for `books` within the current task list and displays all tasks with `books` to the user.

###Notes about the command format:
- Words in `UPPER_CASE` are the parameters to be supplied by the user. 
  - e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo homework`.
- Items in square brackets are optional.
  - e.g. `event DESCRIPTION d/DATE [t/TIME]` can be used as `event lecture d/2020-12-31 t/15:00` or as `event lecture d/2020-12-31`.
  - If `TIME` is not given for `event` or `deadline`, the default time is set to `00:00`;
- Parameters must be in the order stated.
- Format of `DATE` is `yyyy-mm-dd`
- Format of `TIME` is `hh:mm`

###Summary of commands
| **Command**            | **Format**                             | **Example**                                       |
|------------------------|----------------------------------------|---------------------------------------------------|
| Add todo               | `todo DESCRIPTION`                     | `todo homework`                                   |
| Add event              | `event DESCRIPTION d/DATE [t/TIME]`    | `event lecture d/2020-12-31 t/15:00`              |
| Add deadline           | `deadline DESCRIPTION d/DATE [t/TIME]` | `deadline submit assignment d/2020-12-31 t/15:00` |
| Clear                  | `clear`                                | `clear`                                           |
| Delete                 | `delete INDEX`                         | `delete 2`                                        |
| Exit                   | `bye`                                  | `bye`                                             |
| Find                   | `find KEYWORD [MORE_KEYWORDS]`         | `find homework`                                   |
| Help                   | `help`                                 | `help`                                            |
| List                   | `list`                                 | `list`                                            |
| Mark task as done | `done INDEX`                           | `done 2`                                          |

###References
- contacts https://github.com/nus-cs2113-AY2122S1/contacts
- personBook https://github.com/nus-cs2113-AY2122S1/personbook
- taskStream https://github.com/nus-cs2113-AY2122S1/TaskStream
- addressBook-level2 https://github.com/se-edu/addressbook-level2