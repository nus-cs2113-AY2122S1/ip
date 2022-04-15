# Duke User Guide
Welcome to Duke, your very own command line task scheduler! In this user guide, you can find out how to use Duke.
## Features 

### Track your tasks!
- Add your tasks to our database
- Check tasks off when you've completed them
- Never miss another task ever!

### 3 different types of tasks
- Todo: Simple description, no deadline/ time
- Deadlines: Description with deadline to be completed by
- Events: Description with the date and time of the event

## List of commands

### 1. `todo` - Adds a Todo task to the database

- Briefly describe the Todo task after typing `todo`

#### Example of usage:

`todo clean kitchen`

#### Expected outcome:
```
Got it. I've added this task:
    [T][ ] clean kitchen
Now you have 1 tasks in your list.
```
#### Description of the outcome.
- The first box shows that the task is of the Todo type, represented by a 'T'
- The next box is empty, showing that the task has not been completed yet
  - If there is a cross 'X' in it, the task has been completed



### 2. `deadline` - Adds a Deadline task to the database
- Briefly describe the Deadline task after typing `deadline`
- Then type `/by`, followed by the deadline of the Deadline task

#### Example of usage:

`deadline finish assignment /by tonight`

#### Expected outcome:
```
Got it. I've added this task:
    [D][ ] finish assignment by: tonight
Now you have 2 tasks in your list.
```
#### Description of the outcome.
- The first box shows that the task is of the Deadline type, represented by a 'D'
- The next box is empty, showing that the task has not been completed yet
    - If there is a cross 'X' in it, that would mean that the task has been completed
- After the Deadline description, the deadline of the task will be displayed
    - In this case, the deadline is tonight


### 3. `event` - Adds an Event task to the database
- Briefly describe the Event task after typing `event`
- Then type `/at`, followed by the date and time of the Event task

#### Example of usage:

`event CS2113T exam /at 31 Feb 2021`

#### Expected outcome:
```
Got it. I've added this task:
    [E][ ] CS2113T exam at: 31 Feb 2021
Now you have 3 tasks in your list.
```
#### Description of the outcome.
- The first box shows that the task is of the Event type, represented by an 'E'
- The next box is empty, showing that the task has not been completed yet
    - If there is a cross 'X' in it, that would mean that the task has been completed
- After the Event description, the date and time of the task will be displayed
    - In this case, the date and time is 31 Feb 2021


### 4. `list` - Prints a list of all tasks that have been added

#### Example of usage:

`list`

#### Expected outcome:
```
Here are the tasks in your list:
1. [T][ ] clean kitchen
2. [D][ ] finish assignment by: tonight
3. [E][ ] CS2113T exam at: 31 Feb 2021
```
####  Description of the outcome.
- The tasks that have been added are numbered sequentially


### 5. `done` - Indicate to Duke that you have completed a task
- After typing `done`, type the index of the task you have completed 

#### Example of usage:

`done 1`

#### Expected outcome:
```
Nice! I've marked this task as done:
[T][X] clean kitchen
```
#### Description of the outcome.
- The second box will have an 'X' to indicate that it is checked



### 6. `find` - Prints all the tasks that match the keyword you give it
- After typing `find`, type the keyword of the tasks you are searching for
#### Example of usage:

`find finish`

#### Expected outcome:
```
Here are the matching tasks in your list:
1. [D][ ] finish assignment by: tonight
```
#### Description of the outcome.
- Since the word 'finish' appears in the above task, it will be displayed



### 7. `delete` - Removes a task from the database
- After typing `delete`, type the index of the task you want to delete
#### Example of usage:

`delete 1`

#### Expected outcome:
```
Noted. I've removed this task:
    [T][X] clean kitchen
Now you have 2 tasks in the list.
```



### 8. `bye` - Exits Duke

#### Example of usage:

`bye`

#### Expected outcome:
```
Byebye! Hope to see you again soon!
```