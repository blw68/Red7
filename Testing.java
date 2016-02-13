
public class Testing {
	public static void printDeck(Card head) {
		// print all cards in deck
		int i = 1;
		for (Card temp = head; temp != null; temp = temp.next) {
			System.out.println(i + toStr(temp));
			i++;
		}
	}
		
	public static String toStr(Card c) {
		// return String with c's information
		if (c == null) {
			return null;
		}
		return toColor(c.color) + c.num;
	}
	
	public static String toColor(int c) {
		// convert 0 to violet, etc
		if (c == 0) {
			return "Violet";
		} else if (c == 1) {
			return "Indigo";
		} else if (c == 2) {
			return "Blue";
		} else if (c == 3) {
			return "Green";
		} else if (c == 4) {
			return "Yellow";
		} else if (c == 5) {
			return "Orange";
		} else if (c == 6){
			return "Red";
		} else {
			// shouldn't happen
			return "bad";
		}
	}
	
	public static void printA(Card[][] A, int p) {
		// print every card in array
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < p; j++) {
				if (A[i][j] != null) {
					System.out.println(i + "," + j + " " + toStr(A[i][j]));
				}
			}
		}
	}
	

}
