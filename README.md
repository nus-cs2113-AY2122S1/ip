# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

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

## References and Acknowledgement
- Converting text for Alfred: <br />
  https://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20
- Inspiration for OOP structure: AddressBook (Level 2) <br />
  https://github.com/se-edu/addressbook-level2
- Inspiration for User Guide crafting: AddressBook (Level 3) <br />
  https://se-education.org/addressbook-level3/UserGuide.html
- GitHub Markdown Emoji Syntax for User Guide: <br />
  https://github.com/ikatyang/emoji-cheat-sheet/blob/master/README.md
