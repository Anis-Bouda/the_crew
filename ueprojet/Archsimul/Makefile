SRC_DIR=src/java

# Options du compilateur
JAVAC=javac

# Compilation de tous les fichiers .java en une seule commande
compile:
	$(eval SOURCES=$(shell find $(SRC_DIR) -name "*.java"))  
	$(JAVAC) $(SOURCES)  

# Nettoyage des fichiers .class dans src/java
clean:
	find $(SRC_DIR) -type f -name "*.class" -exec rm -f {} \;

run:
	java -cp $(SRC_DIR) GRAPHIQUE.MainView  

Archsimule: run
