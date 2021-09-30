# User Guide

Duke is your personal task manager! Interact with him using the Command Line Interface.
## Features

### Add a Todo Task: `todo`

Adds a todo task to your task list.
* Format: `todo { description }`
* Example of usage: `todo mow the lawn`
* Expected output:
```
 __________________________________________________
Got it. I've added this task:
 [T][ ]  mow the lawn
Now you have 2 tasks on the list.
 __________________________________________________
```

### Add a deadline Task: `deadline`

Adds a deadline task to your task list.
* Format: `deadline { description } /by { deadline }`
* Example of usage: `deadline finish homework /by 11pm`
* Expected output:
```
 __________________________________________________
Got it. I've added this task:
 [D][ ] watch CS2113T lecture (by: 11pm)
Now you have 3 tasks on the list.
 __________________________________________________
```

### Add an event Task: `event`

Adds an event task to your task list.
* Format: `event { description } /at { event }`
* Example of usage: `event OP2 meeting /at 4pm`
* Expected output:
```
 __________________________________________________
Got it. I've added this task:
 [E][ ] watch Netflix with friends (at: 4pm)
Now you have 4 tasks on the list.
 __________________________________________________
```

### List all Tasks: `list`

Displays all current tasks
* Format: `list`
* Example of usage: `list`
* Expected output:
```
 __________________________________________________
Here are the tasks in your list:
 __________________________________________________
 
1. [T][ ] mow the lawn

2. [D][ ] watch CS2113T lecture (by: 11pm)

3. [E][ ] watch Netflix with friends (at: 4pm)

 __________________________________________________
 __________________________________________________
```

### Find all Tasks with Keyword: `find`

Displays all tasks that contains a specific keyword.
* Format: `find { keyword }`
* Example of usage: `find watch`
* Expected output:
```
 __________________________________________________
Here are the matching tasks in your list:
 __________________________________________________

1. [D][ ] watch CS2113T lecture (by: 11pm)

2. [E][ ] watch Netflix with friends (at: 4pm)

 __________________________________________________
 __________________________________________________

```

### Marks Task as Done: `done`

Marks a specific indexed task as done. 
* Format: `done { index }`
* Example of usage: `done 2`
* Expected output:
```
 __________________________________________________
Nice! I've marked this task as done:
   [D][X] watch CS2113T lecture (by: 11pm)

 __________________________________________________
```

### Delete a Task: `delete`

Delete a specific indexed task.
* Format: `delete { index }`
* Example of usage: `delete 2`
* Expected output:
```
 __________________________________________________
Noted. I've removed this task:
   [D][X] watch CS2113T lecture (by: 11pm)
Now you have 2 task(s) on the list.
 __________________________________________________

```

### Exit the program: `bye`

End the program
* Format: `bye`
* Example of usage: `bye`
* Expected output:
```
 __________________________________________________
   Bye! Hope to see you again soon!
 __________________________________________________

```
