
package binaryTree;


public class BinaryTree {

	private Node root;

	public void insert(int key, Object value) {
		this.root = this.insertNode(this.root, key, value);
	}

	private Node insertNode(Node root, int key, Object value) {
		if(root == null) {
			Node node = new Node();
			node.setKey(key);
			node.setValue(value);
			return node;
		} else {
			if(key < root.getKey()) {
				root.setLeft(this.insertNode(root.getLeft(), key, value));
			} else if(key > root.getKey()) {
				root.setRight(this.insertNode(root.getRight(), key, value));
			}
			return root;
		}
	}

	public Object get(int key) {
		return this.getNodeValue(this.root, key);
	}

	private Object getNodeValue(Node root, int key) {
		if(root != null) {
			if(key < root.getKey()) {
				return this.getNodeValue(root.getLeft(), key);
			} else if(key > root.getKey()) {
				return this.getNodeValue(root.getRight(), key);
			} else {
				return root.getValue();
			}
		}
		return null;
	}

	private String print(Node root, int level) {
		String output = "";
		for(int i=0; i<level; i++) {
			output += "\t";
		}
		output += root.getKey() + ": " + (root.getValue() != null ? root.getValue() : "null");
		output += "\n";
		output += (root.getLeft() != null ? print(root.getLeft(), level + 1) : "");
		output += (root.getRight() != null ? print(root.getRight(), level + 1) : "");
		return output;
	}

	@Override
	public String toString() {
		return this.print(this.root, 0);
	}

	public void remove(int key) {
		this.root = removeNode(this.root, key);
	}

	private Node removeNode(Node root, int key) {
		if (root == null) {
			return null;
		}

		if (key < root.getKey()) {
			root.setLeft(removeNode(root.getLeft(), key));
		} else if (key > root.getKey()) {
			root.setRight(removeNode(root.getRight(), key));
		} else {
			if (root.getLeft() == null && root.getRight() == null) {
				return null;
			} else if (root.getLeft() == null) {
				return root.getRight();
			} else if (root.getRight() == null) {
				return root.getLeft();
			} else {
				Node successor = findMinimum(root.getRight());
				root.setKey(successor.getKey());
				root.setValue(successor.getValue());
				root.setRight(removeNode(root.getRight(), successor.getKey()));
			}
		}

		return root;
	}

	private Node findMinimum(Node root) {
		while (root.getLeft() != null) {
			root = root.getLeft();
		}
		return root;
	}

	public void postOrder() {
		postOrderTraversal(this.root);
	}

	private void postOrderTraversal(Node root) {
		if (root != null) {
			postOrderTraversal(root.getLeft());
			postOrderTraversal(root.getRight());
			System.out.println(root.getKey() + ": " + root.getValue());
		}
	}

	public void preOrder() {
		preOrderTraversal(this.root);
	}

	private void preOrderTraversal(Node root) {
		if (root != null) {
			System.out.println(root.getKey() + ": " + root.getValue());
			preOrderTraversal(root.getLeft());
			preOrderTraversal(root.getRight());
		}
	}

	public void inOrder() {
		inOrderTraversal(this.root);
	}

	private void inOrderTraversal(Node root) {
		if (root != null) {
			inOrderTraversal(root.getLeft());
			System.out.println(root.getKey() + ": " + root.getValue());
			inOrderTraversal(root.getRight());
		}
	}

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree();


		binaryTree.insert(1, "a");
		binaryTree.insert(2, "b");
		binaryTree.insert(3, "c");
		binaryTree.insert(4, "d");
		binaryTree.insert(5, "e");
		binaryTree.insert(6, "f");
		binaryTree.insert(7, "g");
		System.out.println("Mostrar 2 e 7");
		System.out.println(binaryTree.get(2) + ", " + binaryTree.get(7));
		System.out.println("Remover 7");
		binaryTree.remove(7);
		System.out.println("Mostrar 2 e 7 depois removendo");
		System.out.println(binaryTree.get(2) + ", " + binaryTree.get(7));

		System.out.println("Transversal em ordem:");
		binaryTree.inOrder();
		System.out.println("Transversal de pré-ordem:");
		binaryTree.preOrder();
		System.out.println("Transversal pós ordem:");
		binaryTree.postOrder();
	}
}