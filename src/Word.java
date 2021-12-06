/**
 * File: 	Word.java
 * Project: Project 3 - Index Creator
 * Instructions:  See README file
 */

import java.util.LinkedList;
import java.util.Queue;

public class Word implements Comparable<Word>
{
	
	private String wordText;
	private int count; //number of times wordText appears in indexed text
	private Queue<Integer> lineNumbers; //queue of line numbers in which wordText appears
	private static final int INDEX_TAB = 24; //constant used for spacing of output
	
	/**
	 * Default constructor. Sets wordText and count to defaults.
	 * Instantiates lineNumbers queue.
	 */
	public Word()
	{
		wordText = null;
		lineNumbers = new LinkedList<Integer>();
		count = 0;
	}
	
	/**
	 * Constructor with String parameter. Sets wordText to parameter.
	 * Sets count to default and instantiates lineNumbers queue.
	 * @param inWord
	 * 		stop word, or word to be compared and/or indexed, as a String
	 */
	public Word(String inWord)
	{
		this.wordText = inWord;
		this.lineNumbers = new LinkedList<Integer>();
		this.count = 0;
	}
	
	/**
	 * Adds parameter to lineNumbers queue. Increments word count.
	 * @param lineNumber
	 * 		line number on which word is found, as an integer
	 */
	public void countWord(int lineNumber)
	{
		this.lineNumbers.add(lineNumber); //add line number to queue
		this.count++; //increment word count
	}

	public String getWordText()
	{ return wordText; }

	public int getCount()
	{ return count; }
	
	public Queue<Integer> getLineNumbersQueue()
	{ return lineNumbers; }
	
	public void setWordText(String wordText)
	{ this.wordText = wordText; }
	
	public void setCount(int count)
	{ this.count = count; }
	
	public void setLineNumbersQueue(Queue<Integer> lineNumbers)
	{ this.lineNumbers = lineNumbers; }
	
	public void setSingleLineNumber(int lineNumber)
	{ this.lineNumbers.add(lineNumber); }
	
	/**
	 * Displays Word object as a String. If Word object has integers added to its queue,
	 * output is formatted to display wordText, periods as spacers, word count, and the
	 * queue contents. If Word's queue is empty, only wordText is displayed. 
	 */
	public String toString()
	{
		
		boolean debug = false;
		
		StringBuilder displayWord = new StringBuilder(); //used to compile output
		
		if(!this.lineNumbers.isEmpty()) //if Word's queue has contents; used for indexedBST
		{
			int tabAdjust; //number of periods to add between index word and word count
			
			//determine number of spaces needed to display count
			int countLength = 1 + (int)Math.log10(this.count);
			
			if(this.wordText.length() <= 22) //for words with 22 characters or less
			{
				//use INDEX_TAB to determine number of periods to add between index word and word count
				tabAdjust = INDEX_TAB - this.wordText.length() - countLength;
			}
			else //for words with 23 characters or more
			{
				//set tabAdjust to provide a smaller amount of padding for longer words
				tabAdjust = 1 + countLength;
			}
			
			//add appropriate number of periods to index word
			displayWord.append(this.padRight(tabAdjust, '.'));
			//add appropriate word count to index word and periods
			displayWord.append(Integer.toString(this.count) + ": ");
			
			if(debug)
			{
				System.out.println("countLength = " + countLength); //to check countLength calc
				System.out.println("Tab adjust is: " + tabAdjust); //to check tabAdjust calc
				System.out.println(displayWord.toString()); //to check spacing
			}
			
			//add queue output to index word and word count
			displayWord.append(QueueToString());
			
			if(debug)
			{ System.out.printf("%s\n%s\n", "word with queue is: ", displayWord.toString()); } //to check queue output
		}
		else //if Word has an empty queue; used for filteredBST of stop words
		{
			displayWord.append(this.getWordText());
			if(debug)
				System.out.println("tempWord is: " + displayWord.toString()); //to check output
		}
		return displayWord.toString();
	}
	
	/**
	 * Converts lineNumbers queue's contents to String format. A copy of the
	 * queue is first generated, since data in the queue must be removed
	 * during the conversion process. The copy is used to generate a 
	 * StringBuilder, which is then returned as a String.
	 * @return
	 * 		Queue contents, as a String
	 */
	private String QueueToString()
	{
		
		StringBuilder queueNumbers = new StringBuilder(); //used to compile output
		int temp = 0; //used to store line number value
		
		//create a copy of the lineNumbers queue
		Queue<Integer> lineNumbersCopy = new LinkedList<Integer>(this.lineNumbers);
		
		//go through queue copy's contents and add numbers to StringBuilder as necessary
		for(int i = lineNumbersCopy.size(); i > 0; i--)
		{
			if(lineNumbersCopy.peek() != temp) //if line number is a new line number
			{
				//add line number to StringBuilder
				queueNumbers.append((lineNumbersCopy.peek()) + " ");
			}
			else //if line number equals stored line number
				; //do nothing, number has already been added to StringBuilder
			temp = lineNumbersCopy.poll(); //set temp to head of queue copy and remove head
		}
		return queueNumbers.toString();
	}
	
	/**
	 * Pad String output so that wordText and word count are spaced correctly
	 * for each Word object. Replace spaces with char parameter provided.
	 * @param tabAdjust
	 * 		number of characters to add between wordText and count, as an integer
	 * @param c
	 * 		element to use as a spacer, as a char
	 * @return
	 * 		padded String
	 */
	public String padRight(int tabAdjust, char c)
	{
		return String.format(this.wordText + "%" + 
				(tabAdjust + "s"), " ").replace(" ", String.valueOf(c));	
	}
	
	/**
	 * Tests for equivalence of two Word objects, ignoring case.
	 * @param w
	 * 		Word object
	 * @return
	 * 		true if the two are equal (ignoring case)
	 */
	public boolean equals(Word w)
	{
		if(this.wordText.equalsIgnoreCase(w.wordText))
			return true;
		else
			return false;
	}
	
	/**
	 * Determines order of two Word objects, based on wordText
	 * values and ignoring case.
	 * @param w
	 * 		Word object
	 * @return
	 * 		0 if the two are the same;
	 * 		greater than 0 if the second object comes first;
	 * 		less than 0 if the first object comes first;
	 */
	public int compareTo(Word w)
	{ return this.wordText.compareToIgnoreCase(w.wordText); }
	
}
