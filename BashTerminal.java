import java.util.*;
public class BashTerminal {
	
public static void main(String args[]){
	DirectoryTree data = new DirectoryTree();
	Scanner input =new Scanner(System.in);
	boolean flag = true;
	System.out.println("Starting bash terminal.");
	while (flag){
		System.out.print("[110033615@еке┐еп]: $ ");
		String stream = input.nextLine();
	switch(stream){
	case "pwd":System.out.println(data.presentWorkingDirectory()); break;
	case "ls": System.out.println(data.listDirectory());break;
	case "ls -R":data.printDirectoryTree();break;
	case "cd /":data.resetCursor();break;
	case "exit":flag = false; break;
	default:{
		try{
		if(stream.contains("cd "))data.changeDirectory(stream.substring(stream.indexOf(" ")+1));
		}catch (NotADirectoryException exc){
			System.out.println("Can't find this path");
		}
		try{
			if(stream.contains("mkdir "))data.makeDirectory(stream.substring(stream.indexOf(" ")+1));
		}catch(FullDirectoryException exc){
			System.out.println("This Dirctory is full!!");
		}
		catch (IllegalArgumentException exc){
			System.out.println("This name is illegal!");
		}
		try{
			if(stream.contains("touch "))data.makeFile(stream.substring(stream.indexOf(" ")+1));
		}catch(FullDirectoryException exc){
			System.out.println("This Dirctory is full!!");
		}catch (IllegalArgumentException exc){
			System.out.println("This name is illegal!");
		}
	}
	}}
	System.out.println("Program terminating normally");
	input.close();
	}
}

