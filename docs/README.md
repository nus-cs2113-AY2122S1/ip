# **Duke User Guide**
Hello everyone! Thank you for using Duke which is a simply based Command Line Interface (CLI) to help you manage your tasks!
## **Contents**
 * **Quick Start**
 * **Features**
 * **Command**
    1. Add
    2. Delete
    3. Done
    4. List
    5. Find
    6. Exit
 * **Command Summary**

## **Quick Start**
 1. Firstly, make sure you have installed Java 11 on your computer.
 2. Secondly, download the latest release of Duke.jar [here](https://github.com/Cesare4869/ip/releases).
 3. Thirdly, place the file to a location you like.
 4. Fourthly, go to the directory where you place the file using Command Line Interface
 5. Lastly, enter the command `java -jar Duke.jar` to start!

## **Features** 
### **Tasks Classification**
 1. Todo - Tasks with only a description.
 2. Deadline - Tasks with a description and a date/time by.
 3. Event - Tasks with a description and a date/time at.

## **Command**

### **Add**

Add a task in your list.
* Add a Todo. `todo <description>`
* Add a Deadline `deadline <description> /by <date/time>`
* Add an event `event <description> /at <date/time>`

Example of usage:`todo reading`,`deadline CS2113T /by Friday`, `event meeting /at 12pm`.

### **Delete**

Delete a task from your list.
* Delete a task. `Delete <task no.>`

Example of usage: `Delete 2`.

### **Done**

Mark a task as done.
* Done a task. `done <task no.>`

Example of usage `done 3`.

### **List**

List all the tasks in your list.
* List tasks. `list`

Example of usage `list`.

### **Find**

Find the tasks containing your description.
* Find tasks. `find <description>`

Example of usage `find reading`.

### **Exit**

Exit the Duke programme and save your tasks.
* Exit programme. `bye`

Example of usage `bye`.

## **Command Summary**
Command | Usage
------------ | -------------
Add a todo | `todo <description>`
Add a deadline|`deadline <description> /by <date/time>`
Add an event | `event <description> /by <date/time>`
Delete | `delete <task no.>`
Done | `done <task no.>`
List | `list`
Find | `find <description>`
Exit | `bye`

