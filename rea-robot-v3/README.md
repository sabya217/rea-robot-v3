# REA Group Toy robot simulator

Simulator to represent a toy robot moving on a bounded surface area.

Please refer `PROBLEM.md` for detailed description of the problem statement.
Please refer `SOLUTION.md` for explanation of design and approach.

## Instructions

Please run the toy robot simulator as follows.

java -jar rea-robot-v3.jar -input <inputFileName> [-output <outputFileName>]

 - Each line in the input file should respond to an acceptable command.
 - Output file option is optional, it helps save the output report to a file. If the output file does not exist, it will be created, else, its contents will be replaced with the new contents.

### Build

To build the project, just run `mvn clean package`.
The built JAR along with sample inputs can be found in the `./target/` directory.

