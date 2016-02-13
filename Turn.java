
public class Turn {

	public static int takeTurn(Card[][] hands, Card[][] field, int p, int rule, int numP) {
		// player p's turn, return rule, -1 if out
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("Player " + p + "'s Turn:");

		// first print own cards
		System.out.print("Your hand: ");
		CardUtilities.printMineI(hands, p);
		System.out.print("Your palette: ");
		CardUtilities.sort(field, p);
		CardUtilities.printMine(field, p);
		
		// print current rule
		System.out.println();
		System.out.println("Current rule: " + ruleStr(rule));
		
		// print out opponents fields
		CardUtilities.printOthers(field, p, numP);

		// prompt card to play from hand onto field, update arrays		
		UserInput.chooseCard(hands, field, p);
		
		// check if player p is boss or not
		if (checkBoss(field, p, rule, numP) == true) {
			// yes boss
			return rule;
		}
		
		System.out.print("You aren't the boss yet! ");

		//		System.out.print("Your hand: ");
		//		CardUtilities.printMine(hands, p);
		//		System.out.print("Your palette: ");
		//		CardUtilities.printMine(field, p);

		// prompt card for new rule
		int newRule = UserInput.chooseRule(hands, field, p, rule);
		
		// check if player p is boss or not after playing new rule
		if (checkBoss(field, p, newRule, numP) == true) {
			// yes boss
			return newRule;
		} else {
			// not boss after playing rule
			System.out.println("You didn't end the round as the boss");
			return newRule;
		}
	}

	public static String ruleStr(int rule) {
		if (rule == 6) {
			return "Highest card wins (Red)";
		} else if (rule == 5) {
			return "Most of 1 number wins (Orange)";
		} else if (rule == 4) {
			return "Most of 1 color wins (Yellow)";
		} else if (rule == 3) {
			return "Most evens wins (Green)";
		} else if (rule == 2) {
			return "Most different colors wins (Blue)";
		} else if (rule == 1) {
			return "Most cards in a row wins (Indigo)";
		} else if (rule == 0) {
			return "Most cards below 4 wins (Violet)";
		} else {
			// shouldn't happen
			return "Bogus rule";
		}
	}

	public static boolean checkBoss(Card[][] field, int p, int rule, int numP) {
		// return true if player p is boss, false otherwise

		if (rule == 6) {
			// rule is red, highest card
			if (locateHighest(field, numP) == p) {
				return true;
			} else {
				return false;
			}
		} else if (rule == 5) {
			// rule is orange, most if 1 number
			if (mostNum(field, numP) == p) {
				return true;
			} else {
				return false;
			}
		} else if (rule == 4) {
			// rule is yellow, most of 1 color
			if (mostOneColor(field, numP) == p) {
				return true;
			} else {
				return false;
			}
		} else if (rule == 3) {
			// rule is green, most evens
			if (countEvens(field, numP) == p) {
				return true;
			} else {
				return false;
			}
		} else if (rule == 2) {
			// rule is blue, most different colors
			if (mostDiffColor(field, numP) == p) {
				return true;
			} else {
				return false;
			}
		} else if (rule == 1) {
			// rule is indigo, most cards in a row
			if (mostInRow(field, numP) == p) {
				return true;
			} else {
				return false;
			}
		} else if (rule == 0) {
			// rule is violet, most cards below 4
			if (mostBelow4(field, numP) == p) {
				return true;
			} else {
				return false;
			}
		} else {
			// shouldn't happen
			return false;
		}
	}

	public static Card highestCard(Card[][] field, int numP) {
		// return highest card in game (ex. blue 6)
		int color = 0;
		int num = 1; // lowest card in game

		for (int i = 0; i < numP; i++) {
			for (int j = 0; j < 7; j++) {
				if (field[j][i] == null) {
					continue;
				}
				if (field[j][i].num > num) {
					// higher number, update number
					num = field[j][i].num;
					color = field[j][i].color;
				}
				if (field[j][i].num == num) {
					// same number, different colors, update color (6 biggest for colors)
					if (field[j][i].color > color) {
						color = field[j][i].color;
					}
				}
			}
		}

		return new Card(color, num, null);
	}

