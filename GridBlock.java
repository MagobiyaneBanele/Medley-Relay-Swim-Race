//Banele Magobiyane
// I changed variables to Atomic, synchronized methods to ensure that only one thread at a time owns a GridBlock

//This class represent a block in the grid

package medleySimulation;
import java.util.concurrent.atomic.*;

public class GridBlock {
   
   // Atomic variable to track which thread occupies the block
	private AtomicInteger isOccupied = new AtomicInteger(-1); 
   
	// Atomic variable to check if this is a starting block
	private final AtomicBoolean isStart = new AtomicBoolean();  
   
   // the coordinate of the block.
	private int [] coords; 
   
	// Constructor for creating a GridBlock with a starting block indicator
	GridBlock(boolean startBlock) throws InterruptedException {
		isStart.set(startBlock);

	}
	// Constructor for creating a GridBlock with coordinates and a starting block indicator
	GridBlock(int x, int y, boolean startBlock) throws InterruptedException {
		this(startBlock);
		coords = new int [] {x,y};
	}
	
   // Get the X coordinate of the block
	public   int getX() {return coords[0];}  
	
	public   int getY() {return coords[1];}
	
	
	
	// Attempt to occupy the block with the given thread ID
   // Returns true if the block was successfully occupied, false otherwise
	public synchronized boolean get(int threadID) throws InterruptedException {
      
      //thread Already in this block
		if (isOccupied.get()==threadID) return true; 
      
      //space is occupied
		if (isOccupied.get()>=0) return false; 
      
      //set ID to thread that had block
		isOccupied.set(threadID);  
		return true;
	}
		
	
	// Release the block, marking it as free
   // Notifies all waiting threads
	public synchronized void release() {
		isOccupied.set(-1);
       notifyAll();
	}
	

	// Check if the block is currently occupied
   	public  boolean occupied() {
		return isOccupied.get() != -1;
	}
	
	
	// Check if the block is currently occupied
	public synchronized boolean isStart() {
		return isStart.get();	
	}

}
