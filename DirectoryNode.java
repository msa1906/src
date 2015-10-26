
public class DirectoryNode {
	private String name;
	private DirectoryNode left;
	private DirectoryNode middle;
	private DirectoryNode right;
private boolean isFile=false;
	public DirectoryNode() {
	}

	public void setName(String name) {
		// The name member variable should be a full string with no spaces,
		// tabs, or any other whitespace.
		this.name = name;
	}

	public void setLeft(DirectoryNode left) {
		this.left = left;
	}

	public void setRight(DirectoryNode right) {
		this.right = right;
	}
public void setIsFile(){
	if(this.getLeft()==null&&this.middle==null&&this.right==null)this.isFile=false;
this.isFile =true;
}
	public void setMiddle(DirectoryNode middle) {
		this.middle = middle;
	}

	public String getName() {
		return this.name;
	}
public boolean getIsFile(){
	return this.isFile;
}
	public DirectoryNode getMiddle() {
		return this.middle;
	}

	public DirectoryNode getRight() {
		return this.right;

	}

	public DirectoryNode getLeft() {
		return this.left;
	}
public String toString(){
	return (this.isFile==true?"-"+this.getName():"|-"+this.getName());
}
	public void addChild(DirectoryNode node) throws NotADirectoryException, FullDirectoryException {
		if(node.isFile==true)throw new NotADirectoryException();
		if (this.getLeft() != null)
			this.setLeft(node);
		else {
			if (this.getMiddle() != null)
				this.setMiddle(node);
			else {
				if (this.getRight() != null)
					this.setRight(node);
				else
					throw new FullDirectoryException();
			}
		}
	}
}
