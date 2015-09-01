UTEID: np8259; dir253;
FIRSTNAME: Nelma; Daniela;
LASTNAME: PereraRodriguez; Reyes;
CSACCOUNT: npererar; daniela17;
EMAIL: npererar@utexas.edu; danielareyes174@utexas.edu;

[Program 3]
[Description]
There are 2 java files: In Encoder.java, We implemented some functions. Nelma wrote enc, twoEnc, main; Daniela wrote dec, twoDec, characters. The most important method is main, which has four main steps calling other functions: Step 1: calculate entropy; Step 2 : Use Huffman algorithm to device a binary encoding, and print to screen; Step 3: create and store data in the testText.txt file, Step 4: calculate the avg bits per symbol, and the persentage difference, Step 5: make 2-symbols enc and 2-symbols calculations, and print to the screen the results. To compile our program, you need to use "javac *.java". To run our program, you need to use "java Encoder frequenciesFile.txt k"
We get the Huffman code implementation from http://rosettacode.org/wiki/Huffman_coding, and then we modified it to storage the prefixes used for coding


[Finish]
Finished


[Test Cases]
[Input of test 1]
[command line]
java Encoder frequenciesFile.txt 20

please copy your input file(frequenciesFile) here
1
9
6
8
5
6

[Output of test 1]
please copy your output here.
h = 2.41

1-character encoding
SYMBOL	WEIGHT	HUFFMAN CODE
C	6	00
D	8	01
B	9	10
F	6	110
A	1	1110
E	5	1111

Average bits per symbol = 2.4
Percentage difference =  -0.43%

2-character encoding
SYMBOL	WEIGHT	HUFFMAN CODE
AA	1	0000
AB	9	0001
AC	6	00100
AD	8	00101
AE	5	00110
AF	6	00111
BA	9	0100
BB	81	0101
BC	54	0110
BD	72	01110
BE	45	01111
BF	54	10000
CA	6	10001
CB	54	10010
CC	36	10011
CD	48	1010
CE	30	1011000
CF	36	1011001
DA	8	10110100
DB	72	101101010
DC	48	101101011
DD	64	10110110
DE	40	10110111
DF	48	10111
EA	5	11000
EB	45	11001
EC	30	11010
ED	40	11011
EE	25	11100
EF	30	111010
FA	6	11101100
FB	54	11101101
FC	36	11101110
FD	48	11101111
FE	30	11110
FF	36	11111

Average bits per symbol = 3.15
Percentage difference = 30.68%
Percentage difference with 1-character encoding = 31.25%

--------------------------------------------------------
[Input of test 2]
[command line]
java Encoder frequenciesFile.txt 71

input file(frequenciesFile) here
1
9
6
8
5
6

[Output of test 2]

h = 2.41

1-character encoding
SYMBOL	WEIGHT	HUFFMAN CODE
C	6	00
D	8	01
B	9	10
F	6	110
A	1	1110
E	5	1111

Average bits per symbol = 2.51
Percentage difference =  4.01%

2-character encoding
SYMBOL	WEIGHT	HUFFMAN CODE
AA	1	0000
AB	9	0001
AC	6	00100
AD	8	00101
AE	5	00110
AF	6	00111
BA	9	0100
BB	81	0101
BC	54	0110
BD	72	01110
BE	45	01111
BF	54	10000
CA	6	10001
CB	54	10010
CC	36	10011
CD	48	1010
CE	30	1011000
CF	36	1011001
DA	8	10110100
DB	72	101101010
DC	48	101101011
DD	64	10110110
DE	40	10110111
DF	48	10111
EA	5	11000
EB	45	11001
EC	30	11010
ED	40	11011
EE	25	11100
EF	30	111010
FA	6	11101100
FB	54	11101101
FC	36	11101110
FD	48	11101111
FE	30	11110
FF	36	11111

Average bits per symbol = 3
Percentage difference = 24.46%
Percentage difference with 1-character encoding = 19.66%

--------------------------------------------------------
[Input of test 3]
[command line]
java Encoder frequenciesFile.txt 500

input file(frequenciesFile) here
1
9
6
8
5
6

[Output of test 3]

h = 2.41

1-character encoding
SYMBOL	WEIGHT	HUFFMAN CODE
C	6	00
D	8	01
B	9	10
F	6	110
A	1	1110
E	5	1111

Average bits per symbol = 2.47
Percentage difference =  2.47%

2-character encoding
SYMBOL	WEIGHT	HUFFMAN CODE
AA	1	0000
AB	9	0001
AC	6	00100
AD	8	00101
AE	5	00110
AF	6	00111
BA	9	0100
BB	81	0101
BC	54	0110
BD	72	01110
BE	45	01111
BF	54	10000
CA	6	10001
CB	54	10010
CC	36	10011
CD	48	1010
CE	30	1011000
CF	36	1011001
DA	8	10110100
DB	72	101101010
DC	48	101101011
DD	64	10110110
DE	40	10110111
DF	48	10111
EA	5	11000
EB	45	11001
EC	30	11010
ED	40	11011
EE	25	11100
EF	30	111010
FA	6	11101100
FB	54	11101101
FC	36	11101110
FD	48	11101111
FE	30	11110
FF	36	11111

Average bits per symbol = 2.99
Percentage difference = 23.88%
Percentage difference with 1-character encoding = 20.89%

--------------------------------------------------------
[Input of test 4]
[command line]
java Encoder frequenciesFile.txt 1000

input file(frequenciesFile) here
1
9
6
8
5
6

[Output of test 4]

h = 2.41

1-character encoding
SYMBOL	WEIGHT	HUFFMAN CODE
C	6	00
D	8	01
B	9	10
F	6	110
A	1	1110
E	5	1111

Average bits per symbol = 2.49
Percentage difference =  3.34%

2-character encoding
SYMBOL	WEIGHT	HUFFMAN CODE
AA	1	0000
AB	9	0001
AC	6	00100
AD	8	00101
AE	5	00110
AF	6	00111
BA	9	0100
BB	81	0101
BC	54	0110
BD	72	01110
BE	45	01111
BF	54	10000
CA	6	10001
CB	54	10010
CC	36	10011
CD	48	1010
CE	30	1011000
CF	36	1011001
DA	8	10110100
DB	72	101101010
DC	48	101101011
DD	64	10110110
DE	40	10110111
DF	48	10111
EA	5	11000
EB	45	11001
EC	30	11010
ED	40	11011
EE	25	11100
EF	30	111010
FA	6	11101100
FB	54	11101101
FC	36	11101110
FD	48	11101111
FE	30	11110
FF	36	11111

Average bits per symbol = 2.95
Percentage difference = 22.42%
Percentage difference with 1-character encoding = 18.47%

