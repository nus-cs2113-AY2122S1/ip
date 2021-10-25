
# Owl's User Guide

This is a user guide designed to help newer users who are unfamiliar with the commands so do not worry and just follow through!
<details>
<summary>Example</summary>
<pre>
-----------------------------------------------------------------------------------------------------
This is a list of commands and their format!
-----------------------------------------------------------------------------------------------------
List Out All Commands: help
Adding Expense: add_ex d/DESCRIPTION a/AMOUNT c/CATEGORY
Deleting Expense: del_ex i/INDEX
Listing Expense: list_ex
Show Total Expense: total_ex
Show Total Expense between 2 dates: btw_ex s/START_DATE e/END_DATE
Adding Income: add_in d/DESCRIPTION a/AMOUNT c/CATEGORY
Deleting Income: del_in i/INDEX
Listing Income: list_in
Show Total Income: total_in
To Find Using Date: find YYYY-MM-DD
To Find Based On Keyword: find KEYWORD
To Display Total Balance: balance
Show Total Income between 2 dates: btw_in s/START_DATE e/END_DATE
To Set Budgets: set_budget c/CATEGORY a/AMOUNT
To Check Budgets: check_budget c/CATEGORY
To Set Threshold Value for Reminders: set_threshold t/THRESHOLD
To View Your Yearly Report: show_graph
To Terminate The Program: end
-----------------------------------------------------------------------------------------------------
</pre>
</details>

```
  Hello this is OWL! I help you remember tasks!!
     ______   _        _   _
    |  __  | | | ___  | | | | 
    | |  | | | |/   \\| | | | 
    | |__| | |   / \\   | | |____
    |______| |__/   \\__| |______|
```
## Content Page

* Features
    * Add a Todo : `todo`
    * Add a Deadline : `deadline`
    * Add an Event : `event`
    * Delete a task : `delete`
    * List the entire list : `list`
    * Mark a task as done : `done`
    * Find a task using a keyword : `find`
    * Terminate the program : `bye`
* Command Summary

### Adding a todo

Adds a Todo type of task to the Owl

Format: `todo TASK_DESCRIPTION`  
Examples:
- `todo fishing`
- `todo swim in the ocean`

Expected outcome:
````
todo fishing
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Owl: I've added that!
Owl: You added this: [T] fishing
There are currently 1 task now!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
todo swim in the ocean
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Owl: I've added that!
Owl: You added this: [T] swim in the ocean
There are currently 2 task now!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
````
### Adding a deadline

Adds a deadline type of task to the Owl
```
Take not user can add in date in the form of YYYY-MM-DD as due date to
to get automatically converted into nice date formats!
```

Format: `deadline TASK_DESCRIPTION /by DUE_DATE`  
Examples:
- `deadline this assignment /by tomorrow 9am`
- `deadline MidTerms /by 2020-08-19`

Expected outcome:
````
deadline this assignment /by tomorrow 9am
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Owl: I've added that!
Owl: You added this: [D] this assignment(by: tomorrow 9am)
There are currently 1 task now!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
deadline MidTerms /by 2020-08-19
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Owl: I've added that!
Owl: You added this: [D] MidTerms(by: Aug 19 2020)
There are currently 2 task now!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
````
### Adding an event

Adds a event type of task to the Owl
```
Take not user can add in date in the form of YYYY-MM-DD as event timeframe to
to get automatically converted into nice date formats!
```

Format: `event TASK_DESCRIPTION /at TIMEFRAME`  
Examples:
- `event basketball match /at tomorrow 8-9am`
- `event MidTerms /at 2020-08-19`

Expected outcome:
````
event basketball match /at tomorrow 8-9am
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Owl: I've added that!
Owl: You added this: [E] basketball match(at: tomorrow 8-9am)
There are currently 1 task now!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
event MidTerms /at 2020-08-19
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Owl: I've added that!
Owl: You added this: [E] MidTerms(at: Aug 19 2020)
There are currently 2 task now!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
````

