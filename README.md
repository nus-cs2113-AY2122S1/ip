# User guide

Duke is a task management program to help you keep track of things that need to be done. Duke maintains a list of tasks
that is easily manipulated on command line.

##Setting up
Place the .jar file in the desired folder and open command line from the folder. Open command line in the folder 
and launch Duke using the command:

**java -jar "duke.jar"**

##Asking for help
If at any time you forget how to use Duke, just enter the command "**help**" for a quick guide on commands and inputs

##Adding a task
There are three types of tasks: Todo, Deadline and Event.

Todo tasks have only a description while Deadline and Event tasks have a description as well as date and time information.

###### Todo
To add a Todo task, use following command: **todo description**
If you've successfully added a task to the list you should see the following response:
```
========================================================================
I've added the following task:
[T][ ] task 1
========================================================================
```

###### Deadline
To add a deadline task, use the following command: **deadline description /by yyyy-mm-dd xx:xx**

Note: enter date and time information as yyyy-mm-dd xx:xx where the time is in 24-hour time. If the task does not have
a specific time, use a placeholder time.

If you've successfully added a task to the list you should see the following response:
```
========================================================================
I've added the following task:
[D][ ] task 2 (by: Dec 9 2020 12:0 )
========================================================================
```
######Event
To add an Event task, use the following command: **event description /at yyyy-mm-dd xx:xx**

If you've successfully added a task to the list you should see the following response:
```
========================================================================
I've added the following task:
[E][ ] task 3 (at: Dec 12 2021 9:30 )
========================================================================
```

##Deleting a task
To delete a task from the list, use the command: **delete x** 

Replace 'x' with the desired number of the entry that you want to delete.

You should see the following response:
````
========================================================================
I've deleted the following entry
[D][X] task 2 (by: Dec 9 2020 12:0 )
========================================================================
````


##Setting as done
Duke allows you to mark tasks as completed, or done.

To set a task as done, use the command: **done x**

Replace 'x' with the desired number of the entry that you want to delete.

You should see the following response:
````
========================================================================
task 1 done. Well done.
========================================================================
````

##Viewing your tasks
Duke offers a few ways you can view the tasks in your list.

######List
This will display your entire list, ordered in the order in which you entered them.

Use the command: **list**

######Search
Duke allows you to search your list and show you the tasks that match a keyword.

Use the command: **search keyword**

Note that the keyword need not be one word. However, it will be treated as a single search term. For example, 
"search do project" will search the list for tasks with have a description matching "do project".

######Date
This will display all the deadline and events that occur on the specified date.

Use the command: **date yyyy-mm-dd**

This command ignores the time at which the tasks occur.

##Closing the program
Once you're done with Duke for the time being, use the command **bye** to close the program.

Duke saves your data everytime you make changes to the list, as well as when it closes. So don't worry about your 
data being lost!