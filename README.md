# Spatial Simulator Plugin
CellDesigner plugin for Spatial Simulator

## Requirements
```sh
celldesigner.jar
```
you have to manually download [CellDesigner](http://celldesigner.org/) and copy `exec/celldesigner.jar` to `thirdparty/` directory.

## How to compile
```sh
git clone git@github.com:spatialsimulator/Spatial_Simulator_plugin.git
cd Spatial_Simulator_plugin/
git submodule update --init
cd thirdparty/spatialsimulator/
make
cd ../../
./createjar.sh
```
you will find `Spatial_Simulator_plugin/target/SpatialSimulatorPlugin-1.0.0-SNAPSHOT-jar-with-dependencies.jar` under `target/` directory.

Have fun!
