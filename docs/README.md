# User Guide

JARVIS is your own personalised bot that keeps track of your daily activities. You can use it either from the GUI or from command line, it caters to every person's needs!

## Features

### Tracking day-to-day activities

A handy list of things that you can classify as an event, deadline or todo. You can even assign dates, times and even the location next to the item to remind you later.

### Editing your list freely

The list of items above can be edited freely. You can add, remove, see a list of current items and even mark items as done when completed.

### Loading saved data

Once you close the bot, your list is stored and can be accessed again whenever you want! 

## Usage

You will first be greeted with a warm welcome message from JARVIS!

```
Initialising...............
--------------------------------------------------------------------------------
        _|       _|_|        _|_|_|      _|      _|    _|_|_|        _|_|_|
        _|     _|    _|      _|    _|    _|      _|      _|        _|
        _|     _|_|_|_|      _|_|_|      _|      _|      _|          _|_|
 _|     _|     _|    _|      _|    _|      _|  _|        _|              _|
  _| _|    _|  _|    _|  _|  _|    _|  _|    _|    _|  _|_|_|  _|  _|_|_|

----------------------------------Just a rather very intelligent system---------
Good Evening Sir! I'm J.A.R.V.I.S
How may I be of assistance to you today? 😁
--------------------------------------------------------------------------------
```
From there, you can start typing the below commands to interact with the him.

### `todo`

Allows you to add an item or chore that is yet to be completed by you.

Example of usage: 

`todo exercise`

Expected outcome:

```
--------------------------------------------------------------------------------
Will do sir, I've added:
  [T][ ] exercise
Now you have 1 task in your list.
--------------------------------------------------------------------------------
```
<br/>

### `deadline`

Allows you to add a deadline with a date or time.

Example of usage:

`deadline finish homework /by 7:30 pm Sunday`

Expected outcome:

```
--------------------------------------------------------------------------------
Will do sir, I've added: 
  [D][ ] finish homework (by: 7:30 pm Sunday)
Now you have 2 tasks in your list.
--------------------------------------------------------------------------------
```
<br/>

### `event`

Allows you to add an event with a date, time and even a place.

Example of usage:

`event attend party /at Marina Bay Sands from 1200 hrs`

Expected outcome:

```
--------------------------------------------------------------------------------
Will do sir, I've added: 
  [E][ ] attend party (at: Marina Bay Sands from 1200 hrs)
Now you have 3 tasks in your list.
--------------------------------------------------------------------------------
```
<br/>

### `list`

Shows all events, deadlines and todo items in your list so far.

Example of usage:

`list`

Expected outcome:

```
--------------------------------------------------------------------------------
Here are the current tasks in your list:
1.[T][ ] exercise
2.[D][ ] finish homework (by: 7:30 pm Sunday)
3.[E][ ] attend party (at: Marina Bay Sands from 1200 hrs)
--------------------------------------------------------------------------------
```
<br/>

### `done`

Allows you to mark any item as done using an "X".

Example of usage:

`done 2`

Expected outcome:

```
--------------------------------------------------------------------------------
As you wish sir, this task will be marked as done! 😁
    [D][X] finish homework (by: 7:30 pm Sunday)
--------------------------------------------------------------------------------
```
<br/>

```
--------------------------------------------------------------------------------
Here are the current tasks in your list:
1.[T][ ] exercise
2.[D][X] finish homework (by: 7:30 pm Sunday)
3.[E][ ] attend party (at: Marina Bay Sands from 1200 hrs)
--------------------------------------------------------------------------------
```

### `delete`

Allows you to delete any item from the list.

Example of usage:

`delete 2`

Expected outcome:

```
--------------------------------------------------------------------------------
1.[D][X] finish homework (by: 7:30 pm Sunday)
2.[E][ ] attend party (at: Marina Bay Sands from 1200 hrs)
--------------------------------------------------------------------------------
```
<br/>

### `echo`

JARVIS will repeat anything you say and will stop if you say so.

Example of usage:

`echo`
<br/>
`How are you?`
<br/>
`stop`


Expected outcome:

```
--------------------------------------------------------------------------------
What would you like me to repeat sir?
--------------------------------------------------------------------------------
```
```
--------------------------------------------------------------------------------
J.A.R.V.I.S says: How are you?
--------------------------------------------------------------------------------
```
```
--------------------------------------------------------------------------------
Okay sir, stopping echo mode
--------------------------------------------------------------------------------
```
<br/>

### `bye`

Say goodbye to JARVIS to end all operations.

Example of usage:

`bye`

Expected outcome:

```
--------------------------------------------------------------------------------
Affirmative sir, I'll shut down all operations
--------------------------------------------------------------------------------
```
