
public class DirectoryTree {
	private DirectoryNode root;
	private DirectoryNode cursor = root;

	public DirectoryTree() {
		root = new DirectoryNode("root", false);
		cursor = root;
	}

	public void resetCursor() {
		cursor = root;
	}

	public DirectoryNode find(DirectoryNode node, String name) {
		DirectoryNode a =null;
		if (node.getName().equals(name))
			return node;
		else {if(node.getLeft()!=null)
			a = find(node.getLeft(), name);
			if (a == null&&node.getMiddle()!=null)
				a = find(node.getMiddle(), name);
			if (a == null&&node.getRight()!=null)
				a = find(node.getRight(), name);
		}
		return a;
	}
//public void find(String name){
	//DirectoryNode a =null;
	//a = find(a, name);
	//System.out.println(this.findPath("", a));
//}
	public void changeDirectory(String name) throws NotADirectoryException {
		cursor = find(this.root, name);
		if (cursor == null) {
			System.out.println("This Direction doesn't exsist!");
			this.resetCursor();
		}
		if (cursor.getIsFile() == true) {
			this.resetCursor();
			throw new NotADirectoryException();
		}
		;
	}

	public String findPath(String a, DirectoryNode node) {
		String b =a;
		if (node == cursor) {
			return a+node.getName();
		} else {
			if (node.getLeft() != null)
				a=findPath(a + node.getName() + "/", node.getLeft());
			if (node.getMiddle() != null&&a.charAt(a.length()-1)=='/'){
			a=b;
				a = findPath(a + node.getName() + "/", node.getMiddle());
			}		if (node.getRight() != null&&a.charAt(a.length()-1)=='/'){
				a=b;
				a = findPath(a + node.getName() + "/", node.getRight());}
		}
		return  a;
	}

	public String presentWorkingDirectory() {
		String b = "";
		if(cursor==root)return "root";
		b=findPath(b,root);
		if(b.charAt(b.length()-1)=='/') return "not exsist";
		return b;
	}

	public String listDirectory() {
		return String.format("%s %s %s", cursor.getLeft() != null ? cursor.getLeft().getName() : "",
				cursor.getMiddle() != null ? cursor.getMiddle().getName() : "",
				cursor.getRight() != null ? cursor.getRight().getName() : "");
	}

	public void print(DirectoryNode a, int decent) {
		String b = "";
		for (int c = 0; c < decent; c++) {
			b += "	";
		}
		System.out.println(b + a.toString());
		if (a.getLeft() != null)
			print(a.getLeft(), decent + 1);
		if (a.getMiddle() != null)
			print(a.getMiddle(), decent + 1);
		if (a.getRight() != null)
			print(a.getRight(), decent + 1);
	}

	public void printDirectoryTree() {
		print(cursor, 0);
	}

	public void makeDirectory(String name) throws FullDirectoryException, IllegalArgumentException {
		if (name.contains(" ") || name.contains("/"))
			throw new IllegalArgumentException();
		try {
			cursor.addChild(new DirectoryNode(name, false));
		} catch (NotADirectoryException a) {
			System.out.println("not a directory node");
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
		}
		;
	}
}
