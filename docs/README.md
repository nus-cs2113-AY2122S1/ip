# Billy User Guide
Billy is task management application for managing regular tasks, deadlines and events. Billy interacts with the user using a CLI (Command Line Interface).

Prerequisites: JDK 11.

---

## For First-time Users:
1. Download `Billy.jar` into your desired directory.
2. Run `java -jar IP.jar` within the directory where `Billy.jar` is located to launch the program.
3. When the program runs, a new folder `data` containing `BillyData.txt` will be created.

## Using Billy:
Upon startup, the user will be greeted with the following message.
   ```
   Hello from
 ______    _   __   __            
|_   _ \  (_) [  | [  |           
  | |_) | __   | |  | |   _   __  
  |  __'.[  |  | |  | |  [ \ [  ] 
 _| |__) || |  | |  | |  \ '/  /  
|_______/[___][___][___][\_: /   
                           \__.'    
____________________________________________________________
Hello! I'm Billy
____________________________________________________________
   ```
Following which, the user can use the CLI to make use of Billy's respective features.

> **Notes about the command format:**
> - Words in `<UPPER_CASE>` surrounded by angle brackets are **required** parameters to be supplied by the user
> - Items in square brackets `[ ]` are **optional**.

---

## Summary of Features
* [Add a to-do task](#todo---add-a-to-do-task)
* [Add a deadline](#deadline---add-a-deadline)
* [Add an event](#event---add-an-event)
* [List current tasks](#list---display-the-current-list-of-tasks-with-their-respective-ids)
* [Mark tasks as done](#done---mark-tasks-as-done)
* [Find tasks containing a given description](#find---find-a-task-with-a-given-description)
* [Delete tasks](#delete---delete-tasks)
* [Terminate program](#bye--terminate-the-program)

---

### `todo` - Add a to-do task
Format: `todo <DESCRIPTION>`

Example: `todo Buy an apple`

---

### `deadline` - Add a deadline
Format: `deadline  <DESCRIPTION> /by <DATE> [TIME]`
> - Date is to be input in the following format:  `dd/MM/yyyy`
>
> - Time is to be input in the following format: `HH:mm`

Example 1: `deadline Finish homework /by 29/12/2021 23:59`

Example 2: `deadline Finish homework /by 29/12/2021` 

---

### `event` - Add an event
Format: `event  <DESCRIPTION> /at <DATE> [TIME]`
> - Date is to be input in the following format:  `dd/MM/yyyy`
>
> - Time is to be input in the following format: `HH:mm`

Example 1: `event Concert /at 29/12/2021 20:00`

Example 2: `event Concert /at 29/12/2021`

---

### `list` - Display the current list of tasks with their respective IDs
Format: `list`

---

### `done` - Mark tasks as done
Format: `done <TASK_IDs>`
> - Multiple tasks can be marked as done with a single command as long as the task IDs are separated by any character.

Example 1: `done 1 2 3`

Example 2: `done 1, 2 and 3`

---

### `find` - Find a task with a given description
Format: `find <DESCRIPTION>`

Example: `find iP user guide increment`

---

### `delete` - Delete tasks
Format: `delete <TASK_IDs>`
> - Multiple tasks can be marked as done with a single command as long as the task IDs are separated by any character.

Example 1: `delete 1 2 3`

Example 2: `delete 1, 2 and 3`

--- 

### `bye`- Terminate the program
Format: `bye`
