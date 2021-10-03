# Duke User Guide

Duke is a CLI-based application with similar functionalities to TodoList apps.

## Setting up and running Duke
1. Ensure you have java installed on your operating system. You can download Java from https://www.java.com/en/download/manual.jsp.
2. You can download Duke's jarfile from https://github.com/Rakesh12000/ip/releases/download/v2.0/ip.jar. It is best to keep it in an easily accessible directory. E.g. C:\Users\[UserName]\Desktop\jarfile
3. Run command prompt and navigate to your jarfile directory using cd. E.g. "cd C:\Users\[UserName]\Desktop\jarfile".
4. Type command "java -jar ip.jar". This will run Duke.
5. Duke will prompt you to type in a directory where you can save the data file. Duke will then handle the creation of a text file 'duke.txt' in this folder to save your tasks.

## Duke commands
1. Adding Tasks
   1. Adding Todo Task :      **"todo [task description]"**
   1. Adding Deadline Task :  **"deadline [task description /by yyyy-mm-dd HHMM]"**
   1. Adding Event Task :     **"event [task description /at date/place]"**
1. Viewing Tasks :            **"list"**
1. Finding Certain Tasks :    **"find [keyword]"**
1. Marking Tasks As Done :    **"done [task number]"**
1. Removing Tasks :           **"delete [task number]"**
1. Exiting Program:           **"bye"**
