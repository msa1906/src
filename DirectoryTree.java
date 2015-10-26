
public class DirectoryTree {
	private DirectoryNode root;
	private DirectoryNode cursor = root;

	public DirectoryTree() {
		root = new DirectoryNode();
		root.setName("root");
	}

	public void resetCursor() {
		cursor = root;
	}

	public DirectoryNode find(DirectoryNode node, String name) {
		if (node.getName().equals(name))
			return node;
		else {
			return find(node.getLeft(), name);
			return find(node.getMiddle(), name);
			return find(node.getRight(), name);
		}
	}

	public void changeDirectory(String name) throws NotADirectoryException {
		// NotADirectoryException
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
		if (node == cursor) {
			return a += node.getName();
		} else {
			if (node.getLeft() != null)
				 findPath(a+node.getName() + "/", node.getLeft());
			if (node.getMiddle() != null)
				a+= findPath(a +node.getName()+ "/", node.getMiddle());
			if (node.getRight() != null)
				a+=	 findPath(a + node.getName()+"/", node.getRight());
		}
		return "not exsist";
	}

	public String presentWorkingDirectory() {
		DirectoryNode a;
		a = root;
		String b = "root";
		return findPath(b, a);
	}

	public String listDirectory() {
		return String.format("%s %s %s", cursor.getLeft() != null ? cursor.getLeft().getName() : "",
				cursor.getMiddle() != null ? cursor.getMiddle().getName() : "",
				cursor.getRight() != null ? cursor.getRight().getName() : "");
	}
public void print(DirectoryNode a,int decent ){
	String b ="";
	for(int c =0;c<decent;c++){
		b+="	";
	}
	System.out.println(b+a.toString());
	if(a.getLeft()!=null)print(a.getLeft(),decent+1);
	if(a.getMiddle()!=null)print(a.getMiddle(),decent+1);
	if(a.getRight()!=null)print(a.getRight(), decent+1);
}
	public void printDirectoryTree() {
print(cursor, 0);
	}

	public void makeDirectory(String name) throws FullDirectoryException, IllegalArgumentException {

	}

	public void makeFile(String name) throws FullDirectoryException, IllegalArgumentException {

	}
}
