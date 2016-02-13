import java.util.Scanner;

public class UserInput {
	
	public static int numPlayers() {
		// promt user, return number of players (ex. 3) in game
		Scanner sc = new Scanner(System.in);
		
		int num;
		do {
			System.out.print("Enter number of players in game (2-4): ");
			num = sc.nextInt();
		} while (num < 1 || num > 4);
		
		return num;
	}
	
	public static void chooseCard(Card[][] hands, Card[][] field, int p) {
		
		Scanner sc = new Scanner(System.in);
		// first select palette
		
		int pal;
		do {
			System.out.print("Enter card to play on field, or 0 to not: ");
			pal = sc.nextInt();
			pal--;
			if (pal == -1) {
				break;
			}
		} while (pal < -2 || pal > 6 || hands[pal][p] == null);
		
		// if -1, not playing a card
		if (pal == -1) {
			return;
		}
		
		// get card
		Card play = hands[pal][p];
		
		// put in field
		for (int i = 0; i < 7; i++) {
			if (field[i][p] == null) {
				field[i][p] = play;
				break;
			}
		}
		
		// take out of hand
		hands[pal][p] = null;
		
	}
	
	public static int chooseRule(Card[][] hands, Card[][] field, int p, int rule) {
		Scanner sc = new Scanner(System.in);
		
		// select rule
		int r;
		do {
			System.out.print("Enter card to play as new rule: ");
			r = sc.nextInt();
			r--;
			if (r == -1) {
				break;
			}
		} while (r < -1 || r > 6 || hands[r][p] == null);
		
		// if -1, not playing new rule, return current rule
		if (r == -1) {
			return rule;
		}
		
		// get card
		Card play = hands[r][p];		
		
		// take out of hand
		hands[r][p] = null;
		
		// return new rule
		return play.color;
	}

}
