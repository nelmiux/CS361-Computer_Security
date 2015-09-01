UTEID: np8259;
FIRSTNAME: Nelma;
LASTNAME: PereraRodriguez;
CSACCOUNT: npererar;
EMAIL: npererar@utexas.edu;

[Program 1]
[Description]
There are 5 java files. To run the program you have to use "java SecureSystem instructionList"

[Finish]
I finished completely this assignment.

[Test Cases]
[Input of test 1]
write Hal HObj 
read Hal 
write Lyle LObj 10
read Hal LObj 
write Lyle HObj 20
write Hal LObj 200
read Hal HObj
read Lyle LObj
read Lyle HObj
foo Lyle LObj
Hi Lyle,This is Hal
The missile launch code is 1234567
read aaa HObj
write Hal LObj aaa
write aaa LObj 200

[Output of test 1]
Reading from file: instructionList

Bad Instruction
The current state is:
   LObj has value: 0
   HObj has value: 0
   Lyle has recently read: 0
   Hal has recently read: 0

Bad Instruction
The current state is:
   LObj has value: 0
   HObj has value: 0
   Lyle has recently read: 0
   Hal has recently read: 0

lyle writes value 10 to lobj
The current state is:
   LObj has value: 10
   HObj has value: 0
   Lyle has recently read: 0
   Hal has recently read: 0

hal reads lobj
The current state is:
   LObj has value: 10
   HObj has value: 0
   Lyle has recently read: 0
   Hal has recently read: 10

lyle writes value 20 to hobj
The current state is:
   LObj has value: 10
   HObj has value: 20
   Lyle has recently read: 0
   Hal has recently read: 10

hal writes value 200 to lobj
The current state is:
   LObj has value: 10
   HObj has value: 20
   Lyle has recently read: 0
   Hal has recently read: 10

hal reads hobj
The current state is:
   LObj has value: 10
   HObj has value: 20
   Lyle has recently read: 0
   Hal has recently read: 20

lyle reads lobj
The current state is:
   LObj has value: 10
   HObj has value: 20
   Lyle has recently read: 10
   Hal has recently read: 20

lyle reads hobj
The current state is:
   LObj has value: 10
   HObj has value: 20
   Lyle has recently read: 0
   Hal has recently read: 20

Bad Instruction
The current state is:
   LObj has value: 10
   HObj has value: 20
   Lyle has recently read: 0
   Hal has recently read: 20

Bad Instruction
The current state is:
   LObj has value: 10
   HObj has value: 20
   Lyle has recently read: 0
   Hal has recently read: 20

Bad Instruction
The current state is:
   LObj has value: 10
   HObj has value: 20
   Lyle has recently read: 0
   Hal has recently read: 20

Bad Instruction
The current state is:
   LObj has value: 10
   HObj has value: 20
   Lyle has recently read: 0
   Hal has recently read: 20

Bad Instruction
The current state is:
   LObj has value: 10
   HObj has value: 20
   Lyle has recently read: 0
   Hal has recently read: 20

Bad Instruction
The current state is:
   LObj has value: 10
   HObj has value: 20
   Lyle has recently read: 0
   Hal has recently read: 20
