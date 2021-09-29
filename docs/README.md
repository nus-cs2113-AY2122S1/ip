# User Guide
This is a user guide for Duke Project, a Command Line Interface application used to organise your tasks, deadlines and events.

## Features 

### Add a Task
Adding a task: ToDo, Deadline, Event

Format: `todo`, `deadline /by`, `event /at`

Example: `todo read book`, `deadline Assignment /by Friday 1159`, `event Lecture /at 5pm`

Expected Output:

![image](https://user-images.githubusercontent.com/71833868/135294525-a233c7ea-5e63-4d39-9e06-4fbc6332a266.png)

![image](https://user-images.githubusercontent.com/71833868/135296652-b0561b35-22d1-416e-bc26-476b2adf7bd4.png)

![image](https://user-images.githubusercontent.com/71833868/135296831-19946418-b5a6-4a03-8814-e91e9d3e98a1.png)

### Listing your tasks
Lists all tasks you have added.

Format: `list`

Example: `list`

Expected Output:

![image](https://user-images.githubusercontent.com/71833868/135294880-325d4050-f35f-4222-97d8-ab7483b5f3af.png)

### Delete a Task
Deletes a task from your list.

Format: `delete <n>` where n is the task list number.

Example: `delete 1` (this will delete the first task on the list)

Expected Output:

![image](https://user-images.githubusercontent.com/71833868/135295272-f3461f98-7e8a-4c96-bebe-9d7e1a1fd4b4.png)

### Mark a Task As Done
Marks a task in the list as done [X].

Format: `done <n>` where n is the task list number.

Example: `done 1` (this will mark the first task on the list as done)

Expected Output:

![image](https://user-images.githubusercontent.com/71833868/135295790-0d5bda1c-6c77-4fdc-89c8-38a03c1f6933.png)

### Find for tasks
Finds a list of tasks by keywords.

Format: `find <keyword>`

Example: `find book`

Expected Output:

![image](https://user-images.githubusercontent.com/71833868/135296164-8b399118-c9f5-4c6b-accd-6566461738a7.png)

### Exiting the program
Exits the program.

Format: `bye`

Example: `bye`

Expected Output:

![image](https://user-images.githubusercontent.com/71833868/135296957-7f6e04f8-d73a-4954-96d3-640465706ee8.png)

