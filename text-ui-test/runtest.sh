#!/usr/bin/env bash

# create bin directory if it doesn't exist
[ ! -d "../bin" ] &&  mkdir ../bin

# delete output from previous run
# [ -e "./actual/" ] && rm ACTUAL.TXT

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Duke < inputs/exceptions-test.txt > actual/exceptions.actual
java -classpath ../bin Duke < inputs/nums-test.txt > actual/nums.actual

# compare the output to the expected output
diff expected/exceptions.expected actual/exceptions.actual && echo "Passed" || echo "Failed"
diff expected/nums.expected actual/nums.actual && echo "Passed" || echo "Failed"


# if [ $? -eq 0 ]
# then
#     echo "Test result: PASSED"
#     exit 0
# else
#     echo "Test result: FAILED"
#     exit 1
# fi
