# Security-in-IoT-Devices-using-blockchain

The program is made using Java version 15.

<hr>

Following are the commands to build and run the program.

To build the program: javac BlockchainforIOT.java  

To run the program: java BlockchainforIOT --initialize

<hr>

There are 7 devices initialized for the user to test.

Once the program is running it asks the user to enter arguments.

Arguments below: 

--printDevicesAndData or --d : Prints all the chained IoT Devices and the information in it.<br>
--printAllDeviceName --n : Prints name of each device in the chain.<br>
--removeDevice or --r : Removes the devices the first occurance of device by name if it exist.<br>
--addDevice --a : Adds a new devices at the end of chain with provided name and data.<br>
--checkValid --v : Checks if the chain is valid by calculating and comparing hashes for each device.<br>
--exit --e : Exits the Program.<br><br>

If the argument is not valid it displays Invalid Argument.