	public static int locateHighest(Card[][] field, int numP) {
		// return int of player that has the highest

		Card big = highestCard(field, numP); // ex. red 6
		int color = big.color;
		int num = big.num;

		// find who has big
		for (int i = 0; i < numP; i++) {
			for (int j = 0; j < 7; j++) {
				if (field[j][i] == null) {
					continue;
				}
				if (color == field[j][i].color && num == field[j][i].num) {
					// match
					return i;
				}
			}
		}

		// shouldn't happen
		return -1;
	}	
	
	public static int locateCardNum(Card[][] field, int num, int numP) {
		// return color of most color (ex. out of B3 I3 V3, return blue, highest color), given 3
		int c = 0;	
		
		System.out.println("numP = " + numP);
		System.out.println("num = " + num);
		
		Testing.printA(field, numP);
		
		for (int i = 0; i < numP; i++) {
			for (int j = 0; j < 7; j++) {				
				if (field[j][i] == null) {					
					continue;
				}
				System.out.println("in both loops " + Testing.toStr(field[j][i]));
				if (field[j][i].num != num) {
					continue;
				}
				// got past, yes correct number
				System.out.println("should be correct num " + Testing.toStr(field[j][i]));
				if (field[j][i].color > c) {
					// correct number and better color
					c = field[j][i].color;				
					System.out.println("updated c " + c + " on num = " + num);
				}				
			}
		}

		return c;
		
	}

	public static int locateCardColor(Card[][] field, int color, int numP) {
		// return number of highest card of color color (ex. if highest red is red 5, return 5)
		
		int n = 1;	
		
		for (int i = 0; i < numP; i++) {
			for (int j = 0; j < 7; j++) {
				if (field[j][i] == null) {
					continue;
				}
				if (field[j][i].color != color) {
					continue;
				}
				// got past, yes correct color
				if (field[j][i].num > n) {
					// correct color and better number
					n = field[j][i].num;					
				}				
			}
		}

		return n;
		
	}
	
	public static int locateCardX(Card[][] field, Card x, int numP) {
		// return int of player that has card x
		
		for (int i = 0; i < numP; i++) {
			for (int j = 0; j < 7; j++) {
				if (field[j][i] == null) {
					continue;
				}
				if (x.color == field[j][i].color && x.num == field[j][i].num) {
					// match
					return i;
				}
			}
		}

		// shouldn't happen
		return -1;
		
	}

	public static int mostNum(Card[][] field, int numP) {
		// return int of player that has most of one number

		int num7 = 0;
		int num6 = 0;
		int num5 = 0;
		int num4 = 0;
		int num3 = 0;
		int num2 = 0;
		int num1 = 0;

		int pMost = 0;
		int max = 0;  		// most overall
		int maxR;			// most in row

		for (int i = 0; i < numP; i++) {
			for (int j = 0; j < 7; j++) {
				if (field[j][i] == null) {
					continue;
				} else if (field[j][i].num == 7) {
					num7++;
				} else if (field[j][i].num == 6) {
					num6++;
				} else if (field[j][i].num == 5) {
					num5++;
				} else if (field[j][i].num == 4) {
					num4++;
				} else if (field[j][i].num == 3) {
					num3++;
				} else if (field[j][i].num == 2) {
					num2++;
				} else if (field[j][i].num == 1) {
					num1++;
				} else {
					// shouldn't happen
					System.exit(0);
				}
			}
			
			maxR = max7(num7, num6, num5, num4, num3, num2, num1);
			if (maxR > max) {
				max = maxR;
				pMost = i;
			}
			
			if (maxR == max) {
				// tie
				//pMost = TieBreak.highest1num(field, numP, pMost, i, max);
			}

			num7 = 0;
			num6 = 0;
			num5 = 0;
			num4 = 0;
			num3 = 0;
			num2 = 0;
			num1 = 0;			
		}

		return pMost;
	}

