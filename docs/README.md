# User Guide
toDoList is a Command Line Interface (CLI) application that 
helps you to manage your tasks.

## Contents of User Guide
- [Features] (#user-content-features)
  1. [`list` - List all tasks](#listing-all-tasks)
  2. [`todo` - Create a todo task](#user-content-adding-a-todo)
  3. [`deadline` - Create a deadline task](#user-content-adding-a-deadline)
  4. [`event` - Create an event task](#user-content-adding-an-event)
  5. [`done` - Mark task as done](#user-content-mark-as-done)
  6. [`delete` - Delete task from list](#user-content-delete-task)
  7. [`find` - Find tasks based on keyword](#user-content-find-task)
  8. [`bye` - Exit program](#user-content-exit-program)
## Features 
toDoList supports 3 types of tasks: `To-Do`, `Deadline` and `Event`.

### Listing all tasks

Shows a list of all tasks.

Format: `list`

Example of usage:
```
list
```
Expected outcome:
```
     Here are the tasks in your list:
     1.[T][ ] add book
     2.[E][X] see book (at: 1030)
     3.[D][ ] submit work (by: 1040)
```

### Adding a Todo
Adds a Todo task

Format: `todo <TASK_NAME>`

Example of usage:
```
todo add book
```
Expected outcome:
```
     Got it. I've added this task:
       [T][ ] add book
     Now you have 6 tasks in the list.
```

### Adding a Deadline
Adds a Deadline task, followed by date

Format: `deadline <TASK_NAME> /by <DATE>`

Example of usage:
```
deadline finish assignment /by 29/09/21 2359
```
Expected outcome:
```
     Got it. I've added this task:
       [D][ ] finish assignment (by: 29/09/21 2359)
     Now you have 7 tasks in the list.
```

### Adding an Event
Adds an Event task, followed by date

Format: `event <TASK_NAME> /at <DATE>`

Example of usage:
```
event birthday party /at 30/10/21 1800
```
Expected outcome:
```
     Got it. I've added this task:
       [E][ ] birthday party (at: 30/10/21 1800)
     Now you have 8 tasks in the list.
```

### Mark as done
Marks a task as done

Format: `done <TASK_INDEX>`

Example of usage:
```
done 3
```
Expected outcome:
```
     Nice! I've marked this task as done:
       [D][X] finish assignment (by: 29/09/21 2359)
```       

### Delete task
Deletes a task from the list.

Format: `delete <TASK_INDEX>`

Example of usage:
```
delete 2
```
Expected outcome:
```
     Noted. I've removed this task:
       [E][ ] birthday party (at: 30/10/21 1800)
     Now you have 3 tasks in the list.
```       

### Find task
Finds tasks given keyword

Format: `find <KEYWORD>`

Example of usage:
```
find book
```
Expected outcome:
```
     Here are the matching tasks in your list:
     1.[D][ ] throw book (by: 01/01/22)
     2.[T][ ] buy new book
``` 


### Exit program
Exits from toDoList.

Format: `bye`



