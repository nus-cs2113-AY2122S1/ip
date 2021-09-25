# Project Sassy Duke

Project Duke is a educational software project designed to take the user through the steps of building a small software incrementally, while applying as many Java and SE techniques as possible along the way.

The project aims to build a product named Duke, a Personal Assistant Chatbot that helps a person to keep track of various things. The name Duke was chosen as a placeholder name, in honor of Duke, the Java Mascot.

I have modified Duke to have a sassy attitude. Try not to make any errors as he may be merciless in his teasing!

Here is a table containing the command words and a brief description:

*Psssstttttt click commands to skip sections!*

Command | Brief description of command
------------ | -------------
[**todo**](#todo-command) | adds a task to be completed
[**event**](#event-command) | adds an event + location 
[**deadline**](#deadline-command) | adds a deadline + date + time
[**list**](#list-command) | prints the list of all commitments
[**find**](#find-command) | filters commitments by a keyword and prints them
[**delete**](#delete-command) | remove a task from the list
[**done**](#done-command) | mark a task as completed
[**bye**](#close-duke-command) | close and bids Duke *Au Revoir*

On startup, the user will be greeted by the following message:
![Imgur](https://i.imgur.com/tW87ITA.jpg)

Below contains screenshots of the commands Duke can process and the format of the commands in the box:

# Load saved tasklist from Duke.txt :
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
  deadline "description" /by "YYYY-MM-DD" "HH:MM"
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

Have fun playing!!
