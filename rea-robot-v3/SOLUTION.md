Toy Robot Simulator
===================

Problem Statement
-----------------
Simulate the actions of a toy robot within a specified boundary and with a designated set of commands. Please refer PROGRAM.md for details.

Approach
--------
There are three important facets to the problem 
- Processing of inputs to generate the commands
	- Recognizing the input source - file or stdin
	- Validating the input lines
- Applying the commands
	- Validate the command with respect to the current position
	- Process
- Presenting the output of command executions
	- Recognize the output source - stdout is acceptable as per requirement  
	- Record every move by the robot (may be a simple no-op call)
	- Output as warranted i.e. REPORT command
	
Proposed solution
-----------------
- Establish the boundaries of the field of movement of Robot
- Differentiate the types of commands, simple commands or faceted commands.
	- Simple command - Can be applied without any parameters
	- Faceted command - Can not exist on its own, need an additional facet to proceed
- Use Factory pattern to create the Commands from input
- Use double-despatch with command pattern to execute the commands
