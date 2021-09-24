# Triss: A User Guide

<br>

## List of Features

| Feature | Description |
----------|--------------
<center> `bye` </center> | <center> Shut Triss down </center>
<center> `list` </center> | <center> List all tasks </center>
<center> `done` </center> | <center> Mark task as done </center>
<center> `delete` </center> | <center> Delete task </center>
<center> `find` </center> | <center> Find task by keyword </center>
<center> `deadline` </center> | <center> Add new deadline </center>
<center> `event` </center> | <center> Add new event </center>
<center> `todo` </center> | <center> Add new todo </center>

### Shut Triss down : `bye`
Stops Triss and stores all tasks in _data/storedtasks.txt_.

#### Example of usage:

```
bye
____________________________________________________________
Thanks for coming. Auf wiedersehen!
____________________________________________________________
```
<hr>

### List all tasks : `list`
Lists all tasks in Triss's database.

#### Legend

Each task will be displayed in the following manner:

`TaskID.[Tasking][Completion Status] Name of Task (Timing of Task)`

The symbols in `[Tasking]` and [`Completion Status`] represent varying information as shown below:

`[Tasking]`
  * `[T]` - Todo
  * `[D]` - Deadline
  * `[E]` - Event

`[Completion Status]`
  * `[X]` - Task has been completed
  * `[ ]` - Task has not been completed

#### Example of usage:

```
list
____________________________________________________________
1.[T][X] eat
2.[D][ ] pray (Oct 15 2021)
3.[E][X] love (Jun 13 2021)
____________________________________________________________
```
<hr>

### Mark task as done : `done`
Marks a specific task as done.

#### Format

`done TaskID`

`TaskID` refers to the number preceding the individual task's details in `list`:

`TaskID.[Tasking][CompletionStatus] NameOfTask (TimingOfTask)`

#### Example of usage:

```
list
____________________________________________________________
1.[T][ ] eat
2.[D][ ] pray (Oct 15 2021)
3.[E][ ] love (Jun 13 2021)
____________________________________________________________
done 2
____________________________________________________________
____________________________________________________________
1.[T][ ] eat
2.[D][X] pray (Oct 15 2021)
3.[E][ ] love (Jun 13 2021)
____________________________________________________________
____________________________________________________________
```
<hr>

### Delete a task : `delete`
Delete a task from Triss's database.

#### Format

`delete TaskID`

<hr>

`TaskID` refers to the number preceding the individual task's details in `list`:

`TaskID.[Tasking][CompletionStatus] NameOfTask (TimingOfTask)`

#### Example of usage:

```
list
____________________________________________________________
1.[T][ ] eat
2.[D][ ] pray (Oct 15 2021)
3.[E][ ] love (Jun 13 2021)
____________________________________________________________
delete 2
____________________________________________________________
1.[T][ ] eat
2.[E][ ] love (Jun 13 2021)
____________________________________________________________
```
<hr>

### Find tasks containing a keyword : `find`
Finds tasks in Triss's database containing a given keyword.

#### Format

`find keyword`

`keyword` refers to the text you are searching for.

#### Example of usage:

```
list
____________________________________________________________
1.[T][ ] love
2.[D][ ] lover (Oct 15 2021)
3.[E][ ] lovers (Jun 13 2021)
____________________________________________________________
find lover
____________________________________________________________
Here are the matching tasks:
    [D][ ] lover (Oct 15 2021)
    [E][ ] lovers (Jun 13 2021)
____________________________________________________________
find rovers
____________________________________________________________
No tasks containing 'rovers' found!
____________________________________________________________
```
<hr>

### Add a new deadline: `deadline`
Adds a new deadline to Triss's database.

#### Format

`deadline NameOfDeadline /DueDate`

`NameOfDeadline` refers to the name of your deadline.

`DueDate` refers to the date which your deadline is on. It should be typed in `YYYY-MM-DD` format.

#### Example of usage:

```
event Submit Log Report /2021-12-12
____________________________________________________________
I've added: [E][ ] Submit Log Report (Dec 12 2021)
____________________________________________________________
```
<hr>

### Add a new deadline: `event`
Adds a new event to Triss's database.

#### Format

`event NameOfEvent /EventTiming`

`NameOfEvent` refers to the name of your event.

`EventTiming` refers to the date which your event is on. It should be typed in `YYYY-MM-DD` format.

#### Example of usage:

```
event Submit Log Report /2021-12-12
____________________________________________________________
I've added: [E][ ] Submit Log Report (Dec 12 2021)
____________________________________________________________
```
<hr>

### Add a new todo: `todo`
Adds a new todo to Triss's database.

#### Format

`todo NameOfTodo`

`NameOfTodo` refers to the name of your todo.

#### Example of usage:

```
todo Add Git Request
____________________________________________________________
I've added: [T][ ] Add Git Request
____________________________________________________________
```
<hr>