	public static int mostOneColor(Card[][] field, int numP) {
		// return int of player that has most of one color

		int numRed = 0;
		int numOrange = 0;
		int numYellow = 0;
		int numGreen = 0;
		int numBlue = 0;
		int numIndigo = 0;
		int numViolet = 0;

		int pMost = 0;
		int max = 0;  		// most overall
		int maxR;			// most in row

		for (int i = 0; i < numP; i++) {
			for (int j = 0; j < 7; j++) {
				if (field[j][i] == null) {
					continue;
				} else if (field[j][i].color == 6) {
					numRed++;
				} else if (field[j][i].color == 5) {
					numOrange++;
				} else if (field[j][i].color == 4) {
					numYellow++;
				} else if (field[j][i].color == 3) {
					numGreen++;
				} else if (field[j][i].color == 2) {
					numBlue++;
				} else if (field[j][i].color == 1) {
					numIndigo++;
				} else if (field[j][i].color == 0) {
					numViolet++;
				} else {
					// shouldn't happen
					System.exit(0);
				}
			}

			maxR = max7(numRed, numOrange, numYellow, numGreen, numBlue, numIndigo, numViolet);
			if (maxR > max) {
				max = maxR;
				pMost = i;
			}
			
			if (maxR == max) {
				// tie
				//pMost = TieBreak.highest1color(field, numP, pMost, i, max);
			}

			numRed = 0;
			numOrange = 0;
			numYellow = 0;
			numGreen = 0;
			numBlue = 0;
			numIndigo = 0;
			numViolet = 0;
		}

		return pMost;
	}

	public static int mostDiffColor(Card[][] field, int numP) {
		// return int of player that has most different colors

		int numRed = 0;
		int numOrange = 0;
		int numYellow = 0;
		int numGreen = 0;
		int numBlue = 0;
		int numIndigo = 0;
		int numViolet = 0;

		int pMost = 0;		// player with most overall
		int most = 0;		// most overall
		int mostR;			// most different colors in row

		for (int i = 0; i < numP; i++) {
			for (int j = 0; j < 7; j++) {
				if (field[j][i] == null) {
					continue;
				} else if (field[j][i].color == 6) {
					numRed++;
				} else if (field[j][i].color == 5) {
					numOrange++;
				} else if (field[j][i].color == 4) {
					numYellow++;
				} else if (field[j][i].color == 3) {
					numGreen++;
				} else if (field[j][i].color == 2) {
					numBlue++;
				} else if (field[j][i].color == 1) {
					numIndigo++;
				} else if (field[j][i].color == 0) {
					numViolet++;
				} else {
					// shouldn't happen
					System.exit(0);
				}
			}

			mostR = numNon0(numRed, numOrange, numYellow, numGreen, numBlue, numIndigo, numViolet);
			if (mostR > most) {
				most = mostR;
				pMost = i;
			}
			
			if (mostR == most) {
				// tie
				//pMost = TieBreak.highestOf2(field, numP, pMost, i);
			}
			

			numRed = 0;
			numOrange = 0;
			numYellow = 0;
			numGreen = 0;
			numBlue = 0;
			numIndigo = 0;
			numViolet = 0;
		}

		return pMost;
	}

	public static int max7(int a, int b, int c, int d, int e, int f, int g) {
		// return largest of 7 numbers
		int max = a;
		if (max > b) {
			max = b;
		}
		if (max > c) {
			max = c;
		}
		if (max > d) {
			max = d;
		}
		if (max > e) {
			max = e;
		}
		if (max > f) {
			max = f;
		}
		if (max > g) {
			max = g;
		}
		return max;
	}

	public static int numNon0(int a, int b, int c, int d, int e, int f, int g) {
		// return number of nonzero numbers
		int numNon0 = 0;
		if (a != 0) {
			numNon0++;
		}
		if (b != 0) {
			numNon0++;
		}
		if (c != 0) {
			numNon0++;
		}
		if (d != 0) {
			numNon0++;
		}
		if (e != 0) {
			numNon0++;
		}
		if (f != 0) {
			numNon0++;
		}
		if (g != 0) {
			numNon0++;
		}
		return numNon0;
	}

