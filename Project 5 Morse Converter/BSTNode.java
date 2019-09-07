import java.util.ArrayList;

/**
 * @author < Netta Paulson > Computer Science Department College of Engineering
 *         Virginia Commonwealth University Project 5: MorseCode This project
 *         creates the BSTNode class that creates nodes and can add or remove
 *         them from a tree. 4/30/19 CMSC 256, 003
 */
public class BSTNode<K extends Comparable<K>, V> extends BinaryNode<K, V> {

	/*
	 * This is the parameterized constructor for BSTNode which creates a node
	 * 
	 * @param K aKey the given key for the node
	 * 
	 * @param V aValue the given value for the node
	 */
	public BSTNode(K aKey, V aValue) {
		super(aKey, aValue);
	}

	/*
	 * This is the parameterized constructor for BSTNode which creates a node with
	 * given left and right children given
	 * 
	 * @param K aKey the given key for the node
	 * 
	 * @param V aValue the given value for the node
	 * 
	 * @param BinaryNode<K,V> aLeft the left child of the created node
	 * 
	 * @param BinaryNode<K,V> aRight the right child of the created node
	 */
	public BSTNode(K aKey, V aValue, BinaryNode<K, V> aLeft, BinaryNode<K, V> aRight) {
		super(aKey, aValue, aLeft, aRight);
	}

	/*
	 * This method adds a value to the tree, and calls the next add method
	 * 
	 * @param K aKey the given key for the node
	 * 
	 * @param V aValue the given value for the node
	 */
	public void add(K aKey, V aValue) {
		add(aKey, aValue, this);
	}

	/*
	 * This method adds a value to the tree from a given root
	 * 
	 * @param K aKey the given key for the node
	 * 
	 * @param V aValue the given value for the node
	 * 
	 * @param BinaryNode<K,V> root the given root to begin from
	 */
	private void add(K aKey, V aValue, BinaryNode<K, V> root) {
		if (aKey.compareTo(root.key) < 0) {
			if (root.left != null) {
				add(aKey, aValue, root.left);
			} else {
				root.left = new BinaryNode<K, V>(aKey, aValue);
			}
		} else if (aKey.compareTo(root.key) > 0) {
			if (root.right != null) {
				add(aKey, aValue, root.right);
			} else {
				root.right = new BinaryNode<K, V>(aKey, aValue);
			}
		} else {
			throw new IllegalStateException("All keys must be unique");
		}
	}

	/*
	 * This method searches a given key and calls the next search method and returns
	 * the value at that key
	 * 
	 * @param K aKey the key to find the value at
	 * 
	 * @returns V the value at the key
	 */
	public V search(K aKey) {
		return search(aKey, this);
	}

	/*
	 * This method recursively searches the tree at a given key and starting at the
	 * root and returns the foudn value
	 * 
	 * @param K aKey the given key to find the value at
	 * 
	 * @param BinaryNode<K,V> root the root from which to start the search
	 * 
	 * @returns V root.value the value at the given key
	 */
	private V search(K aKey, BinaryNode<K, V> root) {
		if (root == null) {
			return null;
		}

		if (aKey.compareTo(root.key) < 0) {
			return search(aKey, root.left);
		} else if (aKey.compareTo(root.key) > 0) {
			return search(aKey, root.right);
		} else {
			return root.value;
		}
	}

	/*
	 * This method removes the node at the given key, and calls the next remove
	 * method
	 * 
	 * @param K aKey the key of the node to remove
	 * 
	 * @returns V the removed node
	 */
	public V remove(K aKey) {
		return remove(aKey, this, null, true);
	}

