import java.util.Scanner;
public class gamestarter {
	public String Greeting(){
		return "Do you want to play a game.";
	}
	public String Response(){
		String response="";
		String [] filler =new String [6];
		weapons loaded = new weapons();
		Scanner bot = new Scanner(System.in);
		System.out.println("Which gun do you want to choose? Either gun 1,2, or 3.");
		String answer = bot.nextLine();
		if(answer.equals("1")||answer.equals("2")||answer.equals("3")){
			Scanner AI = new Scanner(System.in);
			System.out.println("Are you ready to be shot at?");
			String feedback = AI.nextLine();
			if(feedback.equalsIgnoreCase("yes")){
				System.out.println(loaded.Handgun(filler));
			}
			else  {
				System.out.println("BANG!");
				System.out.println("RIP!");
				System.out.println("SHOULD HAVE SAID YES!");
				System.out.println("GAME OVER!");
			}	
		}
		else  {
			System.out.println("BANG!");
			System.out.println("RIP!");
			System.out.println("SHOULD HAVE PICKED 1,2 OR 3!");
			System.out.println("TRY NOT TO OUTSMART ME!");
			System.out.println("GAME OVER!");
		}
		return response;
	}

}