	public static int longestRun(int r, int o, int y, int g, int b, int i, int v) {
		// return length of longest run
		int longestLen = 1;
		int len = 0;

		if (r > 0) {
			len++;
		} else {
			if (len > longestLen) {
				longestLen = len;
			}
			len = 0;
		}
		
		if (o > 0) {
			len++;
		} else {
			if (len > longestLen) {
				longestLen = len;
			}
			len = 0;
		}
		
		if (y > 0) {
			len++;
		} else {
			if (len > longestLen) {
				longestLen = len;
			}
			len = 0;
		}
		
		if (g > 0) {
			len++;
		} else {
			if (len > longestLen) {
				longestLen = len;
			}
			len = 0;
		}
		
		if (b > 0) {
			len++;
		} else {
			if (len > longestLen) {
				longestLen = len;
			}
			len = 0;
		}
		
		if (i > 0) {
			len++;
		} else {
			if (len > longestLen) {
				longestLen = len;
			}
			len = 0;
		}
		
		if (v > 0) {
			len++;
		} else {
			if (len > longestLen) {
				longestLen = len;
			}
			len = 0;
		}
		
		if (len > longestLen) {
			longestLen = len;
		}
		len = 0;
		
		return longestLen;
	}

	public static int countEvens(Card[][] field, int numP) {
		// return int of player that has most evens

		int numEvens = 0;
		int most = 0;
		int pMost = 0;

		for (int i = 0; i < numP; i++) {
			for (int j = 0; j < 7; j++) {
				if (field[j][i] == null) {
					continue;
				}
				if (field[j][i].num % 2 == 0) {
					// even
					numEvens++;
				}			
			}

			if (numEvens > most) {
				most = numEvens;
				pMost = i;
			}
			if (numEvens == pMost) {
				// tie
				//pMost = TieBreak.highestEven(field, numP, pMost, i);
			}
			numEvens = 0;
		}

		return pMost;
	}
	
	public static int mostInRow(Card[][] field, int numP) {
		// return player who has longest run
		
		int numRed = 0;
		int numOrange = 0;
		int numYellow = 0;
		int numGreen = 0;
		int numBlue = 0;
		int numIndigo = 0;
		int numViolet = 0;

		int pMost = 0;
		int longest = 0;  		// most in row overall
		int longR;			// most in row (in 1 row)

		for (int i = 0; i < numP; i++) {
			for (int j = 0; j < 7; j++) {
				if (field[j][i] == null) {
					continue;
				} else if (field[j][i].color == 6) {
					numRed++;
				} else if (field[j][i].color == 5) {
					numOrange++;
				} else if (field[j][i].color == 4) {
					numYellow++;
				} else if (field[j][i].color == 3) {
					numGreen++;
				} else if (field[j][i].color == 2) {
					numBlue++;
				} else if (field[j][i].color == 1) {
					numIndigo++;
				} else if (field[j][i].color == 0) {
					numViolet++;
				} else {
					// shouldn't happen
					System.exit(0);
				}
			}

			longR = longestRun(numRed, numOrange, numYellow, numGreen, numBlue, numIndigo, numViolet);
			if (longR > longest) {
				longest = longR;
				pMost = i;
			}
			
			if (longR == longest) {
				// tie
			}

			numRed = 0;
			numOrange = 0;
			numYellow = 0;
			numGreen = 0;
			numBlue = 0;
			numIndigo = 0;
			numViolet = 0;
		}

		return pMost;
	}

	public static int mostBelow4(Card[][] field, int numP) {
		// return int of player that has most evens

		int numBelow4 = 0;
		int most = 0;
		int pMost = 0;

		for (int i = 0; i < numP; i++) {
			for (int j = 0; j < 7; j++) {
				if (field[j][i] == null) {
					continue;
				}
				if (field[j][i].num < 4) {
					// below 4
					numBelow4++;
				}
			}

			if (numBelow4 > most) {
				most = numBelow4;
				pMost = i;
			} 
			if (numBelow4 == most) {
				// tie
				//pMost = TieBreak.highestBelow4(field, numP, pMost, i);
			}
			
			numBelow4 = 0;
		}

		return pMost;
	}

	public static Card returnBig(Card a, Card b) {
		// return a if a is bigger, return b if b is bigger (returns Card)
		if (a.num > b.num) {
			return a;
		} else if (b.num > a.num) {
			return b;
		} else if (a.color > b.color) {
			return a;
		} else if (b.color > a.color) {
			return b;
		} else {
			// 2 cards are same, shouldn't happen
			System.exit(0);
			return null;
		}
	}

}
