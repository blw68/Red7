
public class Main {

	public static void main(String[] args) {
		
		Card head = null;
		int rule = 6; // red
		
		head = CardUtilities.putInDeck(head);
		//Testing.printDeck(head);
		head = CardUtilities.shuffle(head);
		//Testing.printDeck(head);
		
		int numPlayers = UserInput.numPlayers();
		
		Card[][] hands = new Card[7][numPlayers];
		Card[][] field = new Card[7][numPlayers];
		
		head = CardUtilities.loadH(head, hands, numPlayers);
		//Testing.printA(hands, numPlayers);
		
		head = CardUtilities.loadF(head, field, numPlayers);
		Testing.printA(field, numPlayers);
		//System.out.println("highest card in game is " + Testing.toStr(Turn.highestCard(field, numPlayers)));
		int first = CardUtilities.determine1st(field, numPlayers);
		//System.out.println("Player going first is " + first);
		
		int p = first;		
		do {
			rule = Turn.takeTurn(hands, field, p, rule, numPlayers);	

			if (p == (numPlayers - 1)) {
				p = 0;
			} else {
				p++;
			}
			
		} while (true);
	}

}
