# Triss: A User Guide

<br>

## How to use this user guide?

All features can be found in the index to the left. Press the specific command to learn more about it.

## Setting Up Triss

1. Press `View on GitHub` at the top of this page.
2. Open the `Releases` tab at the right of the github repo.
3. Press the dropdown labelled `Assets` in your preferred release.
4. Download the file titled `ip.jar` into an accessible folder.
5. Open up a command line interface in the folder.
6. Enter `java -jar ip.jar` to start Triss in the command line interface.
7. Congratulations! You should be good to go!

## List of Features

| Feature | Description |
----------|--------------
 `bye` |  Shut Triss down
 `list` |  List all tasks
 `done` |  Mark task as done
 `delete` |  Delete task
 `find` |  Find task by keyword
 `deadline` |  Add new deadline
 `event` |  Add new event
 `todo` |  Add new todo

<hr>

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

### Add a new event: `event`
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

### Add a new event: `event`
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

### Save all tasks
All tasks will be saved automatically in `data/storedtasks.txt` after every input from the user. If the program crashes during execution, the data preceding the crash will still be retained.

<hr>

## Frequently Asked Questions

<hr>

Q: I can't access my stored tasks after moving Triss to a new location. What can I do?

A: You should move the folder titled `data` from the original location to the new location, to access the previously stored tasks.

<hr>


<hr>