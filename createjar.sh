#!/bin/zsh

#mvn install:install-file -Dfile=lib/libspatialsimj.jar -DgroupId=spatialsim -DartifactId=libspatialsimj -Dversion=0.0.1 -Dpackaging=jar -DgeneratePom=true
#mvn install:install-file -Dfile=thirdparty/celldesigner.jar -DgroupId=jp.sbi -DartifactId=celldesigner -Dversion=4.4.0 -Dpackaging=jar -DgeneratePom=true
mvn install -X
/bin/cp target/SpatialSimulatorPlugin-0.0.1-SNAPSHOT.jar lib/
cd lib
jar uf SpatialSimulatorPlugin-0.0.1-SNAPSHOT.jar com/ darwin/
echo "done creating jar"
