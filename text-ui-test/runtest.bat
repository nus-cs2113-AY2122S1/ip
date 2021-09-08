@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
REM javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\"*.java"
REM C:\Java0\jdk11.0.12_7\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2\lib\idea_rt.jar=1091:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2\bin" -Dfile.encoding=UTF-8 -classpath "C:\Users\tohyi\Desktop\CG1111\cs2113T\ip project\out\production\ip project" Duke
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java ..\src\main\java\handleException\*.java ..\src\main\java\handleTask\*.java ..\src\main\java\handleInput\*.java

IF ERRORLEVEL 1 (
    echo - BUILD FAILURE -
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
