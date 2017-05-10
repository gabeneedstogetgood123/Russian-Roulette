import java.util.Scanner;
public class gamestarter {
	public String Greeting(){
		return "Let us play a game.";
	}
	public String Response(){
		String response="";
		Scanner bot = new Scanner(System.in);
		System.out.println("Which gun do you want to choose? Either gun 1,2, or 3.");
		String answer = bot.nextLine();
		return response;
	}

}
