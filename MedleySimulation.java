//Banele Magobiyane
// I've added a background picture of a swimming pool and a countDownLatch that makes the start button intitate simulation

// MedleySimulation main class: initializes and starts the simulation of a medley relay race. 
// It sets up the GUI, including a start button with a CountDownLatch to synchronize the start of the race with user input. 
// This  class creates and manages threads for the stadium view, results display, and individual swim teams.

package medleySimulation;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;


public class MedleySimulation {
	static final int numTeams=10;
	
   	static int frameX=300; //frame width
	static int frameY=600;  //frame height
	static int yLimit=400;
	static int max=5;

	static int gridX=50 ; //number of x grid points
	static int gridY=120; //number of y grid points 
		
	static SwimTeam[] teams; // array for team threads
	static PeopleLocation [] peopleLocations;  //array to keep track of where people are
	static StadiumView stadiumView; //threaded panel to display stadium
	static StadiumGrid stadiumGrid; // stadium on a discrete grid
	
	static FinishCounter finishLine; //records who won
	static CounterDisplay counterDisplay ; //threaded display of counter
   
   // The latch is initialized to 1, it will ensure swim teams only start after the user presses start button
   static CountDownLatch latch = new CountDownLatch(1); 
	

	//Method to setup all the elements of the GUI
	public static void setupGUI(int frameX,int frameY) {
		// Frame initialize and dimensions
    	JFrame frame = new JFrame("Swim medley relay animation"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameX, frameY);
    	
      JPanel g = new JPanel(){
      @Override 
      protected void paintComponent(Graphics g){
         // Call the original paintComponent to ensure background is painted properly
         super.paintComponent(g);
         // Load the image and get the image object
         ImageIcon image = new ImageIcon(getClass().getResource("RelayStart.jpg"));
         Image img = image.getImage();
         // Draw the image scaled to fit the panel
         g.drawImage(img,0,0,getWidth(), getHeight(), this);
         }
      };
         
       g.setLayout(new BoxLayout(g, BoxLayout.Y_AXIS)); 
       g.setSize(frameX,frameY);
 	    
		stadiumView = new StadiumView(peopleLocations, stadiumGrid);
		stadiumView.setSize(frameX,frameY);
	    g.add(stadiumView);
	    
	    //add text labels to the panel - this can be extended
	    JPanel txt = new JPanel();
	    txt.setLayout(new BoxLayout(txt, BoxLayout.LINE_AXIS));
	    JLabel winner =new JLabel("");
	    txt.add(winner);
	    g.add(txt);
	    
	    counterDisplay = new CounterDisplay(winner,finishLine);      //thread to update score
	    
	    //Add start and exit buttons
	    JPanel b = new JPanel();
        b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS)); 
        
        JButton startB = new JButton("Start");
		// add the listener to the jbutton to handle the "pressed" event
		startB.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)  {
			 //This is executed when the start button is pressed, it decrements the countDownLatch or releases the latch and swimmers can start 	  
                 latch.countDown();
		    }
		   });
	
		JButton endB = new JButton("Quit");
				// add the listener to the jbutton to handle the "pressed" event
				endB.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			    	  System.exit(0);
			      }
			    });

		b.add(startB);
		b.add(endB);
		g.add(b);
    	
      	frame.setLocationRelativeTo(null);  // Center window on screen.
      	frame.add(g); //add contents to window
        frame.setContentPane(g);     
        frame.setVisible(true);	
        
	}
	
	
   //Main method - starts it all
	public static void main(String[] args) throws InterruptedException {
	   	
	    finishLine = new FinishCounter(); //counters for people inside and outside club
	 
		stadiumGrid = new StadiumGrid(gridX, gridY, numTeams,finishLine); //setup stadium with size     
		SwimTeam.stadium = stadiumGrid; //grid shared with class
		Swimmer.stadium = stadiumGrid; //grid shared with class
	    peopleLocations = new PeopleLocation[numTeams*SwimTeam.sizeOfTeam]; //four swimmers per team
		teams = new SwimTeam[numTeams];
		for (int i=0;i<numTeams;i++) {
        	teams[i]=new SwimTeam(i, finishLine, peopleLocations);        	
		}
		setupGUI(frameX, frameY);  //Start Panel thread - for drawing animation
		
		//start viewer thread
		Thread view = new Thread(stadiumView); 
		view.start();
       
      	//Start counter thread - for updating results
      	Thread results = new Thread(counterDisplay);  
      	results.start();
      	
         // Wait for the Start button to be pressed
         latch.await();
         
      	//start teams, which start swimmers.
      	for (int i=0;i<numTeams;i++) {
			teams[i].start();
		}
	}
}
