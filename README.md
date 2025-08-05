# Medley-Relay-Swim-Race


The objective is to correct and enhance the Java code to simulate a 4x100 medley relay race with proper synchronization, ensuring that the simulation follows the correct sequence of events in the race.

Class-Level Changes and Enhancements

1. MedleySimulation
- Introduced a CountDownLatch to control when the race begins. The race only starts when the user presses the "Start" button, ensuring that no swimmer starts before the race is supposed to begin.

2. GridBlock
- Ensured that only one thread can occupy or release a grid block at a time using synchronized methods like get and release.

3. PeopleLocation
- Has atomicBoolean variables to manage shared properties like a person’s status or location. This ensures that race conditions are avoided when threads interact with or modify these shared properties.

4. StadiumGrid
- Contains startLatch that controls when the race can start and readyLatch coordinates the readiness of all threads.

5. Swimmer
- Ensures that swimmers start in the correct sequence and that no swimmer can proceed before the previous swimmer has completed their leg. This guarantees proper team coordination.

7. SwimTeam
- Ensures that swimmers start their activities in the correct order.

Acknowledgements
•	Special thanks to Professor Michelle Kuttel for their guidance and support throughout this project.


