#
# Java Makefile
#

JC = javac
JCFLAGS = -g

SRCPATH = ./src/
JDOC = javadoc
JDOCFLAGS = -author
MAIN = -cp ./bin/ Jouer
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JCFLAGS) $*.java -d bin


JAVASRC = $(shell ls src/*.java)
CLASSES = $(JAVASRC:.java=.class)

goal: build

all: classes

default: classes

classes: $(CLASSES:.java=.class)

build:
	$(JC) $(JCFLAGS) $(JAVASRC) -cp $(SRCPATH) -d bin

run:
	java $(MAIN)
doc:
	mkdir -p doc
	$(JDOC) $(JDOCFLAGS) -d doc *.java

clean:
	rm -f bin/*.class
	rm -f *.class
	rm -fr doc
 
