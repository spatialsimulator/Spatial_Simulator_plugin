#!/bin/zsh

#mvn install:install-file -Dfile=lib/libspatialsimj.jar -DgroupId=spatialsim -DartifactId=libspatialsimj -Dversion=0.0.1 -Dpackaging=jar -DgeneratePom=true
#mvn install:install-file -Dfile=thirdparty/celldesigner.jar -DgroupId=jp.sbi -DartifactId=celldesigner -Dversion=4.4.0 -Dpackaging=jar -DgeneratePom=true
/bin/cp thirdparty/spatialsimulator/libspatialsimj.jar lib/
mvn clean install
/bin/cp target/SpatialSimulatorPlugin-1.0.0-SNAPSHOT-jar-with-dependencies.jar lib/
cd lib
jar xf libspatialsimj.jar
jar uf SpatialSimulatorPlugin-1.0.0-SNAPSHOT-jar-with-dependencies.jar darwin/
rm -rf META-INF darwin
cd ..
mv lib/SpatialSimulatorPlugin-1.0.0-SNAPSHOT-jar-with-dependencies.jar target/
echo "done creating jar as ${PWD}/target/SpatialSimulatorPlugin-1.0.0-SNAPSHOT-jar-with-dependencies.jar."
