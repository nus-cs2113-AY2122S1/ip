@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL_ERROR_HANDLING.TXT del ACTUAL_ERROR_HANDLING.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input_error_handling.txt > ACTUAL_ERROR_HANDLING.TXT

REM compare the output to the expected output
FC ACTUAL_ERROR_HANDLING.TXT EXPECTED_ERROR_HANDLING.TXT
