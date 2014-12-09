//////////////////////////////////////////////////////////////////////////
//
// Main Class File: 	GuessingGame.java
// File: 				IllegalBinaryTreeOpException.java
// Semester: 			Fall 2014
// Author:				Meag Tessmann mtessmann@wisc.edu
// CS Login:			meaghan
// Lecturer's Name:		Jim Skrentny
// Lab Section: 		NA
//
//////////////////////////////////////////////////////////////////////////


/**
 * Possible exception of different types thrown while performing
 * binary tree operations. 
 * 
 * @author mtessmann
 *
 */
@SuppressWarnings("serial")
public class IllegalBinaryTreeOpException extends Exception {
	// description to output to console or variable
	String errorDescription = "";

	/**
	 * output error message to console when aprticular type is thrown
	 * 
	 * @param type - string with type name
	 */
	public IllegalBinaryTreeOpException(String type) {
		switch (type) {
		case "leftChildExists":
			errorDescription = "A left node already exists.";
		case "rightChildExists":
			errorDescription = "A right node already exists.";
		case "currentDoesntExist":
			errorDescription = "The current node either is null or does not exist.";
		default:
			errorDescription = "An unidentified illegal binary tree operation occured.";
			break;	
		}
		System.out.println(errorDescription);
	}
	
	/**
	 * get the error description
	 * 
	 * @return errorDescription - a string object containing a description of error
	 */
	public String getErrorDescription() {
		return errorDescription;
	}


}

