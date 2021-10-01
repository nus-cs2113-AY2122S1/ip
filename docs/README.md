# User Guide
This is a user guide to Gavien's CS2113T Duke Program.
## Features 
Duke is a task manager which is able to
1. Add new tasks.
2. List all existing tasks in chronological order.
3. Set a task a done.
4. Delete existing tasks.
5. Find all tasks that contain a specific word or words.
6. Save functionality of existing list before exiting.

### Feature - Add tasks
Duke is able to add 3 different tasks types.
To add tasks, you will need to follow the specific formatting of inputs as show in *italics*.
ToDo tasks - ToDo tasks are simple tasks that only have a description - *todo 'description'*
Deadline tasks - Deadline tasks are tasks with a specific deadline - *deadline 'description' /by 'deadline'*
Event tasks - Event tasks are tasks with a specific duration - *event 'description' /at 'duration'*

### Feature - List all tasks
Duke is able to list all existing tasks by chronological order that you have added them.
To list the tasks, the format is *list*.
Duke will then show you the tasks, including its index, type, done status, description and deadline/duration (if applicable) in this specific order.

### Feature - Set a task as done
Tasks will contain a status to show if they are done(X) or not( ).
To set a task as done, the format is *done 'task index'*.
You can find the task index by listing all tasks with *list*.

### Feature - Delete a task
Tasks can be deleted from the existing list.
To delete a task, the format is *delete 'task index'*.
You can find the task index by listing all tasks with *list*.

### Feature - Find tasks
You can find specific tasks by specifying a certain word or words.
Tasks that contain that word/words will then be listed.
To find specific tasks, the format is *find 'specific word/words'*.
Duke will then show you a list with the same format as listing all tasks.

### Feature - Save functionality
You are able to save your existing list of tasks when you exit Duke.
Your list will be saved in a text file **input.txt**.
Do not edit this text file as Duke is only able to read certain characters from the save file.

### Exit Duke
You can exit Duke by typing in *bye*.
Duke will then exit and save your existing list in the save file.

## Here are examples and their expected outputs.

### `ToDo`

Example: *todo buy a book*

Expected outcome:
____________________________________________________________
Gotcha! I've added this task:
[T][ ] buy a book
Now you have 1 tasks in the list.
____________________________________________________________

### `Deadline`

Example: *deadline buy a book /by tomorrow*

Expected outcome:
____________________________________________________________
Gotcha! I've added this task:
[D][ ] buy a book (by: tomorrow)
Now you have 2 tasks in the list.
____________________________________________________________

### `Event`

Example: *event book sale /at tomorrow 2-4pm*

Expected outcome:
____________________________________________________________
Gotcha! I've added this task:
[E][ ] book sale (at: tomorrow 2-4pm)
Now you have 3 tasks in the list.
____________________________________________________________

### `List`

Example: *list*

Expected outcome:
____________________________________________________________

Here are the tasks in your list:
1. [T][ ] buy a book
2. [D][ ] buy a book (by: tomorrow)
3. [E][ ] book sale (at: tomorrow 2-4pm)
____________________________________________________________

### `Done`

Example: *done 1*

Expected outcome:
____________________________________________________________
Nice! I've marked this task as done:
[T][X] buy a book
____________________________________________________________

### `Delete`

Example: *delete 2*

Expected outcome:
____________________________________________________________
Alright! I've removed this task from the list:
[D][ ] buy a book (by: tomorrow)
Now you have 2 tasks in the list.
____________________________________________________________

### `Find`

Example: *find buy*

Expected outcome:
____________________________________________________________

Here are the tasks in your list containing 'buy' :
1. [T][X] buy a book
____________________________________________________________

### `bye`

Example : *bye*

Expected outcome:
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________