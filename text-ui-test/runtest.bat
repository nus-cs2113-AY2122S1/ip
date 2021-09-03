@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
REM the ..\bin is where it will compile to.
javac  -cp ..\src\main\java\tan -Xlint:none -d ..\bin ..\src\main\java\tan\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
REM First FIle path is the compiled Java path following the Package name.
REM java -classpath C:\Users\Pryo\Desktop\CS2113\bin tan/Duke < input.txt > ACTUAL.TXT
java -classpath ..\bin tan/Duke < input.txt > ACTUAL.TXT
REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
