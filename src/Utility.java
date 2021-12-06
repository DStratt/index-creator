/**
 * File: 	Utility.java
 * Project: Project 3 - Index Creator
 * Instructions:  See README file
 */

/*
 * Class removes unwanted punctuation and other characters from an input
 * line of text
 */

public class Utility
{
	/**
	 * Removes unwanted punctuation from line of text.
	 * @param line
	 * 		Single line of text, as a String
	 * @return
	 * 		Single line of text, as a String, with double
	 * 		quotes and certain other punctuation removed.
	 */
	public static String editString(String line)
	{
		
		line = removeQuotes(line); //remove double and some single quotes
		line = removePossessives(line); //remove apostrophes with or without 's'
		line = removeBasicPunctuation(line);
		line = removeHyphens(line); //remove extra hyphens
		line = editApostrophes(line); //remove extra apostrophes
		line = editRomanNumerals(line); //remove roman numerals
		return line;
	}
	
	/**
	 * Removes each double quote, whether alone or with a single 
	 * quote. Also removes a hyphen with a single quote.
	 * @param line
	 * 		Single line of text, as a String
	 * @return
	 * 		Single line of text, as a String
	 */
	private static String removeQuotes(String line)
	{
		
		line = line.replace("\"'", "");
		line = line.replace("'\"", "");
		line = line.replace("-'", " ");
		line = line.replace("\"", " ");
		return line;
	}
	
	/**
	 * Removes possessives from line of text. For the singular
	 * possessive, removes 's. For the plural possessive, just 
	 * removes the apostrophe.
	 * @param line
	 * 		Single line of text, as a String
	 * @return
	 * 		Single line of text, as a String
	 */
	private static String removePossessives(String line)
	{
		
		line = line.replace("'s", "");
		line = line.replace("s' ", "s ");
		return line;
	}
	
	/**
	 * Removes punctuation from end of line; does this two times.
	 * Replaces certain punctuation types with a space. Note that
	 * apostrophes and hyphens are not removed (except from end of
	 * line) since they are potentially necessary to certain words
	 * in the input text.
	 * @param line
	 * 		Single line of text, as a String
	 * @return
	 * 		Single line of text, as a String
	 */
	private static String removeBasicPunctuation(String line)
	{
		
		//removes punctuation from end of line
		if(line.endsWith("\\p{Punct}+"))
		 	line = line.substring(0, line.length() - 1);
		if(line.endsWith("\\p{Punct}+"))
			line = line.substring(0, line.length() - 1);
		//removes certain punctuation marks
		line = line.replace(".-", " ");
		line = line.replace(",-", " ");
		line = line.replace(";-", " ");
		line = line.replace("?-", " ");
		line = line.replace(",", "");
		line = line.replace(".", "");
		line = line.replace("(", "");
		line = line.replace(")", "");
		line = line.replace(":", "");
		line = line.replace(";", "");
		line = line.replace("?", "");
		line = line.replace("!", "");
		line = line.replace("&", "");
		line = line.replace("$", "");
		return line;
	}
	
	/**
	 * Removes hyphens from line of text. Includes certain
	 * special cases for the sample Quran text.
	 * @param line
	 * 		Single line of text, as a String
	 * @return
	 * 		Single line of text, as a String
	 */
	private static String removeHyphens(String line)
	{
		
		//removes hyphen from end of line
		if(line.endsWith("-"))
			line = line.substring(0, line.length() - 1);
		//removes hyphens from text based on spacing
		line = line.replace(" -", " ");
		line = line.replace("- ", " ");
		line = line.replace(" - ", " ");
		//handles special cases from sample text
		line = line.replace("h-s", "h s");
		line = line.replace("d-b", "d b");
		line = line.replace("y-t", "y t");
		line = line.replace("s-s", "s s");
		line = line.replace("ay-bu", "ay bu");
		line = line.replace("iel-for", "iel for");
		line = line.replace("h-H", "h H");
		return line;
	}
	
	/**
	 * Removes apostrophes from line of text. Calls method
	 * editQuranNames to reinsert apostrophes into certain names
	 * from the sample Quran text.
	 * @param line
	 * 		Single line of text, as a String
	 * @return
	 * 		Single line of text, as a String
	 */
	private static String editApostrophes(String line)
	{
		
		//removes apostrophe from end of line
		if(line.endsWith("'"))
			line = line.substring(0, line.length() - 1);
		//removes double apostrophe from beginning of line
		if(line.startsWith("''"))
			line = line.substring(2, line.length());
		//removes single apostrophe from beginning of line
		if(line.startsWith("'"))
			line = line.substring(1, line.length());
		//removes single apostrophes based on spacing
		line = line.replace("' ", " ");
		line = line.replace(" '", " ");
		//removes double apostrophe
		line = line.replace("''", " ");
		//calls method to handle certain names from sample text
		line = editQuranNames(line);
		return line;
	}
	
