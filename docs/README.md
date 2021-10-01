# Duke User Guide

Welcome to Duke 🐧 - your personal task manager!
Let's record all your 'tasks' in a 'list'!

## Features

### Feature-Add

Adds task to your list

### Feature-Deadline

Updates a todo to deadline

### Feature-Done

Marks a set of tasks as done

### Feature-Find

Return tasks that have matching keywords

### Feature-Date

Returns tasks that have valid dates

### Feature-List

Shows you the current list

### Feature-Delete

Deletes a set of tasks

### Feature-Clear

Clears your list

### Feature-Echo

Echo's your command

### Feature-Mascot

Have a 🐧 mascot repeat after you

### Feature-Help

List all of duke's command prefixes and brief description

## Usage

### `add` - adds task to list

Adds tasks to a list, line by line, stops when `stop` is called

A task could either be a :

1. todo [T]    - does not have any date recorded
1. deadline [D] - contains a due date/ time, mark using `/at`
1. event    [E]    - contains the date of event, mark using `/by`

Example of usage:

```
add
Read books
CS2113 Tutorial /by 10am
Lunch with friends /at Tommorow 6PM
stop
```

### `todo` - adds a todo to a list

Similar to adding a task, but adds one at a time rather than a list

```
todo Nigel submit UG draft /by today 2359
```

### `event` - adds a event to a list

Similar to adding a task, but adds one at a time rather than a list

```
event Nigel attend CS2113 lecture Friday Sep 2021 /at 4 - 6pm
```

### `deadline` - updates deadline for a todo

Modifies a todo into a deadline

Format: [INDEX] [DEADLINE]
Examples of usage:

```
deadline
3 today 6pm
```

### `done` - marks tasks as done

Marks a set of tasks as done, read in one line

Example of usage:

```
done
1 2
```
Expected outcome:

Tasks marked as done, descriptions indicated to user

```
done Read books, CS2113 Tutorial ,
 / done tasks, good job! /
finished marking as done!
```
### `find` - find tasks 

find tasks with matching keyword

Example of usage:

```
find
book
```
Expected outcome:

tasks with matching description returned,
as well as status of task

```
Here are the matching tasks in your list: 
[T] [✓] Read books
finished finding
```

### `date` - finds tasks with valid date given

Display tasks with matching date.

Note that if no valid date is added with a task , the task is tagged with 
 default date 1999-11-30.

Example of usage:

```
date
1999-11-30
```
Expected outcome:

Display tasks with matching date

```
I found the following tasks:
movie 
with the given date: 1969-04-20
finished getting date!
```
### `list` - display list

Display list of tasks

Example of usage:

```
list
```
Expected outcome:

list displayed, along with status of task

```
 /          / 
1. [T] [✓] Read books
2. [D] [✓] CS2113 Tutorial  (by:  10am) 
3. [E] [ ] Lunch with friends  (at:  Tommorow 6PM) 
 /          / 
finished listing tasks!
```
### `delete` - delete tasks

Delete tasks from list

Example of usage:

```
delete
1 3
```
Expected outcome:

Delete set of tasks with respective index

```
remove 3: Lunch with friends 
remove 1: Read books
finished deleting tasks!
```

### `clear` - clear all tasks

Clears list

Example of usage:

```
clear
```
Expected outcome:

Output clear message

```
finished clearing list!
```

### `echo` - echo input

repeat anything said

Example of usage:

```
echo
hello world
```
Expected outcome:

repeats input

```
    hello world
finished echoing!
```
### `mascot` - echo, but 'cooler' 🐧

🐧 repeats input

Example of usage:

```
mascot
hello world
```
Expected outcome:

```
< hello world  >
 -----------------------
   \
    \
        .--.
       |o_o |
       |:_/ |
      //   \ \
     (|     | )
    /'\_   _/`\
    \___)=(___/
                  
finished saying stuff!
```

### `help` - list all of Duke's features

Example of usage:
```
help
```
Expected outcome:

all features listed with command prefix, followed by description

```
add : add tasks to list
 .. More features ..
help : prints feature list
```