/**
 * The <code>Heap</code> class implements a heap of <code>HeapItem</code>
 * objects.
 * 
 *
 * @author Mingtong Wu e-mail: Mingtong.wu@stonybrook.edu Stony Brook
 *         ID:110033615
 **/
public class DirectoryTree {
	private DirectoryNode root;
	private DirectoryNode cursor = root;

	public DirectoryTree() {
		root = new DirectoryNode("root", false);
		cursor = root;
	}

	public DirectoryNode getRoot() {
		return this.root;
	}

	public void resetCursor() {
		cursor = root;
	}

	public DirectoryNode find(DirectoryNode node, String name) {
		DirectoryNode a = null;
		for (int b = 0; b < 10; b++) {
			if (node.getNode(b) != null && node.getNode(b).getName().equals(name))
				a = node.getNode(b);
		}
		return a;
	}

	public void findPrint(DirectoryNode node, String name) {
		if (node.getName().equals(name))
			System.out.println(this.findPathPrint("", root, node));
		for (int b = 0; b < 10; b++) {
			if (node.getNode(b) != null)
				findPrint(node.getNode(b), name);
		}
		
	}

	public String findPathPrint(String a, DirectoryNode node, DirectoryNode target) {
		String b = a;
		if (node == target) {
			return a + node.getName();
		} else {
			for (int c = 0; c < 10; c++) {

				if (c == 0 && node.getNode(c) != null)
					a = findPathPrint(a + node.getName() + "/", node.getNode(c), target);
				else {
					if (node.getNode(c) != null && (a.charAt(a.length() - 1) == '/')) {
						a = b;
						a = findPathPrint(b + node.getName() + "/", node.getNode(c), target);
					}
				}
			}
		}
		return a;
	}

	public void mv(String a, String b) {
		DirectoryNode c;
		String name = a;

		this.resetCursor();
		this.cdPath(a.substring(a.indexOf(" ") + 6));
		c = cursor;
		this.cdParent(root, root);
		do {
			name = name.substring(name.indexOf('/') + 1);
		} while (name.contains("/"));
		cursor.remove(name);
		this.resetCursor();
		this.cdPath(b.substring(b.indexOf(" ") + 6));
		try {
			cursor.addChild(c);

		} catch (NotADirectoryException exc) {
			System.out.println("not a directory node");
		} catch (SanmeNameException exc) {
			System.out.println("Same name forder!");
		} catch (FullDirectoryException exc) {
			System.out.println("This Dirctory is full!!");
		}
	}

	public void changeDirectory(String name) throws NotADirectoryException {
		cursor = find(cursor, name);
		if (cursor == null) {
			System.out.println("This Direction doesn't exsist!\n reset cursor to root");
			this.resetCursor();
		}
		if (cursor.getIsFile() == true) {
			this.resetCursor();
			throw new NotADirectoryException();
		}
		;
	}

	public String findPath(String a, DirectoryNode node) {
		String b = a;
		if (node == cursor) {
			return a + node.getName();
		} else {
			for (int c = 0; c < 10; c++) {
				if (c == 0 && node.getNode(c) != null)
					a = findPath(a + node.getName() + "/", node.getNode(c));
				else {
					if (a.charAt(a.length() - 1) == '/' && node.getNode(c) != null) {
						a = b;
						a = findPath(a + node.getName() + "/", node.getNode(c));
					}
				}
			}
		}
		return a;
	}

	public String presentWorkingDirectory() {
		String b = "";
		if (cursor == root)
			return "root";
		b = findPath(b, root);
		if (b.charAt(b.length() - 1) == '/')
			return "not exsist";
		return b;
	}

	public String listDirectory() {
		String b = "";
		for (int a = 0; a < 10; a++) {
			if (cursor.getNode(a) != null)
				b += String.format("%s ", cursor.getNode(a) != null ? cursor.getNode(a).getName() : "");
		}
		return b;
	}

	public void print(DirectoryNode a, int decent) {
		String b = "";
		for (int c = 0; c < decent; c++) {
			b += "	";
		}
		System.out.println(b + a.toString());
		for (int c = 0; c < 10; c++) {
			if (c == 1 && a.getNode(c) != null)
				print(a.getNode(c), decent + 1);
			else {
				if (a.getNode(c) != null)
					print(a.getNode(c), decent + 1);
			}
		}
	}

	public void printDirectoryTree() {
		print(cursor, 0);
	}

	public void cdPath(String stream) {
		try {
			if (!stream.contains("/"))
				this.changeDirectory(stream);

			else {
				this.changeDirectory(stream.substring(0, stream.indexOf('/')));
				cdPath(stream.substring(stream.indexOf('/') + 1));
			}
		} catch (NotADirectoryException exc) {
			this.resetCursor();
			System.out.println("Can't find this path\n reset cursor to root");
		}
	}

	public void cdParent(DirectoryNode node, DirectoryNode pre) {
		if(cursor==root)System.out.println("ERROR: Already at root directory.");
		else{if (node == cursor) {
			cursor = pre;
		}
		for (int a = 0; a < 10; a++) {
			if (node.getNode(a) != null)
				cdParent(node.getNode(a), node);
		}
	}}

	public void makeDirectory(String name) throws FullDirectoryException, IllegalArgumentException {
		if (name.contains(" ") || name.contains("/"))
			throw new IllegalArgumentException();
		try {
			cursor.addChild(new DirectoryNode(name, false));
		} catch (NotADirectoryException a) {
			System.out.println("not a directory node");
		} catch (SanmeNameException b) {
			System.out.println("Same name forder!");
		}
		;
	}

	public void makeFile(String name) throws FullDirectoryException, IllegalArgumentException {
		if (name.contains(" ") || name.contains("/"))
			throw new IllegalArgumentException();
		try {
			cursor.addChild(new DirectoryNode(name, true));
		} catch (NotADirectoryException a) {
			System.out.println("not a directory node");
		} catch (SanmeNameException b) {
			System.out.println("Same name forder!");
		}
		;
	}
}
