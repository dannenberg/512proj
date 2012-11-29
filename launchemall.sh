#!/usr/bin/bash
xterm -hold -e "ssh mimi -t 'rmiregistry 9988& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/ResourceManagerImpl ResImpl/MiddleWare 9988 8085'"
xterm -hold -e "ssh willy -t 'rmiregistry 9988& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/ResourceManagerImpl ResImpl/MiddleWare 9988 8085 mimi 8085'"

xterm -hold -e "ssh skinner -t 'rmiregistry 9898& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/ResourceManagerImpl Car 9898 mimi 8085'"
xterm -hold -e "ssh mimi -t 'rmiregistry 9898& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/ResourceManagerImpl Car 9898 willy 8085'"

xterm -hold -e "ssh willy -t 'rmiregistry 9899& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/ResourceManagerImpl Plane 9899 mimi 8085'"
xterm -hold -e "ssh skinner -t 'rmiregistry 9899& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/ResourceManagerImpl Plane 9899 willy 8085'"

xterm -hold -e "ssh mimi -t 'rmiregistry 9897& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/ResourceManagerImpl Hotel 9897 mimi 8085'"
xterm -hold -e "ssh willy -t 'rmiregistry 9897& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/ResourceManagerImpl Hotel 9897 willy 8085'"

java Client
