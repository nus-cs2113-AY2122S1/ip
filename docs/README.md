# **Duke User Guide**

Duke is an application written in Java used for managing tasks, optimized for use via a Command Line Interface (CLI). It can be used to efficiently add, delete and search for relevant tasks, be it to-do tasks, deadlines or events.

</br>

## **Features**

<hr>
<br>

## **Adding/ Deleting Tasks**

<hr>

There are three different type of tasks that can be added: Todo, Deadline and Event. Each type of task has a different format to follow.

</br>

## Adding Todo:  ```todo```

Adds a todo task.

</br>

Format: ```todo [DESCRIPTION]```

Examples:

* ```todo go to NUS```

* ```todo CS2113 assignment```

</br>

For the first example, the following output will be shown:

```Got it. I've added this task: ```

```[T][ ] go to NUS```

```Now you have 1 tasks in the list```

</br>
</br>

## Adding Deadline:  ```deadline```

Adds a deadline task with a due date.

</br>

Format: ```deadline [DESCRIPTION] /by [DUEDATE]```

Examples:

* ```deadline finish project /by Friday 10pm```

* ```deadline buy birthday present /by Saturday```

</br>

For the first example, the following output will be shown:

```Got it. I've added this task: ```

```[D][ ] finish project  (by: Friday 10pm)```

```Now you have 1 tasks in the list```

</br>
</br>

## Adding Event:  ```event```

Adds a event task with a due location/date.

</br>

Format: ```event [DESCRIPTION] /at [DUELOCATION]```

Examples:

* ```event birthday party /at Bedok, 10pm```

* ```event friend gathering /at Bugis```

</br>

For the first example, the following output will be shown:

```Got it. I've added this task: ```

```[E][ ] birthday party  (at: Bedok, 10pm)```

```Now you have 1 tasks in the list```

</br>

## Deleting Task:  ```delete```

Deletes a task based on its position on the list.

</br>

Format: ```delete [INDEX]]```

Examples:

* ```delete 1``` deletes the first task in the list.


</br>

<hr>
</br>

## **Querying Tasks**

<hr>

The user is also able to view all tasks in a list, as well as find specific task(s) based on keywords.

</br>

## Listing all tasks: ```list```

Shows a list of all available tasks.

</br>

Format: ```list```

An output of this format will be shown:

```Here are the tasks in your list:```

```1. [T][ ] go to NUS```

```2. [D][ ] finish project  (by: Friday 10pm)```

```3. [E][ ] birthday party  (at: Bedok, 10pm)```

</br>

## Finding task: ```find```

Finds task(s) with the matching keyword(s).

</br>

Format: ```find [KEYWORD]```

Examples:

* ```find NUS``` finds tasks with 'NUS' in its description.

An output of this format will be shown:

```Here are the matching tasks in your list:```

```1. [T][ ] go to NUS```

</br>

## Mark task as done: ```done```

Marks a task as done, based on its position in the list.

</br>

Format: ```done [INDEX]```

Examples:

* ```done 1``` marks the first task in the list as done.

An output of this format will be shown:

```Nice! I've marked this task as done:```

```1. [T][X] go to NUS```

</br>
<hr>

## Exit application: ```exit```

Exits the application.

</br>

Format: ```bye```

The following output will be shown:

```Bye. Hope to see you again soon!```

</br>
<hr>

## **Command Summary**

Command | Format
------------ | -------------
```todo``` | ```todo [DESCRIPTION]```
```deadline``` | ```deadline [DESCRIPTION] /by [DUEDATE]```
```event``` | ```event [DESCRIPTION] /at [DUELOCATION]```
```delete``` | ```delete [INDEX]```
```list``` | ```list```
```find``` | ```find [KEYWORD]```
```done``` | ```done [INDEX]```
```bye``` | ```bye```

