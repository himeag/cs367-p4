//////////////////////////////////////////////////////////////////////////
//
// Title:				GuessingGame
// Files: 				GuessingGame.java, ScannerInput.java,
//                      BinaryTree.java, BinaryTreenode.java, 
//                      IllegalBinaryTreeOpException.java
// Semester: 			Fall 2014
// Author:				Meag Tessmann mtessmann@wisc.edu
// CS Login:			meaghan
// Lecturer's Name:		Jim Skrentny
// Lab Section: 		NA
//
//////////////////////////////////////////////////////////////////////////

/**
 * A guessing game program similar to 20 Questions that attempts to 
 * determine what the user is thinking of by asking the user yes/no 
 * questions
 * 
 *  <p> Bugs: none known
 *  
 *  author: Meag Tessmann
 */

public class GuessingGame {

	/**
	 * Main method - runs simulation of game, prompting for yes/no
	 * answers and altering binary tree as user responds
	 * 
	 * @param args - filename read in, if exists
	 */
	public static void main(String[] args) {
		// main binary tree keeping current game
		BinaryTree<String> gameTree = null;
		// object maintaining input from stdin and file
		ScannerInput scanners = null;
		// boolean keeping track if simulation is finished or not
		boolean done = false;


		/**
		 * read in file if exists, and create scanner object
		 */
		if (args.length > 0) {
			scanners = new ScannerInput(args[0]);
		} else if (args.length > 1) {
			System.out.println("Please enter valid command: java GuessingGame filename.txt");
		} else {
			scanners = new ScannerInput();
		}

		/**
		 * simulation - 
		 */
		while (!done) {
			System.out.println("Please enter a command (o, p, q, r):");
			switch (scanners.getNextCmd()) { 
			/*
			 *  print out current game tree
			 */
			case "o":
				try {
					if (gameTree == null || gameTree.getCurrent() == null) {
						System.out.println("Empty Tree");
					} else {
						gameTree.print();
					}
				} catch (IllegalBinaryTreeOpException e3) {
					e3.printStackTrace();
				} catch (NullPointerException e4) {
					System.out.println("Null pointer.... meag messed up");
				}

				break;

				/*
				 * Play the game with the program. The steps for this command are as follows:
				 * If the tree is empty, follow the steps for the "r" command as described below.
				 */
			case "p":
				try {
					if (gameTree == null || gameTree.getCurrent() == null) {
						System.out.println("Please enter a question.");
						// get either file, or stdin
						gameTree = new BinaryTree<String>(scanners.getNextCmd());
						// start game
						gameTree.start();
						System.out.println("Please enter something that is true for that question.");
						//add new true answer to tree
						try {
							gameTree.addLeftChild(scanners.getNextCmd());
						} catch (IllegalBinaryTreeOpException e1) {
							e1.printStackTrace();
						}
						System.out.println("Please enter something that is false for that question.");
						// add new false answer to tree
						try {
							gameTree.addRightChild(scanners.getNextCmd());
						} catch (IllegalBinaryTreeOpException e) {
							e.printStackTrace();
						}
					}
				} catch (IllegalBinaryTreeOpException e3) {
					e3.printStackTrace();
				}
				 
				// if game already exists, play
				gameTree.start();
				// keep asking questions until a guess is made
				while (!gameTree.isLeaf()) {
					try {
						System.out.println(gameTree.getCurrent());
						String answer = scanners.getNextCmd();
						if (answer.equalsIgnoreCase("y") ) {
							gameTree.goLeft();
						} else if (answer.equalsIgnoreCase("n")) {
							gameTree.goRight();
						} else {
							System.out.println("Please answer y for yes or n for no");
							break;
						}
					} catch (IllegalBinaryTreeOpException e) {
						e.printStackTrace();
					} 
				}
				// guess is made, asks if right
				// if right, game wins
				// if wrong, then asks user for answer and a question to add into tree
				
				try {
					System.out.println("I guess: " + gameTree.getCurrent() + ". Was I right?");
					String answer = scanners.getNextCmd().toLowerCase();
					if (answer.equalsIgnoreCase("n")) {
						System.out.println("Darn. Ok, tell me a question that is true for your answer, but false for my guess.");
						String newQuestion = scanners.getNextCmd().replace(System.getProperty("line.separator"), "");
						System.out.println("Thanks! And what were you thinking of?");
						String newAnswer = scanners.getNextCmd().replace(System.getProperty("line.separator"), "");
						gameTree.addRightChild(gameTree.getCurrent());
						gameTree.changeCurrent(newQuestion);
						gameTree.addLeftChild(newAnswer);
					} else if (answer.equalsIgnoreCase("y")) {
						System.out.println("I win!");
					} else {
						System.out.println("nobody wins");
					}
				} catch (IllegalBinaryTreeOpException e2) {
					e2.printStackTrace();
				}
				break;
				
				/*
				 * quit simulation
				 */
			case "q":
				done = true;
				break;

				/*
				 *  Reset the game. You should make the current tree completely empty, 
				 *  and prompt the user for a new root for the tree.
				 */
			case "r":
				// start with a question create new tree with question as root
				System.out.println("Please enter a question.");
				gameTree = new BinaryTree<String>(scanners.getNextCmd());
				// start the game
				gameTree.start();
				//asks for true response
				System.out.println("Please enter something that is true for that question.");
				try {
					gameTree.addLeftChild(scanners.getNextCmd());
				} catch (IllegalBinaryTreeOpException e1) {
					e1.printStackTrace();
				}
				// asks for false response
				System.out.println("Please enter something that is false for that question.");
				try {
					gameTree.addRightChild(scanners.getNextCmd());
				} catch (IllegalBinaryTreeOpException e) {
					e.printStackTrace();
				}
				break;

			default:  //a command with no argument
				System.out.println("Incorrect command.");
				break;
			} //end switch
		} // end while
		scanners.close();
	} // end main

	
} // end class
