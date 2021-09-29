# User Guide for Duke 1.0
 Duke is an interactive command line interface application to help you manage your tasks
efficiently and effectively!

Version 1.0 is Hero themed 😎 With Duke being an AI assassin that you (the Hero) are contracting
from a mysterious merchant known only as "The Templar". Tasks are considered
Duke's "targets" for execution! Because who says being responsible can't be fun, right?

## Contents

- [Quick Start](#quick-start)
- [Features](#features)
  - [Categorisation: Todo, Event, and Deadline](#categorisation-todo-event-and-deadline)
  - [Consolidated list of activity](#consolidated-list-of-activity)
  - [Mark activities as Done](#mark-activities-as-done)
  - [Delete activities from your list](#delete-activities-from-your-list)
  - [Find activities with a simple search](#find-activities-with-a-simple-search)
  - [Auto-Save & Auto-Load](#auto-save--auto-load)
- [Usage](#usage)
  - [Todo](#1-todo)
  - [Event](#2-event)
  - [Deadline](#3-deadline)
  - [List](#4-list)
  - [Done](#5-done)
  - [Delete](#6-delete)
  - [Find](#7-find)
  - [Bye](#8-bye)

## Quick Start
Step 1: Ensure you have __Java version 11__ installed on your computer. You may download it [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).

Step 2: Download the latest version of Duke from [here](https://github.com/ChrisLangton/ip/releases).

Step 3: Run 'java -jar Duke.jar' in your terminal window.

Step 4: Tada! You are now using Duke on your command line.

## Features

#### Categorisation: Todo, Event, and Deadline
These three individual features work hand in hand to __categorise your tasks__ and help you keep track of all
upcoming activities to keep your schedule in order!

#### Consolidated list of activity
The most crucial feature of Duke - the list feature consolidates, centralises, and stores all your activities into __one combined list__ for ease of tracking.

#### Mark activities as Done
Everybody wants the satisfaction of marking off their "todo list". Duke provides this gratification with the done feature, allowing you to reap maximum satisfaction by __marking your tasks as completed__ in the list.

#### Delete activities from your list
This feature helps you to __de-clutter your list__ - be it getting rid of already completed tasks or last minute cancellation of plans, this feature will help keep your list clean and in order.

#### Find activities with a simple search
This feature __helps you find (using keyword) tasks in your list__ that you are searching for. Whether you've temporarily forgotten about Duke's "delete" feature or are just that busy, this feature will help resolve all your search queries.

#### Auto-Save & Auto-Load
When you __end your session with Duke__, the Auto-Save feature will save the latest version of your list into a text file (see [Usage: bye](#8-bye)) for ease of transfer between computers and for memory purposes. When
you boot Duke up, the Auto-Load feature will load your previous saved text file onto the command line automatically too! No addidtional work needed ✌🏻

## Usage
Duke works through the user (you) keying in commands on the command line. This section outlines how to use each command.

> Tip: if you are ever unsure, key in `print commands` to display the valid commands that Duke can act on!


### 1. Todo

Command `todo (description)` will add your task to Duke's list.

Example: `todo GEQ Readings` will result in the addition of this task, giving the following output:

____________________________________________________________
`[DUKE:] ...understood.`

`============= TASK ACQUIRED: [T][ ] GEQ Readings =============`

`current execution total: 1`
____________________________________________________________
This confirms your Todo task has been added to the list.

### 2. Event

Command `event (description) /at (dd-MM-yyyy HH:mm)` will add your event to Duke's list.

Example: `event Dental Appointment /at 29-09-2021 14:30` will result in the addition of this event, giving the following output:

____________________________________________________________
`[DUKE:]
...understood.`

`============= TASK ACQUIRED: [E][ ] Dental Appointment (at: Sep 29 2021 2.30PM) =============`

`current execution total: 1`
____________________________________________________________
This confirms your Event has been added to the list.

### 3. Deadline
Command `deadline (description) /by (dd-MM-yyyy HH:mm)` will add your deadline to Duke's list.

Example: `deadline CS2113T Quiz /by 01-10-2021 23:59` will result in the addition of this deadline, giving the following output:
____________________________________________________________
`[DUKE:]
...understood.`

`============= TASK ACQUIRED: [D][ ] CS2113T Quiz (by: Oct 01 2021 11.59PM) =============`

`current execution total: 1`
____________________________________________________________
This confirms your Deadline has been added to the list.

### 4. List
Command `list` displays every activity you have added to the list thus far, numbered in order of addition.

Example: `list` after keying in all three of the [above](#1-todo) activities __in order__ will produce the following output:

____________________________________________________________
`PENDING HIT LIST:`

`1.[T][ ] GEQ Readings`

`2.[E][ ] Dental Appointment (at: Sep 29 2021 2.30PM)`

`3.[D][ ] CS2113T Quiz (by: Oct 01 2021 11.59PM)`
____________________________________________________________
This is hence the numbered list of activities that you have added so far.

### 5. Done
Command `done (number in list)` marks the specified activity as completed in your list.

Example: `done 1` used after keying in the [above](#4-list) three activities will produce this output:
____________________________________________________________
`TARGET NEUTRALISED: 1. [T][x] GEQ Readings`
____________________________________________________________
This confirms that the activity has been marked as completed in your list. To verify this, using `list` immediately after this will produce this output:

____________________________________________________________
`PENDING HIT LIST:`

`1.[T][x] GEQ Readings`

`2.[E][ ] Dental Appointment (at: Sep 29 2021 2.30PM)`

`3.[D][ ] CS2113T Quiz (by: Oct 01 2021 11.59PM)`
____________________________________________________________

### 6. Delete
Command `delete (number in list)` will remove the specified activity from your list.

Example: `delete 3` from the list given [above](#5-done) will produce the following output:

____________________________________________________________
`TARGET REMOVED: 3. [D][ ] CS2113T Quiz (by: Oct 01 2021 11.59PM)`
____________________________________________________________
This confirms the activity has been removed from your list. To verify this, we can enter
command `list` once again. We should see the output:

____________________________________________________________
`PENDING HIT LIST:`

`1.[T][x] GEQ Readings`

`2.[E][ ] Dental Appointment (at: Sep 29 2021 2.30PM)`
____________________________________________________________

### 7. Find
Command `find (keyword)` will search your list and display any activities that contain the key word/phrase
that you input.

Example: `find Readings` on the [above](#6-delete) list will produce the output:

____________________________________________________________
`TASK(S) FOUND ARE:`

`- [T][x] GEQ Readings`
____________________________________________________________
The resulting activities are all activities in your list containing your key word/phrase.

### 8. Bye
Command `bye` ends your session with Duke and saves it in "tasks.txt" in the file titled "data".

Example: `bye` will produce this output:

____________________________________________________________
`[The Templar:]`

`DUKE shall carry out his mission. Farewell, Hero.`
____________________________________________________________
and (following the example [above](#6-delete)) your "tasks.txt" file should read:

`[T][x] GEQ Readings`

`[E][ ] Dental Appointment (at: Sep 29 2021 2.30PM)`

[__to top of page__](#user-guide-for-duke-10)