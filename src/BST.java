import java.util.ArrayList;

public class BST<E extends Comparable<E>> {
	//data members
	private TreeNode root;
	private int size;
	//private class to instantiate a TreeNode
	private class TreeNode{
		E value;
		TreeNode left;
		TreeNode right;
		TreeNode(E val){
			value = val;
			left = right = null;
			}
	}
	//Constructor with no parameters
	BST(){ 
		root = null; 
		size = 0; 
	}
	/**
	 * Constructor with 1 parameter
	 * @param dataArray to be converted to the BST with insert()
	 */
	BST(ArrayList<E> list){
		for(int i=0; i<list.size(); i++)
			insert(list.get(i));
	}
	/**
	 * Getter
	 * @return returns size
	 */
	public int size() { 
		return size; 
	}
	/**
	 * Checks if empty
	 * @return boolean result of operation
	 */
	public boolean isEmpty() { 
		return (size == 0); 
	}
	/**
	 * Clears BST
	 */
	public void clear() { 
		root = null; 
	}
	/**
	 * Returns the number of iterations for the search
	 * @param item value searched for
	 * @return int number of iterations
	 */
	public int searchIterations(E item) {
		int iterations = 0;
		TreeNode current = root;
		while (current != null) {
			iterations++;
			if( item.compareTo(current.value) < 0)
				current = current.left;
			else if (item.compareTo(current.value)> 0)
				current = current.right;
			else // equals
				return iterations;
		}
		return iterations;
	}
	/**
	 * Basic search for an item E
	 * @param item to be searched for
	 * @return boolean result of operation
	 */
	// Method search()
	public boolean search(E item) {
		TreeNode current = root;
		while (current != null) {
			if( item.compareTo(current.value) < 0)
				current = current.left;
			else if (item.compareTo(current.value)> 0)
				current = current.right;
			else // equals
				return true;
		}
		return false;
	}
	/**
	 * Inserts item in BST and makes rearrangements if necessary
	 * @param item to be inserted
	 * @return returns boolean result of operation
	 */
	
	public int insert(E item) {
		int iterations = 0;
		if (root == null) // first node to be inserted
			root = new TreeNode(item);
			
		else {
			TreeNode parent, current;
			parent = null; 
			current = root;
			while (current != null) {// Looking for a leaf node     
				iterations++;
				parent = current;
				if(item.compareTo(current.value) < 0) {
					current = current.left; 
				}
				else if (item.compareTo(current.value) > 0) {
					current = current.right; 
				}
				else
					return iterations; // duplicates are not allowed
			}
			if (item.compareTo(parent.value)< 0)
				parent.left = new TreeNode(item);
			else
				parent.right = new TreeNode(item);
		}
		size++;  
		return iterations; 
	}
	
	/**
	 *  Method delete() which deletes item and makes rearrangements as necessary
	 * @param item to be deleted
	 * @return boolean result of operation
	 */
	public int delete(E item) {
		int iterations = 0;
		TreeNode parent, current;
		parent = null; current = root;
		// Find item first
		while (current != null) {
			iterations++;
			parent = current;
			if (item.compareTo(current.value) < 0) {
				current = current.left; 
			}
			else if (item.compareTo(current.value) > 0) { 
				current = current.right;
			}
			else
				break; // item found}
		}
		if (current == null) // item not in the tree
			return iterations;
		// Case 1: node found and has no left child
		if(current.left == null){ // No left child
			if (parent == null) // delete root
				root = current.right;
			else {// modify link from parent to current’s child
				if (item.compareTo(parent.value) < 0 )
					parent.left = current.right;
				else
					parent.right = current.right;
			}
		}
		
		else { //Case 2: current has a left child
			TreeNode rightMostParent = current;
			TreeNode rightMost = current.left;
			// going right on left subtree
			while (rightMost.right != null) {
				iterations++;
				rightMostParent = rightMost;
				rightMost = rightMost.right;
			}
			current.value = rightMost.value;
			//delete rigthMost node
			if (rightMostParent.right == rightMost)
				rightMostParent.right = rightMost.left;
			else
				rightMostParent.left = rightMost.left;
		}
			size--;
			return iterations;
	}
	/**
	 * Recursive method leafNodes()
	 * @return returns a call with the parameter 'root'
	 */
	public int leafNodes() {
		return leafNodes(root);
	}
	/**
	 * Definition of method leafNodes()
	 * @param node starting point
	 */
	public int leafNodes(TreeNode node) {
		if (node == null) {
			return 0;
		}
		if(node.left == null && node.right == null) {
			return 1;
		}
		else {
			return leafNodes(node.left) + leafNodes(node.right);
		}
	}
	/**
	 * Recursive method height()
	 * @return returns a call with the parameter 'root'
	 */
	public int height() {
		return height(root);
	}
	/**
	 * Definition of method height()
	 * @param node starting point
	 */
	public int height(TreeNode node) {
		if (node == null) {
			return 0;
		}		
		else {
			int leftDepth = height(node.left);
			int rightDepth = height(node.right);
			
			if (leftDepth > rightDepth) {
				return leftDepth +1;
			}
			else {
				return rightDepth +1;
			}
		}
	}
	/**
	 * Recursive method isBalanced()
	 * @return returns a call with the parameter 'root'
	 */
	public boolean isBalanced() {
		return isBalanced(root);
	}
	/**
	 * Definition of method isBalanced()
	 * @param node starting point
	 */
	public boolean isBalanced(TreeNode node) {
		
		if (node == null) {
			return true;
		}	
		else {
			int leftheight = height(node.left);
			int rightheight = height(node.right);
			if(leftheight - rightheight > 1 || rightheight - leftheight > 1){
	            return false;
	        }
	    }
	    return true;
	}
		
	
	/**
	 *  Recursive method inorder()
	 */
	public void inorder() {
		inorder(root);
	}
	/**
	 * Definition of method inorder()
	 * @param node starting point
	 */
	private void inorder(TreeNode node) {
		if (node != null) {
			inorder(node.left);
			System.out.print(node.value.toString() );
			inorder(node.right);
		}
	}
		
	// Recursive method preorder()
	public void preorder() {
		preorder(root);
	}
	/**
	 * Definition of method preorder()
	 * @param node starting point
	 */
	private void preorder(TreeNode node) {
		if (node != null) {
			System.out.print(node.value + " ");
			preorder(node.left);
			preorder(node.right);
		}
	}
	/**
	 *  Recursive method postorder()
	 */
	public void postorder() {
		postorder(root);
	}
	/**
	 * Definition of method postorder()
	 * @param node starting point
	 */
	private void postorder(TreeNode node){
		if (node != null) {
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.value + " ");
		}
	}
	
}
			

	

	
		


