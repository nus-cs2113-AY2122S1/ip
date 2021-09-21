# User Guide for Duke
Duke is a simple application that makes use of a Command Line Interface to help you in keeping track of your daily tasks.
## Installation
1. Install Java 11 on your machine if you have not done so yet. You can follow the guide [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/what-is-corretto-11.html).
2. Download the latest release of Duke from the [GitHub repository](https://github.com/Roycius/ip).
3. Copy the `.jar` file into the folder you want to use as the home directory for the application.
4. Go to the directory where the file is stored in your machine.
5. Run the command `java -jar [filename].jar` to launch the application.
## Features

Note: The parameters for date are to be in `yyyy-MM-dd HH:mm` format. The index of tasks is such that "1" represents the
first task in the list.

### Listing out all Tasks - `list`

Lists out all added tasks that have not yet been deleted.

Format: `list`

Example usage:
```
list
========================================================================
Here are the tasks in your list:
1.[D][ ] Assignment 1 (by: 19 Sep 2021 20:00)
2.[D][ ] CS2113 Project (by: 12 Dec 2021 20:30)
3.[E][X] CCA meeting (at: 26 Sep 2021 20:00)
4.[E][ ] Wedding Dinner (at: 12 Dec 2021 20:30)
5.[T][ ] Buy a new computer
========================================================================
```

### Adding a Todo - `todo`

Adds a Todo task to the list of tasks. A Todo Task requires only a description of the task.

Format: `todo [description]`

Example usage:
```
todo Buy a new computer
========================================================================
Got it. I've added this task:
  [T][ ] Buy a new computer
Now you have 5 tasks in the list.
========================================================================
```

### Adding a Deadline - `deadline`

Adds a Deadline task to the list of tasks. A Deadline Task requires a description of the task and the date of
due of the task.

Format: `deadline [description] /by [date]`

Example usage:
```
deadline Return library book /by 2021-12-20 15:00
========================================================================
Got it. I've added this task:
  [D][ ] Return library book (by: 20 Dec 2021 15:00)
Now you have 6 tasks in the list.
========================================================================
```

### Adding an Event - `event`

Adds an Event task to the list of tasks. An Event Task requires a description of the task and the date of
commencement of the event.

Format: `event [description] /at [date]`

Example usage:
```
event Charity Marathon /at 2022-02-15 10:00
========================================================================
Got it. I've added this task:
  [E][ ] Charity Marathon (at: 15 Feb 2022 10:00)
Now you have 7 tasks in the list.
========================================================================
```

### Marking a Task as Done - `done`

Marks the task of the given index as done.

Format: `done [index]`

Example usage:
```
done 4
========================================================================
Nice! I've marked task number 4 as done:
  [E][X] Wedding Dinner (at: 12 Dec 2021 20:30)
========================================================================
```

### Deleting a Task - `delete`

Deletes the task of the given index.

Format: `delete [index]`

Example usage:
```
delete 3
========================================================================
Noted. I've removed task number 3:
  [E][X] CCA Meeting (at: 19 Sep 2021 20:00)
Now you have 6 tasks in the list.
========================================================================
```

### Listing out Upcoming Tasks - `upcoming`

Lists out the upcoming deadlines and events within a specified number of days from the current time.

Format: `upcoming [days]`

Example usage:
```
upcoming 3
========================================================================
Here are the tasks due within the next 3 days:
1.[D][ ] Assignment 1 (by: 19 Sep 2021 20:00)
2.[D][X] Presentation Project (by: 20 Sep 2021 05:00)
========================================================================
========================================================================
Here are the upcoming events within the next 3 days:
You have no events within the next 3 days!
========================================================================
```

### Searching for Task by Keyword - `find`

Lists out all the tasks in the list that contains the specified keyword.

Format: `find [keyword]`

Example usage:
```
find Exam
========================================================================
Here are the matching tasks in your list:
1.[E][ ] Math Mid-Term Exam (by: 24 Sep 2021 09:00)
2.[E][ ] History Mid-Term Exam (at: 29 Sep 2021 12:30)
========================================================================
```

### Exiting the Program - `bye`

Terminates the program.

Format: `bye`

Example usage:
```
bye
========================================================================
Bye! Hope to see you again soon!
========================================================================
```

### Saving the Data

There is no need to manually save the task data as Duke automatically updates the save file whenever any changes are
made to the task list.