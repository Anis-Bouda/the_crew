# Répertoires
SRC_DIR=src/java

# Options du compilateur
JAVAC=javac

# Règle par défaut
all:compile

# Compilation de tous les fichiers .java en une seule commande
compile:
	$(eval SOURCES=$(shell find $(SRC_DIR) -name "*.java"))  # Récupère les fichiers .java
	$(JAVAC) $(SOURCES)  # Compile tous les fichiers

# Nettoyage des fichiers .class dans src/java
clean:
	find $(SRC_DIR) -type f -name "*.class" -exec rm -f {} \;

# Exécution
run:
	java -cp $(SRC_DIR) GRAPHIQUE.MainView  # Change Main si besoin

