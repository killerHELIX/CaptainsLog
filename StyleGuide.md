CPSC 240 CaptainsLog Style & Repository Guide
=====================================

Table of Contents
-----------------
0000 : Repository Guidelines  
  * 0100 : Commits  
  * 0300 : Pushing  
  * 0500 : Pulling  
  * 0700 : Merging  
  
1000 : Programming Style  
  * 1050 : Tabulation, Spacing, & Line Wrapping
  * 1100 : API Resources  
  * 1300 : Classes  
  * 1500 : Functions & Methods  
  * 1700 : Variables  
  * 1800 : Flow Control  
  * ---- 1820 : for, while, and if
  * ---- 1840 : do-while and switch
  * ---- 1860 : switch
  * ---- 1880 : try-catch
  * 1900 : Comments and Docstrings  
  * ---- 1930 : Javadoc Comments
  * ---- 1960 : Internal Comments
  * ---- 1980 : Variable Comments
  * ---- 1990 : Final Note
   
0000 : Repository Guidelines
----------------------------
While I initially thought we should work with a system where we have a 
master repository and individual development forks, I get the feeling  
we'll be better served with just some rules for dictating our code  
contributions. Everyone will be made a contributor to this primary  
repository.  

However, because of this, we must adhere to these rules:  

### 0100 : Commits
Commit often and ALWAYS provide a useful commit message that denotes the  
changes you made. Swear words in commit messages will be frowned upon, and  
commit messages that consist entirely of swears will bring *ten myriad*  
*years of suffering upon your family.*  

### 0300 : Pushing
Before you push code to the master repository, ensure that:  
  * **Your code compiles.**
  * The module(s) you have made or edited work for at least a few basic test cases.
  * You have made a commit per the guidelines in 0100.

Pushing untested code or code that fails to compile will force *someone*  
*else* on the team to fix your broken code. Don't do that. If your code  
doesn't work yet, do a commit and come back to it later on your own  
machine. Shoot one of us a text or an email if you need a hand piecing a  
function or algorithm together.  

If your code has some bugs in it but works for general cases, **leave a**  
**comment** in the commit message *and* **in the source** **file where**  
**you made your changes.**  

### 0500 : Pulling
Before you begin working on the project source code, **always** be sure to  
pull down from the master repository.  

Before you pull code to your local repository, ensure that:  
  * You have made a commit to your local repository. *This will allow git to auto-merge if necessary.*  

### 0700 : Merging
Doing a manual merge with git can be bad news. I'll add information here  
as we go (we're bound to run into a merge issue at least once). Usually  
git will resolve merges on its own, but if you get an error on a  
`git push` or a `git pull` that relates to merging, alert a team member  
in the group text.  


1000 : Programming Style
------------------------

For the sake of easier debugging, easier reading, and smoother flow when  
modules of the code change hands between us, we're going to adhere to our  
own ad-hoc set of rules. Since we'll be working in Java, our practices are  
going to, in some respects, mirror the styles evident in Java's API and  
many example programs.

## 1050 : Tabulation, Spacing, and Line Wrapping
Use tabs when indenting code. If you're formatting comments or Javadoc  
strings, then you can use spaces to line things up.  

Operators should be separated from their operands by a space. Butting plus  
signs and the like up against literals and variables makes things hard    
to read. If you want part of an expression to be grouped together, use  
parentheses.  

We are using Eclipse to code this and not a terminal interface, though  
avoiding line wrapping makes code more readable. If you have a line of  
code that would run off the side of your editor window or wrap back around  
to the next line down, cut it off with a proper newline. If you have a  
conditional or method/function call that runs off the side of the editor  
or wraps, please note the following examples:  

GOOD:

    // indent second line over by one space
    SomeBigDataType sbdt = new SomeBigDataType(argumentOne, argumentTwo,  
        argumentThree, argumentFour);
    
    // for code blocks, start the bracing at the end of the conditional:
    if (thePresident.isDead() && theVicePresident.isDead() &&  
        && theSpeakerOfTheHouse.isDead()) {
        thePresidentProTemporeOfTheSenate.becomesPresident();
    }
    // This is bad code for other reasons, but it's just an example.  
    // Open brace where the condition ends, close brace lines up with  
    // keyword that started the conditional.

BAD:  

    // Every time you commit a line of code that looks like this, a  
    // programmer from forty years ago comes alive to hunt you down for  
    // bad style.
    SomeBigDataType sbdt = new SomeBigDataType(argumentOne, argumentTwo, argumentThree, argumentFour);
    
    // NO NO NO NO
    // if (condition /* more nonsense about the presidential line of succession I mean seriously this is a terrible example because we'd rather have an array of important figures rather than having a separate named variable for each of them. */ ) {
        someCode();
    }


