# "Task Manager" User Guide

This is a programme that can help you manage your tasks.
'The task list you stored before will be re-loaded everytime you use this programme'

The task form accepted have **3 types**
1. Todo : basic Task with no time element
2. Event : Task that is arranged at certain time
3. Deadline : Task that has a deadline

**Commands you can give to this programme:**
1. Add tasks:
    1. todo <content>: add a todo task
    2. event <content> /at <time>: add an event task
    3. deadline <content> /by <time>: add a deadline task
2. Mark the task as done:
   done <index>: mark the task at index \'index\' as done
3. Delete a task:delete <index>: delete the task at index \'index\'
4. Show the list: list
5. Close the program: bye
6. Show the information of all possible commands: help


## More Details

Welcome information
   ```
   Hello from
 ____              ________  
|  _ \  | |   | |  |__  __| 
| |_| | | |   | |    |  |
|____/  | |   | |    |  |
| |\ \  | |   | |    |  |
| | \ \ | |___| |   --  --
|_|  \ \\ _____ /  | ____|

   ```
- After seeing the welcome information, you can type in the command you want based on the "help information"

- Regarding the tasks type "deadline" & "event", the programme accept dates in a format such as yyyy-mm-dd.
- Errors are handled by reflecting error information and exit the programme.
  Possible errors handled in this programme:
    - Empty Task Error:The description of task is empty
    - Invalid Date Error: The inputted time format for "Deadline" & "event" task is wrong
    - Invalid Input Error: Reveal errors in commands that don't follow the format; Invalid command
    - Missing Para Error: The information given is not enough for task type required
    - Out Of Range Error: User ask to delete/mark as done a task that beyond the number of tasks stored
- List is shown in the format:

where "T" - Todo task "D" - Deadline task "E" - Event task
"X" - task is done
```
  1.[T][X] read book
  2.[D][ ] return book (by: June 6th)
  3.[E][ ] project meeting (at: Aug 6th 2-4pm)
  4.[T][X] join sports club
  5.[T][ ] borrow book



