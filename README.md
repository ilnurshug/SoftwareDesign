# SoftwareDesign 
  
	Prototype command line interpreter
  
---
##  Installation
	1. clone repository
	2. open project in Intellij IDEA
	3. install ANTLR plugin
	4. open Shell.g4 file (grammar)
	5. configure ANTLR (select generate parse tree visitor)
	6. generate ANTLR recognizer
	7. mark gen folder (contains ANTLR-generted lexer and parser) as Generated Sources Root
##  Supported commands:
   * cat
   * echo
   * wc
   * pwd
   * exit
   * grep [OPTIONS] PATTERN [FILE]
   
   		OPTIONS:
   		* i - Ignore  case  distinctions  in  both  the  PATTERN and the input files.
   		* w - Select  only  those  lines  containing  matches  that form whole words.
   		* A NUM - Print  NUM  lines  of  trailing  context  after  matching lines.
   		PATTERN:
		* regular expression
  
---
  Supported strong and weak quoting, $, calling of external program, pipes
  
## Example
  
* echo "Hello, world!" 
* Hello, world!
* FILE=example.txt
* example.txt
* cat $FILE
* Some example text
* cat example.txt | wc
* 1 2 13 example.txt
* grep -i '.*' $FILE
* :hello, world!
* echo 'blah hello' | grep 'hello'
* :blah hello
  
  
  