	/**
	 * Inserts apostrophes into certain names from the Quran text
	 * sample.
	 * @param line
	 * 		Single line of text, as a String
	 * @return
	 * 		Single line of text, as a String
	 */
	private static String editQuranNames(String line)
	{
		
		line = line.replace(" Ad ", " 'Ad ");
		line = line.replace(" Ad,", " 'Ad,");
		line = line.replace("Ain", " 'Ain");
		line = line.replace(" Aziz", " 'Aziz");
		line = line.replace(" Iddat", " 'Iddat");
		line = line.replace(" iddat", " 'iddat");
		line = line.replace(" Ifr", " 'Ifr");
		line = line.replace(" Illi", " 'Illi");
		line = line.replace(" Imr", " 'Imr");
		line = line.replace(" umra", " 'umra");
		line = line.replace(" Uz", " 'Uz");
		line = line.replace("Nasr", "Nasr'");
		line = line.replace("Suwa", "Suwa'");
		line = line.replace("Dhari", "Dhari'");
		line = line.replace("Tubba", "Tubba'");
		return line;
	}
		
	/**
	 * Remove roman numerals from line of text.
	 * @param line
	 * 		Single line of text, as a String
	 * @return
	 * 		Single line of text, as a String
	 */
	private static String editRomanNumerals(String line)
	{
		
		line = removeRomansFromEnd(line);
		line = removeRomanLines(line);
		//remove additional roman numeral patterns
		line = line.replace("iii", " ");
		line = line.replace("xxx", " ");
		line = line.replace("vii", " ");
		line = line.replace("xii", " ");
		line = line.replace("xiv", " ");
		line = line.replace("xx", " ");
		line = line.replace("xv", " ");
		line = line.replace("cx", " ");
		line = line.replace("cv", " ");
		line = line.replace("lx", " ");
		line = line.replace(" ii ", " ");
		line = line.replace(" iv ", " ");
		line = line.replace(" vi ", " ");
		line = line.replace(" ix ", " ");
		line = line.replace(" xi", " ");
		return line;
	}
	
	/**
	 * Shortens lines that have the specified roman numeral
	 * patterns at their ends.
	 * @param line
	 * 		Single line of text, as a String
	 * @return
	 * 		Single line of text, as a String
	 */
	private static String removeRomansFromEnd(String line)
	{
		
		if(line.endsWith("viii"))
			line = line.substring(0, line.length() - 4);
		if(line.endsWith("xiii"))
			line = line.substring(0, line.length() - 4);
		if(line.endsWith("liii"))
			line = line.substring(0, line.length() - 4);
		if(line.endsWith("ciii"))
			line = line.substring(0, line.length() - 4);
		if(line.endsWith("xlix"))
			line = line.substring(0, line.length() - 4);
		
		if(line.endsWith("iii"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("vii"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("xii"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("lii"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("cii"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("xxx"))
			line = line.substring(0, line.length() - 3);
		
		if(line.endsWith("xiv"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("civ"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("liv"))
			line = line.substring(0, line.length() - 3);
		
		if(line.endsWith("xvi"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("cvi"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("lvi"))
			line = line.substring(0, line.length() - 3);
		
		if(line.endsWith("xix"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("cix"))
			line = line.substring(0, line.length() - 3);
		
		if(line.endsWith("xxi"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("cxi"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("lxi"))
			line = line.substring(0, line.length() - 3);
		
		if(line.endsWith("xxv"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("cxv"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("lxv"))
			line = line.substring(0, line.length() - 3);
		
		if(line.endsWith("xli"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("cli"))
			line = line.substring(0, line.length() - 3);
		
		if(line.endsWith("xlv"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("xci"))
			line = line.substring(0, line.length() - 3);
		if(line.endsWith("cxl"))
			line = line.substring(0, line.length() - 3);
		
		if(line.endsWith(" ii"))
			line = line.substring(0, line.length() - 2);
		if(line.endsWith(" iv"))
			line = line.substring(0, line.length() - 2);
		if(line.endsWith(" vi"))
			line = line.substring(0, line.length() - 2);	
		return line;
	}
	
	/**
	 * Removes roman numerals left as sole contents of line.
	 * @param line
	 * 		Single line of text, as a String
	 * @return
	 * 		Single line of text, as a String
	 */
	private static String removeRomanLines(String line)
	{
		
		if(line.contentEquals("lix"))
			line = line.replace("lix", "");
		if(line.contentEquals("li"))
			line = line.replace("li", "");
		if(line.contentEquals("lv"))
			line = line.replace("lv", "");
		if(line.contentEquals("xc"))
			line = line.replace("xc", "");
		if(line.contentEquals("xl"))
			line = line.replace("xl", "");
		if(line.contentEquals("cl"))
			line = line.replace("cl", "");
		if(line.contentEquals("ci"))
			line = line.replace("ci", "");
		if(line.contentEquals("ii"))
			line = line.replace("ii", "");
		if(line.contentEquals("iv"))
			line = line.replace("iv", "");
		if(line.contentEquals("vi"))
			line = line.replace("vi", "");
		if(line.contentEquals("ix"))
			line = line.replace("ix", "");
		if(line.contentEquals("xi"))
			line = line.replace("xi", "");
		return line;
	}
	
}
