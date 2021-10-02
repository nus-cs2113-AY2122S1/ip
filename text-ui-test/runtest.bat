@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -jar %~dp0\..\bin\Duke.jar %-noload < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
