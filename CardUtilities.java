import java.util.Random;

public class CardUtilities {

	public static Card addToDeck(Card head, int c, int n) {
		// make new Card with info passed in and add to front, return head
		if (head == null) {
			return new Card(c, n, null);
		}

		return new Card(c, n, head);
	}

	public static Card addToDeckEnd(Card head, int c, int n) {
		// make new Card with info passed in and add to end, return head
		if (head == null) {
			return new Card(c, n, null);
		}
		for (Card temp = head; temp != null; temp = temp.next) {
			if (temp.next == null) {				
				temp.next = new Card(c, n, null);				
				return head;
			}
		}
		return head;
	}

	public static Card addSomewhere(Card head, int c, int n, int r) {
		// add anywhere, return head
		Card temp = head;
		for (int i = 0; i < r; i++) {
			temp = temp.next;
		}

		Card temp2 = temp.next;
		temp.next = new Card(c, n, null);
		temp.next.next = temp2;

		return head;
	}

	public static Card putInDeck(Card head) {
		// add all 49 cards into deck, return head

		// violet 1-7
		head = addToDeck(head, 0, 1);
		head = addToDeck(head, 0, 2);
		head = addToDeck(head, 0, 3);
		head = addToDeck(head, 0, 4);
		head = addToDeck(head, 0, 5);
		head = addToDeck(head, 0, 6);
		head = addToDeck(head, 0, 7);

		// indigo 1-7
		head = addToDeck(head, 1, 1);
		head = addToDeck(head, 1, 2);
		head = addToDeck(head, 1, 3);
		head = addToDeck(head, 1, 4);
		head = addToDeck(head, 1, 5);
		head = addToDeck(head, 1, 6);
		head = addToDeck(head, 1, 7);

		// blue 1-7
		head = addToDeck(head, 2, 1);
		head = addToDeck(head, 2, 2);
		head = addToDeck(head, 2, 3);
		head = addToDeck(head, 2, 4);
		head = addToDeck(head, 2, 5);
		head = addToDeck(head, 2, 6);
		head = addToDeck(head, 2, 7);

		// green 1-7
		head = addToDeck(head, 3, 1);
		head = addToDeck(head, 3, 2);
		head = addToDeck(head, 3, 3);
		head = addToDeck(head, 3, 4);
		head = addToDeck(head, 3, 5);
		head = addToDeck(head, 3, 6);
		head = addToDeck(head, 3, 7);

		// yellow 1-7
		head = addToDeck(head, 4, 1);
		head = addToDeck(head, 4, 2);
		head = addToDeck(head, 4, 3);
		head = addToDeck(head, 4, 4);
		head = addToDeck(head, 4, 5);
		head = addToDeck(head, 4, 6);
		head = addToDeck(head, 4, 7);

		// orange 1-7
		head = addToDeck(head, 5, 1);
		head = addToDeck(head, 5, 2);
		head = addToDeck(head, 5, 3);
		head = addToDeck(head, 5, 4);
		head = addToDeck(head, 5, 5);
		head = addToDeck(head, 5, 6);
		head = addToDeck(head, 5, 7);

		// red 1-7
		head = addToDeck(head, 6, 1);
		head = addToDeck(head, 6, 2);
		head = addToDeck(head, 6, 3);
		head = addToDeck(head, 6, 4);
		head = addToDeck(head, 6, 5);
		head = addToDeck(head, 6, 6);
		head = addToDeck(head, 6, 7);

		return head;
	}

	public static Card deleteFront(Card head) {
		// take first out of deck, return head
		return head.next;
	}

	public static Card firstToLast(Card head) {
		// move first to last, return head
		
		// store first
		Card first = head;

		// delete first
		head = deleteFront(head);

		// add back somewhere in list		
		head = addToDeckEnd(head, first.color, first.num);
		
		return head;
	}

	public static Card shuffle(Card head) {
		// shuffle deck, return head
		Random rn = new Random();

		for (int i = 0; i < 1000; i++) {
			// store first
			Card first = head;

			// delete first
			head = deleteFront(head);

			// add back somewhere in list
			int r = rn.nextInt(48);
			head = addSomewhere(head, first.color, first.num, r);
		}
		
		for (int i = 0; i < 1000; i++) {
			head = firstToLast(head);
		}

		return head;
	}
	
	public static Card loadH(Card head, Card[][] h, int numP) {
		// load hands, return head
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < numP; j++) {
				h[i][j] = new Card(head.color, head.num, null);
				head = deleteFront(head);
			}
		}
		return head;
	}
	
	public static Card loadF(Card head, Card[][] h, int numP) {
		// load first cards into field before game starts, return head
		for (int i = 0; i < numP; i++) {
			h[0][i] = new Card(head.color, head.num, null);
			head = deleteFront(head);
		}
		return head;
	}
	
	public static void printMineI(Card[][] arr, int p) {
		// print player p's row
		for (int i = 0; i < 7; i++) {
			if (arr[i][p] != null) {
				System.out.print(i + 1 + "=" + Testing.toStr(arr[i][p]) + " ");
			}
		}
		System.out.println();
	}
	
	public static void printMine(Card[][] arr, int p) {
		// print player p's row
		for (int i = 0; i < 7; i++) {
			if (arr[i][p] != null) {
				System.out.print(Testing.toStr(arr[i][p]) + " ");
			}
		}
		System.out.println();
	}
	
	public static void printOthers(Card[][] arr, int p, int numP) {
		// print all rows except player p's
		for (int i = 0; i < numP; i++) {
			if (i == p) {
				continue;
			}
			System.out.print("Player " + i + "'s field: ");
			for (int j = 0; j < 7; j++) {
				if (arr[j][i] == null) {
					continue;
				}
				System.out.print(Testing.toStr(arr[j][i]) + " ");
			}
			System.out.println();
		}
	}
	
	public static int determine1st(Card[][] field, int numP) {
		// return int for player going first
		int highest = Turn.locateHighest(field, numP); // has highest
		
		// if 3 players, and 1 first, 2 -> 0 -> 1
		// so first is player 1 after highest
		// if highest is 2 (max), return 0
		
		if (highest == numP - 1) {
			return 0;
		} else {
			return highest + 1;
		}
	}
	
	public static void sort(Card[][] field, int p) {
		// sort line p (in field), sort by number, then color (ex. red 7 > orange 7 > orange 6)
		// note: only one copy of each card and no 2 cards equal, so there is right order
		
		// set len to number of cards (non-null) in field
		int len = 0;
		for (int i = 0; i < 7; i++) {
			if(field[i][p] != null) {
				len++;
			}
		}
		
		// if len = 1, do nothing
		if (len == 1) {
			return;
		}
		
		
		// numbers
		for (int a = 0; a < (len - 1); a++) {
			for (int b = 0; b < (len - a - 1); b++) {
				if (Turn.returnBig(field[b][p], field[b+1][p]) == field[b][p]) {
					// swap
					Card temp = field[b][p];
					field[b][p] = field[b+1][p];
					field[b+1][p] = temp;
				}
			}
		}
		
		// colors, lesser color should be first
		for (int a = 0; a < (len - 1); a++) {
			for (int b = 0; b < (len - a - 1); b++) {
				if (field[b][p].num == field[b+1][p].num) {
					// same numbers (ex. red 5 and yellow 5)
					if (field[b][p].color > field[b][p].color) {
						// (ex. red, then yellow), swap
						Card temp = field[b][p];
						field[b][p] = field[b+1][p];
						field[b+1][p] = temp;
					}
				}
			}
		}
	}

}
