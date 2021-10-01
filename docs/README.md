# User Guide

## Features 

### Adding a Todo : `todo`

Adds a todo to the task list.

Format : `todo TODO`

Examples : `todo Read book to my boyfriend`

Expected outcome :

Program shows the todo that has been added to the list and the total number of tasks in the list.
```
Ok! I've added this task:
[T][ ] Read book to my boyfriend
Now you have 5 tasks in your list uwu
```


### Adding a Deadline : `deadline`

Adds a deadline and the date (and time) that it is due to the task list.

Format : `deadline DEADLINE /by DATE_AND_TIME`

* Date and time will be stored the way that it is put in by the user, there is no specified format.
* Date or time **must** be provided

Examples : `deadline Break up with Boyfriend /by 1st October 2359`

Expected outcome :

Program shows the deadline that has been added to the list and the total number of tasks in the list.

```
Ok! I've added this task:
  [D][ ] Break up with Boyfriend (by: 1st October 2359)
Now you have 4 tasks in your list uwu
```

### Adding an Event : `event`

Adds an event and the date (and time) that it is happening to the task list.

Format : `event EVENT /at DATE_AND_TIME`

* Date and time will be stored the way that it is put in by the user, there is no specified format.
* Date or time **must** be provided

Examples : `event Spend time with Boyfriend /at 2nd October 0000`

Expected outcome :

Program shows the event that has been added to the list and the total number of tasks in the list.
```
Ok! I've added this task:
  [E][ ] Spend time with Boyfriend (at: 2nd October 0000)
Now you have 4 tasks in your list uwu
```



### Listing all tasks : `list`

Shows a list of all the tasks that are currently in the task list.

Format : `list`

Expected outcome :

Program will show the whole task list with all the remaining tasks left.
```
Here are the tasks in your list:
1.   [E][ ] Go on a date with my boyfriend (at: Wed)
2.   [D][X] Break up with my boyfriend (by: 1st Oct 2359)
3.   [E][ ] Spend time with Boyfriend (at: 2nd October 0000)
4.   [D][ ] Break up with Boyfriend (by: 1st October 2359)
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 

```


### Marking a Task as Completed : `done`

Marks an existing task in the task list as completed.

Format : `done INDEX`

* Marks task as completed at the specified `INDEX`. The index refers to the index number displayed in the task list. The index **must be a positive integer** 1, 2, 3, .. etc.

Examples : `done 1` marks the task at index 1 as completed.

Expected outcome :

Program will show the task that has been marked as completed.
```
Good job! I've marked this task as done:
  [D][X] Break up with my boyfriend (by: 1st Oct 2359)
```


### Deleting a Task : `delete`

Deletes an existing task in the task list.

Format : `delete INDEX`

* Deletes task at the specified `INDEX`. The index refers to the index number displayed in the task list. The index **must be a positive integer** 1, 2, 3, ... etc.

Examples : `delete 1` deletes the task at index 1.

Expected outcome :

Program will show the task that has been recently deleted, the remaining number of tasks left in the list and the task list with the remaining tasks.
```
Okies! I've removed this task <3 :
  [T][ ] Buy a gift for my boyfriend
Now you have 1 tasks in your list uwu
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
Here are the tasks in your list:
1.   [E][ ] Go on a date with my boyfriend (at: Wed)
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
```




### Locating task by keyword : `find`

Find tasks which contain the keyword provided.

Format : `find KEYWORD`

* The search is case-sensitive. eg. boyfriend will not match Boyfriend
* Multiple keywords can be entered, but tasks will only be found if word orientation is the same.
* Date and time for Events and Deadlines can be searched for as well.
* Non-full words will be returned, eg. Boy will return Boyfriend

Examples : `find Book` returns `Read Book`

Expected outcome :

Program returns a list with all the matching tasks.
```
Here are the matching tasks in your list:
1.   [T][ ] Read Book
_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
```


### Exiting the Program : `bye`

Exits the program.

Format : `bye`

Expected outcome :

```
Goodbye. I will miss you sooo much :(
```
