# User Guide
FRIDAY is a command-line application run on Java-11 for easy task management. 
It is modelled after Tony Stark aka Iron Man's own AI helper.

* [Set-Up](#Set-Up)
* [Program Start](#Program-Start)
* [Features](#Features)
    * [Adding Tasks](#Adding-Tasks)
        * [Add Todo Tasks](#Add-Todo-Tasks)
        * [Add Deadline Tasks](#Add-Deadline-Tasks)
        * [Add Event Tasks](#Add-Event-Tasks)
    * [List](#List)
    * [Finding Tasks](#Finding-tasks)
    * [Marking Tasks as Done](#Marking-Tasks-as-Done)
    * [Deleting Tasks](#Deleting-Tasks)
* [Program Exit](#Program-Exit)
* [Command Summary](#Command-Summary)
* [FAQs](#FAQs)
* [References](#References)

## Set Up
1. Ensure you have Java 11 installed in your computer.
2. If Java 11 is not installed, click [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) 
to download it.
3. After downloading, add path to bin folder to PATH variable in Environment Variables
4. Check if Java is downloaded by running `java --version` in your Terminal.
5. Download the latest jar file from [here](https://github.com/STAung07/ip/releases/tag/A-Release).
6. Save jar file in folder of choice.

## Program Start
Open terminal in folder containing `Friday.jar` file.\
Type in command `java -jar Friday.jar`.\
The screen below should appear after a short while.\
This indicates that previous data from storage has been loaded successfully into your current task list,
and you can proceed with using the program.
```
                           .*%@@@@@@@@@@@@@@@@@@@@@@&(.                         
                      /@@&*  #@@@@@@@@@@@@@@@@@@@@@@@  .%@@%.                   
                  &@@*        @@@@@@@@@@@@@@@@@@@@@@,       .@@@.               
              *@@#            ,@@@@@@@@@@@@@@@@@@@@#            *@@(            
             &%                &@@@@@@@@@@@@@@@@@@@                /@.          
           .%                   @@@@@@@@@@@@@@@@@@,                  ,%         
           %@                   ,@@@@@@@@@@@@@@@@%                   *@         
           @@                    #@@@@@@@@@@@@@@@                    /@/        
          *@@                                                        (@&        
          &@@.                                                       %@@.       
          @@@.                                                       &@@*       
         .@@@,                                                       &@@/       
         ,@@@*                                                       @@@%       
         *@@@*                                                       @@@@       
         (@@@.                                                       &@@@       
         @@@&  /@&%/,.                                      .,*#%&@  ,@@@/      
         @@@. .@@@@@@@@@@@@@@@@@@%###############@@@@@@@@@@@@@@@@@@(  @@@(      
         @@%     ,&@@@@@@@@&%/,.                   .*#&&@@@@@@@@(.    .@@#      
         @@.                                                           %@(      
         @@                                                            *@/      
         &@.                                                           #@,      
         &@@,                                                         @@@.      
         %@@@..                                                      #@@@       
         *@@@@ &/                                                .@,/@@@&       
          &@@@@.#@/                                             @@.#@@@@.       
          ,@@@@@*/@@.                                         %@& @@@@@&        
          .@@@@@@#.@@*                                       @@#,@@@@@@#        
           @@@@@@@@ &@,                                     &@.(@@@@@@@,        
           /@@@@@@@@,/#                                    .& @@@@@@@@@         
            @@@@@@@@@#..     &@@&%#######((((####%@@@.     ,,@@@@@@@@@,         
            *@@@@@@@@@. *@%/@*                      .@%*@%  %@@@@@@@@&          
             &@@@@@@@&                                      ,@@@@@@@@,          
             *@@@@@@@                 ,&&&&(                 #@@@@@@&           
              /@@@@@@#        .&@@@@@@@@@@@@@@@@@@@*        *@@@@@@&            
                 (@@@@@@@, .@@@@@@@@@@@@@@@@@@@@@@@@@@/  #@@@@@@&.              
                    .&@@@@@*@@@@@@@@@@@@@@@@@@@@@@@@@@%(@@@@@*                  
                        .#@@,@#                    *@##@&,                      

  __      _     _             
 / _|    (_)   | |            
| |_ _ __ _  __| | __ _ _   _ 
|  _| '__| |/ _` |/ _` | | | |
| | | |  | | (_| | (_| | |_| |
|_| |_|  |_|\__,_|\__,_|\__, |
                         __/ |
                        |___/ 
Initiating FRIDAY
Allow me to fetch the data from the archives ...
Data successfully loaded into memory sir.
Hello Mr Stark, how may I be of assistance to you today?
```

## Features

### Adding Tasks
Users are able to add 3 different types of tasks, todo, deadline or event tasks. 

#### Add Todo Tasks 
Todo tasks without a deadline or specific date. 

Usage: `todo <TASK_NAME>`
* **todo** keyword is case - insensitive
* TASK_NAME should not be empty.

Example
```
todo design Mach-47
```

Output
```
Very well, adding task "design Mach-47"
```

#### Add Deadline Tasks 
Deadline tasks where user has to finish task by a certain date e.g. finish assignment by 30th September.

Usage: `deadline <TASK_NAME> /by <DATE>`
* **deadline** keyword is case - insensitive
* TASK_NAME should not be empty
* **/by** keyword needs to be present before DATE
* DATE needs to be provided in format _YYYY-MM-DD_

Example
```
deadline buy Valentines Day gift for Pepper /by 2022-02-14
```

Output
```
Very well, adding task "buy Valentines Day gift for Pepper".
```

#### Add Event Tasks 
Event tasks where an event occurs on a certain date e.g. 2027 midterms at 2nd October.

Usage: `event <TASK_NAME> /at <DATE>`
* **event** keyword is case - insensitive
* <TASK_NAME> should not be empty
* **/at** keyword needs to be present before DATE
* DATE needs to be provided in format _YYYY-MM-DD_

Example
```
event fight Thanos /at 2021-11-01
```

Output
```
Very well, adding task "fight Thanos".
```

### List
Lists all current tasks in task list of user.

Usage: `list`
* Command is case - insensitive

Output
```
You have a total of 3 tasks sir, listing them out now.
>[T][ ]design Mach-47
>[D][ ]buy Valentines Day gift for Pepper(by: 14 Feb 2022)
>[E][ ]fight Thanos(at: 01 Nov 2021)
```

### Finding Tasks
Users can find all tasks containing their query e.g. books, read books etc.

Usage: `find <QUERY>`
* Command is case - insensitive
* Search for QUERY within tasks is case - insensitive

Example
```
find thanos
```

Output
```
Here are all your current tasks matching your query sir.
>[E][ ]fight Thanos(at: 01 Nov 2021)
```

### Marking Tasks as Done
Users can mark any task they previously added into the list as "Done" using the position of task in the list.

Usage: `done <POSITION_OF_TASK>`
* **done** keyword is case - insensitive
* POSITION_OF_TASK should be an integer starting from 1
* There should be a task at the position of integer specified.

Example
```
done 1
```

Output
```
Your task "design Mach-47" is marked as complete. Splendid sir.
[X]design Mach-47
```

Running `list` command after this will indicate an 'X' beside task marked as done.
```
You have a total of 3 tasks sir, listing them out now.
>[T][X]design Mach-47
>[D][ ]buy Valentines Day gift for Pepper(by: 14 Feb 2022)
>[E][ ]fight Thanos(at: 01 Nov 2021)
```

### Deleting Tasks
Users can delete any task from the list of tasks using the position of task in the list as well.

Usage: `delete <POSITION_OF_TASK`
* **delete** keyword is case - insensitive
* POSITION_OF_TASK should be an integer starting from 1
* There should be a task at the position of integer specified.

Example
```
delete 1
```

Output
```
Alright sir, removing task "design Mach-47".
You now have 2 tasks remaining.
```

Running `list` command after this will show the new list without the deleted task.
```
You have a total of 3 tasks sir, listing them out now.
>[D][ ]buy Valentines Day gift for Pepper(by: 14 Feb 2022)
>[E][ ]fight Thanos(at: 01 Nov 2021)
```

## Program Exit
Users can type `bye` command and any point to exit the program. Command is case - insensitive as well.

Output
```
Powering off now. Good Bye Mr Stark.
```

## Command Summary

Action | Command Format | Example
------------ | ------------ | ------------
Add Todo task | `todo <TASK_NAME>` | `todo design Mach-47`
Add Deadline task | `deadline <TASK_NAME> /by <DATE>` | `deadline buy Valentines Day gift for Pepper /by 2022-02-14`
Add Event task | `event <TASK_NAME> /at <DATE>` | `event fight Thanos /at 2021-11-01`
List tasks | `list` | `list`
Find tasks | `find <QUERY>` | `find thanos` 
Mark task as done | `done <POSITION_OF_TASK>` | `done 2`
Delete task | `delete <POSITION_OF_TASK>` | `delete 2`
Exit Program | `bye` | `bye`


## FAQs
_**Q:**_ What happens to my tasks stored if my computer was to crash halfway?
_**A:**_ Data is stored and updated live in the _friday.txt_ file inside _/data_ directory. 
This means that once your command has been executed it will auto update the stored data. Thus,
you don't have to worry about losing data :) (unless you delete the whole txt file...)

_**Q:**_ Can I upload my own _.txt_ file to populate the tasks array beforehand?
_**A:**_ Yes you can! In fact that is a much faster and easier way to transfer data. However, do take note that 
the information in your _.txt_ file has to be in the same format as that in _friday.txt_. Otherwise, there will 
be an error in loading the data.

## References
* Converting Text for Friday\
  https://ascii.co.uk/
* Converting image for Iron Man Mask\
  https://manytools.org/hacker-tools/convert-images-to-ascii-art/

