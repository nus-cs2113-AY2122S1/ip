# User Guide

## Features 

### Reminder Bot

One can use the bot to remind oneself of their upcoming Deadlines, Events, Todos

### Auto Save on Exit

Bot automatically save on exit and retrieve items on next log in

## Usage

### `todo` - Set up basic todo item


Example of usage: 

`todo (item name)`

Expected outcome:

A basic todo item is set up

```
Got it. I've added this task:
[T][ ] homework
```

### `list` - list out all reminders

Example of usage: 

`list`

Expected outcome:

All the reminders are listed in a readable format, with indication of type of reminder and if reminder is done

```
1.[T][ ] homework
2.[D][ ] sw1  (by: Feb 19 2021 1800)
3.[E][ ] Prom (at: noon)
```

### `done` - Mark reminder as done


Example of usage: 

`done (number)`

Expected outcome:

The reminder which is to be marked would be marked as done, indicated by the X in the second checkbox

```
Nice! I've marked this task as done:
[D][X] sw1  (by: Feb 19 2021 1800)
```

### `deadline` - Set a Deadline


Example of usage: 

`deadline sw1 /by 19/02/2021 1800`

Expected outcome:

set deadline reminder

```
Got it. I've added this task:
[D][ ] sw1  (by: Feb 19 2021 1800)
```

### `event` - Set an Event


Example of usage: 

`event prom /at noon`

Expected outcome:

An event is set up with a timing set

```
Got it. I've added this task:
[E][ ] prom  (at: noon)
 Now you have 4 tasks in the list.
```


### `delete` - Deletes a reminder


Example of usage: 

`delete (number)`

Expected outcome:

Item deleted would be reflected

```
Noted. I've removed this task:
[T][ ] homework
Now you have 4 tasks in the list.
```

### `find` - Find an item by keyword


Example of usage: 

`find (keyword)`

Expected outcome:

The reminders matching would be listed
```
Here are the matching tasks in your list:
1.[E][ ] prom  (at: noon)
```

### `bye` - Terminates session


Example of usage: 

`bye`

Expected outcome:

Terminates the chatbot and saves reminders to file to be retrieved later
```
Bye. Hope to see you again soon!
```
