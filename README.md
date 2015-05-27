### Compile Time Annotation Processing
This application demonstrates how to create custom annotations and how to process them at compile time.
This is live example of compile time code generation with its further invocation at runtime.

### Technology Stack
* JDK 8
* Apache Maven v.3.2
* Apache Velocity v.1.7
* JUnit v.4.12

### Build Instructions
Invoke the following maven command from the app root dir:

`mvn clean install`

Examine build log, make sure build was successful:

`[INFO] BUILD SUCCESS`

### Launch Instructions
Once the app is assembled, take a look at AptPocTest class - this is the entry point.