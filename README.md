# Medley-Relay-Swim-Race

Aim and Hypothesis
•	Aim: The objective is to correct and enhance the Java code to simulate a 4x100 medley relay race with proper synchronization, ensuring that the simulation follows the correct sequence of events in the race.
•	Hypothesis: The hypothesis is that by implementing appropriate synchronization mechanisms, the race can be simulated accurately, ensuring correct order and preventing issues like data races.
________________________________________
Class-Level Changes and Enhancements
1. MedleySimulation
•	Enhancement: Added a background picture of a swimming pool to improve the visual aspect of the simulation.
•	Synchronization: Introduced a CountDownLatch to control when the race begins. The race only starts when the user presses the "Start" button, ensuring that no swimmer starts before the race is supposed to begin.
2. GridBlock
•	Enhancement: Modified the class to use atomic variables like AtomicInteger and AtomicBoolean to manage grid blocks safely.
o	AtomicInteger tracks which thread occupies a grid block.
o	AtomicBoolean determines whether a grid block is a starting block.
•	Synchronization: Ensured that only one thread can occupy or release a grid block at a time using synchronized methods like get and release.
3. PeopleLocation
•	Enhancement: Introduced AtomicBoolean variables to manage shared properties like a person’s status or location.
•	Synchronization: This ensures that race conditions are avoided when threads interact with or modify these shared properties.
4. StadiumGrid
•	Enhancement: Introduced two latches (startLatch and readyLatch) to manage synchronization within the simulation.
•	Synchronization:
o	startLatch controls when the race can start.
o	readyLatch coordinates the readiness of all threads.
o	Added getTeamStatus and setTeamStatus methods to monitor and update the status of teams in the stadium.
5. Swimmer
•	Enhancement: Made changes to the run method to synchronize the swimmer's actions.
o	Simulates swimmer arrival, entering the stadium, and moving to the starting block.
o	Uses CountDownLatch to ensure that all swimmers are ready before the race starts.
•	Synchronization: The method ensures that swimmers start in the correct sequence and that no swimmer can proceed before the previous swimmer has completed their leg. This guarantees proper team coordination.
6. SwimTeam
•	Enhancement: Adjusted the run method to start each swimmer thread sequentially and wait for notifications from each swimmer before proceeding. This ensures that swimmers start their activities in the correct order.
________________________________________
New Extensions for Enhancements
CounterDisplay and FinishCounter
•	Enhancement: Modified the classes to display the finishing positions (1st, 2nd, and 3rd) of the swimmers at the end of the race.
________________________________________
Makefile and Testing Issues
•	Testing Issue: Encountered a problem on the SeniorLab PCs where the background image (a feature added) failed to render.
o	Reason: The Java version installed on the SeniorLab PCs does not support the image rendering used in the code.
o	Solution: The simulation works correctly on the user's laptop using different IDEs and command-line interfaces like PowerShell or CMD.
________________________________________
Conclusion
•	Summary of Changes:
o	The modifications focus on enhancing thread management and synchronization to maintain correct race order.
o	The introduction of CountDownLatch and synchronized methods ensures that swimmers start and finish in the correct sequence, reflecting the real-world rules of a relay race.
o	By using synchronized blocks and initializing swimmer attributes properly, the simulation ensures the race proceeds smoothly and correctly.
•	Key Benefits:
o	The program now adheres to synchronization principles, improving thread safety and reliability.
o	The accuracy of the race simulation is enhanced, and the program ensures that swimmers follow the correct order and that the race progresses without issues.

Acknowledgements
•	Special thanks to Professor Michelle Kuttel for their guidance and support throughout this project.