### Deleting a task

Deletes task from the list remembered by OWL
```
Take not user can figure out the task's index by usingthe list command
```

Format: `delete INDEX`  
Examples:
- `delete 1`
- `delete 3`

Expected outcome:
````
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
1.[E][X] basketball match(at: tomorrow 8-9am)
2.[E][ ] MidTerms(at: Aug 19 2020)
3.[T][ ] chiwawa
4.[E][X] you can do it(at: 9am)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
delete 1
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Owl: I've deleted that!
Owl: You deleted this: basketball match
There are currently 3 task now!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
delete 3
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Owl: I've deleted that!
Owl: You deleted this: you can do it
There are currently 2 task now!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
list
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Squawkkk this is everything you have!!!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
1.[E][ ] MidTerms(at: Aug 19 2020)
2.[T][ ] chiwawa
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
````
### List all the tasks

List all the task remembered by OWL including Todos, Deadlines and Events


Format: `list`

Expected outcome:
````
list
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Squawkkk this is everything you have!!!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
1.[E][X] basketball match(at: tomorrow 8-9am)
2.[E][ ] MidTerms(at: Aug 19 2020)
3.[T][ ] chiwawa
4.[E][X] you can do it(at: 9am)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
````
### Done (Mark Completion of a task)

Owl will mark the task as completed

Format: `done INDEX`  
Examples:
- `done 1`
- `done 4`

Expected outcome:
````
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
1.[E][ ] basketball match(at: tomorrow 8-9am)
2.[E][ ] MidTerms(at: Aug 19 2020)
3.[T][ ] chiwawa
4.[E][ ] you can do it(at: 9am)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
done 1
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Oooh I see you've done task 1
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
done 4
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Oooh I see you've done task 4
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
list
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Squawkkk this is everything you have!!!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
1.[E][X] basketball match(at: tomorrow 8-9am)
2.[E][ ] MidTerms(at: Aug 19 2020)
3.[T][ ] chiwawa
4.[E][X] you can do it(at: 9am)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 

````
### Finding a task using a keyword

Based on an input keyword, Owl will find the task with similar details

Format: `find KEYWORD`  
Examples:
- `find basketball`
- `find the needle in the haystack`

Expected outcome:
````
list
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Squawkkk this is everything you have!!!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
1.[E][ ] MidTerms(at: Aug 19 2020)
2.[T][ ] chiwawa
3.[T][ ] basketball is life
4.[T][ ] throw the basketball
5.[T][ ] fishing
6.[D][ ] find the needle in the haystack(by: tmr 8am)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
find basketball
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Squawkkk this is everything you have!!!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
1.[T][ ] basketball is life
2.[T][ ] throw the basketball
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
find the needle in the haystack
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
Squawkkk this is everything you have!!!
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
1.[D][ ] find the needle in the haystack(by: tmr 8am)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 

````
### Bye

Terminates the Owl program and saves the file
```
Take note that the entries remembered by OWL will be saved in a file in
the same folder as the jar file of the OWL program!
```

Format: `bye`  

Expected outcome:
````
bye
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
SQUAWK! See you next time! :)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
````

### Command Summary

Action | Example Input with format
------------ | -------------
Add todo | `todo TASK_DESCRIPTION` <br> e.g `todo swim in the ocean`
Add deadline | `deadline TASK_DESCRIPTION /by DUE_DATE` <br> e.g `deadline this assignment /by tomorrow 9am`
Add event | `event TASK_DESCRIPTION /at TIMEFRAME` <br> e.g `event basketball match /at tomorrow 8-9am`
Delete task | `delete INDEX` <br> e.g `delete 4`
List | `list` 
Done | `done INDEX` <br> e.g `done 4`
Find | `find KEYWORD` <br> e.g `find basketball`
Terminate | `bye`






  


