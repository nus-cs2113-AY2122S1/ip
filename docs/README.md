# Duke - CLI Task Manager

## Getting started with Duke
1. Copy the JAR file into an empty folder
2. Open command window in that folder
3. Run `java -jar {filename}.jar`

## Functionalities within Duke App

### Non-Insertion commands
####  `list` - *Lists down all the tasks you have*
    1. [T][] Buy bread
    2. [T][] Buy chicken
    3. [D][] Chemistry homework submission (by: 10pm)
    4. [E][] Having dinner with friends (at: 6pm)

####  `done {task number}` - *Marks the corresponding task as done*
    done 3
    This task is done:
    [D][X] Chemistry homework submission (by: 10pm)

####  `delete {task number}` - *Removes the corresponding task*
    delete 4
    I have removed this task:
    [E][] Having dinner with friends (at: 6pm)

####  `find {search filter}` - *Returns the Tasks that match your search filter*
    find buy
    Here are the matching tasks in your list:
    [T][] Buy bread
    [T][] Buy chicken

#### `bye` - *Exits Duke*
    bye
    See you later alligator

### Insertion commands
#### `todo {task description}` - *Adds a todo Task*
    todo Do laundry
    I've added:
    [T][] Do laundry

#### `deadline {task description} /by {task deadline}` - *Adds a Deadline Task*
    deadline Essay submission /by 10pm
    I've added:
    [D][] Essay submission (by: 10pm)

#### `event {task description} /at {task timing}` - *Adds an Event Task*
    event Birthday party /at 7pm
    I've added:
    [E][] Birthday party (at: 7pm)

## Functionalities outside Duke App
#### Viewing your Tasks on the hard drive
Besides booting up Duke and using `list` to view your Tasks, you can opt to view your Tasks on a saved *.txt* file.
1. Locate the folder where you copied the JAR file into
2. Click on `savedData.txt`
3. Latest version of your Tasks will be shown, **provided you exited Duke using `bye`.**

