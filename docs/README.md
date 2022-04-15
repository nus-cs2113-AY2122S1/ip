# Quick Start
1. Ensure that you have Java 11 or above installed in your machine.
2. Download the latest Duke.jar from [here]().
3. Copy the jar file to a directory you wish to use as the root folder for the app.
4. Open your terminal in the directory and run the jar file using the following command:
```
java -jar Duke.jar
```

# Features 

## Add Tasks
Add 3 types of tasks for Duke to keep track of.

The 3 types of tasks are:
### 1. `todo` - Add a new todo
Command format:
```
todo <description>
```
Example:
```
todo read book
```
Expected Outcome:
```
Got it. I've added this task: 
Now you have 3 task(s) in the list.
```
   
### 2. `deadline` - Add a new deadline task
Command format:
```
deadline <description> /by <datetime>
```
Example:
```
deadline finish reading book /by Wednesday 2pm
```
Expected Outcome:
```
Got it. I've added this task: 
Now you have 4 task(s) in the list.
```

### 3. `event` - Add a new event task
Command format:
```
event <description> /at <datetime>
```
Example:
```
event project meeting /at 22-Sep 2pm
```
Expected Outcome:
```
Got it. I've added this task: 
Now you have 5 task(s) in the list.
```

## List tasks
View all your tasks and their statuses you have entered into Duke.

Example:
```
list
```
Expected Outcome:
```
Here are the tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: June 6th)
3.[E][ ] project meeting (at: Aug 6th 2-4pm)
4.[T][X] join sports club
5.[T][ ] borrow book
```

## Delete tasks
Delete tasks that you have already entered into Duke.

Command Format:
```
delete <task no.>
```
Example:
```
delete 5
```
Expected Outcome:
```
Noted. I've removed this task: 
[X] borrow book
Now you have 4 task(s) in the list.
```

## Find tasks
Search and find any tasks by typing a keyword

Command Format:
```
find <keyword>
```
Example:
```
find book
```
Expected Outcome:
```
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: June 6th)
3.[T][ ] borrow book
```

## Mark Tasks
Tick off your tasks in Duke

Command Format:
```
done <task no.>
```
Example:
```
done 1
```
Expected Outcome:
```
Nice! I've marked this task as done:
[X] read book
```

## Save tasks locally
Save all the tasks you have entered into Duke locally 
in your local storage for you to see them again next 
time you run the application once more.

## Command Summary

Action | Command
-------|-----------------
**Add Todo** | `todo <description>` <br> e.g. `todo read book`
**Add Deadline** | `deadline <description> /by <datetime>` <br> e.g. `deadline finish reading book /by Wednesday 2pm`
**Add Event** | `event <description> /at <datetime>` <br> e.g. `event project meeting /at 22-Sep 2pm`
**List** | `list`
**Delete** | `delete <task no.>` <br> e.g. `delete 5`
**Find** | `find <keyword>` <br> e.g. `find book`
**Mark Done** | `done <task no.>` <br> e.g. `done 1`
**Exit** | `bye`
