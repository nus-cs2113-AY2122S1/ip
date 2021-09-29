# Duke

* Duke helps you record your tasks and manage them.  
* It is a CLI program, where you type in commands to the program.  
* If you forget or are unsure about Duke's functions, enter **help** to learn how to use them.  

## Functionalities

Here are a few things Duke can do

1. Allows you to add different kinds of tasks such as ToDos, Deadlines and Events
   * ToDo
      * ToDos are tasks that can be done at any time
      * Format : todo *description*
      * Example : todo clean the house  

   * Deadline
      * Deadlines are tasks that needs to be done before a specific time
      * Format: deadline *description* /by *yyyy-MM-ddTHH:mm:ss*
      * Example: deadline Homework /by 2021-10-01T23:59:59
      * yyyy represents the year, MM the month, dd the day, HH the hour, mm the minute and ss the second.  

   * Event
      * Events are tasks that occurs at a specific time period
      * Format: event *description* /at *yyyy-MM-ddTHH:mm:ss* /to *yyyy-MM-ddTHH:mm:ss*
      * Example: event celebrate Mary's birthday /at 2021-09-30T22:00:00 /to 2021-09-30T23:00:00
      * Add the event start time before **/to** and the event end time after.  <br>
</br>
2. Shows you the list of tasks you saved onto Duke and allows you to search for specific tasks

   * List
      * Shows you the list of all the tasks you have
      * Format: list  

   * Find
      * Find a task with a specific description or date
      * Format: find *description* **or** find /d *date*
      * Example: find Homework, find /d 2021-09-30
      * **/d** tells Duke that you are searching for a task with a specific date.  <br>
</br>
3. Allows you to mark a task as done  

   * Done
     * Marks the task of a specific number on the list as done
     * Format: done *integer*
     * Example: done 1 (This marks the first task on the list as done.)  <br>
</br>
4. Allows you to remove a task from the list  

   * Delete
     * Deletes the task of a specific number on the list
     * Format: delete *integer*
     * Example: delete 1 (This deletes the first task on the list.)  <br>
</br>
5. Loads the tasks from the "duke.txt" file when you start the program, if it exists. Creates the file to save your tasks if it doesn't. <br>
</br>
6. Automatically saves your tasks to the file "duke.txt" when you terminate the program.  <br>
</br>
7. To terminate the program, type **bye**.  <br>
</br>

   ```
   Hello from
    ____        _          
   |  _ \ _   _| | _____   
   | | | | | | | |/ / _ \  
   | |_| | |_| |   <  __/  
   |____/ \__,_|_|\_\___|  
   ```