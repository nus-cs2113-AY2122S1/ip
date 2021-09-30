# Duke User Guide

Duke is a personal assistant chat-bot that can help its user to record different type of tasks.  

## Contents
1. To Start
2. Features
3. Command List




## To Start
1. Check your Java version. This app requires Java version 11 to be installed on your PC.
2. After installing Java version 11, download the Duke.jar file from [here]
3. After downloading, please open the command prompt on your PC.
4. Once you have opened the command prompt, run Duke.jar by typing in "java -jar Duke.jar".
   (Note:If you failed to run Duke.jar, please check you are at the correct direction which Duke.jar is saved)
5. If you have run Duke successfully, you should see the logo of Duke like this: [logo](images/logo.png)





 
## Features
1. Duke supports adding three types of task: todo, deadline and event.
2. User can delete any task they want in the task list.
3. User can mark any task in the task list as done.
4. If user's input is not valid, Duke will display error message to remind user.


## Command List
 
### 1. Add a todo task to task list
* Syntax: todo <task description/command>
* e.g. todo buy Java book
* Sample output: [todo](images/todo.png)

### 2. Add a deadline task to task list
* Syntax: deadline <task description/command> /by YYYY-MM-DD
* e.g. deadline return car /by 2021-11-20
* Sample output: [deadline](images/deadline.png)

### 3. Add an event task to task list
* Syntax: event <task description/command> /at YYYY-MM-DD
* e.g. event meet friend /at 2021-12-31
* Sample output: [event](images/event.png)

### 4. List all the tasks in the task list
* Syntax: list
* e.g. list
* Sample output:[list](images/list.png)

### 5. Mark task as done
* Syntax: done <task index>
* e.g. done 2
* Sample output: [mark_as_done](images/mark_as_done.png)

### 6. Delete task in task list
* Syntax: delete <index of task that you want to delete>
* e.g. delete 3
* Sample output: [delete](images/delete.png)

### 7. Find task with keyword in the task list
* Syntax: find <keyword you want to search>
* e.g. find car
* Sample output: [find](images/find.png)

### 8. Quit Duke app
* Syntax: bye
* e.g. bye
* Sample output: [bye](images/bye.png)






