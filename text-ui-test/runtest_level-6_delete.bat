@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL_LEVEL-6_DELETE.TXT del ACTUAL_LEVEL-6_DELETE.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin duke.Duke < input_level-6_delete.txt > ACTUAL_LEVEL-6_DELETE.TXT

REM compare the output to the expected output
FC ACTUAL_LEVEL-6_DELETE.TXT EXPECTED_LEVEL-6_DELETE.TXT
