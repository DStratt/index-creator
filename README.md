# index-creator
Generates a textbook index in Java using Binary Search Trees, queues, and recursion.

Project Details:
- Case is not considered.
- File of stop words is provided (filename stopFile.txt)
- File of text to be indexed must first be filtered to remove stop words.
- Sample files to index may include punctuation that should be removed.
  - Examples: quotation marks; ending apostrophes for plural possessives
- Sample files to index may contain names whose apostrophes, accents, or other diacritical marks need to be preserved.

To Run from Terminal using Ant (requires Ant install):
- Select a sample input file, copy into a main directory, and rename to "textFile.txt".
- Copy build.xml file and stopFile.txt into main directory.
- Copy src folder into main directory.
- In main directory, enter "ant run" command.

To run from Terminal without Ant:
- Copy all java files into a main directory.
- Select a sample input file, copy into the main directory, and rename to "textFile.txt".
- Copy stopFile.txt into main directory.
- Compile and run using "javac Driver.java" and "java Driver.java" commands.
