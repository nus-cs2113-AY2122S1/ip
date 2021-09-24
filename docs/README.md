# Project Duke

Project Duke is a educational software project designed to take you through the steps of building a small software incrementally, while applying as many Java and SE techniques as possible along the way.

The project aims to build a product named Duke, a Personal Assistant Chatbot that helps a person to keep track of various things. The name Duke was chosen as a placeholder name, in honor of Duke, the Java Mascot. You may give it any other name and personality you wish.

Here is a table containing the command words and a brief description:

Command | description
------------ | -------------
[todo](#todo-command) | adds a task to be completed
[event](#event-command) | adds an event + location
[deadline](#deadline-command) | adds a deadline + date + time
[list](#list-command) | allows user to see a list of all commitments
[find](#find-command) | allows user to filter commitments by a keyword
[delete](#delete-command) | allows user to remove a task from the list
[done](#done-command) | mark a task as complete
[bye](#close-duke-command) | close Duke

On startup, the user will be greeted by the following message:
![Imgur](https://i.imgur.com/tW87ITA.jpg)

Below contains screenshots of the commands Duke can process and the format of the command in the box:

#Load saved tasklist from Duke.txt :
   ```
  list
   ```
![Imgur](https://i.imgur.com/vdB5yXK.jpg)

# Todo Command

   ```
  todo "description"
   ```
![Imgur](https://i.imgur.com/3NFVn3U.jpg)

# Event Command

   ```
  event "description" /at "location"
   ```
![Imgur](https://i.imgur.com/fcUClHK.jpg)

# Deadline Command

   ```
  dealine "description" /by "YYYY-MM-DD" "HH:MM"
   ```
![Imgur](https://i.imgur.com/xL42jdE.jpg)

# Done Command

   ```
  done "index_number"
   ```
![Imgur](https://i.imgur.com/Hs0F7Ej.jpg)

# Delete Command

   ```
 delete "index_number"
   ```
![Imgur](https://i.imgur.com/1tfECL4.jpg)

# List Command

   ```
  list
   ```

![Imgur](https://i.imgur.com/BOq0AwX.jpg)

# Find Command

   ```
  find "index_number"
   ```
![Imgur](https://i.imgur.com/XcLGGCH.jpg)

# Close DUKE Command

   ```
 bye
   ```
![Imgur](https://i.imgur.com/MJVdEue.jpg)

Have fun trialing!!
