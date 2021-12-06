/**
 * File: 	Indexer.java
 * Project: Project 3 - Index Creator
 * Instructions:  See README file
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * Class to build binary search trees from input files of stop words
 * and text to be indexed, and output each tree to a separate file.
 */

public class Indexer
{
	
	private BinarySearchTree<Word> filteredBST; //contains words to be filtered out from index
	private BinarySearchTree<Word> indexedBST; //contains indexed words
	private String filterFile; //filename of stop words list
	private String dataFile; //filename of text file to be indexed
	
	private String filterOutFile = "filterResults.txt"; //output filename (specified in instructions)
	private String indexOutFile = "indexResults.txt"; //output filename (specified in instructions)
	
	private boolean debug = false;
	
	/**
	 * Constructor. Sets filterFile and dataFile variables to the parameters provided.
	 * Verifies that both files are present and can be read. If so, calls doIndex method.
	 * If not, displays error messages and exits program.
	 * @param stopFile
	 * 		filename of stop words list, as a String
	 * @param textFile
	 * 		filename of the text to be indexed, as a String
	 */
	public Indexer(String stopFile, String textFile)
	{
		
		filterFile = stopFile; //set filterFile to stop words filename
		dataFile = textFile; //set dataFile to filename of text to be indexed
		
		if((!FileExists(filterFile)) || (!FileExists(dataFile)))
		{ //if either file is not there or cannot be read, display message and exit
			System.out.printf("%s\n", "Exiting the program.");
			System.exit(0); 
		}
		else //if both files are present and readable
			doIndex();
		
	}
	
	/**
	 * Calls method to create filteredBST. If filteredBST is
	 * not empty, calls method to create indexedBST. If indexedBST
	 * is not empty, calls output method to generate output files.
	 */
	public void doIndex()
	{
		if(!FileFilterReader(filterFile).isEmpty())
		{
			if(!FileWordReader(dataFile).isEmpty())
			{ OutputResults(); }
		}
	}
	
	/**
	 * Tests whether file is present and readable. Displays message for
	 * each case.
	 * @param filename
	 * 		.txt filename, as a String
	 * @return
	 * 		true if file is present and is readable. Otherwise, false.
	 */
	private boolean FileExists(String filename)
	{
		
		boolean fileFound = true; //boolean return variable
		
		File fileObject = new File(filename);
		if(!fileObject.exists()) //if file does not exist
		{
			fileFound = false; //set return variable to false
			System.out.printf("%s%s%s\n", "File of filename ", filename, " not found.");
		}
		else if(!fileObject.canRead()) //if file cannot be read
		{
			fileFound = false; //set return variable to false
			System.out.printf("%s%s\n", "Unable to read from file ", filename);
		}
		return fileFound;
	}
	
	/**
	 * Reads stop words from .txt file and adds to filteredBST.
	 * Preconditions:
	 * 		.txt file saved to same package/directory as Project 3 .java files;
	 * 		.txt file has one word per line;	
	 * @param filterFile
	 * 		Filename of stop words list, as a String
	 * @return
	 * 		filteredBST, a BST of stop words
	 */
	private BinarySearchTree<Word> FileFilterReader(String filterFile)
	{
		
		filteredBST = new BinarySearchTree<Word>(); //instantiate filteredBST
		Scanner infile = null;
		
		try //detect FileNotFoundException
		{ infile = new Scanner(new FileReader(filterFile)); } //open input stream
		catch(FileNotFoundException e) //handle exception
		{
			System.out.println("File not found");
			e.printStackTrace();
			System.exit(0);
		}
		while(infile.hasNextLine())
		{
			Word nextWord = new Word(infile.nextLine()); //create Word object from infile line
			filteredBST.insert(nextWord); //add Word object to filteredBST
		}
		infile.close(); //close input stream
		return filteredBST;
	}
	
