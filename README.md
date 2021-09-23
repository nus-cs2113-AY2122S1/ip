# User Guide for Sierra

Sierra is a task manager which allows you keep track of 3 different kinds of tasks, namely, todo, deadline and events.
   ```
   Hello from
      ____                U _____ u   ____      ____        _
     / __"| u      ___    \| ___"|/U |  _"\ uU |  _"\ u U  /"\  u
    <\___ \/      |_"_|    |  _|"   \| |_) |/ \| |_) |/  \/ _ \/
     u___) |       | |     | |___    |  _ <    |  _ <    / ___ \
     |____/>>    U/| |\u   |_____|   |_| \_\   |_| \_\  /_/   \_\
      )(  (__).-,_|___|_,-.<<   >>   //   \\_  //   \\_  \\    >>
     (__)      \_)-' '-(_/(__) (__) (__)  (__)(__)  (__)(__)  (__)
   ```

## Quick Start

1. Ensure you have JDK 11 or above installed in computer.
2. Download the latest version of Sierra from [here](https://github.com/joshualeeky/ip/releases)
3. Run the Jar file through your preferred command line interface the Sierra should start up.

## Main Menu
There are 2 functions that can be accessed through the main menu, they are `echo` and `tasks`.
Key in either words and press enter to activate the function.

### Echo
In this function, keying in any word will make Sierra reply you with whatever was sent.
1. Entering `change` will return you back to the main menu.
2. Entering `bye` will exit the application.

### Tasks
In this function, Sierra will be able to store and manage your tasks.
1. Entering `todo` followed by a task will make Sierra store the task as a 'to do' task.
2. Entering `event` followed by a task and then `/at` with a date and time in the format `ddmmyyyy time(in 24hr)` will make Sierra store the task as an 'event' task
3. Entering `deadline` followed by a task and then `/by` with a date and time in the format `ddmmyyyy time(in 24hr)` will make Sierra store the task as a 'deadline' task
4. Entering `list` will make Sierra list down all the tasks you have recorded in her database.
5. Entering `find` will make Sierra list down all the tasks that have that particular keyword in (if any) with their corresponding identification number.
6. Entering `delete` followed by a task's identification number will make Sierra delete the task from her database.
7. Entering `done` followed by a task's identification number will make Sierra mark that task as complete in her database.
8. Entering `change` will return you back to the main menu.
9. Entering `bye` will exit the application.
