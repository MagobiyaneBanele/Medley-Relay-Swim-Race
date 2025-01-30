//Banele Magobiyane
//This is a modified class for CounterDisplay that adds an additional gui parrt that prints the the 1st, 2nd, 3rd swimmers

package medleySimulation;

import java.awt.Color;
import javax.swing.JLabel;

public class CounterDisplay implements Runnable {
	private FinishCounter results;
	private JLabel win;
		
	CounterDisplay(JLabel w, FinishCounter score) {
        this.win = w;
        this.results = score;
    }
	
	public void run() { 
        while (true) {
        	// Update the display based on the results
        	if (results.isRaceWon()) {
        		win.setForeground(Color.BLUE);
        		StringBuilder resultText = new StringBuilder("<html>Results:<br>");
        		
        		// Show first place
        		if (results.getFirstTeam() != -1) {
        			resultText.append("1st Place: Team ").append(results.getFirstTeam()).append("<br>");
        		}
        		
        		// Show second place
        		if (results.getSecondTeam() != -1) {
        			resultText.append("2nd Place: Team ").append(results.getSecondTeam()).append("<br>");
        		}
        		
        		// Show third place
        		if (results.getThirdTeam() != -1) {
        			resultText.append("3rd Place: Team ").append(results.getThirdTeam());
        		}
        		
        		resultText.append("</html>");
        		win.setText(resultText.toString());
        	} else {
        		win.setForeground(Color.BLACK);
        		win.setText("------"); 
        	}
        }
    }
}
