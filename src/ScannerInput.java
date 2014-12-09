//////////////////////////////////////////////////////////////////////////
//
// Main Class File: 	GuessingGame.java
// File: 				ScannerInput.java
// Semester: 			Fall 2014
// Author:				Meag Tessmann mtessmann@wisc.edu
// CS Login:			meaghan
// Lecturer's Name:		Jim Skrentny
// Lab Section: 		NA
//
//////////////////////////////////////////////////////////////////////////

/**
 * A Scanner Input object class. Maintains control of both file input and
 * standard system input (system.in) when simulation asks for next input, 
 * this object will first see if file contents are exhausted. If not, 
 * then will return the next command from the file. 
 * If file does not exist, or is exhausted, then scanner will return
 * the next system in
 * 
 * <p> Bugs: none known
 * 
 * author: meag tessmann
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class ScannerInput {
	// scanner to the input file
	Scanner fileIn = null; 
	// scanner to the standard system in
	Scanner consoleIn = null;
	// arraylist implemented as queue to keep track of file command lines
	ArrayList<String> fileCmds = new ArrayList<String>();

	/**
	 * constructor:
	 * reads in a file, creates a scanner object for said file, then 
	 * creates stdin scanner
	 * 
	 * @param arg - [command line] arg that will be a filename
	 * this file should contain a list of commands to run the simulation by
	 */
	public ScannerInput(String arg) {
		try {
			// file object rep-ing file being read
			File file = new File(arg);     		
			//scanner of said file
			fileIn = new Scanner(file); 
			//read file cmd lns into arraylist
			while (fileIn.hasNext()) { 
				fileCmds.add(fileIn.nextLine());
			}
			fileIn.close();
		} catch (FileNotFoundException e) {
			System.err.println("Cannot find the specified file");
			System.exit(2);
		} 
		//create stdin scanner
		consoleIn = new Scanner(System.in);		
	}


	/**
	 * constructor:
	 * if no file, then creates just the stdin scanner object
	 */
	public ScannerInput() {
		//screate stdin scanner
		consoleIn = new Scanner(System.in);		
	}

	/**
	 * retursn the next command in line. If there's still cmds from read-in file,
	 * then return next cmd, otherwise return next stdin cmd
	 * 
	 * @return String with the next command line
	 * -
	 */
	public String getNextCmd() {
		String returnCmd = ""; 
		//if arraylist has next, return and delete from queue
		if (!fileCmds.isEmpty() || fileCmds == null) {
			returnCmd = (String) fileCmds.get(0);
			fileCmds.remove(0);
		// else return next stdin
		} else { 
			returnCmd = consoleIn.nextLine();
		}
		return returnCmd;
	}

	/**
	 * close all scanners
	 */
	public void close() {
		consoleIn.close();
		fileIn.close();
	}
}
