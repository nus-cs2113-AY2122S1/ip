# User Guide
![Craig the Brute, er I mean Tired](img.png)
<br> _Image Courtesy of [343 Industries](https://www.343industries.com/)_ 

_Tired_ is a **todo** desktop app for **Personal Assistant Chatbot** that helps you to **keep track of various things**.
Optimised for use via a **[Command Line Interface (CLI)](https://en.wikipedia.org/wiki/Command-line_interface)**,
with a dash of **User Interface (UI) design** that keeps it enjoyable to use.
It's **light and easy** to use, without any fancy embellishments that will bag down its performance.

- [Setup](#setup)
- [Quick Start](#quick-start)
- [Features](#features)
    - [So Snarky!](#so-snarky)
    - [Why So Case-Insensitive?](#why-so-case-insensitive)
    - [I'll Never Forget What You Did.](#ill-never-forget-what-you-did)
    - [I'm What You Call a Minimalist.](#im-what-you-call-a-minimalist)
- [Usage](#usage)
  - [`help`](#help)
  - [`todo`](#todo)
  - [`deadline`](#deadline)
  - [`event`](#event)
  - [`list`](#list)
  - [`done`](#done)
  - [`delete`](#delete)
  - [`find`](#find)
  - [`bye`](#bye)
- [Command Summary](#command-summary)
- [FAQ](#faq)
  - [Q1](#q1)
  - [Links to More Chatbot Assistants](#links-to-more-chatbot-assistants)
- [Coming Soon](#coming-soon)

## Setup
To get _Tired_ up and running on your computer, follow the steps below:
1. Download and install [Java 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
on your computer
2. Download _Tired_'s **JAR file** by clicking [here](https://github.com/kahhe/ip/releases/download/A-Release/ip.jar). 
(As the creator I vouch for its safety, so ignore the warning message, :D)
3. The **JAR file** will appear in your **Downloads** folder. Open **Command Prompt**.
One method is to press the Windows  key, search  for "cmd", and click the first option.
4. In Command Prompt, type `cd Downloads` and click enter. Type `java -jar ip.jar` and click enter again.
5. _Tired_ is up and running! Enjoy the app!

## Quick Start
_Tired_ will greet you with reluctance upon startup:
````
    ┌────────────────────────────────────────────────────────────────────┐
    │ Load file: not found.                                              │
    │ Tasks added in this session will be automatically saved upon exit. │
    └────────────────────────────────────────────────────────────────────┘
         ______       __     __                 __
        / ____/___   / /_   / /   ____   _____ / /_
       / / __ / _ \ / __/  / /   / __ \ / ___// __/
      / /_/ //  __// /_   / /___/ /_/ /(__  )/ /_
      \____/ \___/ \__/  /_____/\____//____/ \__/
            ┌─┐┬  ┌─┐┌─┐┌─┐┌─┐┌─┐
            ├─┘│  ├┤ ├─┤└─┐├┤  ┌┘
      o o o ┴  ┴─┘└─┘┴ ┴└─┘└─┘ o
    ┌────────────────────────────────────────────────────────────────────┐
    │ *Sigh* Hi... I'm Tired                                             │
    │ What do you want from me?                                          │
    └────────────────────────────────────────────────────────────────────┘
````
This is how it should look on a window:
![image](https://user-images.githubusercontent.com/69589263/135413895-7868344d-e0fa-436a-9761-1af222adb5be.png)
You may look at [Usage](#usage) to find out what the commmands are, or type `help` and _Tired_ will print you a list for reference.

## Features 

### So Snarky!
_Tired_ is like your co-worker that likes to get off work at 5PM sharp,
does only what he is told and never goes beyond. He gets annoyed when asked to do extra stuff,
but does it anyway because he is secretly good-natured. (so lovable, right?)

### Why So Case-Insensitive?
_Tired_ is case-insensitive! It's flexibility you should come to expect.
Never will you have to be frustrated that your commands are not accepted because they had to be in lower-case. (yuck!)

### I'll Never Forget What You Did.
_Tired_ may seem  like a half-baked app since he works on a CLI, but he can save all the tasks you throw at him.
Log off and come back later at any time. Your tasks will be right where they are when you need them.
<br> (Just make sure you exit the app with the command `bye` to save your progress!)

### I'm What You Call a Minimalist.
_Tired's_ UI only has the essentials. It's so bare bone and minimalist it leaves Apple quaking and  their knees shaking.
<br> (They've been real quiet since the JAR file was released.)

## Usage

### `help`
**Command**: `help <optional_text>`

Shows a list with brief explanations for all possible commands:
````
    ┌────────────────────────────────────────────────────────────────────┐
    │ Okay. For the last time, don't make me remind you again.           │
    │                                                                    │
    │                       list: Shows all tasks currently in the list. │
    │                todo <name>: Add a todo task to the list.           │
    │ deadline <name> /by <time>: Add a task with specified deadline.    │
    │    event <name> /at <time>: Add an event with the specified time.  │
    │         done <task_number>: Mark a task as done.                   │
    │       delete <task_number>: Remove a task from the list.           │
    │               find <query>: Search for text in the list.           │
    │                                                                    │
    │ Still having trouble? *sigh* I have a more detailed explanation.   │
    │ Head over to https://kahhe.github.io/ip/ for the User Guide.       │
    └────────────────────────────────────────────────────────────────────┘
````
Type any extra text  _Tired_ will get cranky: 
````
help please! My brother, he's dying! Get help! Help him!
    ┌────────────────────────────────────────────────────────────────────┐
    │ Okay. For the last time, don't make me remind you again.           │
    │                                                                    │
    │                       list: Shows all tasks currently in the list. │
    │                todo <name>: Add a todo task to the list.           │
    │ deadline <name> /by <time>: Add a task with specified deadline.    │
    │    event <name> /at <time>: Add an event with the specified time.  │
    │         done <task_number>: Mark a task as done.                   │
    │       delete <task_number>: Remove a task from the list.           │
    │               find <query>: Search for text in the list.           │
    │                                                                    │
    │ Still having trouble? *sigh* I have a more detailed explanation.   │
    │ Head over to https://kahhe.github.io/ip/ for the User Guide.       │
    │                                                                    │
    │ Oh and you typed extra garbage after "help":                       │
    │ please! My brother, he's dying! Get help! Help him!
    └────────────────────────────────────────────────────────────────────┘
````

### `todo`
**Command**: `todo <TASK_NAME>`

Adds a `todo` tasks with name `<TASK_NAME>` to the list:

````
todo midterm prep cus it's in 2 days and I haven't started
    ┌────────────────────────────────────────────────────────────────────┐
    │  Fine. Added to your list:
    │    [T][ ] midterm prep cus it's in 2 days and I haven't started
    │  You have 5 pending tasks. tHaT's aWeSoMe!!!!!1!!
    └────────────────────────────────────────────────────────────────────┘
````

### `deadline`
**Command**: <br>`deadline <TASK_NAME> /by <YYYY-MM-DD> /d` <br> for all-day deadlines, indicated by `/d`, 
<br> _or_
`deadline <TASK_NAME> /by <YYYY-MM-DD>T<HH:mm>` <br> for deadlines with a specific time.

Add a `deadline` task with a deadline. Adhere to command above to format date and time nicely. 

For example, 
````
deadline COVID-19 Invasion /by 2019-12-31 /d 
    ┌────────────────────────────────────────────────────────────────────┐
    │  Fine. Added to your list:
    │    [D][ ] COVID-19 Invasion (by: 31 Dec 2019)
    │  You have 4 pending tasks. tHaT's aWeSoMe!!!!!1!!
    └────────────────────────────────────────────────────────────────────┘
````
_or_
````
deadline iP submission /by 2021-10-01T23:59
    ┌────────────────────────────────────────────────────────────────────┐
    │  Fine. Added to your list:
    │    [D][ ] iP submission (by: 1 Oct 2021 23:59)
    │  You have 3 pending tasks. tHaT's aWeSoMe!!!!!1!!
    └────────────────────────────────────────────────────────────────────┘
````

### `event`
**Command**: <br>`event <TASK_NAME> /by <YYYY-MM-DD> /d` <br> for all-day events, indicated by `/d`,
<br> _or_ 
`event <TASK_NAME> /by <YYYY-MM-DD>T<HH:mm>` <br> for events at a specific time.


Add an `event` task with a start time. Adhere to command above to format date and time nicely.

For example,
````
event Java Birthday /at 1996-01-23 /d
    ┌────────────────────────────────────────────────────────────────────┐
    │  Fine. Added to your list:
    │    [E][ ] Java Birthday (at: 23 Jan 1996)
    │  You have 4 pending tasks. tHaT's aWeSoMe!!!!!1!!
    └────────────────────────────────────────────────────────────────────┘
````
_or_
````
event Christmas meetup /at 2021-12-25T08:15
    ┌────────────────────────────────────────────────────────────────────┐
    │  Fine. Added to your list:
    │    [E][ ] Christmas meetup (at: 25 Dec 2021 08:15)
    │  You have 5 pending tasks. tHaT's aWeSoMe!!!!!1!!
    └────────────────────────────────────────────────────────────────────┘
````
### `list`
**Command**: `list`

Shows the list of all tasks:
````
list
    ┌────────────────────────────────────────────────────────────────────┐
    │ Here are your tasks, oRgAnIc InTeLlIgEnCe:
    │ 1.[T][ ] things
    │ 2.[T][X] iP
    │ 3.[D][ ] iP submission (by: 1 Oct 2021 23:59)
    │ 4.[D][ ] COVID-19 Invasion (by: 31 Dec 2019)
    │ 5.[E][ ] Java Birthday (at: 23 Jan 1996)
    │ 6.[E][ ] Christmas meetup (at: 25 Dec 2021 08:15)
    └────────────────────────────────────────────────────────────────────┘
````

### `done`
**Command**: `done <TASK_NUMBER>`

Marks the task with index `<TASK_NUMBER>` on the list as done.

For example, using the [list](#list) above, <br>
to mark the 4th tasks as done:
````
done 4
    ┌────────────────────────────────────────────────────────────────────┐
    │ About time. I've mark that task as done:
    │ [X] COVID-19 Invasion
    └────────────────────────────────────────────────────────────────────┘
````
Resulting list:
````
list
    ┌────────────────────────────────────────────────────────────────────┐
    │ Here are your tasks, oRgAnIc InTeLlIgEnCe:
    │ 1.[T][ ] things
    │ 2.[T][X] iP
    │ 3.[D][ ] iP submission (by: 1 Oct 2021 23:59)
    │ 4.[D][X] COVID-19 Invasion (by: 31 Dec 2019)
    │ 5.[E][ ] Java Birthday (at: 23 Jan 1996)
    │ 6.[E][ ] Christmas meetup (at: 25 Dec 2021 08:15)
    └────────────────────────────────────────────────────────────────────┘
````

### `delete`
**Command**: `delete <TASK_NUMBER>`

Deletes the task with index `<TASK_NUMBER>` on the list. (Be careful, it'll be gone forever!)

For example, using the [list](#list) above, <br>
to delete the 3rd tasks:
````
delete 3
    ┌────────────────────────────────────────────────────────────────────┐
    │ Lazy eh? Gotcha fam, removed the task:
    │ [ ] iP submission
    │ You left 5 tasks in the list.
    └────────────────────────────────────────────────────────────────────┘
````
Resulting list:
````
list
    ┌────────────────────────────────────────────────────────────────────┐
    │ Here are your tasks, oRgAnIc InTeLlIgEnCe:
    │ 1.[T][ ] things
    │ 2.[T][X] iP
    │ 3.[D][X] COVID-19 Invasion (by: 31 Dec 2019)
    │ 4.[E][ ] Java Birthday (at: 23 Jan 1996)
    │ 5.[E][ ] Christmas meetup (at: 25 Dec 2021 08:15)
    └────────────────────────────────────────────────────────────────────┘
````

### `find`
**Command**: `find <QUERY>`

Searches the `<TASK_NAME>` of all tasks and returns a list of all matching results.

For example, <br>
This is the current list:
````
list
    ┌────────────────────────────────────────────────────────────────────┐
    │ Here are your tasks, oRgAnIc InTeLlIgEnCe:
    │ 1.[D][X] COVID-19 Invasion (by: 31 Dec 2019)
    │ 2.[E][ ] Java Birthday (at: 23 Jan 1996)
    │ 3.[E][ ] Christmas meetup (at: 25 Dec 2021 08:15)
    │ 4.[T][ ] big wolf bonanza
    │ 5.[T][X] hokey pokey while eating wolfberries
    │ 6.[E][ ] The Great Wolfberries Sale (at: 15 Oct 2021)
    └────────────────────────────────────────────────────────────────────┘
````
To look for `wolfberries`:
````
find wolfberries
    ┌────────────────────────────────────────────────────────────────────┐
    │ Here are the matching tasks, oRgAnIc InTeLlIgEnCe:
    │ 1.[T][X] hokey pokey while eating wolfberries
    │ 2.[E][ ] The Great Wolfberries Sale (at: 15 Oct 2021)
    └────────────────────────────────────────────────────────────────────┘
````

### `bye`
**Command**: `bye`

Initiates emotional parting (and task saving):
````
bye
    ┌────────────────────────────────────────────────────────────────────┐
    │ "Only in the agony of parting do we look into the depths of love." │
    │  —— George Eliot                                                   │
    │                                                                    │
    │ Ha! As if I care! Goodbye!!                                        │
    └────────────────────────────────────────────────────────────────────┘
    ┌────────────────────────────────────────────────────────────────────┐
    │ Tasks successfully saved to file.                                  │
    └────────────────────────────────────────────────────────────────────┘
````

### Command Summary
````
Do you remember, 21st night of September?
Love was changing the mind of pretenders
While chasing the clouds away
Our hearts were ringing
In the key that our souls were singing
As we danced in the night
Remember
How the stars stole the night away, oh yeah
````

Oops, wrong one. Here's the correct one:

| Action       | Format
| :---         | :---                                                                                                               
| **Help**     | `help`
| **Todo**     | `todo <TASK_NAME>`
| **Deadline** | `deadline <TASK_NAME> /by <YYYY-MM-DD> /d` <br> or `deadline <TASK_NAME> /by <YYYY-MM-DD>T<HH:mm>`                   
| **Event**    | `event <TASK_NAME> /by <YYYY-MM-DD> /d` <br> or `event <TASK_NAME> /by <YYYY-MM-DD>T<HH:mm>`
| **List**     | `list`
| **Done**     | `done <TASK_NUMBER>`
| **Delete**   | `delete <TASK_NUMBER>`
| **Find**     | `find <QUERY>`
| **Exit**     | `bye`

## FAQ
### Q1:
_Tired_ really hurt my feelings. How can I change his attitude?

**A**: “That which does not kill us, makes us stronger.” — Friedrich Nietzsche
<br> Alternatively, you can check out my friends' chatbot assistants (they're friendlier.)

### Links to More Chatbot Assistants
**Friday**: [JAR file](https://github.com/STAung07/ip/releases/download/A-Release/Friday.jar),
[User Guide](https://staung07.github.io/ip/)

**Gudetama**: [JAR file](https://github.com/huien77/ip/releases/download/A-Release/ip.jar),
[User Guide](https://huien77.github.io/ip/)

**Rimuru Tempest**: [JAR file](https://github.com/DJ-Tan/ip/releases/download/A-UserGuide/ip.jar), 
[User Guide](https://dj-tan.github.io/ip/)

**Shima Rin**: [JAR file](https://github.com/tryyang2001/ip/releases/download/A-Release/IP.jar),
[User Guide](https://tryyang2001.github.io/ip/)

## Coming Soon
Kidding! Nothing in the pipeline.

[Sayonara CS2113T iP!](https://www.youtube.com/watch?v=dQw4w9WgXcQ)
