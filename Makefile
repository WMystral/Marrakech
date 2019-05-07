JC = javac 
EXE = marrakech

java = $(CLASSES:.class=.java)

CLASSES = \
		Pair.class\
		Cell.class\
		AfficheDe.class\
		Grid.class\
		Pannel.class\
		Game.class\
		Button.class\
		Menu.class\
		Frame.class\
		Main.class
 
but : ${EXE}
 
Cell.class:

Pair.class: Cell.class

AfficheDe.class: 

Grid.class: Cell.class Pair.class

Pannel.class: AfficheDe.class

Game.class: Pannel.class Grid.class

Button.class:

Menu.class: Button.class

Grid.class: Cell.class

Frame.class: Menu.class Game.class

Main.class: Frame.class

${EXE} : $(java)
	$(JC) -g $(java)
 
clean :
	-rm -f *.class
	#-rm -f ./save.dat
	clear

test : 
	java Main 
 
.PHONY : but clean test
 