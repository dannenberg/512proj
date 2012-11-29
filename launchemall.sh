#!/usr/local/bin/bash
xterm -hold -e "ssh willy -t 'rmiregistry 9988& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/Middleware 9988 8089'"&
xterm -hold -e "ssh skinner -t 'rmiregistry 9988& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/Middleware 9988 8089'"&
sleep 5

xterm -hold -e "ssh mimi -t 'rmiregistry 9878& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/ResourceManagerImpl Car 9878 skinner 8089 willy 8089'"&
xterm -hold -e "ssh skinner -t 'rmiregistry 9888& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/ResourceManagerImpl Car 9888 skinner 8089 willy 8089'"&

xterm -hold -e "ssh willy -t 'rmiregistry 9879& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/ResourceManagerImpl Plane 9879 skinner 8089 willy 8089'"&
xterm -hold -e "ssh mimi -t 'rmiregistry 9889& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/ResourceManagerImpl Plane 9889 skinner 8089 willy 8089'"&

xterm -hold -e "ssh skinner -t 'rmiregistry 9897& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/ResourceManagerImpl Hotel 9897 skinner 8089 willy 8089'"&
xterm -hold -e "ssh willy -t 'rmiregistry 9887& java -Djava.rmi.server.codebase=file:$CLASSPATH -Djava.policy=$CLASSPATH/java.policy ResImpl/ResourceManagerImpl Hotel 9887 skinner 8089 willy 8089'"&
sleep 5

java Client skinner 9988 willy 9988
