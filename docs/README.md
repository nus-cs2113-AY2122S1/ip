# DUKE User Guide

Hello~ DUKE is a CLI (Command Line Interface) app to help you record your tasks.

![Image of Duke](https://upload.wikimedia.org/wikipedia/commons/5/5d/Duke_%28Java_mascot%29_waving.svg)
## Features 

### Feature- edit task

You can edit your tasks with given task types(todo, deadline, event).

### Feature- save/load

After you quit the app, your task list is stored in your computer.

## Usage
### Open DUKE

![Picture_Guide](https://github.com/DuckWillow/ip/blob/master/image/Picture_Guide.png)

### `Keyword` - Describe action

List of the keywords are shown below:

`bye`-Exit the program.

`list`-See all existing tasks.

`done`+ _number_ -Mark task as done.

>Example: `done 1`

`todo`+ _task_ Add a todo task

`deadline` + _task_ + `/by` -Add a deadline

>Example: `deadline return book /by Sunday`

`event` + _task_ + `/at` -Add an event

`delete` + _number_ -Delete the number task

`find` + _keyword_ -Find tasks with the keyword

>Example: `find book`

## Example of usage:

>`deadline return book /by Sunday`
```
____________________________________________________________
Got it. I've added this task: 
[D][ ] return book (by: Sunday)
Now you have 1 tasks in the list.
____________________________________________________________
```
>`event project meeting /at Mon 2-4pm`
```
____________________________________________________________
Got it. I've added this task:
[E][ ] project meeting (at: Mon 2-4pm)
Now you have 2 tasks in the list.
____________________________________________________________
```
>`done 2`
```
____________________________________________________________
Nice! I've marked this task as done:
[E][X] project meeting (at: Mon 2-4pm)
____________________________________________________________
```
>`list`
```
____________________________________________________________
1. [D][ ] return book (by: Sunday)
2. [E][X] project meeting (at: Mon 2-4pm)
____________________________________________________________
```
>`delete 1`
```
____________________________________________________________
Noted. I've removed this task: 
[D][ ] return book (by: Sunday)
Now you have 1 tasks in the list.
____________________________________________________________
```
>`list`
```
____________________________________________________________
1. [E][X] project meeting (at: Mon 2-4pm)
____________________________________________________________
```