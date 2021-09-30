# THE MARAUDER'S MAP User Guide 

## About 
> "Messrs Moony, Wormtail, Padfoot, and Prongs
> Purveyors of Aids to Magical Mischief-Makers
> are proud to present
> **THE MARAUDER'S MAP**"

**The Marauder's Map** is a CLI (Command Line Interface) task manager that (somewhat) assists you in manageing your tasks. 
Follow this User Guide to learn the map's charms. 

## Quick Start 
To set up Java Environment: Install the lastest Version of Java 11.
1. Download the latest release of THE MARAUDER'S MAP [here](https://github.com/Isabella-L/ip/releases/latest).
2. Navigate to the folder that contains the Jar file.
3. Run `java -jar -ip.jar`
4. If there is no error, the map should ask you for a Revealing Charm: 
![image](https://user-images.githubusercontent.com/69776265/135422052-4c6a9809-99f0-4b9e-a433-80e8059f0ff3.png)
5. Once the correct charm is entered, the map is at your service. 
![image](https://user-images.githubusercontent.com/69776265/135422439-767bd5b1-7678-456a-b03e-24357b4a5479.png)
   Or it will brutally insults its nemesis if you are a muggle(i.e. dont know the charm) :
   ![image](https://user-images.githubusercontent.com/69776265/135422175-5089dd3a-3c63-4827-8902-6355d90d26bf.png)

   

## Features 
### Command Summary 
Command | Format | Feature
------------ | ------------- | -------------
todo | `todo <description>` | Adds a todo task 
deadline | `deadline <description> /by [due time]` | Adds a deadline task
event | `event <description> /at <event duration>` | Adds an event task
list | `list` | Lists all current tasks
done | `done <item id>` | Marks a task as done
delete | `delete <item id>` | Deletes a task

### Adding Tasks 
There are 3 types of tasks you can add: todo, event, deadline.
1. todo - tasks that you need to do, requires task description
    - Format : `todo <task description>`
    - E.g. `todo collect asphodel, dittany and wiggentree bark`
    ![image](https://user-images.githubusercontent.com/69776265/135445227-f7b2c8f2-699c-46aa-905d-88a940a9e3c0.png)
2. deadline - tasks that you need to attend, requires description and duration of event
    - Format : `deadline <task description> /by <due time> `
    - E.g. : `deadline brew wiggenweld potion /by next Potion lesson`
    ![image](https://user-images.githubusercontent.com/69776265/135445345-6448ddef-df77-4519-8583-4a19763797c8.png)
3. event - tasks that you need to finish at certain time, requires description and due time
    - Format : `event <task description> /at <duration>
    - E.g. : `event sky observation /at wednesday 10 - 12pm`
    ![image](https://user-images.githubusercontent.com/69776265/135445419-7e8d46e8-ffaa-41b4-9694-c0ec77e16c69.png)

### Listing Tasks
You can list out all the tasks you have stored through the `list` command.
- E.g.  `list`
![image](https://user-images.githubusercontent.com/69776265/135445503-4ae2f355-c0b6-4660-9565-7a751f013d86.png)

### Marking Tasks as done
You can mark one task as done each time. 
- Format : `done <task id>`
- E.g. `done 2`
![image](https://user-images.githubusercontent.com/69776265/135445587-e9ed1b79-feb0-4f90-a886-89a68e741b39.png)

### Deleting Tasks 
You can delete one task at a time.
- Format ：`delete <task id>`
- E.g. `delete 3`
![image](https://user-images.githubusercontent.com/69776265/135446872-7ee625f0-bc68-4f67-bb37-973dc03d22bc.png)

### Finding tasks 
You can search for tasks that contains a specific phrase.
- Format : `find <search phrase>`
- E.g. `find potion`
![image](https://user-images.githubusercontent.com/69776265/135445664-1e08dc80-f10c-4ba2-ac4b-01307fafa1bd.png)

### Saving tasks 
The map automatically saves task data into the file everytime the list is modified. Your tasks will still be retained after you exit the program. In the same folder as your JAR file, there will be a data folder which contains 'duke.txt' that stores all your tasks.

### loading data
Everytime the map activates, it will look for the 'duke.txt' file and load the tasks inside. If there is no such file, the program will automatically creates one. 
![image](https://user-images.githubusercontent.com/69776265/135446784-00a84ae5-216a-4d9e-8dbc-a067bc7a8ff6.png)

### Exit 
You can exit the Map through the `bye` command 
- E.g. `bye`
![image](https://user-images.githubusercontent.com/69776265/135445739-47d5e54d-812a-4797-900b-bec5ae2eb8b0.png)


### Acknowledgement
S.LU

_Powered By: The Homonculous Charm_
