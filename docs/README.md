# User Guide <br/><br/>
## Overview <br/><br/>

### Duke is a Personal Assistant ChatBot that helps users keep track of various tasks. It works like a Notebook/Memorandum/Planner in which you can add/remove/update tasks you need to keep track of. With Duke, you can save your daily plan or things to do efficiently and update the task status once you have done the task.
### Duke can parse users' input command (add/remove/update/modify/display) and update correspondingly inside the program. Duke will respond to users once it parses users' input command. Duke keeps track of your TaskList to enables users to update and modify the tasks. <br/><br/>


## Features <br/><br/>

### 1. list
### 2. todo
### 3. deadline
### 4. event
### 5. done
### 6. delete
### 7. save
### 8. load
### 9. terminate <br/><br/>

# Features description <br/><br/>

### 1. list <br/>
### Usage: Print the list of tasks stored in Duke <br/>
### Example of usage:
### User Input: 'list'
### Expected Output:  
### Here are the tasks in your list:
### 1.[T][ ] read a book
### 2.[T][ ] borrow a book

### Description: The output contains all tasks in Duke. The first number denotes the index of the task. The first square bracket denotes the type of tasks: T for todo, D for deadline, E for event. The second square bracket denotes the task status: X for done and WHITESPACE for undone. The task name is shown after the two square bracket.<br/><br/>

### 2. todo <br/>
### Usage: Add a task with 'todo' type in Duke. Tasks with 'todo' type do not have a specific deadline or time to occur. <br/>

### Example of usage:
### User Input: 'todo read a book'
### Expected Output:
### Got it. I've added this task:
###     [T][ ] read a book
### Now you have 1 tasks in the list. <br/>

### Description: Duke will display the added task with 'todo' type if added successfully. Duke will then print the number of tasks in the list. Ensure there is a whitespace between 'todo' and the task name. Duke will remind the user if the command is invalid, i.e., Commands fail to follow the template. <br/><br/>


### 3. deadline <br/>
### Usage: Add a task with 'deadline' type in Duke. Tasks with 'deadline' type have a specific deadline. <br/>

### Example of usage:
### User Input: 'deadline return the book /by Sunday 11am'
### Expected Output:
### Got it. I've added this task:
###     [D][ ] return the book (by: Sunday 11am)
### Now you have 3 tasks in the list. <br/>

### Description: Duke will display the added task with 'deadline' type if added successfully. Duke will then print the number of tasks in the list. To add a task with 'deadline' type, users must input command with '/by' string inside. The '/by' string is typed in before the actual deadline, i.e., /by Sunday 11am. Input without '/by' string will be considered as invalid input for a 'deadline' command. Duke will remind the user if the command is invalid, i.e., Commands fail to follow the template. <br/><br/>


### 4. event <br/>
### Usage: Add a task with 'event' type in Duke. Tasks with 'event' type have a specific time to occur. <br/>
### Example of usage:
### User Input: 'event I should attend the lecture /at Friday 17 Sep 2021 from 4-6pm'
### Expected Output:
### Got it. I've added this task:
###     [E][ ] I should attend the lecture (at: Friday 17 Sep 2021 from 4-6pm)
### Now you have 4 tasks in the list. <br/>

### Description: Duke will display the added task with 'event' type if added successfully. Duke will then print the number of tasks in the list. To add a task with 'event' type, users must input command with '/at' string inside or at least containing a '/'. The '/at' string is typed in before the actual time, i.e., /at Friday 17 Sep 2021 from 4-6pm. Duke will remind the user if the command is invalid, i.e., Commands fail to follow the template. <br/><br/>


### 5. done <br/>
### Usage: Mark a task as done. <br/>
### Example of usage:
### User Input: 'done 4'
### Expected Output:
### Nice! I've marked this task as done:
###     [E][X] I should attend the lecture (at: Friday 17 Sep 2021 from 4-6pm) <br/>

### Description: Duke will print the task once it is marked as done. Once the task is marked as done, the second square bracket will have a 'X' symbol inside. The 'X' denotes the done status, and the ' ' denotes the undone status. The 'done' command should contain an integer denoting the index of the task. You can find the index of a task by inputting the 'list' command.
### Command without valid index will be considered invalid by Duke, i.e., index out of bound, or without an integer index. Duke will remind the user if the command is invalid, i.e., Commands fail to follow the template. <br/><br/>


### 6. delete  <br/>
### Usage: Delete a task from Duke, i.e. delete from the task list <br/>
### Example of usage:
### User Input: 'delete 3'
### Expected Output:
### Noted. I've removed this task:
###     [D][ ] return the book (by: Sunday 11am)
### Now you have 3 tasks in the list. <br/>

### Description: Duke will print the task that is removed by the 'delete' command. Duke will display the current number of tasks in the task list after you delete the task. The 'delete' command should contain an integer denoting the index of the task. You can find the index of a task by inputting the 'list' command. 
### Command without valid index will be considered invalid by Duke, i.e., index out of bound, or without an integer index. Duke will remind the user if the command is invalid, i.e., Commands fail to follow the template.  <br/><br/>


### 7. save <br/>
### Usage: Save tasks so that tasks are stored in Duke, i.e., in the text file
### Example of usage:
### Description: No command needed for saving tasks to file. Once users input a valid command, the task list will update correspondingly. All updates can be saved to file, i.e., add/delete/modify/mark as done. Duke will remind the user if 'save' fails. <br/><br/>


### 8. load  <br/><br/>
### Usage: Load tasks from hard disk when Duke starts up, i.e., load from the last updated file
### Example of usage:
### Description: No command needed for loading tasks from hard disk. Once Duke starts up, it will read the text file you saved before, and load the tasks in the file to your current Duke program. <br/><br/>


### 9. terminate <br/>
### Usage: Terminate the program
### Example of usage:
### User Input: 'bye'
### Expected Output:
### Bye. Hope to see you again soon! <br/>

### Description: To terminate Duke, users must input 'bye' to terminate the running program. All the tasks and updates will be saved before users terminate the program. When users restart Duke, it will automatically load the stored tasks and updates, i.e, as explained in 'load' <br/><br/>