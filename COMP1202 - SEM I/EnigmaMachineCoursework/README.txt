---SIMONE ANDRONACHE - COMP1202 COURSEWORK README---

---PARTS COMPLETED---
1 - Modelling the Plugboard
	Relevant classes: Plug.java, Plugboard.java
2 - Modelling the Rotors
	Relevant classes: Rotor.java
3 - Modelling the Reflector
	Relevant classes: Reflector.java
4 - Modelling Basic Rotors
	Relevant classes: BasicRotor.java
5 - Modelling the Enigma Machine
	Relevant classes: EnigmaMachine.java
6 - Reading and Writing files
	Relevant classes: EnigmaFile.java
7 - Modelling Turnover Rotors
	Relevant classes: TurnoverRotor.java
8 - Building a Bombe
	Relevant classes: Bombe.java, BombeRotor.java, BombeSettings.java, BombeSettingsType.java


---BOMBE CHALLENGE ANSWERS---
Challenge 1:
	Outputs: 
	"TSISYTASSYGIVEMFYOURANSWERTO",
	"TAISYTAISYGIVEMEYOUMANSWERTO",
	"TAISYTAISYDIVEMEYOURANSWERTO",
	"TAISYTAISYGIVEMEYOURANSWERTO",
	"TADSYTADSYGDVEMEYOURANSWERTS",
	"TAISYTAISYGIVEMEYOURANSWERTO",
	"TAISYTAISYGIVEMEYOTRANSWERTO",
	"TAISYTAISYGIVEDEYOURANSWERTO",
	"TAISYTAISYGIVESEYDURANSWERTD",
	"DAISYDAISYGIVEMEYOURANSWERDO", <- You will have to use a filter to get only this output.
	"TAISYTAISYGIVNMEYODRANSWERTO",
	"TAISYIAISYGBDEMEYOURANSWERTO",
	"TAISYTAISYGIVEMESOURANSWERTO",
	"TATSYTAISYGIVEMEYOURANSWERTO",
	"TATSYTAISYGIVEMEYOURANSWERTO"
Challenge 2:
	Output: "WELLALWAYSBETOGETHERHOWEVERFARITSEEMSWELLALWAYSBETOGETHERTOGETHERINELECTRICDREAMS"
Challenge 3:
	Output: "ILOVECOFFEEILOVETEAILOVETHEJAVAJIVEANDITLOVESME"


--RUNNING CODE---
	Relevant classes: Main.java

To run the base code, run "java Main" after compiling. This will complete all challenges and tests specified within the spec and provide a menu to setup your machine and encode strings.

No command line arguments are required for either version.


---EXTENSIONS---
	Relevant classes: Main.java

For my extension I created a command line menu system that allows users to edit the settings
of the rotors, plugboard and reflector. Users can also encode strings and get the output within the terminal.


---JAVA DOC---
I have also generated a Java Doc which can be found under 'doc/index.html'. This can be used to see classes and methods.
