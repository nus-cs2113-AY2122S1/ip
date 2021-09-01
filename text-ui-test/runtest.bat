@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp D:\NUS\Sem3\CS2113\ip\src\main\java -Xlint:none -d D:\NUS\Sem3\CS2113\jdk11.0.12_7\bin D:\NUS\Sem 3\CS2113\ip\src\main\java\Duke.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath D:\NUS\Sem3\CS2113\jdk11.0.12_7\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
