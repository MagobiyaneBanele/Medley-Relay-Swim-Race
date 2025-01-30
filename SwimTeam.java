//Banele Magobiyane
// I have added the following changes on the run method:
// In the run method, starts all swimmer threads and waits for each swimmer's notification to proceed, ensuring they start in sequence.

// The SwimTeam class represents a team of four swimmers and manages their coordination.
// Initializes each swimmer with a unique ID, color, speed, and stroke type, assigning them to starting blocks in the stadium.

package medleySimulation;

import medleySimulation.Swimmer.SwimStroke;

public class SwimTeam extends Thread {
	
	public static StadiumGrid stadium; //shared 
	private Swimmer [] swimmers;
	private int teamNo; //team number 

	
	public static final int sizeOfTeam=4;
	
	SwimTeam( int ID, FinishCounter finish,PeopleLocation [] locArr ) {
		this.teamNo=ID;
		
		swimmers= new Swimmer[sizeOfTeam];
	    SwimStroke[] strokes = SwimStroke.values();  // Get all enum constants
		stadium.returnStartingBlock(ID);

		for(int i=teamNo*sizeOfTeam,s=0;i<((teamNo+1)*sizeOfTeam); i++,s++) { //initialise swimmers in team
			locArr[i]= new PeopleLocation(i,strokes[s].getColour());
	      	int speed=(int)(Math.random() * (3)+30); //range of speeds 
			swimmers[s] = new Swimmer(i,teamNo,locArr[i],finish,speed,strokes[s]); //hardcoded speed for now
		}
	}
	
	
	public void run() {
		try {	
          // Loop through each swimmer in the team
			for(int s=0;s<sizeOfTeam; s++) { //start swimmer threads
            // Synchronize on each swimmer to ensure order
            synchronized(swimmers[s]){ 
				swimmers[s].start();
            // Make the current swimmer wait for the previous one to start
            swimmers[s].wait();
				
			}}
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	

