//////////////////////////////////////////////////////////////////////////
//
// Main Class File: 	GuessingGame.java
// File: 				BinaryTree.java
// Semester: 			Fall 2014
// Author:				Meag Tessmann mtessmann@wisc.edu
// CS Login:			meaghan
// Lecturer's Name:		Jim Skrentny
// Lab Section: 		NA
//
//////////////////////////////////////////////////////////////////////////

/**
 * A Binary Tree implementation using BinaryTreenode(s)
 * 
 * <p> Bugs: none known 
 * 
 * @author mtessmann
 *
 * @param <E>
 */
public class BinaryTree<E> {

	private BinaryTreenode<E> rootNode = new BinaryTreenode<E>();
	private BinaryTreenode<E> currNode = new BinaryTreenode<E>();

	/**
	 * Constructs an empty BinaryTree with a null root.
	 */
	public BinaryTree() {
		rootNode.setData(null);
	}

	/**
	 * Constructs a BinaryTree with data stored in its root.
	 * @param data - data in root node
	 */
	public BinaryTree(E data) {
		rootNode.setData(data);
	}

	/**
	 * Starts the current reference at the root of the tree to 
	 * begin navigation of the tree.
	 */
	public void start() {
		currNode = rootNode;
	}

	/**
	 * Returns the data stored in the current node in navigation. 
	 * Throws an IllegalBinaryTreeOpException if there is no current node in navigation.
	 * @return <E> data stored in current node. 
	 * @throws IllegalBinaryTreeOpException - 
	 */
	public E getCurrent() throws IllegalBinaryTreeOpException {
		if (currNode.getData() == null) throw new IllegalBinaryTreeOpException("currentDoesntExist");
		return currNode.getData();
	}


	/**
	 * Moves the current reference to the left child of the current 
	 * node in navigation. Throws an IllegalBinaryTreeOpException 
	 * if the current node does not have a left child.
	 */
	public void goLeft() {
		currNode = currNode.getLeft();
	}


	/**
	 * Moves the current reference to the right child of the current 
	 * node in navigation. Throws an IllegalBinaryTreeOpException if 
	 * the current node does not have a right child.
	 */
	public void goRight() {
		currNode = currNode.getRight();
	}


	/**
	 * Returns true if the current node in navigation 
	 * is a leaf (i.e., has no children).
	 * @return - true if current node is a leaf
	 */
	public boolean isLeaf() {
		if (currNode.getLeft() == null && currNode.getRight() == null ) {
			return true;
		} else {
		return false;
		}
	}


	/**
	 * Changes the data held by the current node in navigation 
	 * to the specified data.
	 * @param data the object to set the current node's data to
	 */
	public void changeCurrent(E data) {
		currNode.setData(data);
	}


	/**
	 * Adds a node with the specified child as the right child of the 
	 * current node in navigation. Throws an IllegalBinaryTreeOpException 
	 * if the current node already has a right child.
	 * @param child - the data of the node to add as right child
	 * @throws IllegalBinaryTreeOpException 
	 */
	public void addRightChild(E child) throws IllegalBinaryTreeOpException {
		if (currNode.getRight() != null) throw new IllegalBinaryTreeOpException("rightChildExists");
		currNode.setRight(child);
	}


	/**
	 * Adds a node with the specified child as the left 
	 * child of the current node in navigation. 
	 * Throws an IllegalBinaryTreeOpException if the current 
	 * node already has a left child.
	 * @param child - the data of node to add as left child
	 * @throws IllegalBinaryTreeOpException 
	 */
	public void addLeftChild(E child) throws IllegalBinaryTreeOpException {
		if (currNode.getLeft() != null) throw new IllegalBinaryTreeOpException("leftChildExists");
		currNode.setLeft(child);
	}

	/**
	 * Pre-order prints the tree, starting at the root. 
	 * Each additional level of the tree should be 
	 * incremented by three spaces.
	 */
	public void print() {
		print(rootNode, "");
	}
	
	/**
	 * Pre-order prints the tree, starting at the root. 
	 * Each additional level of the tree should be 
	 * incremented by three spaces.
	 * 
	 * @param node - the node to print
	 * @param leadSpace - level of node, indicated by 3n spaces
	 */
	private void print(BinaryTreenode<E> node, String leadSpace) {
		if (node == null) return;
		System.out.println(leadSpace + node.getData());
		leadSpace += "   ";
		print(node.getLeft(), leadSpace);
		print(node.getRight(), leadSpace);
		
	}



}








