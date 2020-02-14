# Barren Land Analysis

Given a rectangular land grid which has one or more sub-reactangles as barren land, find fertile land sorted from smallest 
to largest area. An area of fertile land is defined as the largest area of land that is not covered by any of the rectangles 
of barren land.

# System Requirements

This tool is implemented using JAVA 11. Please make sure to have JAVA_HOME pointing to this version.

# Building and Running Tests

Maven is used for building this tool and maven wrapper mvnw is included in the project. From command line run 
"./mvnw clean install" from project home to create an executable JAR file. It will also execute JUNIT tests from the project.

# Running the tool

To run the tool, from the same project home directory run "java -jar target/barren-land-0.0.1-SNAPSHOT.jar" 
which will prompt for input as shown below:

Please input barren land rectangle coordinates:
"48 192 351 207","48 392 351 407","120 52 135 547","260 52 275 547"
Fertile land areas (in sqm) in ascending order:
22816 192608
