/**
 * The <code>Heap</code> class implements a heap of <code>HeapItem</code>
 * objects.
 * 
 *
 * @author Mingtong Wu e-mail: Mingtong.wu@stonybrook.edu Stony Brook
 *         ID:110033615
 **/
public class DirectoryNode {
	private String name;
	private DirectoryNode[] node= new DirectoryNode[10];
	private boolean isFile = false;

	public DirectoryNode(String name, boolean isFile) {
		this.setName(name);
		this.isFile = isFile;
	}

	public void setName(String name) {
		// The name member variable should be a full string with no spaces,
		// tabs, or any other whitespace.
		this.name = name;
	}

	public void setNode(DirectoryNode node, int a) {
		this.node[a] = node;
	}

	public String getName() {
		return this.name;
	}

	public boolean getIsFile() {
		return this.isFile;
	}

	public DirectoryNode getNode(int a) {
		return this.node[a];
	}

	public String toString() {
		return (this.isFile == true ? "-" + this.getName() : "|-" + this.getName());
	}
public void remove(String name){
	for (int a =0; a<10;a++){
		if(this.node[a]!=null&&this.node[a].getName().equals(name))this.node[a]=null;
	}
}
	public void addChild(DirectoryNode node) throws NotADirectoryException, FullDirectoryException, SanmeNameException {
		if (this.isFile == true)
			throw new NotADirectoryException();
		int a;
			for (a = 0; a < 10 ; a++) {
				if (this.node[a] != null&&this.node[a].getName().equals(node.getName()))
					throw new SanmeNameException();
			}
		for (a = 0; a < 10; a++) {
			if (this.node[a] == null) {
				this.setNode(node, a);
				a = 10;
			}
		}
		if (a == 10)
			throw new FullDirectoryException();
	}
}
