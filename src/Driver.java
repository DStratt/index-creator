/**
 * File: 	Driver.java
 * Project: Project 3 - Index Creator
 * Instructions:  See README file
 */

public class Driver
{ //start of class Driver

	public static void main(String[] args)
	{ //start of method main
		
		String stopFile = null; //filename for stop words file
		String textFile = null; //filename for text file to be indexed
		
		try //detect ArrayIndexOutOfBoundsException
		{
			if(args[0] != null) //if user specifies a stopFile filename
			{ stopFile = args[0]; } //set stopFile to specified filename
		}
		catch(ArrayIndexOutOfBoundsException e) //handle exception
		{ stopFile = "stopFile.txt"; } //set stopFile to default filename
		
		try //detect ArrayIndexOutOfBoundsException
		{
			if(args[1] != null) //if user specifies a textFile filename
			{ textFile = args[1]; } //set textFile to specified filename
		}
		catch(ArrayIndexOutOfBoundsException e) //handle exception
		{ textFile = "textFile.txt"; } //set textFile to default filename
		
		//Declare and instantiate a new Indexer object using filename variables
		Indexer index = new Indexer(stopFile, textFile);
		
	} //end of method main

} //end of class Driver
