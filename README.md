# Project Duke

Project Duke is a educational software project designed to take you through the steps of building a small software incrementally, while applying as many Java and SE techniques as possible along the way.

The project aims to build a product named Duke, a Personal Assistant Chatbot that helps a person to keep track of various things. The name Duke was chosen as a placeholder name, in honor of Duke, the Java Mascot. You may give it any other name and personality you wish.

Here is a table containing the command words and a brief description:

Command | description
------------ | -------------
[todo](##-todo-command) | adds a task to be completed
[event](##event-command) | adds an event + location 
[deadline](##deadline-command) | adds a deadline + date + time
[list](##list-command) | allows user to see a list of all commitments
[find](##find-command) | allows user to filter commitments by a keyword
[delete](##delete-command) | allows user to remove a task from the list
[done](##done-command) | mark a task as complete
[bye](##close-duke-command) | close Duke

On startup, the user will be greeted by the following message:
![ScreenShot](\welcome message.jpg)

Below contains screenshots of the commands Duke can process and the format of the command in the box:

##Load saved tasklist from Duke.txt :
   ```
  list
   ```
![ScreenShot](\list.jpg)
## Todo command

   ```
  todo "description"
   ```
![ScreenShot](\todo.jpg)
##Event command

   ```
  event "description" /at "location"
   ```
![ScreenShot](\event.jpg)
##Deadline command

   ```
  dealine "description" /by "YYYY-MM-DD" "HH:MM"
   ```
![ScreenShot](\deadline.jpg)
##Done command

   ```
  done "index_number"
   ```
![ScreenShot](\done.jpg)
##Delete command

   ```
 delete "index_number"
   ```
![ScreenShot](\delete.jpg)
##List command

   ```
  list
   ```

![ScreenShot](\list2.jpg)
##Find command

   ```
  find "index_number"
   ```
![ScreenShot](\find.jpg)
##Close DUKE command

   ```
 bye
   ```
![ScreenShot](\bye.jpg)

Have fun trialing!!