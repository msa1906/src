
/**
* The <code>Heap</code> class implements a heap of <code>HeapItem</code>
* objects.
*    
*
* @author Mingtong Wu 
*    e-mail: Mingtong.wu@stonybrook.edu	
*    Stony Brook ID:110033615
**/
import java.util.*;

public class BashTerminal {

	public static void main(String args[]) {
		DirectoryTree data = new DirectoryTree();
		Scanner input = new Scanner(System.in);
		boolean flag = true;
		System.out.println("Starting bash terminal.");
		while (flag) {
			System.out.print("[110033615@еке┐еп]: $ ");
			String stream = input.nextLine();
			switch (stream) {
			case "pwd":
				System.out.println(data.presentWorkingDirectory());
				break;
			case "ls":
				System.out.println(data.listDirectory());
				break;
			case "ls -R":
				data.printDirectoryTree();
				break;
			case "cd /":
				data.resetCursor();
				break;
			case "exit":
				flag = false;
				break;
			case "cd..":
				data.cdParent(data.getRoot(), data.getRoot());
				break;
			default: {
if(stream.contains("mv ")){
	stream = stream.substring(stream.indexOf(' ')+1);
	String rec =stream.substring(0, stream.indexOf(' '));
	String teg = stream.substring(stream.indexOf(' ')+1);
	data.mv(rec,teg) ;
}
				if (stream.contains("cd ")) {
					if (!stream.contains("/"))
						try {
							data.changeDirectory(stream.substring(stream.indexOf(" ") + 1));
						} catch (NotADirectoryException exc) {
							System.out.println("ERROR: Cannot change directory into a file.");
						}
					else {
						if (stream.contains("cd "))
							data.cdPath(stream.substring(stream.indexOf(" ") + 6));
					}
				}
				try {
					if (stream.contains("mkdir "))
						data.makeDirectory(stream.substring(stream.indexOf(" ") + 1));
				} catch (FullDirectoryException exc) {
					System.out.println("ERROR: Present directory is full");
				} catch (IllegalArgumentException exc) {
					System.out.println("ERROR: This name is illegal!");
				}
				try {
					if (stream.contains("touch "))
						data.makeFile(stream.substring(stream.indexOf(" ") + 1));
				} catch (FullDirectoryException exc) {
					System.out.println("This Dirctory is full!!");
				} catch (IllegalArgumentException exc) {
					System.out.println("This name is illegal!");
				}
				if (stream.contains("find "))
					data.findPrint(data.getRoot(), stream.substring(stream.indexOf(" ") + 1));
			}
			}
		}
		System.out.println("Program terminating normally");
		input.close();
	}
}