	/**
	 * Reads text to be indexed from .txt file. Removes punctuation from and tokenizes each
	 * line. For each token, ignores token if token begins with a number. If token doesn't
	 * begin with a number, compares token to BST of stop words, and ignores token if token
	 * is a stop word. Otherwise, compares token to BST of indexed words. If not found,
	 * token's count and line number queue are updated and token is added to index BST.
	 * If found, count and line number queue of existing BST node are updated.
	 * Preconditions:
	 * 		.txt file saved to same package/directory as Project 3 .java files;
	 * @param dataFile
	 * 		Filename of text file, as a String
	 * @return
	 * 		indexedBST, a BST of indexed words
	 */
	private BinarySearchTree<Word> FileWordReader(String dataFile)
	{
		
		indexedBST = new BinarySearchTree<Word>(); //instantiate indexedBST
		int lineNumber = 0; //variable to track current line number of input file
		Word ignore = new Word(); //Word object to contain ignored tokens
		
		Scanner infile = null;
		
		try //detect FileNotFoundException
		{ infile = new Scanner(new FileReader(dataFile)); } //open input stream
		catch(FileNotFoundException e) //handle exception
		{
			System.out.println("File not found");
			e.printStackTrace();
			System.exit(0);
		}
		while(infile.hasNextLine())
		{
			
			String line = infile.nextLine(); //create String object from infile line
			lineNumber++; //increment lineNumber
			line = Utility.editString(line); //remove double quotes and most punctuation
			
			if(debug) //display edited line
			{ System.out.println("Line is: " + line); }
			
			//create tokenizer with space set as delimiter
			StringTokenizer tokenizer = new StringTokenizer(line, " ");
			
			while(tokenizer.hasMoreTokens()) //while line has more words
			{
				
				String next = tokenizer.nextToken(); //create String from next token

				if(!StartsWithDigit(next)) //if next does not start with a digit
				{
					
					Word token = new Word(next); //create Word object using next
					if(!filteredBST.contains(token)) //if token is not in Stop List
					{
						if(!indexedBST.isEmpty()) //if indexedBST has been initialized
						{
							if(!indexedBST.contains(token)) //if token not found in index
							{
								if(debug) //display message that token not found in index
								{ System.out.println("Token " + token.getWordText() + " not in index"); }
								
								token.countWord(lineNumber); //increment queue and count for token
								indexedBST.insert(token); //add token to index
							}
							else //if token found in index
							{ 
								//increment queue and count of matching node in index
								indexedBST.findMatchingNode(token).countWord(lineNumber);
								
								if(debug) //display token and token's count from indexedBST
								{
									System.out.println("Token " + indexedBST.findMatchingNode(token).getWordText() + 
											" in index");
									System.out.println("Count in tree is " + 
											indexedBST.findMatchingNode(token).getCount());
								}
							}	
						}
						else //if indexedBST is empty
						{ 
							token.countWord(lineNumber); //increment queue and count for token
							indexedBST.insert(token); //add token to index 
						}
					}
					else //if token is in Stop List
					{ ignore = token; } //set ignore variable to token 
				
				}
				else //if next starts with a digit
				{ ; } //do nothing; get next token
				
			}
		}
		infile.close(); //close input stream
		return indexedBST;
	}
	
	/**
	 * Determines if a word starts with a digit.
	 * @param next
	 * 		Word from input text, as a String
	 * @return
	 * 		true if word starts with a digit, otherwise false
	 */
	private boolean StartsWithDigit(String next)
	{
		if(Character.isDigit(next.charAt(0)))
			return true;
		else
			return false;
	}
	
	/**
	 * Displays a message that tree has been constructed, and
	 * writes trees to output files. Can also display to screen
	 * using debug code.
	 */
	private void OutputResults()
	{
		
		System.out.println("filteredBST contents are ready.");
		WriteTreeToFile(filteredBST, filterOutFile);
		System.out.println("indexedBST contents are ready.");
		WriteTreeToFile(indexedBST, indexOutFile);
		
		if(debug) //print trees to screen
		{
			filteredBST.printTree();
			indexedBST.printTree();
		}
	}
	
	/**
	 * Writes tree to specified output file. Declares and instantiates
	 * PrintWriter then calls writeTree() method.
	 * @param BSTree
	 * 		Binary Search Tree, of type Word
	 * @param outputFile
	 * 		output filename, as a String
	 */
	private void WriteTreeToFile(BinarySearchTree<Word> BSTree, String outputFile)
	{
		
		PrintWriter output = null;
		try //detect FileNotFoundException
		{ output = new PrintWriter(outputFile); } //open output stream
		catch (FileNotFoundException e) //handle exception
		{ 
			System.out.println("File not found");
			e.printStackTrace();
			System.exit(0);
		}
		BSTree.writeTree(output); //write tree to file
		output.close(); //close output stream
	}
		
}