	/*
	 * This method removes a node based on a given key
	 * 
	 * @param K aKey the key of the node to be removed
	 * 
	 * @param BinaryNode<K,V> root the given root to start searching the tree
	 * 
	 * @param BinaryNode<K,V> parent the variable to store the parent node
	 * 
	 * @param boolean left set to true is the key is smaller than the root
	 * 
	 * @returns V root.value the value of the node that is removed
	 */
	private V remove(K aKey, BinaryNode<K, V> root, BinaryNode<K, V> parent, boolean left) {
		// Use recursion to find the node being removed. If the node is not in the tree
		// return null.
		// Recursive calls keep track of the current node, its parent,
		// and if the current node is the left or right child of the parent.

		if (aKey.compareTo(root.key) < 0) {
			if (root.left != null) {
				remove(aKey, root.left, root, true);
			} else {
				return null;
			}
		} else if (aKey.compareTo(root.key) > 0) {
			if (root.right != null) {
				remove(aKey, root.right, root, false);

			} else {
				return null;
			}
		}
		// This condition is only reached when the node to be removed is found.
		else {
			// newChild is the node replacing the removed node.
			// More formally, it is the new child of the removed nodes parent.
			// Which child (left or right) is determined by the left parameter.
			BinaryNode<K, V> newChild = null;
			// Here we implement our removal algorithms
			// In the following code the "root" is the node being removed
			// If the root has no children, the newChild is null
			if (root.left == null && root.right == null) {
				newChild = null;
			}
			// If the root has both a left and right child...
			else if (root.left != null && root.right != null) {
				// We find the smallest node in the removed nodes right subtree

				BinaryNode<K, V> smallest = root.right;
				BinaryNode<K, V> smallestParent = root;
				while (smallest.left != null) {
					smallestParent = smallest;
					smallest = smallest.left;
				}

				// If the root of the removed nodes right subtree is the smallest value in the
				// subtree
				// Set the removed node's right child to its right grand-child.

				if (root.right.left == null) {
					root.right = smallest.right;
				}

				// Else, find the smallest value in the removed nodes right subtree
				// When the smallest node is found, take its right subtree and make it it's
				// parents left child.
				// If the smallest node does not have a right subtree, then we make its parents
				// left child null.
				// This is done to remove the smallest node from the tree (so it doesn't show up
				// twice)

				else {
					if (smallest.right != null) {
						smallestParent.left = smallest.right;
					} else {
						smallestParent.left = null;
					}
				}

				// Make the newChild the smallest node in the removed nodes right subtree

				newChild = smallest;

				// Make the newChild's children the children of the removed node

				newChild.left = root.left;
				newChild.right = root.right;

			}
			// If the root has a left child, make the new child the root's left child
			else if (root.left != null) {
				newChild = root.left; // TODO
			}
			// If the root has a right child, make the new child the roots' right child
			else if (root.right != null) {
				newChild = root.right; // TODO
			}

			if (parent == null) {
				if (newChild == null) {
					throw new IllegalStateException("Can't remove root from tree of height zero");
				}
				V toReturn = root.value;
				this.key = newChild.key;
				this.value = newChild.value;
				this.left = newChild.left;
				this.right = newChild.right;
				return toReturn;
			}
			if (left) {
				parent.left = newChild;
			} else {
				parent.right = newChild;
			}
			return root.value;
		}
		return root.value;
	}

	/*
	 * This method traverses the tree and returns an ArrayList of Morse code
	 * characters
	 * 
	 * @returns ArrayList<K> list ordered Morse code characters from least to
	 * greatest
	 */
	public ArrayList<K> Inorder() {
		ArrayList<K> list = new ArrayList<K>();
		return Inorder(list, this);
	}

	/*
	 * This method traverses the tree and returns an ArrayList of Morse code
	 * characters
	 * 
	 * @param ArrayList<K> list an ArrayList to store the values
	 * 
	 * @param BinaryNode<K, V> root the first node to begin traversal
	 * 
	 * @returns ArrayList<K> list filled with the characters in ascending order
	 */
	public ArrayList<K> Inorder(ArrayList<K> list, BinaryNode<K, V> root) {

		if (root == null) {
			return null;
		}

		Inorder(list, root.left);

		list.add(root.key);

		Inorder(list, root.right);

		return list;
	}

}