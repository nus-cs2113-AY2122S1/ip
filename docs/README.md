# Duke User Guide

## Introduction
Duke is a Personal Assistant Chatbot that helps a person keep track of various things,

## Features 

### Feature: Add task as "to do"
**Description of feature:** Add daily tasks that do not have a specified time to be done by.

**Format of command:** `to do [task]`
    
* `task` can be any string of characters.

**Examples:** 

**Command** | **Expected Output**
------------|----------------------
`to do run`| I can do that! I have added [run] to your task list!
`to do laundry`| I can do that! I have added [laundry] to your task list!

> Current Task list:
> 1. [T][ ] run 
> 2. [T][ ] laundry

### Feature: Add task as "Deadline"
**Description of the feature:** Add tasks that have a deadline it should be completed by.

**Format of command:** `deadline [deadlineName] /by [date]`

* `deadlineName` can be any string of characters.
* `date` is in the format [dd MM yyyy hh:mm].

**Examples:**

**Command** | **Expected Output**
------------|----------------------
`deadline assignment /by 02 06 2021 14:02`| I can do that! I have added [assignment] to your task list!
`deadline cg2027 /by 03 09 2021 00:00`| I can do that! I have added [cg2027] to your task list!

> Current Task list:
> 1. [T][ ] run
> 2. [T][ ] laundry
> 3. [D][ ] assignment (by:02 Jun 2021 02:02 PM)
> 4. [D][ ] cg2027 (by:03 Sep 2021 12:00 AM)

### Feature: Add task as "Event"
**Description of the feature:** Add events to the to do list.

**Format of command:** `event [eventName] /at [date]`

* `eventName` can be any string of characters.
* `date` is in the format [dd MM yyyy hh:mm].

**Examples:**

**Command** | **Expected Output**
------------|----------------------
`event 24km run /at 19 02 2021 06:00`| I can do that! I have added [running a marathon] to your task list!
`event conference /at 09 04 2021 08:00`| I can do that! I have added [conference] to your task list!

> Current Task list:
> 1. [T][ ] run
> 2. [T][ ] laundry
> 3. [D][ ] assignment (by:02 Jun 2021 02:02 PM)
> 4. [D][ ] cg2027 (by:03 Sep 2021 12:00 AM)
> 5. [E][ ] 24km run (at:19 Feb 2021 06:00 AM)
> 6. [E][ ] conference (at:09 Apr 2021 08:00 AM)

### Feature: Mark tasks as done
**Description of the feature:** Mark completed tasks as done.

**Format of command:** `done [taskNumber]`

**Examples:**

**Command** | **Expected Output**
------------|----------------------
`done 1 2`| Nice! I've marked this task as done: <br> 1.[T][X] run <br>Nice! I've marked this task as done: <br> 2.[T][X] Laundry
`done 6`| Nice! I've marked this task as done: <br> 6.[E][X] conference (at:09 Apr 2021 08:00 AM)

> Current Task list:
> 1. [T][X] run
> 2. [T][X] laundry
> 3. [D][ ] assignment (by:02 Jun 2021 02:02 PM)
> 4. [D][ ] cg2027 (by:03 Sep 2021 12:00 AM)
> 5. [E][ ] 24km run (at:19 Feb 2021 06:00 AM)
> 6. [E][ ] conference (at:09 Apr 2021 08:00 AM)

### Feature: Delete task
**Description of the feature:** Delete tasks from the to do list.

**Format of command:** `delete [taskNumber]`

* `taskNumber` should be a number within the number of tasks in the task list.

**Examples:**

**Command** | **Expected Output**
------------|----------------------
`delete 3 5`| I got you! I've deleted this task: <br> 3.[D][ ] assignment (by:02 Jun 2021 02:02 PM) <br> I got you! I've deleted this task: <br> 5.[E][ ] 24km run (at:19 Feb 2021 06:00 AM)
`delete 1`| I got you! I've deleted this task: <br> 1.[T][X] run

> Current Task list:
> 1. [T][X] laundry
> 2. [D][ ] cg2027 (by:03 Sep 2021 12:00 AM)
> 3. [E][ ] conference (at:09 Apr 2021 08:00 AM)

### Feature: View tasks in the to do list
**Description of the feature:** View all the tasks that have been added to the to do list.

**Format of command:** `list`

### Feature: Search for tasks in the to do list
**Description of the feature:** Search through tasks on the task list.

**Format of command:** `find [keywords]`

* `keywords` can be any string of characters.

### Feature: End programme
**Format of command:** `bye`

**Description of the feature:** Terminate Duke.