## 1100 : API Resources
When declaring part of the API as being in a source file's namespace, or  
in other words, using an `import` statement, each module should be  
`import`ed separately. Don't use wildcard imports. Examples as follows:  

GOOD:  

    import java.util.Scanner;
    import java.io.IOException;

BAD:  

    import java.util.*;

Group resources together by category. `util` modules should be next to  
other `util` modules, `io` with `io`, etc. Exceptions should be separated  
from other classes. Example:  

    /* API Resources */
    import java.util.Scanner;
	import java.util.RandomAccess;
	import java.lang.Integer;
	import java.lang.Double;
	import java.io.PrintWriter;
	import java.io.File;
    
    /* API Exceptions */
    import java.io.IOException;
    import java.lang.IndexOutOfBoundsException;
    import java.lang.ArithmeticException;

And so on. If using external resources (i.e. libraries found elsewhere),  
`import` modules and exceptions in their own comment-marked block.  

## 1300 : Classes
Before you write a class, find out if someone else has already made one we  
could be using instead.

Standard encapsulation rules apply. If we have need to do any object-  
oriented processing, then use appropriate accessor methods for reading and  
writing object fields. Make private helper methods where appropriate.  

It is more likely, however, that we'd be creating classes with `static`  
helper methods than true object-oriented code, due to the fact that we're  
using a database.  

Class names should be `UpperCamelCase` not `mixedCamelCase` -- the first  
letter being capitalized helps to signal that it's a class.

Bracing for classes will be akin to what's called "the one true brace  
style" in the C community. Open brace occurs on the same line as the class  
header, padded by a space. Closing brace occurs on its own line. Example:  


GOOD:  

    public class StyleExample {  
        // code goes here  
    }  

BAD:
  
    public class StyleExample{
        // code goes here  
    }  

    public class StyleExample2  
    {  
        // code goes here  
    }  

