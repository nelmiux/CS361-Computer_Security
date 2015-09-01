UTEID: np8259; dir253;
FIRSTNAME: Nelma; Daniela;
LASTNAME: PereraRodriguez; Reyes;
CSACCOUNT: npererar; daniela7;
EMAIL: npererar@utexas.edu; danielareyes174@utexas.edu;

[Program 5]
[Description]
There are 3 java files: We downloaded jcrypt.java from the webpage provided on the assigment intructions, We implemented the class User in User.java, but the most important file, where main reside, is PasswordCrack.java. In PasswordCrack.java, We implemented some functions, Nelma wrote functions main, toggleCase, toggleCase2, toggleCase3, oneM, twoM, threeM, fiveM, match, manglesAll. Daniela wrote functions main, prepend, append, prepend2, append2, upper, lower, duplicate, reverse, deleteFirst, deleteLast, reflect, capitalize, ncapitalize, toggleCase, toggleCase2, toggleCase3, replace2, replace3, replace4, replace5, replace6, replace7. The most important method is main, which calls the User class and other function do acomplish five main steps : 1.-Extract the encrypted password and salt for that user, 2.- Seed the word list with words that the user might have utilized in constructing his or her password, 3.- With the salt and augmented wordlist, systematically encrypt words and compare against the stored encrypted password, 4.- Redo step 3, but using mangled versions of the words, 5.- Redo step 4, attempting to apply two mangles to each word. To compile our program, you need to use "javac *.java". To run our program, you need to use "java PasswordCrack words.txt passwd1.txt" or "java PasswordCrack words.txt passwd2.txt".

[Finish]
Finished

[Test Cases]
[Input of test 1]
https://www.cs.utexas.edu/~byoung/cs361/passwd1

[Output of test 1]
01.- Username: michael        	Password: michael
02.- Username: abigail        	Password: liagiba
03.- Username: maria          	Password: Salizar
04.- Username: samantha       	Password: amazing
05.- Username: tyler          	Password: eeffoc
06.- Username: benjamin       	Password: abort6
07.- Username: morgan         	Password: rdoctor
08.- Username: jennifer       	Password: doorrood
09.- Username: connor         	Password: enoggone
10.- Username: evan           	Password: Impact
11.- Username: nicole         	Password: keyskeys
12.- Username: rachel         	Password: obliqu3
13.- Username: jack           	Password: sATCHEL
14.- Username: alexander      	Password: squadro
15.- Username: victor         	Password: THIRTY
16.- Username: james          	Password: icious
17.- Username: caleb          	Password: teserP
18.- Username: dustin         	Password: litpeR

Uncracked:nathan
Uncracked:paige

I can crack 18 cases in 300.006 seconds.

I cannot crack 2 cases.

[Input of test 2]
https://www.cs.utexas.edu/~byoung/cs361/passwd2

[Output of test 2]
01.- Username: samantha       	Password: cOnNeLlY
02.- Username: jennifer       	Password: ElmerJ
03.- Username: alexander      	Password: Lacque
04.- Username: nathan         	Password: uPLIFTr
05.- Username: evan           	Password: ^bribed
06.- Username: morgan         	Password: dIAMETER
07.- Username: james          	Password: enchant$
08.- Username: tyler          	Password: eltneg
09.- Username: nicole         	Password: INDIGNITY
10.- Username: abigail        	Password: Saxon
11.- Username: dustin         	Password: Swine3
12.- Username: michael        	Password: tremors
13.- Username: jack           	Password: ellows
14.- Username: caleb          	Password: zoossooz
15.- Username: benjamin       	Password: soozzoos
16.- Username: connor         	Password: nosral

Uncracked:victor
Uncracked:rachel
Uncracked:maria
Uncracked:paige

I can crack 16 cases in 300.006 seconds.

I cannot crack 4 cases.

