# PcPartDatabase API
PcPartDatabase API is a Java-based RESTful service designed to manage and retrieve information about PC components. This guide provides instructions on how to build and launch the application.

### Prerequisites
Ensure you have the following installed on your system:

Java Development Kit (JDK) 17: The application is built using Java 17. You can download it from the official Oracle website or use a package manager suitable for your operating system.

Maven: The project uses Maven for dependency management and build processes. If Maven is not installed, you can utilize the Maven Wrapper included in the project.

### Building the Application
Clone the Repository:

bash
git clone https://github.com/ArtkoXT/PcPartDatabase-api.git
Navigate to the Project Directory:

`cd PcPartDatabase-api`

Build with Maven:

Using Installed Maven:

`mvn clean install`

Using Maven Wrapper:

On Unix-based systems:

`./mvnw clean install`

On Windows:

`mvnw.cmd clean install`

These commands will compile the source code, run tests, and package the application into a JAR file located in the target directory.

### Running the Application
After building, you can run the application using the following command:

`java -jar target/pcpartdatabase-api-0.0.1-SNAPSHOT.jar`

Replace pcpartdatabase-api-0.0.1-SNAPSHOT.jar with the actual JAR filename if it differs.

The application should now be running, and you can access the API endpoints as documented.

### Additional Notes
Configuration: Ensure that any necessary configuration files (e.g., application.properties or application.yml) are properly set up before running the application.

Dependencies: Maven will handle all necessary dependencies as defined in the pom.xml file.


For more detailed information, please refer to the project's source code and documentation.
