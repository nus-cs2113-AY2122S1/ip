# User Guide
![Image of Duke](https://www.vhv.rs/dpng/d/47-477379_java-duke-png-transparent-png.png)

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
  - [`done`](#done)
  - [`delete`](#delete)
  - [`find`](#find)
  - [`bye`](#bye)
- [Command Summary](#command-summary)
- [FAQ](#faq)
  - [Links to More Chatbot Assistants](#links-to-more-chatbot-assistants)
- [Coming Soon](#coming-soon)

## Setup
To get _Tired_ up and running on your computer, follow the steps below:
1. Download and install [Java 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
on your computer
2. Download _Tired_'s **JAR file** by clicking [here](). 
(As the creator I vouch for its safety, so ignore the warning message, :D)
3. The **JAR file** will appear in your **Downloads** folder. Open **Command Prompt**.
One method is to press the Windows  key, search  for "cmd", and click the first option.
4. In Command Prompt, type `cd Downloads` and click enter. Type `java -jar ip.jar` and click enter again.
5. _Tired_ is up and running! Enjoy the app!

## Quick Start
_Tired_ will greet you with reluctance upon startup.
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
This is how it should look on a window.
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
(Just make sure you exit the app with the command `bye` to save your progress!)

### I'm What You Call a Minimalist.
The UI only has the essentials. It's so bare bone and minimalist it leaves Apple quaking and knees shaking.
(They've been real quiet since the JAR file was released.)

## Usage

### `help`
**Command**: `help <optional_text>`

Shows a list with brief explanations for all possible commands:
````
    ┌────────────────────────────────────────────────────────────────────┐
    │ Okay. For the last time, don't make me remind you again.           │
    │                                                                    │
    │                todo <name>: Add a todo task to the list.           │
    │ deadline <name> /by <time>: Add a task with specified deadline.    │
    │    event <name> /at <time>: Add an event with the specified time.  │
    │         done <task_number>: Mark a task as done.                   │
    │       delete <task_number>: Remove a task from the list.           │
    │               find <query>: Search for text in the list.           │
    │                                                                    │
    │ Still having trouble? *sigh*                                       │
    │ Head over to https://kahhe.github.io/ip/ for the User Guide        │
    └────────────────────────────────────────────────────────────────────┘

````
Type any extra text  _Tired_ will get cranky: 
````
todo text to include funy extra text
````
### `todo`
**Command**: `todo <TASK_NAME>`

Adds a `todo` tasks with name `<TASK_NAME>` to the list:

````
show expected outcome with example command
````

### `deadline`
**Command**: `deadline <TASK_NAME> /by <>`


### `event`
**Command**: `event <TASK_NAME> /at <>`

### `done`
**Command**: `done <TASK_NUMBER>`

Marks the task with index `<TASK_NUMBER>` on the list as done.

For example, 

This is the current list:
````

````
To mark the 3rd tasks as done:
````

````

###`delete`
**Command**: `delete <TASK_NUMBER>`

Deletes the task with index `<TASK_NUMBER>` on the list. (Be careful, it'll be gone forever!)

For example,

This is the current list:
````

````
To delete the 2rd task:
````

````

###`find`
**Command**: `find <QUERY>`

Searches the `<TASK_NAME>` of all tasks and returns a list of all matching results.

For example,

This is the current list:
````

````
To look for `dingleberries`:
````

````

### `bye`
**Command**: `bye`

Emotional parting and saving tasks begins:
````

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
````


````

## FAQ
Q: _Tired_ really hurt my feelings. How can I change his attitude?

A: “That which does not kill us, makes us stronger.” — Friedrich Nietzsche
<br> Alternatively, you can check out my friends' chatbot assistants (they're friendlier.)

### Links to More Chatbot Assistants
[Rimura JAR file]()

## Coming Soon
Kidding! Nothing in the pipeline.

[Sayonara CS2113T iP!](https://www.youtube.com/watch?v=dQw4w9WgXcQ)
