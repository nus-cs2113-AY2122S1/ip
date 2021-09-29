# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.
- [**User Guide**](https://yuxinn-j.github.io/ip/)

## Duke Overview

Project Duke is a educational software project designed to take you through the steps of building a small software incrementally, while applying as many Java and SE techniques as possible along the way.

**The project aims to build a product named Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.** The name Duke was chosen as a placeholder name, in honor of [Duke, the Java Mascot](https://www.oracle.com/java/duke.html). You may give it any other name and personality you wish.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/duke.data.Duke.java` file, right-click it, and choose `Run duke.data.Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

## Duke Learning objectives

- Java
- OOP
- IDEs
- Git and GitHub
- Project automation tools
- Code quality

##  Duke Sample interaction
 
 ```bash
   ____________________________________________________________
      ____        _        
     |  _ \ _   _| | _____ 
     | | | | | | | |/ / _ \
     | |_| | |_| |   <  __/
     |____/ \__,_|_|\_\___|

     Hello! I'm Duke
     What can I do for you?
    ____________________________________________________________

list
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][X] read book
     2.[D][ ] return book (by: June 6th)
     3.[E][ ] project meeting (at: Aug 6th 2-4pm)
     4.[T][X] join sports club
    ____________________________________________________________

todo borrow book
    ____________________________________________________________
     Got it. I've added this task: 
       [T][ ] borrow book
     Now you have 5 tasks in the list.
    ____________________________________________________________


deadline return book /by Sunday
    ____________________________________________________________
     Got it. I've added this task: 
       [D][ ] return book (by: Sunday)
     Now you have 6 tasks in the list.
    ____________________________________________________________

done 2
    ____________________________________________________________
     Nice! I've marked this task as done: 
       [D][X] return book (by: June 6th)
    ____________________________________________________________

blah
    ____________________________________________________________
     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(
    ____________________________________________________________

bye
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________

 ```
