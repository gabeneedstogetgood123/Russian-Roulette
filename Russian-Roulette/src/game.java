import java.util.Scanner;
public class game {
	public static void main (String[]args){
		gamestarter start = new gamestarter();
		System.out.println(start.Greeting());
		Scanner bot = new Scanner(System.in);
		String response = bot.nextLine();
		if(response.equalsIgnoreCase("yes")){
			System.out.println(start.Response());
		}
		else {
			System.out.println("BANG!");
			System.out.println("RIP!");
			System.out.println("GUESS YOU WERE NOT LUCKY!");
			System.out.println("GAME OVER!");
		}
	}
	
}
