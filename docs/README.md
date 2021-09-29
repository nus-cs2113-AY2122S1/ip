# IP (BoBo) User Guide

## Features 

### Add Todo task

Adds a new task with a description.

### Add Deadline task

Adds a new task with a description and a completion deadline.

### Add Event task

Adds a new task with a description and a event timeframe.

### List all tasks

Shows a numbered list of all tasks.

### Mark a task as done

Marks a specified task as completed. Represented in `list` with an `[X]`.

### Delete a task

Deletes the specified task.

### Exit

Shows a goodbye message and terminates the programme.

## Usage

### `todo` - Adds Todo task

Enter the `todo` keyword followed by the description of the task.

The description of the task can contain spaces, numbers and other characters.
The number of spaces between the `todo` keyword and the description does not matter. 

Example of usage: 

`todo CS2113T Homework` <br/>
`todoCS2113T Homework` <br/>
`todo   CS2113T Homework`

Expected outcome:

Confirmation of the addition of the `todo` task is displayed. 
`[T]` represents a task of type `todo`, 
while the second empty brace `[ ]` represents an undone task.

```
Umm ok added:
  [T][ ] CS2113T Homework
Now you have 1 tasks in the list.
```

### `deadline` - Adds Deadline task

Enter the `deadline` keyword followed by the description of the task. 
Enter the completion deadline after the `/by` keyword.

The description and completion deadline of the task can contain spaces, numbers and other characters.
The number of spaces between the `deadline`/`/by` keyword and the description/completion deadline does not matter,
however the `/by` keyword must be present.

If the completion deadline is entered in the format `YYYY-MM-DD HH:MM`, 
the completion deadline will be stored as a LocalDateTime object.


Example of usage:

`deadline Return library book /by tomorrow 2359H (30/9/2021)` <br/>
`deadlineReturn library book/bytomorrow 2359H (30/9/2021)` <br/>
`deadline  Return library book  /by   tomorrow 2359H (30/9/2021)` <br/>
`deadline Return library book /by 2021-09-30 23:59`

Expected outcome:

Confirmation of the addition of the `deadline` task is displayed.
`[D]` represents a task of type `deadline`,
while the second empty brace `[ ]` represents an undone task.
Completion deadline is found in brackets `()` after the `by: ` keyword.

```
Umm ok added:
  [D][ ] Return library book (by: tomorrow 2359H (30/9/2021))
Now you have 2 tasks in the list.
```
```
Umm ok added:
  [D][ ] Return library book (by: 30 Sep 2021 23:59H)
Now you have 2 tasks in the list.
```

### `event` - Adds Event task

Enter the `event` keyword followed by the description of the task.
Enter the event timeframe after the `/at` keyword.

The description and event timeframe of the task can contain spaces, numbers and other characters.
The number of spaces between the `event`/`/at` keyword and the description/event timeframe does not matter,
however the `/at` keyword must be present.

If the time is entered in the format `YYYY-MM-DD HH:MM`,
the time will be stored as a LocalDateTime object.

Example of usage:

`event CS2113 lecture /at Friday 4-6pm` <br/>
`eventCS2113 lecture/atFriday 4-6pm` <br/>
`event  CS2113 lecture  /at   Friday 4-6pm` <br/>
`event CS2113 lecture /at 2021-10-01 16:00`

Expected outcome:

Confirmation of the addition of the `event` task is displayed.
`[E]` represents a task of type `event`,
while the second empty brace `[ ]` represents an undone task.
Event timeframe is found in brackets `()` after the `at: ` keyword.

```
Umm ok added:
  [E][ ] CS2113 lecture (at: Friday 4-6pm)
Now you have 3 tasks in the list.
```
```
Umm ok added:
  [E][ ] CS2113 lecture (at: 1 Oct 2021 16:00H)
Now you have 3 tasks in the list.
```

### `list` - Lists all tasks

Enter the `list` keyword.

Example of usage:

`list`

Expected outcome:

Numbered list of all tasks stored in memory is displayed.
`[T]`/`[D]`/`[E]` represent the type of the task, 
while `[X]` represents a completed task.

```
Here are the tasks in your list:
1.[T][ ] CS2113T Homework
2.[D][ ] Return library book (by: tomorrow 2359H (30/9/2021))
3.[E][X] CS2113 lecture (at: Friday 4-6pm)
```

### `done` - Marks a task as done

Enter the `done` keyword followed but the task number shown in `list`.
Number of spaces between `done` and the number does not matter,
however the number must be in numeral format.

Example of usage:

`done 2` <br/>
`done2` <br/>
`done   2`

Expected outcome:

Shows confirmation that specified task has been marked as done.
If the task was previously marked as done, a remark will be shown to notify the user.

```
Okie! Marked this as done:
2.[D][X] Return library book (by: tomorrow 2359H (30/9/2021))
```
```
This task is already done!
```

### `delete` - Deletes a task

Enter the `delete` keyword followed but the task number shown in `list`.
Number of spaces between `delete` and the number does not matter,
however the number must be in numeral format.

Example of usage:

`delete 2` <br/>
`delete2` <br/>
`delete   2`

Expected outcome:

Shows confirmation that specified task has been deleted.

```
Okie! Deleted this task:
  [D][X] Return library book (by: tomorrow 2359H (30/9/2021))
Now you have 2 tasks in the list.
```

### `bye` - Exits the programme

Enter the `bye` keyword.

Example of usage:

`bye`

Expected outcome:

Shows a goodbye message before exiting the programme. 
All previously added tasks will be stored in memory and retrieved upon next run of the programme.

```
Ok. Bye bye!
```
