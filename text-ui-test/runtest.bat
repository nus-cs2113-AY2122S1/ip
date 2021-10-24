@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist data\duke.txt del data\duke.txt

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\*.java ..\src\main\java\duke\tasks\*.java ..\src\main\java\duke\exceptions\*.java ..\src\main\java\duke\commands\*.java ..\src\main\java\duke\parser\*.java ..\src\main\java\duke\ui\*.java ..\src\main\java\duke\storage\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin duke.Duke < input.txt > ACTUAL.TXT
java -classpath ..\bin duke.Duke < input.txt > ACTUAL_2.TXT
java -classpath ..\bin duke.Duke < input_2.txt > ACTUAL_3.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
FC ACTUAL_2.TXT EXPECTED_2.TXT
FC ACTUAL_3.TXT EXPECTED_3.TXT






