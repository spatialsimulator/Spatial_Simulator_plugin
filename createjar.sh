#! /bin/zsh

mvn install:install-file -Dfile=lib/libspatialsimj.jar -DgroupId=spatialsim -DartifactId=libspatialsimj -Dversion=0.0.1 -Dpackaging=jar
mvn install -X
/bin/cp target/SpatialSimulatorPlugin-0.0.1-SNAPSHOT.jar lib/
cd lib
jar uf SpatialSimulatorPlugin-0.0.1-SNAPSHOT.jar com/ darwin/
echo "done creating jar"
