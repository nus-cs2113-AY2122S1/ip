# User Guide
# PEPO - The CLI Task Manager
## What is PEPO?
- PEPO is a Command Line Interface(CLI) Task Manager that keeps track of any tasks that you need to record.
- PEPO works best if you are a fast typist and work well with CLI

## Getting Started
1. Download the JAR release file from github
2. Open a Command Window in the folder that the JAR file is in
3. Run "java -jar {filename}.jar" on the command line
4. PEPO is now running!

## Features 

1. Add ToDo 
2. Add Deadline 
3. Add Event 
4. List all tasks 
5. Delete a task 
6. Mark a task as done 
7. Find a task
8. Exit PEPO

## Usage

### `todo` - Adds a task to be done into the list

- e.g. *todo water plants*
- Expected Output:
- *Nice! The task has been added to your todo list*
- *You now have x tasks in your list.*

### `deadline` - Adds a task with a deadline into the list

- e.g. *deadline go for a run /by 2021-09-30:1930*
- Expected output:
- *Nice! The task has been added to your todo list*
- *You now have x tasks in your list.*

### `event` - Adds an event with a specified location or timing into the list

- e.g. *event badminton /at SengKang Sports Hall at 2pm
- Expected output:
- *Nice! The task has been added to your event list*
- *You now have x tasks in your list.*

### `list` - Lists all current tasks

- e.g. *list*
- Expected output:
- 1.[E][ ] Dentist (At: SKGH Sunday 5pm)
- 2.[D][ ] Bypass surgery (By: Jan 01 2022 12:00 AM)
- 3.[D][X] run (By: Oct 19 2021 11:59 PM)
- 4.[T][ ] water plants

### `delete` - Deletes a task from the list

- e.g. *delete 3*
- Expected output:
- *Ok! The task has been deleted from your list*

### `done` - Marks a specific task from the list as done

- e.g. *done 2*
- Expected output:
- *YAY! That task has been marked as complete*

### `find` - Searches for a task that has the keyword you inputted and lists all matching tasks.

- e.g. *find run*
- Expected output:
- These are the matching tasks in your list:
- 3.[D][X] run (By: Oct 19 2021 11:59 PM)

### `bye` - Exits the program

- e.g. *bye*
- Expected output:
- *widePeepoSad :(*