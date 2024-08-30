# Darius-Florentin Neatu <neatudarius@gmail.com>

# Exemplu de Makefile pentru tema

# tag-uri obligatorii (nume + comportament identic)
# build    => compileaza toata tema
#             (ATENTIE! E important - NU compilati in tag-urile de run. Sesizati?)
# run-p$ID => ruleaza problema cu ID-ul specificat (1, 2, 3, 4)
# clean    => sterge toate fisierele generate

# restul este la alegerea studentului
# TODO

# nume necesar (build)
build:
			g++ -Wall -std=c++11 walsh.cpp -o walsh
			javac Statistics.java
			g++ -Wall -g -std=c++11 prinel.cpp -o prinel
			javac Crypto.java
			javac Regele.java
run-p1:     
			./walsh
run-p2:     
			java Statistics
run-p3:      
			./prinel
run-p4:      
			java Crypto
run-p5:
			java Regele
clean:		 
			rm -f walsh prinel *.class