VERY BAD:  

    public class StyleExample{  
        // code goes here }  

In other words, the closing brace of the class declaration should line up  
with the first character of its declaration.  

### 1500 : Functions & Methods
This is the meat of the code right here, and since it requires the most  
brainpower to grok, we want to make it as descriptive as possible so we  
expend the energy of fewer brain cells to merely parse it.  

Functions and methods should have descriptive names, preferably verbs or  
verb phrases, but use whatever seems appropriate. If you think the meaning  
of its name might not be intuitive, mark it with a comment or try to come  
up with a better name. Function and method names should be written in  
`camelCase` if it is longer than one word. Additionally, functions/methods  
should be braced like classes. Examples:  

GOOD:  

    public String toString() {
        // follows Java API style
    }
	
    public static int[] piecewiseSumOf(int[] a, int[] b) {
        // Compound words should not be camelCased because they are their
        // own lexical units
    }

BAD:  

    public String to_string(){
        // don't use snake_case
        // pad your braces with spaces, yo
    }
    
    public static char FooBarFunc()
    {
        // don't use crummy names
        // don't use UpperCamelCase
        // don't use this brace style
    }

## 1700 : Variables
Variables should use `mixedCamelCase`, as with methods and functions.  
Single words (including compound words), of course, can be lowercase. If  
you include the word "my" or the type of the variable in the variable's  
name, you will be visited by the moderator of reddit.com/r/badcode in your  
sleep. You have been warned. Examples:  

GOOD:  

    float cashOnHand = wallet.cash + pockets.cash;
    String filename = defaultFilename;

BAD:  

    float my_cash = wallet.cash + pockets.cash; // NO NO NO
    String fileName = defaultFileName; // this is a compound word!

## 1800 : Flow Control

### 1820 : for, while, and if

`for`, `while`, and unaccompanied `if` blocks should use the same bracing  
style as functions, methods, and classes. However, the conditional keyword  
should be separated from the condition by a space. `if-else` blocks should  
use "cuddled" `else` statements. Examples:  

GOOD:  

    for (int i = 0; i < range; i++) {
        // code goes here
    }
    
    while (condition) {
		// code goes here
    }
    
    if (conditionOne) {
        // first choice
    } else if (conditionTwo) {
        // second choice
    } else {
        // default choice
    }

BAD:

    for(int i=0; i<range; i++){
        // pad your operators and remember your spaces
    }
    
    while(condition)
    {
        // don't do this!
    }
    
    if (condition) doStuff(); // Always brace your conditionals!
    
    if (condition) {
        // first check
    }
    else if (conditionTwo) {
        // second check
    }
    else {
       // none of this uncuddled conditional nonsense!
    }

### 1840 : do-while
The `do` of a `do-while` loop should be on its own line, and should have  
a space between it an the opening brace. At the end of a `do-while` loop,  
the condition should follow the closing brace (with an intervening space)  
and should have a space between the `while` keyword and the condition.  
Examples:  

GOOD: 
 
    do {
        // code goes here
    } while (condition);

BAD:  

    do
    {
        // you probably get it by now
    }while(condition);

### 1860 : switch
Switch statements should share bracing style with all other control flow  
structures and should indent for each case, and the code for each case.  
This causes some deep indentation for a `switch` block, but I think it  
prevents too much code from being piled up inside. `switch` statements  
have limited applications and shouldn't be used to house entire blocks of  
program logic -- each case should just be calling a few functions and then  
`break`ing if appropriate. Examples:  

GOOD:  

    switch (option) {
        case 1:
            optionOneFunctionCall();
            break;
    
        case 2:
        case 3:
            optionTwoOrThreeFunctionCall();
            break;
    
        default:
            defaultOption();
            break;
    }

BAD:  

    switch(option)
    {
        case 1:
        optionOneFunctionCall();
        break; // poor indentation
    case 2:
    optionTwoFunctionCall();
    break; // VERY poor indentation
    } // bad bracing

### 1880 : try-catch
Exception-handling blocks should follow the bracing and spacing guidelines  
specified for other types of code blocks. Each type of exception that can  
be caught by a `try` block should have its own `catch` statement.  
Examples:  

GOOD:  

    try {
        someDangerousFunctionCall();
	} catch (TypeOfException e) {
        // handler
    } catch (DifferentTypeOfException e) {
        // another handler
    } finally {
        // finally block if necessary
    }

BAD:

    try
    {
        someAwfulBracingGoingOn();
    }catch(LackOfSpacingException | BadBracingException e){
        // please don't do this
    }
    finally {
        // uncuddled blocking is a SIN
    }

## 1900 : Comments & Docstrings

### 1930 : Javadoc
If you are creating a class, comments should be in Javadoc style. A header  
comment with some information about the overall responsibilities of the  
class and appropriate `@author` tags. Add your name in such a tag if you  
edit the file in some way. Member variables (instance or static) should  
each have one or two lines explaining the purpose of the variable. Methods  
(static or instance) should have Javadoc comments with `@param` tags for  
each parameter, `@throws` tags for each potential exception thrown, and an  
`@return` tag if it `return`s non-void. Example:  

    /** Example class for CaptainsLog Style Guide
     * @author James Murphy
     */
    public class ScrumTeam {
        /** Number of developers */
        private int teamMembers;
        
        /** Name of project */
        private String projectName;
        
        /** Constructs a ScrumTeam object
         * @param developers An int describing the size of the team.
         * @param name A String representing the name of the project.
         * @throws IllegalArgumentException if team has 0 or negative devs.
         */
        public ScrumTeam(int developers, String name) {
            if (developers < 1) {
                throw new IllegalArgumentException("No empty teams!");
            }
            this.teamMembers = developers;
            this.projectName = name;
        }
        
        /** Obtains the name of the ScrumTeam's project.
         * @return a String -- the project name.
         */
        public String getProjectName() {
            return this.projectName;
        }
    }

### 1960 : Internal Comments
Within methods, you can use non-Javadoc comments. `//` or `/* */` styles  
are acceptable. The same applies for non-member functions in the main  
program, including `main()` itself.  

Additionally, non-member functions should be headed with a comment to  
explain its general purpose. Example:  

    /* Shortcut for calling System.out.print() */
    static void print(String message) {
        System.out.print(message);
    }

### 1980 : Variable Comments
You need not comment variables in the same way unless you believe their  
use to be non-obvious or unintuitive. If some code using that variable was  
a challenge to write / required some deep thinking, it probably needs a  
comment. Otherwise, you can group variables together under a single  
comment that explains their collective purpose. Example:  

    /* Initialize variables for do-while + switch menu. */
    Scanner input = new Scanner(System.in);
    char selection = 0;
    boolean validCommand = false;

### 1990 : Final Note
When in doubt, leave a comment. That's the main rule.
