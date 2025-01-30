 //Banele Magobiyane
 /*
 I changed variables to Atomic to ensure that there's thread safety and consistency when accessing or modifying shared properties.
 This prevents race conditions and ensures accurate updates and retrievals of the person's status and location across multiple threads.
 Specifically, it manages concurrent access to the inStadium, arrived, and location properties, ensuring that updates and checks are done
 in a controlled manner and that all threads observe a consistent state.
*/

package medleySimulation;

import java.awt.Color;
import java.util.concurrent.atomic.*;
import java.util.Random;


public class PeopleLocation  { // this is made a separate class so don't have to access thread
	
	private final int ID; //each person has an ID
	private Color myColor; //colour of the person
	
	private AtomicBoolean inStadium; //are they here?
	private AtomicBoolean arrived; //have they arrived at the event?
	private GridBlock location; //which GridBlock are they on?
	
	//constructor
	PeopleLocation(int ID , Color c) {
      Random rand = new Random();
		myColor = c;
		inStadium = new AtomicBoolean(false); //not in pool
		arrived = new AtomicBoolean(false); //have not arrived
		this.ID=ID;
	}
	
	//setter
	public  void setInStadium(boolean in) {
		this.inStadium.set(in);
	}
	
	//getter and setter
	public boolean getArrived() {
		return arrived.get();
	}
	public void setArrived() {
		this.arrived.set(true);;
	}

//getter and setter
	public GridBlock getLocation() {
		return location;
	}
	public  void setLocation(GridBlock location) {
		this.location = location;
	}

	//getter
	public  int getX() { return location.getX();}	
	
	//getter
	public  int getY() {	return location.getY();	}
	
	//getter
	public  int getID() {	return ID;	}

	//getter
	public  synchronized boolean inPool() {
		return inStadium.get();
	}
	//getter and setter
	public   Color getColor() { return myColor; }
	public  void setColor(Color myColor) { this.myColor= myColor; }
}