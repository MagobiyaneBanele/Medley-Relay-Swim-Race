//Banele Magobiyane

// This is a modified class of FinishCounter to record the 1st, 2nd, 3rd swimmers

package medleySimulation;

public class FinishCounter {
	private int firstSwimmer = -1; // first swimmer to finish
	private int firstTeam = -1;    // team of the first swimmer
	private int secondSwimmer = -1; // second swimmer to finish
	private int secondTeam = -1;   // team of the second swimmer
	private int thirdSwimmer = -1;  // third swimmer to finish
	private int thirdTeam = -1;     // team of the third swimmer
	
	private int finishCount = 0;    // keeps track of the number of swimmers who have finished
	
	FinishCounter() { 
		// All initial values are set to -1 (meaning no swimmer/team has finished yet)
	}

	// Called by a swimmer when they touch the finish line
	public synchronized void finishRace(int swimmer, int team) {
		// If 3 swimmers have already finished, don't record anymore
		if (finishCount >= 3) {
			return;
		}
		
		// Record first place
		if (finishCount == 0) {
			firstSwimmer = swimmer;
			firstTeam = team;
		}
		// Record second place
		else if (finishCount == 1) {
			secondSwimmer = swimmer;
			secondTeam = team;
		}
		// Record third place
		else if (finishCount == 2) {
			thirdSwimmer = swimmer;
			thirdTeam = team;
		}
		// Increase the finish count
		finishCount++;
	}

	// Methods to check if the race has been won
	public boolean isRaceWon() {
		return finishCount > 0; // returns true when at least one swimmer has finished
	}

	// Getters for the first, second, and third winners
	public int getFirstSwimmer() { return firstSwimmer; }
	public int getFirstTeam() { return firstTeam; }
	
	public int getSecondSwimmer() { return secondSwimmer; }
	public int getSecondTeam() { return secondTeam; }
	
	public int getThirdSwimmer() { return thirdSwimmer; }
	public int getThirdTeam() { return thirdTeam; }
}
