
import java.io.*;
import java.util.*;

public class RussianRouletteDriver {

	// Create a single shared BufferedReader for keyboard input
	private static BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));

	// Create a single shared Random number generator
	private static Random generator = new Random();

	// Program execution starts here
	public static void main(String[] args) throws IOException{

		System.out.println("Welcome to Russian Roulette!");

		// Initialize starting conditions
		int numPlayers = getNumPlayers(); 	// get number of players
		int startChamber = spinCylinder(); // generate a random starting chamber
		int bulletChamber = spinCylinder();	// generate a random chamber for the bullet
		
		while(true){
			// Play game
			int currentChamber = play(numPlayers, startChamber, bulletChamber);
			// Prompt action after one round of action
			int action = promptAction(currentChamber);
			// Determine end-of-round action
			if(action == 1 && currentChamber != -1){	// retain current chamber position
				startChamber = currentChamber;
			}else if(action == 1 && currentChamber == -1){ // gun has fired and start new game
				System.out.println("\nResetting game...");
				startChamber = spinCylinder();
				bulletChamber = spinCylinder();
			}else if(action == 2){
				System.out.println("\nRevolver's cylinder is spun.");
				startChamber = spinCylinder();
			}else if(action == 3){
				System.out.println("\nResetting game...");
				numPlayers = getNumPlayers();
				startChamber = spinCylinder();
				bulletChamber = spinCylinder();
			}else if(action == 4){ // end game
				System.out.println("\nEnding program...");
				System.exit(0);
			}
		}

	}


	/* Generate a random number from 0 to 6 (inclusive)
	 *  to represent a chamber
	 */
	public static int spinCylinder() {
		return generator.nextInt(7);
	}

	/* Plays one round of Russian Roulette and returns the
	 * current chamber, a return value of -1 signifies a fired bullet
	 */
	public static int play(int numPlayers, int currentChamber, int bulletChamber){
		for(int i = 1; i <= numPlayers; i++){
			System.out.println("\nPlayer " + i + " pulls the trigger...");
			// Revolve cylinder and fire gun
			if(currentChamber == 6){
				currentChamber = 0;
			}else{
				currentChamber++;
			}
			// Determine if chamber with bullet is fired
			if(currentChamber == bulletChamber){
				System.out.println("\n\t\tPlayer " + i + " is dead!");
				return -1;
			}else{
				System.out.println("\t\t...nothing happens.");
			}
		}
		return currentChamber;
	}

	/* Prints end-of-round menu according to current game status */
	public static int promptAction(int gunFired) throws IOException {
		while(true){
			// Prompt user for number of players
			System.out.println("\nPlease select an action:");
			// Determines if gun has been fired
			if(gunFired == -1){
				System.out.println("1. New game (same players)");
			}else{
				System.out.println("1. Continue");
			}
			System.out.println("2. Spin cylinder (reset)");
			System.out.println("3. Change number of players");
			System.out.println("4. Quit");

			System.out.print("\nEnter a choice: ");
			try{
				// Attempt to parse input String as int
				int action = Integer.parseInt(kb.readLine().trim());
				if(action < 1 || action > 4){
					System.out.println("\nInvalid input. Please enter a valid value.");
				}else{
					return action;
				}
			}catch(NumberFormatException e){
				System.out.println("\nInvalid input. Please enter a valid value.");
			}
		}

	}

	/* Prompts user for number of players */
	public static int getNumPlayers() throws IOException {
		while(true){
			// Prompt user for number of players
			System.out.print("\nEnter number of players: ");
			try{
				// Attempt to parse input String as int
				int numPlayers = Integer.parseInt(kb.readLine().trim());
				if(numPlayers > 0){
					return numPlayers;
				}else{
					System.out.println("\nInvalid input. Please enter a valid value.");
				}
			}catch(NumberFormatException e){
				System.out.println("\nInvalid input. Please enter a valid value.");
			}
		}
	}
}
